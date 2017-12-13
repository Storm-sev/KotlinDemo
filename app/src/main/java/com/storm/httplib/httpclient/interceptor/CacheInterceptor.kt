package com.storm.httplib.httpclient.interceptor

import com.storm.httplib.utils.NetWorkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by Administrator on 2017/12/12.
 *
 */

class CacheInterceptor : Interceptor {

    companion object {
        private val TAG: String = CacheInterceptor::class.java.simpleName
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        if (!NetWorkUtils.isConnected) {
            request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
        }

        val response = chain.proceed(request)

        if (NetWorkUtils.isConnected) {
            return response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + 60)
                    .removeHeader("Pragma")
                    .build()
        } else {
            // 没有网络
            val maxTime = 24 * 60 * 60
            return response.newBuilder()
                    // 无网络时, 设置缓存时间为一天时间
                    // key 缓冲规则
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxTime)
                    .removeHeader("Pragma")
                    .build()
        }


    }
}