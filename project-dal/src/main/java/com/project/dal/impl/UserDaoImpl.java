package com.project.dal.impl;

import java.util.List;

import com.project.cache.annotation.HashCacheEvict;
import com.project.cache.annotation.HashCachePut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.project.cache.annotation.HashCacheable;
import com.project.dal.UserDao;
import com.project.dal.domain.User;

@Repository
@Slf4j
public class UserDaoImpl extends AbstractSessionFactory implements UserDao {

    @Override
    @HashCacheEvict(key = "user", field = "#id")
    public int deleteByPrimaryKey(Long id) {
        return getSqlSession().delete("deleteByPrimaryKey", id);
    }

    @Override
    @HashCachePut(key = "user", field = "#id")
    public User insert(User user) {
        getSqlSession().insert("insert", user);
        return user;
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
    @HashCachePut(key = "user", field = "#user.id")
    public User updateByPrimaryKey(User user) {
        getSqlSession().update("updateByPrimaryKey", user);
        return user;
    }
}