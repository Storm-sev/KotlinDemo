package com.storm.kotlindemo.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by Administrator on 2017/12/7.
 *
 * viewHolder
 *
 */
class AnotherViewHolder(private val mItemView: View) : RecyclerView.ViewHolder(mItemView) {


    private val mViews: SparseArray<View> = SparseArray()


    /**
     *  通过 id 获取控件
     */
    fun <T : View> getView(viewId: Int): T {

        var childView: View = mViews.get(viewId)

        if (childView == null) {

            childView = mItemView.findViewById(viewId)

            mViews.put(viewId, childView)

        }


        return childView as T
    }


    /**
     * 获取ItemView
     */
    fun getItemView(): View = mItemView


    /**
     * set text
     */
    fun setText(viewId: Int, data: String) {

        val textView: TextView = getView(viewId)
        textView.setText(data)

    }


    /**
     * set BackGroundColor
     */
    fun setBgColor(viewId: Int, colorId: Int)
            = mViews.get(viewId).setBackgroundColor(colorId)


    // 静态创建 :

    companion object {

        /**
         * create viewHolder
         */
        fun create(itemView: View): AnotherViewHolder = AnotherViewHolder(itemView)

        /**
         *
         */
        fun create(context: Context, layoutId: Int, parent: ViewGroup) : AnotherViewHolder {

            val itemView: View = LayoutInflater.from(context)
                    .inflate(layoutId, parent, false)
            return AnotherViewHolder(itemView)
        }


    }

}