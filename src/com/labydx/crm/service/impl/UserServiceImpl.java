package com.labydx.crm.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.labydx.crm.dao.UserDao;
import com.labydx.crm.domain.User;
import com.labydx.crm.service.UserService;
import com.labydx.crm.utils.MD5Utils;
/**
 * 用户管理的Service的实现类
 * @author jt
 *
 */
@Transactional
public class UserServiceImpl implements UserService {

	// 注入DAO:
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	// 业务层注册用户的方法
	public void regist(User user) {
		// 对密码进行加密处理：
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");
		// 调用DAO
		userDao.save(user);
	}

	@Override
	public User login(User user) {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		return userDao.login(user);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

}
