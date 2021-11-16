package com.epam.dao.impl;

import com.epam.dao.db.InMemoryDatabase;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CommonDao {

  @Autowired
  protected InMemoryDatabase database;

}
