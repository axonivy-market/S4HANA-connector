package com.axonivy.connector.hana.integration.test;

import static org.assertj.core.api.Assertions.assertThat;
import static com.axonivy.utils.e2etest.enums.E2EEnvironment.REAL_SERVER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.axon.market.s4.hana.getHANAData;
import com.axonivy.connector.hana.HanaTestClient;
import com.axonivy.connector.hana.integration.test.constants.S4HanaTestConstants;
import com.axonivy.connector.hana.integration.test.helper.SetupHelper;
import com.axonivy.utils.e2etest.context.MultiEnvironmentContextProvider;
import com.axonivy.utils.e2etest.utils.E2ETestUtils;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.rest.client.RestClient;
import ch.ivyteam.ivy.rest.client.RestClients;
import ch.ivyteam.ivy.security.ISession;
import hana.bo.BusinessPartnerRequest;

@IvyProcessTest(enableWebServer = true)
@ExtendWith(MultiEnvironmentContextProvider.class)
class RestHANAProcessTest {
	public static final UUID HANA_CLIENT_ID = UUID.fromString("319c4c35-df80-4f51-b63e-ade0e8f60a9a");
	public static final String REST_CLIENT_FEATURE =
			"RestClients.HANA_BUSINESS_PARTNER_ (Business Partner (A2X)).Features";
	public static final List<String> CONFIG_FEATURES = List.of("ch.ivyteam.ivy.rest.client.mapper.JsonFeature",
			"com.axonivy.connector.hana.integration.test.auth.S4HanaAuthFeature");
	private static final String SELECT_FIELDS_BUSINESS_PARTNER =
			"BusinessPartnerCategory,BusinessPartnerFullName,BusinessPartner";
	private static final String TO_BUSINESS_PARTNER_ADDRESS_TO_EMAIL_ADDRESS =
			"to_BusinessPartnerAddress/to_EmailAddress";

	private static final AtomicReference<RestClient> ORIGINAL = new AtomicReference<>();
	private boolean isRealTest;

	@BeforeEach
	void beforeEach(ExtensionContext context, AppFixture fixture, IApplication app) throws IOException {
		isRealTest = context.getDisplayName().equals(REAL_SERVER.getDisplayName());
		E2ETestUtils.determineConfigForContext(context.getDisplayName(), runRealEnv(fixture, app),
				runMockEnv(fixture, app));
	}

	@AfterEach
	void afterEach(ExtensionContext context, AppFixture fixture, IApplication app) {
		RestClients clients = RestClients.of(app);
		clients.remove("HANA_BUSINESS_PARTNER_ (Business Partner (A2X))");
	}

	// partnersCategory2Expand - filter only business category = 2, top =10, Expand
	// field Address/email
	@TestTemplate
	void testPartnersCategory2Expand(ExtensionContext context, BpmClient bpmClient, ISession session) {
		BusinessPartnerRequest bpr = new BusinessPartnerRequest();
		bpr.setBusinessPartnerCategory("2");
		bpr.getQuery().setTopCount(10);
		bpr.getQuery().setExpand(Arrays.asList(TO_BUSINESS_PARTNER_ADDRESS_TO_EMAIL_ADDRESS));

		ExecutionResult result = bpmClient.start().subProcess(BpmElement.pid("181AFD80906674FB-f8"))// Processes/getHANA/getBusinessPartners.ivp
				.withParam("request", bpr).as().session(session).execute();

		Object obj = result.data().last();
		assertThat(obj).isInstanceOf(getHANAData.class);
		getHANAData jsonData = result.data().last();
		assertThat(jsonData).isNotNull();
		List<com.axon.market.s4hana.client.APIBUSINESSPARTNERABusinessPartnerType> bps = jsonData.getBusinessPartners();
		if (isRealTest) {
			assertThat(bps.size()).isGreaterThan(1);
		} else {
			for (var bp : bps) {
				assertThat(bp.getBusinessPartnerCategory()).isEqualTo("2");
				assertThat(bp.getToBusinessPartnerAddress().getResults()).isNotNull();
			}
		}
	}

	// partnersCategory2NOExpand - filter only business category = 2, top =10, no
	// fields Expanded
	@TestTemplate
	void testPartnersCategory2NOExpand(ExtensionContext context, BpmClient bpmClient, ISession session) {
		BusinessPartnerRequest bpr = new BusinessPartnerRequest();
		bpr.setBusinessPartnerCategory("2");
		bpr.getQuery().setTopCount(10);

		ExecutionResult result = bpmClient.start().subProcess(BpmElement.pid("181AFD80906674FB-f8"))// Processes/getHANA/getBusinessPartners.ivp
				.withParam("request", bpr).as().session(session).execute();

		Object obj = result.data().last();
		assertThat(obj).isInstanceOf(getHANAData.class);
		getHANAData jsonData = result.data().last();
		assertThat(jsonData).isNotNull();
		List<com.axon.market.s4hana.client.APIBUSINESSPARTNERABusinessPartnerType> bps = jsonData.getBusinessPartners();
		if (isRealTest) {
			assertThat(bps.size()).isGreaterThan(1);
		} else {
			assertThat(bps).isNotNull();
			for (var bp : bps) {
				assertThat(bp.getBusinessPartnerCategory()).isEqualTo("2");
				assertThat(bp.getToBusinessPartnerAddress().getResults()).isNull();
			}
		}
	}

