package zhuyekeji.zhengzhou.jxlifecircle.bean;

/**
 * Created by Administrator on 2018/7/11.
 */

public class UserOrderYOuHuiBean
{

    /**
     * id : 订单Id
     * order_num : 订单编号
     * totalprice : 总价
     * createdtime : 下单时间
     * title : 产品名称
     * pic : 产品图片
     */

    private String id;
    private String order_num;
    private String totalprice;
    private String createdtime;
    private String title;
    private String pic;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getOrder_num()
    {
        return order_num;
    }

    public void setOrder_num(String order_num)
    {
        this.order_num = order_num;
    }

    public String getTotalprice()
    {
        return totalprice;
    }

    public void setTotalprice(String totalprice)
    {
        this.totalprice = totalprice;
    }

    public String getCreatedtime()
    {
        return createdtime;
    }

    public void setCreatedtime(String createdtime)
    {
        this.createdtime = createdtime;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getPic()
    {
        return pic;
    }

    public void setPic(String pic)
    {
        this.pic = pic;
    }
}
