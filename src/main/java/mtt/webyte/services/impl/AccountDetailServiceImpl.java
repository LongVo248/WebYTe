package mtt.webyte.services.impl;

import mtt.webyte.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AccountDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            mtt.webyte.model.User user = userRepository.findByUsername(username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            return new User(user.getUsername(), user.getPassword(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found " + username);
        }
    }
}
