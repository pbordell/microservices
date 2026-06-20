package com.pbs.quarkus.client;

import com.pbs.quarkus.dto.CrudDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

// Registramos la interfaz para que Quarkus la implemente por nosotros
@RegisterRestClient(configKey = "gateway-api")
@Path("/CrudBasic/cruds") // Unificamos el path base que tenías mapeado
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface CoreCrudsClient {

	@GET
	@Path("/")
	List<CrudDTO> getCrusAll(@HeaderParam("Authorization") String token);

	@POST
	@Path("/create")
	void create(@HeaderParam("Authorization") String token, CrudDTO item);

	@PUT
	@Path("/update")
	void update(@HeaderParam("Authorization") String token, CrudDTO item);

	@DELETE
	@Path("/delete")
	void delete(@HeaderParam("Authorization") String token, @QueryParam("id") Long id);
}
