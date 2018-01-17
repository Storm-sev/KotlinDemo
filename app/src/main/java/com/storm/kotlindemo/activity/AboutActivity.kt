package com.storm.kotlindemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.storm.httplib.base.BaseActivity
import com.storm.kotlindemo.R
import com.storm.kotlindemo.R.id.collapsing_toolbar
import kotlinx.android.synthetic.main.activity_about.*

/**
 *  关于界面 .
 */
class AboutActivity : BaseActivity() {

    companion object {
        val TAG = AboutActivity.javaClass.simpleName.toString()
    }

    override fun initViews() {
        collapsing_toolbar.title = "关于界面"
        setSupportActionBar(titleBar)
        // 左上角添加返回键
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



    }

    override fun initData() {
    }

    override fun setupListener() {


    }

    override fun attachLayoutRes(): Int {
        return R.layout.activity_about
    }


    /**
     * Toolbar 添加返回键之后的监听
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
