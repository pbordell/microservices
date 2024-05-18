package com.pbs.oauth.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        if (name == null || !name.equals("pep") || !name.equals("admin")) throw new UsernameNotFoundException("Username or password wrong");

        UserDetails details = null;
        if (name.equals("pep"))
            details = new User(name, "", true, true, true, true, getAuthorities("ROLE_USER"));
        else if (name.equals("admin"))
            details = new User(name, "", true, true, true, true, getAuthorities("ROLE_ADMIN"));

        return details;
    }

    private Collection<GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
        authList.add(new SimpleGrantedAuthority(role));
        return authList;
    }
}