package com.company.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;


//This is country model. Created according to given DB schema.
//It is used for creating new countries or retrieving existing country information.
public class Country {
    private final int id;
    @NotBlank
    private final String cname;

    public Country(
            @JsonProperty("id") int id,
            @JsonProperty("cname") String cname) {
        this.id = id;
        this.cname = cname;
    }


    public int getId() { return id; }

    public String getName() {
        return cname;
    }
}
