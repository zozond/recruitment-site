package com.recruitment.site.user;

import com.recruitment.site.user.entity.User;
import com.recruitment.site.user.entity.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository repository;

    UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        Optional<User> user = repository.findById(id);
        return user;
    }

    @Override
    public User save(UserDTO userDTO) {
        User user = convertDTO(userDTO);
        return repository.save(user);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    private User convertDTO(UserDTO userDTO){
        User user = new User();
        user.setAge(userDTO.getAge());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        return user;
    }
}
