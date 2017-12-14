package com.storm.httplib.downloadfile

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by Administrator on 2017/12/14.
 * file 观察者
 */
class FileSubscriber<T>(private val fileCallBack: FileCallBack<T>?) : Observer<T> {


    private lateinit var mDisposable: Disposable


    override fun onSubscribe(d: Disposable) {
        mDisposable = d

        if (fileCallBack != null) {
            fileCallBack.onStart()
        }
    }

    override fun onComplete() {

        if (fileCallBack != null) {
            fileCallBack.onComplete()
        }
    }

    override fun onNext(t: T) {

        if (fileCallBack != null) {
            fileCallBack.onSuccess(t)
        }


    }

    override fun onError(e: Throwable) {
        if (fileCallBack != null) {
            fileCallBack.onError(e)

        }
    }


    companion object {
        private val TAG: String = FileSubscriber::class.java.simpleName
    }

}