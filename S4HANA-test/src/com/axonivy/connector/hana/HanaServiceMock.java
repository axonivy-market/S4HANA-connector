package com.axonivy.connector.hana;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.v3.oas.annotations.Hidden;

@Path(HanaServiceMock.PATH_SUFFIX)
@PermitAll
@Hidden
@SuppressWarnings("unused")
public class HanaServiceMock {
	static final String PATH_SUFFIX = "hanaMock";
	// URI where this mock can be reached: to be referenced in tests that use it!
	public static final String URI = "{ivy.app.baseurl}/api/" + PATH_SUFFIX;
	
	@GET
	@Path("A_BusinessPartner")
	@Produces(MediaType.APPLICATION_JSON)
	public Response A_BusinessPartner(						
			@QueryParam("$top") Integer top,
			@QueryParam("$skip") Integer end,
			@QueryParam("$filter") String filter,
			@QueryParam("$inlinecount") String inlinecount,
			@QueryParam("$orderBy") List<String> orderBy,
			@QueryParam("$expand") List<String> expand,
			@QueryParam("$select") List<String> select
			)
	{
		
		if(top == 10 &&
				end == 0 &&
				filter.equals("(BusinessPartnerCategory eq '2')") &&
				inlinecount.equals("allpages") &&
				orderBy.isEmpty() &&
				expand.get(0).equals("to_BusinessPartnerAddress/to_EmailAddress") &&
				select.get(0).equals("")
			) 
		{
			return Response.status(200)
		    	      .entity(load("json/partnersCategory2Expand.json"))
		    	      .build();
		}
		
		if(top == 10 &&
				end == 0 &&
				filter.equals("(BusinessPartnerCategory eq '2')") &&
				inlinecount.equals("allpages") &&
				orderBy.isEmpty() &&
				expand.get(0).equals("") &&
				select.get(0).equals("")
			) 
		{
			return Response.status(200)
		    	      .entity(load("json/partnersCategory2NOExpand.json"))
		    	      .build();
		}
		
		if(top == 10 &&
				end == 0 &&
				filter.equals("(BusinessPartnerCategory eq '2')") &&
				inlinecount.equals("allpages") &&
				orderBy.isEmpty() &&
				expand.get(0).equals("") &&
				select.get(0).equals("BusinessPartnerCategory,BusinessPartnerFullName,BusinessPartner")
			) 
		{
			return Response.status(200)
		    	      .entity(load("json/partnersCategory2SelectFields.json"))
		    	      .build();
		}
		
		if(top == 0 &&
				end == 0 &&
				filter.equals("") &&
				inlinecount.equals("allpages") &&
				orderBy.isEmpty() &&
				expand.get(0).equals("") &&
				select.get(0).equals("BusinessPartnerCategory,BusinessPartnerFullName,BusinessPartner")
			) 
		{
			return Response.status(200)
		    	      .entity(load("json/partnersManyOnlySelectFields.json"))
		    	      .build();
		}				
	    return null;
	}		
	
	private static String load(String path) {
		try (InputStream is = HanaServiceMock.class.getResourceAsStream(path)) {
			return IOUtils.toString(is, StandardCharsets.UTF_8);
		} catch (IOException ex) {
			throw new RuntimeException("Failed to read resource: " + path);
		}
	}
}