package co.mawen.majiangcommunity.service.impl;

import co.mawen.majiangcommunity.mapper.UserMapper;
import co.mawen.majiangcommunity.model.User;
import co.mawen.majiangcommunity.model.UserExample;
import co.mawen.majiangcommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void insert(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
//        User _user = userMapper.getByAccountId(Integer.parseInt(String.valueOf()));
        if(users.size()==0){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            userMapper.insert(user);
        }else {
            User _user = users.get(0);
            _user.setToken(user.getToken());
            _user.setName(user.getName());
            _user.setBio(user.getBio());
            _user.setAvatarUrl(user.getAvatarUrl());
            _user.setGmtModified(System.currentTimeMillis());
//            userMapper.update(_user);
            userMapper.updateByPrimaryKeySelective(_user);

        }

    }

    @Override
    public User findByToken(String token) {
         //userMapper.findByToken(token);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andTokenEqualTo(token);
        List<User> users = userMapper.selectByExample(userExample);
        return users.get(0);
    }


}
