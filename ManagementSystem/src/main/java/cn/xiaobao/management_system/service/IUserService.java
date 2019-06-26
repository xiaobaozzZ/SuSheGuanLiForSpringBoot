package cn.xiaobao.management_system.service;

import cn.xiaobao.management_system.entity.User;


public interface IUserService {
	User login(String username,String password);
}
