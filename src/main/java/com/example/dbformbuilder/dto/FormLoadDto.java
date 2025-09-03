package com.example.dbformbuilder.dto;

import com.example.dbformbuilder.model.FormStructure;

import java.util.List;

public class FormLoadDto {
    private FormStructure formStructure;
    private List<FormFieldDto> formFields;
    private List<FormFieldValidationDto> validations;
    private List<FormFieldDependencyDto> dependencies;

    public FormLoadDto(FormStructure formStructure, List<FormFieldDto> formFields, List<FormFieldValidationDto> validations, List<FormFieldDependencyDto> dependencies) {
        this.formStructure = formStructure;
        this.formFields = formFields;
        this.validations = validations;
        this.dependencies = dependencies;
    }

    // Getters and Setters
    public FormStructure getFormStructure() {
        return formStructure;
    }

    public void setFormStructure(FormStructure formStructure) {
        this.formStructure = formStructure;
    }

    public List<FormFieldDto> getFormFields() {
        return formFields;
    }

    public void setFormFields(List<FormFieldDto> formFields) {
        this.formFields = formFields;
    }

    public List<FormFieldValidationDto> getValidations() {
        return validations;
    }

    public void setValidations(List<FormFieldValidationDto> validations) {
        this.validations = validations;
    }

    public List<FormFieldDependencyDto> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<FormFieldDependencyDto> dependencies) {
        this.dependencies = dependencies;
    }
}
