package com.storm.httplib.httpclient.service

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Administrator on 2017/12/14.
 * 公共处理类
 */
class CommonTransformer<T> : ObservableTransformer<T,T>{


    override fun apply(upstream: Observable<T>): ObservableSource<T> {

        return upstream.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }
}