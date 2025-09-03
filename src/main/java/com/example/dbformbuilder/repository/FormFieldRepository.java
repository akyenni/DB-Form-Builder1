package com.example.dbformbuilder.repository;

import com.example.dbformbuilder.model.FormField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FormFieldRepository extends JpaRepository<FormField, UUID> {
    List<FormField> findByFormStructure_FormId(UUID formId);
}
