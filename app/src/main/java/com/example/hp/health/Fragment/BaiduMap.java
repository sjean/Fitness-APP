package com.example.hp.health.Fragment;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.example.hp.health.Model.Utility;
import com.example.hp.health.R;

import java.util.ArrayList;
import java.util.List;

public class BaiduMap extends Activity {
    TextureMapView mMapview;
    private com.baidu.mapapi.map.BaiduMap mBaiduMap;
    private PoiSearch mPoiSearch = null;
    private LatLng center = new LatLng(31.273491, 120.730435);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidu_map);
        mMapview = (TextureMapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapview.getMap();
        mPoiSearch = PoiSearch.newInstance();
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(center));
        OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult == null || poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
                    Utility.show(BaiduMap.this, "Sorry, there is no Park Nearby you!");
                    return;}
                ArrayList<PoiInfo> list = new ArrayList<PoiInfo>();
                if (poiResult.getAllPoi()!=null && poiResult.getAllPoi().size()>0){
                    list.addAll(poiResult.getAllPoi());
                    System.out.println(list.get(1).location.toString());
                    for (int i=0;i<list.size();++i){
                        LatLng position = list.get(i).location;
                        showOverLay(position);
                    }
                }
            }
            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
                if (poiDetailResult.error != SearchResult.ERRORNO.NO_ERROR){
                    Utility.show(BaiduMap.this,"Sorry, no result.");
                }else {
                    Utility.show(BaiduMap.this, poiDetailResult.getName()+":"+poiDetailResult.getAddress());
                }
            }
        };

        Inition();
        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);
        nearbySearch();

    }


    private void Inition(){

        MapStatus mMapStatus = new MapStatus.Builder()
                .target(center)
                .zoom(16)
                .build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);

    }




    private void showOverLay(LatLng s) {
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
        OverlayOptions options = new MarkerOptions()
                .position(s)
                .icon(bitmap);
        mBaiduMap.addOverlay(options);
    }

    private void nearbySearch() {

        PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption();
        nearbySearchOption.location(center);
        nearbySearchOption.pageNum(20);
        nearbySearchOption.sortType(PoiSortType.distance_from_near_to_far);
        nearbySearchOption.keyword("公园");
        nearbySearchOption.radius(15000);// 检索半径，单位是米
        mPoiSearch.searchNearby(nearbySearchOption);  // 发起附近检索请求

    }


    protected void onDestroy(){
        super.onDestroy();
        mMapview.onDestroy();
    }

    protected void onResume() {
        super.onResume();
        mMapview.onResume();
    }

    protected void onPause(){
        super.onPause();
        mMapview.onPause();
    }

}


