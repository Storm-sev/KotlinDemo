package com.storm.httplib.httpclient.interceptor

import android.content.Context
import android.provider.CalendarContract
import android.provider.ContactsContract
import android.text.style.BulletSpan
import com.storm.httplib.utils.SPUtils
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import okhttp3.Interceptor
import okhttp3.Response
import java.math.MathContext
import kotlin.math.acos

/**
 * Created by storm_Zhang 2018/1/23.
 *
 * 读取 cookie 存储到本地
 */
class ReceivedCookieInterceptor(private var mContext: Context) : Interceptor {


    lateinit var cookieSPUtils : SPUtils


    override fun intercept(chain: Interceptor.Chain): Response {

        val originalResponse: Response = chain.proceed(chain.request())


        if (!originalResponse.headers("Set-Cookie").isEmpty()) {

            var stringBuffer: StringBuffer = StringBuffer()

            Observable.fromIterable(originalResponse.headers("Set-Cookie"))
                    .map(object : Function<String,String>{
                        override fun apply(t: String): String {
                            val strings: List<String> = t.split(";")

                            return strings[0]
                        }
                    })
                    .subscribe(object : Consumer<String>{
                        override fun accept(t: String?) {

                            t?.let { stringBuffer.append(it).append(";") }

                        }
                    })

            cookieSPUtils = SPUtils.getInstance("cookie_sp")
            cookieSPUtils.put("cookie",stringBuffer.toString())
        }

        return originalResponse
    }
}