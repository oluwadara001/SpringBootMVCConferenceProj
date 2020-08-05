package com.YomiOluwadara.conferencedemo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String title;
    private String company;
    private String email;
    private String phoneNumber;
    private String passPhrase;
    private List<User> users;

    public  User(){
    }

}
