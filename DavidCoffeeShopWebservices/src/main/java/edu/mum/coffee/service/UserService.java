package edu.mum.coffee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.coffee.domain.User;
import edu.mum.coffee.repository.UserRepository;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(int userId) {
        return  userRepository.findOne(userId);
    }
    
    public List<User> getAllUser() {
        return  userRepository.findAll() ;
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
    
    public void delete(User user) {
        userRepository.delete(user);
    }

}