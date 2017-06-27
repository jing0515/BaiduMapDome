package com.bawei.lvwenjing.baidumapdome;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends Activity {

    private MapView mMapView = null;
    private BaiduMap mBaiduMap = null;
    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplication());
        setContentView(R.layout.activity_main);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

//普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
//开启交通图
        mBaiduMap.setTrafficEnabled(true);
        //定义Maker坐标点
        LatLng point = new LatLng(40.049256, 116.306406);
//构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_marka);
//构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
//在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
        OverlayOptions options = new MarkerOptions()
                .alpha(0.1f)
                .position(point) //设置marker的位置
                .icon(bitmap)  //设置marker图标
                .zIndex(9)
                //设置marker所在层级
                .draggable(true);  //设置手势拖拽
//将marker添加到地图上
        marker = (Marker) (mBaiduMap.addOverlay(options));


        //创建InfoWindow展示的view
        Button button = new Button(getApplicationContext());
        button.setBackgroundResource(R.drawable.popup);
        button.setText(""+"北京八维学院");
//定义用于显示该InfoWindow的坐标点
        LatLng pt = new LatLng(40.049256, 116.306406);
//创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
        InfoWindow mInfoWindow = new InfoWindow(button, pt, -47);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, ""+"北京八维学院", Toast.LENGTH_SHORT).show();
            }
        });
//显示InfoWindow
        mBaiduMap.showInfoWindow(mInfoWindow);
    }
}
