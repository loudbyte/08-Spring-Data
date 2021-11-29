package com.epam.dao.impl;

import com.epam.dao.EventDao;
import com.epam.model.Event;
import org.springframework.stereotype.Repository;

@Repository
public class EventDaoImpl extends AbstractHibernateDao<Event> implements EventDao {

  public EventDaoImpl() {
    super(Event.class);
  }
}
