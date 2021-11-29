package com.epam.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.dao.UserDao;
import com.epam.exception.BusinessException;
import com.epam.exception.NotFoundException;
import com.epam.model.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class UserServiceImplTest {

  @Test
  void testGetById() {
    UserDao userDao = mock(UserDao.class);
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    ReflectionTestUtils.setField(userServiceImpl, "userDao", userDao);
    userServiceImpl.getById(123L);
    assertTrue(userServiceImpl.getAll().isEmpty());
  }

  @Test
  void testGetAll() {
    UserDao userDao = mock(UserDao.class);
    ArrayList<User> userList = new ArrayList<>();
    when(userDao.findAll()).thenReturn(userList);
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    ReflectionTestUtils.setField(userServiceImpl, "userDao", userDao);
    List<User> actualAll = userServiceImpl.getAll();
    assertSame(userList, actualAll);
    assertTrue(actualAll.isEmpty());
    verify(userDao).findAll();
  }

  @Test
  void testCreate() throws BusinessException {
    UserDao userDao = mock(UserDao.class);
    when(userDao.create(any())).thenReturn(mock(User.class));
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    ReflectionTestUtils.setField(userServiceImpl, "userDao", userDao);
    userServiceImpl.create(mock(User.class));
    verify(userDao).create(any());
    assertTrue(userServiceImpl.getAll().isEmpty());
  }

  @Test
  void testUpdate() throws NotFoundException {
    UserDao userDao = mock(UserDao.class);
    when(userDao.update(any())).thenReturn(mock(User.class));
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    ReflectionTestUtils.setField(userServiceImpl, "userDao", userDao);
    userServiceImpl.update(mock(User.class));
    verify(userDao).update(any());
    assertTrue(userServiceImpl.getAll().isEmpty());
  }

  @Test
  void testDeleteById() {
    UserDao userDao = mock(UserDao.class);
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    ReflectionTestUtils.setField(userServiceImpl, "userDao", userDao);
    assertTrue(userServiceImpl.getAll().isEmpty());
  }

  @Test
  void testGetByEmail() {
    UserDao userDao = mock(UserDao.class);
    when(userDao.findAll()).thenReturn(new ArrayList<>());
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    ReflectionTestUtils.setField(userServiceImpl, "userDao", userDao);
    assertThrows(NotFoundException.class, () -> userServiceImpl.getByEmail("jane.doe@example.org"));
    verify(userDao).findAll();
  }

  @Test
  void testGetByName() {
    UserDao userDao = mock(UserDao.class);
    when(userDao.findAll()).thenReturn(new ArrayList<>());
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    ReflectionTestUtils.setField(userServiceImpl, "userDao", userDao);
    assertTrue(userServiceImpl.getByName("Name", 3, 10).isEmpty());
    verify(userDao).findAll();
    assertTrue(userServiceImpl.getAll().isEmpty());
  }
}

