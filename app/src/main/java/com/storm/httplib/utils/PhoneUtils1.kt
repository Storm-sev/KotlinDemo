package com.storm.httplib.utils

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import java.net.NetworkInterface
import java.util.*

/**
 * Created by Administrator on 2017/12/11.
 */

object PhoneUtils1 {


    /**
     * 获取手机的IMEI 值
     *
     * Requires Permission:[READ_PHONE_STATE][android.Manifest.permission.READ_PHONE_STATE]
     */
    //    public static String getPhoneIMEI() {
    //        TelephonyManager tm =
    //                (TelephonyManager) getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
    //
    //        return tm.getDeviceId();
    //    }


    /**
     * 获取手机设备厂商
     */
    val phoneManufacturers: String
        get() = Build.MANUFACTURER


    /**
     * 获取手机型号
     */
    val phoneModel: String
        get() = Build.MODEL


    /**
     * 获取手机系统版本号
     */
    val phoneSystemVersion: String
        get() = Build.VERSION.RELEASE


    /**
     * 获取sdk的版本号
     */
    val sdkVersion: Int
        get() = Build.VERSION.SDK_INT


    /**
     * 获取 设备为mac 地址
     */
    // 通过wifimanager 获取
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
     * 通过 wifi 获取mac 地址
     * 低版本的会返回 null 实际"02:00:00:00:00:00"
     * @return
     */
    val macAddressByWifiInfo: String
        get() {

            try {
                val wm = AppUtils.appContext.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

                if (wm != null) {
                    val wifiInfo = wm.connectionInfo

                    if (wifiInfo != null) {
                        return wifiInfo.macAddress
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

            return "02:00:00:00:00:00"
        }


    /**
     * networkinfo 获取 mac地址
     * 7.0 即以上都可以获取到 wlan0 就是wifi mac地址
     */
    val macAddressByNetworkInterface: String
        get() {

            try {
                val nis = Collections.list(NetworkInterface.getNetworkInterfaces())

                for (ni in nis) {

                    if (!ni.name.equals("wlan0", ignoreCase = true)) continue
                    val macByte = ni.hardwareAddress
                    if (macByte != null && macByte.size > 0) {
                        val res1 = StringBuffer()

                        for (b in macByte) {

                            res1.append(String.format("%02x:", b))
                        }
                        return res1.deleteCharAt(res1.length - 1).toString()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return "02:00:00:00:00:00"
        }

    /**
     * 获取屏幕相关 displayMetrics
     */
    fun obtain(context: Context): DisplayMetrics {
        val wm = context.applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val dm = DisplayMetrics()

        wm.defaultDisplay.getMetrics(dm)
        return dm
    }


    /**
     * 获取手机屏幕宽度
     */
    fun getPhoneScreenWidth(context: Context): Int {
        val outMetrics = obtain(context)
        return outMetrics.widthPixels
    }

    /**
     * 获取手机屏幕的高度
     */
    fun getPhoneScreenHeight(context: Context): Int {

        return obtain(context).heightPixels
    }


    /**
     * 获取屏幕的尺寸
     */
    fun getPhoneScreenSize(context: Context): IntArray {

        val metrics = obtain(context)

        val size = IntArray(2)

        size[0] = metrics.widthPixels

        size[1] = metrics.heightPixels

        return size
    }

}
