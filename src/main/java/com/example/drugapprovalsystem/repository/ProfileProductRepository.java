package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.Profile;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileProductRepository extends JpaRepository<Profile, Integer> {
    public Page<Profile> findAllByTitleContainingAndIsActive(Pageable pageable, String title, boolean isActive);
    public Profile findByIdAndIsActive(int id, boolean isActive);
    public Page<Profile> findAllByTitleContainingAndIsActiveAndCreatedById(Pageable pageable,
                                                                           String title,
                                                                           boolean isActive,
                                                                           int createdById);
    @Modifying
    @Transactional
    @Query("UPDATE Profile p SET p.status = :status WHERE p.id = :profileId")
    public void updateProfileStatus(int profileId, String status);
}
