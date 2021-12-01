package com.epam.dao.impl;

import com.epam.dao.UserAccountDao;
import com.epam.model.UserAccount;
import org.springframework.stereotype.Repository;

@Repository
public class UserAccountDaoImpl extends AbstractHibernateDao<UserAccount> implements UserAccountDao {

  public UserAccountDaoImpl() {
    super(UserAccount.class);
  }

}
