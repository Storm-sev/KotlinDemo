package com.storm.httplib.downloadfile

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.POST
import retrofit2.http.Streaming
import retrofit2.http.Url

/**
 * Created by Administrator on 2017/12/12.\
 * 下载管理
 */
interface DownLoadService {

    /**
     * 下载最新apk
     */
    @Streaming
    @POST
    fun downLoadApk(@Url url: String): Observable<ResponseBody>

}