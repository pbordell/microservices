package com.pbs.core.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CRUD")
public class Crud {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ID_CRUD")
  @SequenceGenerator(name = "SEQ_ID_MOVIE", sequenceName = "SEQ_ID_CRUD")
  private Long id;

  @Column(name = "NAME", length = 50, nullable = false)
  private String name;

}
