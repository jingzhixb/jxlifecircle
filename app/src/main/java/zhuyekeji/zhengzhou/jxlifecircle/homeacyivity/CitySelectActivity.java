package zhuyekeji.zhengzhou.jxlifecircle.homeacyivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.ListSortAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.bean.SortModel;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.other.CharacterParser;
import zhuyekeji.zhengzhou.jxlifecircle.utils.other.PinyinComparator;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.SPUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;
import zhuyekeji.zhengzhou.jxlifecircle.widget.SideBar;

public class CitySelectActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.country_lvcountry)
    ListView sortListView;
    @BindView(R.id.dialog)
    TextView dialog;
    @BindView(R.id.sidrbar)
    SideBar sideBar;

    private List<SortModel> SourceDateList,citys;
    private PinyinComparator pinyinComparator;
    private ListSortAdapter adapter;
    private RecyclerView recyclerView_hot;
    private CharacterParser characterParser;
    private String location_city;//当前城市
    @Override
    public int getViewId()
    {
        return R.layout.activity_city_select;
    }

    @Override
    protected void processLogic()
    {
        JxApiCallBack.city_all(1,this,callBack);
        location_city=getIntent().getStringExtra("location_city");
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();

        sideBar.setTextView(dialog);

        //设置右触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }

            }
        });
        View headerView = View.inflate(this, R.layout.address_header, null);
        TextView tv_now = (TextView) headerView.findViewById(R.id.tv_now);
        recyclerView_hot = (RecyclerView) headerView.findViewById(R.id.recyclerView_hot);
        recyclerView_hot.setLayoutManager(new GridLayoutManager(CitySelectActivity.this, 5));
        // tv_now.setText(location_city);


        sortListView.addHeaderView(headerView);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position > 0) {
//                    Toast.makeText(getApplication(), ((SortModel) adapter.getItem(position - 1)).getName(), Toast.LENGTH_SHORT).show();
//                    SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
//                    sp.edit().putString("Location", ((SortModel) adapter.getItem(position - 1)).getName()).apply();
                    SPUtils.getInstance().put("city_id",((SortModel) adapter.getItem(position - 1)).getCity_id());
                    Intent intent = getIntent();
                    intent.putExtra("address", ((SortModel) adapter.getItem(position - 1)).getName());
                    CitySelectActivity.this.setResult(0, intent);
                    CitySelectActivity.this.finish();
                }
            }
        });

        //SourceDateList = filledData(getResources().getStringArray(R.array.date));
//       根据a-z进行排序源数据

    }


    CallBack callBack=new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
            if (JsonUtile.getCode(result.body()).equals("200"))
            {
                List list= Arrays.asList(new Gson().fromJson(JsonUtile.getbody(result.body()),SortModel[].class));
                SourceDateList=new ArrayList<>(list);
                citys= filledData(SourceDateList);
                Collections.sort(citys, pinyinComparator);
//        创建adapter
                adapter = new ListSortAdapter(CitySelectActivity.this, citys);
//        给listView设置数据
                sortListView.setAdapter(adapter);
            }else {
                ToastUtils.showShort(JsonUtile.getresulter(result.body()));
            }
        }

        @Override
        public void onFail(int what, Response<String> result)
        {

        }

        @Override
        public void onFinish(int what)
        {

        }
    };
    @Override
    protected void setListener()
    {

    }


    /**
     * ÎªListViewÌî³äÊý¾Ý
     * 填充数据
     *
     * @param date
     * @return
     */
    private List<SortModel> filledData(List<SortModel> date) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.size(); i++) {
            SortModel sortModel = new SortModel();
            sortModel.setCity_name(date.get(i).getCity_name());
//            汉字转拼音
            String pinyin = characterParser.getSelling(date.get(i).getCity_name());
//            转换为大写
            String sortString = pinyin.substring(0, 1).toUpperCase();

//            正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

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
}
