package com.YomiOluwadara.conferencedemo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @Column(name = "userid")
    private Long id;

    @NotEmpty(message = "Username is required")
    @Size(min = 4, max = 60, message = "Username must be between 4 and 60 characters")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 60, message = "Password must be between 6 and 60 characters")
    @Column(nullable = false)
    private String password;

    @NotEmpty(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Column(unique = true, nullable = false)
    private String email;

    @NotEmpty(message = "Passphrase is required")
    @Size(min = 4, max = 80, message = "Passphrase must be between 4 and 80 characters")
    @Column(name = "passphrase", nullable = false)
    private String passphrase;

    @NotEmpty(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(name = "firstname", nullable = false)
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Size(max = 50, message = "Middle name must not exceed 50 characters")
    @Column(name = "middlename")
    private String middleName;

    @Size(max = 40, message = "Title must not exceed 40 characters")
    @Column(name = "title")
    private String title;

    @Size(max = 50, message = "Company must not exceed 50 characters")
    @Column(name = "company")
    private String company;

    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    @Column(name = "phonenumber")
    private String phoneNumber;

    private boolean enabled = true;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
} 