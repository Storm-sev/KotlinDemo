package com.storm.kotlindemo.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup


/**
 * Created by Administrator on 2017/12/7.
 *
 */
class AnotherAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    companion object {
        private val TAG: String = "AnotherAdapter"
    }

    // 数据
    val datas = arrayListOf<Any>()


    //获取类型
    override fun getItemViewType(position: Int): Int {

        val type =

        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {


    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}