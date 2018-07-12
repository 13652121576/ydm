package com.ydm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydm.dao.UserMapper;
import com.ydm.pojo.User;
import com.ydm.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findUser() {
		User user = userMapper.findUser();
		return user;
	}

}
