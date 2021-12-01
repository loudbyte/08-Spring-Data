package com.epam.model;

import java.io.Serializable;

/**
 * Created by maksym_govorischev.
 */
public interface Ticket extends Serializable {

    enum Category {STANDARD, PREMIUM, BAR}

    /**
     * Ticket Id. UNIQUE.
     * @return Ticket Id.
     */
    Long getId();
    void setId(long id);
    Event getEvent();
    void setEvent(Event event);
    User getUser();
    void setUser(User user);
    Category getCategory();
    void setCategory(Category category);
    int getPlace();
    void setPlace(int place);

}
