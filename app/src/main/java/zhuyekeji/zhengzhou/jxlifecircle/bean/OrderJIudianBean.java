package zhuyekeji.zhengzhou.jxlifecircle.bean;

/**
 * Created by Administrator on 2018/7/11.
 */

public class OrderJIudianBean
{

    /**
     * id : 订单id
     * order_num : 订单编号
     * totalprice : 总价格
     * createdtime : 下单时间
     * title : 名称
     * pic : 图片
     * come_time : 入住时间
     * leave_time : 离开时间
     */

    private String id;
    private String order_num;
    private String totalprice;
    private String createdtime;
    private String title;
    private String pic;
    private String come_time;
    private String leave_time;

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

    public String getCome_time()
    {
        return come_time;
    }

    public void setCome_time(String come_time)
    {
        this.come_time = come_time;
    }

    public String getLeave_time()
    {
        return leave_time;
    }

    public void setLeave_time(String leave_time)
    {
        this.leave_time = leave_time;
    }
}
