package com.storm.httplib.utils

import android.content.Context
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import java.net.NetworkInterface
import java.util.*

/**
 * Created by Administrator on 2017/12/11.
 * 手机相关工具类
 */
object PhoneUtils {


    /**
     * 获取屏幕相关 DisplayMetrics
     */
    fun obtain(context: Context): DisplayMetrics {
        val wm: WindowManager =
                context.applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()

        wm.defaultDisplay.getMetrics(dm)
        return dm
    }

    /**
     * 获取手机屏幕宽度
     */
    fun getPhoneScreenWidth(context: Context): Int =
            obtain(context).widthPixels

    /**
     * 获取手机屏幕高度
     */
    fun getPhoneScreenHeight(context: Context): Int =
            obtain(context).heightPixels

    /**
     * 获取手机屏幕的高度
     */
    fun getPhoneScreenSize(context: Context): IntArray {

        val metrics: DisplayMetrics = obtain(context)
        val size: IntArray = IntArray(2)

        size[0] = metrics.widthPixels
        size[1] = metrics.heightPixels

        return size
    }

    /**
     * 获取手机设备厂商
     */
//    fun getPhoneManuFacturers(): String = Build.MANUFACTURER
    val phoneManuFacturers: String
        get() = Build.MANUFACTURER

    /**
     * 获取手机型号
     */
//    fun getPhoneModel(): String = Build.MODEL
    val phoneModel: String
        get() = Build.MODEL


    /**
     * 获取手机系统版本号
     */
//    fun getPhoneSystemVersion(): String = Build.VERSION.RELEASE
    val phoneSystemVersion: String
        get() = Build.VERSION.RELEASE

    /**
     * 获取sdk 版本号
     */
//    fun getSDKVersion(): Int = Build.VERSION.SDK_INT
    val sdkVersion: Int
        get() = Build.VERSION.SDK_INT


    /**
     * 获取设备的mac地址
     */
//    fun getMacAddress(): String? {
//
//        //通过wifi
//        var macAddress = getMacAddressByWifiInfo()
//
//        if ("02:00:00:00:00:00" != macAddress) {
//            return macAddress
//        }
//
//        macAddress = getMacAddressByNetworkInterface()
//
//        if ("02:00:00:00:00:00" != macAddress) {
//            return macAddress
//        }
//
//        return null
//    }

    /**
     * 获取设置的mac 地址
     */
    val macAddress: String?
        get() {
            var macAddress = macAddressByWifiInfo
            if ("02:00:00:00:00:00" != macAddress) {
                return macAddress
            }

            macAddress = macAddressByNetworkInterface

            return if ("02:00:00:00:00:00" != macAddress) {
                macAddress
            } else null
        }

    /**
     * NETWORKINFO 获取mac 地址
     * 7.0 即以上都可以获取到, wlan0 就是wifi mac 地址
     */
//    private fun getMacAddressByNetworkInterface(): String {
//
//        try {
//            val nis = Collections.list(NetworkInterface.getNetworkInterfaces())
//
//            for (ni: NetworkInterface in nis) {
//
//                if (!ni.name.equals("wlan0", true)) continue
//
//                val macByte: ByteArray? = ni.hardwareAddress
//
//                if (null != macByte && macByte.isNotEmpty()) {
//
//                    val res1 = StringBuffer()
//
//                    for (b: Byte in macByte) {
//                        res1.append(String.format("%02x:", b))
//                    }
//
//                    return res1.deleteCharAt(res1.length - 1).toString()
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//        return "02:00:00:00:00:00"
//
//    }

    /**
     * NETWORKINFO 获取mac 地址
     * 7.0 即以上都可以获取到, wlan0 就是wifi mac 地址
     */
    val macAddressByNetworkInterface: String
        get() {
            val nis = Collections.list(NetworkInterface.getNetworkInterfaces())

            for (ni: NetworkInterface in nis) {

                if (!ni.name.equals("wlan0", true)) continue

                val macByte: ByteArray? = ni.hardwareAddress

                if (null != macByte && macByte.isNotEmpty()) {
                    val res1 = StringBuffer()

                    for (b: Byte in macByte) {
                        res1.append(String.format("%02x:", b))

                    }
                    return res1.deleteCharAt(res1.length - 1).toString()
                }
            }
            return "02:00:00:00:00:00"
        }


//    @SuppressLint("WifiManagerLeak")
//    private fun getMacAddressByWifiInfo(): String {
//
//        try {
//            val wm: WifiManager? =
//                    AppUtils.appContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
//
//            if (wm != null) {
//                val wifiInfo: WifiInfo? = wm.connectionInfo
//                if (null != wifiInfo) {
//                    return wifiInfo.macAddress
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//        return "02:00:00:00:00:00"
//    }

    /**
     *  wifi 获取mac地址
     *  低版本的会返回null 实际  "02:00:00:00:00:00"
     */
    val macAddressByWifiInfo: String
        get() {

            try {
                val wm: WifiManager? = AppUtils.appContext.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                if (null != wm) {
                    val wifiInfo: WifiInfo? = wm.connectionInfo

                    if (null != wifiInfo) {
                        return wifiInfo.macAddress
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return "02:00:00:00:00:00"
        }
}