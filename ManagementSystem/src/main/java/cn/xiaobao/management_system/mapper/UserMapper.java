package cn.xiaobao.management_system.mapper;

import java.util.List;

import cn.xiaobao.management_system.entity.User;

public interface UserMapper {
	List<User> findAllUser();
	User findByUserName(String username);
}
