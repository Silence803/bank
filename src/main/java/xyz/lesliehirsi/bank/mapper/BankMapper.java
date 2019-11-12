package xyz.lesliehirsi.bank.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Creat by ZhangXueRong on 2019/4/27
 */
@Repository
public interface BankMapper {

    @Insert("insert into bank_user values(#{openId}, current_timestamp)")
    void insertWxUser(String openId);

    @Select("select count(*) from bank_user where openid=#{openId}")
    int checkUserExist(String openId);
}
