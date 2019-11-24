package xyz.lesliehirsi.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lesliehirsi.bank.mapper.BankMapper;

import java.util.Map;

/**
 * Creat by ZhangXueRong on 2019/4/27
 */
@Service
public class BankService {

    @Autowired
    BankMapper bankMapper;

    public void insertWxUser(Map<String, Object> param){
        bankMapper.insertWxUser(param);
    }

    public boolean checkUserExist(String openId){
        int ret = bankMapper.checkUserExist(openId);
        return ret > 0;
    }

}
