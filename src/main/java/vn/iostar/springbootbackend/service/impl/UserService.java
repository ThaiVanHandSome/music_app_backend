package vn.iostar.springbootbackend.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import vn.iostar.springbootbackend.auth.registration.RegisterResponse;
import vn.iostar.springbootbackend.entity.User;
import vn.iostar.springbootbackend.repository.UserRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final static String EMAIL_NOT_FOUND_MSG = "Email %s not found!";

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(EMAIL_NOT_FOUND_MSG)));
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
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

    public RegisterResponse changePassword(Long idUser, String password) {
        Optional<User> optUser = userRepository.findByIdUser(idUser);
        if(optUser.isPresent()) {
            User user = optUser.get();
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            return RegisterResponse.builder().message("Change Password Successfully!").error(false).success(true).build();
        }
        return RegisterResponse.builder().message("User Not Valid!").error(true).success(false).build();
    }

    public RegisterResponse changePasswordForgot(String email, String password) {
        Optional<User> optUser = userRepository.findByEmail(email);
        if(optUser.isPresent()) {
            User user = optUser.get();
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            return RegisterResponse.builder().message("Change Password Successfully!").error(false).success(true).build();
        }
        return RegisterResponse.builder().message("User Not Valid!").error(true).success(false).build();
    }
}
