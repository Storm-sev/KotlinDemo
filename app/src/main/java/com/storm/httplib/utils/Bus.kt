package com.storm.httplib.utils

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.processors.FlowableProcessor
import io.reactivex.processors.PublishProcessor
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import org.reactivestreams.Subscriber
import java.util.*

/**
 * Created by Administrator on 2017/12/8.
 */

class Bus private constructor() {

    /**
     * 默认 bus ;
     */
    private val _mBus: Subject<Any>

    /**
     * 背压
     */
    private val _mBackPressureBus: FlowableProcessor<Any>


    private var mSubscription: MutableMap<Any, CompositeDisposable>? = null


    init {

        _mBus = PublishSubject.create<Any>().toSerialized()

        _mBackPressureBus = PublishProcessor.create<Any>().toSerialized()
    }


    /**
     * 发送普通事件
     */
    fun send(event: Any) {

        _mBus.onNext(event)

    }


    /**
     * 发送背压事件
     */
    fun sendByBackPressure(event: Any) {
        _mBackPressureBus.onNext(event)

    }


    /**
     * 接收普通事件
     */
    fun <T> toObservable(eventType: Class<T>): Observable<T> {

        return _mBus.ofType(eventType)
    }


    /**
     * 接受背压事件
     */
    fun <T> toFlowable(eventType: Class<T>): Flowable<T> {

        return _mBackPressureBus.ofType(eventType)
    }


    /**
     * 普通事件的处理
     */
    fun <T> doSubscribe(eventType: Class<T>, next: Consumer<T>, error: Consumer<Throwable>): Disposable {

        return toObservable(eventType)
                .compose(RxHelper.IO_Main())
                .subscribe(next, error)
    }


    /**
     * 背压事件处理
     */
    fun <T> doFlowable(eventType: Class<T>, tSubscriber: Subscriber<T>): Flowable<*> {

        toFlowable(eventType)
                .onBackpressureLatest() //背压策略
                .compose(RxHelper.IO_Main_Flowable())
                .subscribeWith(tSubscriber)

        return toFlowable(eventType)
    }


    /**
     * 是否有订阅者
     */
    fun hasSubscribers(isBackPressure: Boolean): Boolean {

        return if (!isBackPressure)
            _mBus.hasObservers()
        else
            _mBackPressureBus.hasSubscribers()
    }


    /**
     * 背压解除订阅
     */
    fun unSubscription() {

        _mBackPressureBus.onComplete()

    }


    /**
     * 添加订阅到集合(一般事件)
     */
    fun addSubscriptions(o: Any, disposable: Disposable) {

        if (mSubscription == null) {
            mSubscription = HashMap()
        }

        val key = o.javaClass.name

        if (mSubscription!![key] != null) {
            mSubscription!![key]!!.add(disposable)

        } else {
            val compositeDisposable = CompositeDisposable()

            compositeDisposable.add(disposable)
            mSubscription!!.put(key, compositeDisposable)
        }


    }


    /**
     * 解除订阅
     * 一般事件的解除订阅
     *
     * @param o
     */
    fun clearSubscriptions(o: Any) {
        if (mSubscription == null) {
            return
        }


        val key = o.javaClass.name

        if (!mSubscription!!.containsKey(key)) {
            return
        }


        //--------------zh
        if (mSubscription!![key] != null) {
            mSubscription!![key]!!.dispose()

        }

        mSubscription!!.remove(key)
    }

    companion object {

        private val TAG = Bus::class.java.simpleName


        @Volatile private var mInstance: Bus? = null


        val instance: Bus?
            get() {

                if (mInstance == null) {

                    synchronized(Bus::class.java) {
                        if (mInstance == null) {

                            mInstance = Bus()
                        }
                    }
                }

                return mInstance
            }
    }


}
