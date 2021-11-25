package com.epam.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.dao.EventDao;
import com.epam.exception.BusinessException;
import com.epam.exception.NotFoundException;
import com.epam.model.Event;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class EventServiceImplTest {

  @Test
  void testGetById() {
    EventDao eventDao = mock(EventDao.class);
    when(eventDao.findById(anyLong())).thenReturn(mock(Event.class));
    EventServiceImpl eventServiceImpl = new EventServiceImpl();
    ReflectionTestUtils.setField(eventServiceImpl, "eventDao", eventDao);
    eventServiceImpl.getById(123L);
    verify(eventDao).findById(anyLong());
    assertTrue(eventServiceImpl.getAll().isEmpty());
  }

  @Test
  void testGetAll() {
    EventDao eventDao = mock(EventDao.class);
    ArrayList<Event> eventList = new ArrayList<>();
    when(eventDao.findAll()).thenReturn(eventList);
    EventServiceImpl eventServiceImpl = new EventServiceImpl();
    ReflectionTestUtils.setField(eventServiceImpl, "eventDao", eventDao);
    List<Event> actualAll = eventServiceImpl.getAll();
    assertSame(eventList, actualAll);
    assertTrue(actualAll.isEmpty());
    verify(eventDao).findAll();
  }

  @Test
  void testCreate() throws BusinessException {
    EventDao eventDao = mock(EventDao.class);
    when(eventDao.create(any())).thenReturn(mock(Event.class));
    EventServiceImpl eventServiceImpl = new EventServiceImpl();
    ReflectionTestUtils.setField(eventServiceImpl, "eventDao", eventDao);
    eventServiceImpl.create(mock(Event.class));
    verify(eventDao).create(any());
    assertTrue(eventServiceImpl.getAll().isEmpty());
  }

  @Test
  void testUpdate() throws NotFoundException {
    EventDao eventDao = mock(EventDao.class);
    when(eventDao.update(any())).thenReturn(mock(Event.class));
    EventServiceImpl eventServiceImpl = new EventServiceImpl();
    ReflectionTestUtils.setField(eventServiceImpl, "eventDao", eventDao);
    eventServiceImpl.update(mock(Event.class));
    verify(eventDao).update(any());
    assertTrue(eventServiceImpl.getAll().isEmpty());
  }

  @Test
  void testDeleteById() throws NotFoundException {
    EventDao eventDao = mock(EventDao.class);
    when(eventDao.delete(any())).thenReturn(true);
    when(eventDao.findById(anyLong())).thenReturn(mock(Event.class));
    EventServiceImpl eventServiceImpl = new EventServiceImpl();
    ReflectionTestUtils.setField(eventServiceImpl, "eventDao", eventDao);
    assertTrue(eventServiceImpl.deleteById(123L));
    verify(eventDao).delete(any());
    verify(eventDao).findById(anyLong());
    assertTrue(eventServiceImpl.getAll().isEmpty());
  }

  @Test
  void testGetEventsForDay() {
    EventDao eventDao = mock(EventDao.class);
    when(eventDao.findAll()).thenReturn(new ArrayList<>());
    EventServiceImpl eventServiceImpl = new EventServiceImpl();
    ReflectionTestUtils.setField(eventServiceImpl, "eventDao", eventDao);
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    assertTrue(
        eventServiceImpl.getEventsForDay(
                Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()), 3, 10)
            .isEmpty());
    verify(eventDao).findAll();
    assertTrue(eventServiceImpl.getAll().isEmpty());
  }

  @Test
  void testGetEventsByTitle() {
    EventDao eventDao = mock(EventDao.class);
    when(eventDao.findAll()).thenReturn(new ArrayList<>());
    EventServiceImpl eventServiceImpl = new EventServiceImpl();
    ReflectionTestUtils.setField(eventServiceImpl, "eventDao", eventDao);
    assertTrue(eventServiceImpl.getEventsByTitle("Dr", 3, 10).isEmpty());
    verify(eventDao).findAll();
    assertTrue(eventServiceImpl.getAll().isEmpty());
  }
}

