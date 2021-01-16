package com.company.backend.dao;

import com.company.backend.model.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//This is the class implements StatsDao methods and queries the Stats table.
//Stats table definition can be found in resources/db.migration/V1__MainTables.sql file
@Repository("postgres-stats")
public class StatsDataAccessService implements StatsDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StatsDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Stats> selectStatByCountry(int cid) {
        final String sql = "SELECT * FROM stats WHERE countryID = ?";
        Stats stats = jdbcTemplate.queryForObject(
                sql,
                new Object[]{cid},
                (resultSet, i) -> {
                    int countryID = resultSet.getInt("countryID");
                    int totalTest = resultSet.getInt("totalTest");
                    int totalCase = resultSet.getInt("totalCase");
                    int totalIntubated = resultSet.getInt("totalIntubated");
                    int totalICU = resultSet.getInt("totalICU");
                    int totalRecovered = resultSet.getInt("totalRecovered");
                    int totalDeceased = resultSet.getInt("totalDeceased");
                    return new Stats(countryID, totalTest, totalCase, totalIntubated, totalICU, totalRecovered, totalDeceased);
                });
        return Optional.ofNullable(stats);
    }

    @Override
    public Optional<Stats> selectGlobalStats() {
        final String sql = "SELECT " +
                "SUM(totalTest) as totalTest, " +
                "SUM(totalCase) as totalCase, " +
                "SUM(totalIntubated) as totalIntubated, " +
                "SUM(totalICU) as totalICU, " +
                "SUM(totalRecovered) as totalRecovered, " +
                "SUM(totalDeceased) as totalDeceased " +
                "FROM stats";
        Stats stats = jdbcTemplate.queryForObject(
                sql,
                (resultSet, i) -> {
                    int countryID = 0;
                    int totalTest = resultSet.getInt("totalTest");
                    int totalCase = resultSet.getInt("totalCase");
                    int totalIntubated = resultSet.getInt("totalIntubated");
                    int totalICU = resultSet.getInt("totalICU");
                    int totalRecovered = resultSet.getInt("totalRecovered");
                    int totalDeceased = resultSet.getInt("totalDeceased");
                    return new Stats(countryID, totalTest, totalCase, totalIntubated, totalICU, totalRecovered, totalDeceased);
                });
        return Optional.ofNullable(stats);
    }
}
