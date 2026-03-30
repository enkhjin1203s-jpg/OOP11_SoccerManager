package com.example.soccermanager.repository;

import com.example.soccermanager.model.Match;

import java.util.List;

public class MatchRepository extends Repository<Match> {


    public List<Match> filterByTeam(String team) {
        if (team == null || team.trim().isEmpty()) return getAll();
        return filter(match ->
                match.getHomeTeam().equalsIgnoreCase(team) ||
                match.getAwayTeam().equalsIgnoreCase(team));
    }
}
