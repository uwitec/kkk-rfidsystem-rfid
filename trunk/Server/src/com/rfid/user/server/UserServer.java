package com.rfid.user.server;

import java.util.List;

import com.rfid.user.vo.UserDetailVo;
import com.rfid.user.vo.UsersVo;

public interface UserServer {
	
	/**
	 * 是否能够登录
	 * @param loginName 用户名
	 * @param passWord 用户密码
	 * @return boolean
	 */
	public boolean loginAble(String loginName,String passWord);
	
	/**
	 * 通过密码和用户名取得用户信息
	 * @param loginName 用户名
	 * @param passWord 用户密码
	 * @return 用户值对象
	 * @throws Exception 
	 */
	public UsersVo getUsersByLoginNamePassWord(String loginName,String passWord) throws Exception;
	public UserDetailVo getUserDetailByLoginNamePassWord(String loginName,String passWord) throws Exception;;
	
	/**
	 * 通过用户编号userId获得用户信息
	 * @param userId
	 * @return 用户值对象
	 */
	public UsersVo getUsersByUserId(Long userId);
	public UserDetailVo getUserDetailByUserId(Long userId);
	
	/**
	 * 注册用户
	 * @param userVo 用户值对象
	 * @return 报错信息或者其他信息
	 * @throws Exception 
	 */
	public boolean regUsers(UsersVo userVo) throws Exception;
	public boolean regUsersDetail(UserDetailVo userDetailVo) throws Exception;
	
	/**
	 * 判断是否存在此用户名
	 * @param loginName 用户名
	 * @return 存在与否.存在返回true 不存在返回false
	 */
	public boolean hasUserByLoginName(String loginName);
	
	
	/**
	 * 获取所有的用户
	 * @return 返回用户集合
	 */
	public List<UsersVo> getAllUsers();
	public List<UserDetailVo> getAllUserDetail();
	
}
