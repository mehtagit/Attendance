package com.ecom.utility;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.ecom.services.JDBCTemplateService;
import com.google.gson.Gson;

@Configuration
// @ConfigurationProperties("foo")
@ConfigurationProperties(prefix = "app")
public class AppConfig {

	/*@NotNull
	@NotBlank
	private String dbConnectionType;

	@NotNull
	@NotBlank
	private String dbConnectionUrl;
	@NotNull
	@NotBlank
	private String dbConnectionUser;
	@NotNull
	@NotBlank
	private String dbConnectionPass;
	@NotNull
	@NotBlank
	private String dbConnectionDriver;

	@Min(1)
	@Max(10)
	private int dbMinConnections;

	@Min(11)
	@Max(50)
	private int dbMaxConnections;

	@Bean
	@ConditionalOnMissingBean(name = "gson")
	public Gson gson() {
		return new Gson();
	}

	@Bean
	public JDBCTemplateService jdbcTemplate(BasicDataSource dataSource,DataSourceTransactionManager transactionManager) {
		return new JDBCTemplateService(dataSource,transactionManager);
	}

	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(dbConnectionDriver);
		dataSource.setUrl(dbConnectionUrl);
		dataSource.setUsername(dbConnectionUser);
		dataSource.setPassword(dbConnectionPass);
		dataSource.setMaxActive(dbMaxConnections);
		dataSource.setInitialSize(dbMinConnections);
		dataSource.setValidationQuery("select NOW()");
		dataSource.setTestOnBorrow(true);

		return dataSource;
	}

	@Bean
	public org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager(BasicDataSource dataSource){
		org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}*/
	/*
	 * @Bean public DataSourceInitializer dataSourceInitializer(DataSource
	 * dataSource) { DataSourceInitializer dataSourceInitializer = new
	 * DataSourceInitializer(); dataSourceInitializer.setDataSource(dataSource);
	 * ResourceDatabasePopulator databasePopulator = new
	 * ResourceDatabasePopulator(); databasePopulator.addScript(new
	 * ClassPathResource("data.sql"));
	 * dataSourceInitializer.setDatabasePopulator(databasePopulator);
	 * dataSourceInitializer.setEnabled(Boolean.parseBoolean(initDatabase));
	 * return dataSourceInitializer; }
	 */

/*	@Bean
	@ConditionalOnMissingBean(name = "requestQueue")
	public BlockingQueue<Request> requestQueue() {
		return new java.util.concurrent.LinkedBlockingQueue<Request>();
	}*/

	/*@Bean
	@ConditionalOnMissingBean(name = "transactionQueue")
	public BlockingQueue<Transaction> transactionQueue() {
		return new java.util.concurrent.LinkedBlockingQueue<Transaction>();
	}
*/
	/*@Bean
	@ConditionalOnMissingBean(name = "clientSocket")
	public java.net.DatagramSocket clientSocket() {
		java.net.DatagramSocket clientSocket = null;
		try {
			clientSocket = new java.net.DatagramSocket();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientSocket;
	}*/

	/*@Bean
	@ConditionalOnMissingBean(name = "sqlUtility")
	public SqlUtility SqlUtility() {
		try {
			return new SqlUtility(dbConnectionType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/

}
