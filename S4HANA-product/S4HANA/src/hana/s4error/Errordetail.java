package hana.s4error;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "code", "message", "longtext_url", "propertyref", "severity", "transition", "target" })
@Generated("jsonschema2pojo")
public class Errordetail {

	@JsonProperty("code")
	private String code;
	@JsonProperty("message")
	private String message;
	@JsonProperty("longtext_url")
	private String longtextUrl;
	@JsonProperty("propertyref")
	private String propertyref;
	@JsonProperty("severity")
	private String severity;
	@JsonProperty("transition")
	private Boolean transition;
	@JsonProperty("target")
	private String target;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty("longtext_url")
	public String getLongtextUrl() {
		return longtextUrl;
	}

	@JsonProperty("longtext_url")
	public void setLongtextUrl(String longtextUrl) {
		this.longtextUrl = longtextUrl;
	}

	@JsonProperty("propertyref")
	public String getPropertyref() {
		return propertyref;
	}

	@JsonProperty("propertyref")
	public void setPropertyref(String propertyref) {
		this.propertyref = propertyref;
	}

	@JsonProperty("severity")
	public String getSeverity() {
		return severity;
	}

	@JsonProperty("severity")
	public void setSeverity(String severity) {
		this.severity = severity;
	}

	@JsonProperty("transition")
	public Boolean getTransition() {
		return transition;
	}

	@JsonProperty("transition")
	public void setTransition(Boolean transition) {
		this.transition = transition;
	}

	@JsonProperty("target")
	public String getTarget() {
		return target;
	}

	@JsonProperty("target")
	public void setTarget(String target) {
		this.target = target;
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
