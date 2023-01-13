package com.tyut.covid.service;

import com.tyut.covid.bean.User;
import com.tyut.covid.service.ex.*;

/**
 * 处理用户数据的业务层接口
 */
public interface IUserService {
	
	/**
	 * 用户注册
	 * @param user 用户注册数据对象
	 * @throws UsernameDuplicateException 用户名冲突异常
	 * @throws InsertException 用户数据插入异常
	 */
	void reg(User user) throws UsernameDuplicateException, InsertException;
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 登陆成功的用户信息
	 * @throws UserNotFoundException 用户不存在异常
	 * @throws PasswordNotMatchException 密码错误异常
	 */
	User login(String username, String password) 
			throws UserNotFoundException, PasswordNotMatchException;
	
	/**
	 * 修改密码
	 * @param uid 用户 uid
	 * @param username 用户名
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @throws UserNotFoundException 用户不存在异常
	 * @throws PasswordNotMatchException 密码错误异常
	 * @throws UpdateException 更新操作异常
	 */
	void changePassword(Integer uid, String username, String oldPassword, String newPassword) 
			throws UserNotFoundException, PasswordNotMatchException, UpdateException;
	
	/**
	 * 根据用户 uid 查询用户信息
	 * @param uid 
	 * @return 查询到的用户信息
	 */
	User getByUid(Integer uid);
	
	/**
	 * 修改个人信息
	 * @param user 用户对象
	 * @throws UserNotFoundException 用户不存在异常
	 * @throws UpdateException 更新失败异常
	 */
	void changeInfo(User user) throws UserNotFoundException, UpdateException;
	
	/**
	 * 上传头像
	 * @param uid 用户 uid
	 * @param username 用户名
	 * @param avatar 头像路径
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeAvatar(Integer uid, String username, String avatar) 
			throws UserNotFoundException, UpdateException;
	
}
