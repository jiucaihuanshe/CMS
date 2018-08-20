package aiyunnet.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;

import aiyunnet.dao.SysUserDao;
import aiyunnet.entity.SysUser;

public class SysUserRealm extends AuthorizingRealm
{
	@Autowired
	@Qualifier("sysUserDao")
	private SysUserDao sysUserDao;

	/***
	 * 完成授权信息的获取以及封装. 登陆用户拥有的权限，包含哪些action node， 此方法何时调用?(执行授权检测时调用)
	 * 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		// Set<String> roleNmae=new HashSet<>();
		// Set<String> permissions=new HashSet<>();
		// 获取登陆用户身份信息
		// int userId=(int)principals.getPrimaryPrincipal();
		// 查找用户权限，或是角色信息, actionnode
		//List<String> permissionList = new ArrayList<>();
		// List<String> permissionList=accountDao.findUserPermission(userId);

		//Set<String> permissions = new HashSet<>();
//		for (String permission : permissionList)
//		{
//			if (StringUtils.isEmpty(permission))
//			{
//				permissions.add(permission);
//			}
//		}
		// 对权限信息进行封装
		//SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// info.setStringPermissions(permissions);
		
		//String userName = (String) principals.getPrimaryPrincipal();
        //List<String> permissionList=new ArrayList<String>();
       //此用户没有user:add权限，在userAddcontroller中加上注解，当访问的时候，会跳转没有权限页面
        //permissionList.add("user:add"); 
        
        //permissionList.add("sysUser:add");       
        //permissionList.add("sysUser:query");       
        //SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //info.addStringPermissions(permissionList);
        //info.addRole("admin");		
		
		return null;
	}

	/**
	 * 完成认证信息的获取以及封装 此方法何时调用?(执行登陆认证时调用)
	 * @param 用于接收用户身份以及凭证信息的对象(用户输入的)
	 * @return AuthenticationInfo 封装了认证信息的对象(从数据库查询到的)
	 * 
	 *client-->controller-->service-->realm
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
	{
		// 获取用户身份信息
		UsernamePasswordToken uToken = (UsernamePasswordToken) token;
		// 基于用户身份查询数据库信息
		String userName = uToken.getUsername();
		SysUser sysUser = sysUserDao.getSysUserByName(userName);
		AuthenticationInfo info = null;
		if (sysUser != null)
		{
			//System.out.println(sysUser.getPassword());
			// 对查询结果进行封装
			// 获取用户salt值，将其转换成一个字节对象
			//ByteSource byteSource = ByteSource.Util.bytes(sysUser.getSalt());
//			info = new SimpleAuthenticationInfo(sysUser.getAccount(), // 主身份
//					sysUser.getPassword(), // 已加密的密码
//					//byteSource, // salt对应的字节源对象
//					getName()// realm的名字
			//);
			info=new SimpleAuthenticationInfo(userName,sysUser.getPassword(),getName()); 
		}

		return info;
	}

}
