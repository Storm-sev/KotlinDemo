package com.storm.httplib.httpclient.interceptor

import android.content.Context
import com.storm.httplib.utils.SPUtils
import com.storm.kotlindemo.utils.LogUtils
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.*

/**
 * Created by stormzhang on 2018/1/23.
 *   拦截器  添加 cookie
 */
class AddCookieInterceptor(private var lang: String) : Interceptor {


    /**
     *  builder 添加cookie
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        if (chain == null) LogUtils.d("HTTP: ", "http : addCookieInterceptor addChain  == null")

        val builder : Request.Builder? = chain?.request()?.newBuilder()
        val cookieSPUtils = SPUtils.getInstance("cookie_sp")

        cookieSPUtils.getString("cookie","")

        Observable.just(cookieSPUtils.getString("cookie",""))
                .subscribe(object : Consumer<String>{
                    override fun accept(cookie: String?) {
                        builder?.addHeader("Cookie",cookie)
                    }
                })

        return chain.proceed(builder?.build())

    }

}