package zhuyekeji.zhengzhou.jxlifecircle.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/16.
 */

public class KanJiaDeliteBean
{

    /**
     * headerpic : ./Uploads/20180628/5b34afa1c306c.jpg
     * message : [{"id":"1","img":"Uploads/Picture/2018-07-09/5b42fac1f18f8.png","name":"商品1","price":"89.99","time":"172800"}]
     * yikan : 89.99
     * shengyu : 0
     * kanjiabang : [{"others":"62","money":"30.00","time":"1531400000","nickname":"182****1363","header":"http://192.168.1.14/jxsc/Uploads/userlogo/5b34b09a22180.jpg"},{"others":"57","money":"29.00","time":"1531384567","nickname":"瀑布下的鱼8","header":"./Uploads/20180628/5b34afa1c306c.jpg"},{"others":"59","money":"12.44","time":"1531372345","nickname":"135****3232","header":"http://192.168.1.14/jxsc/Uploads/userlogo/5b34b09a22180.jpg"},{"others":"51","money":"8.56","time":"1531370000","nickname":"瀑布下的鱼2","header":"./Uploads/20180628/5b34afa1c306c.jpg"},{"others":"61","money":"14.00","time":"1531367689","nickname":"182****1563","header":"http://192.168.1.14/jxsc/Uploads/userlogo/5b34b09a22180.jpg"},{"others":"60","money":"5.99","time":"1531366999","nickname":"182****1362","header":"http://192.168.1.14/jxsc/Uploads/userlogo/5b34b09a22180.jpg"}]
     */

    private String headerpic;
    private String yikan;
    private int shengyu;
    private List<MessageBean> message;
    private List<KanjiabangBean> kanjiabang;

    public String getHeaderpic()
    {
        return headerpic;
    }

    public void setHeaderpic(String headerpic)
    {
        this.headerpic = headerpic;
    }

    public String getYikan()
    {
        return yikan;
    }

    public void setYikan(String yikan)
    {
        this.yikan = yikan;
    }

    public int getShengyu()
    {
        return shengyu;
    }

    public void setShengyu(int shengyu)
    {
        this.shengyu = shengyu;
    }

    public List<MessageBean> getMessage()
    {
        return message;
    }

    public void setMessage(List<MessageBean> message)
    {
        this.message = message;
    }

    public List<KanjiabangBean> getKanjiabang()
    {
        return kanjiabang;
    }

    public void setKanjiabang(List<KanjiabangBean> kanjiabang)
    {
        this.kanjiabang = kanjiabang;
    }

    public static class MessageBean
    {
        /**
         * id : 1
         * img : Uploads/Picture/2018-07-09/5b42fac1f18f8.png
         * name : 商品1
         * price : 89.99
         * time : 172800
         */

        private String id;
        private String img;
        private String name;
        private String price;
        private String time;

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getImg()
        {
            return img;
        }

        public void setImg(String img)
        {
            this.img = img;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getPrice()
        {
            return price;
        }

        public void setPrice(String price)
        {
            this.price = price;
        }

        public String getTime()
        {
            return time;
        }

        public void setTime(String time)
        {
            this.time = time;
        }
    }

    public static class KanjiabangBean
    {
        /**
         * others : 62
         * money : 30.00
         * time : 1531400000
         * nickname : 182****1363
         * header : http://192.168.1.14/jxsc/Uploads/userlogo/5b34b09a22180.jpg
         */

        private String others;
        private String money;
        private String time;
        private String nickname;
        private String header;

        public String getOthers()
        {
            return others;
        }

        public void setOthers(String others)
        {
            this.others = others;
        }

        public String getMoney()
        {
            return money;
        }

        public void setMoney(String money)
        {
            this.money = money;
        }

        public String getTime()
        {
            return time;
        }

        public void setTime(String time)
        {
            this.time = time;
        }

        public String getNickname()
        {
            return nickname;
        }

        public void setNickname(String nickname)
        {
            this.nickname = nickname;
        }

        public String getHeader()
        {
            return header;
        }

        public void setHeader(String header)
        {
            this.header = header;
        }
    }
}
