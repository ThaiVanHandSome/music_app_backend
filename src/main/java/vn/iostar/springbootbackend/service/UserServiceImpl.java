package vn.iostar.springbootbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import vn.iostar.springbootbackend.entity.UserEntity;
import vn.iostar.springbootbackend.repository.UserRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity findByUuid(String uuid) {
        return userRepository.findByUuid(uuid);
    }

    @Override
    public UserEntity updateUserByFields(Long id_user, Map<String, Object> fields) {
        Optional<UserEntity> userEntity = userRepository.findByIdUser(id_user);
        if (userEntity.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(UserEntity.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, userEntity.get(), value);
            });
            return userRepository.save(userEntity.get());
        }
        return null;
    }

    @Override
    public UserEntity findByIdUser(Long id_user) {
        return userRepository.findByIdUser(id_user).get();
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public <S extends UserEntity> S save(S entity) {
        return userRepository.save(entity);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }
}
