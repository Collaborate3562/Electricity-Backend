package com.idigital.epam.energy.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.idigital.epam.energy.entity.Role;
import com.idigital.epam.energy.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
/**
 * Spring security uchun yaratilgan maxsus user
 *
 * */
public class UserMaxsus implements UserDetails  {

   private String username;
   private String password;
   private boolean rememberMe;


   private Boolean enabled = false;
   private Collection<SimpleGrantedAuthority> authorities;

   public UserMaxsus(){}
   public UserMaxsus(User u) {
        this.username = u.getCardNumber();
        this.password = u.getPassword();
        this.enabled = u.isActive();

   }


    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return this.enabled;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
    public void setLavozimlar(Set<Role> roles){
        this.authorities = new HashSet<SimpleGrantedAuthority>();

        roles.forEach(l -> authorities.add(new SimpleGrantedAuthority(l.getName())));
    }
}
