package com.storm.httplib.downloadfile

import com.storm.httplib.utils.CloseUtils
import com.storm.httplib.utils.RxBus
import com.storm.kotlindemo.utils.LogUtils
import okhttp3.ResponseBody
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

/**
 * Created by Administrator on 2017/12/13.
 */
abstract class FileCallBack<T>(private val fileDir: String, private val fileName: String) {


    init {
        subscribeLoadProgress()
    }


    abstract fun onSuccess(t: T)

    abstract fun onStart()

    abstract fun onComplete()

    abstract fun onError(e: Throwable)

    abstract fun onProgress(progress: Float, total: Long)


    /**
     * 响应内容
     */
    fun saveFile(body: ResponseBody) {

        var ins: InputStream? = null
        var fos: FileOutputStream? = null
        val buff: ByteArray = ByteArray(2048)
        var len: Int = 0

        try {
            val dir = File(fileDir)

            if (!dir.exists()) {
                dir.mkdirs()
            }

            val file = File(dir, fileName)

            ins = body.byteStream()

            fos = FileOutputStream(file)

            while ((ins.read(buff).apply { len = this }) != -1) {
                fos.write(buff, 0, len)
            }

            fos.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            CloseUtils.closeIO(ins, fos)
        }
        // 解除订阅
        unSubscribe()

    }


    /**
     * 解除订阅
     */
    private fun unSubscribe() {
        if (RxBus.instance.hasSubscribers(true)) {

            RxBus.instance.unSubscription()
        }
    }


    private fun subscribeLoadProgress() {

        val flowable = RxBus.instance.doFlowable(FileLoadEvent::class.java,
                object : Subscriber<FileLoadEvent> {

                    lateinit var sub: Subscription

                    override fun onError(t: Throwable) {

                        LogUtils.d(TAG, "新RxBus数据 : onError")
                    }

                    override fun onNext(t: FileLoadEvent) {

                        val progress = t.progress
                        val total = t.total

                        onProgress(progress * 1.0F / total, total)
                        sub.request(1)
                    }

                    override fun onComplete() {
                        LogUtils.d(TAG, "新的RxBus数据 :  onComplete")
                    }

                    override fun onSubscribe(s: Subscription) {
                        LogUtils.d(TAG, "新的RxBus数据 : onSubscribe")
                        this.sub = s
                        sub.request(1)
                    }
                })

    }


    companion object {
        private val TAG = FileCallBack::class.java.simpleName

    }
}