package com.YomiOluwadara.conferencedemo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "speakers")
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "speaker_id")
    private Long id;

    @Column(name = "id", insertable = false, updatable = false)
    private Long secondaryId;

    @NotEmpty(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Column(name = "email")
    private String email;

    @Size(max = 200, message = "Bio must not exceed 200 characters")
    @Column(name = "speaker_bio")
    private String bio;

    @Column(name = "company")
    private String company;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "speaker", fetch = FetchType.EAGER)
    private List<Session> sessions = new ArrayList<>();

    @OneToOne(mappedBy = "speaker", fetch = FetchType.EAGER)
    private SpeakerProfile profile;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        this.secondaryId = id;
    }

    public Long getSecondaryId() {
        return secondaryId;
    }

    public void setSecondaryId(Long secondaryId) {
        this.secondaryId = secondaryId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions != null ? sessions : new ArrayList<>();
    }

    public SpeakerProfile getProfile() {
        return profile;
    }

    public void setProfile(SpeakerProfile profile) {
        this.profile = profile;
    }
} 