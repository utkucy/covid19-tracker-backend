package com.company.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

//This is CovidData model. Created according to given DB schema.
//It is used for creating new covid-data or retrieving existing covid-data information.
public class CovidData {
    @NotNull
    private final int countryID;

    private final Date day_data;
    @NotNull
    private final int numTest;
    @NotNull
    private final int numCase;
    @NotNull
    private final int numIntubated;
    @NotNull
    private final int numICU;
    @NotNull
    private final int numRecovered;
    @NotNull
    private final int numDeceased;

    public CovidData(
            @JsonProperty("countryID") int countryID,
            @JsonProperty("day_data") Date day_data,
            @JsonProperty("numTest") int numTest,
            @JsonProperty("numCase") int numCase,
            @JsonProperty("numIntubated") int numIntubated,
            @JsonProperty("numICU") int numICU,
            @JsonProperty("numRecovered") int numRecovered,
            @JsonProperty("numDeceased") int numDeceased) {

        this.countryID = countryID;
        this.day_data = day_data;
        this.numTest = numTest;
        this.numCase = numCase;
        this.numIntubated = numIntubated;
        this.numICU = numICU;
        this.numRecovered = numRecovered;
        this.numDeceased = numDeceased;
    }

    public int getCountryID() {
        return countryID;
    }

    public Date getDay_data() {
        return day_data;
    }

    public int getNumTest() {
        return numTest;
    }

    public int getNumCase() {
        return numCase;
    }

    public int getNumIntubated() {
        return numIntubated;
    }

    public int getNumICU() {
        return numICU;
    }

    public int getNumRecovered() {
        return numRecovered;
    }

    public int getNumDeceased() {
        return numDeceased;
    }


}
