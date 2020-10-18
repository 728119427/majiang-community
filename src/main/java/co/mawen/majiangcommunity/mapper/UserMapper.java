package co.mawen.majiangcommunity.mapper;

import co.mawen.majiangcommunity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    /**
     * 添加user
     * @param user
     */
    @Insert("INSERT INTO user (account_id,name,token,gmt_create,gmt_modified)VALUES(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    /**
     * 根据token查找user
     * @param token
     * @return
     */
    @Select("SELECT * FROM user WHERE token=#{token}")
    User findByToken(String token);
}
