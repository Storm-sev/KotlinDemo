package com.storm.kotlindemo.app

import com.cheng315.lib.utils.LogUtils
import com.cheng315.lib.utils.RxBus
import com.storm.httplib.downloadfile.FileLoadEvent

import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

import io.reactivex.Flowable
import okhttp3.ResponseBody

/**
 * Created by Administrator on 2017/8/25.
 *
 *
 * 下载文件的回调
 */

abstract class FileCallBack<T>
//    private Flowable flowable;

(private val fileDir: String// 文件的存储路径
 , private val fileName: String // 文件的名字
) {
    init {
        subscribeLoadProgress()
    }


    //
    abstract fun onSuccess(t: T)

    abstract fun onStart()

    abstract fun onComplete()

    abstract fun onError(e: Throwable)

    abstract fun onProgress(progress: Float, total: Long)


    /**
     * @param body 响应内容
     */
    fun saveFile(body: ResponseBody) {

        var `is`: InputStream? = null
        var fos: FileOutputStream? = null

        val buff = ByteArray(2048)
        var len: Int

        var file: File? = null

        try {
            `is` = body.byteStream()

            val dir = File(fileDir)
            if (!dir.exists()) {

                dir.mkdirs()
            }

            file = File(dir, fileName)
            fos = FileOutputStream(file)

            while ((len = `is`!!.read(buff)) != -1) {
                fos.write(buff, 0, len)
            }
            fos.flush()


        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                if (`is` != null) `is`.close()
                if (fos != null) fos.close()

            } catch (e: IOException) {
                LogUtils.d(TAG, e.message)
            }

            // 解除订阅关系
            unSubscribe()


        }

    }

    /**
     * 解除订阅
     */
    fun unSubscribe() {

        if (RxBus.getInstance().hasSubscribers(true)) {

            RxBus.getInstance().unSubscription()
        }


    }


    //订阅加载进度条
    fun subscribeLoadProgress() {

        val flowable = RxBus.getInstance().doFlowable(FileLoadEvent::class.java, object : Subscriber<FileLoadEvent> {
             lateinit var sub: Subscription

            override fun onSubscribe(s: Subscription) {
                LogUtils.d(TAG, "新Rxbus,数据 + onSubscribe ")
                this.sub = s

                sub.request(1)

            }

            override fun onNext(fileLoadEvent: FileLoadEvent) {

                val progress = fileLoadEvent.getProgress()
                val total = fileLoadEvent.getTotal()

                onProgress(progress * 1.0f / total, total)
                sub.request(1)

            }

            override fun onError(t: Throwable) {
                LogUtils.d(TAG, "新Rxbus,数据 + onError")
            }

            override fun onComplete() {
                LogUtils.d(TAG, "新Rxbus,数据 .  + onComplete")


            }
        })


    }

    companion object {

        private val TAG = FileCallBack<*>::class.java.simpleName
    }

}



