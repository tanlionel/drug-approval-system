package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.Labeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LabellerRepository extends JpaRepository<Labeller, Integer> {

}
