package aiyunnet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import aiyunnet.entity.*;

public interface AdminDao
{
	//WebMenu Module
	int addWebMenu(WebMenu webMenu);

	WebMenu getWebMenu(int id);
	
	int updateWebMenu();
	
	int deleteWebMenu(int id);
	
	//WebNews Module	
	int addWebNews(WebNews webNews);

	int updateWebNews(WebNews webNews);

	WebNews getWebNewsById(Integer id);

	List<WebNews> getWebNewsList(int menuId);

	int deleteWebNews(int id);

	int deleteWebNewsByIds(@Param("ids") String[] ids);
	
	List<WebNews> searchWebNews(@Param("title") String title, @Param("keywords") String keywords,
			@Param("author") String author, @Param("content") String content, @Param("menuId") Integer menuId);

	//WebFile Module
	int addWebFile(WebFile webFile);
	
	int addWebFileOutId(@Param("outId")int outId, @Param("id")int id);
	
	int updateWebFile(WebFile webFile);

	WebFile getWebFileById(int id);

	List<WebFile> getWebFilesByOutId(int outId);

	int deleteWebFiles(int outId);

	int deleteWebFile(int id);

	//SysRole module
	int addSysRole(SysRole sysRole);
	
	int deleteSysRole(int id);
	
	int addSysRoleActionNode();
	
	List<SysRole> getSysRoles();
	
	SysRole getSysRole(int id);
	
	int addSysRoleUsers(@Param("userId")int userId,@Param("roleIds")String[] roleIds);
	
	int deleteSysRoleUser(int userId);
	
	List<SysRole> getSysRolesByUserId(int userId);
}
