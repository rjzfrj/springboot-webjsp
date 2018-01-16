package com.my.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.jolbox.bonecp.BoneCPDataSource;

/**
 * 使用阿里链接池
 *
 */
@Configuration // 通过该注解来表明该类是一个Spring的配置，相当于一个xml文件
@PropertySource(value = { "classpath:jdbc.properties" }, ignoreResourceNotFound = true) // 读取数据库连接信息
@ComponentScan(basePackages = "com.my") // 配置扫描包
@SpringBootApplication
@MapperScan("com.my.mapper")
public class SpringSourceConfig2 extends SpringBootServletInitializer {// 此类需要SpringBootServletInitializer用于发布到独立tomcat

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${jdbc.driverClassName}")
	private String jdbcDriverClassName;

	@Value("${jdbc.username}")
	private String jdbcUsername;

	@Value("${jdbc.password}")
	private String jdbcPassword;
	
	@Value("${jdbc.initialSize}")
	private String initialSize;
	
	@Value("${jdbc.minIdle}")
	private String minIdle;
	
	@Value("${jdbc.maxIdle}")
	private String maxIdle;
	
	@Value("${jdbc.maxActive}")
	private String maxActive;
	
	@Value("${jdbc.maxWaitMillis}")
	private String maxWaitMillis;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DruidDataSource boneCPDataSource = new DruidDataSource();
		// 数据库驱动
		boneCPDataSource.setDriverClassName(jdbcDriverClassName);
		// 相应驱动的jdbcUrl
		boneCPDataSource.setUrl(jdbcUrl);
		// 数据库的用户名
		boneCPDataSource.setUsername(jdbcUsername);
		// 数据库的密码
		boneCPDataSource.setPassword(jdbcPassword);
		//通常来说，只需要修改initialSize、minIdle、maxActive 
		boneCPDataSource.setInitialSize(Integer.valueOf(initialSize));
		//最小连接池数量
		boneCPDataSource.setMinIdle(Integer.valueOf(minIdle));
		//
		boneCPDataSource.setMaxIdle(Integer.valueOf(maxIdle));
		
		boneCPDataSource.setMaxActive(Integer.valueOf(maxActive));
//		最大连接池数量，缺省值为8  获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
		boneCPDataSource.setMaxWait(Integer.valueOf(maxWaitMillis));
		return boneCPDataSource;
	}
	
	
	
	/*<bean id="dataSource_polymer" class="com.alibaba.druid.pool.DruidDataSource"
			init-method="init" destroy-method="clone">
			<!-- 基本属性driverClassName、 url、user、password -->
			<property name="driverClassName" value="${polymer.driver}" />
			<property name="url" value="${polymer.url}" />
			<property name="username" value="${polymer.username}" />
			<property name="password" value="${polymer.password}" />

			
		</bean>*/

	@Bean // sqlSession
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

		// 为sqlSessionFactory 注入数据源
		sqlSessionFactoryBean.setDataSource(dataSource());

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		// 扫描xml配置的包
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));

		return sqlSessionFactoryBean.getObject();
	}

	@Bean // 事物管理
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 设置启动类用于独立tomcat运行入口
		return builder.sources(SpringSourceConfig2.class);
	}

}
