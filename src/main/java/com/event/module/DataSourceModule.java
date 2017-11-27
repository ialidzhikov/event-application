package com.event.module;

import java.sql.SQLException;
import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.event.provider.DataSourceProvider;
import com.google.inject.AbstractModule;
import com.google.inject.ProvisionException;
import com.google.inject.persist.jpa.JpaPersistModule;

public class DataSourceModule extends AbstractModule {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceModule.class);

	private static final String DEBUG_CONFIGURING_MODULE_MESSAGE = "Configuring DataSourceModule.";
	private static final String ERROR_DATA_SOURCE_INITIALIZATION_FAILED_MESSAGE = "DataSource initialization failed.";

	private static final String PERSISTENCE_UNIT = "event-application";

	@Override
	protected void configure() {
		logger.debug(DEBUG_CONFIGURING_MODULE_MESSAGE);

		DataSourceProvider provider;
		try {
			provider = DataSourceProvider.getInstance();
			DataSource dataSource = provider.getDataSource();
			Properties properties = provider.getDataSourceProperties(dataSource);
			install(new JpaPersistModule(PERSISTENCE_UNIT).properties(properties));
		} catch (NamingException | SQLException e) {
			throw new ProvisionException(ERROR_DATA_SOURCE_INITIALIZATION_FAILED_MESSAGE, e);
		}
	}
}
