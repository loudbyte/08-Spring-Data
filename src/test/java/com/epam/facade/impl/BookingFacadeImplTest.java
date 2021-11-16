package com.epam.facade.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.exception.BusinessExcetion;
import com.epam.exception.NotFoundException;
import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;
import com.epam.service.EventService;
import com.epam.service.TicketService;
import com.epam.service.UserService;
import com.epam.service.impl.EventServiceImpl;
import com.epam.service.impl.TicketServiceImpl;
import com.epam.service.impl.UserServiceImpl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;

class BookingFacadeImplTest {

  @Test
  void testGetEventById() {
    EventService eventService = mock(EventService.class);
    when(eventService.getById(anyLong())).thenReturn(mock(Event.class));
    TicketServiceImpl ticketService = new TicketServiceImpl();
    (new BookingFacadeImpl(eventService, ticketService, new UserServiceImpl())).getEventById(123L);
    verify(eventService).getById(anyLong());
  }

  @Test
  void testGetEventsByTitle() {
    EventService eventService = mock(EventService.class);
    ArrayList<Event> eventList = new ArrayList<>();
    when(eventService.getEventsByTitle(any(), anyInt(), anyInt())).thenReturn(eventList);
    TicketServiceImpl ticketService = new TicketServiceImpl();
    List<Event> actualEventsByTitle = (new BookingFacadeImpl(eventService, ticketService,
        new UserServiceImpl()))
        .getEventsByTitle("Dr", 3, 10);
    assertSame(eventList, actualEventsByTitle);
    assertTrue(actualEventsByTitle.isEmpty());
    verify(eventService).getEventsByTitle(any(), anyInt(), anyInt());
  }

