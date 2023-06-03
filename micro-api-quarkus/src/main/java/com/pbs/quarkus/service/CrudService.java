package com.pbs.quarkus.service;

import com.pbs.quarkus.dto.CrudDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CrudService {
    public List<CrudDTO> get() {
        return null;
    }


    public void create(CrudDTO item) {
    }


    public void update(CrudDTO item) {
    }


    public void delete(Long id) {
    }
}
