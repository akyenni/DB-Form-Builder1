package com.example.dbformbuilder.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "form_fields")
public class FormField {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "field_id")
    private UUID fieldId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id", nullable = false)
    private FormStructure formStructure;

    @Column(name = "field_name", nullable = false)
    private String fieldName;

    @Column(name = "table_name", nullable = false)
    private String tableName;

    @Column(name = "field_order", nullable = false)
    private Integer fieldOrder;

    @Column(name = "field_type", nullable = false)
    private String fieldType;

    @Column(name = "is_primary_key")
    private Boolean isPrimaryKey = false;

    @Column(name = "foreign_key_reference")
    private String foreignKeyReference;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "field_properties", columnDefinition = "jsonb", nullable = false)
    private JsonNode fieldProperties;

    // Getters and Setters
    public UUID getFieldId() {
        return fieldId;
    }

    public void setFieldId(UUID fieldId) {
        this.fieldId = fieldId;
    }

    public FormStructure getFormStructure() {
        return formStructure;
    }

    public void setFormStructure(FormStructure formStructure) {
        this.formStructure = formStructure;
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
