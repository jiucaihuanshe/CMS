package aiyunnet.service;

import java.util.List;

import aiyunnet.entity.SysUser;

public interface AccountService
{	
	int login(String account, String password);	
	
	SysUser getLoginUser(String account, String password);

	SysUser getSysUser(int id);

	List<SysUser> getSysUserList();

	int addSysUser(SysUser sysUser,String roleIds);

	int updateSysUser(SysUser sysUser,String roleIds);

	int changeUserPassword(String newPassword, int id);
}
