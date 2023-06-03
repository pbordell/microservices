package com.pbs.quarkus.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pbs.quarkus.dto.CrudDTO;
import com.pbs.quarkus.service.CrudService;

@Path("items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CrudResource {

    @Inject
    CrudService crudService;

    @GET
    public List<CrudDTO> get(){
        return crudService.get();
    }

    @POST
    public void create(CrudDTO item){
        crudService.create(item);
    }

    @PUT
    public void update(CrudDTO item){
        crudService.update(item);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id){
        crudService.delete(id);
    }
}
