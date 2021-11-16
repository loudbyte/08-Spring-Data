package com.epam.service;

import com.epam.model.Event;
import java.util.Date;
import java.util.List;

public interface EventService extends BaseService<Event> {

  List<Event> getEventsForDay(Date day, int pageSize, int pageNum);

  List<Event> getEventsByTitle(String title, int pageSize, int pageNum);
}
