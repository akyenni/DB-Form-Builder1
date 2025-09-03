package com.example.dbformbuilder.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "form_field_validations")
public class FormFieldValidation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "validation_id")
    private UUID validationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id", nullable = false)
    private FormField formField;

    @Column(name = "validation_type", nullable = false)
    private String validationType;

    @Column(name = "validation_value")
    private String validationValue;

    @Column(name = "error_message", nullable = false)
    private String errorMessage;

    @Column(name = "validation_order")
    private Integer validationOrder = 1;

    // Getters and Setters
    public UUID getValidationId() {
        return validationId;
    }

    public void setValidationId(UUID validationId) {
        this.validationId = validationId;
    }

    public FormField getFormField() {
        return formField;
    }

    public void setFormField(FormField formField) {
        this.formField = formField;
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
