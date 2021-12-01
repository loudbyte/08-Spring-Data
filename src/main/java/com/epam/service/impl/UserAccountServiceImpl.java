package com.epam.service.impl;

import com.epam.dao.UserAccountDao;
import com.epam.exception.BusinessException;
import com.epam.exception.NotFoundException;
import com.epam.model.UserAccount;
import com.epam.service.UserAccountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

  @Autowired
  private UserAccountDao userAccountDao;

  @Override
  public UserAccount getById(long id) {
    return userAccountDao.findOne(id);
  }

  @Override
  public List<UserAccount> getAll() {
    return userAccountDao.findAll();
  }

  @Override
  public UserAccount create(UserAccount entity) throws BusinessException {
    return userAccountDao.create(entity);
  }

  @Override
  public UserAccount update(UserAccount entity) throws NotFoundException {
    return userAccountDao.update(entity);
  }

  @Override
  public void deleteById(long id) throws NotFoundException {
    userAccountDao.deleteById(id);
  }

  @Override
  public UserAccount getByUserId(long userId) throws NotFoundException {
    return userAccountDao.findAll().stream()
        .filter(userAccount -> userAccount.getUser().getId() == userId)
        .findFirst()
        .orElseThrow(() -> new NotFoundException("UserAccount with userId " + userId + " not found"));
  }
}
