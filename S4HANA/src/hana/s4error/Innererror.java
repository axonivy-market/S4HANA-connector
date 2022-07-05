package hana.s4error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "application", "transactionid", "timestamp", "Error_Resolution", "errordetails" })
@Generated("jsonschema2pojo")
public class Innererror {

	@JsonProperty("application")
	private Application application;
	@JsonProperty("transactionid")
	private String transactionid;
	@JsonProperty("timestamp")
	private String timestamp;
	@JsonProperty("Error_Resolution")
	private ErrorResolution errorResolution;
	@JsonProperty("errordetails")
	private List<Errordetail> errordetails = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("application")
	public Application getApplication() {
		return application;
	}

	@JsonProperty("application")
	public void setApplication(Application application) {
		this.application = application;
	}

	@JsonProperty("transactionid")
	public String getTransactionid() {
		return transactionid;
	}

	@JsonProperty("transactionid")
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	@JsonProperty("timestamp")
	public String getTimestamp() {
		return timestamp;
	}

	@JsonProperty("timestamp")
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@JsonProperty("Error_Resolution")
	public ErrorResolution getErrorResolution() {
		return errorResolution;
	}

	@JsonProperty("Error_Resolution")
	public void setErrorResolution(ErrorResolution errorResolution) {
		this.errorResolution = errorResolution;
	}

	@JsonProperty("errordetails")
	public List<Errordetail> getErrordetails() {
		return errordetails;
	}

	@JsonProperty("errordetails")
	public void setErrordetails(List<Errordetail> errordetails) {
		this.errordetails = errordetails;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
