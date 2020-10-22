package co.mawen.majiangcommunity.mapper;

import co.mawen.majiangcommunity.model.User;

public interface UserMapper {

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

    User findByToken(String token);

    /**
     * 根据accountId查询user
     * @param AccountId
     * @return
     */
    User getByAccountId(Integer AccountId);

    /**
     * 更新user
     * @param user
     */
    void update(User user);
}
