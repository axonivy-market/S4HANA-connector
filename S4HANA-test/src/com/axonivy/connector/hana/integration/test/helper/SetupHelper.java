package com.axonivy.connector.hana.integration.test.helper;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.AppFixture;

public class SetupHelper {
	public static final String HANA_URL_PROP = "SAP4HANA_CONNECTOR_URL";

	public static void setup(AppFixture fixture) throws IOException {
		var hanaUrl = System.getProperty(HANA_URL_PROP);

		if (StringUtils.isEmpty(hanaUrl)) {
			try (var in = SetupHelper.class.getResourceAsStream("client.properties")) {
				if (in != null) {
					var props = new Properties();
					props.load(in);
					hanaUrl = (String) props.get(HANA_URL_PROP);
				} else {
					hanaUrl = "localhost";
				}
			}
		}
		fixture.var(HANA_URL_PROP, hanaUrl);
	}
}
