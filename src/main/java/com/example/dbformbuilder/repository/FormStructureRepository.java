package com.example.dbformbuilder.repository;

import com.example.dbformbuilder.model.FormStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FormStructureRepository extends JpaRepository<FormStructure, UUID> {
}
