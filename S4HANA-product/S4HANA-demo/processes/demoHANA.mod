[Ivy]
181AFDBBCB8F15B9 9.3.1 #module
>Proto >Proto Collection #zClass
dA0 demoHANA Big #zClass
dA0 B #cInfo
dA0 #process
dA0 @AnnotationInP-0n ai ai #zField
dA0 @TextInP .type .type #zField
dA0 @TextInP .processKind .processKind #zField
dA0 @TextInP .xml .xml #zField
dA0 @TextInP .responsibility .responsibility #zField
dA0 @StartRequest f0 '' #zField
dA0 @EndTask f1 '' #zField
dA0 @CallSub f3 '' #zField
dA0 @PushWFArc f4 '' #zField
dA0 @GridStep f5 '' #zField
dA0 @PushWFArc f6 '' #zField
dA0 @PushWFArc f2 '' #zField
>Proto dA0 dA0 demoHANA #zField
dA0 f0 outLink start.ivp #txt
dA0 f0 inParamDecl '<> param;' #txt
dA0 f0 requestEnabled true #txt
dA0 f0 triggerEnabled false #txt
dA0 f0 callSignature start() #txt
dA0 f0 caseData businessCase.attach=true #txt
dA0 f0 @CG|tags demo #txt
dA0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
dA0 f0 @C|.responsibility Everybody #txt
dA0 f0 81 49 30 30 -20 17 #rect
dA0 f1 513 49 30 30 0 15 #rect
dA0 f3 processCall getHANA:getBusinessPartners(hana.bo.BusinessPartnerRequest) #txt
dA0 f3 requestActionDecl '<hana.bo.BusinessPartnerRequest request> param;' #txt
dA0 f3 requestMappingAction 'param.request.sapClient=0;
' #txt
dA0 f3 responseMappingAction 'out=in;
out.businessPartners=in.businessPartners;
' #txt
dA0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getBusinessPartners</name>
    </language>
</elementInfo>
' #txt
dA0 f3 160 42 128 44 -55 -8 #rect
dA0 f4 111 64 160 64 #arcP
dA0 f5 actionTable 'out=in;
' #txt
dA0 f5 actionCode 'for (int i=0;i<in.businessPartners.size();i++) {
	ivy.log.info("BusinessPartners("+i+"):"+in.businessPartners.get(i).getBusinessPartnerFullName());
}
' #txt
dA0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Log BusinessPartners</name>
    </language>
</elementInfo>
' #txt
dA0 f5 336 42 128 44 -58 -8 #rect
dA0 f6 288 64 336 64 #arcP
dA0 f2 464 64 513 64 #arcP
>Proto dA0 .type com.axon.market.s4.hana.demo.demoHANAData #txt
>Proto dA0 .processKind NORMAL #txt
>Proto dA0 0 0 32 24 18 0 #rect
>Proto dA0 @|BIcon #fIcon
dA0 f0 mainOut f4 tail #connect
dA0 f4 head f3 mainIn #connect
dA0 f3 mainOut f6 tail #connect
dA0 f6 head f5 mainIn #connect
dA0 f5 mainOut f2 tail #connect
dA0 f2 head f1 mainIn #connect
