package zhuyekeji.zhengzhou.jxlifecircle.bean;

/**
 * Created by Administrator on 2018/6/22.
 */

public class HomeIconInfo
{
    String iconName;
    int iconID;

    public HomeIconInfo(String iconName, int iconID)
    {
        this.iconName = iconName;
        this.iconID = iconID;
    }

    public String getIconName()
    {
        return iconName;
    }

    public void setIconName(String iconName)
    {
        this.iconName = iconName;
    }

    public int getIconID()
    {
        return iconID;
    }

    public void setIconID(int iconID)
    {
        this.iconID = iconID;
    }
}
