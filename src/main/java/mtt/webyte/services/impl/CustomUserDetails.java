package mtt.webyte.services.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import mtt.webyte.model.Account;
import mtt.webyte.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    Account account;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        for(Role role : account.getRoles()) {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+ role.getRoleName()));
        }
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
