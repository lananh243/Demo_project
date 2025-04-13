package business.service.account;

import business.service.AppService;

public interface AccountService extends AppService {
    boolean logIn(String username, String password);
}
