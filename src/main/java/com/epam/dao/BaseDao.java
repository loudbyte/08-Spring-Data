package com.epam.dao;

import com.epam.exception.BusinessException;
import com.epam.exception.NotFoundException;
import java.util.List;

public interface BaseDao <T>{

  T findOne(long id);

  List<T> findAll();

  T create(T entity) throws BusinessException;

  T update(T entity) throws NotFoundException;

  void delete(T entity) throws NotFoundException;

  void deleteById(long id) throws NotFoundException;

}
