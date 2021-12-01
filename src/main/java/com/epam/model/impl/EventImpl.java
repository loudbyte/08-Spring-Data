package com.epam.model.impl;

import com.epam.model.Event;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "event_table")
public class EventImpl implements Event {

  @Id
  @SequenceGenerator(
      name="event_table_id_seq",
      sequenceName="event_table_id_seq",
      allocationSize=1)
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator="event_table_id_seq"
  )
  @Column(name = "id", nullable = false)
  private Long id;
  private String title;
  private Date date;
  private Long ticketPrice;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;

  }

  @Override
  public Date getDate() {
    return date;
  }

  @Override
  public void setDate(Date date) {
    this.date = date;

  }

  @Override
  public Long getTicketPrice() {
    return ticketPrice;
  }

  @Override
  public void setTicketPrice(long ticketPrice) {
    this.ticketPrice = ticketPrice;
  }
}
