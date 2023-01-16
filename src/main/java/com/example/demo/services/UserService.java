package com.example.demo.services;

import com.example.demo.models.enums.Role;
import com.example.demo.models.User;
import com.example.demo.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    // Регистрация пользователя
    public boolean registration(User user) {
        User userDB = userRepository.findByUsername(user.getUsername());
        if (userDB != null) {
            return false;
        }
        user.setRole(Role.REGISTERED);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUsername(user.getUsername());
        userRepository.save(user);
        return true;
    }

    public void makeManager(Long id){
        User user = userRepository.getOne(id);
        user.setRole(Role.MANAGER);
        userRepository.save(user);
    }

    public void makeChief(Long id){
        User user = userRepository.getOne(id);
        user.setRole(Role.CHIEF);
        userRepository.save(user);
    }
}
