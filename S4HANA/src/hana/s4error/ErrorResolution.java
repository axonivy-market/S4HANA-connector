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
@JsonPropertyOrder({ "SAP_Transaction", "SAP_Note" })
@Generated("jsonschema2pojo")
public class ErrorResolution {

	@JsonProperty("SAP_Transaction")
	private String sAPTransaction;
	@JsonProperty("SAP_Note")
	private String sAPNote;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("SAP_Transaction")
	public String getSAPTransaction() {
		return sAPTransaction;
	}

	@JsonProperty("SAP_Transaction")
	public void setSAPTransaction(String sAPTransaction) {
		this.sAPTransaction = sAPTransaction;
	}

	@JsonProperty("SAP_Note")
	public String getSAPNote() {
		return sAPNote;
	}

	@JsonProperty("SAP_Note")
	public void setSAPNote(String sAPNote) {
		this.sAPNote = sAPNote;
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
