package com.test.sapclient;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.*;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DestinationDataProvider;

public class TestSAPConnection {
    // make sure that current user have access to this folder 
	 private static Logger logger = Logger.getLogger(TestSAPConnection.class.getName());
	 private static String DESTINATION_NAME = "private_destination";
	 public static void main(String[] args) throws JCoException {
		System.setProperty("jco.destinations.dir", "/var/sap/conf/");

		Properties connectProperties = new Properties();
		connectProperties.setProperty(DestinationDataProvider.JCO_LANG, "en");
		connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, "sap host");
		connectProperties.setProperty(DestinationDataProvider.JCO_USER, "sap user");
		connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, "");
		connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, "");
		connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, "");
		connectProperties.setProperty(DestinationDataProvider.JCO_CODEPAGE, "");
		connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, "");
		connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, "");
		
		createDestinationPropertiesFile(DESTINATION_NAME, connectProperties);
		
		logger.info("destinationName: " + DESTINATION_NAME);
		JCoDestination destination = JCoDestinationManager.getDestination(DESTINATION_NAME);
		logger.info("Host :");
		logger.info(destination.getAttributes().getHost());

	}
	
	static void createDestinationPropertiesFile(String destinationName, Properties connectProperties) {

		String fileName = System.getProperty("jco.destinations.dir")+"/"+destinationName + ".jcoDestination";
		File destCfg = new File(fileName);
		try {
			FileOutputStream fos = new FileOutputStream(destCfg, false);
			connectProperties.store(fos, "sap client test");
			fos.close();
			logger.info("file: '" + destCfg.getAbsolutePath() + "' written");
		} catch (Exception e) {
			throw new RuntimeException("Unable to create the destination files", e);
		}
	}

}
