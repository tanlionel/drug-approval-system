package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileProductRepository extends JpaRepository<Profile, Integer> {

    public Page<Profile> findAllByTitleContainingAndIsActive(Pageable pageable, String title, boolean isActive);

    public Profile findByIdAndIsActive(int id, boolean isActive);
}
