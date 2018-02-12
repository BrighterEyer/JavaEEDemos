package org.bsframe.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.bsframe.entity.User;

public interface UserMapper {

	/**
	 * 添加新用户
	 * 
	 * @param user
	 * @return
	 */
	// 使用UserMapper.xml文件配置sql语句
	public User findByUsername(String username);

	@Insert("insert into user(username, password, nickname) values(#{username}, #{password}, #{nickname})")
	public int addUser(User user);

	@Select("select r.name from user u,role r,user_role ur where u.id = ur.userid and r.id = ur.roleid and u.username = #{username}")
	public Set<String> findUserRoles(String username);

	@Select("select p.url from user u, role r, permission p, user_role ur, role_permission rp where u.username= #{username} and u.id=ur.userid and r.id=ur.roleid and r.id=rp.roleid and p.id=rp.permissionid")
	public Set<String> findUserPermissions(String username);

	@Select("select * from user where nickname = #{nickname}")
	public User findByNickname(String nickname);

	@Select("select * from user where id = #{id}")
	public User findUserById(Integer id);

}