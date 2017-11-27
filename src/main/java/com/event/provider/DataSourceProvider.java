package com.event.provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.config.TargetDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceProvider {


	/**
	 * Enumeration of the supported database types
	 */
	public enum SupportedDatabases {
		/**
		 * Database product name for HANA
		 */
		HDB("HDB"),
		/**
		 * Database product name for Apache Derby
		 */
		APACHE_DERBY("Apache Derby"),
		/**
		 * Database product name for MaxDB
		 */
		MAX_DB("MaxDB"),
		/**
		 * Database product name for SAP DB
		 */
		SAP_DB("SAP DB"),
		/**
		 * Database product name for ASE DB
		 */
		ASE("Adaptive Server Enterprise");

		private String databaseName;

		private SupportedDatabases(String datatabaseName) {
			this.databaseName = datatabaseName;
		}

		public String getDatabaseName() {
			return databaseName;
		}
		
		@Override
		public String toString() {
			return databaseName;
		}
	}

	private static final Logger logger = LoggerFactory.getLogger(DataSourceProvider.class);
	
	/**
	 * The name of the persistence unit
	 */
	public static final String PERSISTENCE_UNIT = "com.sap.cloud.service.abs.core";

	/**
	 * Default data source lookup name
	 */
	public static final String DATA_SOURCE = "java:comp/env/jdbc/DefaultDB";

	private static final String ERROR_DATABASE_NOT_SUPPORTED_MESSAGE = "Database [%s] is not supported. Supported databases are %s";

	private static final String DEBUG_ACTIVATING_DB_DRIVER_MESSAGE = "Activating {} driver";
	private static final String DEBUG_DATABASE_PRODUCT_NAME_MESSAGE = "Database Product Name: [{}]";
	private static final String DEBUG_DATABASE_PRODUCT_VERSION_MESSAGE = "Database Product Version: [{}]";
	private static final String DEBUG_DATABASE_JDBC_DRIVER_NAME_MESSAGE = "Database JDBC Driver Name: [{}]";
	private static final String DEBUG_DATABASE_JDBC_DRIVER_VERSION_MESSAGE = "Database JDBC Driver Version: [{}]";
	private static final String DEBUG_BIND_MESSAGE = "DataSource.class bind to instance: [{}]";

	private static DataSourceProvider instance;

	private DataSource dataSource;

	/**
	 * Creates instance of the PersistenceUtils class
	 *
	 * @return instance of the PersistenceUtils class
	 * @throws NamingException
	 * @throws SQLException
	 * @throws UnsupportedDatabaseException
	 */
	public static DataSourceProvider getInstance() throws NamingException, SQLException {
		if (instance == null) {
			instance = new DataSourceProvider();
		}
		return instance;
	}

	/**
	 * Lookup the DataSource through the initial context (jdbc/DefaultDB)
	 *
	 * @return DataSource
	 * @throws NamingException
	 */
	private DataSource lookupDataSource() throws NamingException {
		return (DataSource) new InitialContext().lookup(DATA_SOURCE);
	}

	private DataSourceProvider() throws NamingException {
		dataSource = lookupDataSource();
	}

	/**
	 * Returns DataSource
	 *
	 * @return DataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Creates properties file with the DataSource settings
	 *
	 * @param ds
	 * @return DataSource properties
	 * @throws SQLException
	 * @throws UnsupportedDatabaseException
	 */
	public Properties getDataSourceProperties(final DataSource ds) throws SQLException {
		Properties properties = new Properties();
		properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, ds);
		logger.debug(DEBUG_BIND_MESSAGE, ds);

		try (Connection con = ds.getConnection()) {
			final String productName = con.getMetaData().getDatabaseProductName();
			logger.debug(DEBUG_DATABASE_PRODUCT_NAME_MESSAGE, productName);
			logger.debug(DEBUG_DATABASE_PRODUCT_VERSION_MESSAGE, con.getMetaData().getDatabaseProductVersion());
			logger.debug(DEBUG_DATABASE_JDBC_DRIVER_NAME_MESSAGE, con.getMetaData().getDriverName());
			logger.debug(DEBUG_DATABASE_JDBC_DRIVER_VERSION_MESSAGE, con.getMetaData().getDriverVersion());

			String targetDatabase = getTargetDatabase(productName);
			logger.debug(DEBUG_ACTIVATING_DB_DRIVER_MESSAGE, targetDatabase);
			properties.put(PersistenceUnitProperties.TARGET_DATABASE, targetDatabase);
		}
		
		properties.put(PersistenceUnitProperties.CACHE_SHARED_DEFAULT, Boolean.FALSE.toString());
		properties.put(PersistenceUnitProperties.SCHEMA_GENERATION_DATABASE_ACTION, PersistenceUnitProperties.CREATE_OR_EXTEND);
		return properties;
	}
	
	private String getTargetDatabase(String productName) {
		String targetDatabase = null;
		if (productName.equalsIgnoreCase(SupportedDatabases.HDB.getDatabaseName())) {
			targetDatabase = TargetDatabase.HANA;
		} else if (productName.equalsIgnoreCase(SupportedDatabases.APACHE_DERBY.databaseName)) {
			targetDatabase = TargetDatabase.Derby;
		} else if (productName.equalsIgnoreCase(SupportedDatabases.MAX_DB.databaseName)
				|| productName.equalsIgnoreCase(SupportedDatabases.SAP_DB.databaseName)) {
			targetDatabase = TargetDatabase.MaxDB;
		} else if (productName.equalsIgnoreCase(SupportedDatabases.ASE.databaseName)) {
			targetDatabase = TargetDatabase.Sybase;
		} else {
			throw new IllegalArgumentException(
					String.format(ERROR_DATABASE_NOT_SUPPORTED_MESSAGE, productName, Arrays.toString(SupportedDatabases.values())));
		}
		
		return targetDatabase;
	}
}
