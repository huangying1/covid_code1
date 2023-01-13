package com.tyut.covid.dao;


import com.tyut.covid.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 处理用户数据的持久层接口
 *
 */
@Mapper
public interface UserDao {

	/**
	 * 插入用户数据
	 * @param user 要插入的用户数据
	 * @return 受影响的行数
	 */
	Integer insert(User user);

	/**
	 * 根据用户名查询用户数据
	 * @param username 要查询的用户名
	 * @return 查询到的用户数据
	 */
	User findByUsername(String username);

	/**
	 * 修改密码
	 * @param uid 用户uid
	 * @param password 密码
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改时间
	 * @return
	 */
	Integer updatePassword(
            @Param("uid") Integer uid,
            @Param("password") String password,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

	/**
	 * 根据用户 uid 查询用户数据
	 * @param uid 用户 uid
	 * @return 查询到的用户数据
	 */
	User findByUid(Integer uid);

	/**
	 * 修改个人信息
	 * @param user 用户对象
	 * @return 受影响的行数
	 */
	Integer updateInfo(User user);

	/**
	 * 上传头像
	 * @param uid 用户 uid
	 * @param avatar 头像路径
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return 受影响的行数
	 */
	Integer updateAvatar(
            @Param("uid") Integer uid,
            @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
	
}
