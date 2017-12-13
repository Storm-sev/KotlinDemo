package com.storm.httplib.httpclient.service

import com.storm.kotlindemo.bean.VersionBean
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * Created by Administrator on 2017/12/13.
 *  网络请求接口
 */
interface HttpClientService {

    /**
     * check versioncode
     */
    @GET("appVersion/upgrade?")
    fun checkVersionService(@Query("versionCode") versionCode: String): Observable<VersionBean>


    /**
     * 单文件上传
     */
    @Multipart
    @POST("")
    fun uploadFile(@Part("description") body: RequestBody,
                   @Part file: MultipartBody.Part): Observable<ResponseBody>


    @POST("http://t.kaozhibao.com/api.php/Msg/msg?")
    fun get(@Query("info") info: String): Observable<ResponseBody>


}