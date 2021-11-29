package com.epam.model.impl;

import com.epam.model.Ticket;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
  private long eventId;
  private long userId;
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
  public long getEventId() {
    return eventId;
  }

  @Override
  public void setEventId(long eventId) {
    this.eventId = eventId;
  }

  @Override
  public long getUserId() {
    return userId;
  }

  @Override
  public void setUserId(long userId) {
    this.userId = userId;
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
