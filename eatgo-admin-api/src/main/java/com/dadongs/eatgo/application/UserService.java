package com.dadongs.eatgo.application;

import com.dadongs.eatgo.domain.CategoryRepository;
import com.dadongs.eatgo.domain.User;
import com.dadongs.eatgo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(String email, String name) {
        User user = User.builder()
                .email(email)
                .name(name)
                .build();
        return userRepository.save(user);
    }

    public User updateUser(Long id, String email, String name, Long level) {
        User user = userRepository.findById(id).orElse(null); // TODO

        user.setEmail(email);
        user.setName(name);
        user.setLevel(level);

        return user;
    }

    public User deativateUser(long id) {
        User user = userRepository.findById(id).orElse(null);
        user.deactive();

        return user;
    }
}
