package id.co.metrodata.serverApp.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AppUserDetail implements UserDetails {
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authories = new ArrayList<>();

        user.getRoles().forEach(
                role -> {
                    String role_name = "ROLE_" + role.getName().toUpperCase();
                    authories.add(new SimpleGrantedAuthority(role_name));
                    role.getPrivileges().forEach(
                            privilege -> {
                                String privilege_name = privilege.getName().toUpperCase();
                                authories.add(new SimpleGrantedAuthority(privilege_name));
                            });
                });
        return authories;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getIsAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getIsEnabled();
    }

}
