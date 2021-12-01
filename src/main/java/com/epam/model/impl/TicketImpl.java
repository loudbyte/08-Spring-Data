package com.epam.model.impl;

import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_table")
public class TicketImpl implements Ticket {

  @Id
  @SequenceGenerator(
      name="ticket_table_id_seq",
      sequenceName="ticket_table_id_seq",
      allocationSize=1)
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator="ticket_table_id_seq"
  )
  @Column(name = "id", nullable = false)
  private Long id;
  @JoinColumn(name = "event_id", referencedColumnName = "id")
  private Event event;
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
  private Category category;
  private int place;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(long id) {
    this.id = id;
  }

  @Override
  public Event getEvent() {
    return event;
  }

  @Override
  public void setEvent(Event event) {
    this.event = event;
  }

  @Override
  public User getUser() {
    return user;
  }

  @Override
  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public Category getCategory() {
    return category;
  }

  @Override
  public void setCategory(Category category) {
    this.category = category;
  }

  @Override
  public int getPlace() {
    return place;
  }

  @Override
  public void setPlace(int place) {
    this.place = place;
  }
}
