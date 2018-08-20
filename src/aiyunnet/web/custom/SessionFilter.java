package aiyunnet.web.custom;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiyunnet.utils.ContextHelper;

public class SessionFilter implements Filter
{
	public FilterConfig config;

	public void destroy()
	{
		this.config = null;
	}

	public boolean isContains(String container, String[] regx)
	{
		boolean result = false;

		for (int i = 0; i < regx.length; i++)
		{
			if (container.indexOf(regx[i]) != -1)
			{
				return true;
			}
		}
		return result;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String logonStrings = config.getInitParameter("logonStrings"); // 登录登陆页面
		String includeStrings = config.getInitParameter("includeStrings"); // 过滤资源后缀参数
		String redirectPath = request.getContextPath() + config.getInitParameter("redirectPath");// 没有登陆转向页面
		String disableFilter = config.getInitParameter("disableFilter");// 过滤器是否有效

		if (disableFilter.toUpperCase().equals("Y"))
		{ // 过滤无效
			chain.doFilter(request, response);
			return;
		}
		String[] logonList = logonStrings.split(";");
		String[] includeList = includeStrings.split(";");

		if (!isContains(request.getRequestURI(), includeList))
		{// 只对指定过滤参数后缀进行过滤
			chain.doFilter(request, response);
			return;
		}

		if (isContains(request.getRequestURI(), logonList))
		{// 对登录页面不进行过滤
			chain.doFilter(request, response);
			return;
		}

		int staffId = ContextHelper.current().getStaffId();// 判断用户是否登录
		if (staffId == 0)
		{
			response.sendRedirect(redirectPath);
			return;
		} else
		{
			chain.doFilter(request, response);
			return;
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException
	{
		config = filterConfig;
	}

}
