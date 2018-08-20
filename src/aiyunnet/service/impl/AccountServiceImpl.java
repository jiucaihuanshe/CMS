package aiyunnet.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import aiyunnet.dao.*;
import aiyunnet.entity.SysUser;
import aiyunnet.service.AccountService;
import aiyunnet.utils.ContextHelper;

@Service("accountServiceImpl")
public class AccountServiceImpl implements AccountService
{
	@Autowired
	@Qualifier("sysUserDao")
	private SysUserDao sysUserDao;

	@Autowired
	@Qualifier("adminDao")
	private AdminDao adminDao;

	@Override
	public int login(String username, String password)
	{
		int retCode = 0;

		if (StringUtils.isEmpty(username))
		{
			retCode = 1;//用户名为空
			return retCode;
		}
		if (StringUtils.isEmpty(password))
		{
			retCode = 2;//密码为空
			return retCode;
		}

		try
		{
			String salt = sysUserDao.getSalt(username);
			SimpleHash sHash = new SimpleHash("MD5", password, salt);
			String enryPwd = sHash.toString();
			// 获取subject主体对象
			// 封装用户名和密码
			// 执行身份认证
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, enryPwd);
			subject.login(token);
			// 此请求会提交给SecurityManager
			// SecurityManager会调用认证处理器Authenticator
			// 认证处理器会去访问相关Realm对象获取认证信息
		} catch (UnknownAccountException e)
		{
			retCode = 3;//用户名不正确
			return retCode;
		} catch (IncorrectCredentialsException e)
		{
			retCode = 4;//密码不正确
			return retCode;
		}

		// 认证成功，获取用户相关信息，存入session中
		SysUser sysUser = sysUserDao.getSysUserByName(username);

		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute(ContextHelper.STAFFID, sysUser.getId());
		session.setAttribute(ContextHelper.STAFFNAME, sysUser.getAccount());
		return retCode;
	}

	@Override
	public SysUser getLoginUser(String account, String password)
	{
		SysUser user = sysUserDao.getLoginUser(account, password);
		return user;
	}

	@Override
	public SysUser getSysUser(int id)
	{
		SysUser user = sysUserDao.getSysUserById(id);
		return user;
	}

	@Override
	public List<SysUser> getSysUserList()
	{
		List<SysUser> usList = sysUserDao.getSysUserList();
		return usList;
	}

	@Transactional
	@Override
	public int addSysUser(SysUser sysUser, String roleIds)
	{
		String salt = UUID.randomUUID().toString();
		String pwd = sysUser.getPassword();

		SimpleHash sHash = new SimpleHash("MD5", pwd, salt);
		String newPwd = sHash.toString();
		sysUser.setSalt(salt);
		sysUser.setPassword(newPwd);
		
		int n = sysUserDao.addSysUser(sysUser);
		int userId = sysUser.getId();
		if (roleIds != null && roleIds.length() > 0)
		{
			adminDao.addSysRoleUsers(userId, roleIds.split(","));
		}

		//当在方法上加上@Transactional注解来声明事务，此方法执行的sql操作要在一个事务中执行
		//如果方法中任意一处有异常，那么此事务自动回滚不提交数据库中，避免产生脏数据
		//Integer.parseInt("");
		
		return n;
	}

	@Transactional
	@Override
	public int updateSysUser(SysUser sysUser, String roleIds)
	{
		int n = sysUserDao.updateSysUser(sysUser);
		adminDao.deleteSysRoleUser(sysUser.getId());
		if (roleIds != null && roleIds.length() > 0)
		{
			adminDao.addSysRoleUsers(sysUser.getId(), roleIds.split(","));
		}
		return n;
	}

	@Override
	public int changeUserPassword(String newPassword, int id)
	{
		String salt = UUID.randomUUID().toString();
		SimpleHash sHash = new SimpleHash("MD5", newPassword, salt);
		String newPwd = sHash.toString();
		int n = sysUserDao.changeSysUserPassword(newPwd, salt, id);
		return n;
	}

}
