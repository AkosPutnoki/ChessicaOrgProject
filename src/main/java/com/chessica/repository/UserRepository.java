package com.chessica.repository;

import com.chessica.domain.userRelated.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User getUserByEmail(String email);

    public User getUserByName(String name);
}
