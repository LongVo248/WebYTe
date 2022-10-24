package mtt.webyte.services.impl;

import mtt.webyte.mapper.AccountMapper;
import mtt.webyte.model.Account;
import mtt.webyte.repository.AccountRepository;
import mtt.webyte.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Service
public class AccountDetailServiceImpl implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findById(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found With Username: " + username )
        );
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        account.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new User(account.getUserName(), account.getPassword(), authorities);
    }
}
