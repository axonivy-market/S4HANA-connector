package hana.url;

import java.util.Arrays;
import java.util.List;

public enum URLComparisonOperator {

	EQUAL("eq"),

	NOT_EQUAL("ne"),

	LESS_THAN_OR_EQUAL("le"),

	LESS_THAN("lt"),

	GREATER_THAN_OR_EQUAL("ge"),

	GREATER_THAN("gt"),

	SUBSTRINGOF("substringof"),

	STARTSWITH("startswith"),
	
	NOT_STARTSWITH("not startswith"),

	ENDSWITH("endswith");

	private final String comparisonOperator;

	URLComparisonOperator(String comparisonOperator) {
		this.comparisonOperator = comparisonOperator;
	}

	private static final List<URLComparisonOperator> METHODS = Arrays.asList(
			URLComparisonOperator.STARTSWITH,
			URLComparisonOperator.ENDSWITH,
			URLComparisonOperator.NOT_STARTSWITH);
	
	private static final List<URLComparisonOperator> ARITHMETIC_COMPARISONS = Arrays.asList(
			URLComparisonOperator.LESS_THAN_OR_EQUAL,
			URLComparisonOperator.LESS_THAN,
			URLComparisonOperator.GREATER_THAN_OR_EQUAL,
			URLComparisonOperator.GREATER_THAN);

	public boolean isMethodType() {
		return METHODS.contains(this);
	}
	
	public boolean isMethodTypeSubStringOf() {
		return SUBSTRINGOF == this;
	}

	public boolean isMethodArithmeticComparison() {
		return ARITHMETIC_COMPARISONS.contains(this);
	}
	
	public String getComparisonOperator() {
		return comparisonOperator;
	}
}
