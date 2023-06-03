package com.pbs.core.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "MOVIE")
public class Crud {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ID_MOVIE")
  @SequenceGenerator(name = "SEQ_ID_MOVIE", sequenceName = "SEQ_ID_MOVIE")
  private Long id;

  @Column(name = "NAME", length = 50, nullable = false)
  private String name;

}
