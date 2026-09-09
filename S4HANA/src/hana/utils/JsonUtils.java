package hana.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ch.ivyteam.ivy.environment.Ivy;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.json.JsonMapper;

public class JsonUtils {

	private static final ObjectMapper OM = JsonMapper.builder()
			.disable(SerializationFeature.INDENT_OUTPUT)
			.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
			.disable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS)
			.changeDefaultPropertyInclusion(inclusion -> inclusion.withValueInclusion(Include.NON_NULL))
			.changeDefaultPropertyInclusion(inclusion -> inclusion.withContentInclusion(Include.NON_NULL))
			.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.build();

	private JsonUtils() {}

	public static String writeValueAsString(Object o) throws JacksonException {
		return OM.writeValueAsString(o);
	}	
	
	public static <T> T jsonToObject(String json, Class<T> type) {
		T result = null;
		try {
			result = OM.readValue(json, type);
		} catch (JacksonException e) {
			Ivy.log().error(e);
		}

		return result;

	}
	
	public static <T> List<T> jsonToObjects(String json, Class<T> type) {
		List<T> results = null;
		try {
			results = OM.readValue(json, new TypeReference<List<T>>() {
			});
		} catch (JacksonException e) {
			Ivy.log().error(e);
		}
		return results;

	}
}
