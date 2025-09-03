package com.example.dbformbuilder.service;

import com.example.dbformbuilder.dto.*;
import com.example.dbformbuilder.model.*;
import com.example.dbformbuilder.repository.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FormService {

    @Autowired
    private FormStructureRepository formStructureRepository;
    @Autowired
    private FormFieldRepository formFieldRepository;
    @Autowired
    private FormFieldValidationRepository formFieldValidationRepository;
    @Autowired
    private FormFieldDependencyRepository formFieldDependencyRepository;
    @Autowired
    private FormSubmissionRepository formSubmissionRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    public FormStructure saveForm(FormSaveRequest request) {
        FormStructure formStructure = new FormStructure();
        formStructure.setFormName(request.getFormName());
        formStructure.setFormDescription(request.getFormDescription());
        formStructure.setFormMode(request.getFormMode());
        formStructure.setExpiryDate(request.getExpiryDate());
        formStructure.setFormConfig(request.getFormConfig());
        formStructure.setTablesInvolved(request.getTablesInvolved());
        formStructure = formStructureRepository.save(formStructure);

        // Save Form Fields
        for (FormFieldDto fieldDto : request.getFormFields()) {
            FormField formField = new FormField();
            formField.setFormStructure(formStructure);
            formField.setFieldName(fieldDto.getFieldName());
            formField.setTableName(fieldDto.getTableName());
            formField.setFieldOrder(fieldDto.getFieldOrder());
            formField.setFieldType(fieldDto.getFieldType());
            formField.setPrimaryKey(fieldDto.getPrimaryKey());
            formField.setForeignKeyReference(fieldDto.getForeignKeyReference());
            formField.setFieldProperties(fieldDto.getFieldProperties());
            final FormField savedFormField = formFieldRepository.save(formField); // Make it effectively final

            // Save Validations
            if (fieldDto.getFieldId() != null) { // If fieldId is provided, it's an existing field, so link validations
                request.getValidations().stream()
                        .filter(v -> v.getFieldId().equals(fieldDto.getFieldId()))
                        .forEach(validationDto -> {
                            FormFieldValidation validation = new FormFieldValidation();
                            validation.setFormField(savedFormField); // Use the effectively final variable
                            validation.setValidationType(validationDto.getValidationType());
                            validation.setValidationValue(validationDto.getValidationValue());
                            validation.setErrorMessage(validationDto.getErrorMessage());
                            validation.setValidationOrder(validationDto.getValidationOrder());
                            formFieldValidationRepository.save(validation);
                        });

                // Save Dependencies
                request.getDependencies().stream()
                        .filter(d -> d.getFieldId().equals(fieldDto.getFieldId()))
                        .forEach(dependencyDto -> {
                            FormFieldDependency dependency = new FormFieldDependency();
                            dependency.setFormField(savedFormField); // Use the effectively final variable
                            dependency.setDependsOnField(dependencyDto.getDependsOnField());
                            dependency.setConditionType(dependencyDto.getConditionType());
                            dependency.setConditionValue(dependencyDto.getConditionValue());
                            dependency.setActionType(dependencyDto.getActionType());
                            formFieldDependencyRepository.save(dependency);
                        });
            }
        }
        return formStructure;
    }

    public List<FormListDto> getAllForms() {
        return formStructureRepository.findAll().stream()
                .map(form -> new FormListDto(form.getFormId(), form.getFormName(), form.getVersionNumber(), form.getActive()))
                .collect(Collectors.toList());
    }

    public FormLoadDto getFormById(UUID formId) {
        FormStructure formStructure = formStructureRepository.findById(formId)
                .orElseThrow(() -> new RuntimeException("Form not found"));

        List<FormField> fields = formFieldRepository.findByFormStructure_FormId(formId);
        List<FormFieldDto> fieldDtos = new ArrayList<>();

        for (FormField field : fields) {
            FormFieldDto fieldDto = new FormFieldDto();
            fieldDto.setFieldId(field.getFieldId());
            fieldDto.setFormId(field.getFormStructure().getFormId());
            fieldDto.setFieldName(field.getFieldName());
            fieldDto.setTableName(field.getTableName());
            fieldDto.setFieldOrder(field.getFieldOrder());
            fieldDto.setFieldType(field.getFieldType());
            fieldDto.setPrimaryKey(field.getPrimaryKey());
            fieldDto.setForeignKeyReference(field.getForeignKeyReference());
            fieldDto.setFieldProperties(field.getFieldProperties());
            fieldDtos.add(fieldDto);
        }

        List<FormFieldValidation> validations = new ArrayList<>();
        for (FormField field : fields) {
            validations.addAll(formFieldValidationRepository.findByFormField_FieldId(field.getFieldId()));
        }
        List<FormFieldValidationDto> validationDtos = validations.stream()
                .map(v -> {
                    FormFieldValidationDto dto = new FormFieldValidationDto();
                    dto.setValidationId(v.getValidationId());
                    dto.setFieldId(v.getFormField().getFieldId());
                    dto.setValidationType(v.getValidationType());
                    dto.setValidationValue(v.getValidationValue());
                    dto.setErrorMessage(v.getErrorMessage());
                    dto.setValidationOrder(v.getValidationOrder());
                    return dto;
                })
                .collect(Collectors.toList());

        List<FormFieldDependency> dependencies = new ArrayList<>();
        for (FormField field : fields) {
            dependencies.addAll(formFieldDependencyRepository.findByFormField_FieldId(field.getFieldId()));
        }
        List<FormFieldDependencyDto> dependencyDtos = dependencies.stream()
                .map(d -> {
                    FormFieldDependencyDto dto = new FormFieldDependencyDto();
                    dto.setDependencyId(d.getDependencyId());
                    dto.setFieldId(d.getFormField().getFieldId());
                    dto.setDependsOnField(d.getDependsOnField());
                    dto.setConditionType(d.getConditionType());
                    dto.setConditionValue(d.getConditionValue());
                    dto.setActionType(d.getActionType());
                    return dto;
                })
                .collect(Collectors.toList());

        return new FormLoadDto(formStructure, fieldDtos, validationDtos, dependencyDtos);
    }

    @Transactional
    public void deleteForm(UUID formId) {
        formStructureRepository.deleteById(formId);
    }

    @Transactional
    public FormSubmission submitFormData(FormSubmissionRequest request) {
        FormStructure formStructure = formStructureRepository.findById(request.getFormId())
                .orElseThrow(() -> new RuntimeException("Form not found"));

        FormSubmission submission = new FormSubmission();
        submission.setFormStructure(formStructure);
        submission.setSubmissionData(request.getSubmissionData());
        submission.setIpAddress(request.getIpAddress());
        submission.setUserAgent(request.getUserAgent());
        return formSubmissionRepository.save(submission);
    }
}
