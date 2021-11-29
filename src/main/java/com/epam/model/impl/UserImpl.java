package com.epam.model.impl;

import com.epam.model.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user_table")
public class UserImpl implements User {

  @Id
  @SequenceGenerator(
      name="user_table_id_seq",
      sequenceName="user_table_id_seq",
      allocationSize=1)
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator="user_table_id_seq"
  )
  @Column(name = "id", nullable = false)
  private Long id;
  private String name;
  private String email;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;
  }
}
