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
gA0 @PushWFArc f4 '' #zField
gA0 @PushWFArc f2 '' #zField
gA0 @ErrorBoundaryEvent f5 '' #zField
gA0 @PushWFArc f6 '' #zField
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
gA0 f0 81 49 30 30 -76 15 #rect
gA0 f1 465 49 30 30 0 15 #rect
gA0 f3 clientId 66c119a8-9209-4da5-814d-650f3c5a6c17 #txt
gA0 f3 path A_BusinessPartner #txt
gA0 f3 queryParams '%24filter=hana.url.URLBusinessPartnerService.generateURLFilter(in.businessPartnerRequest);
%24format="json";
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
gA0 f4 111 64 296 64 #arcP
gA0 f2 408 64 465 64 #arcP
gA0 f5 actionTable 'out=in;
out.messages=hana.converter.MessageConverter.convertToMessages(error);
' #txt
gA0 f5 attachedToRef 181AFD80906674FB-f3 #txt
gA0 f5 369 81 30 30 0 15 #rect
gA0 f6 399 96 465 64 #arcP
gA0 f6 1 416 96 #addKink
gA0 f6 2 432 64 #addKink
gA0 f6 1 0.774026969403047 0 0 #arcLabel
>Proto gA0 .type com.axon.market.s4.hana.getHANAData #txt
>Proto gA0 .processKind CALLABLE_SUB #txt
>Proto gA0 0 0 32 24 18 0 #rect
>Proto gA0 @|BIcon #fIcon
gA0 f0 mainOut f4 tail #connect
gA0 f4 head f3 mainIn #connect
gA0 f3 mainOut f2 tail #connect
gA0 f2 head f1 mainIn #connect
gA0 f5 mainOut f6 tail #connect
gA0 f6 head f1 mainIn #connect
