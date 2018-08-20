package aiyunnet.application;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import aiyunnet.service.AccountService;
import aiyunnet.service.AdminService;

@ComponentScan("aiyunnet.service.impl")
public class AppConfig
{
	//使用druid数据库连接池，整合mybatis。提供数据库访问效率。
	/*
	 * @Bean("sqlSessionTemplate") public SqlSessionTemplate newSqlSessionTemplate()
	 * throws Exception {
	 * 
	 * Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
	 * SqlSessionFactory sqlSessionFactory = new
	 * SqlSessionFactoryBuilder().build(reader); SqlSessionTemplate
	 * sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
	 * 
	 * return sqlSessionTemplate; }
	 */
}
