package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.DingWeiAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.SPUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class AddressSelectActivity extends BaseActivity
{
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.ed_search)
    AutoCompleteTextView edSearch;
    @BindView(R.id.rv_addrsee)
    RecyclerView rvAddrsee;
    private SuggestionSearch suggestionSearch = null;           //模糊搜索模块
    private PoiSearch poiSearch;                                //POI搜索模块
    private BDLocation currentLocation;                         //当前定位信息[最好使用这个]
    private LocationClient locationClient;                      //定位SDK核心类
    @Override
    public int getViewId()
    {
        return R.layout.activity_address_select;
    }

    @Override
    protected void processLogic()
    {

    }

    @Override
    protected void setListener()
    {
        edSearch.addTextChangedListener(new TextViewWatcher());
        initBaiduMap();

    }

    private void initBaiduMap() {
        /*****************************************************
         * 地图模块
         *****************************************************/
        //定位服务客户端
        locationClient = new LocationClient(this);
        //注册监听
        locationClient.registerLocationListener(new MyLocationListenner());

        //定位配置信息
        LocationClientOption option = new LocationClientOption();
        // 打开gps
        option.setOpenGps(true);
        // 设置坐标类型,国测局经纬度坐标系:gcj02;  百度墨卡托坐标系:bd09;  百度经纬度坐标系:bd09ll
        option.setCoorType("bd09ll");
        //定位请求时间间隔 1秒
        option.setScanSpan(1000);
        //设备方向
        option.setNeedDeviceDirect(true);
        //是否需要地址信息
        option.setIsNeedAddress(true);
        //是否需要地址语义化信息
        option.setIsNeedLocationDescribe(true);
        locationClient.setLocOption(option);
        //开启定位
        locationClient.start();
        /******************************************************
         * POI搜索模块
         ******************************************************/
        //POI搜索模块
        poiSearch = PoiSearch.newInstance();
        //增加监听：POI搜索结果
        poiSearch.setOnGetPoiSearchResultListener(new PoiSearchListener());
        //模糊搜索
        suggestionSearch = SuggestionSearch.newInstance();
        //增加监听：模糊搜索查询结果
        suggestionSearch.setOnGetSuggestionResultListener(new SuggestionResultListener());
    }

    @Override
    protected Context getActivityContext()
    {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }



    /*
        * 模糊查询回调接口实列
        * */
    private class SuggestionResultListener implements OnGetSuggestionResultListener
    {
        @Override
        public void onGetSuggestionResult(final SuggestionResult suggestionResult) {
            if (suggestionResult == null || suggestionResult.getAllSuggestions() == null) {
                return;
            }
            List<String> suggest = new ArrayList<>();
            for (SuggestionResult.SuggestionInfo suggestionInfo : suggestionResult.getAllSuggestions()) {
                if (suggestionInfo.key != null) {
                    suggest.add(suggestionInfo.key);
                }
            }
            final DingWeiAdapter adapter=new DingWeiAdapter(AddressSelectActivity.this,R.layout.address_item, suggest);
            rvAddrsee.setLayoutManager(new LinearLayoutManager(AddressSelectActivity.this));
            rvAddrsee.setAdapter(adapter);
            adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener()
            {
                @Override
                public void onItemClick(View view, int i)
                {
                    String address=adapter.getItem(i);
                    SPUtils.getInstance().put("address",address);
                    finish();
                }
            });
        }
    }

    private class TextViewWatcher implements TextWatcher
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            if (s.length() < 1)
            {
                return;
            }
            suggestionSearch
                    .requestSuggestion(new SuggestionSearchOption()
                            .city(currentLocation.getCity())
                            .keyword(s.toString()));
        }

        @Override
        public void afterTextChanged(Editable s)
        {

        }
    }




    private class PoiSearchListener implements OnGetPoiSearchResultListener
    {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            if (poiResult == null
                    || poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
                Toast.makeText(AddressSelectActivity.this, "未找到结果", Toast.LENGTH_LONG)
                        .show();
                return;
            }
            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {

                return;
            }
            if (poiResult.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {

                // 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
                String strInfo = "在";
                for (CityInfo cityInfo : poiResult.getSuggestCityList()) {
                    strInfo += cityInfo.city;
                    strInfo += ",";
                }
                strInfo += "找到结果";
                Toast.makeText(AddressSelectActivity.this, strInfo, Toast.LENGTH_LONG)
                        .show();
            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            if (poiDetailResult == null
                    || poiDetailResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
                Toast.makeText(AddressSelectActivity.this, "未找到结果", Toast.LENGTH_LONG)
                        .show();
                return;
            }
            if (poiDetailResult.error == SearchResult.ERRORNO.NO_ERROR) {

                return;
            }
            if (poiDetailResult.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {
            }
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
    }


    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener
    {

        @Override
        public void onReceiveLocation(BDLocation location)
        {
            // map view 销毁后不在处理新接收的位置
            if (location == null)
            {
                ToastUtils.showShort("暂时无法获取位置信息");
                return;
            }
            //Toast.makeText(MainActivity.this, "定位结果编码："+location.getLocType(), Toast.LENGTH_LONG).show();
            currentLocation = location;
        }


        @Override
        public void onConnectHotSpotMessage(String s, int i)
        {

        }
    }
}
