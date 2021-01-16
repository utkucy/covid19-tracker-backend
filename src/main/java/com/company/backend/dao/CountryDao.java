package com.company.backend.dao;

import com.company.backend.model.Country;

import java.util.List;
import java.util.Optional;

//This is CountryDataAccessObject which is an interface and defines all country methods.
public interface CountryDao {

    int insertCountry(Country country);

    List<Country> selectAllCountries();

    Optional<Country> selectCountryByCountryName(String countryName);

    int deleteCountry(int id);

    int updateCountryById(int id, Country country);
}
