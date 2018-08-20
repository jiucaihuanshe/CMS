package aiyunnet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import aiyunnet.dao.SysUserDao;
import aiyunnet.dao.AdminDao;
import aiyunnet.entity.*;
import aiyunnet.service.AdminService;

@Service("adminServiceImpl")
public class AdminServiceImpl implements AdminService
{

	@Autowired
	@Qualifier("sysUserDao")
	private SysUserDao sysUserDao;

	@Autowired
	@Qualifier("adminDao")
	private AdminDao adminDao;

	@Override
	public int addWebMenu(WebMenu webMenu)
	{
		int n = adminDao.addWebMenu(webMenu);
		return n;
	}

	@Override
	public int addWebNews(WebNews webNews)
	{
		int n = adminDao.addWebNews(webNews);
		addWebNewsFiles(webNews.getFileIds(), webNews.getId());
		return n;
	}

	@Override
	public int updateWebNews(WebNews webNews)
	{
		int n = adminDao.updateWebNews(webNews);
		adminDao.deleteWebFiles(webNews.getId());
		addWebNewsFiles(webNews.getFileIds(), webNews.getId());
		return n;
	}

	private void addWebNewsFiles(String fileIds, int newsId)
	{
		if (fileIds != null && fileIds.length() != 0)
		{
			String[] fileIdArr = fileIds.split(",");
			for (String fileId : fileIdArr)
			{
				adminDao.addWebFileOutId(newsId, Integer.parseInt(fileId));
			}
		}
	}

	@Override
	public WebNews getWebNewsById(int id)
	{
		WebNews webNews = adminDao.getWebNewsById(id);
		List<WebFile> files = adminDao.getWebFilesByOutId(id);
		String fileIds = "";
		for (WebFile file : files)
		{
			fileIds += "," + file.getId();
		}
		if (fileIds.length() != 0)
		{
			fileIds = fileIds.substring(1);
		}
		webNews.setFileIds(fileIds);
		return webNews;
	}

	@Override
	public List<WebNews> getWebNewsList(int menuId)
	{
		List<WebNews> webNewsList = adminDao.getWebNewsList(menuId);
		for (WebNews news : webNewsList)
		{
			news.setFiles(adminDao.getWebFilesByOutId(news.getId()));
		}
		return webNewsList;
	}

	@Override
	public int deleteWebNews(int id)
	{
		int n = adminDao.deleteWebNews(id);
		return n;
	}

	public int deleteWebNewsByIds(String ids)
	{
		String[] idArr = ids.split(",");
		int n = adminDao.deleteWebNewsByIds(idArr);
		if (n > 0)
		{
			for (String outId : idArr)
			{
				adminDao.deleteWebFiles(Integer.valueOf(outId));
			}
		}

		return n;
	}

	@Override
	public int addWebFile(WebFile webFile)
	{
		int n = adminDao.addWebFile(webFile);
		int fileId = 0;
		if (n > 0)
		{
			fileId = webFile.getId();
		}
		return fileId;
	}

	@Override
	public WebFile getWebFileById(int id)
	{
		WebFile webFile = adminDao.getWebFileById(id);
		return webFile;
	}

	@Override
	public int deleteWebFile(int id)
	{
		int n = adminDao.deleteWebFile(id);
		return n;
	}

	@Override
	public List<WebNews> searchWebNews(String title, String keywords, String author, String content, Integer menuId)
	{
		List<WebNews> results = adminDao.searchWebNews(title, keywords, author, content, menuId);
		return results;
	}

	@Override
	public WebMenu getWebMenu(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateWebMenu()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteWebMenu(int id)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addSysRole(SysRole sysRole)
	{
		int n = adminDao.addSysRole(sysRole);
		return n;
	}

	@Override
	public int deleteSysRole(int id)
	{
		int n = adminDao.deleteSysRole(id);
		return n;
	}

	@Override
	public int addSysRoleActionNode()
	{

		return 0;
	}

	@Override
	public List<SysRole> getSysRoles()
	{
		List<SysRole> roles = adminDao.getSysRoles();
		return roles;
	}

	@Override
	public SysRole getSysRole(int id)
	{
		SysRole role = adminDao.getSysRole(id);
		return role;
	}

	@Override
	public List<SysRole> getSysRolesByUserId(int userId)
	{
		List<SysRole> roles = adminDao.getSysRolesByUserId(userId);
		return roles;
	}

	@Override
	public int deleteSysUser(int id)
	{
		sysUserDao.deleteSysUser(id);
		adminDao.deleteSysRoleUser(id);
		return 1;
	}
}
