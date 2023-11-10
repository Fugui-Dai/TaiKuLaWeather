package com.dfg.taikulaweather

import android.app.Application
import com.baidu.location.LocationClient

/*
* Create by KUTAI1 on 2023/11/10
*/
class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        //使用定位需要同意隐私合规政策
        LocationClient.setAgreePrivacy(true)
    }
}
