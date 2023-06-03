package com.pbs.core.service;

import java.util.List;

import com.pbs.core.model.Crud;

public interface CrudService {

  Crud getById(Long movieId);

  List<Crud> getAll();

  Crud create(Crud movie);

  Crud update(Crud movie);

  void delete(Long movieId);

  boolean isExist(Crud crud);
}
