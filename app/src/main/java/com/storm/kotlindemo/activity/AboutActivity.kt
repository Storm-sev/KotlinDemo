package com.storm.kotlindemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.storm.kotlindemo.R
import kotlinx.android.synthetic.main.activity_about.*


class AboutActivity : AppCompatActivity() {


    companion object {
        val TAG: String = "AboutActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        initView()
    }

    /**
     * init
     */
    private fun initView() {

        collapsing_toolbar.title = "关于界面"
        setSupportActionBar(titleBar)
        /**
         * 左上角添加返回键
         */
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
