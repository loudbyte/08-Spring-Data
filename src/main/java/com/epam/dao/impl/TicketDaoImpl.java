package com.epam.dao.impl;

import com.epam.dao.TicketDao;
import com.epam.model.Ticket;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl extends AbstractHibernateDao<Ticket> implements TicketDao {

  public TicketDaoImpl() {
    super(Ticket.class);
  }
}
