package com.ecom.services;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

public class JDBCTemplateService {
	private JdbcTemplate jdbcTemplate;
	private PlatformTransactionManager transactionManager;
	
	public JDBCTemplateService(DataSource dataSource ,PlatformTransactionManager transactionManager) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.transactionManager  = transactionManager;
	}
	
	public JdbcTemplate geJdbcTemplate(){
		return jdbcTemplate;
	}

	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}
	
	
}
