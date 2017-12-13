package com.storm.httplib.downloadfile

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by Administrator on 2017/12/12.
 * 进度相关拦截器
 */
class ProgressInterceptor : Interceptor {

    //
    //    override fun intercept(chain: Interceptor.Chain): Response?{
//
//        val originalResponse = chain.proceed(chain.request())
//        //--------------------------------
////        return originalResponse.newBuilder()
////                .body(originalResponse.body()?.let { ProgressResponseBody(it) })
////                .build()
//
//        return originalResponse.newBuilder()
//                .body(originalResponse.body()?.let { ProgressResponseBody(it) })
//                .build()
//
//
//    }

    /**
     * 此处异常处理
     */
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalResponse =
                chain.proceed(chain.request())

        return originalResponse.newBuilder()
                .body(originalResponse.body()?.let { ProgressResponseBody(it) })
                .build()
    }


    companion object {

        private val TAG = ProgressInterceptor::class.java.simpleName
    }

}