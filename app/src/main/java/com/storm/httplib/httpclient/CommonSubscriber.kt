package com.storm.httplib.httpclient

import com.storm.httplib.utils.NetWorkUtils
import com.storm.kotlindemo.utils.LogUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by Administrator on 2017/12/14.
 */
abstract class CommonSubscriber<T> : Observer<T> {


    private lateinit var mDisposable: Disposable


    override fun onSubscribe(d: Disposable) {
        this.mDisposable = d

        LogUtils.d(TAG, "Base CommonSubscriber, onSubscribe: ")
        if (!NetWorkUtils.isConnected) {

            return
        }

    }

    override fun onNext(t: T) {

        LogUtils.d(TAG, "Base CommonSubscribe, onNext: ")
    }

    override fun onError(e: Throwable) {
        LogUtils.d(TAG, "Base CommonSubscribe, on Error ")
    }


    override fun onComplete() {
        LogUtils.d(TAG, "Base CommonSubscribe, onComplete:  ")
    }


    companion object {
        private val TAG: String = CommonSubscriber::class.java.simpleName
    }


}