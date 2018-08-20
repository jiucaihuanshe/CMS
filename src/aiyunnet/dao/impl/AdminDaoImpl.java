package aiyunnet.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import aiyunnet.dao.AdminDao;
import aiyunnet.entity.WebFile;
import aiyunnet.entity.WebMenu;
import aiyunnet.entity.WebNews;

/**
 *不用手动实现dao层接口，借助spring mybatis，配合dao层接口扫描路径和mapper路径。
 *spring自动完成dao接口的实现类并实例化对象，自动注入到service实现类中的dao属性中。
 *简化代码，因为dao层的实现步骤一样，就是调用mybatis的工程类获取session，开启session，然后找到mapper文件中定义的
 *sql方法，执行增删改查之后，关闭session。
 *
 */
//@Repository
public class AdminDaoImpl 
//implements AdminDao
{
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	//@Override
	public int addWebNews(WebNews webNews)
	{
		int n = sqlSessionTemplate.insert("aiyunnet.entity.DomainMapper.insertWebNews", webNews);
		return n;
	}

	//@Override
	public int updateWebNews(WebNews webNews)
	{
		int n = sqlSessionTemplate.update("aiyunnet.entity.DomainMapper.updateWebNews", webNews);
		return n;
	}

	/*@Override
	public WebNews getWebNewsById(Integer id)
	{
		WebNews webNews = sqlSessionTemplate.selectOne("aiyunnet.entity.DomainMapper.getNewsById", id);
		return webNews;
	}
*/
	//@Override
	public List<WebNews> getWebNewsList(int menuId)
	{
		List<WebNews> webNewsList = sqlSessionTemplate.selectList("aiyunnet.entity.DomainMapper.getNewsList", menuId);
		return webNewsList;
	}

	//@Override
	public int deleteWebNews(int id)
	{
		int n = sqlSessionTemplate.delete("aiyunnet.entity.DomainMapper.deleteWebNews", id);
		return n;
	}

	//@Override
	public int addWebMenu(WebMenu webMenu)
	{
		int n = sqlSessionTemplate.insert("aiyunnet.entity.DomainMapper.insertWebMenu", webMenu);
		return n;
	}

	//@Override
	public WebMenu getWebMenu(int id)
	{
		WebMenu webMenu = sqlSessionTemplate.selectOne("aiyunnet.entity.DomainMapper.getWebMenuById", id);
		return webMenu;
	}

	//@Override
	public int addWebFile(WebFile webFile)
	{
		int n = sqlSessionTemplate.insert("aiyunnet.entity.DomainMapper.insertWebFile", webFile);
		if (n > 0)
		{
			return webFile.getId();
		}
		return 0;
	}

	//@Override
	public int addWebFileOutId(int outId, int id)
	{
		//int[] params = { outId, id };
		//int n = sqlSessionTemplate.update("aiyunnet.entity.DomainMapper.insertOutId", params);
		//int n =mapper.addWebFileOutId(outId, id);
		return 0;
	}

	//@Override
	public int updateWebFile(WebFile webFile)
	{
		//int n = mapper.updateWebFile(webFile);
		return 0;
	}

	//@Override
	public WebFile getWebFileById(int id)
	{
		//WebFile webFile = mapper.getWebFileById(id);
		return null;
	}

	//@Override
	public List<WebFile> getWebFilesByOutId(int outId)
	{
		// List<WebFile> webFileList =
		// sqlSessionTemplate.selectList("aiyunnet.entity.DomainMapper.getWebFilesByOutId",
		// outId);
		//List<WebFile> webFileList = mapper.getWebFilesByOutId(outId);
		return null;
	}

	//@Override
	public int deleteWebFiles(int outId)
	{
		// int n =
		// sqlSessionTemplate.delete("aiyunnet.entity.DomainMapper.deleteWebFiles",
		// outId);
		//int n = mapper.deleteWebFiles(outId);
		return 0;
	}

	//@Override
	public int deleteWebFile(int id)
	{
		// DomainMapper mapper = sqlSessionTemplate.getMapper(DomainMapper.class);
		//int n = mapper.deleteWebFile(id);
		return 0;
	}

	//@Override
	public List<WebNews> searchWebNews(String title, String keywords, String author, String content, Integer menuId)
	{
		// DomainMapper mapper = sqlSessionTemplate.getMapper(DomainMapper.class);
		//List<WebNews> webNewsList = mapper.searchWebNews(title, keywords, author, content, menuId);
		return null;
	}

	//@Override
	public WebNews getWebNewsById(Integer id)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
