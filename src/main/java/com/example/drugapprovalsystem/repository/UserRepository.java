package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    Page<User> findByRoleNameContainingAndIsActiveContaining(String roleName,String status,Pageable pageable);
    Page<User> findByRoleNameContainingAndIsActiveContainingAndGender(String roleName,String status,Integer gender,Pageable pageable);
}
