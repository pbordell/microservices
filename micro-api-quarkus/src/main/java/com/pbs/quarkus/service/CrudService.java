package com.pbs.quarkus.service;

import com.pbs.quarkus.client.CoreCrudsClient;
import com.pbs.quarkus.dto.CrudDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CrudService {

    private CoreCrudsClient coreCrudsClient = new CoreCrudsClient();

    public List<CrudDTO> get() {
        return coreCrudsClient.getCrusAll();
    }


    public void create(CrudDTO item) {
        coreCrudsClient.create(item);
    }


    public void update(CrudDTO item) {
        coreCrudsClient.update(item);
    }


    public void delete(Long id) {
        coreCrudsClient.delete(id);
    }
}
