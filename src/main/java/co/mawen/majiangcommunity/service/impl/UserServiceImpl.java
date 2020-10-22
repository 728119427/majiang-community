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
        User _user = userMapper.getByAccountId(Integer.parseInt(String.valueOf(user.getAccountId())));
        if(_user==null){
            user.setGmtCreate(System.currentTimeMillis());
            userMapper.insert(user);
        }else {
            _user.setToken(user.getToken());
            _user.setName(user.getName());
            _user.setBio(user.getBio());
            _user.setAvatarUrl(user.getAvatarUrl());
            userMapper.update(_user);

        }

    }

    @Override
    public User findByToken(String token) {
        return userMapper.findByToken(token);
    }
}
