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

/**
 * Created by Administrator on 2017/12/8.
 *  lazyLoad
 */
class RxBus private constructor() {

    private val _mBus: Subject<Any>

    private val _mBackPressureBus: FlowableProcessor<Any>

    private var mSubscription: MutableMap<Any, CompositeDisposable>? = null


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
    private fun <T> toFlowable(eventType: Class<T>): Flowable<T> {
        return _mBackPressureBus.ofType(eventType)
    }


    /**
     *  处理普通时间
     */
    fun <T> doSubscribe(eventType: Class<T>, next: Consumer<T>, error: Consumer<Throwable>)
            : Disposable {

        return toObservable(eventType)
                .compose(RxHelper.IO_Main())
                .subscribe(next, error)

    }

    /**
     * 背压事件的处理
     */
    fun <T> doFlowable(eventType: Class<T>, tSubscriber: Subscriber<T>): Flowable<T> {

        toFlowable(eventType)
                .onBackpressureLatest()
                .compose(RxHelper.IO_Main_Flowable())
                .subscribeWith(tSubscriber)

        return toFlowable(eventType)

    }

    /**
     * 是否有订阅者
     */
    fun hasSubscribers(isBackPressure: Boolean): Boolean {
        if (!isBackPressure) {
            return _mBus.hasObservers()
        } else {
            return _mBackPressureBus.hasSubscribers()

        }

    }

    /**
     * 背压解除订阅
     */
    fun unSubscription() = _mBackPressureBus.onComplete()

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
     */
    fun clearSubscriptions(a: Any) {

        if (mSubscription == null) return

        val key = a.javaClass.name

        if (!mSubscription!!.containsKey(key)) return

        if (mSubscription!![key] != null) {
            mSubscription!![key]!!.dispose()
        }

        mSubscription!!.remove(key)
    }


    companion object {

        private val TAG: String = RxBus::class.java.simpleName

        val instance: RxBus by lazy {
            //            LogUtils.d(TAG,"单例懒汉式模式")
            RxBus()
        }
    }


}