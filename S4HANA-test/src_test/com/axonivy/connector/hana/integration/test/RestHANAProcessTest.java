package com.axonivy.connector.hana.integration.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axon.market.s4.hana.getHANAData;
import com.axon.market.s4hana.client.Wrapper9;
import com.axonivy.connector.hana.HanaTestClient;
import com.axonivy.connector.hana.integration.test.helper.SetupHelper;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISession;
import hana.bo.BusinessPartnerRequest;
import hana.url.URLBusinessPartnerService;

@IvyProcessTest(enableWebServer = true)
class RestHANAProcessTest {

	private static final String SELECT_FIELDS_BUSINESS_PARTNER ="BusinessPartnerCategory,BusinessPartnerFullName,BusinessPartner";
	private static final String TO_BUSINESS_PARTNER_ADDRESS_TO_EMAIL_ADDRESS = "to_BusinessPartnerAddress/to_EmailAddress";
	//private static final String REST_CLIENT_NAME_HANA_BUSINESS_PARTNER_ = "HANA_BUSINESS_PARTNER_ (Service for namespace API_BUSINESS_PARTNER)";	

	@BeforeEach
	void beforeEach(AppFixture fixture, IApplication app) throws IOException {
		SetupHelper.setup(fixture);
		
		String url = Ivy.var().get(SetupHelper.HANA_URL_PROP);
		if(url.contains("localhost")) {
			HanaTestClient.mockForApp(app);
		}			
	}
		
	//partnersCategory2Expand - filter only business category = 2, top =10, Expand field Address/email
	@Test
	void testPartnersCategory2Expand (BpmClient bpmClient, ISession session, AppFixture fixture) {

		BusinessPartnerRequest bpr = new BusinessPartnerRequest();
		bpr.setBusinessPartnerCategory("2");
		bpr.getQuery().setTopCount(10);
		bpr.getQuery().setExpand(Arrays.asList(TO_BUSINESS_PARTNER_ADDRESS_TO_EMAIL_ADDRESS));
		
		
		ExecutionResult result = bpmClient.start().subProcess(BpmElement.pid("181AFD80906674FB-f8"))//Processes/getHANA/getBusinessPartners.ivp
				.withParam("request", bpr).as().session(session).execute();
		
		
		Object obj = result.data().last();
		assertThat(obj).isInstanceOf(getHANAData.class);
		getHANAData jsonData = result.data().last();
		assertThat(jsonData).isNotNull();
		List<com.axon.market.s4hana.client.APIBUSINESSPARTNERABusinessPartnerType> bps = jsonData.getBusinessPartners();
		assertThat(bps).isNotNull();
		
		for(var bp :bps) {
			assertThat(bp.getBusinessPartnerCategory()).isEqualTo("2");
			assertThat(bp.getToBusinessPartnerAddress().getResults()).isNotNull();
		}
	}
	
	//partnersCategory2NOExpand - filter only business category = 2, top =10, no fields Expanded	
	@Test
	void testPartnersCategory2NOExpand(BpmClient bpmClient, ISession session, AppFixture fixture) {

		BusinessPartnerRequest bpr = new BusinessPartnerRequest();
		bpr.setBusinessPartnerCategory("2");
		bpr.getQuery().setTopCount(10);
		
		
		ExecutionResult result = bpmClient.start().subProcess(BpmElement.pid("181AFD80906674FB-f8"))//Processes/getHANA/getBusinessPartners.ivp
				.withParam("request", bpr).as().session(session).execute();
		
		
		Object obj = result.data().last();
		assertThat(obj).isInstanceOf(getHANAData.class);
		getHANAData jsonData = result.data().last();
		assertThat(jsonData).isNotNull();
		List<com.axon.market.s4hana.client.APIBUSINESSPARTNERABusinessPartnerType> bps = jsonData.getBusinessPartners();
		assertThat(bps).isNotNull();
		
		for(var bp :bps) {
			assertThat(bp.getBusinessPartnerCategory()).isEqualTo("2");
			assertThat(bp.getToBusinessPartnerAddress().getResults()).isNull();
		}
	}
	
	//partnersCategory2SelectFields - filter only business category = 2, top =10, select only a few fields	
	@Test
	void testPartnersCategory2SelectFields(BpmClient bpmClient, ISession session, AppFixture fixture) {

		BusinessPartnerRequest bpr = new BusinessPartnerRequest();
		bpr.setBusinessPartnerCategory("2");
		bpr.getQuery().setTopCount(10);
		bpr.getQuery().setSelect(Arrays.asList(SELECT_FIELDS_BUSINESS_PARTNER));
		
		
		ExecutionResult result = bpmClient.start().subProcess(BpmElement.pid("181AFD80906674FB-f8"))//Processes/getHANA/getBusinessPartners.ivp
				.withParam("request", bpr).as().session(session).execute();
				
		Object obj = result.data().last();
		assertThat(obj).isInstanceOf(getHANAData.class);
		getHANAData jsonData = result.data().last();
		assertThat(jsonData).isNotNull();
		List<com.axon.market.s4hana.client.APIBUSINESSPARTNERABusinessPartnerType> bps = jsonData.getBusinessPartners();
		assertThat(bps).isNotNull();
		
		for(var bp :bps) {
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
	
	//partnersManyOnlySelectFields = no filter applied , select only a few fields	
	@Test
	void testPartnersManyOnlySelectFields(BpmClient bpmClient, ISession session, AppFixture fixture) {

		BusinessPartnerRequest bpr = new BusinessPartnerRequest();
		bpr.getQuery().setSelect(Arrays.asList(SELECT_FIELDS_BUSINESS_PARTNER));
		
		
		ExecutionResult result = bpmClient.start().subProcess(BpmElement.pid("181AFD80906674FB-f8"))//Processes/getHANA/getBusinessPartners.ivp
				.withParam("request", bpr).as().session(session).execute();
				
		Object obj = result.data().last();
		assertThat(obj).isInstanceOf(getHANAData.class);
		getHANAData jsonData = result.data().last();
		assertThat(jsonData).isNotNull();
		List<com.axon.market.s4hana.client.APIBUSINESSPARTNERABusinessPartnerType> bps = jsonData.getBusinessPartners();
		assertThat(bps).isNotNull();
		
		for(var bp :bps) {
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
