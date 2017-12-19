package com.storm.kotlindemo.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import com.storm.kotlindemo.R
import com.storm.kotlindemo.bean.HomeBean


/**
 * Created by Administrator on 2017/12/7.
 *
 */
class AnotherAdapter(override var mContext: Context, var isOpenloadMore: Boolean) : BaseAdapter<HomeBean>(mContext, isOpenloadMore) {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AnotherViewHolder? {

        if (isCommonViewType(viewType)) {
            return AnotherViewHolder.Companion.create(mContext, getItemLayoutId(), parent!!)
        }
        return super.onCreateViewHolder(parent, viewType)
    }

    private fun getItemLayoutId(): Int {
        return R.layout.adapter_home
    }


    override fun getViewType(position: Int, t: HomeBean?): Int {
        return 0

    }

    override fun onBindViewHolder(holder: AnotherViewHolder, position: Int) {

        if (isCommonViewType(holder.itemViewType)) {
            bindData(holder, position)
        }
    }

    /**
     * 绑定数据
     */
    private fun bindData(holder: AnotherViewHolder, position: Int) {

        val homeBean = mDatas?.get(position)

        val tvContent = holder.getView<TextView>(R.id.tv_content)

        tvContent.text = homeBean?.id + homeBean?.content
    }


}