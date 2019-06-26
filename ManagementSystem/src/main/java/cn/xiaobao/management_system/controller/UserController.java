package cn.xiaobao.management_system.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.xiaobao.management_system.entity.User;
import cn.xiaobao.management_system.exception.PasswordErrorException;
import cn.xiaobao.management_system.exception.UsernameErrorException;
import cn.xiaobao.management_system.mapper.UserMapper;
import cn.xiaobao.management_system.service.IUserService;
import cn.xiaobao.management_system.util.JWTUtils;
import cn.xiaobao.management_system.util.JsonResult;

@RequestMapping("user")
@RestController
public class UserController {
	//service自动填装
	@Autowired
	private IUserService service;
	/**
	 * 该方法实现了用户登录逻辑,根据用户提供的用户名在数据库中进行查询,根据查询到的结果与用户提供的密码进行登录验证并
	 * 返回一个JsonResult对象,用来让客户端判断是否登录成功
	 * @param username
	 * @param password
	 * @return	JsonResult
	 */
	@RequestMapping("handle_login")
	public JsonResult<Void> handleLogin(String username,String password,HttpServletResponse response) {
		//创建一个JSON对象
		JsonResult<Void> jr = new JsonResult<Void>();
		//调用Service进行登录
		try {
			service.login(username, password);
			//登录成功,设置状态码为1
			jr.setState(1);
			//获得用户对应的token
			String token = JWTUtils.getToken(username);
			//给浏览器发送Cookie保存用户对应的token
			Cookie cookie = new Cookie("token", token);
			//设置cookie时间为1小时,路径为全应用通用
			cookie.setMaxAge(60*60*24);
			cookie.setPath("/");
			//发送cookie
			response.addCookie(cookie);
		} catch (UsernameErrorException e) {
			//用户名错误,设置状态码为0
			jr.setState(0);
			jr.setMessage("用户名不存在!");
		} catch (PasswordErrorException e) {
			//密码错误,设置状态码为-1
			jr.setState(-1);
			jr.setMessage("密码错误!");
		}
		//返回JsonResult对象
		return jr;
	}
}
