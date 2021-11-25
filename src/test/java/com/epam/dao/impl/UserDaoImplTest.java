package com.epam.dao.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.dao.db.InMemoryDatabase;
import com.epam.exception.BusinessException;
import com.epam.exception.NotFoundException;
import com.epam.model.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class UserDaoImplTest {

  @Test
  void testFindById() {
    InMemoryDatabase inMemoryDatabase = mock(InMemoryDatabase.class);
    when(inMemoryDatabase.findUserById(anyLong())).thenReturn(mock(User.class));
    UserDaoImpl userDaoImpl = new UserDaoImpl();
    ReflectionTestUtils.setField(userDaoImpl, "database", inMemoryDatabase);
    userDaoImpl.findById(123L);
    verify(inMemoryDatabase).findUserById(anyLong());
    assertTrue(userDaoImpl.findAll().isEmpty());
  }

  @Test
  void testFindAll() {
    InMemoryDatabase inMemoryDatabase = mock(InMemoryDatabase.class);
    ArrayList<User> userList = new ArrayList<>();
    when(inMemoryDatabase.findAllUsers()).thenReturn(userList);
    UserDaoImpl userDaoImpl = new UserDaoImpl();
    ReflectionTestUtils.setField(userDaoImpl, "database", inMemoryDatabase);
    List<User> actualFindAllResult = userDaoImpl.findAll();
    assertSame(userList, actualFindAllResult);
    assertTrue(actualFindAllResult.isEmpty());
    verify(inMemoryDatabase).findAllUsers();
  }

  @Test
  void testCreate() throws BusinessException {
    InMemoryDatabase inMemoryDatabase = mock(InMemoryDatabase.class);
    when(inMemoryDatabase.create(any())).thenThrow(
        new BusinessException("Not all who wander are lost"));
    UserDaoImpl userDaoImpl = new UserDaoImpl();
    ReflectionTestUtils.setField(userDaoImpl, "database", inMemoryDatabase);
    assertThrows(BusinessException.class, () -> userDaoImpl.create(mock(User.class)));
    verify(inMemoryDatabase).create(any());
  }

  @Test
  void testUpdate() throws NotFoundException {
    InMemoryDatabase inMemoryDatabase = mock(InMemoryDatabase.class);
    when(inMemoryDatabase.update(any())).thenThrow(
        new NotFoundException("An error occurred"));
    UserDaoImpl userDaoImpl = new UserDaoImpl();
    ReflectionTestUtils.setField(userDaoImpl, "database", inMemoryDatabase);
    assertThrows(NotFoundException.class, () -> userDaoImpl.update(mock(User.class)));
    verify(inMemoryDatabase).update(any());
  }

  @Test
  void testDelete() throws NotFoundException {
    InMemoryDatabase inMemoryDatabase = mock(InMemoryDatabase.class);
    when(inMemoryDatabase.delete(any())).thenReturn(true);
    UserDaoImpl userDaoImpl = new UserDaoImpl();
    ReflectionTestUtils.setField(userDaoImpl, "database", inMemoryDatabase);
    assertTrue(userDaoImpl.delete(mock(User.class)));
    verify(inMemoryDatabase).delete(any());
    assertTrue(userDaoImpl.findAll().isEmpty());
  }
}

