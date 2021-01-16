package com.company.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;


//This is Stats model. Created according to given DB schema.
//It is used for creating new stats or retrieving existing stat information.
public class Stats {

    @NotNull
    private final int countryID;
    @NotNull
    private final int totalTest;
    @NotNull
    private final int totalCase;
    @NotNull
    private final int totalIntubated;
    @NotNull
    private final int totalICU;
    @NotNull
    private final int totalRecovered;
    @NotNull
    private final int totalDeceased;

    public Stats(
            @JsonProperty("countryID") int countryID,
            @JsonProperty("totalTest") int totalTest,
            @JsonProperty("totalCase") int totalCase,
            @JsonProperty("totalIntubated") int totalIntubated,
            @JsonProperty("totalICU") int totalICU,
            @JsonProperty("totalRecovered") int totalRecovered,
            @JsonProperty("totalDeceased") int totalDeceased
    ) {
        this.countryID = countryID;
        this.totalTest = totalTest;
        this.totalCase = totalCase;
        this.totalIntubated = totalIntubated;
        this.totalICU = totalICU;
        this.totalRecovered = totalRecovered;
        this.totalDeceased = totalDeceased;
    }

    public int getCountryID() {
        return countryID;
    }

    public int getTotalTest() {
        return totalTest;
    }

    public int getTotalCase() {
        return totalCase;
    }

    public int getTotalIntubated() {
        return totalIntubated;
    }

    public int getTotalICU() {
        return totalICU;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public int getTotalDeceased() {
        return totalDeceased;
    }
}
