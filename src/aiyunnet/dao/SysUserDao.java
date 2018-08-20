package aiyunnet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import aiyunnet.entity.SysUser;

public interface SysUserDao
{
	int addSysUser(SysUser sysUser);

	List<SysUser> getSysUserList();

	SysUser getSysUserById(int id);

	int updateSysUser(SysUser sysUser);

	int changeSysUserPassword(@Param("newpwd")String newPassword,@Param("salt")String salt, @Param("id")int id);

	SysUser getLoginUser(@Param("account") String account, @Param("password") String password);
	
	int deleteSysUser(int id);
	
	String getSalt(String username);
	
	SysUser getSysUserByName(String username);
}
