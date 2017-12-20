package com.storm.kotlindemo.fragment

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.storm.httplib.downloadfile.FileCallBack
import com.storm.httplib.httpclient.HttpManager
import com.storm.httplib.utils.AppUtils
import com.storm.kotlindemo.R
import com.storm.kotlindemo.utils.LogUtils
import kotlinx.android.synthetic.main.fragment_book.view.*
import okhttp3.ResponseBody
import java.io.File

/**
 * Created by Administrator on 2017/12/6.
 */
class BookFragment : Fragment() {


    lateinit var btn_download_apk: Button
    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_book, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {

        btn_download_apk = view.btn_download_apk


        setUpListener()


    }

    private fun setUpListener() {

        btn_download_apk.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                downLoadApk()

            }

        })

    }

    /**
     * 下载apk
     */
    private fun downLoadApk() {

        val url = "https://qd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk"

        HttpManager.load(url, object : FileCallBack<ResponseBody>(
                Environment.getExternalStorageDirectory().absolutePath,
                AppUtils.getNameFromUrl(url)

        ) {
            override fun onSuccess(t: ResponseBody) {
                LogUtils.d(TAG,"onsuccess")

            }

            override fun onStart() {
                LogUtils.d(TAG,"onstart")
            }

            override fun onComplete() {
                val fileDir = File(Environment.getExternalStorageDirectory().absolutePath)

                val file = File(fileDir, AppUtils.getNameFromUrl(url))

                if (file.exists()) {
                    AppUtils.installApk(mContext, file)
                }

            }

            override fun onError(e: Throwable) {
                LogUtils.d(TAG,"onError")
            }

            override fun onProgress(progress: Float, total: Long) {
                LogUtils.d(TAG,"onProgress")

                val  progress = (progress * 100).toInt().toString() + "%"
                LogUtils.d(TAG,progress)
            }

        })

    }


    companion object {
        private val TAG: String = "BookFragment"

    }


}