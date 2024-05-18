package com.pbs.quarkus.client;

import com.pbs.quarkus.dto.CrudDTO;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class CoreCrudsClient {

	private WebTarget target;

	public CoreCrudsClient() {
		Client client = ClientBuilder.newBuilder().newClient();
		target = client.target("http://localhost:8080/CrudBasic");
	}

	public List<CrudDTO> getCrusAll() {
		Invocation.Builder builder = target.path("/cruds/").request();
		return builder.get(List.class);
	}

	public void create(CrudDTO item) {
		Invocation.Builder builder = target.path("/cruds/create").request();
		builder.buildPost(Entity.entity(item, MediaType.APPLICATION_JSON));
	}

	public void update(CrudDTO item) {
		Invocation.Builder builder = target.path("/cruds/update").request();
		builder.buildPut(Entity.entity(item, MediaType.APPLICATION_JSON));
	}

	public void delete(Long id) {
		Invocation.Builder builder = target.path("/cruds/delete?id="+id).request();
		builder.buildDelete();
	}
}
