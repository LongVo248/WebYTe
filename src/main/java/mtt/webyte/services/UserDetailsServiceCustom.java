package mtt.webyte.services;

import mtt.webyte.dto.UserDTO;
import mtt.webyte.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public interface UserDetailsServiceCustom {
    public UserDetails loadUserByUsernameWithAuthority(String email, Collection grantedAuthorities);

    public void checkStageLogin(UserDTO userDto);

    public Collection<? extends org.springframework.security.core.GrantedAuthority> getAuthorities(User user);

    Collection<? extends GrantedAuthority> getAuthorities(org.springframework.security.core.userdetails.User user);
}
