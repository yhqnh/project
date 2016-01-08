package com.project.service;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.project.dal.domain.User;
import com.project.service.user.UserService;

@Slf4j
public class UserServicelTest extends AbstractTest {

	@Autowired
	private UserService userService;

	@Test
	public void testGet() throws InterruptedException {
		//

		User user = userService.selectByPrimaryKey(1L);
		log.info("user name {}", user.getLoginName());
		user.setLoginName("5555");
		userService.updateByPrimaryKey(user);

		User user2 = userService.selectByPrimaryKey(1L);
		log.info("user2:{}", new Gson().toJson(user2));
	}
}
