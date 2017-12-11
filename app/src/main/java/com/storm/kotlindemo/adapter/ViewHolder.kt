package com.storm.kotlindemo.adapter

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View

/**
 * Created by Administrator on 2017/12/7.
 *  viewHolder 基类.
 */

class ViewHolder(private val mItemView: View) : RecyclerView.ViewHolder(mItemView) {

    private val mViews: SparseArray<View>

    init {
        mViews = SparseArray()
    }

    /**
     * 通过id 获取控件
     * @param viewId
     * @return
     */
    fun <T : View> getView(viewId: Int): T? {
        var childView: View? = mViews.get(viewId)
        if (null == childView) {
            childView = mItemView.findViewById(viewId)
            mViews.put(viewId, childView)
        }
        return childView as T?
    }


    fun getmItemView(): View {
        return mItemView
    }


}
