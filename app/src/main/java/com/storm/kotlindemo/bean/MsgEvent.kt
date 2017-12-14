package com.storm.kotlindemo.bean

/**
 * Created by Administrator on 2017/8/28.
 * Rxbus 消息操作类
 */

class MsgEvent<T>(var code: Int, var content: T)
