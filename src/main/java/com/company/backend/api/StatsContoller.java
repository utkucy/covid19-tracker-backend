package com.company.backend.api;

import com.company.backend.model.Stats;
import com.company.backend.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

//StatsController is the api which communicates with frontend and take parameters and transmit it to DB queries.
//If queries returns a respond, it will send these responds to related URLs.
@RequestMapping("api/v1/stats")
@RestController
public class StatsContoller {

    private final StatsService statsService;

    @Autowired
    public StatsContoller(StatsService statsService) {
        this.statsService = statsService;
    }


    @CrossOrigin(origins = "*")
    @GetMapping(path = "{cid}")
    public Stats getStatsByCountryID(@PathVariable("cid") int cid) {
        return statsService.getStatsByCountryID(cid).orElseThrow(() ->
                new ResponseStatusException(NOT_FOUND, "Unable to find stats for this country"));
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public Optional<Stats> getGlobalStats() {
        return statsService.getGlobalStats();
    }

}
