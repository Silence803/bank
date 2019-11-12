package xyz.lesliehirsi.bank.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lesliehirsi.bank.domain.ResponseBaseDTO;
import xyz.lesliehirsi.bank.service.BankService;

import javax.servlet.http.HttpServletRequest;

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



}
