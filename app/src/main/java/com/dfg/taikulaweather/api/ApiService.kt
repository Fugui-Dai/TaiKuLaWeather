package com.dfg.taikulaweather.api

import com.dfg.taikulaweather.bean.CityLookupBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/*
* Create by KUTAI1 on 2023/11/10
*/
interface ApiService {
    /**
     * 城市搜索
     */
    @GET("v2/city/lookup")
    fun getCityLookup(@QueryMap queryParams:Map<String, String> ): Call<CityLookupBean>
}