package com.axonivy.connector.hana;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import com.microsoft.auth.OAuth2Feature;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.rest.client.RestClient;
import ch.ivyteam.ivy.rest.client.RestClients;
import ch.ivyteam.ivy.rest.client.security.CsrfHeaderFeature;

public class HanaTestClient {
	  public static final UUID HANA_CLIENT_ID = UUID.fromString("319c4c35-df80-4f51-b63e-ade0e8f60a9a");
	  
	  private static final AtomicReference<RestClient> ORIGINAL = new AtomicReference<>();

	  public static void resetForApp(IApplication app) {
	    RestClient client = ORIGINAL.get();
	    if (client != null) {
	      RestClients.of(app).set(client);
	      ORIGINAL.set(null);
	    }
	  }

	  public static void mockForApp(IApplication app) {
	    RestClients clients = RestClients.of(app);
	    RestClient hanaClient = clients.find(HANA_CLIENT_ID);
	    if (ORIGINAL.get() == null) {
	      ORIGINAL.set(hanaClient);
	    }

	    var hanaMock = hanaClient
	      .toBuilder()
	      .uri(HanaServiceMock.URI)
	      .feature(CsrfHeaderFeature.class.getName())
	      .property("AUTH.baseUri", HanaAuthMock.URI)
	      .property("AUTH.secretKey", "1")
	      .property("scope", "user.read calendars.read")
	      .toRestClient();

	    var features = new ArrayList<>(hanaMock.features());
	    if (!features.contains(CsrfHeaderFeature.class.getName())) {
	      features.add(CsrfHeaderFeature.class.getName());
	    }
	    features.remove(OAuth2Feature.class.getName());
	    hanaMock = new RestClient(hanaMock.uri(), hanaMock.name(), hanaMock.uniqueId(), hanaMock.description(),
	      features, hanaMock.properties(), hanaMock.metas());

	    clients.set(hanaMock);
	  }
}
