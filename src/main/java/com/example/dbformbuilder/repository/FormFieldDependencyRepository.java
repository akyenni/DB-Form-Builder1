package com.example.dbformbuilder.repository;

import com.example.dbformbuilder.model.FormFieldDependency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FormFieldDependencyRepository extends JpaRepository<FormFieldDependency, UUID> {
    List<FormFieldDependency> findByFormField_FieldId(UUID fieldId);
}
