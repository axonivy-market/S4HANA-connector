package hana.utils;

import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class Postman {

	private static Postman postman;
	private Invocation.Builder request;
	private static final String CONNECT_TIMEOUT_PROPERTY = "jersey.config.client.connectTimeout";
	public static final int CONNECT_TIMEOUT_VALUE = 30000;

	public static Postman build() {

		if (postman == null) {
			postman = new Postman();
		}

		return postman;
	}

	public Postman withTarget(WebTarget target) {
		target.property(CONNECT_TIMEOUT_PROPERTY, CONNECT_TIMEOUT_VALUE);

		this.request = target.request();
		return postman;
	}

	public Postman withS4Headers(int sapClient) {
		request.header("Accept", "application/json");
		request.header("sap-client", sapClient);
		//request.header("x-csrf-token", ...);
		//request.header("Cookie", ...);
		//request.header("sap-language", "DE");

		return postman;
	}

	public Postman withHeader(String name, String value) {
		request.header(name, value);
		return postman;
	}

	public Postman withHeader(Map<String, String> headers) {
		headers.forEach((key, value) -> {
			this.request.header(key, value);
		});

		return postman;
	}

	public Response get() {
		return postman.request.get();
	}

	public Response post(Object object) {
		return postman.request.post(Entity.json(object));
	}

	public Response post(String json) {
		return postman.request.post(Entity.json(json));
	}

	public Response put(String json) {
		return postman.request.put(Entity.json(json));
	}

}
