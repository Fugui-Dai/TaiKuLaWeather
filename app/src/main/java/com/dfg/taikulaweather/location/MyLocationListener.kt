package com.dfg.taikulaweather.location

import android.util.Log
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation

/*
* Create by KUTAI1 on 2023/11/10
*/
class MyLocationListener : BDAbstractLocationListener() {
    private val TAG: String = MyLocationListener::class.java.simpleName

    //定位回调
    private var callback: LocationCallback? = null
    //需要定位的页面调用此方法进行接口回调处理
    fun setCallback(callback: LocationCallback?) {
        this.callback = callback
    }

    override fun onReceiveLocation(bdLocation: BDLocation?) {
        if (callback == null) {
            Log.e(TAG, "callback is Null!");
            return;
        }
        callback?.onReceiveLocation(bdLocation);
    }
}