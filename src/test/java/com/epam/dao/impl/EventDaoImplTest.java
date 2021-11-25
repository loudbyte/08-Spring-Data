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
import com.epam.model.Event;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class EventDaoImplTest {

  @Test
  void testFindById() {
    InMemoryDatabase inMemoryDatabase = mock(InMemoryDatabase.class);
    when(inMemoryDatabase.findEventById(anyLong())).thenReturn(mock(Event.class));
    EventDaoImpl eventDaoImpl = new EventDaoImpl();
    ReflectionTestUtils.setField(eventDaoImpl, "database", inMemoryDatabase);
    eventDaoImpl.findById(123L);
    verify(inMemoryDatabase).findEventById(anyLong());
    assertTrue(eventDaoImpl.findAll().isEmpty());
  }

  @Test
  void testFindAll() {
    InMemoryDatabase inMemoryDatabase = mock(InMemoryDatabase.class);
    ArrayList<Event> eventList = new ArrayList<>();
    when(inMemoryDatabase.findAllEvents()).thenReturn(eventList);
    EventDaoImpl eventDaoImpl = new EventDaoImpl();
    ReflectionTestUtils.setField(eventDaoImpl, "database", inMemoryDatabase);
    List<Event> actualFindAllResult = eventDaoImpl.findAll();
    assertSame(eventList, actualFindAllResult);
    assertTrue(actualFindAllResult.isEmpty());
    verify(inMemoryDatabase).findAllEvents();
  }

  @Test
  void testCreate() throws BusinessException {
    InMemoryDatabase inMemoryDatabase = mock(InMemoryDatabase.class);
    when(inMemoryDatabase.create(any())).thenThrow(
        new BusinessException("Not all who wander are lost"));
    EventDaoImpl eventDaoImpl = new EventDaoImpl();
    ReflectionTestUtils.setField(eventDaoImpl, "database", inMemoryDatabase);
    assertThrows(BusinessException.class, () -> eventDaoImpl.create(mock(Event.class)));
    verify(inMemoryDatabase).create(any());
  }

  @Test
  void testUpdate() throws NotFoundException {
    InMemoryDatabase inMemoryDatabase = mock(InMemoryDatabase.class);
    when(inMemoryDatabase.update(any())).thenThrow(
        new NotFoundException("An error occurred"));
    EventDaoImpl eventDaoImpl = new EventDaoImpl();
    ReflectionTestUtils.setField(eventDaoImpl, "database", inMemoryDatabase);
    assertThrows(NotFoundException.class, () -> eventDaoImpl.update(mock(Event.class)));
    verify(inMemoryDatabase).update(any());
  }

  @Test
  void testDelete() throws NotFoundException {
    InMemoryDatabase inMemoryDatabase = mock(InMemoryDatabase.class);
    when(inMemoryDatabase.delete(any())).thenReturn(true);
    EventDaoImpl eventDaoImpl = new EventDaoImpl();
    ReflectionTestUtils.setField(eventDaoImpl, "database", inMemoryDatabase);
    assertTrue(eventDaoImpl.delete(mock(Event.class)));
    verify(inMemoryDatabase).delete(any());
    assertTrue(eventDaoImpl.findAll().isEmpty());
  }
}

