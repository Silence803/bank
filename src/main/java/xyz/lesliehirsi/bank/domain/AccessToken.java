package xyz.lesliehirsi.bank.domain;

import lombok.Data;

/**
 * Creat by ZhangXueRong on 2019/4/22
 */
@Data
public class AccessToken {
    private String token;
    private Integer expiresIn;
}
