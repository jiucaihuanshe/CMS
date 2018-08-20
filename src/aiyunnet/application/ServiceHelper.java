package aiyunnet.application;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import aiyunnet.service.AccountService;
import aiyunnet.service.AdminService;

public class ServiceHelper
{
	//public static AnnotationConfigApplicationContext ctx = null;
	//public static ClassPathXmlApplicationContext ctx = null;
	//static
	//{
		//ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		//ctx = new ClassPathXmlApplicationContext("spring-context.xml");
	//}
	
	private ServiceHelper() {}
	
	private static ServiceHelper instance;
	
	public static ServiceHelper getInstance()
	{
		if (instance == null)
		{
			synchronized (ServiceHelper.class)
			{
				if (instance == null)
				{
					instance = new ServiceHelper();
				}
			}
		}
		return instance;
	}

//	public static SqlSessionTemplate sqlSessionTemplate()
//	{
//		SqlSessionTemplate sqlSessionTemplate = ctx.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
//		return sqlSessionTemplate;
//	}
//
//	public static SqlSessionFactory sqlSessionFactory()
//	{
//		SqlSessionFactory sqlSessionFactory = sqlSessionTemplate().getSqlSessionFactory();
//		return sqlSessionFactory;
//	}
//
//	private AnnotationConfigApplicationContext initCtx()
//	{
//		if(ctx==null) 
//		{
//			synchronized (ServiceHelper.class)
//			{
//				if(ctx==null) 
//				{
//					ctx = new AnnotationConfigApplicationContext(AppConfig.class);
//				}
//			}
//		}
//		
//		return ctx;
//	}
//	
//	public AccountService accountService()
//	{
//		return initCtx().getBean("accountServiceImpl", AccountService.class);
//	}
//
//	public AdminService adminService()
//	{
//		return initCtx().getBean("adminServiceImpl", AdminService.class);
//	}

}
