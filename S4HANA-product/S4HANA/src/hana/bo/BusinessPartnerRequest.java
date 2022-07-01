package hana.bo;

import java.util.Date;

public class BusinessPartnerRequest {

	private int sapClient;
	private String businessPartnerCategory;
	private String businessPartnerGrouping;
	private Date creationDateFrom;
	private Date creationDateTo;
	
	public int getSapClient() {
		return sapClient;
	}

	public void setSapClient(int sapClient) {
		this.sapClient = sapClient;
	}
	
	public String getBusinessPartnerCategory() {
		return businessPartnerCategory;
	}

	public String getBusinessPartnerGrouping() {
		return businessPartnerGrouping;
	}

	public void setBusinessPartnerGrouping(String businessPartnerGrouping) {
		this.businessPartnerGrouping = businessPartnerGrouping;
	}

	public Date getCreationDateFrom() {
		return creationDateFrom;
	}

	public void setCreationDateFrom(Date creationDateFrom) {
		this.creationDateFrom = creationDateFrom;
	}

	public Date getCreationDateTo() {
		return creationDateTo;
	}

	public void setCreationDateTo(Date creationDateTo) {
		this.creationDateTo = creationDateTo;
	}

	public void setBusinessPartnerCategory(String businessPartnerCategory) {
		this.businessPartnerCategory = businessPartnerCategory;
	}

}
