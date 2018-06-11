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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.ListSortAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.bean.SortModel;
import zhuyekeji.zhengzhou.jxlifecircle.utils.other.CharacterParser;
import zhuyekeji.zhengzhou.jxlifecircle.utils.other.PinyinComparator;
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

    private List<SortModel> SourceDateList;
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
                    Intent intent = getIntent();
                    intent.putExtra("address", ((SortModel) adapter.getItem(position - 1)).getName());
                    CitySelectActivity.this.setResult(0, intent);
                    CitySelectActivity.this.finish();
                }
            }
        });

        SourceDateList = filledData(getResources().getStringArray(R.array.date));
//       根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
//        创建adapter
        adapter = new ListSortAdapter(this, SourceDateList);
//        给listView设置数据
        sortListView.setAdapter(adapter);
    }

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
    private List<SortModel> filledData(String[] date) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
//            汉字转拼音
            String pinyin = characterParser.getSelling(date[i]);
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
