package com.example.dbformbuilder.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "form_field_dependencies")
public class FormFieldDependency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dependency_id")
    private UUID dependencyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id", nullable = false)
    private FormField formField;

    @Column(name = "depends_on_field", nullable = false)
    private String dependsOnField;

    @Column(name = "condition_type", nullable = false)
    private String conditionType;

    @Column(name = "condition_value")
    private String conditionValue;

    @Column(name = "action_type", nullable = false)
    private String actionType;

    // Getters and Setters
    public UUID getDependencyId() {
        return dependencyId;
    }

    public void setDependencyId(UUID dependencyId) {
        this.dependencyId = dependencyId;
    }

    public FormField getFormField() {
        return formField;
    }

    public void setFormField(FormField formField) {
        this.formField = formField;
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
