package com.epam.service;

import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;
import java.util.List;

public interface TicketService extends BaseService<Ticket> {

  List<Ticket> getBookedTickets(User user);

  List<Ticket> getBookedTickets(Event event);
}
