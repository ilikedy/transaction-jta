package com.picc.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@DependsOn({"transactionManager"})
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef="entityManagerFactoryTargetplan",
		transactionManagerRef="transactionManager",
		basePackages= { "com.picc.repository.targetplan" })
public class RepositoryTargetplanConfig {
  
  private static Logger logger = LoggerFactory.getLogger( RepositoryTargetplanConfig.class );
  
//	@Autowired 
//	private JpaProperties jpaProperties;
	
	@Autowired @Qualifier("targetplanDBSource")
	private DataSource dataSource;
	
	@Bean(name="entityManagerTargetplan")
	@Qualifier("entityManagerTargetplan")
	public EntityManager entityManager(   ){
		 EntityManager ret = entityManagerFactoryTargetplan().getObject().createEntityManager();
		
		 System.out.println("******targetplan entitymanager:******"+ret+"************");
		 return ret;
	}
	

	@Autowired JpaVendorAdapter jpaAdapter;
	
	@Bean(name = "entityManagerFactoryTargetplan")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryTargetplan ( ) {
	  logger.error("targetplan datasource class:"+dataSource.getClass()+"\n targetplan datasource class : "+dataSource);
	  HashMap<String, String> properties = new HashMap<>();
	  properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
		
LocalContainerEntityManagerFactoryBean ret = new LocalContainerEntityManagerFactoryBean();
		
		ret.setJtaDataSource(dataSource );
		ret.setPackagesToScan("com.picc.entity.targetplan");
		ret.setPersistenceUnitName("TargetplanPersistenceUnit");
		ret.setJpaPropertyMap(properties);
		ret.setJpaVendorAdapter(jpaAdapter);
		
		return ret;
//      return builder
//              .dataSource(dataSource)
//              .properties(properties)
//              .packages("com.picc.entity.targetplan") //����ʵ��������λ��
//              .persistenceUnit("TargetplanPersistenceUnit")
//              .build();
	}
	
//	private Map<String, String> getVendorProperties(DataSource dataSource) {
//	    return jpaProperties.getHibernateProperties(dataSource);
//	}
//	
//	@Bean(name = "transactionManagerTargetplan")
//	public PlatformTransactionManager transactionManagerTargetplan(EntityManagerFactoryBuilder builder) {
//		PlatformTransactionManager ret = new JpaTransactionManager(entityManagerFactoryTargetplan(builder).getObject());
//		
//		System.out.println("******targetplan tm:******"+ret+"************");
//		return ret;
//	}
}
