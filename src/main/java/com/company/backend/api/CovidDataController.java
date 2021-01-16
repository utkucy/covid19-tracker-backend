package com.company.backend.api;


import com.company.backend.model.Country;
import com.company.backend.model.CovidData;
import com.company.backend.service.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

//UserController is the api which communicates with frontend and take parameters and transmit it to DB queries.
//If queries returns a respond, it will send these responds to related URLs.
@RequestMapping("api/v1/covid-data")
@RestController
public class CovidDataController {

    private final CovidDataService covidDataService;

   @Autowired
   public CovidDataController(CovidDataService covidDataService) {
       this.covidDataService = covidDataService;
   }

   @CrossOrigin(origins = "*")
   @PostMapping
   @ResponseBody
    public void addCovidData(@Valid @NotNull @RequestBody CovidData covidData) {
       covidDataService.addCovidData(covidData);
   }


   @CrossOrigin(origins = "*")
   @GetMapping
   public List<CovidData> getAllCovidData() {
       return covidDataService.getAllCovidData();
   }

   @CrossOrigin(origins = "*")
   @GetMapping(path = "global-total")
   public List<CovidData> getGlobalStats() {
       return covidDataService.getGlobalStats();
   }

   @CrossOrigin(origins = "*")
   @GetMapping(path = "{cid}")
   public List<CovidData> getCovidDataByCountry(@PathVariable("cid") int cid) {
       return covidDataService.getCovidDataByCountry(cid);
   }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "{cid}/{date}")
    public CovidData getCovidDataByCountryDate(@PathVariable("cid") int cid, @PathVariable("date") Date date) {
       return covidDataService.getCovidDataByCountryDate(cid, date).orElseThrow(() ->
               new ResponseStatusException(NOT_FOUND, "Unable to find covid date for this date!"));
    }


    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "{cid}/{day_data}")
    public void deleteCovidData(@PathVariable("cid") int cid, @PathVariable("day_data") Date day_data) {
        covidDataService.deleteCovidData(cid, day_data);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "{cid}/{day_data}")
    public void updateCovidData(@PathVariable("cid") int cid, @PathVariable("day_data") Date day_data, @Valid @NotNull @RequestBody CovidData cvDataToUpdate) {
        covidDataService.updateCovidData(cid, day_data, cvDataToUpdate);
    }

}
