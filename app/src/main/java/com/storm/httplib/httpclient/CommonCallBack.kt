package com.storm.httplib.httpclient

/**
 * Created by Administrator on 2017/12/14.
 *  公共回调
 */
interface CommonCallBack<T> {

    fun onStart()

    fun onError(e: Throwable)

    fun onComplete()

    fun onNext(t: T)
}