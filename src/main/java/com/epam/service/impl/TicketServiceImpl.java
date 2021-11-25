package com.epam.service.impl;

import com.epam.dao.TicketDao;
import com.epam.exception.BusinessException;
import com.epam.exception.NotFoundException;
import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;
import com.epam.service.TicketService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

  @Autowired
  private TicketDao ticketDao;

  @Override
  public Ticket getById(long id) {
    return ticketDao.findById(id);
  }

  @Override
  public List<Ticket> getAll() {
    return ticketDao.findAll();
  }

  @Override
  public Ticket create(Ticket entity) throws BusinessException {
    return ticketDao.create(entity);
  }

  @Override
  public Ticket update(Ticket entity) throws NotFoundException {
    return ticketDao.update(entity);
  }

  @Override
  public boolean deleteById(long id) throws NotFoundException {
    return ticketDao.delete(ticketDao.findById(id));
  }

  @Override
  public List<Ticket> getBookedTickets(User user) {
    return ticketDao.findAll().stream()
        .filter(ticket -> ticket.getUserId() == user.getId())
        .collect(Collectors.toList());
  }

  @Override
  public List<Ticket> getBookedTickets(Event event) {
    return ticketDao.findAll().stream()
        .filter(ticket -> ticket.getEventId() == event.getId())
        .collect(Collectors.toList());
  }
}
