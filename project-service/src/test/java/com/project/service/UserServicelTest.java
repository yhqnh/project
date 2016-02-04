package com.project.service;

import com.project.api.slice.common.BussinessResponse;
import com.project.api.slice.user._UserSliceOperations;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.project.dal.domain.User;
import com.project.service.user.UserService;

@Slf4j
public class UserServicelTest extends AbstractTest {

	@Autowired
	private _UserSliceOperations userService;

	@Test
	public void testGet() throws InterruptedException {
		//

		BussinessResponse bussinessResponse = userService.getUserInfoByPeimaryKey(1L, null);
		log.info("bussinessResponse jsonObject {}", bussinessResponse.jsonObject);
	}
}
