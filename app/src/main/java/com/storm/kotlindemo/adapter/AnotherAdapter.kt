package com.storm.kotlindemo.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup


/**
 * Created by Administrator on 2017/12/7.
 *
 */
class AnotherAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // 数据
    val datas = arrayListOf<Any>()


    //获取类型
    override fun getItemViewType(position: Int): Int {

        when (position) {

            itemCount -> TYPE_LOAD
            
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




    }

    override fun getItemCount(): Int {
        return datas.size + 1
    }


    companion object {
        private val TAG: String = AnotherAdapter::class.java.simpleName

        val TYPE_LOAD = 1 // 上拉加载更多

    }

}