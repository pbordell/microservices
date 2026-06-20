package com.pbs.quarkus.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.pbs.quarkus.dto.CrudDTO;
import com.pbs.quarkus.service.CrudService;

import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CrudResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    CrudService crudService;

    @GET
    public List<CrudDTO> get(){
        return crudService.get(jwt.getRawToken());
    }

    @POST
    public void create(CrudDTO item){
        crudService.create(jwt.getRawToken(), item);
    }

    @PUT
    public void update(CrudDTO item){
        crudService.update(jwt.getRawToken(), item);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id){
        crudService.delete(jwt.getRawToken(), id);
    }
}
