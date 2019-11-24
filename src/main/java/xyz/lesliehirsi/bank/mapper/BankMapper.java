package xyz.lesliehirsi.bank.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Creat by ZhangXueRong on 2019/4/27
 */
@Repository
public interface BankMapper {

    @Insert("insert into user values(#{openid}, #{nick_name}, null, default, #{comment}, default)")
    void insertWxUser(Map<String, Object> param);

    @Select("select count(*) from user where openid=#{openId}")
    int checkUserExist(String openId);
}
