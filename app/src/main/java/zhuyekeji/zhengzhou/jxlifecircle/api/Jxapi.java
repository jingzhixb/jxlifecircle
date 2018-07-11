package zhuyekeji.zhengzhou.jxlifecircle.api;

/**
 * Created by Administrator on 2018/6/29.
 */

public class Jxapi
{
    public static final String BASE="http://192.168.1.19/jxsc/index.php/";


    public static final String UPDATAPASSWORD=BASE+"Home/Setsysterm/updatepass";
    public static final String GETVERIFY=BASE+"Home/Setsysterm/getverify";
    public static final String GUANYU=BASE+"Home/Setsysterm/aboutsysterm";//关于软件
    public static final String SEDMSM=BASE+"Home/Register/sms";//发送短信验证码
    public static final String USER_REGISTER=BASE+"Home/Register/user_add";//用户注册
    public static final String USER_LOGIN=BASE+"Home/Login/login";//用户登录
    public static final String AGAIN_SET_PASSWORD=BASE+"Home/Register/setnewpassword";//	忘记密码，重新设置密码
    public static final String UPIMG=BASE+"Home/Mobile/uploadFile";//上传图片
    public static final String SHOPCORE=BASE+"Home/Shop/my_shop_brief";//进商家中心，店铺简要信息展示
    public static final String SHOPJOIN=BASE+"Home/Shop/join_shop";//	商家入驻
    public static final String MYSHOP=BASE+"Home/Shop/my_shop";//	店铺详情资料
    public static final String SHOP_CHECK=BASE+"Home/Shop/shop_check";//	店铺信息提交审核
    public static final String SHOP_LIST=BASE+"Home/Shop/type_list";//商家中心-产品管理的产品分类列表数据
    public static final String ADD_SHOP=BASE+"Home/Shop/add_goods";//	商品管理-新建商品
    public static final String SET_SHELF=BASE+"Home/Shop/set_shelf";//	编辑产品原始数据、产品下架
    public static final String EDIT_USER=BASE+"Home/Register/edit_header";//编辑头像昵称
    public static final String ADD_TYPE=BASE+"Home/Shop/add_type";//	添加产品分类
    public static final String DEL_TYPE=BASE+"Home/Shop/del_type";//删除产品分类
    public static final String UPDUOTU=BASE+"Home/Mobile/upload_moreImg";
    public static final String EDIT_GOODS=BASE+"Home/Shop/edit_goods";//	编辑产品信息提交
    public static final String SIGN=BASE+"Home/Integralmall/jifenqiandao";//签到
    public static final String CITY_ALL=BASE+"Home/Mobile/all_city";//城市列表
    public static final String GOODS_LIST=BASE+"Home/Shop/goods_list";	//商品管理-商品列表

    public static final String YOUHUIERJI=BASE+"Home/Mobile/sub_discount";//优惠购对应的二级分类

    public static final String KANJIALIST=BASE+"Home/Cutdownprice/cutdownlist";
    /*
    * 优惠卷
    * */
    public static final String ADD_YOUHUIJUAN=BASE+"Home/Youhuiquan/addyouhuiquan";//商家添加优惠卷
    public static final String DEL_YOUHUIJUAN=BASE+"Home/Youhuiquan/deletecoupon";//	删除优惠券
    public static final String UPORDOWN=BASE+"Home/Youhuiquan/upordown";//上下架

    public static final String COUPONLIST=BASE+"Home/Youhuiquan/couponlist";//展示商家发布过的优惠券
    public static final String EDITCONPOU=BASE+"Home/Youhuiquan/editcoupon";//优惠券点击编辑时返回的数据

    public static final String UPDATECOUPON=BASE+"Home/Youhuiquan/updatecoupon";//更新优惠卷

    public static final String MYCOUPON=BASE+"Home/Youhuiquan/mycouponlist";//读取用户买的优惠卷
    public static final String COUPONSHOP=BASE+"Home/Youhuiquan/couponshop";//	优惠购中点击优惠券时显示所有优惠券商家信息

    /*
    *
    * 拼车
    * */
    public static final String GETALLPINCHE=BASE+"/Home/message/getCarpoolingAll";//获取拼车所有信息
    public static final String CHRFINDREN=BASE+"/Home/message/getCarpoolingForHum";//车找人
    public static final String RENFINDCHE=BASE+"/Home/message/getCarpoolingForCar";//人找车
    public static final String TIANTIANPIN=BASE+"/Home/message/getCarpoolingForDay";//天天拼车
    public static final String ADDRENFINDCHE=BASE+"/Home/message/addCarpoolingForCar";//	添加人找车信息
    public static final String ADDCHRFINDREN=BASE+"/Home/message/addCarpoolingForHum";//添加车找人
    public static final String ADDTIANTIAN=BASE+"/Home/message/addCarpoolingForDay";//添加天天拼车
    public static final String PINCHEDELITE=BASE+"/Home/message/getCarpoolingDetail";//获取拼车详情
    public static final String GETHOT=BASE+"/Home/message/getHotroad";


    /*
    * 收货地址
    * */
  public static final String MYSHOUHUO=BASE+"Home/Mymessage/myaddress";//所有收货地址
  public static final String ADDADDRESS=BASE+"Home/Mymessage/addmyaddress";//添加收货地址
    public static final String EDADDRESS=BASE+"Home/Mymessage/editaddress";//	点击编辑获取要修改的数据
    public static final String TIJIAOEDADDRESS=BASE+"Home/Mymessage/updateaddress";//提交编辑后的地址
    public static final String DELETEADDRESS=BASE+"Home/Mymessage/deleteaddress";//删除地址
    public static final String DEFADDRESS=BASE+"Home/Mymessage/morenaddress";
/*
* 优惠购订单
* */

public static final String ORDER_YOUHUI=BASE+"Home/Order/discount_order";//用户优惠购
public static final String ORDER_JIUDIAN=BASE+"Home/Order/hotel_order";//用户酒店
public static final String DELITE_ORDER=BASE+"Home/Order/del_order";//删除订单
    public static final String SHOPORDER_YOUHUIGOU=BASE+"Home/Order/sell_order";//商家订单
    public static final String SHOPJIUDIANORDER=BASE+"Home/Order/hotelsell_order";//商家酒店订单

    /*
    * 我的收藏
    * */
    public static final String MYCONLL=BASE+"Home/message/getCollectionMessage";//	读取商品、店铺的信息收藏
    public static final String DELETE_COMLLLECT=BASE+"/getCollectionGoods";
}
