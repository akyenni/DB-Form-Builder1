package com.example.dbformbuilder.dto;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class FormSaveRequest {

    @NotBlank
    private String formName;
    private String formDescription;
    @NotBlank
    private String formMode;
    private LocalDate expiryDate;
    private JsonNode formConfig;
    private String[] tablesInvolved;
    private List<FormFieldDto> formFields;
    private List<FormFieldValidationDto> validations;
    private List<FormFieldDependencyDto> dependencies;

    // Getters and Setters
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormDescription() {
        return formDescription;
    }

    public void setFormDescription(String formDescription) {
        this.formDescription = formDescription;
    }

    public String getFormMode() {
        return formMode;
    }

    public void setFormMode(String formMode) {
        this.formMode = formMode;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public JsonNode getFormConfig() {
        return formConfig;
    }

    public void setFormConfig(JsonNode formConfig) {
        this.formConfig = formConfig;
    }

    public String[] getTablesInvolved() {
        return tablesInvolved;
    }

    public void setTablesInvolved(String[] tablesInvolved) {
        this.tablesInvolved = tablesInvolved;
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
