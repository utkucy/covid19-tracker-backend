package com.company.backend.service;

import com.company.backend.dao.CovidDataDao;
import com.company.backend.model.CovidData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


//A CovidDataService retrieves data from related API class and send these data to covidDataDao.
@Service
public class CovidDataService {

    private final CovidDataDao covidDataDao;

    @Autowired
    public CovidDataService(@Qualifier("postgres-covid-data") CovidDataDao covidDataDao) {
        this.covidDataDao = covidDataDao;
    }

    public int addCovidData(CovidData covidData) {
        return covidDataDao.insertCovidData(covidData);
    }

    public Optional<CovidData> getCovidDataByCountryDate(int cid, Date date) {
        return covidDataDao.selectCovidDataByCountryDate(cid, date);
    }

    public List<CovidData> getGlobalStats() {
        return covidDataDao.selectGlobalStats();
    }

    public List<CovidData> getCovidDataByCountry(int cid) {
        return covidDataDao.selectCovidDataByCountry(cid);
    }

    public List<CovidData> getAllCovidData() {
        return covidDataDao.selectAllCovidData();
    }


    public int deleteCovidData(int cid, Date date) {
        return covidDataDao.deleteCovidData(cid, date);
    }

    public int updateCovidData(int cid, Date date, CovidData updatedCovidData) {
        return covidDataDao.updateCovidData(cid, date, updatedCovidData);
    }

}
