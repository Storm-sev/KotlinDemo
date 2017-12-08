package com.storm.kotlindemo.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup

/**
 * Created by Administrator on 2017/12/6.
 *
 * main  adapter
 *
 */
class ContentPagerAdapter(
        val fragments: List<Fragment>,
        val nameList: List<String>,
        fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {

        return fragments[position]

    }

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = nameList[position]


    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        super.destroyItem(container, position, `object`)
    }


}