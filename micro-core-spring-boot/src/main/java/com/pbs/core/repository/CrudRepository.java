package com.pbs.core.repository;

import com.pbs.core.model.Crud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudRepository extends JpaRepository<Crud, Long> {

  Page<Crud> findAll(Specification<Crud> spec, Pageable pageable);

  Crud findByName(String name);
}
