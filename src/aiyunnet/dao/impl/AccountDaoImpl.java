package aiyunnet.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import aiyunnet.dao.SysUserDao;
import aiyunnet.entity.SysUser;

/**
 *spring mvc项目中不推荐使用这种实现dao接口的方式。
 *
 */
//@Repository
public class AccountDaoImpl 
//implements AccountDao
{
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	//@Override
	public int addSysUser(SysUser sysUser)
	{
		int n = sqlSessionTemplate.insert("aiyunnet.entity.DomainMapper.insertSysUser", sysUser);
		return n;
	}

	//@Override
	public List<SysUser> getSysUserList()
	{
		List<SysUser> userList = sqlSessionTemplate.selectList("aiyunnet.entity.DomainMapper.getSysUserList");
		return userList;
	}

	//@Override
	public SysUser getSysUserById(int id)
	{
		SysUser user = (SysUser) sqlSessionTemplate.selectOne("aiyunnet.entity.DomainMapper.getSysUserById", id);
		return user;
	}

	//@Override
	public int updateSysUser(SysUser sysUser)
	{
		int n = sqlSessionTemplate.update("aiyunnet.entity.DomainMapper.updateSysUser", sysUser);
		return n;
	}

	//@Override
	public int changeSysUserPassword(String newPassword, int id)
	{
		String[] params = { newPassword, Integer.toString(id) };
		int n = sqlSessionTemplate.update("aiyunnet.entity.DomainMapper.changeSysUserPassword", params);
		return n;
	}

	//@Override
	public SysUser getLoginUser(String account, String password)
	{		
		//DomainMapper mapper = sqlSessionTemplate.getMapper(DomainMapper.class);
		//SysUser user = mapper.getLoginUser(account,password);
		//String[] params = { account, password };
		// SysUser user =
		// sqlSessionTemplate.selectOne("aiyunnet.entity.DomainMapper.getLoginUser",
		// params);
		return null;
	}

}
