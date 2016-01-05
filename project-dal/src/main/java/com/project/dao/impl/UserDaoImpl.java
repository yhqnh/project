package com.project.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.cache.annotation.HashCacheable;
import com.project.dao.UserDao;
import com.project.domain.User;

@Repository
public class UserDaoImpl extends AbstractSessionFactory implements UserDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		return getSqlSession().delete("deleteByPrimaryKey",id);
	}

	@Override
	public int insert(User user) {
		return getSqlSession().insert("insert", user);
	}

	@Override
	@HashCacheable(key = "user", field = "#id")
	public User selectByPrimaryKey(Long id) {
		return getSqlSession().selectOne("selectByPrimaryKey", id);
	}

	@Override
	public List<User> selectAll() {
		return getSqlSession().selectList("selectAll");
	}

	@Override
	@HashCacheable(key = "user", field = "#id")
	public int updateByPrimaryKey(User user) {
		return getSqlSession().update("updateByPrimaryKey", user);
	}
}