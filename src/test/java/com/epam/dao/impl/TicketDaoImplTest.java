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
import com.epam.exception.BusinessExcetion;
import com.epam.exception.NotFoundException;
import com.epam.model.Ticket;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class TicketDaoImplTest {

  @Test
  void testFindById() {
    InMemoryDatabase inMemoryDatabase = mock(InMemoryDatabase.class);
    when(inMemoryDatabase.findTicketById(anyLong())).thenReturn(mock(Ticket.class));
    TicketDaoImpl ticketDaoImpl = new TicketDaoImpl();
    ReflectionTestUtils.setField(ticketDaoImpl, "database", inMemoryDatabase);
    ticketDaoImpl.findById(123L);
    verify(inMemoryDatabase).findTicketById(anyLong());
    assertTrue(ticketDaoImpl.findAll().isEmpty());
  }

  @Test
  void testFindAll() {
    InMemoryDatabase inMemoryDatabase = mock(InMemoryDatabase.class);
    ArrayList<Ticket> ticketList = new ArrayList<>();
    when(inMemoryDatabase.findAllTickets()).thenReturn(ticketList);
    TicketDaoImpl ticketDaoImpl = new TicketDaoImpl();
    ReflectionTestUtils.setField(ticketDaoImpl, "database", inMemoryDatabase);
    List<Ticket> actualFindAllResult = ticketDaoImpl.findAll();
    assertSame(ticketList, actualFindAllResult);
    assertTrue(actualFindAllResult.isEmpty());
    verify(inMemoryDatabase).findAllTickets();
  }

  @Test
  void testCreate() throws BusinessExcetion {
    InMemoryDatabase inMemoryDatabase = mock(InMemoryDatabase.class);
    when(inMemoryDatabase.create(any())).thenThrow(
        new BusinessExcetion("Not all who wander are lost"));
    TicketDaoImpl ticketDaoImpl = new TicketDaoImpl();
    ReflectionTestUtils.setField(ticketDaoImpl, "database", inMemoryDatabase);
    assertThrows(BusinessExcetion.class, () -> ticketDaoImpl.create(mock(Ticket.class)));
    verify(inMemoryDatabase).create(any());
  }

  @Test
  void testUpdate() throws NotFoundException {
    InMemoryDatabase inMemoryDatabase = mock(InMemoryDatabase.class);
    when(inMemoryDatabase.update(any())).thenThrow(
        new NotFoundException("An error occurred"));
    TicketDaoImpl ticketDaoImpl = new TicketDaoImpl();
    ReflectionTestUtils.setField(ticketDaoImpl, "database", inMemoryDatabase);
    assertThrows(NotFoundException.class, () -> ticketDaoImpl.update(mock(Ticket.class)));
    verify(inMemoryDatabase).update(any());
  }

  @Test
  void testDelete() throws NotFoundException {
    InMemoryDatabase inMemoryDatabase = mock(InMemoryDatabase.class);
    when(inMemoryDatabase.delete(any())).thenReturn(true);
    TicketDaoImpl ticketDaoImpl = new TicketDaoImpl();
    ReflectionTestUtils.setField(ticketDaoImpl, "database", inMemoryDatabase);
    assertTrue(ticketDaoImpl.delete(mock(Ticket.class)));
    verify(inMemoryDatabase).delete(any());
    assertTrue(ticketDaoImpl.findAll().isEmpty());
  }
}

