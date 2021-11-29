package com.epam.service.impl;

import com.epam.dao.EventDao;
import com.epam.exception.BusinessException;
import com.epam.exception.NotFoundException;
import com.epam.model.Event;
import com.epam.service.EventService;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

  @Autowired
  private EventDao eventDao;

  @Override
  public Event getById(long id) {
    return eventDao.findOne(id);
  }

  @Override
  public List<Event> getAll() {
    return eventDao.findAll();
  }

  @Override
  public Event create(Event entity) throws BusinessException {
    return eventDao.create(entity);
  }

  @Override
  public Event update(Event entity) throws NotFoundException {
    return eventDao.update(entity);
  }

  @Override
  public void deleteById(long id) throws NotFoundException {
    eventDao.delete(eventDao.findOne(id));
  }

  @Override
  public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
    return eventDao.findAll().stream()
        .filter(event -> Objects.equals(event.getDate(), day))
        .collect(Collectors.toList());
  }

  @Override
  public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
    return eventDao.findAll().stream()
        .filter(event -> Objects.equals(event.getTitle(), title))
        .collect(Collectors.toList());
  }
}
