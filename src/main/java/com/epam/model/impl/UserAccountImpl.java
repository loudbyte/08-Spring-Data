package com.epam.model.impl;

import com.epam.model.User;
import com.epam.model.UserAccount;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user_account_table")
public class UserAccountImpl implements UserAccount {

  @Id
  @SequenceGenerator(
      name="user_account_table_id_seq",
      sequenceName="user_account_table_id_seq",
      allocationSize=1)
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator="user_account_table_id_seq"
  )
  @Column(name = "id", nullable = false)
  private Long id;
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
  private Long money;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(long id) {
    this.id = id;
  }

  @Override
  public User getUser() {
    return user;
  }

  @Override
  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public Long getMoney() {
    return money;
  }

  @Override
  public void setMoney(Long money) {
    this.money = money;
  }
}
