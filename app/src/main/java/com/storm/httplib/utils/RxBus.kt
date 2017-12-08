package com.storm.httplib.utils

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.processors.FlowableProcessor
import io.reactivex.processors.PublishProcessor
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/**
 * Created by Administrator on 2017/12/8.
 *  lazyLoad
 */
class RxBus private constructor() {


    private val _mBus: Subject<Any>

    private val _mBackPressureBus: FlowableProcessor<Any>


    init {
        _mBus = PublishSubject.create<Any>().toSerialized()
        _mBackPressureBus = PublishProcessor.create<Any>().toSerialized()
    }


    /**
     * 发送普通事件
     */
    fun send(event: Any) = _mBus.onNext(event)


    /**
     * 发送背压事件
     */
    fun sendByBackPressure(event: Any) =
            _mBackPressureBus.onNext(event)


    /**
     * 接收普通事件
     */
    fun <T> toObservable(eventType: Class<T>): Observable<T> {

        return _mBus.ofType(eventType)

    }

    /**
     * 接收背压事件
     */
    fun <T> toFlowable(eventType: Class<T>): Flowable<T> {
        return _mBackPressureBus.ofType(eventType)

    }


    companion object {

        private val TAG: String = RxBus::class.java.simpleName

        val instance: RxBus by lazy {
            //            LogUtils.d(TAG,"单例懒汉式模式")
            RxBus()
        }
    }


}