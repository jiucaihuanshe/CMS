package aiyunnet.service;

import java.util.List;

import aiyunnet.entity.*;

public interface AdminService
{
	//WebMenu
	int addWebMenu(WebMenu webMenu);
	
	WebMenu getWebMenu(int id);
	
	int updateWebMenu();
	
	int deleteWebMenu(int id);
	
	//WebNews
	int addWebNews(WebNews webNews);

	int updateWebNews(WebNews webNews);

	int deleteWebNews(int id);
	
	int deleteWebNewsByIds(String ids);

	WebNews getWebNewsById(int id);

	List<WebNews> getWebNewsList(int menuId);
	
	List<WebNews> searchWebNews(String title,String keywords,String author,String content,Integer menuId);
	
	//WebFiles
	int addWebFile(WebFile webFile);
	
	WebFile getWebFileById(int id);
	
	int deleteWebFile(int id);
	
	//SysRole
	int addSysRole(SysRole sysRole);
	
	int deleteSysRole(int id);
	
	int addSysRoleActionNode();
	
	List<SysRole> getSysRoles();
	
	SysRole getSysRole(int id);
	
	List<SysRole> getSysRolesByUserId(int userId);
	
	int deleteSysUser(int id);
}
