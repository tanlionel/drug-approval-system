package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.ProfileDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProfileDetailRepository extends JpaRepository<ProfileDetail, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE ProfileDetail p SET p.isActive = :isActive WHERE " +
            "p.id NOT IN :idList AND " +
            "p.profile.id = :profileId")
    void updateActiveProfileDetailNotInIdListByProfileId(@Param("isActive") boolean isActive,
                                                     @Param("idList") List<Integer> idList,
                                                     @Param("profileId") int profileId);
    @Transactional
    @Modifying
    @Query("UPDATE ProfileDetail p " +
            "SET p.status = :status " +
            "WHERE p.id = :id")
    void updateProfileDetailStatusById(int id, String status);

    @Transactional
    @Modifying
    @Query("UPDATE ProfileDetail p " +
            "SET p.status = :status " +
            "WHERE p.profile.id = :profileId")
    void updateProfileDetailStatusByProfileId(String status, int profileId);

    public List<ProfileDetail> findAllByProfileIdAndIsActive(int profileId, boolean isActive);

}
