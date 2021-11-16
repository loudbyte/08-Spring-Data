package com.epam.dao.impl;

import com.epam.dao.EventDao;
import com.epam.exception.BusinessExcetion;
import com.epam.exception.NotFoundException;
import com.epam.model.Event;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class EventDaoImpl extends CommonDao implements EventDao {

  @Override
  public Event findById(long id) {
    return database.findEventById(id);
  }

  @Override
  public List<Event> findAll() {
    return database.findAllEvents();
  }

  @Override
  public Event create(Event entity) throws BusinessExcetion {
    return (Event) database.create(entity);
  }

  @Override
  public Event update(Event entity) throws NotFoundException {
    return (Event) database.update(entity);
  }

  @Override
  public boolean delete(Event entity) throws NotFoundException {
    return database.delete(entity);
  }
}
