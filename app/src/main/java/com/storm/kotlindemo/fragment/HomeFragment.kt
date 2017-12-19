package com.storm.kotlindemo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.storm.kotlindemo.R
import com.storm.kotlindemo.adapter.AnotherAdapter
import com.storm.kotlindemo.adapter.BaseAdapter
import com.storm.kotlindemo.bean.HomeBean
import com.storm.kotlindemo.utils.LogUtils
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * Created by Administrator on 2017/12/6.
 *
 * first pager
 */
class HomeFragment : Fragment() {

    companion object {
        val TAG = "HomeFragment"
        val AIM_URL = "http://ishuhui.net/?PageIndex=1"
    }

    lateinit var rv_home: RecyclerView
    lateinit var homeRefresh: SwipeRefreshLayout
    lateinit var adapter: AnotherAdapter


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        LogUtils.a(TAG, "oncreateView")
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    /**
     * 当 视图创建时候调用
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtils.a(TAG, "fragment - onViewCreated")
        initView(view)
    }


    /**
     * initView 初始化视图
     */
    private fun initView(view: View) {

        rv_home = view.rv_home
        homeRefresh = view.srl_homeRefresh
        rv_home.layoutManager = GridLayoutManager(context, 2)
        adapter = AnotherAdapter(this.context, true)
//        adapter.setLoadingView(R.layout.loading_view)
//        adapter.setLoadFailView(R.layout.loadfail_view)
//        adapter.setLoadEndView(R.layout.loadend_view)
//        adapter.setFooterViewState(BaseAdapter.LOAD_LOADING)
        adapter.setLoadView(R.layout.loading_view, R.layout.loadfail_view, R.layout.loadend_view)
        adapter.setFooterViewState(BaseAdapter.LOAD_LOADING)

        initData()
        rv_home.adapter = adapter

    }

    /**
     * 数据
     */
    private fun initData() {

        val datas = arrayListOf<HomeBean>()

        for (i in 0..40) {
            val bean = HomeBean()
            bean.id = i.toString()
            bean.content = "测试据: $i"
            datas.add(bean)
        }

        adapter.refreshData(datas)

    }

}