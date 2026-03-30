package com.example.soccermanager.repository;

import com.example.soccermanager.model.Team;

import java.util.List;

public class TeamRepository extends Repository<Team> {


    public List<Team> filterByLeague(String league) {
        if (league == null || league.trim().isEmpty()) return getAll();
        return filter(team -> team.getLeague().equalsIgnoreCase(league));
    }
}
