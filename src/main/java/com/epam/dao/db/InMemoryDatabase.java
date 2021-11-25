package com.epam.dao.db;

import com.epam.exception.BusinessException;
import com.epam.exception.NotFoundException;
import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryDatabase {

  private static final InMemoryMap dataBase = new InMemoryMap();

  private static final String TICKET_PREFIX = "ticket:";
  private static final String EVENT_PREFIX = "event:";
  private static final String USER_PREFIX = "user:";

  public Ticket findTicketById(long id) {
    return (Ticket) dataBase.get(id, Ticket.class);
  }

  public User findUserById(long id) {
    return (User) dataBase.get(id, Ticket.class);
  }

  public Event findEventById(long id) {
    return (Event) dataBase.get(id, Ticket.class);
  }

  public List<Ticket> findAllTickets() {
    return dataBase.entrySet().stream()
        .filter(entry -> entry.getKey().contains(TICKET_PREFIX))
        .map(entry -> (Ticket) entry.getValue())
        .collect(Collectors.toList());
  }
  public List<User> findAllUsers() {
    return dataBase.entrySet().stream()
        .filter(entry -> entry.getKey().contains(USER_PREFIX))
        .map(entry -> (User) entry.getValue())
        .collect(Collectors.toList());
  }
  public List<Event> findAllEvents() {
    return dataBase.entrySet().stream()
        .filter(entry -> entry.getKey().contains(EVENT_PREFIX))
        .map(entry -> (Event) entry.getValue())
        .collect(Collectors.toList());
  }

  public Object create(Object entity) throws BusinessException {
    if (entity instanceof Ticket) {
      if (((Ticket) entity).getId() != null && dataBase.get(((Ticket) entity).getId(), Ticket.class) != null) {
        throw new BusinessException("Ticket with id " + ((Ticket) entity).getId() + " already exists");
      }
      ((Ticket) entity).setId(getSizeOfDataBase());
      dataBase.put(entity);
    }
    if (entity instanceof Event) {
      if (((Event) entity).getId() != null && dataBase.get(((Event) entity).getId(), Event.class) != null) {
        throw new BusinessException("Event with id " + ((Event) entity).getId() + " already exists");
      }
      ((Event) entity).setId(getSizeOfDataBase());
      dataBase.put(entity);
    }
    if (entity instanceof User) {
      if (((User) entity).getId() != null && dataBase.get(((User) entity).getId(), User.class) != null) {
        throw new BusinessException("User with id " + ((User) entity).getId() + " already exists");
      }
      ((User) entity).setId(getSizeOfDataBase());
      dataBase.put(entity);
    }
    return entity;
  }

  public Object update(Object entity) throws NotFoundException {
    if (entity instanceof Ticket) {
      if (dataBase.get(((Ticket) entity).getId(), Ticket.class) == null) {
        throw new NotFoundException("Ticket with id + " + ((Ticket) entity).getId() + " not exists");
      }
      dataBase.put(entity);
    }
    if (entity instanceof Event) {
      if (dataBase.get(((Event) entity).getId(), Event.class) == null) {
        throw new NotFoundException("Event with id + " + ((Event) entity).getId() + " not exists");
      }
      dataBase.put(entity);
    }
    if (entity instanceof User) {
      if (dataBase.get(((User) entity).getId(), User.class) == null) {
        throw new NotFoundException("User with id + " + ((User) entity).getId() + " not exists");
      }
      dataBase.put(entity);
    }
    return entity;
  }

  public boolean delete(Object entity) throws NotFoundException {
    if (entity instanceof Ticket) {
      if (dataBase.get(((Ticket) entity).getId(), Ticket.class) == null) {
        throw new NotFoundException("Ticket with id " + ((Ticket) entity).getId() + " not exists");
      }
      dataBase.remove(((Ticket)entity).getId(), Ticket.class);
    }
    if (entity instanceof Event) {
      if (dataBase.get(((Event) entity).getId(), Event.class) == null) {
        throw new NotFoundException("Event with id " + ((Event) entity).getId() + " not exists");
      }
      dataBase.remove(((Event)entity).getId(), Event.class);
    }
    if (entity instanceof User) {
      if (dataBase.get(((User) entity).getId(), User.class) == null) {
        throw new NotFoundException("User with id " + ((User) entity).getId() + " not exists");
      }
      dataBase.remove(((User)entity).getId(), User.class);
    }
    return true;
  }

  private static String generateId(long id, Class clazz) {
    if (clazz.getName().equals(Ticket.class.getName())) {
      return TICKET_PREFIX + id;
    }
    if (clazz.getName().equals(Event.class.getName())) {
      return EVENT_PREFIX + id;
    }
    if (clazz.getName().equals(User.class.getName())) {
      return USER_PREFIX + id;
    }
    throw new IllegalArgumentException("Incorrect type");
  }

  private static String generateId(Object entity) {
    long id;
    if (entity instanceof Ticket) {
      if (((Ticket) entity).getId() != null) {
        id = ((Ticket) entity).getId();
      } else {
        id = getSizeOfDataBase();
      }
      return TICKET_PREFIX + id;
    }
    if (entity instanceof Event) {
      if (((Event) entity).getId() != null) {
        id = ((Event) entity).getId();
      } else {
        id = getSizeOfDataBase();
      }
      return EVENT_PREFIX + id;
    }
    if (entity instanceof User) {
      if (((User) entity).getId() != null) {
        id = ((User) entity).getId();
      } else {
        id = getSizeOfDataBase();
      }
      return USER_PREFIX + id;
    }
    throw new IllegalArgumentException("Incorrect type");
  }

  private static int getSizeOfDataBase() {
    return dataBase.db.size();
  }


  static class InMemoryMap {

    private final Map<String, Object> db = new HashMap<>();

    public Object get(long id, Class clazz) {
      Object entity = db.get(generateId(id, clazz));
      if (clazz.isInstance(entity)) {
        return entity;
      }
      return null;
    }

    public void put(Object entity) {
      db.put(generateId(entity), entity);
    }

    public Set<Entry<String, Object>> entrySet(){
      return db.entrySet();
    }

    public void remove(long id, Class clazz) {
      db.remove(generateId(id, clazz));
    }

  }
}
