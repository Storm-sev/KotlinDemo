package com.storm.httplib.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

/**
 * Created by stormzhang on 2018/1/17.
 *
 *  BaseActivity
 */

 abstract class BaseActivity : AppCompatActivity() {

    companion object {
        var TAG = BaseActivity.javaClass.simpleName.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(attachLayoutRes())
        initViews()
        initData()
        setupListener()
    }

    /**
     * init view
     */
    abstract fun initViews()

    /**
     *  load data
     */
    abstract fun initData()

    /**
     *  set listener
     */
    abstract fun setupListener()

    /**
     *  add layout res
     */
    abstract fun attachLayoutRes(): Int


}
