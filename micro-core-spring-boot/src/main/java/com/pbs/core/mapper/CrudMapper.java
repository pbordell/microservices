package com.pbs.core.mapper;

import java.io.IOException;
import java.util.List;

import com.pbs.core.dto.CrudDTO;
import com.pbs.core.model.Crud;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CrudMapper {

  Crud fromDTO(CrudDTO source);

  CrudDTO toDTO(Crud source);

  List<CrudDTO> toListDTO(List<Crud> source);
}
