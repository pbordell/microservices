package com.pbs.report.client;

import com.pbs.report.dto.CrudDTO;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Feature;
import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;

import java.util.List;

public class CoreCrudsClient {

	public CoreCrudsClient() {}

	public List<CrudDTO> getCrusAll(String token) {
		Feature feature = OAuth2ClientSupport.feature(token);
		Client client = ClientBuilder.newBuilder().register(feature).newClient();
		WebTarget target = client.target("http://localhost:8082/CrudBasic");
		target = target.path("/cruds/");

		Invocation.Builder builder = target.request();
		return builder.get(List.class);
	}
}
