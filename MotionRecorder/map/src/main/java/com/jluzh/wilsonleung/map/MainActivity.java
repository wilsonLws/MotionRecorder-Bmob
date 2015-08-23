package com.jluzh.wilsonleung.map;

import android.app.Activity;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;


public class MainActivity extends Activity {
    private MapView mapView;
    private AMap aMap;
//    private OnLocationChangedListener mListener;
//    private LocationManagerProxy mAMapLocationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 必须要写
        aMap = mapView.getMap();

//        init();
    }

//    /**
//     * 初始化AMap对象
//     */
//    private void init() {
//        if (aMap == null) {
//            aMap = mapView.getMap();
//            setUpMap();
//        }
//    }
//
//    private void setUpMap() {
//        aMap.setLocationSource(this);// 设置定位监听
//        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
//        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
//        // 设置定位的类型为定位模式：定位（AMap.LOCATION_TYPE_LOCATE）、跟随（AMap.LOCATION_TYPE_MAP_FOLLOW）
//        // 地图根据面向方向旋转（AMap.LOCATION_TYPE_MAP_ROTATE）三种模式
//        aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);
//    }
//
//    /**
//     * 此方法需存在
//     */
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    /**
//     * 此方法需存在
//     */
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mapView.onPause();
//        deactivate();
//    }
//
//    /**
//     * 此方法需存在
//     */
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mapView.onDestroy();
//    }
//
//    /**
//     * 此方法已经废弃
//     */
//    @Override
//    public void onLocationChanged(Location location) {
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//
//    }
//
//    /**
//     * 定位成功后回调函数
//     */
//    @Override
//    public void onLocationChanged(AMapLocation amapLocation) {
//        if (mListener != null && amapLocation != null) {
//            if (amapLocation.getAMapException().getErrorCode() == 0) {
//                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
//            }
//        }
//    }
//
//    /**
//     * 激活定位
//     */
//    @Override
//    public void activate(OnLocationChangedListener listener) {
//        mListener = listener;
//        if (mAMapLocationManager == null) {
//            mAMapLocationManager = LocationManagerProxy.getInstance(this);
//            //此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
//            //注意设置合适的定位时间的间隔，并且在合适时间调用removeUpdates()方法来取消定位请求
//            //在定位结束后，在合适的生命周期调用destroy()方法
//            //其中如果间隔时间为-1，则定位只定一次
//            mAMapLocationManager.requestLocationData(
//                    LocationProviderProxy.AMapNetwork, 60*1000, 10, this);
//        }
//    }
//
//
//    /**
//     * 停止定位
//     */
//    @Override
//    public void deactivate() {
//        mListener = null;
//        if (mAMapLocationManager != null) {
//            mAMapLocationManager.removeUpdates(this);
//            mAMapLocationManager.destroy();
//        }
//        mAMapLocationManager = null;
//    }


}
