package com.example.dbformbuilder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class FormFieldDependencyDto {
    private UUID dependencyId;
    @NotNull
    private UUID fieldId;
    @NotBlank
    private String dependsOnField;
    @NotBlank
    private String conditionType;
    private String conditionValue;
    @NotBlank
    private String actionType;

    // Getters and Setters
    public UUID getDependencyId() {
        return dependencyId;
    }

    public void setDependencyId(UUID dependencyId) {
        this.dependencyId = dependencyId;
    }

    public UUID getFieldId() {
        return fieldId;
    }

    public void setFieldId(UUID fieldId) {
        this.fieldId = fieldId;
    }

    public String getDependsOnField() {
        return dependsOnField;
    }

    public void setDependsOnField(String dependsOnField) {
        this.dependsOnField = dependsOnField;
    }

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
}
