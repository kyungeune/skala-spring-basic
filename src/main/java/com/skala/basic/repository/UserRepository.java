package com.skala.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skala.basic.table.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByUserNameContaining(String userName);
}

// JpaRepository : Spring Data JPA 라이브러리 안에 이미 구현되어 있는 인터페이스
// JpaRepository를 상속하면 자동으로 생기는 메서드들 :
//     save(entity)
//     findById(id)
//     findAll()
//     existsById(id)
//     deleteById(id)
//     count()