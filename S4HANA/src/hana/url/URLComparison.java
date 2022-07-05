package hana.url;

public class URLComparison {

	private String key;
	private String value;
	private URLComparisonOperator operator;
	private URLComparisonOperator operator2;
	private String value2;

	public URLComparison(String comparisonKey, String comparisonValue, URLComparisonOperator comparisonOperator) {
		this.key = comparisonKey;
		this.value = comparisonValue;
		this.operator = comparisonOperator;
	}
	
	public URLComparison(String comparisonKey, String comparisonValue, URLComparisonOperator comparisonOperator, URLComparisonOperator comparisonOperator2, String comparisonValue2) {
		this.key = comparisonKey;
		this.value = comparisonValue;
		this.operator = comparisonOperator;
		this.operator2 = comparisonOperator2;
		this.value2 = comparisonValue2;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public URLComparisonOperator getOperator() {
		return operator;
	}

	public void setOperator(URLComparisonOperator operator) {
		this.operator = operator;
	}

	public URLComparisonOperator getOperator2() {
		return operator2;
	}

	public void setOperator2(URLComparisonOperator operator2) {
		this.operator2 = operator2;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

}
