package com.skala.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.skala.basic.table.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByUserNameContaining(String userName);
}
