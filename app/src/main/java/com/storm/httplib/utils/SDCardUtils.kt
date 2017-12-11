package com.storm.httplib.utils

import android.os.Environment
import java.io.File

/**
 * Created by Administrator on 2017/12/11.
 * SD卡工具类
 */
object SDCardUtils {

    /**
     * sd卡是否挂载
     */
    public fun isSDCardEnable(): Boolean
            = Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()


    fun getSDCardPath(): String? {

        if (!isSDCardEnable()) return null

        return Environment.getExternalStorageDirectory().path + File.separator

    }


    /**
     * 获取sd 卡data路径
     */
    fun getDataPath(): String? {
        if (!isSDCardEnable()) return null

        return Environment.getExternalStorageDirectory().path +
                File.separator + "data" + File.separator

    }
}