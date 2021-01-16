package com.company.backend.dao;

import com.company.backend.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


//This is the class implements CountryDao methods and queries the Country table.
//Country table definition can be found in resources/db.migration/V1__MainTables.sql file
@Repository("postgres-country")
public class CountryDataAccessService implements CountryDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CountryDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertCountry(Country country) {
        final String insert_country_sql = "INSERT INTO country (cname) VALUES(?)";
        jdbcTemplate.update(
                insert_country_sql,
                country.getName()
        );

        Optional<Country> insertedCountry = this.selectCountryByCountryName(country.getName());
        insertedCountry.ifPresent(country1 -> {
            final String insert_country_to_stats = "INSERT INTO stats (countryID, totalTest, totalCase, totalIntubated, totalICU, totalRecovered, totalDeceased) " +
                    "VALUES(?,?,?,?,?,?,?)";
            jdbcTemplate.update(
                    insert_country_to_stats,
                    country1.getId(), 0, 0, 0, 0, 0, 0
            );
        });
        return 0;
    }

    @Override
    public List<Country> selectAllCountries() {
        final String sql = "SELECT * FROM country";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            String cname = resultSet.getString("cname");
            int cid = resultSet.getInt("cid");
            return new Country(cid, cname);
        });
    }

    public Optional<Country> selectCountryByCountryName(String countryname) {
        final String sql = "SELECT * FROM country WHERE cname = ?";
        Country country = jdbcTemplate.queryForObject(
                sql,
                new Object[]{countryname},
                (resultSet, i) -> {
                    String cname = resultSet.getString("cname");
                    int id = resultSet.getInt("cid");
                    return new Country(id, cname);
                });
        return Optional.ofNullable(country);
    }

    @Override
    public int deleteCountry(int cid) {
        final String sql = "DELETE FROM country WHERE cid = ?";
        jdbcTemplate.update(
                sql,
                cid
        );
        return 0;
    }

    @Override
    public int updateCountryById(int id, Country country) {
        return 0;
    }


}
