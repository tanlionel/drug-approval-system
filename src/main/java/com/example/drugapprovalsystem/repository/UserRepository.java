package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByUsername(String username);
    List<User> findUserByRoleNameAndIsActiveAndEmailContaining(String roleName,String isActive,String email);
    List<User> findUserByRoleNameAndIsActive(String roleName,String isActive);
    List<User> findUserByIsActiveAndEmailContaining(String isActive,String email);
    List<User> findUserByIsActive(String isActive);
    Page<User> findByRoleNameContainingAndIsActiveContainingAndFullnameContaining(String roleName,String status,String search,Pageable pageable);
    Page<User> findByRoleNameContainingAndIsActiveContainingAndGenderAndFullnameContaining(String roleName,String status,Integer gender,String search,Pageable pageable);
}
