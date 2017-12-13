package com.storm.kotlindemo.bean

/**
 * Created by Administrator on 2017/12/13.
 */
class VersionBean {


    private var code: String? = null

    private var data: DataBean? = null

    private var description: String? = null

    class DataBean {

        private var id: String? = null
        private var platform: String? = null
        private var versionId: String? = null
        private var subVersionId: String? = null
        private var versionCode: String? = null
        private var apkUrl: String? = null
        private var status: String? = null

    }


}