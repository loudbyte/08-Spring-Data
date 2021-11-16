package com.epam.dao.impl;

import com.epam.dao.TicketDao;
import com.epam.exception.BusinessExcetion;
import com.epam.exception.NotFoundException;
import com.epam.model.Ticket;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl extends CommonDao implements TicketDao {

  @Override
  public Ticket findById(long id) {
    return database.findTicketById(id);
  }

  @Override
  public List<Ticket> findAll() {
    return database.findAllTickets();
  }

  @Override
  public Ticket create(Ticket entity) throws BusinessExcetion {
    return (Ticket) database.create(entity);
  }

  @Override
  public Ticket update(Ticket entity) throws NotFoundException {
    return (Ticket) database.update(entity);
  }

  @Override
  public boolean delete(Ticket ticket) throws NotFoundException {
    return database.delete(ticket);
  }
}
