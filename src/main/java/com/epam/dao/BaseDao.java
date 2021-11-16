package com.epam.dao;

import com.epam.exception.BusinessExcetion;
import com.epam.exception.NotFoundException;
import java.util.List;

public interface BaseDao <T>{

  T findById(long id);

  List<T> findAll();

  T create(T entity) throws BusinessExcetion;

  T update(T entity) throws NotFoundException;

  boolean delete(T entity) throws NotFoundException;

}
