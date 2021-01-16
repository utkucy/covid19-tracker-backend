package com.company.backend.api;


import com.company.backend.model.Country;

import com.company.backend.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;


//CountryController is the api which communicates with frontend and take parameters and transmit it to DB queries.
//If queries returns a respond, it will send these responds to related URLs.
@RequestMapping("api/v1/country")
@RestController
public class CountryController {


    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    @ResponseBody
    public void  addCountry(@Valid @NotNull @RequestBody Country country) {
        countryService.addCountry(country);
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "{cname}")
    public Country getCountryByCountryName(@PathVariable("cname") String cname) {
        return countryService.getCountryByCountryName(cname).orElseThrow(() ->
                new ResponseStatusException(NOT_FOUND, "Unable to find country"));
    }

    @DeleteMapping(path = "{id}")
    public void deleteCountryById(@PathVariable("id") int id) {
        countryService.deleteCountry(id);
    }

    @PutMapping(path = "{id}")
    public void updateUser(@PathVariable("id") int id, @Valid @NotNull @RequestBody Country countryToUpdate) {
        countryService.updateCountry(id, countryToUpdate);
    }


}
