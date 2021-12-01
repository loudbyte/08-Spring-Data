package com.epam.model;

import java.io.Serializable;

public interface UserAccount extends Serializable {

  Long getId();
  void setId(long id);
  User getUser();
  void setUser(User user);
  Long getMoney();
  void setMoney(Long money);

}
