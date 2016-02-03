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
}
