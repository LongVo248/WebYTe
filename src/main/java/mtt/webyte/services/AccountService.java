package mtt.webyte.services;

import mtt.webyte.model.Account;

public interface AccountService {
    public Account findAccountByUserName(String userName);
    public Account insertAccount(Account account);
    public Account updateAccount(Account account);
    public Account deleteAccount(Account account);
}
