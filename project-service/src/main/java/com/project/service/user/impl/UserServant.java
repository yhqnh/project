package com.project.service.user.impl;

import Ice.Communicator;
import Ice.Current;
import com.google.gson.Gson;
import com.project.api.slice.user.UserDto;
import com.project.api.slice.user._UserSliceDisp;
import com.project.dal.UserDao;
import com.project.dal.domain.User;
import com.project.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class UserServant implements IceBox.Service {

    @Autowired
    private UserDao userDao;

    private Ice.ObjectAdapter _adapter;

    @Override
    public void start(String name, Communicator communicator, String[] strings) {
        _adapter = communicator.createObjectAdapter(name);
        _adapter.add(new UserServiceImpl(), communicator.stringToIdentity("hello"));
        _adapter.activate();
    }

    @Override
    public void stop() {
        _adapter.destroy();
    }

    public User selectByPrimaryKey(Long id) {
        User user = userDao.selectByPrimaryKey(id);
        return user;
    }

    public User updateByPrimaryKey(UserDto userDto,Current __current) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        User result = userDao.updateByPrimaryKey(user);
        return result;
    }

    public UserDto getUserInfoByPeimaryKey(long id, Current __current) {
        log.info("id:" + id);
        User user = userDao.selectByPrimaryKey(id);
//		User user = new User();
        user.setId(1L);
        user.setLoginName("yyyyy");
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.loginName = user.getLoginName();
        log.info("userDto is :{}", new Gson().toJson(userDto));
        return userDto;
    }
}
