package business.dao.account;

import business.dao.AppDao;
import business.model.Account;

public interface AccountDao extends AppDao<Account> {
    boolean logIn(String username, String password);
}
