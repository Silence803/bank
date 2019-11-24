package xyz.lesliehirsi.bank.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.lesliehirsi.bank.domain.ResponseBaseDTO;
import xyz.lesliehirsi.bank.service.BankService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Creat by ZhangXueRong on 2019/4/27
 */
@RestController
public class BankController {

    @Autowired
    BankService bankService;

    @GetMapping("/login")
    public ResponseBaseDTO login(HttpServletRequest request) {

        System.out.println("login---------------------");
        ResponseBaseDTO dto = new ResponseBaseDTO();
        String openid = request.getParameter("openid");
        boolean ret = bankService.checkUserExist(openid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("is_exist", ret);
        dto.setData(jsonObject);
        dto.setSuccess(true);
        return dto;
    }

    @PostMapping("/init")
    public ResponseBaseDTO init(@RequestBody(required = false) JSONObject jo, HttpServletRequest request) {
        System.out.println("init-----------");
        ResponseBaseDTO dto = new ResponseBaseDTO();
        JSONObject data = jo.getJSONObject("data");
        String openid = data.getString("openid");
        String nickName = data.getString("nick_name");
        String comment = data.getString("comment");
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("openid", openid);
            param.put("nick_name", nickName);
            param.put("comment", comment);
            bankService.insertWxUser(param);
            dto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            dto.setSuccess(false);
        }
        return dto;
    }


}
