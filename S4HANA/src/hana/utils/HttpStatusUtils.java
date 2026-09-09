package hana.utils;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status.Family;

public class HttpStatusUtils {
	
	public static boolean isSuccess(Response response) {
		return response.getStatusInfo().getFamily() == Family.SUCCESSFUL;
	}

	public static boolean isClientError(Response response) {
		return response.getStatusInfo().getFamily() == Family.CLIENT_ERROR;
	}

	public static boolean isServerError(Response response) {
		return response.getStatusInfo().getFamily() == Family.SERVER_ERROR;
	}
}
