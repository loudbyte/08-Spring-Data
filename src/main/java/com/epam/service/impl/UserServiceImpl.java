package com.epam.service.impl;

import com.epam.dao.UserDao;
import com.epam.exception.BusinessExcetion;
import com.epam.exception.NotFoundException;
import com.epam.model.User;
import com.epam.service.UserService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Override
  public User getById(long id) {
    return userDao.findById(id);
  }

  @Override
  public List<User> getAll() {
    return userDao.findAll();
  }

  @Override
  public User create(User entity) throws BusinessExcetion {
    return userDao.create(entity);
  }

  @Override
  public User update(User entity) throws NotFoundException {
    return userDao.update(entity);
  }

  @Override
  public boolean deleteById(long id) throws NotFoundException {
    return userDao.delete(userDao.findById(id));
  }

  @Override
  public User getByEmail(String email) throws NotFoundException {
    return userDao.findAll().stream()
        .filter(user -> Objects.equals(user.getEmail(), email))
        .findFirst()
        .orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));
  }

  @Override
  public List<User> getByName(String name, int pageSize, int pageNum) {
    return userDao.findAll().stream()
        .filter(user -> Objects.equals(user.getName(), name))
        .collect(Collectors.toList());
  }
}
