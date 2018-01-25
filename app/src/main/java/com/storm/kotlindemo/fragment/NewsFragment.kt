package com.storm.kotlindemo.fragment

import android.support.v4.app.Fragment
import android.view.View
import com.storm.httplib.base.BaseFragment
import com.storm.kotlindemo.R

/**
 * Created by Administrator on 2017/12/6.
 */
class NewsFragment : BaseFragment() {

    companion object {
        val TAG = NewsFragment::class.java.simpleName.toString()
    }

    override fun attachLayoutRes(): Int {
        return R.layout.fragment_news
    }



    override fun initView(view: View) {
    }

    override fun setUpListener() {
    }

    override fun initData() {
    }
}