  @Test
  void testGetEventsForDay() {
    EventService eventService = mock(EventService.class);
    ArrayList<Event> eventList = new ArrayList<>();
    when(eventService.getEventsForDay(any(), anyInt(), anyInt())).thenReturn(eventList);
    TicketServiceImpl ticketService = new TicketServiceImpl();
    BookingFacadeImpl bookingFacadeImpl = new BookingFacadeImpl(eventService, ticketService,
        new UserServiceImpl());
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    List<Event> actualEventsForDay = bookingFacadeImpl
        .getEventsForDay(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()), 3, 10);
    assertSame(eventList, actualEventsForDay);
    assertTrue(actualEventsForDay.isEmpty());
    verify(eventService).getEventsForDay(any(), anyInt(), anyInt());
  }

  @Test
  void testCreateEvent() throws BusinessExcetion {
    EventService eventService = mock(EventService.class);
    when(eventService.create(any())).thenReturn(mock(Event.class));
    TicketServiceImpl ticketService = new TicketServiceImpl();
    (new BookingFacadeImpl(eventService, ticketService, new UserServiceImpl())).createEvent(
        mock(Event.class));
    verify(eventService).create(any());
  }

  @Test
  void testUpdateEvent() throws NotFoundException {
    EventService eventService = mock(EventService.class);
    when(eventService.update(any())).thenReturn(mock(Event.class));
    TicketServiceImpl ticketService = new TicketServiceImpl();
    (new BookingFacadeImpl(eventService, ticketService, new UserServiceImpl())).updateEvent(
        mock(Event.class));
    verify(eventService).update(any());
  }

  @Test
  void testDeleteEvent() throws NotFoundException {
    EventService eventService = mock(EventService.class);
    when(eventService.deleteById(anyLong())).thenReturn(true);
    TicketServiceImpl ticketService = new TicketServiceImpl();
    assertTrue(
        (new BookingFacadeImpl(eventService, ticketService, new UserServiceImpl())).deleteEvent(
            123L));
    verify(eventService).deleteById(anyLong());
  }

  @Test
  void testGetUserById() {
    UserService userService = mock(UserService.class);
    when(userService.getById(anyLong())).thenReturn(mock(User.class));
    EventServiceImpl eventService = new EventServiceImpl();
    (new BookingFacadeImpl(eventService, new TicketServiceImpl(), userService)).getUserById(123L);
    verify(userService).getById(anyLong());
  }

  @Test
  void testGetUserByEmail() throws NotFoundException {
    UserService userService = mock(UserService.class);
    when(userService.getByEmail(any())).thenReturn(mock(User.class));
    EventServiceImpl eventService = new EventServiceImpl();
    (new BookingFacadeImpl(eventService, new TicketServiceImpl(), userService)).getUserByEmail(
        "jane.doe@example.org");
    verify(userService).getByEmail(any());
  }

  @Test
  void testGetUsersByName() {
    UserService userService = mock(UserService.class);
    ArrayList<User> userList = new ArrayList<>();
    when(userService.getByName(any(), anyInt(), anyInt())).thenReturn(userList);
    EventServiceImpl eventService = new EventServiceImpl();
    List<User> actualUsersByName = (new BookingFacadeImpl(eventService, new TicketServiceImpl(),
        userService))
        .getUsersByName("Name", 3, 10);
    assertSame(userList, actualUsersByName);
    assertTrue(actualUsersByName.isEmpty());
    verify(userService).getByName(any(), anyInt(), anyInt());
  }

  @Test
  void testCreateUser() throws BusinessExcetion {
    UserService userService = mock(UserService.class);
    when(userService.create(any())).thenReturn(mock(User.class));
    EventServiceImpl eventService = new EventServiceImpl();
    (new BookingFacadeImpl(eventService, new TicketServiceImpl(), userService)).createUser(
        mock(User.class));
    verify(userService).create(any());
  }

  @Test
  void testUpdateUser() throws NotFoundException {
    UserService userService = mock(UserService.class);
    when(userService.update(any())).thenReturn(mock(User.class));
    EventServiceImpl eventService = new EventServiceImpl();
    (new BookingFacadeImpl(eventService, new TicketServiceImpl(), userService)).updateUser(
        mock(User.class));
    verify(userService).update(any());
  }

  @Test
  void testDeleteUser() throws NotFoundException {
    UserService userService = mock(UserService.class);
    when(userService.deleteById(anyLong())).thenReturn(true);
    EventServiceImpl eventService = new EventServiceImpl();
    assertTrue(
        (new BookingFacadeImpl(eventService, new TicketServiceImpl(), userService)).deleteUser(
            123L));
    verify(userService).deleteById(anyLong());
  }

  @Test
  void testBookTicket() throws BusinessExcetion {
    TicketService ticketService = mock(TicketService.class);
    when(ticketService.create(any())).thenReturn(mock(Ticket.class));
    EventServiceImpl eventService = new EventServiceImpl();
    (new BookingFacadeImpl(eventService, ticketService, new UserServiceImpl())).bookTicket(123L,
        123L, 1,
        Ticket.Category.STANDARD);
    verify(ticketService).create(any());
  }

  @Test
  void testGetBookedTickets() {
    TicketService ticketService = mock(TicketService.class);
    ArrayList<Ticket> ticketList = new ArrayList<>();
    when(ticketService.getBookedTickets((Event) any())).thenReturn(ticketList);
    EventServiceImpl eventService = new EventServiceImpl();
    List<Ticket> actualBookedTickets = (new BookingFacadeImpl(eventService, ticketService,
        new UserServiceImpl()))
        .getBookedTickets(mock(Event.class), 3, 10);
    assertSame(ticketList, actualBookedTickets);
    assertTrue(actualBookedTickets.isEmpty());
    verify(ticketService).getBookedTickets((Event) any());
  }

  @Test
  void testCancelTicket() throws NotFoundException {
    TicketService ticketService = mock(TicketService.class);
    when(ticketService.deleteById(anyLong())).thenReturn(true);
    EventServiceImpl eventService = new EventServiceImpl();
    assertTrue(
        (new BookingFacadeImpl(eventService, ticketService, new UserServiceImpl())).cancelTicket(
            123L));
    verify(ticketService).deleteById(anyLong());
  }
}

