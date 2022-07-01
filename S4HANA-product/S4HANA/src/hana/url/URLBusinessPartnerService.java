package hana.url;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import hana.bo.BusinessPartnerRequest;
import hana.url.URLFilterGeneration.JoinOperator;

public class URLBusinessPartnerService {

	public static final String BUSINESS_PARTNER_CATEGORY = "BusinessPartnerCategory";
	public static final String BUSINESS_PARTNER_GROUPING = "BusinessPartnerGrouping";
	public static final String CREATION_DATE = "CreationDate";

	public static String generateURLFilter(final BusinessPartnerRequest contractSearch) {
		if (Objects.isNull(contractSearch)) {
			return StringUtils.EMPTY;
		}

		final URLFilterGeneration ufg = URLFilterGeneration.joinWith(JoinOperator.and);
		
		// filters based on (BusinessPartnerCategory eq 'BUSINESS_PARTNER_CATEGRORY')
		if(StringUtils.isNotBlank(contractSearch.getBusinessPartnerCategory())) {
			ufg.addComparison(BUSINESS_PARTNER_CATEGORY, contractSearch.getBusinessPartnerCategory(), URLComparisonOperator.EQUAL);
		}
		
		// filters based on (BusinessPartnerGrouping eq 'BUSINESS_PARTNER_GROUPING')
		if(StringUtils.isNotBlank(contractSearch.getBusinessPartnerGrouping())) {
			ufg.addComparison(BUSINESS_PARTNER_GROUPING, contractSearch.getBusinessPartnerGrouping(), URLComparisonOperator.EQUAL);
		}

		// filters based on (CreationDate ge 'CREATEION_DATE_FROM')
		if (Objects.nonNull(contractSearch.getCreationDateFrom())) {
			ufg.addComparison(CREATION_DATE, contractSearch.getCreationDateFrom(), URLComparisonOperator.GREATER_THAN_OR_EQUAL);
		}

		// filters based on (CreationDate le 'CREATION_DATE_TO')
		if (Objects.nonNull(contractSearch.getCreationDateFrom())) {
			ufg.addComparison(CREATION_DATE, contractSearch.getCreationDateTo(), URLComparisonOperator.LESS_THAN_OR_EQUAL);
		}

		return ufg.generateFilter();
	}

}