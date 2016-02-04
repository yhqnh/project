package com.project.service.user.impl;

import com.google.gson.Gson;
import com.project.api.slice.common.BussinessResponse;
import com.project.api.slice.user._UserSliceOperations;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Ice.Current;

import com.project.api.slice.user.UserDto;
import com.project.api.slice.user._UserSliceDisp;
import com.project.dal.UserDao;
import com.project.dal.domain.User;
import com.project.service.user.UserService;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements _UserSliceOperations {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDto updateByPeimaryKey(UserDto userDto, Current __current) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        User result = userDao.updateByPrimaryKey(user);
        return userDto;
    }

    @Override
    public BussinessResponse getUserInfoByPeimaryKey(long id, Current __current) {
        BussinessResponse bussinessResponse = new BussinessResponse();
        log.info("id:" + id);
        User user = userDao.selectByPrimaryKey(id);
//		User user = new User();
        user.setId(1L);
        user.setLoginName("yyyyy");
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.loginName = user.getLoginName();
        log.info("userDto is :{}", new Gson().toJson(userDto));

        bussinessResponse.jsonObject = new Gson().toJson(userDto);
        return bussinessResponse;
    }
}
