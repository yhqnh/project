package com.project.dal;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.ShardedJedisPool;

import com.github.pagehelper.PageHelper;
import com.project.dao.UserDao;
import com.project.domain.User;

@Slf4j
public class UserDaoTest extends AbstractTest {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ShardedJedisPool shardedJedisPool;

	@Test
	public void test() throws InterruptedException {

//		for (int i = 0; i < 100000; i++) {
//			User insertUser = new User();
//			insertUser.setLoginName("test");
//			userDao.insert(insertUser);
//		}
		

		PageHelper.startPage(1, 5);
		List<User> testList1 = userDao.selectAll();
//		List<User> testList2 = userDao.selectAll();

//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				User user = userDao.selectByPrimaryKey(2L);
//				log.info(user.getLoginName());
//			}
//		}).start();
//		
//		
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				User user = userDao.selectByPrimaryKey(2L);
//				log.info(user.getLoginName());
//			}
//		}).start();
		
		User user = userDao.selectByPrimaryKey(2L);
		log.info(user.getLoginName());
	}

}
