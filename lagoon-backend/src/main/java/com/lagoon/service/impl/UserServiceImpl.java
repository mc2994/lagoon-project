package com.lagoon.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lagoon.dao.UserDao;
import com.lagoon.model.User;
import com.lagoon.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public Page<User> findAllUsers(Pageable pageable) {

		return userDao.findAll(pageable);
	}

	@Override
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
//		List<User> users = new ArrayList<>();
//		User a = new User();
//		a.setUserId(1l);
//		a.setCreated(new Date());
//		a.setFirstName("Mc Kinley");
//		a.setLastName("Tolentino");
//		a.setPassword("sdadsaddsa");
//		a.setUserName("sdadsaddsa");
//		
//		
//		User b = new User();
//		b.setFirstName("Mc Kinley");
//		b.setLastName("Tolentino");
//		b.setCreated(new Date());
//		b.setPassword("sdadsaddsa");
//		b.setUserName("sdadsaddsa");
//		b.setUserId(1l);
//		
//        users.add(a);
//        users.add(b);
//	 	return users;
	}

	@Override
	public User findOne() {
		return userDao.findByUserId(1L);
	}
}