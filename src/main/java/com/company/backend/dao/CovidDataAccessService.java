package com.company.backend.dao;


import com.company.backend.model.CovidData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


//This is the class implements UserDao methods and queries the cvdata table.
//cvdata table definition can be found in resources/db.migration/V1__MainTables.sql file
@Repository("postgres-covid-data")
public class CovidDataAccessService implements CovidDataDao{

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public CovidDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertCovidData(CovidData covidData) {
        final String sql = "INSERT INTO cvdata (countryID, day_data, numTest, numCase, numIntubated, numICU, numRecovered, numDeceased) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                covidData.getCountryID(),
                covidData.getDay_data(),
                covidData.getNumTest(),
                covidData.getNumCase(),
                covidData.getNumIntubated(),
                covidData.getNumICU(),
                covidData.getNumRecovered(),
                covidData.getNumDeceased()
        );
        return 0;
    }



    @Override
    public Optional<CovidData> selectCovidDataByCountryDate(int cid, Date date) {
        return Optional.empty();
    }

    @Override
    public List<CovidData> selectCovidDataByCountry(int cid) {
        final String sql = "SELECT * FROM cvdata WHERE countryID = ?";
        return  jdbcTemplate.query(
                sql,
                new Object[]{cid},
                (resultSet, i) -> {
                    int countryID = resultSet.getInt("countryID");
                    Date day_data = resultSet.getDate("day_data");
                    int numTest = resultSet.getInt("numTest");
                    int numCase = resultSet.getInt("numCase");
                    int numIntubated = resultSet.getInt("numIntubated");
                    int numICU = resultSet.getInt("numICU");
                    int numRecovered = resultSet.getInt("numRecovered");
                    int numDeceased = resultSet.getInt("numDeceased");
                    return new CovidData(countryID, day_data, numTest, numCase, numIntubated, numICU, numRecovered, numDeceased);
                });
    }

    @Override
    public List<CovidData> selectAllCovidData() {
        final String sql = "SELECT * FROM cvdata";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            int countryID = resultSet.getInt("countryID");
            Date day_data = resultSet.getDate("day_data");
            int numTest = resultSet.getInt("numTest");
            int numCase = resultSet.getInt("numCase");
            int numIntubated = resultSet.getInt("numIntubated");
            int numICU = resultSet.getInt("numICU");
            int numRecovered = resultSet.getInt("numRecovered");
            int numDeceased = resultSet.getInt("numDeceased");
            return new CovidData(countryID, day_data, numTest, numCase, numIntubated, numICU, numRecovered, numDeceased);
        });
    }

    @Override
    public List<CovidData> selectGlobalStats() {
        final String sql = "SELECT day_data, SUM(numRecovered) as total_recovered, SUM(numCase)as total_case, SUM(numDeceased) as total_deceased\n" +
                "FROM cvdata GROUP BY(day_data)";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            Date day_data = resultSet.getDate("day_data");
            int total_recovered = resultSet.getInt("total_recovered");
            int total_case = resultSet.getInt("total_case");
            int total_deceased = resultSet.getInt("total_deceased");
            return new CovidData(0, day_data, 0, total_case, 0, 0, total_recovered, total_deceased);
        });
    }

    @Override
    public int deleteCovidData(int cid, Date date) {
        final String sql = "DELETE FROM cvdata WHERE countryID = ? AND day_data = ?";
        jdbcTemplate.update(
                sql,
                cid,
                date
        );
        return 0;
    }

    @Override
    public int updateCovidData(int cid, Date date, CovidData covidData) {
        final String sql = "UPDATE cvdata SET day_data = ?, numTest = ?,  numCase = ?," +
                "numIntubated = ?, numICU = ?, numRecovered = ?, numDeceased = ?" +
                "WHERE countryID = ? AND day_data = ?";
        jdbcTemplate.update(
                sql,
                covidData.getDay_data(),
                covidData.getNumTest(),
                covidData.getNumCase(),
                covidData.getNumIntubated(),
                covidData.getNumICU(),
                covidData.getNumRecovered(),
                covidData.getNumDeceased(),
                cid,
                date
        );
        return 0;
    }
}
