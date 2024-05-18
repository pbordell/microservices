package com.pbs.report.client;

import com.pbs.report.dto.CrudDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import java.util.List;

public class CoreCrudsClient {

	public CoreCrudsClient() {}

	public List<CrudDTO> getCrusAll() {
		Client client = ClientBuilder.newBuilder().newClient();
		WebTarget target = client.target("http://localhost:8080/CrudBasic");
		target = target.path("/cruds/");

		Invocation.Builder builder = target.request();
		return builder.get(List.class);
	}
}
