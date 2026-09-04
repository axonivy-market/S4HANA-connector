package com.axonivy.connector.hana.integration.test.auth;

import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;

public class S4HanaAuthFeature implements Feature {

	@Override
	public boolean configure(FeatureContext context) {
		context.register(new S4HanaAuthFilter(), Priorities.AUTHORIZATION);
		return true;
	}

}
