package com.storm.httplib.downloadfile

/**
 * Created by Administrator on 2017/12/12.
 *
 * 下载的动作
 */
class FileLoadEvent {

    var total: Long = 0
    var progress: Long = 0

    constructor() {}

    constructor(total: Long, progress: Long) {}

}