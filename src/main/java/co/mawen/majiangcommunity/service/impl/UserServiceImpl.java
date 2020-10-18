package co.mawen.majiangcommunity.service.impl;

import co.mawen.majiangcommunity.mapper.UserMapper;
import co.mawen.majiangcommunity.model.User;
import co.mawen.majiangcommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public User findByToken(String token) {
        return userMapper.findByToken(token);
    }
}
