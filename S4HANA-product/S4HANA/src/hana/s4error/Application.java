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
@JsonPropertyOrder({ "component_id", "service_namespace", "service_id", "service_version" })
@Generated("jsonschema2pojo")
public class Application {

	@JsonProperty("component_id")
	private String componentId;
	@JsonProperty("service_namespace")
	private String serviceNamespace;
	@JsonProperty("service_id")
	private String serviceId;
	@JsonProperty("service_version")
	private String serviceVersion;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("component_id")
	public String getComponentId() {
		return componentId;
	}

	@JsonProperty("component_id")
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	@JsonProperty("service_namespace")
	public String getServiceNamespace() {
		return serviceNamespace;
	}

	@JsonProperty("service_namespace")
	public void setServiceNamespace(String serviceNamespace) {
		this.serviceNamespace = serviceNamespace;
	}

	@JsonProperty("service_id")
	public String getServiceId() {
		return serviceId;
	}

	@JsonProperty("service_id")
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	@JsonProperty("service_version")
	public String getServiceVersion() {
		return serviceVersion;
	}

	@JsonProperty("service_version")
	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
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
