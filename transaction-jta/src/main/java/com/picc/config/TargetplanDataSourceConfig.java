package com.picc.config;

import java.sql.SQLException;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

@Configuration
public class TargetplanDataSourceConfig {
	@Bean(name="targetplanXA")
//	@Qualifier("targetplanXA")
	@ConfigurationProperties(prefix="com.picc.targetplandb")
	public MysqlXADataSource druidDs() throws SQLException{
		MysqlXADataSource ds =  new MysqlXADataSource();
//		ds.setUrl("jdbc:log4jdbc:mysql://localhost/test_1"); 
//		ds.setUsername("root"); 
//		ds.setPassword("861214"); 
//		ds.setDriverClassName("net.sf.log4jdbc.DriverSpy"); 
//		ds.setDefaultAutoCommit(true); 
//		ds.setMaxActive(30); 
//		ds.setMinIdle(5); 
//		ds.setInitialSize(5); 
//		ds.setMaxWait(15000); 
//		ds.setTestWhileIdle(false); 
//		ds.setMinEvictableIdleTimeMillis(3600000); 
//		ds.setRemoveAbandonedTimeout(2); 
//		ds.setLogAbandoned(true); 
//		ds.setFilters("stat,log4j"); 
//		ds.setConnectionProperties("druid.stat.slowSqlMillis=1001");
//		
		ds.setURL("jdbc:mysql://localhost/test_1");
		ds.setUser("root");
		ds.setPassword("861214");
		
		
		return ds;
	}
	
	@Bean(name="targetplanDBSource")
	@Qualifier("targetplanDBSource")
	public DataSource targetplanDBSource() throws SQLException{
////		return DataSourceBuilder.create().build();
//	  DruidDataSource ret = new DruidDataSource();
////	  ret.setInitialSize(5);
////	  ret.setMaxActive(10);
////	  ret.setMaxIdle(10);
//	  return new DruidDataSource();
		
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		ds.setXaDataSource(druidDs());

		ds.setUniqueResourceName("targetplanUR");
	  return ds;
	}
}
