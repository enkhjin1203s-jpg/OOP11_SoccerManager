package com.example.soccermanager.repository;

import com.example.soccermanager.model.Player;

import java.util.List;


public class PlayerRepository extends Repository<Player> {


    public List<Player> filterByTeam(String team) {
        if (team == null || team.trim().isEmpty()) return getAll();
        return filter(player -> player.getTeam().equalsIgnoreCase(team));
    }
}
