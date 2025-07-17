package com.axonivy.connector.hana.integration.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import com.axonivy.connector.hana.HanaTestClient;
import com.axonivy.connector.hana.integration.test.constants.S4HanaTestConstants;
import com.axonivy.connector.hana.integration.test.helper.SetupHelper;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.rest.client.RestClient;
import ch.ivyteam.ivy.rest.client.RestClients;

public class S4HanaTestUtils {
	public static final UUID HANA_CLIENT_ID = UUID.fromString("319c4c35-df80-4f51-b63e-ade0e8f60a9a");
	public static final String REST_CLIENT_FEATURE = "RestClients.HANA_BUSINESS_PARTNER_ (Business Partner (A2X)).Features";
	public static final List<String> CONFIG_FEATURES = List.of("ch.ivyteam.ivy.rest.client.mapper.JsonFeature",
			"com.axonivy.connector.hana.integration.test.auth.S4HanaAuthFeature");

	private static final AtomicReference<RestClient> ORIGINAL = new AtomicReference<>();

	public static void setUpConfigForContext(String contextName, AppFixture fixture, IApplication app)
			throws IOException {
		switch (contextName) {
		case S4HanaTestConstants.REAL_CALL_CONTEXT_DISPLAY_NAME:
			setUpConfigForApiTest(fixture, app);
			break;
		case S4HanaTestConstants.MOCK_SERVER_CONTEXT_DISPLAY_NAME:
			setUpConfigForMockServer(fixture, app);
			break;
		default:
			break;
		}
	}

	private static void setUpConfigForMockServer(AppFixture fixture, IApplication app) throws IOException {
		SetupHelper.setup(fixture);
		String url = Ivy.var().get(SetupHelper.HANA_URL_PROP);
		if (url.contains("localhost")) {
			HanaTestClient.mockForApp(app);
		}
	}

	private static void setUpConfigForApiTest(AppFixture fixture, IApplication app) {
		fixture.config(REST_CLIENT_FEATURE, CONFIG_FEATURES);
		RestClients clients = RestClients.of(app);
		RestClient hanaClient = clients.find(HANA_CLIENT_ID);
		if (ORIGINAL.get() == null) {
			ORIGINAL.set(hanaClient);
		}
		String baseUrl = System.getProperty(S4HanaTestConstants.BASE_URL);
		var hanaMock = hanaClient.toBuilder().uri(baseUrl).toRestClient();
		var features = new ArrayList<>(hanaMock.features());
		hanaMock = new RestClient(hanaMock.uri(), hanaMock.name(), hanaMock.uniqueId(), hanaMock.description(),
				features, hanaMock.properties(), hanaMock.metas());
		clients.set(hanaMock);
	}
}
