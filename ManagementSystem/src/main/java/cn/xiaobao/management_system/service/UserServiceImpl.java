package cn.xiaobao.management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiaobao.management_system.entity.User;
import cn.xiaobao.management_system.exception.PasswordErrorException;
import cn.xiaobao.management_system.exception.UsernameErrorException;
import cn.xiaobao.management_system.mapper.UserMapper;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserMapper usermapper;
	@Override
	public User login(String username, String password) {
		//先根据用户名查询数据库返回一个结果Result
		User result = usermapper.findByUserName(username);
		//如果结果为空,则说明用户名不存在,抛出用户名错误异常
		if(result==null) {
			throw new UsernameErrorException();
		}else {
			//结果不为空,说明用户存在,再判断密码是否正确
			if(result.getUpassword().equals(password)) {
				//判断正确,登录成功,返回User对象
				return result;
			}else {
				//判断错误,登录失败,抛出密码错误异常
				throw new PasswordErrorException();
			}
		}

	}

}
