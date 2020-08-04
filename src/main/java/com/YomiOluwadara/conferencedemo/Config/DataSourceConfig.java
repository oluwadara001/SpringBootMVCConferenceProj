package com.YomiOluwadara.conferencedemo.Config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {

	@Bean(name = "dataSourceProperties")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSourceProperties getDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "dataSource")
	@ConditionalOnMissingBean(name="dataSource")
	public DataSource getDataSource() {
		DataSourceProperties properties = getDataSourceProperties();

		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(properties.getDriverClassName());
		dataSourceBuilder.url(properties.getUrl());
		dataSourceBuilder.username(properties.getUsername());
		dataSourceBuilder.password(properties.getPassword());
		return dataSourceBuilder.build();
	}
	@Bean(name = "java.sql.Connection")
	public Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}
}