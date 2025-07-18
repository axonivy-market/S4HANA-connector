package com.axonivy.connector.hana.integration.test.auth;

import javax.ws.rs.Priorities;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

public class S4HanaAuthFeature implements Feature {

	@Override
	public boolean configure(FeatureContext context) {
		context.register(new S4HanaAuthFilter(), Priorities.AUTHORIZATION);
		return true;
	}

}
