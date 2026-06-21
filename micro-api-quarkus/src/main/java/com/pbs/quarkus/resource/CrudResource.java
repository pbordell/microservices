package com.pbs.quarkus.resource;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.pbs.quarkus.dto.CrudDTO;
import com.pbs.quarkus.service.CrudService;

@Path("items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CrudResource {

    @Inject
    CrudService crudService;

    @GET
    public List<CrudDTO> get(@HeaderParam("Authorization") String authHeader) {
        return crudService.get(authHeader);
    }

    @POST
    public void create(@HeaderParam("Authorization") String authHeader, CrudDTO item) {
        crudService.create(authHeader, item);
    }

    @PUT
    public void update(@HeaderParam("Authorization") String authHeader, CrudDTO item) {
        crudService.update(authHeader, item);
    }

    @DELETE
    @Path("{id}")
    public void delete(@HeaderParam("Authorization") String authHeader, @PathParam("id") Long id) {
        crudService.delete(authHeader, id);
    }
}
