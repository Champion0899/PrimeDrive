package com.example.PrimeDriveBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PrimeDriveBackend.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    boolean existsByUsername(String username);

    Optional<Users> findById(Integer id);

    Optional<Users> findByUsername(String username);
}
