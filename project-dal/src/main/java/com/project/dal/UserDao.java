package com.project.dal;

import java.util.List;

import com.project.dal.domain.User;


public interface UserDao {
	
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}