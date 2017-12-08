package com.storm.kotlindemo.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.storm.httplib.utils.RxBus
import com.storm.kotlindemo.R
import com.storm.kotlindemo.adapter.ContentPagerAdapter
import com.storm.kotlindemo.fragment.BookFragment
import com.storm.kotlindemo.fragment.HomeFragment
import com.storm.kotlindemo.fragment.NewsFragment
import com.storm.kotlindemo.utils.AppUtils
import com.storm.kotlindemo.utils.LogUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {


    companion object {
        val TAG: String = "MainActivity"
    }


    val nameResList: ArrayList<Int> = arrayListOf(R.string.tab_one, R.string.tab_two, R.string.tab_three)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }

    private fun init() {
        setSupportActionBar(toolbar)
//        setTitle("")

        //创建Fragment 的集合
        val fragments = ArrayList<Fragment>()

        fragments.add(HomeFragment())
        fragments.add(BookFragment())
        fragments.add(NewsFragment())

        val nameList = nameResList.map(this::getString)
        // 设置adapter
        viewpager.adapter = ContentPagerAdapter(fragments, nameList, supportFragmentManager)

        tablayout.setupWithViewPager(viewpager)

        val instance = RxBus.instance
        LogUtils.d(TAG, "单例模式 : $instance")

    }

    /**
     * 创建 标题栏菜单
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    /**
     *  item is menu selector
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        //  !!. 表示当前对象不为空的时候执行
        val id = item!!.itemId
        LogUtils.d(TAG,"获取的 按钮id : $id")
        if (id == R.id.action_about) {
//            val intent = Intent(this, AboutActivity().javaClass)
//            startActivity(intent)
            //启动activity
            AppUtils.toActivity(this,AboutActivity().javaClass)
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}
