package com.epam.service;

import com.epam.exception.NotFoundException;
import com.epam.model.UserAccount;

public interface UserAccountService extends BaseService<UserAccount>{

  UserAccount getByUserId(long userId) throws NotFoundException;

}
