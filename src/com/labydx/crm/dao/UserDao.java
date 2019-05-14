package com.labydx.crm.dao;

import com.labydx.crm.domain.User;

/**
 * 用户管理的DAO的接口
 * @author jt
 *
 */
public interface UserDao extends BaseDao<User>{

	User login(User user);

}
