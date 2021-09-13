package com.example.doctor.repositories;

import com.example.doctor.entities.InputData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputDataRepo extends JpaRepository<InputData, Integer> {
}
