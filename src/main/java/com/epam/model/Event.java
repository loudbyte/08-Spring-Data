package com.epam.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by maksym_govorischev.
 */
public interface Event extends Serializable {
    /**
     * Event id. UNIQUE.
     * @return Event Id
     */
    Long getId();
    void setId(long id);
    String getTitle();
    void setTitle(String title);
    Date getDate();
    void setDate(Date date);
    Long getTicketPrice();
    void setTicketPrice(long price);
}
