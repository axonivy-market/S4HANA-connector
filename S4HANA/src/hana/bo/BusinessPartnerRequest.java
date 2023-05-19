package hana.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessPartnerRequest {
	private int sapClient;// e.g. 200
	// filtering of input
	private String businessPartnerCategory;
	private String businessPartnerGrouping;
	private Date creationDateFrom;
	private Date creationDateTo;
	
	private Query query = new Query();
	
	public static class Query{
		// filtering of results
		private Integer topCount = 0;
		private Integer SkipFirstCount = 0;
		/**
		 * Sort the result list according to these columns.
		 * List should contain JSON Property names.
		 */
		private List<String> orderBy = new ArrayList<>();
		
		/**
		 * Expandable properties need to be loaded explicitly, or else only a "deferred"
		 * empty value will appear. These are the properties which have to be expanded 
		 * e.g. to_BuPaIdentification , to_BuPaIndustry , to_BusinessPartnerAddress , 
		 * to_BusinessPartnerBank , to_BusinessPartnerContact , to_BusinessPartnerRole
		 * to_BusinessPartnerTax , to_Customer , to_Supplier....
		 * 
		 * For example to load the 1st level field address and the email in it we need to expand it
		 * so result List would be : ["to_BusinessPartnerAddress/to_EmailAdress"]
		 */
		private List<String> expand = new ArrayList<>();
		/**
		 * In case the incoming data contains too many fields, we can limit the fields 
		 * to only some , each field needs to be specified by json property name, and we have
		 * to consider expanded fields also.
		 */
		private List<String> select = new ArrayList<>();
		/**
		 * @return the topCount
		 */
		public Integer getTopCount() {
			return topCount;
		}
		/**
		 * @param topCount the topCount to set
		 */
		public void setTopCount(Integer topCount) {
			this.topCount = topCount;
		}
		/**
		 * @return the skipFirstCount
		 */
		public Integer getSkipFirstCount() {
			return SkipFirstCount;
		}
		/**
		 * @param skipFirstCount the skipFirstCount to set
		 */
		public void setSkipFirstCount(Integer skipFirstCount) {
			SkipFirstCount = skipFirstCount;
		}
		/**
		 * @return the orderBy
		 */
		public List<String> getOrderBy() {
			return orderBy;
		}
		/**
		 * @param orderBy the orderBy to set
		 */
		public void setOrderBy(List<String> orderBy) {
			this.orderBy = orderBy;
		}
		/**
		 * @return the expand
		 */
		public List<String> getExpand() {
			return expand;
		}
		/**
		 * @param expand the expand to set
		 */
		public void setExpand(List<String> expand) {
			this.expand = expand;
		}
		/**
		 * @return the select
		 */
		public List<String> getSelect() {
			return select;
		}
		/**
		 * @param select the select to set
		 */
		public void setSelect(List<String> select) {
			this.select = select;
		}
		
		

	}
	// query definition end

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

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}
	

}
