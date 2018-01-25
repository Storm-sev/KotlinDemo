package com.storm.httplib.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.provider.Settings
import android.telephony.TelephonyManager
import java.lang.reflect.Method

/**
 * Created by Administrator on 2017/12/12.
 *  网络工具类
 */
object NetWorkUtils {

    private val TAG = NetWorkUtils::class.java.simpleName.toString()


    public enum class NetworkType {
        NETWORK_WIFI, NETWORK_4G,
        NETWORK_3G, NETWORK_2G,
        NETWORK_UNKNOWN, NETWORK_NO
    }


    /**
     * 获取活动的网络信息
     */
    val activeNetworkInfo: NetworkInfo
        get() {
            val manager = AppUtils.appContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            return manager.activeNetworkInfo
        }


    /**
     * 网络是否连接诶
     */
    val isConnected: Boolean
        get() {
            return activeNetworkInfo.isConnected
        }


    /**
     * 判断是否打开移动数据网络
     */
    val isOpenDateEnable: Boolean
        get() {
            try {
                val telephonyManager = AppUtils.appContext
                        .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

                val getPhoneDataEnableMethod: Method? = telephonyManager.javaClass
                        .getDeclaredMethod("getDataEnabled") as Method

                if (null != getPhoneDataEnableMethod) {
                    return getPhoneDataEnableMethod.invoke(telephonyManager) as Boolean
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return false
        }


    /**
     * 判断网络是否是4G
     */
    val is4G: Boolean
        get() {
            return activeNetworkInfo.isAvailable && activeNetworkInfo.subtype == TelephonyManager.NETWORK_TYPE_LTE
        }

    /**
     * 判断wifi 是否打开
     */
    val getWIFIEnable: Boolean
            // @SuppressLint("WifiManagerLeak")
        get() {

            val wifiManager =
                    AppUtils.appContext.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            return wifiManager.isWifiEnabled
        }

    /**
     * 打开或者关闭wifi
     */
    fun setWIFIEnable(enable: Boolean) {

        val wifiManager =
                AppUtils.appContext.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        if (enable) { // 需要打开
            if (!wifiManager.isWifiEnabled) {
                wifiManager.isWifiEnabled = true
            }
        } else { // 需要关闭
            if (wifiManager.isWifiEnabled) {
                wifiManager.isWifiEnabled = false
            }
        }
    }

    /**
     * 判断wifi 是否在连接状态
     */
    val isWifiConnected: Boolean
        get() {

            val cm: ConnectivityManager? =
                    AppUtils.appContext.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            // 有写地方有问题
            return cm != null && cm.activeNetworkInfo != null &&
                    cm.activeNetworkInfo.subtype == ConnectivityManager.TYPE_WIFI
        }


    /**
     * 打开或者关闭移动数据
     */
    fun setDateEnable(enable: Boolean) {

        try {
            val manager = AppUtils.appContext
                    .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val method =
                    manager.javaClass.getDeclaredMethod("setDataEnabled", Boolean::class.javaPrimitiveType)
            method?.invoke(manager, enable)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 网络设置界面
     */
    fun openWirelessSetting() {
        AppUtils.appContext.startActivity(
                Intent(Settings.ACTION_WIRELESS_SETTINGS).
                        setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    /**
     * 获取当前网络类型
     */
    fun getNetWorkType(): NetworkType {
        var netType = NetworkType.NETWORK_NO
        val info = activeNetworkInfo

        if (info.isAvailable) {
            if (info.type == ConnectivityManager.TYPE_WIFI) {
                netType = NetworkType.NETWORK_WIFI
            } else if (info.type == ConnectivityManager.TYPE_MOBILE) {

                when (info.subtype) {
                    TelephonyManager.NETWORK_TYPE_GSM,
                    TelephonyManager.NETWORK_TYPE_GPRS,
                    TelephonyManager.NETWORK_TYPE_CDMA,
                    TelephonyManager.NETWORK_TYPE_EDGE,
                    TelephonyManager.NETWORK_TYPE_1xRTT,
                    TelephonyManager.NETWORK_TYPE_IDEN -> netType = NetworkType.NETWORK_2G

                    TelephonyManager.NETWORK_TYPE_TD_SCDMA,
                    TelephonyManager.NETWORK_TYPE_EVDO_A,
                    TelephonyManager.NETWORK_TYPE_EVDO_0,
                    TelephonyManager.NETWORK_TYPE_EVDO_B,
                    TelephonyManager.NETWORK_TYPE_UMTS,
                    TelephonyManager.NETWORK_TYPE_HSDPA,
                    TelephonyManager.NETWORK_TYPE_HSUPA,
                    TelephonyManager.NETWORK_TYPE_HSPA,
                    TelephonyManager.NETWORK_TYPE_EHRPD,
                    TelephonyManager.NETWORK_TYPE_HSPAP -> netType = NetworkType.NETWORK_3G

                    TelephonyManager.NETWORK_TYPE_IWLAN,
                    TelephonyManager.NETWORK_TYPE_LTE -> netType = NetworkType.NETWORK_4G

                    else -> {
                        val subtypeName: String = info.subtypeName

                        if (subtypeName.equals("TD-SCDMA", true)
                                || subtypeName.equals("WCDMA", true)
                                || subtypeName.equals("CDMA2000", true)) {

                            netType = NetworkType.NETWORK_3G
                        } else {
                            netType = NetworkType.NETWORK_UNKNOWN
                        }

                    }
                }

            } else {
                netType = NetworkType.NETWORK_UNKNOWN
            }


        }
        return netType
    }


}