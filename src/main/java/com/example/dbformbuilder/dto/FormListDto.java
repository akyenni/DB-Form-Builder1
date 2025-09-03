package com.example.dbformbuilder.dto;

import java.util.UUID;

public class FormListDto {
    private UUID formId;
    private String formName;
    private Integer versionNumber;
    private Boolean isActive;

    public FormListDto(UUID formId, String formName, Integer versionNumber, Boolean isActive) {
        this.formId = formId;
        this.formName = formName;
        this.versionNumber = versionNumber;
        this.isActive = isActive;
    }

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

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
