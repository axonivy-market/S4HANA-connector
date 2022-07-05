package hana.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ch.ivyteam.ivy.environment.Ivy;

public class JsonUtils {

	private static final ObjectMapper OM = new ObjectMapper();
	static {
		// serialization features
		OM.disable(SerializationFeature.INDENT_OUTPUT);
		OM.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		OM.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		OM.setSerializationInclusion(Include.NON_NULL);

		// deserialization features
		OM.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		OM.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	private JsonUtils() {}

	public static String writeValueAsString(Object o) throws JsonProcessingException {
		return OM.writeValueAsString(o);
	}	
	
	public static <T> T jsonToObject(String json, Class<T> type) {
		T result = null;
		try {
			result = OM.readValue(json, type);
		} catch (JsonParseException e) {
			Ivy.log().error(e);
		} catch (JsonMappingException e) {
			Ivy.log().error(e);
		} catch (IOException e) {
			Ivy.log().error(e);
		}

		return result;

	}
	
	public static <T> List<T> jsonToObjects(String json, Class<T> type) {
		List<T> results = null;
		try {
			results = OM.readValue(json, new TypeReference<List<T>>() {
			});
		} catch (JsonParseException e) {
			Ivy.log().error(e);
		} catch (JsonMappingException e) {
			Ivy.log().error(e);
		} catch (IOException e) {
			Ivy.log().error(e);
		}
		return results;

	}
}
