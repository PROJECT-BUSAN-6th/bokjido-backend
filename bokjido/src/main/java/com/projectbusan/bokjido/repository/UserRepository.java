package com.projectbusan.bokjido.repository;

import com.projectbusan.bokjido.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public User findByUserid(String userid);
}
