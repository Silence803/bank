package xyz.lesliehirsi.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lesliehirsi.bank.mapper.BankMapper;

/**
 * Creat by ZhangXueRong on 2019/4/27
 */
@Service
public class BankService {

    @Autowired
    BankMapper bankMapper;

    public void insertWxUser(String openId){
        bankMapper.insertWxUser(openId);
    }

    public boolean checkUserExist(String openId){
        int ret = bankMapper.checkUserExist(openId);
        return ret > 0;
    }

}
