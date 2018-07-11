package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.bean.MyAddrseeBean;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.SPUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class AddAddressActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_shouhuo_name)
    EditText edShouhuoName;
    @BindView(R.id.ed_lianxi_type)
    EditText edLianxiType;
    @BindView(R.id.ed_area)
    EditText edArea;
    @BindView(R.id.ed_address)
    EditText edAddress;
    @BindView(R.id.queren)
    TextView queren;
    private MyAddrseeBean[] myAddrseeBean;
    private String type;
    private String theid;
    private List<MyAddrseeBean> beans=new ArrayList<>();
    @Override
    public int getViewId()
    {
        return R.layout.activity_add_address;
    }

    @Override
    protected void processLogic()
    {
        type = getIntent().getStringExtra("type");
        if (type == null || !type.equals("1"))
        {
            theid=getIntent().getStringExtra("id");
            JxApiCallBack.edaddress(getToken(), theid, 2, this, callBack);
            tvTitle.setText("修改地址");
        }else {
            tvTitle.setText("新增地址");
        }
    }

    @Override
    protected void setListener()
    {
        rlBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
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

    @OnClick({R.id.rl_back, R.id.queren})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.queren:
                String name = edShouhuoName.getText().toString().trim();
                String phone = edLianxiType.getText().toString().trim();
                String area = edArea.getText().toString().trim();
                String address = edAddress.getText().toString().trim();

                if (name == null || name.length() < 1)
                {
                    ToastUtils.showShort("请输入名字");
                    return;
                }

                if (phone == null || phone.length() < 1)
                {
                    ToastUtils.showShort("请输入联系方式");
                    return;
                }
                if (area == null || area.length() < 1)
                {
                    ToastUtils.showShort("请输入地区");
                    return;
                }
                if (address == null || address.length() < 1)
                {
                    ToastUtils.showShort("请输入详细地址");
                    return;
                }
                if (type!=null&&type.equals("1"))
                {
                    JxApiCallBack.addaddress(getToken(), name, phone, area, address, 1, AddAddressActivity.this, callBack);
                }else {
                    int status=Integer.parseInt(beans.get(0).getStatus());
                    JxApiCallBack.tijiaoaddress(getToken(),name,phone,area,address,beans.get(0).getId(),status,1,
                            AddAddressActivity.this,callBack);
                }


                break;
        }
    }

    CallBack callBack = new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
            switch (what)
            {
                case 1:
                    if (JsonUtile.getCode(result.body()).equals("200"))
                    {
                        ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                        SPUtils.getInstance().put("edit","1");
                        finish();
                    } else
                    {
                        ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                    }
                    break;
                case 2:
                    if (JsonUtile.getCode(result.body()).equals("200"))
                    {
                       myAddrseeBean=new Gson().fromJson(JsonUtile.getbody(result.body()),MyAddrseeBean[].class);
                       List list= Arrays.asList(myAddrseeBean);
                       beans.addAll(list);
                       if (myAddrseeBean!=null)
                       {
                           edShouhuoName.setText(beans.get(0).getName());
                           edLianxiType.setText(beans.get(0).getTel());
                           edArea.setText(beans.get(0).getDiqu());
                           edAddress.setText(beans.get(0).getAddre());
                       }
                    } else
                    {
                        ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                    }
                    break;
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
}
