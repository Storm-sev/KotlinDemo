package com.storm.httplib.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.storm.kotlindemo.MApplication
import java.io.File

/**
 * Created by Administrator on 2017/12/8.
 */
object AppUtils {

    private val TAG: String = AppUtils.javaClass.simpleName

    /**
     *  获取 context
     */
    val appContext : Context
        get() = MApplication.appContext

    /**
     * 安装apk
     */
    fun installApk(context: Context, file: File) {

        val intent = Intent()

        intent.action = Intent.ACTION_VIEW
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive")
        context.applicationContext.startActivity(intent)

    }


    /**
     * 通过下载文件获取相应的文件名字
     */
    fun getNameFromUrl(url: String): String {
        return url.substring(url.lastIndexOf("/") + 1)

    }


}