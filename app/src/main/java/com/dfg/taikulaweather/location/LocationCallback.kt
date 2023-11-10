package com.dfg.taikulaweather.location

import com.baidu.location.BDLocation
/*
* 定位接口
* Create by KUTAI1 on 2023/11/10
*/
interface LocationCallback {
    /**
     * 接收定位
     * @param bdLocation 定位数据
     */
    fun onReceiveLocation(bdLocation: BDLocation?)
}