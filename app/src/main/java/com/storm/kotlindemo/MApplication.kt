package com.storm.kotlindemo

import android.app.Application
import android.content.Context
import com.storm.httplib.utils.AppUtils.appContext
import com.storm.kotlindemo.utils.LogUtils

/**
 * Created by Administrator on 2017/12/6.
 */
class MApplication : Application() {



    override fun onCreate() {
        super.onCreate()

        appContext = this

        // log
        mBuilder = LogUtils.Builder()
                .setLogSwitch(true)
                .setGlobalTag("LJY")// 设置log全局标签，默认为空
                // 当全局标签不为空时，我们输出的log全部为该tag，
                // 为空时，如果传入的tag为空那就显示类名，否则显示tag
                .setLog2FileSwitch(false)// 打印log时是否存到文件的开关，默认关
                .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
                .setLogFilter(LogUtils.V)// log过滤器，和logcat过滤器同理，默认Verbose


    }


    companion object {
        lateinit var appContext: Context
        lateinit var mBuilder: LogUtils.Builder
    }


}