package mtt.webyte.services.impl;

import mtt.webyte.model.User;
import mtt.webyte.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.debug("User not found with username: " + username);
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        } else {
            List<GrantedAuthority> grantedAuthorities= new ArrayList<GrantedAuthority>();
            GrantedAuthority authorit= new SimpleGrantedAuthority(user.getRole().toString());
            grantedAuthorities.add(authorit);
            UserDetails userDetails= new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPwd(),grantedAuthorities);
            return userDetails;
        }
    }
}
