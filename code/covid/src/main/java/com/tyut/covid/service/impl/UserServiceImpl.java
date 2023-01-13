package com.tyut.covid.service.impl;

import com.tyut.covid.bean.User;
import com.tyut.covid.dao.UserDao;
import com.tyut.covid.service.IUserService;
import com.tyut.covid.service.ex.*;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * 处理用户数据的业务层接口的实现类
 *
 */
@Service
public class UserServiceImpl implements IUserService {
	
	@Resource
	private UserDao dao;
	
	/**
	 * 注册
	 */
	@Override
	public void reg(User user) throws UsernameDuplicateException, InsertException {
		// 根据 user 获取 username
		// 根据 username 查询用户数据是否已存在
		// 是 -- 抛出用户名冲突异常
		String username = user.getUsername();
		User result = dao.findByUsername(username);
		if(result != null) {
			throw new UsernameDuplicateException("注册失败！该用户名已被占用！");
		}

		// 得到盐值
		// 获取用户输入的密码，将密码加密
		// 将加密后的密码和盐值封装到 user 中
		String salt = UUID.randomUUID().toString();
		String md5Password = getMd5Password(user.getPassword(), salt);
		user.setPassword(md5Password);
		user.setSalt(salt);

		// 将 user 中的 isDelete 设置为 0
		user.setIsDelete(0);

		// 封装 user 中的 4 个日志属性
		Date now = new Date();
		user.setCreatedUser(username);
		user.setCreatedTime(now);
		user.setModifiedUser(username);
		user.setModifiedTime(now);

		// 执行注册
		Integer rows = dao.insert(user);
		if(rows != 1) {
			throw new InsertException("注册失败！出现未知错误，请联系管理员！");
		}
		
	}
	
	/**
	 * 登录
	 */
	@Override
	public User login(String username, String password) 
			throws UserNotFoundException, PasswordNotMatchException {
		// 根据 username 查询该 username 是否存在
		// 不存在 -- 抛出用户不存在异常
		// 存在 -- 判断是否被标记为已删除
		// 是 -- 抛出用户不存在异常
		User result = dao.findByUsername(username);
		if(result == null) {
			throw new UserNotFoundException("登陆失败！用户名不存在！");
		}
		if(result.getIsDelete() == 1) {
			throw new UserNotFoundException("登陆失败！用户名不存在！");
		}
		
		// 根据查询结果获取数据库中的密码和盐值
		// 将 password 和盐值进行加密得到 md5Password
		// 判断 md5Password 与 数据库中的密码是否一致
		// 不一致 -- 抛出密码错误异常
		String mysqlPassword = result.getPassword();
		String salt = result.getSalt();
		String md5Password = getMd5Password(password, salt);
		if(!md5Password.equals(mysqlPassword)) {
			throw new PasswordNotMatchException("登陆失败！密码错误！");
		}
		
		// 将查询结果中的password、salt、isDelete设置为null
		result.setPassword(null);
		result.setSalt(null);
		result.setIsDelete(null);
		
		// 返回查询结果
		return result;
	}
	
	/**
	 * 修改密码
	 */
	@Override
	public void changePassword(Integer uid, String username, String oldPassword, String newPassword)
			throws UserNotFoundException, PasswordNotMatchException, UpdateException {
		// 根据 uid 查询用户数据
		// 判断查询结果过是否为空
		// 是 -- 抛出用户不存在异常
		// 判断是否被标记为已删除
		// 是 -- 抛出用户不存在异常
		User result = dao.findByUid(uid);
		if(result == null) {
			throw new UserNotFoundException("修改密码失败！用户不存在！");
		}
		if(result.getIsDelete() == 1) {
			throw new UserNotFoundException("修改密码失败！用户不存在！");
		}
		
		// 从查询结果中获取数据库中的密码和盐值
		// 将用户输入的旧密码与盐值进行加密得到md5OldPassword
		// 比较数据库中的密码与md5OldPassword
		// 不一致 -- 抛出密码错误异常
		// 将用户输入的新密码与盐值进行加密得到md5NewPassword
		String mysqlPassword = result.getPassword();
		String salt = result.getSalt();
		String md5OldPassword = getMd5Password(oldPassword, salt);
		if(!mysqlPassword.equals(md5OldPassword)) {
			throw new PasswordNotMatchException("修改密码失败！原密码错误！");
		}
		String md5NewPassword = getMd5Password(newPassword, salt);
		
		// 获取当前时间对象
		Date now = new Date();
		
		// 执行修改密码操作
		Integer rows = dao.updatePassword(uid, md5NewPassword, username, now);
		if(rows != 1) {
			throw new UpdateException("修改密码失败！出现未知错误，请联系管理员！");
		}
	}
	
	/**
	 * 根据 uid 查询用户数据
	 */
	@Override
	public User getByUid(Integer uid) {
		// 根据 uid 查询数据
		// 将查询结果中的 password、salt、isDelete 设为 null
		// 返回查询结果
		User result = dao.findByUid(uid);
		if(result != null) {
			result.setPassword(null);
			result.setSalt(null);
			result.setIsDelete(null);
		}
		return result;
	}
	
	/**
	 * 修改用户信息
	 */
	@Override
	public void changeInfo(User user) throws UserNotFoundException, UpdateException {
		// 根据 user 获取 uid 查询数据
		// 判断查询结果是否为空
		// 是 -- 抛出用户不存在异常
		// 判断查询结果是否被标记为已删除
		// 是 -- 抛出用户不存在异常
		Integer uid = user.getUid();
		User result = dao.findByUid(uid);
		if(result == null) {
			throw new UserNotFoundException("修改个人信息失败！用户不存在！");
		}
		if(result.getIsDelete() == 1) {
			throw new UserNotFoundException("修改个人信息失败！用户不存在！");
		}
		
		// 创建当前时间对象
		// 将时间对象和修改人封装到 user
		// 执行修改操作
		// 判断受影响行数是否为1
		// 否 -- 抛出更新失败异常
		Date now = new Date();
		user.setModifiedTime(now);
		user.setModifiedUser(user.getUsername());
		Integer rows = dao.updateInfo(user);
		if(rows != 1) {
			throw new UpdateException("修改个人信息失败！出现未知错误，请联系系统管理员！");
		}
	}

	/**
	 * 上传头像
	 */
	@Override
	public void changeAvatar(Integer uid, String username, String avatar)
			throws UserNotFoundException, UpdateException {
		// 根据 uid 查询用户数据
		// 判断查询结果是否为空
		// 是 -- 抛出用户不存在异常
		// 判断查询结果是否被标记为已删除
		// 是 -- 抛出用户不存在异常
		User result = dao.findByUid(uid);
		if(result == null) {
			throw new UserNotFoundException("上传头像失败！用户不存在！");
		}
		if(result.getIsDelete() == 1) {
			throw new UserNotFoundException("上传头像失败！用户不存在！");
		}
		
		// 创建当前事件对象
		// 执行上传头像
		// 判断受影响行数是否不为 1
		// 是 -- 抛出上传失败异常
		Date now = new Date();
		Integer rows = dao.updateAvatar(uid, avatar, username, now);
		if(rows != 1) {
			throw new UpdateException("上传头像失败！出现未知错误，请联系管理员！");
		}
	}
	
	/**
	 * 对密码进行加密
	 * @param password 原始密码
	 * @param salt 盐值
	 * @return 加密后的密码
	 */
	String getMd5Password(String password, String salt) {
		// 规则：对原始密码 3 重加密
		String str = password + salt;
		for (int i = 0; i < 3; i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes());
		}
		return str;
	}

	
}
