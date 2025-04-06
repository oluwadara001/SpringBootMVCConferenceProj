package com.YomiOluwadara.conferencedemo.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "session_details")
public class SessionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @Column(name = "learning_objectives")
    @ElementCollection
    private List<String> learningObjectives;

    @Column(name = "prerequisites")
    @ElementCollection
    private List<String> prerequisites;

    @Column(name = "target_audience")
    @ElementCollection
    private List<String> targetAudience;

    @Column(name = "key_topics")
    @ElementCollection
    private List<String> keyTopics;

    @Column(name = "materials_needed")
    @ElementCollection
    private List<String> materialsNeeded;

    @Column(name = "additional_notes")
    private String additionalNotes;

    // Default constructor
    public SessionDetails() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public List<String> getLearningObjectives() {
        return learningObjectives;
    }

    public void setLearningObjectives(List<String> learningObjectives) {
        this.learningObjectives = learningObjectives;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public List<String> getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(List<String> targetAudience) {
        this.targetAudience = targetAudience;
    }

    public List<String> getKeyTopics() {
        return keyTopics;
    }

    public void setKeyTopics(List<String> keyTopics) {
        this.keyTopics = keyTopics;
    }

    public List<String> getMaterialsNeeded() {
        return materialsNeeded;
    }

    public void setMaterialsNeeded(List<String> materialsNeeded) {
        this.materialsNeeded = materialsNeeded;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
} 