package com.pbs.core.controller;

import com.pbs.core.dto.CrudDTO;
import com.pbs.core.mapper.CrudMapper;
import com.pbs.core.model.Crud;
import com.pbs.core.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("CrudBasic")
public class CrudController {

  @Autowired private CrudService crudService;

  @Autowired private CrudMapper crudMapper;


  @GetMapping(value = "/cruds/")
  public ResponseEntity<List<CrudDTO>> listAll() {
    List<Crud> cruds = crudService.getAll();
    if (cruds.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(crudMapper.toListDTO(cruds), HttpStatus.OK);
  }

  // -------------------get Single --------------------------------------------------------

  @GetMapping(value = "/crud/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
  public ResponseEntity<CrudDTO> getById(@PathVariable("id") long id) {
    CrudDTO crudDTO = crudMapper.toDTO(crudService.getById(id));
    if (crudDTO == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(crudDTO, HttpStatus.OK);
  }

  // ------------------- Create --------------------------------------------------------

  @PostMapping(value = "/crud/")
  public ResponseEntity<CrudDTO> create(@RequestBody CrudDTO crudDTO) throws IOException {

    if (crudService.isExist(crudMapper.fromDTO(crudDTO))) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    return new ResponseEntity<>(crudMapper.toDTO(crudService.create(crudMapper.fromDTO(crudDTO))),
            HttpStatus.CREATED);
  }

  // ------------------- Update --------------------------------------------------------

  @PutMapping(value = "/crud/{id}")
  public ResponseEntity<CrudDTO> update(@PathVariable("id") long id, @RequestBody CrudDTO crudDTO) throws IOException {

    if (crudService.getById(id) == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(crudMapper.toDTO(crudService.update(crudMapper.fromDTO(crudDTO))),
            HttpStatus.OK);
  }

  // ------------------- Delete --------------------------------------------------------

  @DeleteMapping(value = "/crud/{id}")
  public ResponseEntity delete(@PathVariable("id") long id) {
    if (crudService.getById(id) == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    crudService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
