package com.sample.protobuff.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

@Configuration
@Component("dataSourceConfig")
public class DataSourceConfig {
	@Primary
	@ConfigurationProperties(prefix = "datasource.postgre")
	@Bean(name = "postgreDataSource", destroyMethod = "close")
	public DataSource monitorDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "postgreSqlSession")
	public SqlSessionFactory monitorSqlSessionFactory(@Qualifier("postgreDataSource") DataSource addressDataSource)
		throws Exception {
		PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();

		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(addressDataSource);
		sessionFactory.setConfigLocation(pathResolver.getResource("classpath:mybatis-postgre.xml"));
		return sessionFactory.getObject();
	}

	@Configuration
	@MapperScan(basePackages = "com.sample.protobuff.mapper", sqlSessionFactoryRef = "postgreSqlSession")
	static class sampleMybatisConfig extends MybatisAutoConfiguration {

	}

	
}
