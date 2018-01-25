package com.storm.httplib.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.storm.kotlindemo.utils.LogUtils

/**
 * Created by stormzhang on 2018/1/16.
 * Fragment 基类
 */
abstract class BaseFragment : Fragment() {

    companion object {
        public val TAG = BaseFragment::class.java.simpleName.toString()
    }

    protected lateinit var mContext: Context
    protected var mRootView: View? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), container,false)

        }
        LogUtils.d(TAG,"fragment 的生命周期 onCreateView")
        return mRootView
    }

    /**
     *  set fragment layoutId
     */
    abstract fun attachLayoutRes(): Int


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        LogUtils.d(TAG,"fragment 的生命周期 onViewCreated")

    }

    /**
     * 初始化 视图操作
     */
    abstract fun initView(view: View)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        setUpListener()
        LogUtils.d(TAG,"fragment 的生命周期 onActivityCreated")
    }

    /**
     *  set listener
     */
    abstract fun setUpListener()

    /**
     * 加载数据
     */
    abstract fun initData()
}

