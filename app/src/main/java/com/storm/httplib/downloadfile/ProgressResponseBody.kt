package com.storm.httplib.downloadfile

import com.storm.httplib.utils.RxBus
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*

/**
 * Created by Administrator on 2017/12/12.
 * 显示下载进度 重写ResponseBody
 */
class ProgressResponseBody(private val responseBody: ResponseBody) : ResponseBody() {

    private var bufferedSource: BufferedSource? = null

    private val fileLoadEvent: FileLoadEvent

    init {
        this.fileLoadEvent = FileLoadEvent()
    }


    override fun contentLength(): Long
            = responseBody.contentLength()

    override fun contentType(): MediaType?
            = responseBody.contentType()


    override fun source(): BufferedSource? {
        if (null == bufferedSource) {
            bufferedSource = Okio.buffer(source(responseBody.source()))

        }
        return bufferedSource
    }


    private fun source(source: Source): Source {

        return object : ForwardingSource(source) {

            internal var byteReaded: Long = 0

            override fun read(sink: Buffer?, byteCount: Long): Long {

                val bytesRead : Long = super.read(sink, byteCount)
                byteReaded += if (bytesRead == -1L) 0 else bytesRead

                //
                fileLoadEvent.total = responseBody.contentLength()
                fileLoadEvent.progress = byteReaded
                RxBus.instance.sendByBackPressure(fileLoadEvent)

                // 使用 rxbus 发送数据
                return bytesRead
            }

        }


    }


    companion object {

        private val TAG: String = ProgressResponseBody::class.java.simpleName

    }

}