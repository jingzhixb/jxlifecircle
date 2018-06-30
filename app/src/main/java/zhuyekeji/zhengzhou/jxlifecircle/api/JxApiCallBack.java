package zhuyekeji.zhengzhou.jxlifecircle.api;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.File;

/**
 * Created by Administrator on 2018/6/29.
 */

public class JxApiCallBack
{
    /*
用途：用户注册
user_phone	string	是	用户手机号
password	string	是	密码
nickname	string	否	用户昵称
headerid	int	否	头像id
    * */
    public static void user_register(String user_phone, String password, String nickname,
                                     String headerid, final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.USER_REGISTER)
                .params("user_phone",user_phone)
                .params("password",password)
                .params("nickname",nickname)
                .params("headerid",headerid)
                .execute(new StringDialogCallback(activity,"加载中")
                {
                    @Override
                    public void onSuccess(Response<String> response)
                    {
                        callBack.onSuccess(what,response);
                    }
                });
    }


    /*
用途：用户登陆
user_phone	string	否	手机号/用户名
password	string	否	密码
openid	string	否	第三方登录标识
nickname	string	否	昵称（第三方登录必传）
login_type	int	是	登录类型（1手机号2qq3微信）
header_pic	string	否	头像url（第三方登录必传）
* */
    public static void user_login(String user_phone, String password, String openid,String nickname,
                                     int login_type,String header_pic,
                                     int headerid, final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.USER_LOGIN)
                .params("user_phone",user_phone)
                .params("password",password)
                .params("openid",openid)
                .params("login_type",login_type)
                .params("header_pic",header_pic)
                .params("nickname",nickname)
                .params("headerid",headerid)
                .execute(new StringDialogCallback(activity,"加载中")
                {
                    @Override
                    public void onSuccess(Response<String> response)
                    {
                        callBack.onSuccess(what,response);
                    }
                });
    }



    /*
用途：忘记密码，重新设置密码
user_phone	string	是	手机号
password	string	是	新密码
repassword	string	是	再次输入新密码
* */
    public static void again_set_password(String user_phone, String password, String repassword,
                                 final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.AGAIN_SET_PASSWORD)
                .params("user_phone",user_phone)
                .params("password",password)
                .params("repassword",repassword)
                .execute(new StringDialogCallback(activity,"加载中")
                {
                    @Override
                    public void onSuccess(Response<String> response)
                    {
                        callBack.onSuccess(what,response);
                    }
                });
    }

    /*
用途：忘记密码，重新设置密码
user_phone	string	是	手机号
password	string	是	新密码
repassword	string	是	再次输入新密码
* */
    public static void up_img(File file, final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.UPIMG)
                .params("file",file)
                .execute(new StringDialogCallback(activity,"请稍后")
                {
                    @Override
                    public void onSuccess(Response<String> response)
                    {
                        callBack.onSuccess(what,response);
                    }
                });
    }

    /*
用途：	进商家中心，店铺简要信息展示
token
* */
    public static void shopcore(String token, final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.SHOPCORE)
                .params("token",token)
                .execute(new StringDialogCallback(activity,"请稍后")
                {
                    @Override
                    public void onSuccess(Response<String> response)
                    {
                        callBack.onSuccess(what,response);
                    }
                });
    }

    /*
用途：	商家入驻
token	string	是	登录唯一标识
typeid	int	是	商家类型id
classid	int	否	店铺类型id
name	string	是	店铺名称
brand_pic	int	是	招牌图片id
lat	float	是	店铺纬度
lng	string	是	店铺经度
addr	string	是	店铺详细地址
city	int	是	城市id
legal_person	string	是	法人名称
tel	string	是	电话
card_z	int	是	身份证正面照片id
card_f	int	是	身份证反面照片id
license	int	是	营业执照id
* */
    public static void shopjoin(String token,int typeid,int classid,String name,String brand_pic,
                              float lat,float lng,String addr,int city,String legal_person,String tel,
                                String card_z,String card_f,String license,final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.SHOPJOIN)
                .params("token",token)
                .params("typeid",typeid)
                .params("classid",classid)
                .params("name",name)
                .params("brand_pic",brand_pic)
                .params("lat",lat)
                .params("lng",lng)
                .params("addr",addr)
                .params("city",city)
                .params("legal_person",legal_person)
                .params("tel",tel)
                .params("card_z",card_z)
                .params("card_f",card_f)
                .params("license",license)
                .execute(new StringDialogCallback(activity,"请稍后")
                {
                    @Override
                    public void onSuccess(Response<String> response)
                    {
                        callBack.onSuccess(what,response);
                    }
                });
    }




    /*
用途：		店铺详情资料
token	string	是	用户登录唯一标识
shopid	int	是	店铺id
* */
    public static void myshow(String token,int shopid,final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.MYSHOP)
                .params("token",token)
                .params("shopid",shopid)
                .execute(new StringDialogCallback(activity,"请稍后")
                {
                    @Override
                    public void onSuccess(Response<String> response)
                    {
                        callBack.onSuccess(what,response);
                    }
                });
    }
}
