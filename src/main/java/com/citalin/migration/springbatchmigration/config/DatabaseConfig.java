package com.citalin.migration.springbatchmigration.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DatabaseConfig {
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource primary()
	{
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.universitydatadestiny")
	public DataSource universityDataDestiny()
	{
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.universitydataorigin")
	public DataSource universityDataOrigin()
	{
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public EntityManagerFactory postgresEntityManagerFactory()
	{
		LocalContainerEntityManagerFactoryBean lem =
				new LocalContainerEntityManagerFactoryBean();
		
		lem.setDataSource(universityDataOrigin());
		lem.setPackagesToScan("com.citalin.migration.springbatchmigration.postgresql.entity");
		lem.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		lem.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		lem.afterPropertiesSet();
		
		return lem.getObject();
	}
	
	@Bean
	public EntityManagerFactory mySqlEntityManagerFactory()
	{
		LocalContainerEntityManagerFactoryBean lem =
				new LocalContainerEntityManagerFactoryBean();
		
		lem.setDataSource(universityDataDestiny());
		lem.setPackagesToScan("com.citalin.migration.springbatchmigration.mysql.entity");
		lem.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		lem.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		lem.afterPropertiesSet();
		
		return lem.getObject();
	}
	
	@Bean
	@Primary // Son spring can pick this one for transaction management.
	public JpaTransactionManager jpaTransactionManager()
	{
		JpaTransactionManager jpaTransactionManager =
				new JpaTransactionManager();
		
		jpaTransactionManager.setDataSource(universityDataDestiny());
		jpaTransactionManager.setEntityManagerFactory(mySqlEntityManagerFactory());
		
		return jpaTransactionManager;
	}

}
