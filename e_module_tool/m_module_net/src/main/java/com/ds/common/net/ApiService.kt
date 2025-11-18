package com.ds.common.net

import com.ds.common.net.bean.ApiResponse
import com.ds.common.net.bean.ConvertDTO
import okhttp3.MultipartBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

/**
 * @Author ljiezhou
 * @date 2023/5/16 10:23
 * @Description
 */
interface ApiService {
    companion object {
        const val SERVER_URL = "https://food.idishi.cn/"
    }

    @Multipart
    @POST("app/scanmanager/v1/file/convert")
    suspend fun convert(
        @Query("fromtype") fromType: String,
        @Query("totype") toType: String,
        @Part file_upload: MultipartBody.Part
    ): ApiResponse<ConvertDTO>

    /**
     * version model firm channel content screensize contact datatype oaid android systemversion
     * @param id Int
     * @param page Int
     * @return ApiResponse<List<Food>>
     */
    @FormUrlEncoded
    @POST("app/babyfood/v1/message/add")
    suspend fun message(@Field("content") content: String): ApiResponse<Any>

}
