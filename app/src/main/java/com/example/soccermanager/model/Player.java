package com.example.soccermanager.model;

public class Player implements SoccerEntity {

    private final String id;
    private final String name;
    private final String position;
    private final String team;

    public Player(String name, String position, String team) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Player name must not be empty");
        if (position == null || position.trim().isEmpty())
            throw new IllegalArgumentException("Position must not be empty");
        if (team == null || team.trim().isEmpty())
            throw new IllegalArgumentException("Team must not be empty");

        this.id       = name.toLowerCase().replaceAll("\\s+", "_");
        this.name     = name;
        this.position = position;
        this.team     = team;
    }

    @Override public String getId()   { return id; }
    @Override public String getName() { return name; }
    public String getPosition()       { return position; }
    public String getTeam()           { return team; }

    @Override
    public String toString() {
        return name + " | " + position + " | " + team;
    }
}
