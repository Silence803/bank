package xyz.lesliehirsi.bank.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import xyz.lesliehirsi.bank.domain.AccessToken;
import xyz.lesliehirsi.bank.domain.WxSession;

import java.io.IOException;

/**
 * Creat by ZhangXueRong on 2019/4/22
 */
public class WeiXinUtil {

    //从微信后台拿到APPID和APPSECRET 并封装为常量
    private static final String APPID = "wxd34fc733183052d2";
    private static final String APPSECRET = "6bdeca7bbe50820c4cd52f108b7065d7";
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String OPEN_ID_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=APPSECRET&js_code=JSCODE&grant_type=authorization_code";

    /**
     * 编写Get请求的方法。但没有参数传递的时候，可以使用Get请求
     *
     * @param url 需要请求的URL
     * @return 将请求URL后返回的数据，转为JSON格式，并return
     */
    public static JSONObject doGetStr(String url) throws ClientProtocolException, IOException {
        DefaultHttpClient client = new DefaultHttpClient();//获取DefaultHttpClient请求
        HttpGet httpGet = new HttpGet(url);//HttpGet将使用Get方式发送请求URL
        JSONObject jsonObject = null;
        HttpResponse response = client.execute(httpGet);//使用HttpResponse接收client执行httpGet的结果
        HttpEntity entity = response.getEntity();//从response中获取结果，类型为HttpEntity
        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");//HttpEntity转为字符串类型
            jsonObject = JSONObject.parseObject(result);//字符串类型转为JSON类型
        }
        return jsonObject;
    }

    /**
     * 编写Post请求的方法。当我们需要参数传递的时候，可以使用Post请求
     *
     * @param url    需要请求的URL
     * @param outStr 需要传递的参数
     * @return 将请求URL后返回的数据，转为JSON格式，并return
     */
    public static JSONObject doPostStr(String url, String outStr) throws ClientProtocolException, IOException {
        DefaultHttpClient client = new DefaultHttpClient();//获取DefaultHttpClient请求
        HttpPost httpost = new HttpPost(url);//HttpPost将使用Get方式发送请求URL
        JSONObject jsonObject = null;
        httpost.setEntity(new StringEntity(outStr, "UTF-8"));//使用setEntity方法，将我们传进来的参数放入请求中
        HttpResponse response = client.execute(httpost);//使用HttpResponse接收client执行httpost的结果
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");//HttpEntity转为字符串类型
        jsonObject = JSONObject.parseObject(result);//字符串类型转为JSON类型
        return jsonObject;
    }

    /**
     * 获取AccessToken
     *
     * @return 返回拿到的access_token及有效期
     */
    public static AccessToken getAccessToken() throws ClientProtocolException, IOException {
        AccessToken token = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);//将URL中的两个参数替换掉
        JSONObject jsonObject = doGetStr(url);//使用刚刚写的doGet方法接收结果
        if (jsonObject != null) { //如果返回不为空，将返回结果封装进AccessToken实体类
            token.setToken(jsonObject.getString("access_token"));//取出access_token
            token.setExpiresIn(jsonObject.getInteger("expires_in"));//取出access_token的有效期
        }
        return token;
    }

    public static WxSession getWxSession(String jsCode) throws IOException {
        WxSession wxSession = new WxSession();
        String url = OPEN_ID_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET).replace("JSCODE", jsCode);
        JSONObject jsonObject = doGetStr(url);//使用刚刚写的doGet方法接收结果
        if (jsonObject != null) {
            wxSession.setOpenid(jsonObject.getString("openid"));
            wxSession.setSession_key(jsonObject.getString("session_key"));
            wxSession.setUnionid(jsonObject.getString("unionid"));
        }
        return wxSession;
    }

    public static void main(String[] args) throws IOException {
        WxSession wxSession = getWxSession("001fvIda1tKazO17opba15KNda1fvId9");
        System.out.println(JSONObject.toJSONString(wxSession));
    }
}
