package com.pbs.core.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.pbs.core.model.Crud;
import com.pbs.core.repository.CrudRepository;
import com.pbs.core.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CrudServiceImpl implements CrudService {

  @Autowired private CrudRepository crudRepository;

  @Override
  public Crud getById(Long movieId) {
    return crudRepository.getById(movieId);
  }

  @Override
  public List<Crud> getAll() {
    return crudRepository.findAll();
  }

  @Override
  public Crud create(Crud movie) {
    return crudRepository.save(movie);
  }

  @Override
  public Crud update(Crud movie) {
    Crud movieAux = crudRepository.getOne(movie.getId());
    return crudRepository.save(movieAux);
  }

  @Override
  public void delete(Long movieId) {
    crudRepository.deleteById(movieId);
  }

  @Override
  public boolean isExist(Crud crud) {
    return crudRepository.findByName(crud.getName().trim()) != null;
  }
}
