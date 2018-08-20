package aiyunnet.entity;

import java.io.Serializable;

/**
 * 将来会被缓存使用，所以需要实现序列化接口
 */
public class SysUser extends BaseEntity implements Serializable
{
	private static final long serialVersionUID = -948462229692269686L;

	private int id;
	private String account;
	private String password;
	private String email;
	private String phone;
	private String salt;
	private String roleNames;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getAccount()
	{
		return account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public void setSalt(String salt)
	{
		this.salt = salt;
	}

	public String getSalt()
	{
		return salt;
	}

	public String getRoleNames()
	{
		return roleNames;
	}

	public void setRoleNames(String roleNames)
	{
		this.roleNames = roleNames;
	}
}
