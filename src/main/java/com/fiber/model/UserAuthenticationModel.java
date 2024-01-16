package com.fiber.model;

import com.fiber.entity.UserEntity;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class UserAuthenticationModel implements Authentication {

    private UserEntity userEntity;

    public UserAuthenticationModel(UserDetails userDetails) {
        if (userDetails instanceof UserEntity entity) {
            this.userEntity = entity;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.NO_AUTHORITIES;
    }

    @Override
    public Object getCredentials() {
        if (userEntity != null) {
            return userEntity.getPassword();
        }
        return null;
    }

    @Override
    public Object getDetails() {
        if (userEntity != null) {
            return userEntity;
        }
        return null;
    }

    @Override
    public Object getPrincipal() {
        if (userEntity != null) {
            return userEntity;
        }
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        if (userEntity != null) {
            return userEntity.getUsername();
        }
        return null;
    }
}
