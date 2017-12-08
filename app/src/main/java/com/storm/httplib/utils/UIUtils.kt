package com.storm.httplib.utils

import android.app.KeyguardManager
import android.content.Context

/**
 * Created by Administrator on 2017/12/8.
 * 　屏幕操作相关
 */
object UIUtils {


    /**
     * 获取屏幕宽度
     */
    val getScreenWidth: Int
        get() = AppUtils.appContext.resources.displayMetrics.widthPixels


    /**
     * 获取屏幕的宽度
     */
    val getScreenHeight: Int
        get() = AppUtils.appContext.resources.displayMetrics.heightPixels


    /**
     * 屏幕是否是锁屏状态
     */
    val isScreenLock: Boolean
        get() {

            // 获取系统的设备进行强转
            val km: KeyguardManager = AppUtils.appContext.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

            return km.inKeyguardRestrictedInputMode()
        }


    /**
     * dp 转换成px
     */
    fun dip2px(dpValue: Float): Int {
        val scale = AppUtils.appContext.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * px转换dp
     */
    fun px2dip(pxValue: Float): Int {
        val scale = AppUtils.appContext.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()

    }
}








