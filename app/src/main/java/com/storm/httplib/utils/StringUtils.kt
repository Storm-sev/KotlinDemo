package com.storm.httplib.utils

/**
 * Created by Administrator on 2017/12/8.
 */
object StringUtils {


    private val TAG : String = StringUtils::class.java.simpleName.toString()


    

    fun getNameFromUrl(url: String): String {

        return url.substring(url.lastIndexOf("/") + 1)
    }

}