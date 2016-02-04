package com.project.service.user;

import com.project.api.slice.common.BussinessResponse;
import com.project.dal.domain.User;
import com.project.service.BaseService;

public interface UserService extends BaseService {

	public BussinessResponse selectByPrimaryKey(Long id);
	
	public User updateByPrimaryKey(User user);
}
