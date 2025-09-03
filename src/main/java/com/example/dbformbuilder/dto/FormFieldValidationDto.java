package com.example.dbformbuilder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class FormFieldValidationDto {
    private UUID validationId;
    @NotNull
    private UUID fieldId;
    @NotBlank
    private String validationType;
    private String validationValue;
    @NotBlank
    private String errorMessage;
    private Integer validationOrder = 1;

    // Getters and Setters
    public UUID getValidationId() {
        return validationId;
    }

    public void setValidationId(UUID validationId) {
        this.validationId = validationId;
    }

    public UUID getFieldId() {
        return fieldId;
    }

    public void setFieldId(UUID fieldId) {
        this.fieldId = fieldId;
    }

    public String getValidationType() {
        return validationType;
    }

    public void setValidationType(String validationType) {
        this.validationType = validationType;
    }

    public String getValidationValue() {
        return validationValue;
    }

    public void setValidationValue(String validationValue) {
        this.validationValue = validationValue;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getValidationOrder() {
        return validationOrder;
    }

    public void setValidationOrder(Integer validationOrder) {
        this.validationOrder = validationOrder;
    }
}
