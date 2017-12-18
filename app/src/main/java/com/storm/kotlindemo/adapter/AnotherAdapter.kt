package com.storm.kotlindemo.adapter

import android.content.Context
import com.storm.kotlindemo.bean.HomeBean


/**
 * Created by Administrator on 2017/12/7.
 *
 */
class AnotherAdapter(override var mContext:Context, var isOpenloadMore:Boolean) : BaseAdapter<HomeBean>(mContext,isOpenloadMore){



    override fun getViewType(position: Int, t: HomeBean?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: AnotherViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}