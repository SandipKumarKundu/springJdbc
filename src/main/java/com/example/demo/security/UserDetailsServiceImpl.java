package com.example.demo.security;

import com.example.demo.Impl.UsersRepositoryImpl;
import com.example.demo.model.Users;
import com.example.demo.respository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
Users user= userRepo.findByUserName(username);
if(user==null)
    throw new UsernameNotFoundException("User Not Found with username: " + username);

    return  UsersRepositoryImpl.composeUser(user);
    }

}
