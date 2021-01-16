package com.company.backend.dao;

import com.company.backend.model.CovidData;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

//This is CovidDataAccessObject which is an interface and defines all cvdata methods.
public interface CovidDataDao {

    int insertCovidData(CovidData covidData);

    List<CovidData> selectAllCovidData();

    List<CovidData> selectGlobalStats();

    Optional<CovidData> selectCovidDataByCountryDate(int cid, Date date);

    List<CovidData> selectCovidDataByCountry(int cid);

    int deleteCovidData(int cid, Date date);

    int updateCovidData(int cid, Date date, CovidData covidData);


}
