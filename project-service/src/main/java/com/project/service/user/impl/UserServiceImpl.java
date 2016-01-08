package com.project.service.user.impl;

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
public class UserServiceImpl extends _UserSliceDisp implements UserService {

	@Autowired
	private UserDao userDao;

	public User selectByPrimaryKey(Long id) {
		User user = userDao.selectByPrimaryKey(id);
		return user;
	}

	public int updateByPrimaryKey(User user) {
		int result = userDao.updateByPrimaryKey(user);
		return result;
	}
	
	@Override
	public UserDto getUserInfoByPeimaryKey(long id, Current __current) {
		log.info("id:" + id);
//		User user = userDao.selectByPrimaryKey(id);
		User user = new User();
		user.setId(1L);
		user.setLoginName("loginName");
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}
}
