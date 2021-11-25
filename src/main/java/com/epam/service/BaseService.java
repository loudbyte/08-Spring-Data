package com.epam.service;

import com.epam.exception.BusinessException;
import com.epam.exception.NotFoundException;
import java.util.List;

public interface BaseService<T> {

  T getById(long id);

  List<T> getAll();

  T create(T entity) throws BusinessException;

  T update(T entity) throws NotFoundException;

  boolean deleteById(long id) throws NotFoundException;

}
