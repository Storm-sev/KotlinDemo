package com.storm.httplib.utils


import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by Administrator on 2017/8/28.
 * rx 工具类
 */

object RxHelper1 {
    /**
     * rx observable 线程切换
     */
    fun <T> IO_Main(): ObservableTransformer<T, T> {

        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }

    }


    /**
     * rx Flowable 线程
     */
    fun <T> IO_Main_Flowable(): FlowableTransformer<T, T> {


        return FlowableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

}
