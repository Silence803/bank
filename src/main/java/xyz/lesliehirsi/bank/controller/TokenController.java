package xyz.lesliehirsi.bank.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lesliehirsi.bank.domain.WxSession;
import xyz.lesliehirsi.bank.util.SignUtil;
import xyz.lesliehirsi.bank.util.WeiXinUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Creat by ZhangXueRong on 2019/4/22
 */
@RestController
public class TokenController {

    @GetMapping("/wx")
    public void signatureToken(HttpServletRequest request, HttpServletResponse response){
        System.out.println("微信token验证");
        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
            out = null;
        }

    }

    @GetMapping("/wx/session")
    public String getOpenId(HttpServletRequest request) throws IOException {
        String code = request.getParameter("code");
        WxSession wxSession = WeiXinUtil.getWxSession(code);
        return JSONObject.toJSONString(wxSession);
    }

    @GetMapping("/test")
    public String test(){
        return "hello";
    }

}
