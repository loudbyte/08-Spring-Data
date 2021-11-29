package com.epam.dao.impl;

import com.epam.dao.UserDao;
import com.epam.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractHibernateDao<User> implements UserDao {

  public UserDaoImpl() {
    super(User.class);
  }
}
