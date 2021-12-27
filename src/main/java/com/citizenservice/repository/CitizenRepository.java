package com.citizenservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citizenservice.entity.Citizen;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen,Integer> {
     List<Citizen> findByVaccinationCenterId(int id);
}
