package com.storm.kotlindemo.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.storm.kotlindemo.MApplication

/**
 * Created by Administrator on 2017/12/7.
 */
object AppUtils {

    val TAG: String = "AppUtil"


    /**
     * 返回全局 的context
     */
    fun getAppContext(): Context = MApplication.appContext

    fun toActivity(from: Activity, javaClass: Class<Activity>) {
        val intent = Intent(from, javaClass)
        from.startActivity(intent)
    }

//
//    fun toActivity(f) {
//
//        val intent = Intent(from, to.javaClass)
//
//        from.startActivity(intent)
//    }

}