package com.springboot.app;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findUserById(Long id) {
        Optional<User> opt = repository.findById(id);
        return opt.orElse(null);
    }

    public User findUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User createUser(User user) {
        System.out.println(user.getUsername() + user.getPassword() + user.getEmail());

        return repository.save(user);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

}
