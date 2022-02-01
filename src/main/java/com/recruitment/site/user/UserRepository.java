package com.recruitment.site.user;

import com.recruitment.site.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findById(long id);

    User save(User user);
    void deleteById(long id);
    void deleteAll();
}


