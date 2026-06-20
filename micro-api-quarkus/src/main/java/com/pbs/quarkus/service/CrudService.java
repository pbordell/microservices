package com.pbs.quarkus.service;

import com.pbs.quarkus.client.CoreCrudsClient;
import com.pbs.quarkus.dto.CrudDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import java.util.List;

@ApplicationScoped
public class CrudService {

    @Inject
    @RestClient
    CoreCrudsClient coreCrudsClient;

    public List<CrudDTO> get(String token) {
        return coreCrudsClient.getCrusAll(token);
    }

    public void create(String token, CrudDTO item) {
        coreCrudsClient.create(token, item);
    }

    public void update(String token, CrudDTO item) {
        coreCrudsClient.update(token, item);
    }

    public void delete(String token, Long id) {
        coreCrudsClient.delete(token, id);
    }
}
