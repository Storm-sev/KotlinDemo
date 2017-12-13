package com.storm.httplib.downloadfile

import com.storm.httplib.utils.RxBus
import com.storm.kotlindemo.utils.LogUtils
import okhttp3.ResponseBody
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.io.File

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


        val dir = File(fileDir)

        if (!dir.exists()) {
            dir.mkdirs()
        }

        val file = File(dir, fileName)

        // 文件操作流---------------------------------------------------------------


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