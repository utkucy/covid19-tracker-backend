package com.company.backend.service;

import com.company.backend.dao.CountryDao;
import com.company.backend.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//A CountryService retrieves data from related API class and send these data to CountryDao.
@Service
public class CountryService {

    private final CountryDao countryDao;

    @Autowired
    public CountryService(@Qualifier("postgres-country") CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public int addCountry(Country country) {
        return countryDao.insertCountry(country);
    }

    public List<Country> getAllCountries() {
        return countryDao.selectAllCountries();
    }

    public Optional<Country> getCountryByCountryName(String countryname) {
        return countryDao.selectCountryByCountryName(countryname);
    }

    public int deleteCountry(int id) {
        return countryDao.deleteCountry(id);
    }

    public int updateCountry(int id, Country newCountry) {
        return countryDao.updateCountryById(id, newCountry);
    }

}
