package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByUsername(String username);
    Page<User> findByRoleNameContainingAndIsActiveContainingAndFullnameContaining(String roleName,String status,String search,Pageable pageable);
    Page<User> findByRoleNameContainingAndIsActiveContainingAndGenderAndFullnameContaining(String roleName,String status,Integer gender,String search,Pageable pageable);
}
