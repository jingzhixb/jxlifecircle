package zhuyekeji.zhengzhou.jxlifecircle.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/6/29.
 */

public class JsonUtile
{
    public static String getbody(String body)
    {
        try
        {
            JSONObject jsonObject=new JSONObject(body);
            String code=jsonObject.getString("code");
            if (code.equals("200"))
            {
                String object=jsonObject.getString("body");
                return object;
            }else {
                return "";
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return "";
    }
public static String getCode(String body)
{
    try
    {
        JSONObject o=new JSONObject(body);
        String code=o.getString("code");
        return code;
    } catch (JSONException e)
    {
        e.printStackTrace();
    }
    return "";
}
    public static String getresulter(String body)
    {
        try
        {
            JSONObject jsonObject=new JSONObject(body);
            String result=jsonObject.getString("result");
            return result;
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
