package com.company.backend.service;

import com.company.backend.dao.StatsDao;
import com.company.backend.model.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

//A StatsService retrieves data from related API class and send these data to statsDao.
@Service
public class StatsService {

    private final StatsDao statsDao;

    @Autowired
    public StatsService(@Qualifier("postgres-stats") StatsDao statsDao) {
        this.statsDao = statsDao;
    }

    public Optional<Stats> getStatsByCountryID(int cid) {
        return statsDao.selectStatByCountry(cid);
    }

    public Optional<Stats> getGlobalStats() {
        return statsDao.selectGlobalStats();
    }
}
