package com.example.dbformbuilder.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "form_structures")
public class FormStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "form_id")
    private UUID formId;

    @Column(name = "form_name", nullable = false)
    private String formName;

    @Column(name = "form_description")
    private String formDescription;

    @Column(name = "form_mode", nullable = false)
    private String formMode;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "version_number")
    private Integer versionNumber = 1;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "form_config", columnDefinition = "jsonb", nullable = false)
    private JsonNode formConfig;

    @Column(name = "tables_involved", columnDefinition = "text[]")
    private String[] tablesInvolved;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Getters and Setters
    public UUID getFormId() {
        return formId;
    }

    public void setFormId(UUID formId) {
        this.formId = formId;
    }

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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
