package com.dfg.taikulaweather

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.baidu.location.BDLocation
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.baidu.location.LocationClientOption.LocationMode
import com.dfg.taikulaweather.api.ApiDefine
import com.dfg.taikulaweather.api.ApiService
import com.dfg.taikulaweather.bean.CityLookupBean
import com.dfg.taikulaweather.databinding.ActivityMainBinding
import com.dfg.taikulaweather.location.LocationCallback
import com.dfg.taikulaweather.location.MyLocationListener
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), LocationCallback {

    var TAG: String = MainActivity::class.java.simpleName
    lateinit var binding: ActivityMainBinding
    var allPermissionsGranted = true
    val permissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    lateinit var registerForActivityResult: ActivityResultLauncher<Array<String>>
    lateinit var mLocationClient: LocationClient
    lateinit var myListener: MyLocationListener
    lateinit var locationOption: LocationClientOption

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLocation();
        requestPermission();

        startLocation()
    }

    /**
     * 初始化定位
     */
    fun initLocation() {
        try {
            //定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
            mLocationClient = LocationClient(applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        myListener = MyLocationListener()
        myListener.setCallback(this);
        //注册定位监听
        mLocationClient.registerLocationListener(myListener)
        //声明LocationClient类实例并配置定位参数
        locationOption = LocationClientOption()
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.locationMode = LocationMode.Hight_Accuracy
        //可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("bd09ll")
        //可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        locationOption.setScanSpan(1000)
        //可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true)
        //可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true)
        //可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(false)
        //可选，默认false，设置是否当卫星定位有效时按照1S1次频率输出卫星定位结果
        locationOption.isLocationNotify = true
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true)
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true)
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(true)
        //可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false)
        //可选，默认false，设置是否开启卫星定位
        locationOption.isOpenGnss = true
        //可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false)
        //设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode()
        //设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        locationOption.setOpenAutoNotifyMode(3000, 1, LocationClientOption.LOC_SENSITIVITY_HIGHT)
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        mLocationClient.locOption = locationOption
    }

    /**
     * 申请权限
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun requestPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            registerForActivityResult =
                registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { it: Map<String, Boolean> ->
                    for ((permission, isGranted) in it) {
                        Log.d("TAG", "permission：" + permission + "，isGranted：" + isGranted)
                        if (isGranted) {
                            //use allow the permission
                            when (permission) {
                                Manifest.permission.ACCESS_COARSE_LOCATION -> {
                                    // 处理 ACCESS_COARSE_LOCATION 权限授权后的逻辑
                                }

                                Manifest.permission.ACCESS_FINE_LOCATION -> {
                                    // 处理 ACCESS_FINE_LOCATION 权限授权后的逻辑
                                }

                                Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                                    // 处理 WRITE_EXTERNAL_STORAGE 权限授权后的逻辑
                                }
                            }
                        } else {
                            allPermissionsGranted = false;
                            //use deny the permission
                            when (permission) {
                                Manifest.permission.ACCESS_COARSE_LOCATION -> {
                                    // 处理 ACCESS_COARSE_LOCATION 权限未授权的逻辑
                                    Toast.makeText(
                                        applicationContext,
                                        "you deny the permission ACCESS_COARSE_LOCATION",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }

                                Manifest.permission.ACCESS_FINE_LOCATION -> {
                                    // 处理 ACCESS_FINE_LOCATION 权限未授权的逻辑
                                    Toast.makeText(
                                        applicationContext,
                                        "you deny the permission ACCESS_FINE_LOCATION",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }

                                Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                                    // 处理 WRITE_EXTERNAL_STORAGE 权限未授权的逻辑
                                    Toast.makeText(
                                        applicationContext,
                                        "you deny the permission WRITE_EXTERNAL_STORAGE",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    }
                    if (allPermissionsGranted) {
                        // grant all permission
                        startLocation()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "You must agree to all permissions",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            registerForActivityResult.launch(permissions)
        }
    }

    /**
     * 城市搜索
     * location 城市
     * key key值
     */
    fun searchCity(location: String,key:String) {
        var retrofit = Retrofit.Builder().baseUrl(ApiDefine.CITY_SEARCH)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var apiService = retrofit.create(ApiService::class.java)

        val queryParams: MutableMap<String, String> = HashMap()
        queryParams["locaiton"] = location
        queryParams["key"] = key


        apiService.getCityLookup(queryParams).enqueue(object : retrofit2.Callback<CityLookupBean> {
            override fun onResponse(
                call: Call<CityLookupBean>,
                response: Response<CityLookupBean>
            ) {
                if (response.isSuccessful) {
                    var body = response.body()
                    Log.d("TAG", "获取数据成功了");
                    Log.d("TAG", "response.code()==" + body?.code);
                    Log.d("TAG", "response.body()==" + body);
                    Log.d("TAG", "body.location.size==" + body?.location?.size);

                }
            }

            override fun onFailure(call: Call<CityLookupBean>, t: Throwable) {
            }
        })
    }


    private fun startLocation() {
        //开始定位，stop()：关闭定位SDK。
        mLocationClient.start()
    }


    override fun onReceiveLocation(location: BDLocation?) {
        //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
        //以下只列举部分获取经纬度相关（常用）的结果信息
        //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
        val locationDescribe = location?.locationDescribe //获取位置描述信息

        //获取纬度信息
        val latitude = location?.latitude
        //获取经度信息
        val longitude = location?.longitude
        //获取定位精度，默认值为0.0f
        val radius = location?.radius
        //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
        val coorType = location?.coorType
        //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
        val errorCode = location?.locType

        val addr = location?.addrStr //获取详细地址信息
        val country = location?.country //获取国家
        val province = location?.province //获取省份
        val city = location?.city //获取城市
        val district = location?.district //获取区县
        val street = location?.street //获取街道信息
        val adcode = location?.adCode //获取adcode
        val town = location?.town //获取乡镇信息



        Log.d("TAG", "纬度latitude：" + latitude);
        Log.d("TAG", "经度longitude：" + longitude);
        Log.d("TAG", "定位精度radius：" + radius);
        Log.d("TAG", "经纬度坐标类型coorType：" + coorType);
        Log.d("TAG", "定位类型errorCode：" + errorCode);
        Log.d("TAG", "位置描述信息locationDescribe：" + locationDescribe);
        Log.d("TAG", "详细地址addr：" + addr);
        Log.d("TAG", "国家country：" + country);
        Log.d("TAG", "省份province：" + province);
        Log.d("TAG", "城市city：" + city);
        Log.d("TAG", "县区district：" + district);
        Log.d("TAG", "街道street：" + street);
        Log.d("TAG", "Code码adcode：" + adcode);
        Log.d("TAG", "乡镇town：" + town);

        if (district != null) {
            searchCity(district,"4918ca9d4f56406c97d99cfce30af794")
        };
    }
}

