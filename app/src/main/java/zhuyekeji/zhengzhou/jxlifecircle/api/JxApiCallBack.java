package zhuyekeji.zhengzhou.jxlifecircle.api;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.List;

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
    public static void user_register(String user_phone, String password
            , final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.USER_REGISTER)
                .params("user_phone",user_phone)
                .params("password",password)
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
send_type		是	0或不传是注册 1更改手机 绑定银行卡 修改密码
* */
    public static void sedmsm(String user_phone, String send_type,
                                          final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.SEDMSM)
                .params("user_phone",user_phone)
                .params("send_type",send_type)
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
token	string	是	手机号
* */
    public static void guanyu(String token,
                              final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.GUANYU)
                .params("token",token)
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
用途：点击完成，实现密码修改
token	string	是
first	string	是	新密码
second	string	是	再次确认密码
* */
    public static void updatapassword(String token,String first,String second,
                              final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.UPDATAPASSWORD)
                .params("token",token)
                .params("first",first)
                .params("second",second)
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
用途：	获取验证码数字
token		是
thetel	string	是	用户输入的手机号
* */
    public static void getverify(String token,String thetel,
                              final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.GETVERIFY)
                .params("token",token)
                .params("thetel",thetel)
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
用途：积分签到
token	string	是
* */
    public static void sign(String token,
                              final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.SIGN)
                .params("token",token)
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
用途：积分签到
token	string	是	手机号
* */
    public static void kanjia(String token,
                            final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.KANJIALIST)
                .params("token",token)
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
img_str	string	是	base64文件，多个用英文逗号拼接
* */
    public static void upduotu(String img_str,
                              final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.UPDUOTU)
                .params("img_str",img_str)
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
用途：	进商家中心，店铺简要信息展示
token
* */
    public static void city_all(final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.CITY_ALL)
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
typeid	int	是	一级分类id
* */
    public static void youhui_er(int typeid,final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>get(Jxapi.YOUHUIERJI)
                .params("typeid",typeid)
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
                              double lat,double lng,String addr,int city,String legal_person,String tel,
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





    /*
用途：		店铺信息提交审核
token	string	是	用户登录唯一标识
shopid	int	是	店铺id
brand_pic	int	否	招牌照片id
name	string	否	店铺名称
tel	string	否	店铺电话
addr	string	是	地址
* */
    public static void shop_check(String token,int shopid,int brand_pic,String name,
                                  String tel,String addr,final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.SHOP_CHECK)
                .params("token",token)
                .params("shopid",shopid)
                .params("brand_pic",brand_pic)
                .params("name",name)
                .params("tel",tel)
                .params("addr",addr)
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
    public static void shop_list(String token,int shopid,final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.SHOP_LIST)
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




    /*
用途：			商品管理-新建商品
token	string	是	登录唯一标识
shopid	int	是	店铺id
type	int	是	1添加分类产品，2添加套餐产品
pic	int	是	产品图片id
title	string	是	商品名称
desc	string	是	商品描述
label	string	否	商品标签（多个标签之间用‘’英文‘’逗号拼接）
type_meal	array	是	分类产品传分类id，套餐产品传套餐内容数组
price	float	是	价格
market_price	float	是	市场价
validity_time	string	否	有效期或者酒店入住时间（开始时间和结束时间之间用英文逗号拼接）
use_time_f	string	否	使用时间1
use_time_s	string	否	使用时间2
is_week	int	是	节假日通用（1是2否） 或 房间数量
rule	string	是	使用规则
* */
    public static void add_shop(String token, int shopid, int type, int pic,
                                String title, String desc, String label, List<String> type_meal,String price ,
                                float market_price,String validity_time,String use_time_f,
                                String use_time_s,int is_week,String rule,
                                final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.ADD_SHOP)
                .params("token",token)
                .params("shopid",shopid)
                .params("type",type)
                .params("pic",pic)
                .params("title",title)
                .params("desc",desc)
                .params("label",label)
                .addUrlParams("type_meal",type_meal)
                .params("price",price)
                .params("market_price",market_price)
                .params("validity_time",validity_time)
                .params("use_time_f",use_time_f)
                .params("use_time_s",use_time_s)
                .params("is_week",is_week)
                .params("rule",rule)

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
用途：			编辑产品原始数据、产品下架
token	string	是	登录唯一标识
pid	int	是	产品id
type	int	是	1产品编辑数据 2下架
* */
    public static void set_shelf(String token,int pid,int type,final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.SET_SHELF)
                .params("token",token)
                .params("pid",pid)
                .params("type",type)
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
*
* 	编辑头像昵称
key	参数类型	是否必须	参数解释
nickname	string	否	用户昵称
header_pic	int	否	头像id
token	string	是	登录唯一标识
*
* */

    public static void edit_header(String token,String nickname,int header_pic,final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.EDIT_USER)
                .params("token",token)
                .params("nickname",nickname)
                .params("header_pic",header_pic)
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
*
* 		添加产品分类
token	string	是	登录标识
shopid	int	是	店铺id
class_name	string	是	分类名称
*
* */

    public static void add_type(String token,String class_name,int shopid,final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.ADD_TYPE)
                .params("token",token)
                .params("class_name",class_name)
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

        /*
*
* 		删除产品分类
token	string	是	登录标识
class_Id	int	是	分类id
*
* */

    public static void del_type(String token,int class_Id,final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.DEL_TYPE)
                .params("token",token)
                .params("class_Id",class_Id)
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
    *	编辑产品信息提交
    * token	string	是	登录唯一标识
pic	int	否	产品图片id
title	string	否	产品名称
desc	string	否	产品描述
price	float	否	产品描述
label	string	否	产品标签（多个标签之间用英文逗号分隔）
type_meal	string	否	分类产品传分类id，套餐产品传套餐内容数组
price	float	否	价格
market_price	float	否	市场价
validity_time	string	否	有效期或入住时间（开始时间和结束时间之间用英文逗号拼接）
use_time_f	string	否	使用时间1（开始时间和结束时间之间用英文逗号拼接）
use_time_s	string	否	使用时间2（开始时间和结束时间之间用英文逗号拼接）
is_week	int	否	节假日通用（1是2否） 或 房间数量
rule	string	否	使用规则
    * */
    public static void edit_shop(String token, int shopid, int type, int pic,
                                String title, String desc, String label, List<String> type_meal,String price ,
                                float market_price,String validity_time,String use_time_f,
                                String use_time_s,int is_week,String rule,
                                final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.EDIT_GOODS)
                .params("token",token)
                .params("shopid",shopid)
                .params("type",type)
                .params("pic",pic)
                .params("title",title)
                .params("desc",desc)
                .params("label",label)
                .addUrlParams("type_meal",type_meal)
                .params("price",price)
                .params("market_price",market_price)
                .params("validity_time",validity_time)
                .params("use_time_f",use_time_f)
                .params("use_time_s",use_time_s)
                .params("is_week",is_week)
                .params("rule",rule)

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
用途：商品管理-商品列表
token	string	是	登录唯一标识
shopid	int	是	店铺id
type	int	否	1分类产品 2套餐产品
type_meal	int	否	分类id(当type是1时，传入该值)
is_shelf	int	否	2已下架产品
* */
    public static void goods_list(String token, int shopid
                                  ,int type,int type_meal,int is_shelf
            , final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.USER_REGISTER)
                .params("token",token)
                .params("shopid",shopid)
                .params("type",type)
                .params("type_meal",type_meal)
                .params("is_shelf",is_shelf)
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
用途：商家添加优惠券
token	string	是
coupon_money	string	是	面值金额，整数
coupon_label	int	是	优惠券标签
coupon_num	string	是	优惠券总数，必须是整数
coupon_price	string	是	优惠券价格，可以是小数
* */
    public static void add_youhuijuan(String token, String coupon_money
            ,int coupon_label,int coupon_num,String coupon_price
            , final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.ADD_YOUHUIJUAN)
                .params("token",token)
                .params("coupon_money",coupon_money)
                .params("coupon_label",coupon_label)
                .params("coupon_num",coupon_num)
                .params("coupon_price",coupon_price)
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
用途：删除优惠卷
token		是
theid	int	是	优惠券编号
* */
    public static void del_youhuijuan(String token, int theid
            , final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.DEL_YOUHUIJUAN)
                .params("token",token)
                .params("theid",theid)
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
用途：	优惠券上、下架
token		是
theid	int	是	优惠券编号
sta	int	是	优惠券上、下架状态值
* */
    public static void unordown(String token, int theid,String sta
            , final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.UPORDOWN)
                .params("token",token)
                .params("theid",theid)
                .params("sta",sta)
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
用途：	展示商家发布过的优惠券
token	string	是
userid	int	是	用户id
* */
    public static void couponlist(String token, int userid
            , final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.COUPONLIST)
                .params("token",token)
                .params("userid",userid)
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
用途：	优惠券点击编辑时返回的数据
token	string	是
theid	int	是	优惠券编号
* */
    public static void editcoupon(String token, int theid
            , final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.EDITCONPOU)
                .params("token",token)
                .params("theid",theid)
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
用途：	优惠券信息修改
token	string	是
coupon_money	string	是	优惠券面额，整数
coupon_label	int	是	优惠券标签
coupon_num	string	是	优惠券数量
coupon_price	string	是	优惠券价格，可以是小数
couid	int	是	优惠券编号
* */
    public static void updatecoupon(String token, String coupon_money
            ,int coupon_label,int coupon_num,String coupon_price,int couid
            , final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.UPDATECOUPON)
                .params("token",token)
                .params("coupon_money",coupon_money)
                .params("coupon_label",coupon_label)
                .params("coupon_num",coupon_num)
                .params("coupon_price",coupon_price)
                .params("couid",couid)
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
用途：	优惠券点击编辑时返回的数据
token	string	是
* */
    public static void mycoupon(String token,
            final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.MYCOUPON)
                .params("token",token)
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
用途：	优惠购中点击优惠券时显示所有优惠券商家信息
token		是
thelat	float	是	用户坐标纬度
thelng	float	是	用户坐标经度
* */
    public static void couponshop(String token,float thelat,float thelng,
                                final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.MYCOUPON)
                .params("token",token)
                .params("thelat",thelat)
                .params("thelng",thelng)
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
用途：		获取拼车所有信息据
token	string	是
city int 城市ID
* */
    public static void getallpinche(String token,int city,
                                final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.GETALLPINCHE)
                .params("token",token)
                .params("city",city)
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
用途：		获取拼车下的车找人信息
token	string	是
city int 城市ID
* */
    public static void chefindren(String token,int city,
                                    final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.CHRFINDREN)
                .params("token",token)
                .params("city",city)
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
用途：			获取人找车信息
token	string	是
city int 城市ID
* */
    public static void renfindche(String token,int city,
                                  final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.RENFINDCHE)
                .params("token",token)
                .params("city",city)
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
用途：			天天拼车
token	string	是
city int 城市ID
* */
    public static void tiantianpin(String token,int city,
                                  final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.TIANTIANPIN)
                .params("token",token)
                .params("city",city)
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
用途：			添加人找车信息
token	string	是	token
city	int	是	当前城市ID
is_top	int	否	是否置顶，1为置顶，默认为0
setout	int	是	出发城市ID
destination	int	是	目的城市id
time	int	是	出发时间戳
passenger	int	是	乘车人数
contact	string	是	联系人
contact_tel	long	是	联系电话
oth_requst	string	是	其他需求
* */
    public static void addrenindche(String token,int city,int is_top,int setout,int destination,
                                   int time,int passenger,String contact,String contact_tel,String oth_requst,
                                   final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.ADDRENFINDCHE)
                .params("token",token)
                .params("city",city)
                .params("is_top",is_top)
                .params("setout",setout)
                .params("destination",destination)
                .params("time",time)
                .params("passenger",passenger)
                .params("contact",contact)
                .params("contact_tel",contact_tel)
                .params("oth_requst",oth_requst)
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
用途：			添加车找人
token	string	是	token
city	int	是	当前城市ID
setout	int	是	出发城市ID
destination	int	是	目的城市ID
time	int	是	出发时间戳
is_top	int	否	是否置顶，置顶传1
can_seat	int	是	能座人数
oth_requst	string	是	其它需求
driver_tel	long	是	联系号码
contact	string	是	联系人
* */
    public static void addchefindren(String token,int city,int is_top,int setout,int destination,
                                     int time,String contact,String driver_tel,String oth_requst,int can_seat,
                                     final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.ADDCHRFINDREN)
                .params("token",token)
                .params("city",city)
                .params("is_top",is_top)
                .params("setout",setout)
                .params("destination",destination)
                .params("time",time)
                .params("can_seat",can_seat)
                .params("contact",contact)
                .params("driver_tel",driver_tel)
                .params("oth_requst",oth_requst)
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
用途：		添加天天拼车
token	string	是	token
city	int	是	当前城市ID
setout		是	出发城市
destination		是	目的城市
time		是	出发时间戳
is_top		是	是否置顶，置顶传1
passenger		是	人数
contact_tel		是	联系电话
contact		是	联系人
oth_requst		是	其它需求
* */
    public static void addtiantianpinche(String token,int city,int is_top,int setout,int destination,
                                     int time,String contact,String driver_tel,String oth_requst,int passenger,
                                     final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.ADDTIANTIAN)
                .params("token",token)
                .params("city",city)
                .params("is_top",is_top)
                .params("setout",setout)
                .params("destination",destination)
                .params("time",time)
                .params("passenger",passenger)
                .params("contact",contact)
                .params("driver_tel",driver_tel)
                .params("oth_requst",oth_requst)
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
用途：			获取拼车详情
token	string	是	token
city		是	城市ID
id		是	拼车信息的id
* */
    public static void pinchedelite(String token,int city,int id,
                                         final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.PINCHEDELITE)
                .params("token",token)
                .params("city",city)
                .params("id",id)
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
用途：			获取热门线路
token	string	是	token
city	int	是	城市id
* */
    public static void gethot(String token,int city,
                                    final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.GETHOT)
                .params("token",token)
                .params("city",city)
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
用途：			获取热门线路
token	string	是	token
* */
    public static void myaddress(String token,
                              final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.MYSHOUHUO)
                .params("token",token)
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
用途：				用户添加收货地址
token		是
name	string	是	收货人姓名
tel	string	是	收货人联系方式
diqu	string	是	收货人所在省市区
jiedao	string	是	收货人所在街道
* */
    public static void addaddress(String token,String name,String tel,String diqu,String jiedao,
                                 final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.ADDADDRESS)
                .params("token",token)
                .params("name",name)
                .params("tel",tel)
                .params("diqu",diqu)
                .params("jiedao",jiedao)
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
用途：			点击编辑获取要修改的数据
token	string	是	token
theid	int	是	收货地址id
* */
    public static void edaddress(String token,String theid,
                                 final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.EDADDRESS)
                .params("token",token)
                .params("theid",theid)
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
用途：				用户添加收货地址
token		是
id	int	是	收货地址id
name	string	是	收货人姓名
tel	string	是	收货人联系方式
diqu	string	是	收货人所在省市区
jiedao	string	是	收货人所在街道
status	int	是	收货地址状态值，（1：为默认地址 0：不是默认地址）
* */
    public static void tijiaoaddress(String token,String name,String tel,String diqu,String jiedao,
                                  String id,int status,
                                  final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.TIJIAOEDADDRESS)
                .params("token",token)
                .params("name",name)
                .params("tel",tel)
                .params("diqu",diqu)
                .params("jiedao",jiedao)
                .params("id",id)
                .params("status",status)
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
用途：			删除收货地址信息
token	string	是	token
theid	int	是	收货地址id
* */
    public static void deleteaddress(String token,String theid,
                                 final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.DELETEADDRESS)
                .params("token",token)
                .params("theid",theid)
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
用途：			设置收货地址的默认状态
token		是
theid	int	是	收货地址id
status	int	是	默认状态值，只能取0或1；0代表取消默认 1代表设置为默认地址
* */
    public static void defaddress(String token,String theid,int status,
                                     final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.DEFADDRESS)
                .params("token",token)
                .params("theid",theid)
                .params("status",status)
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
用途：			订单-优惠购订单（买家）
token	string	是	用户登录唯一标识
type	int	是	（1待消费，2待评价，3历史订单）
* */
    public static void order_youhui(String token,int type,
                                  final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.ORDER_YOUHUI)
                .params("token",token)
                .params("type",type)
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
用途：			订单-优惠购订单（买家）
token	string	是	用户登录唯一标识
type		是	1待入住 2入住中 3待评价 4申请退款 5历史订单
* */
    public static void order_jiudian(String token,int type,
                                    final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.ORDER_JIUDIAN)
                .params("token",token)
                .params("type",type)
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
用途：			删除订单
token	string	是	用户登录唯一标识
orderid	int	是	订单id
* */
    public static void delete_order(String token,String orderid,
                                     final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.DELITE_ORDER)
                .params("token",token)
                .params("orderid",orderid)
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
用途：			删除订单
token	string	是	用户登录唯一标识
type	int	是	1待消费 2已消费 3申请退款 4评价列表
shopid	int	是	店铺id
* */
    public static void shoporder_you(String token,String type,String shopid,
                                    final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.SHOPORDER_YOUHUIGOU)
                .params("token",token)
                .params("type",type)
                .params("shopid",shopid)
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
用途：			酒店订单（商家）
token	string	是	token
type	int	是	类型ID，1为店铺，2为商品
* */
    public static void myshoucang(String token,String type,
                                     final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.MYCONLL)
                .params("token",token)
                .params("type",type)
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
用途：			酒店订单（商家）
token	string	是	用户登录唯一标识
shopid	int	是	店铺id
type	int	是	//1待消费 2已入住 3已退房 4申请退款 5评价列表
* */
    public static void shopjiudian_order(String token,String type,String shopid,
                                         final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.SHOPJIUDIANORDER)
                .params("token",token)
                .params("type",type)
                .params("shopid",shopid)
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
用途：			删除商品、店铺的收藏
token		是	token
id		是	收藏id
* */
    public static void delete_conllect(String token,String id,
                                         final int what, Activity activity, final CallBack callBack)
    {
        OkGo.<String>post(Jxapi.DELETE_COMLLLECT)
                .params("token",token)
                .params("id",id)
                .execute(new StringDialogCallback(activity,"加载中")
                {
                    @Override
                    public void onSuccess(Response<String> response)
                    {
                        callBack.onSuccess(what,response);
                    }
                });
    }
}
