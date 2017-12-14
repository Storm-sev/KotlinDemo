package com.storm.httplib.httpclient

import com.storm.httplib.downloadfile.FileCallBack
import com.storm.httplib.downloadfile.FileSubscriber
import com.storm.httplib.httpclient.manager.HttpClientManager
import com.storm.kotlindemo.bean.VersionBean
import com.storm.kotlindemo.utils.LogUtils
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

/**
 * Created by Administrator on 2017/12/14.
 */
object HttpManager {

    private val TAG: String = HttpManager::class.java.simpleName


    /**
     * 文件上传
     */
    fun upLoadFile() {

    }


    /**
     * 执行下载请求
     */
    fun load(url: String, fileCallBack: FileCallBack<ResponseBody>) {
        val downLoadApk = HttpClientManager.getDownLoadService.downLoadApk(url)
        downLoadApk
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(object : Consumer<ResponseBody> {
                    override fun accept(t: ResponseBody) {
                        fileCallBack.saveFile(t)
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(FileSubscriber<ResponseBody>(fileCallBack))

    }


    /**
     * 执行版本检查
     */
    fun checkVersion(curVersionCode: String, commonCallBack: CommonCallBack<VersionBean>?) {

        val checkVersionService =
                HttpClientManager.getHttpClientService.checkVersionService(curVersionCode)

        checkVersionService.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<VersionBean> {

                    private var mDisposable: Disposable? = null

                    override fun onSubscribe(d: Disposable) {
                        this.mDisposable = d
                        LogUtils.d(TAG, "HttpManager -> 版本检查 _checkVersion : onSubscribe: ")
                    }

                    override fun onComplete() {
                        LogUtils.d(TAG, "HttpManager -> 版本检查 _checkVersion :　onComplete")
                        commonCallBack?.onComplete()
                    }

                    override fun onError(e: Throwable) {
                        LogUtils.d(TAG, "HttpManager -> 版本检查 _checkVersion: onError")
                        commonCallBack?.onError(e)
                    }

                    override fun onNext(t: VersionBean) {
                        LogUtils.d(TAG, "HttpManager -> 版本检查 _checkVersion:onNext")
                        commonCallBack?.onNext(t)

                    }
                })
    }





}