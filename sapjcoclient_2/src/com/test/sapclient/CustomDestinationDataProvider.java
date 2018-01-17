package com.test.sapclient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import com.sap.conn.jco.ext.DataProviderException;
import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;

public class CustomDestinationDataProvider implements DestinationDataProvider {
	private static Logger logger = Logger.getLogger(CustomDestinationDataProvider.class.getName());
	private DestinationDataEventListener destinationDataEventListener;

	@Override
	public Properties getDestinationProperties(String connectionName) throws DataProviderException {
		final Properties properties = new Properties();
		try {
			String customPropFilePath = "/var/sap/conf/"+connectionName+".jcoDestination";
			logger.info("read propfile fom custom location propfile : " + customPropFilePath);
			final FileInputStream in = new FileInputStream(customPropFilePath);
			properties.load(in);
			in.close();
			return properties;
		} catch (final IOException ioe) {
			return new Properties();
		}
	}

	@Override
	public void setDestinationDataEventListener(DestinationDataEventListener destinationDataEventListener) {
		this.destinationDataEventListener = destinationDataEventListener;		
	}

	@Override
	public boolean supportsEvents() {
		return true;
	}

}
