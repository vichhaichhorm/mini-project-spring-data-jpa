package com.spring.mini.project.demo.spring.mini.project.repository;

import com.spring.mini.project.demo.spring.mini.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
