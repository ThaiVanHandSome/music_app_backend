package vn.iostar.springbootbackend.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import vn.iostar.springbootbackend.entity.User;
import vn.iostar.springbootbackend.repository.UserRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    private final static String EMAIL_NOT_FOUND_MSG = "Email %s not found!";

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(EMAIL_NOT_FOUND_MSG)));
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUserByFields(Long id_user, Map<String, Object> fields) {
        Optional<User> userEntity = userRepository.findByIdUser(id_user);
        if (userEntity.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(User.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, userEntity.get(), value);
            });
            userRepository.save(userEntity.get());
            return userEntity.get();
        }
        return null;
    }

    public User findByIdUser(Long id_user) {
        return userRepository.findByIdUser(id_user).get();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public <S extends User> S save(S entity) {
        return userRepository.save(entity);
    }

    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }
}
