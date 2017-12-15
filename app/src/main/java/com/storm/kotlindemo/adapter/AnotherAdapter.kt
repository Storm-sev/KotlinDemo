package com.storm.kotlindemo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView


/**
 * Created by Administrator on 2017/12/7.
 *
 */
class AnotherAdapter(private var mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // 数据
    val datas = arrayListOf<Any>()


    //获取类型
    override fun getItemViewType(position: Int): Int {

        when (position) {

//            itemCount ->
        }


        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {


    }

    /**
     * 创建 ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if (parent == null) throw IllegalAccessError("an adapter must be attach a recycleView")

        return AnotherViewHolder.create(ImageView(mContext))
    }

    override fun getItemCount(): Int {
        return datas.size + 1
    }


    companion object {
        private val TAG: String = AnotherAdapter::class.java.simpleName
    }

}