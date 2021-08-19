package com.example.demo.Impl;

import com.example.demo.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class UsersRepositoryImpl implements UserDetails {
    public String firstName;
    public String lastName;
    public String userName;
    public String password;
    public String address;
    public String phoneNumber;
    public String gender;
    public Date DOB;
    public List roles;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public Date getDOB() {
        return DOB;
    }
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public UsersRepositoryImpl(String firstName, String lastName, String userName, String password, String address, String phoneNumber, String gender, Date DOB, Collection<? extends GrantedAuthority> authorities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.DOB = DOB;
        this.roles = roles;
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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

    public static UsersRepositoryImpl composeUser(Users user){
        List authorities = new ArrayList();
        user.roles.forEach(role->
                authorities.add(new SimpleGrantedAuthority((String)role )));
return new UsersRepositoryImpl(user.firstName,user.lastName,user.userName,user.password
,user.address,user.phoneNumber,user.gender,user.getDOB(),authorities);
    }
}
