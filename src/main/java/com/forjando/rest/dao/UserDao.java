package com.forjando.rest.dao;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.forjando.rest.entity.User;



public class UserDao {
	
	 private static UserDao instance;
	 
    private List<User> users = new ArrayList<>();
    private Long idCounter = 1L;
    
    private UserDao() {}
    
    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public Optional<User> getUserById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public User createUser(User user) {
        user.setId(idCounter++);
        users.add(user);
        return user;
    }

    public Optional<User> updateUser(Long id, User updatedUser) {
        Optional<User> userOpt = getUserById(id);
        userOpt.ifPresent(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
        });
        return userOpt;
    }

    public boolean deleteUser(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }
}
