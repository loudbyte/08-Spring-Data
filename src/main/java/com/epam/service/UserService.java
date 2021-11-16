package com.epam.service;

import com.epam.exception.NotFoundException;
import com.epam.model.User;
import java.util.List;

public interface UserService extends BaseService<User> {

  User getByEmail(String email) throws NotFoundException;

  List<User> getByName(String name, int pageSize, int pageNum);
}
