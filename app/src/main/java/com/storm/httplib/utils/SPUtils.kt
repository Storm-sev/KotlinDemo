package com.storm.httplib.utils

import android.content.Context
import android.content.SharedPreferences
import android.support.v4.util.SimpleArrayMap
import com.storm.kotlindemo.MApplication

/**
 * Created by Administrator on 2017/12/8.
 */
class SPUtils private constructor(spName: String) {


    init {
        sp = MApplication.appContext.getSharedPreferences(spName, Context.MODE_PRIVATE)

    }


     val all: Map<String, *>
        get() = sp.all

    /**
     * 存储 String 类型的值
     */
    fun put(key: String, value: String?)
            = sp.edit().putString(key, value).apply()

    /**
     * 读取sp中String
     */
     fun getString(key: String): String =
            sp.getString(key, "")

    /**
     * 读取sp中的String类型的值, 如果不存在 返回默认值
     */
     fun getString(key: String, defaultValue: String): String =
            sp.getString(key, defaultValue)

    /**
     * 存储int类型的值
     */
     fun put(key: String, value: Int) =
            sp.edit().putInt(key, value).apply()

    /**
     * 读取sp中的int值 没有返回 -1
     */
     fun getInt(key: String): Int =
            sp.getInt(key, -1)

    /**
     * 获取sp中的int值, 没有返回默认值
     */
     fun getInt(key: String, defaultValue: Int): Int =
            sp.getInt(key, defaultValue)

    /**
     * 存储float类型的值
     */
     fun put(key: String, value: Float) =
            sp.edit().putFloat(key, value).apply()

    /**
     * 读取sp中的值, 没有返回-1f
     */
     fun getFloat(key: String): Float =
            sp.getFloat(key, -1F)

    /**
     * 读取float值 返回默认值
     */
     fun getFloat(key: String, defaultValue: Float): Float =
            sp.getFloat(key, defaultValue)

    /**
     * 存储long值
     */
     fun put(key: String, value: Long) =
            sp.edit().putLong(key, value).apply()

    /**
     * 读取Long值 没有返回-1
     *
     */
     fun getLong(key: String): Long =
            sp.getLong(key, -1L)

    /**
     * 读取long值 返回默认值
     */
     fun getLong(key: String, defaultValue: Long): Long =
            sp.getLong(key, defaultValue)

    /**
     * 存储boolean值
     */
     fun put(key: String, value: Boolean) =
            sp.edit().putBoolean(key, value).apply()

    /**
     * 读取boolean值 返回false或者默认
     */
     fun getBoolean(key: String): Boolean =
            sp.getBoolean(key, false)


     fun getBoolean(key: String, defaultValue: Boolean): Boolean =
            sp.getBoolean(key, defaultValue)

    /**
     * 移除相应的key
     */
     fun remove(key: String) =
            sp.edit().remove(key).apply()

    /**
     * 清除sp的值
     */
     fun clear() =
            sp.edit().clear().apply()

    /**
     * 伴生对象
     */
    companion object {

        private val TAG = "SPUtil"

        private val SP_UTILS_MAP = SimpleArrayMap<String, SPUtils>()

        private lateinit var sp: SharedPreferences

        val instance: SPUtils
            get() = getInstance("")

        /**
         * 获取相应的spUtils
         */
        public  fun getInstance(spName: String): SPUtils {
            var spName = spName

            if (isSpace(spName)) {
                spName = "storm"
            }

            var spUtil: SPUtils? = SP_UTILS_MAP.get(spName)
            if (spUtil == null) {
                spUtil = SPUtils(spName)
                SP_UTILS_MAP.put(spName, spUtil)

            }
            return spUtil
        }


        /**
         * 判断字符串是否为null 或者全部为空白字符
         */
        fun isSpace(str: String?): Boolean {

            if (str == null) return true

            for (i in str.indices) {

                if (!Character.isWhitespace(str[i])) {
                    return false
                }
            }
            return true
        }

    }


}