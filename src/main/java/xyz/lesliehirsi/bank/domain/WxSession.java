package xyz.lesliehirsi.bank.domain;

import lombok.Data;

/**
 * Creat by ZhangXueRong on 2019/4/24
 */
@Data
public class WxSession {

    private String openid;
    private String session_key;
    private String unionid;

}