	// partnersCategory2SelectFields - filter only business category = 2, top =10,
	// select only a few fields
	@TestTemplate
	void testPartnersCategory2SelectFields(ExtensionContext context, BpmClient bpmClient, ISession session) {
		BusinessPartnerRequest bpr = new BusinessPartnerRequest();
		bpr.setBusinessPartnerCategory("2");
		bpr.getQuery().setTopCount(10);
		bpr.getQuery().setSelect(Arrays.asList(SELECT_FIELDS_BUSINESS_PARTNER));

		ExecutionResult result = bpmClient.start().subProcess(BpmElement.pid("181AFD80906674FB-f8"))// Processes/getHANA/getBusinessPartners.ivp
				.withParam("request", bpr).as().session(session).execute();

		Object obj = result.data().last();
		assertThat(obj).isInstanceOf(getHANAData.class);
		getHANAData jsonData = result.data().last();
		assertThat(jsonData).isNotNull();
		List<com.axon.market.s4hana.client.APIBUSINESSPARTNERABusinessPartnerType> bps = jsonData.getBusinessPartners();
		assertThat(bps).isNotNull();
		if (isRealTest) {
			assertThat(bps.size()).isGreaterThan(1);
		} else {
			for (var bp : bps) {
				assertThat(bp.getBusinessPartnerCategory()).isEqualTo("2");
				assertThat(bp.getBusinessPartner()).isNotNull();
				assertThat(bp.getBusinessPartnerFullName()).isNotNull();

				assertThat(bp.getAcademicTitle()).isNull();
				assertThat(bp.getAdditionalLastName()).isNull();
				assertThat(bp.getAuthorizationGroup()).isNull();
				assertThat(bp.getBirthDate()).isNull();
				assertThat(bp.getBusinessPartnerBirthplaceName()).isNull();
				assertThat(bp.getBusinessPartnerGrouping()).isNull();
				assertThat(bp.getBusinessPartnerIDByExtSystem()).isNull();
				assertThat(bp.getBusinessPartnerName()).isNull();
				assertThat(bp.getBusinessPartnerType()).isNull();
				assertThat(bp.getBusinessPartnerUUID()).isNull();
				assertThat(bp.getToBusinessPartnerAddress()).isNull();
			}
		}

	}

	// partnersManyOnlySelectFields = no filter applied , select only a few fields
	@TestTemplate
	void testPartnersManyOnlySelectFields(ExtensionContext context, BpmClient bpmClient, ISession session) {
		BusinessPartnerRequest bpr = new BusinessPartnerRequest();
		bpr.getQuery().setSelect(Arrays.asList(SELECT_FIELDS_BUSINESS_PARTNER));

		ExecutionResult result = bpmClient.start().subProcess(BpmElement.pid("181AFD80906674FB-f8"))// Processes/getHANA/getBusinessPartners.ivp
				.withParam("request", bpr).as().session(session).execute();

		Object obj = result.data().last();
		assertThat(obj).isInstanceOf(getHANAData.class);
		getHANAData jsonData = result.data().last();
		assertThat(jsonData).isNotNull();
		List<com.axon.market.s4hana.client.APIBUSINESSPARTNERABusinessPartnerType> bps = jsonData.getBusinessPartners();
		assertThat(bps).isNotNull();
		if (isRealTest) {
			assertThat(bps.size()).isEqualTo(0);
		} else {
			for (var bp : bps) {
				assertThat(bp.getBusinessPartnerCategory()).isNotNull();
				assertThat(bp.getBusinessPartner()).isNotNull();
				assertThat(bp.getBusinessPartnerFullName()).isNotNull();

				assertThat(bp.getAcademicTitle()).isNull();
				assertThat(bp.getAdditionalLastName()).isNull();
				assertThat(bp.getAuthorizationGroup()).isNull();
				assertThat(bp.getBirthDate()).isNull();
				assertThat(bp.getBusinessPartnerBirthplaceName()).isNull();
				assertThat(bp.getBusinessPartnerGrouping()).isNull();
				assertThat(bp.getBusinessPartnerIDByExtSystem()).isNull();
				assertThat(bp.getBusinessPartnerName()).isNull();
				assertThat(bp.getBusinessPartnerType()).isNull();
				assertThat(bp.getBusinessPartnerUUID()).isNull();
				assertThat(bp.getToBusinessPartnerAddress()).isNull();
			}
		}
	}

	private Runnable runRealEnv(AppFixture fixture, IApplication app) {
		return () -> {
			fixture.config(REST_CLIENT_FEATURE, CONFIG_FEATURES);
			RestClients clients = RestClients.of(app);
			RestClient hanaClient = clients.find(HANA_CLIENT_ID);
			if (ORIGINAL.get() == null) {
				ORIGINAL.set(hanaClient);
			}
			String baseUrl = System.getProperty(S4HanaTestConstants.BASE_URL);
			var hanaMock = hanaClient.toBuilder().uri(baseUrl).toRestClient();
			var features = new ArrayList<>(hanaMock.features());
			hanaMock = new RestClient(hanaMock.uri(), hanaMock.name(), hanaMock.uniqueId(), hanaMock.description(), features,
					hanaMock.properties(), hanaMock.metas());
			clients.set(hanaMock);
		};
	}

	private Runnable runMockEnv(AppFixture fixture, IApplication app) {
		return () -> {
			try {
				SetupHelper.setup(fixture);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String url = Ivy.var().get(SetupHelper.HANA_URL_PROP);
			if (url.contains("localhost")) {
				HanaTestClient.mockForApp(app);
			}
		};
	}

}
