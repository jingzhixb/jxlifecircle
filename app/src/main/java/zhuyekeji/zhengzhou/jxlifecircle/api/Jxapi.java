package zhuyekeji.zhengzhou.jxlifecircle.api;

/**
 * Created by Administrator on 2018/6/29.
 */

public class Jxapi
{
    public static final String BASE="http://192.168.1.14/jxsc/index.php/";

    public static final String USER_REGISTER=BASE+"Home/Register/user_add";//用户注册
    public static final String USER_LOGIN=BASE+"Home/Login/login";//用户登录
    public static final String AGAIN_SET_PASSWORD=BASE+"Home/Register/setnewpassword";//	忘记密码，重新设置密码
    public static final String UPIMG=BASE+"Mobile/Mobile/uploadFile";//上传图片
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

    public static final String EDIT_GOODS=BASE+"Home/Shop/edit_goods";//	编辑产品信息提交

    public static final String GOODS_LIST=BASE+"Home/Shop/goods_list";	//商品管理-商品列表

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





}
