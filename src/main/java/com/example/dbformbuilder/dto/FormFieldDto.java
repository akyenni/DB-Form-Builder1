package com.example.dbformbuilder.dto;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class FormFieldDto {
    private UUID fieldId;
    @NotNull
    private UUID formId;
    @NotBlank
    private String fieldName;
    @NotBlank
    private String tableName;
    @NotNull
    private Integer fieldOrder;
    @NotBlank
    private String fieldType;
    private Boolean isPrimaryKey = false;
    private String foreignKeyReference;
    @NotNull
    private JsonNode fieldProperties;

    // Getters and Setters
    public UUID getFieldId() {
        return fieldId;
    }

    public void setFieldId(UUID fieldId) {
        this.fieldId = fieldId;
    }

    public UUID getFormId() {
        return formId;
    }

    public void setFormId(UUID formId) {
        this.formId = formId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getFieldOrder() {
        return fieldOrder;
    }

    public void setFieldOrder(Integer fieldOrder) {
        this.fieldOrder = fieldOrder;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public Boolean getPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public String getForeignKeyReference() {
        return foreignKeyReference;
    }

    public void setForeignKeyReference(String foreignKeyReference) {
        this.foreignKeyReference = foreignKeyReference;
    }

    public JsonNode getFieldProperties() {
        return fieldProperties;
    }

    public void setFieldProperties(JsonNode fieldProperties) {
        this.fieldProperties = fieldProperties;
    }
}
