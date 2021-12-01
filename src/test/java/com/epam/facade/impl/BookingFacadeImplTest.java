package com.epam.facade.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.dao.UserDao;
import com.epam.exception.BusinessException;
import com.epam.exception.NotFoundException;
import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.service.EventService;
import com.epam.service.TicketService;
import com.epam.service.UserAccountService;
import com.epam.service.UserService;
import com.epam.service.impl.EventServiceImpl;
import com.epam.service.impl.TicketServiceImpl;
import com.epam.service.impl.UserAccountServiceImpl;
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
    (new BookingFacadeImpl(eventService, ticketService, new UserServiceImpl(),
        new UserAccountServiceImpl())).getEventById(123L);
    verify(eventService).getById(anyLong());
  }

  @Test
  void testGetEventsByTitle() {
    EventService eventService = mock(EventService.class);
    ArrayList<Event> eventList = new ArrayList<>();
    when(eventService.getEventsByTitle(any(), anyInt(), anyInt())).thenReturn(eventList);
    TicketServiceImpl ticketService = new TicketServiceImpl();
    List<Event> actualEventsByTitle = (new BookingFacadeImpl(eventService, ticketService,
        new UserServiceImpl(), new UserAccountServiceImpl()))
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
        new UserServiceImpl(), new UserAccountServiceImpl());
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    List<Event> actualEventsForDay = bookingFacadeImpl
        .getEventsForDay(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()), 3, 10);
    assertSame(eventList, actualEventsForDay);
    assertTrue(actualEventsForDay.isEmpty());
    verify(eventService).getEventsForDay(any(), anyInt(), anyInt());
  }

  @Test
  void testCreateEvent() throws BusinessException {
    EventService eventService = mock(EventService.class);
    when(eventService.create(any())).thenReturn(mock(Event.class));
    TicketServiceImpl ticketService = new TicketServiceImpl();
    (new BookingFacadeImpl(eventService, ticketService, new UserServiceImpl(),
        new UserAccountServiceImpl())).createEvent(
        mock(Event.class));
    verify(eventService).create(any());
  }

  @Test
  void testUpdateEvent() throws NotFoundException {
    EventService eventService = mock(EventService.class);
    when(eventService.update(any())).thenReturn(mock(Event.class));
    TicketServiceImpl ticketService = new TicketServiceImpl();
    (new BookingFacadeImpl(eventService, ticketService, new UserServiceImpl(),
        new UserAccountServiceImpl())).updateEvent(
        mock(Event.class));
    verify(eventService).update(any());
  }

  @Test
  void testDeleteEvent() throws NotFoundException {
    EventService eventService = mock(EventService.class);
    TicketServiceImpl ticketService = new TicketServiceImpl();
    assertTrue(
        (new BookingFacadeImpl(eventService, ticketService, new UserServiceImpl(),
            new UserAccountServiceImpl())).deleteEvent(
            123L));
    verify(eventService).deleteById(anyLong());
  }

  @Test
  void testGetUserById() {
    UserService userService = mock(UserService.class);
    when(userService.getById(anyLong())).thenReturn(mock(User.class));
    EventServiceImpl eventService = new EventServiceImpl();
    (new BookingFacadeImpl(eventService, new TicketServiceImpl(), userService,
        new UserAccountServiceImpl())).getUserById(123L);
    verify(userService).getById(anyLong());
  }

  @Test
  void testGetUserByEmail() throws NotFoundException {
    UserService userService = mock(UserService.class);
    when(userService.getByEmail(any())).thenReturn(mock(User.class));
    EventServiceImpl eventService = new EventServiceImpl();
    (new BookingFacadeImpl(eventService, new TicketServiceImpl(), userService,
        new UserAccountServiceImpl())).getUserByEmail(
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
        userService, new UserAccountServiceImpl()))
        .getUsersByName("Name", 3, 10);
    assertSame(userList, actualUsersByName);
    assertTrue(actualUsersByName.isEmpty());
    verify(userService).getByName(any(), anyInt(), anyInt());
  }

  @Test
  void testCreateUser() throws BusinessException {
    UserService userService = mock(UserService.class);
    when(userService.create(any())).thenReturn(mock(User.class));
    EventServiceImpl eventService = new EventServiceImpl();
    (new BookingFacadeImpl(eventService, new TicketServiceImpl(), userService,
        new UserAccountServiceImpl())).createUser(
        mock(User.class));
    verify(userService).create(any());
  }

  @Test
  void testUpdateUser() throws NotFoundException {
    UserService userService = mock(UserService.class);
    when(userService.update(any())).thenReturn(mock(User.class));
    EventServiceImpl eventService = new EventServiceImpl();
    (new BookingFacadeImpl(eventService, new TicketServiceImpl(), userService,
        new UserAccountServiceImpl())).updateUser(
        mock(User.class));
    verify(userService).update(any());
  }

  @Test
  void testDeleteUser() throws NotFoundException {
    UserService userService = mock(UserService.class);
    EventServiceImpl eventService = new EventServiceImpl();
    assertTrue(
        (new BookingFacadeImpl(eventService, new TicketServiceImpl(), userService,
            new UserAccountServiceImpl())).deleteUser(
            123L));
    verify(userService).deleteById(anyLong());
  }

  @Test
  void testBookTicket() throws BusinessException {
    TicketService ticketService = mock(TicketService.class);
    UserService userService = mock(UserService.class);
    EventService eventService = mock(EventService.class);
    when(ticketService.create(any())).thenReturn(mock(Ticket.class));
    (new BookingFacadeImpl(eventService, ticketService, userService,
        new UserAccountServiceImpl())).bookTicket(123L,
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
        new UserServiceImpl(), new UserAccountServiceImpl()))
        .getBookedTickets(mock(Event.class), 3, 10);
    assertSame(ticketList, actualBookedTickets);
    assertTrue(actualBookedTickets.isEmpty());
    verify(ticketService).getBookedTickets((Event) any());
  }

  @Test
  void testCancelTicket() throws NotFoundException {
    TicketService ticketService = mock(TicketService.class);
    EventServiceImpl eventService = new EventServiceImpl();
    assertTrue(
        (new BookingFacadeImpl(eventService, ticketService, new UserServiceImpl(),
            new UserAccountServiceImpl())).cancelTicket(
            123L));
    verify(ticketService).deleteById(anyLong());
  }

  @Test
  void testCreateUserAccount() throws BusinessException {
    UserAccountService userAccountService = mock(UserAccountService.class);
    when(userAccountService.create(any())).thenReturn(mock(UserAccount.class));
    EventServiceImpl eventService = new EventServiceImpl();
    TicketServiceImpl ticketService = new TicketServiceImpl();
    (new BookingFacadeImpl(eventService, ticketService, new UserServiceImpl(), userAccountService))
        .createUserAccount(mock(UserAccount.class));
    verify(userAccountService).create(any());
  }

  @Test
  void testFillUserAccount() throws NotFoundException {
    UserAccount userAccount = mock(UserAccount.class);
    doNothing().when(userAccount).setMoney(any());
    UserAccountService userAccountService = mock(UserAccountService.class);
    when(userAccountService.update(any())).thenReturn(mock(UserAccount.class));
    when(userAccountService.getByUserId(anyLong())).thenReturn(userAccount);
    EventServiceImpl eventService = new EventServiceImpl();
    TicketServiceImpl ticketService = new TicketServiceImpl();
    (new BookingFacadeImpl(eventService, ticketService, new UserServiceImpl(),
        userAccountService)).fillUserAccount(3L,
        3L);
    verify(userAccountService).getByUserId(anyLong());
    verify(userAccountService).update(any());
    verify(userAccount).setMoney(any());
  }
}

