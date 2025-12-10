package com.axonivy.connector.hana;

import java.util.List;
import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.rest.client.authentication.HttpBasicAuthenticationFeature;
import ch.ivyteam.ivy.rest.client.mapper.JsonFeature;
import ch.ivyteam.ivy.rest.client.security.CsrfHeaderFeature;

public class HanaTestClient {
	public static void mock(AppFixture fixture) {
		fixture.config("RestClients.'HANA_BUSINESS_PARTNER_ (Business Partner (A2X))'.Properties.AUTH.baseUri",
				HanaAuthMock.URI);
		fixture.config("RestClients.'HANA_BUSINESS_PARTNER_ (Business Partner (A2X))'.Properties.AUTH.secretKey", "1");
		fixture.config("RestClients.'HANA_BUSINESS_PARTNER_ (Business Partner (A2X))'.Properties.scope",
				"user.read calendars.read");
		fixture.config("RestClients.'HANA_BUSINESS_PARTNER_ (Business Partner (A2X))'.Properties.scope",
				"user.read calendars.read");
		fixture.config("RestClients.'HANA_BUSINESS_PARTNER_ (Business Partner (A2X))'.Features",
				List.of(CsrfHeaderFeature.class.getName(), JsonFeature.class.getName(),
						HttpBasicAuthenticationFeature.class.getName()));
	}
}
