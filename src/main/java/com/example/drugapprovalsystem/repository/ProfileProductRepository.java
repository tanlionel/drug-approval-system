package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileProductRepository extends JpaRepository<Profile, Integer> {



}
