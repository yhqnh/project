package com.project.service.user;

import com.project.dal.domain.User;
import com.project.service.BaseService;

public interface UserService extends BaseService {

	public User selectByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(User user);
}
