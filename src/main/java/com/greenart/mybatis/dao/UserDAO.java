package com.greenart.mybatis.dao;

import java.util.List;

import com.greenart.mybatis.model.User;

public interface UserDAO {

	int insertUser(User user);

	int updateUser(User user);

	int deleteUser(String id);

	User selectUser(String id);

	//��ü ȸ�� ��ȸ
	List<User> selectUserAll();

}