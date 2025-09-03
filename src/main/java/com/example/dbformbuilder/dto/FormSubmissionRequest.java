package com.example.dbformbuilder.dto;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class FormSubmissionRequest {
    @NotNull
    private UUID formId;
    @NotNull
    private JsonNode submissionData;
    private String ipAddress;
    private String userAgent;

    // Getters and Setters
    public UUID getFormId() {
        return formId;
    }

    public void setFormId(UUID formId) {
        this.formId = formId;
    }

    public JsonNode getSubmissionData() {
        return submissionData;
    }

    public void setSubmissionData(JsonNode submissionData) {
        this.submissionData = submissionData;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
