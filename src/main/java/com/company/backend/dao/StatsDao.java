package com.company.backend.dao;

import com.company.backend.model.Stats;

import java.util.List;
import java.util.Optional;

//This is UserDataAccessObject which is an interface and defines all user methods.
public interface StatsDao {

    Optional<Stats> selectStatByCountry(int cid);

    Optional<Stats> selectGlobalStats();
}
