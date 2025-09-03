package com.example.dbformbuilder.repository;

import com.example.dbformbuilder.model.FormFieldValidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FormFieldValidationRepository extends JpaRepository<FormFieldValidation, UUID> {
    List<FormFieldValidation> findByFormField_FieldId(UUID fieldId);
}
