package com.axonivy.connector.hana.integration.test.auth;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import com.axonivy.connector.hana.integration.test.constants.S4HanaTestConstants;

public class S4HanaAuthFilter implements ClientRequestFilter {

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		requestContext.getHeaders().add("APIKey", System.getProperty(S4HanaTestConstants.API_KEY));
	}

}
