package mtt.webyte.services;

import mtt.webyte.dto.AccountDTO;
import mtt.webyte.dto.AuthenticationResponseDTO;
import mtt.webyte.dto.LoginRequestDTO;
import mtt.webyte.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface AccountService {
    Map<String, Object> login(AccountDTO accountDTO);
    List<Account> findAllAccount();
    public  Account findAccountByUserName(String userName);
    Account register(List<Map> account);
    public  Account updateAccount(Account account);
    public  Account deleteAccount(Account account);
}
