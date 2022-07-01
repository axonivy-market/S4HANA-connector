package hana.url;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class URLFilterGeneration {

	public static final String DATETIME_PREFIX = "datetime";
	public static final String DATETIME_OFFSET_PREFIX = "datetimeoffset";
	public static final String QUOTE_VALUES = "'";
	public static final String OPENING_PARENTHESIS = "(";
	public static final String CLOSING_PARENTHESIS = ")";
	
	
	private JoinOperator joinOperator;
	private List<URLComparison> comparisons;

	public enum JoinOperator {
		and, 
		or
	}

	private URLFilterGeneration(JoinOperator joinOperator) {
		this.joinOperator = joinOperator;
	}

	public static URLFilterGeneration joinWith(JoinOperator joinOperator) {
		return new URLFilterGeneration(joinOperator);
	}

	public URLFilterGeneration addComparison(String comparisonKey, Date comparisonDateValue, URLComparisonOperator comparisonOperator) {
		return addComparison(comparisonKey, comparisonDateValue, comparisonOperator, DATETIME_PREFIX);
	}
	
	public URLFilterGeneration addComparison(String comparisonKey, Date comparisonDateValue, URLComparisonOperator comparisonOperator, String dateTimePrefix) {
		String comparisonValue = (new StringBuilder()).append(dateTimePrefix).append("'")
				.append(asIsoLocalDateTime(comparisonDateValue)).append("'").toString();
		return addComparison(comparisonKey,comparisonValue, comparisonOperator);
	}
	
	private static String asIsoLocalDateTime(Date date) {
		return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(date.toInstant().atZone(ZoneId.systemDefault()));
	}
	
	public URLFilterGeneration addComparison(String comparisonKey, String comparisonValue, URLComparisonOperator comparisonOperator) {
		if (CollectionUtils.isEmpty(comparisons)) {
			comparisons = new ArrayList<>();
		}
		if (StringUtils.isNotBlank(comparisonKey) && comparisonOperator != null) {
			comparisons.add(new URLComparison(comparisonKey, comparisonValue, comparisonOperator));
		}

		return this;
	}

	public URLFilterGeneration addComparisonSubStringOf(String comparisonKey, String comparisonValue, URLComparisonOperator comparisonOperator, URLComparisonOperator comparisonOperator2, String comparisonValue2) {
		if (CollectionUtils.isEmpty(comparisons)) {
			comparisons = new ArrayList<>();
		}
		if (StringUtils.isNotBlank(comparisonKey) && comparisonOperator != null && StringUtils.isNotBlank(comparisonValue2) && comparisonOperator2 != null) {
			comparisons.add(new URLComparison(comparisonKey, comparisonValue, comparisonOperator, comparisonOperator2, comparisonValue2));
		}
		
		return this;
	}

	public String generateFilter() {
		StringBuilder filter = new StringBuilder(StringUtils.EMPTY);
		if (CollectionUtils.isEmpty(comparisons)) {
			return filter.toString();
		}
		filter.append(OPENING_PARENTHESIS);
		for (URLComparison comparison : comparisons) {
			// append join operator and|or
			if (this.joinOperator != null && comparisons.size() > 1 && comparisons.indexOf(comparison) > 0) {
				filter.append(StringUtils.SPACE).append(this.joinOperator.name()).append(StringUtils.SPACE);
			}
			if(comparison.getOperator().isMethodTypeSubStringOf()) {
				filter.append(comparison.getOperator().getComparisonOperator())
				.append("('").append(comparison.getValue()).append("',").append(comparison.getKey()).append(")");
				if (comparison.getOperator2() != null && StringUtils.isNotEmpty(comparison.getValue2())) {
					filter.append(StringUtils.SPACE).append(comparison.getOperator2().getComparisonOperator()).append(StringUtils.SPACE)
					.append(comparison.getValue2());
				}
			}
			else if (comparison.getOperator().isMethodType()) {
				filter.append(comparison.getOperator().getComparisonOperator())
				.append("(").append(comparison.getKey()).append(",'").append(comparison.getValue()).append("')");
			} 
			else if (comparison.getOperator().isMethodArithmeticComparison()) {
				filter.append(comparison.getKey())
				.append(StringUtils.SPACE).append(comparison.getOperator().getComparisonOperator()).append(StringUtils.SPACE)
				.append(comparison.getValue());
			} else {
				filter.append(comparison.getKey())
				.append(StringUtils.SPACE).append(comparison.getOperator().getComparisonOperator()).append(StringUtils.SPACE)
				.append("'").append(comparison.getValue()).append("'");
			}
		}
		filter.append(CLOSING_PARENTHESIS);
		return filter.toString();
	}
	
	public static String getQuotedValue(String value) {
		StringBuilder filter = new StringBuilder(StringUtils.EMPTY);
		filter.append(QUOTE_VALUES);
		if(StringUtils.isNotEmpty(value)) {
			filter.append(value);
		}
		filter.append(QUOTE_VALUES);
		return filter.toString();
	}
	
	public JoinOperator getJoinOperator() {
		return joinOperator;
	}

	public void setJoinOperator(JoinOperator joinOperator) {
		this.joinOperator = joinOperator;
	}

	public List<URLComparison> getComparisons() {
		return comparisons;
	}

	public void setComparisons(List<URLComparison> comparisons) {
		this.comparisons = comparisons;
	}
}
