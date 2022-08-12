[Ivy]
181AFD80906674FB 9.3.1 #module
>Proto >Proto Collection #zClass
gA0 getHANA Big #zClass
gA0 B #cInfo
gA0 #process
gA0 @AnnotationInP-0n ai ai #zField
gA0 @TextInP .type .type #zField
gA0 @TextInP .processKind .processKind #zField
gA0 @TextInP .xml .xml #zField
gA0 @TextInP .responsibility .responsibility #zField
gA0 @StartSub f0 '' #zField
gA0 @EndSub f1 '' #zField
gA0 @RestClientCall f3 '' #zField
gA0 @PushWFArc f2 '' #zField
gA0 @ErrorBoundaryEvent f5 '' #zField
gA0 @PushWFArc f6 '' #zField
gA0 @PushWFArc f4 '' #zField
gA0 @RestClientCall f7 '' #zField
gA0 @StartSub f8 '' #zField
gA0 @EndSub f9 '' #zField
gA0 @PushWFArc f10 '' #zField
gA0 @ErrorBoundaryEvent f11 '' #zField
gA0 @PushWFArc f12 '' #zField
gA0 @PushWFArc f13 '' #zField
>Proto gA0 gA0 getHANA #zField
gA0 f0 inParamDecl '<hana.bo.BusinessPartnerRequest request> param;' #txt
gA0 f0 inParamTable 'out.businessPartnerRequest=param.request;
' #txt
gA0 f0 outParamDecl '<List<localhost.service_root.client.APIBUSINESSPARTNERABusinessPartnerType> businessPartners> result;' #txt
gA0 f0 outParamTable 'result.businessPartners=in.businessPartners;
' #txt
gA0 f0 callSignature getBusinessPartners(hana.bo.BusinessPartnerRequest) #txt
gA0 f0 @CG|tags connector #txt
gA0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getBusinessPartners(BusinessPartnerRequest)</name>
    </language>
</elementInfo>
' #txt
gA0 f0 81 49 30 30 -78 17 #rect
gA0 f1 545 49 30 30 0 15 #rect
gA0 f3 clientId 66c119a8-9209-4da5-814d-650f3c5a6c17 #txt
gA0 f3 path A_BusinessPartner #txt
gA0 f3 queryParams '%24filter=hana.url.URLBusinessPartnerService.generateURLFilter(in.businessPartnerRequest);
%24format="json";
' #txt
gA0 f3 templateParams 'URI=ivy.var.get("HANA_BUSINESS_PARTNER_URL");
' #txt
gA0 f3 method JAX_RS #txt
gA0 f3 clientCode 'import localhost.service_root.client.CollectionOfABusinessPartnerType;
import hana.converter.MessageConverter;
import hana.utils.JsonUtils;
import hana.utils.HttpStatusUtils;
import ch.ivyteam.ivy.bpm.error.BpmError;
import com.fasterxml.jackson.databind.JsonNode;
import javax.ws.rs.core.Response;
import hana.utils.Postman;
import hana.converter.MessageConverter;
import org.apache.http.HttpStatus;
import Postman;
import HttpStatusUtils;

// prepare client and request, set headers
Postman postman = Postman.build()
	.withTarget(client)
	.withS4Headers(in.businessPartnerRequest.sapClient);
	
//ivy.log.info("URI:"+client.getUri().getHost()+"|URI Port:"+client.getUri().getPort()+"|URI Path:"+client.getUri().getPath());

// execute request
Response response = postman.get();

