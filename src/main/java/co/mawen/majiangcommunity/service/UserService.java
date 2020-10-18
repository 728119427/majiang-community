package co.mawen.majiangcommunity.service;

import co.mawen.majiangcommunity.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {

    /**
     * 添加user
     * @param user
     */
    void insert(User user);

    /**
     * 根据token查找user
     * @param token
     * @return
     */
    @Select("SELECT * FROM user WHERE token=#{token}")
    User findByToken(String token);
}
