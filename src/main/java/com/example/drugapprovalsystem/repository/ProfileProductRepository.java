package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileProductRepository extends JpaRepository<Profile, Integer> {

    public List<Profile> findAllByTitleContainingAndIsActive(Pageable pageable, String title, boolean isActive);

    public Profile findByIdAndIsActive(int id, boolean isActive);
}
