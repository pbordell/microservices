package com.pbs.quarkus.client;

import com.pbs.quarkus.dto.CrudDTO;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;

import java.util.List;

public class CoreCrudsClient {

	public CoreCrudsClient() {
	}

	public List<CrudDTO> getCrusAll(String token) {
		Feature feature = OAuth2ClientSupport.feature(token);
		Client client = ClientBuilder.newBuilder().register(feature).newClient();
		WebTarget target = client.target("http://localhost:8082/CrudBasic");
		Invocation.Builder builder = target.path("/cruds/").request();
		return builder.get(List.class);
	}

	public void create(String token, CrudDTO item) {
		Feature feature = OAuth2ClientSupport.feature(token);
		Client client = ClientBuilder.newBuilder().register(feature).newClient();
		WebTarget target = client.target("http://localhost:8082/CrudBasic");
		Invocation.Builder builder = target.path("/cruds/create").request();
		builder.buildPost(Entity.entity(item, MediaType.APPLICATION_JSON));
	}

	public void update(String token, CrudDTO item) {
		Feature feature = OAuth2ClientSupport.feature(token);
		Client client = ClientBuilder.newBuilder().register(feature).newClient();
		WebTarget target = client.target("http://localhost:8082/CrudBasic");
		Invocation.Builder builder = target.path("/cruds/update").request();
		builder.buildPut(Entity.entity(item, MediaType.APPLICATION_JSON));
	}

	public void delete(String token, Long id) {
		Feature feature = OAuth2ClientSupport.feature(token);
		Client client = ClientBuilder.newBuilder().register(feature).newClient();
		WebTarget target = client.target("http://localhost:8082/CrudBasic");
		Invocation.Builder builder = target.path("/cruds/delete?id="+id).request();
		builder.buildDelete();
	}
}
