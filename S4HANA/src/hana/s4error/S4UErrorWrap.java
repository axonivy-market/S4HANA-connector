package hana.s4error;

import com.fasterxml.jackson.annotation.JsonProperty;

public class S4UErrorWrap {
	@JsonProperty("error")
	private Error error;

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

}
