package com.example.dbformbuilder.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "form_submissions")
public class FormSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "submission_id")
    private UUID submissionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id", nullable = false)
    private FormStructure formStructure;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "submission_data", columnDefinition = "jsonb", nullable = false)
    private JsonNode submissionData;

    @Column(name = "submitted_at", updatable = false)
    private LocalDateTime submittedAt = LocalDateTime.now();

    @Column(name = "ip_address")
    private String ipAddress; // INET type in PostgreSQL can be mapped to String in Java

    @Column(name = "user_agent")
    private String userAgent;

    // Getters and Setters
    public UUID getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(UUID submissionId) {
        this.submissionId = submissionId;
    }

    public FormStructure getFormStructure() {
        return formStructure;
    }

    public void setFormStructure(FormStructure formStructure) {
        this.formStructure = formStructure;
    }

    public JsonNode getSubmissionData() {
        return submissionData;
    }

    public void setSubmissionData(JsonNode submissionData) {
        this.submissionData = submissionData;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
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
