package com.storm.httplib.httpclient.manager

import com.storm.httplib.downloadfile.DownLoadService
import com.storm.httplib.httpclient.Api
import com.storm.httplib.httpclient.interceptor.CacheInterceptor
import com.storm.httplib.httpclient.interceptor.ProgressInterceptor
import com.storm.httplib.utils.AppUtils
import com.storm.kotlindemo.utils.LogUtils
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by Administrator on 2017/12/12.
 * 网络请求框架
 */
object HttpClientManager {

    //TAG
    private val TAG = HttpClientManager.javaClass.simpleName

    private lateinit var httpClient: OkHttpClient

    //双重锁定
    private val mRetrofit: Retrofit by lazy { initRetrofit() }


    private fun initRetrofit(): Retrofit {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val cacheInterceptor = CacheInterceptor()
        val cache = Cache(File(AppUtils.appContext.cacheDir, "HttpCache"),
                1024 * 1024 * 100)

        LogUtils.d(TAG, "okhttp 的缓存路径 :　${AppUtils.appContext.cacheDir.toString()}　")

        httpClient = OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(cacheInterceptor)
                .retryOnConnectionFailure(true)
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build()


        return Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }


    /**
     * 下载接口
     */
    val getDownLoadService: DownLoadService
        get() {
            val progressInterceptor = ProgressInterceptor()

            return mRetrofit.newBuilder()
                    .client(httpClient.newBuilder()
                            .addNetworkInterceptor(progressInterceptor)
                            .build())
                    .build()
                    .create(DownLoadService::class.java)
        }





//
//    private fun initRetrofit(): Retrofit? {
//
//        if (null == mRetrofit) {
//            synchronized(HttpClientManager::class.java) {
//                //log
//                val httpLoggingInterceptor = HttpLoggingInterceptor()
//                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//                val cacheInterceptor = CacheInterceptor()
//                val cache =
//                        Cache(File(MApplication.appContext.cacheDir, "HttpCache"), (1024 * 1024 * 100).toLong())
//
//                LogUtils.d(TAG, "okHTTP的缓存路径" + MApplication.appContext.cacheDir.toString())
//                httpClient = OkHttpClient.Builder()
//                        .cache(cache)
//                        .addInterceptor(cacheInterceptor)
//                        .retryOnConnectionFailure(true)
//                        .addInterceptor(httpLoggingInterceptor)
//                        .connectTimeout(10, TimeUnit.SECONDS)
//                        .readTimeout(10, TimeUnit.SECONDS)
//                        .writeTimeout(10, TimeUnit.SECONDS)
//                        .build()
//
//                mRetrofit = Retrofit.Builder()
//                        .baseUrl(Api.BASE_URL)
//                        .client(httpClient)
//                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build()
//            }
//        }
//
//        return mRetrofit
//    }


    /**
     * 获取下载接口
    //     */
//    val getDownLoadService: DownLoadService
//        get() {
//            val progressInterceptor = ProgressInterceptor()
//
//            return initRetrofit().newBuilder().build().create(DownLoadService::class.java)
//        }


}