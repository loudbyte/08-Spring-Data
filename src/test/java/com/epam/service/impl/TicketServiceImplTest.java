package com.epam.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.dao.TicketDao;
import com.epam.exception.BusinessException;
import com.epam.exception.NotFoundException;
import com.epam.model.Event;
import com.epam.model.Ticket;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class TicketServiceImplTest {

  @Test
  void testGetById() {
    TicketDao ticketDao = mock(TicketDao.class);
    when(ticketDao.findOne(anyLong())).thenReturn(mock(Ticket.class));
    TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();
    ReflectionTestUtils.setField(ticketServiceImpl, "ticketDao", ticketDao);
    ticketServiceImpl.getById(123L);
    verify(ticketDao).findOne(anyLong());
    assertTrue(ticketServiceImpl.getAll().isEmpty());
  }

  @Test
  void testGetAll() {
    TicketDao ticketDao = mock(TicketDao.class);
    ArrayList<Ticket> ticketList = new ArrayList<>();
    when(ticketDao.findAll()).thenReturn(ticketList);
    TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();
    ReflectionTestUtils.setField(ticketServiceImpl, "ticketDao", ticketDao);
    List<Ticket> actualAll = ticketServiceImpl.getAll();
    assertSame(ticketList, actualAll);
    assertTrue(actualAll.isEmpty());
    verify(ticketDao).findAll();
  }

  @Test
  void testCreate() throws BusinessException {
    TicketDao ticketDao = mock(TicketDao.class);
    when(ticketDao.create(any())).thenReturn(mock(Ticket.class));
    TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();
    ReflectionTestUtils.setField(ticketServiceImpl, "ticketDao", ticketDao);
    ticketServiceImpl.create(mock(Ticket.class));
    verify(ticketDao).create(any());
    assertTrue(ticketServiceImpl.getAll().isEmpty());
  }

  @Test
  void testUpdate() throws NotFoundException {
    TicketDao ticketDao = mock(TicketDao.class);
    when(ticketDao.update(any())).thenReturn(mock(Ticket.class));
    TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();
    ReflectionTestUtils.setField(ticketServiceImpl, "ticketDao", ticketDao);
    ticketServiceImpl.update(mock(Ticket.class));
    verify(ticketDao).update(any());
    assertTrue(ticketServiceImpl.getAll().isEmpty());
  }

  @Test
  void testDeleteById() {
    TicketDao ticketDao = mock(TicketDao.class);
    when(ticketDao.findOne(anyLong())).thenReturn(mock(Ticket.class));
    TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();
    ReflectionTestUtils.setField(ticketServiceImpl, "ticketDao", ticketDao);
    assertTrue(ticketServiceImpl.getAll().isEmpty());
  }

  @Test
  void testGetBookedTickets() {
    TicketDao ticketDao = mock(TicketDao.class);
    when(ticketDao.findAll()).thenReturn(new ArrayList<>());
    TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();
    ReflectionTestUtils.setField(ticketServiceImpl, "ticketDao", ticketDao);
    assertTrue(ticketServiceImpl.getBookedTickets(mock(Event.class)).isEmpty());
    verify(ticketDao).findAll();
    assertTrue(ticketServiceImpl.getAll().isEmpty());
  }
}

