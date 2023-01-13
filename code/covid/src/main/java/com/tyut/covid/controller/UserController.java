package com.tyut.covid.controller;

import com.tyut.covid.bean.User;
import com.tyut.covid.controller.ex.FileEmptyException;
import com.tyut.covid.controller.ex.FileSizeException;
import com.tyut.covid.controller.ex.FileTypeException;
import com.tyut.covid.service.IUserService;
import com.tyut.covid.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 处理用户数据的控制器类
 * @author hls
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController{

	@Autowired
	private IUserService service;
	
	/**
	 * 用户注册
	 * @param user 注册的用户数据
	 * @return
	 */
	@RequestMapping("reg") //  /users/reg
		public JsonResult<Void> reg(User user){
			// 执行注册，失败时会抛异常，异常会在 BaseController 中进行统一处理
			// 此方法只处理返回成功的情况
			service.reg(user);
		// 返回成功
		return new JsonResult<Void>(SUCCESS);
	}
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @param session 登陆成功后用于存储用户信息
	 * @return
	 */
	@RequestMapping("login")  // //users/login
	public JsonResult<User> login(String username, String password, HttpSession session){
		// 执行登录，获取登录返回结果
		User user = service.login(username, password);
		// 向 session 中封装数据
		session.setAttribute("uid", user.getUid());
		session.setAttribute("username", user.getUsername());
		// 向客户端响应操作成功
		return new JsonResult<User>(SUCCESS, user);
	}
	
	/**
	 * 修改密码
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @param session 用于获取用户 uid 和 username
	 * @return
	 */
	@RequestMapping("change_password")
	public JsonResult<Void> changePassword(
			@RequestParam("old_password") String oldPassword,
			@RequestParam("new_password") String newPassword,
			HttpSession session){
		// 从 session 中获取 uid 和 username
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		// 执行修改密码
		service.changePassword(uid, username, oldPassword, newPassword);
		// 响应修改成功
		return new JsonResult<Void>(SUCCESS);
	}
	
	/**
	 * 根据 uid 获取用户信息
	 * @param session 通过 session 获取 uid
	 * @return 查询到的用户信息
	 */
	@GetMapping("get_info")
	public JsonResult<User> getInfo(HttpSession session){
		// 从 session 中获取 uid
		Integer uid = getUidFromSession(session);
		// 查询匹配的数据
		User result = service.getByUid(uid);
		// 返回查询到的数据
		return new JsonResult<User>(SUCCESS,result);
	}
	
	/**
	 * 修改个人信息
	 * @param user 用户数据对象
	 * @param session 用于获取用户 uid、username
	 * @return
	 */
	@RequestMapping("change_info")
	public JsonResult<Void> changeInfo(User user, HttpSession session){
		// 根据 session 获取 uid、username
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		// 将 uid 和 username 封装到 user 中
		user.setUid(uid);
		user.setUsername(username);
		// 执行修改
		service.changeInfo(user);
		// 响应
		return new JsonResult<Void>(SUCCESS);
	}
	
	/**
	 * 上传的头像大小
	 */
	public static final long AVATAR_MAX_SIZE = 2 * 1024 * 1024;
	
	/**
	 * 头像保存的文件夹
	 */
	public static final String AVATAR_DIR = "upload";
	
	/**
	 * 上传时允许的头像文件类型
	 */
	public static final List<String> AVATAR_CONTENT_TYPES = new ArrayList<String>();
	
	/**
	 * 初始化上传时允许的文件类型
	 */
	static {
		AVATAR_CONTENT_TYPES.add("image/jpeg");
		AVATAR_CONTENT_TYPES.add("image/png");
	}
	
	/**
	 * 上传头像
	 * @param file 文件操作相关参数
	 * @param request 用于确定上传的文件夹
	 * @return
	 */
	@PostMapping("change_avatar")
	public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file,
			HttpServletRequest request){
		// 判断上传文件是否为空
		if(file.isEmpty()) {
			throw new FileEmptyException("上传失败！上传文件为空！");
		}
		
		// 检查上传文件大小
		if(file.getSize() > AVATAR_MAX_SIZE) {
			throw new FileSizeException("上传失败！仅允许上传" + (AVATAR_MAX_SIZE / 1024) + "KB以内的文件！");
		}
		
		// 检查文件类型
		if(!AVATAR_CONTENT_TYPES.contains(file.getContentType())) {
			throw new FileTypeException("上传失败！仅允许上传以下类型的文件：" + AVATAR_CONTENT_TYPES);
		}
		
		
		// 确定上传的文件夹
		String dirPath = request.getServletContext().getRealPath(AVATAR_DIR);
		File dir = new File(dirPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		// 确定上传的文件名
		// 获取原文件名
		String originalFilename = file.getOriginalFilename();
		String suffix = "";
		Integer beginIndex = originalFilename.lastIndexOf(".");
		if(beginIndex != -1) {
			suffix = originalFilename.substring(beginIndex);
		}
		String filename = UUID.randomUUID().toString() + suffix;
		
		// 执行上传
		File dest = new File(dir, filename);
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 更新数据表
		String avatar = "/upload/" + filename;
		HttpSession session = request.getSession();
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		service.changeAvatar(uid, username, avatar);
		
		// 返回
		// JsonResult<String> jr = new JsonResult<String>();
		// jr.setState(SUCCESS);
		// jr.setData(avatar);
		// return jr;
		
		return new JsonResult<String>(SUCCESS, avatar);
		
	}
	
}
