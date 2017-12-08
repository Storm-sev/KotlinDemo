package com.storm.httplib.utils

import java.io.Closeable
import java.io.IOException

/**
 * Created by Administrator on 2017/12/8.
 *  关闭流
 */
object CloseUtils {

    /**
     * 关闭流
     */
    fun closeIO(vararg closeables: Closeable?) {

        for (closeable in closeables) {
            if (closeable != null) {
                try {
                    closeable.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

}