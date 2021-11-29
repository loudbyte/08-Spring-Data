package com.epam.facade.impl;

import com.epam.exception.BusinessException;
import com.epam.exception.NotFoundException;
import com.epam.facade.BookingFacade;
import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.Ticket.Category;
import com.epam.model.User;
import com.epam.model.impl.TicketImpl;
import com.epam.service.EventService;
import com.epam.service.TicketService;
import com.epam.service.UserService;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class BookingFacadeImpl implements BookingFacade {

  private final static Logger LOGGER = Logger.getLogger(BookingFacadeImpl.class.getName());

  private final EventService eventService;
  private final TicketService ticketService;
  private final UserService userService;

  public BookingFacadeImpl(EventService eventService, TicketService ticketService,
      UserService userService) {
    this.eventService = eventService;
    this.ticketService = ticketService;
    this.userService = userService;
  }

  @Override
  public Event getEventById(long eventId) {
    LOGGER.info("Try to fetch event with id: " + eventId);
    return eventService.getById(eventId);
  }

  @Override
  public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
    LOGGER.info("Try to fetch events with title: " + title);
    return eventService.getEventsByTitle(title, pageSize, pageNum);
  }

  @Override
  public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
    LOGGER.info("Try to fetch events for day: " + day);
    return eventService.getEventsForDay(day, pageSize, pageNum);
  }

  @Override
  public Event createEvent(Event event) {
    LOGGER.info("Try to create event: " + event);
    try {
      return eventService.create(event);
    } catch (BusinessException e) {
      LOGGER.log(Level.WARNING, "Error creating event: " + event, e);
      return null;
    }
  }

  @Override
  public Event updateEvent(Event event) {
    LOGGER.info("Try to update event: " + event);
    try {
      return eventService.update(event);
    } catch (NotFoundException e) {
      LOGGER.log(Level.WARNING, "Error updating event: " + event, e);
      return null;
    }
  }

  @Override
  public boolean deleteEvent(long eventId) {
    LOGGER.info("Try to update event with id: " + eventId);
    try {
      eventService.deleteById(eventId);
      return true;
    } catch (NotFoundException e) {
      LOGGER.log(Level.WARNING, "Error deleting event with id: " + eventId, e);
      return false;
    }
  }

  @Override
  public User getUserById(long userId) {
    LOGGER.info("Try to fetch user with id: " + userId);
    return userService.getById(userId);
  }

  @Override
  public User getUserByEmail(String email) {
    LOGGER.info("Try to fetch user with email: " + email);
    try {
      return userService.getByEmail(email);
    } catch (NotFoundException e) {
      LOGGER.log(Level.WARNING, "Error fetching user with email: " + email, e);
      return null;
    }
  }

  @Override
  public List<User> getUsersByName(String name, int pageSize, int pageNum) {
    LOGGER.info("Try to fetch users with name: " + name);
    return userService.getByName(name, pageSize, pageNum);
  }

  @Override
  public User createUser(User user) throws BusinessException {
    LOGGER.info("Try to create user: " + user);
    try {
      return userService.create(user);
    } catch (BusinessException exception) {
      LOGGER.log(Level.WARNING, "Error creating user: " + user, exception);
      throw exception;
    }
  }

  @Override
  public User updateUser(User user) {
    LOGGER.info("Try to update user: " + user);
    try {
      return userService.update(user);
    } catch (NotFoundException e) {
      LOGGER.log(Level.WARNING, "Error updating user: " + user, e);
      return null;
    }
  }

  @Override
  public boolean deleteUser(long userId) {
    LOGGER.info("Try to delete user with id: " + userId);
    try {
      userService.deleteById(userId);
      return true;
    } catch (NotFoundException e) {
      LOGGER.log(Level.WARNING, "Error deleting user with id: " + userId, e);
      return false;
    }
  }

  @Override
  public Ticket bookTicket(long userId, long eventId, int place, Category category) {
    Ticket ticket = new TicketImpl();
    ticket.setCategory(category);
    ticket.setUserId(userId);
    ticket.setPlace(place);
    ticket.setEventId(eventId);
    LOGGER.info("Try to book ticket: " + ticket);
    try {
      return ticketService.create(ticket);
    } catch (BusinessException e) {
      LOGGER.log(Level.WARNING, "Error booking ticket: " + ticket, e);
      return null;
    }
  }

  @Override
  public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
    LOGGER.info("Try to fetch booked tickets for user: " + user);
    return ticketService.getBookedTickets(user);
  }

  @Override
  public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
    LOGGER.info("Try to fetch booked tickets for event: " + event);
    return ticketService.getBookedTickets(event);
  }

  @Override
  public boolean cancelTicket(long ticketId) {
    LOGGER.info("Try to cancel ticket with id: " + ticketId);
    try {
      ticketService.deleteById(ticketId);
      return true;
    } catch (NotFoundException e) {
      LOGGER.log(Level.WARNING, "Error cancelling ticket with id: " + ticketId, e);
      return false;
    }
  }
}