// handle response
if(HttpStatusUtils.isSuccess(response)){
	JsonNode node = response.readEntity(JsonNode.class) as JsonNode;
	CollectionOfABusinessPartnerType collection = JsonUtils.jsonToObject(node.get("d").toString(), CollectionOfABusinessPartnerType.class) as CollectionOfABusinessPartnerType;
	in.businessPartners = collection.results;
} else if(HttpStatusUtils.isClientError(response) || HttpStatusUtils.isServerError(response)){
	in.messages = MessageConverter.convertBadRequestMessages(response);
} else{
  BpmError.create("ivy:error:rest:client").throwError();
}' #txt
gA0 f3 resultType List<hana.bo.BusinessPartner> #txt
gA0 f3 responseCode '/* empty due to JAX-RS request+response handling  */' #txt
gA0 f3 clientErrorCode ivy:error:rest:client #txt
gA0 f3 statusErrorCode ivy:error:rest:client #txt
gA0 f3 296 42 112 44 0 -8 #rect
gA0 f2 408 64 545 64 #arcP
gA0 f5 actionTable 'out=in;
out.messages=hana.converter.MessageConverter.convertToMessages(error);
' #txt
gA0 f5 attachedToRef 181AFD80906674FB-f3 #txt
gA0 f5 369 81 30 30 0 15 #rect
gA0 f6 399 96 545 64 #arcP
gA0 f6 1 496 96 #addKink
gA0 f6 2 512 64 #addKink
gA0 f6 1 0.7593949905686715 0 0 #arcLabel
gA0 f4 111 64 296 64 #arcP
gA0 f7 clientId 319c4c35-df80-4f51-b63e-ade0e8f60a9a #txt
gA0 f7 path /A_BusinessPartner #txt
gA0 f7 queryParams '$$top=;
$$skip=;
$$filter=hana.url.URLBusinessPartnerService.generateURLFilter(in.businessPartnerRequest);
$$inlinecount=;
$$orderby=;
$$select=;
$$expand=;
' #txt
gA0 f7 templateParams 'URI=ivy.var.get("HANA_BUSINESS_PARTNER_URL");
' #txt
gA0 f7 headers 'Accept=application/json;
sap-client=in.businessPartnerRequest.sapClient;
' #txt
gA0 f7 method GET #txt
gA0 f7 resultType com.axon.market.s4hana.client.Wrapper9 #txt
gA0 f7 responseMapping 'out.businessPartners=result.d.results as List;
' #txt
gA0 f7 clientErrorCode ivy:error:rest:client #txt
gA0 f7 statusErrorCode ivy:error:rest:client #txt
gA0 f7 296 170 112 44 0 -8 #rect
gA0 f8 inParamDecl '<hana.bo.BusinessPartnerRequest request> param;' #txt
gA0 f8 inParamTable 'out.businessPartnerRequest=param.request;
' #txt
gA0 f8 outParamDecl '<> result;' #txt
gA0 f8 callSignature getBusinessPartners_(hana.bo.BusinessPartnerRequest) #txt
gA0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getBusinessPartners(BusinessPartnerRequest)</name>
    </language>
</elementInfo>
' #txt
gA0 f8 81 177 30 30 -77 17 #rect
gA0 f9 545 177 30 30 0 15 #rect
gA0 f10 111 192 296 192 #arcP
gA0 f11 actionTable 'out=in;
out.messages=hana.converter.MessageConverter.convertToMessages(error);
' #txt
gA0 f11 attachedToRef 181AFD80906674FB-f7 #txt
gA0 f11 369 209 30 30 0 15 #rect
gA0 f12 399 224 545 192 #arcP
gA0 f12 1 480 224 #addKink
gA0 f12 2 496 192 #addKink
gA0 f12 0 0.9930408222885537 0 0 #arcLabel
gA0 f13 408 192 545 192 #arcP
>Proto gA0 .type com.axon.market.s4.hana.getHANAData #txt
>Proto gA0 .processKind CALLABLE_SUB #txt
>Proto gA0 0 0 32 24 18 0 #rect
>Proto gA0 @|BIcon #fIcon
gA0 f3 mainOut f2 tail #connect
gA0 f2 head f1 mainIn #connect
gA0 f5 mainOut f6 tail #connect
gA0 f6 head f1 mainIn #connect
gA0 f0 mainOut f4 tail #connect
gA0 f4 head f3 mainIn #connect
gA0 f8 mainOut f10 tail #connect
gA0 f10 head f7 mainIn #connect
gA0 f11 mainOut f12 tail #connect
gA0 f12 head f9 mainIn #connect
gA0 f7 mainOut f13 tail #connect
gA0 f13 head f9 mainIn #connect
