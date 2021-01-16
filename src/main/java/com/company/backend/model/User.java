package com.company.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;


//This is user model. Created according to given DB schema.
//It is used for creating new users or retrieving existing user information.
public class User {

    @NotNull
    private final int countryID;

    @NotBlank
    private final String fName;
    @NotBlank
    private final String lName;
    @NotBlank
    private final String username;
    @NotBlank
    private final String password;
    @NotNull
    private final int rid;

    public User(
            //@JsonProperty("id") UUID id,
            @JsonProperty("countryID") int countryID,
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("fName") String fName,
            @JsonProperty("lName") String lName,
            @JsonProperty("rid") int rid)
        //@NotNull Boolean is_admin)
    {
        this.countryID = countryID;
        this.username = username;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.rid = rid;
    }


    public int getCountryID() {
        return countryID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public int getRid() {
        return rid;
    }
}
