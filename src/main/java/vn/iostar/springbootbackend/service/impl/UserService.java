package vn.iostar.springbootbackend.service.impl;

import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.entity.UserEntity;
import vn.iostar.springbootbackend.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
