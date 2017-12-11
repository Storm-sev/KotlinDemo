package com.storm.httplib.utils

/**
 * Created by Administrator on 2017/12/11.
 */
class Demo {

    fun show(strs: Source<String>) {

        val o: Source<Any> = strs
    }

}


abstract class Source<out T> {

    abstract fun nextT(): T
}