package com.epam.dao.impl;

import com.epam.dao.UserDao;
import com.epam.exception.BusinessExcetion;
import com.epam.exception.NotFoundException;
import com.epam.model.User;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends CommonDao implements UserDao {

  @Override
  public User findById(long id) {
    return database.findUserById(id);
  }

  @Override
  public List<User> findAll() {
    return database.findAllUsers();
  }

  @Override
  public User create(User entity) throws BusinessExcetion {
    return (User) database.create(entity);
  }

  @Override
  public User update(User entity) throws NotFoundException {
    return (User) database.update(entity);
  }

  @Override
  public boolean delete(User entity) throws NotFoundException {
    return database.delete(entity);
  }
}
