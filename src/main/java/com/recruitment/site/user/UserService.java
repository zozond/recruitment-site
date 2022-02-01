package com.recruitment.site.user;

import com.recruitment.site.user.entity.User;
import com.recruitment.site.user.entity.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(long id);
    User save(UserDTO userDTO);
    void deleteById(long id);
}
