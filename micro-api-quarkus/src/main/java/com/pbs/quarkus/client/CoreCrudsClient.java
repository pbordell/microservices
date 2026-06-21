package com.pbs.quarkus.client;

import com.pbs.quarkus.dto.CrudDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

@RegisterRestClient(configKey = "gateway-api")
@Path("/core-api/CrudBasic/cruds")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface CoreCrudsClient {

	@GET
	@Path("/")
	List<CrudDTO> getCrusAll(@HeaderParam("Authorization") String token);

	@POST
	@Path("/")
	void create(@HeaderParam("Authorization") String token, CrudDTO item);

	@PUT
	@Path("/")
	void update(@HeaderParam("Authorization") String token, CrudDTO item);

	@DELETE
	@Path("/{id}")
	void delete(@HeaderParam("Authorization") String token, @PathParam("id") Long id);
}
