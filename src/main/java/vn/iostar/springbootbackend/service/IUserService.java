package vn.iostar.springbootbackend.service;

import vn.iostar.springbootbackend.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface IUserService {

    UserEntity findByUuid(String uuid);

    UserEntity updateUserByFields(Long id_user, Map<String, Object> fields);

    UserEntity findByIdUser(Long id_user);

    List<UserEntity> findAll();

    <S extends UserEntity> S save(S entity);

    void deleteById(Long aLong);
}
