package com.example.soccermanager.model;

public class Team implements SoccerEntity {

    private final String id;
    private final String name;
    private final String country;
    private final String league;

    public Team(String name, String country, String league) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Team name must not be empty");
        if (country == null || country.trim().isEmpty())
            throw new IllegalArgumentException("Country must not be empty");
        if (league == null || league.trim().isEmpty())
            throw new IllegalArgumentException("League must not be empty");

        this.id      = name.toLowerCase().replaceAll("\\s+", "_");
        this.name    = name;
        this.country = country;
        this.league  = league;
    }

    @Override public String getId()   { return id; }
    @Override public String getName() { return name; }
    public String getCountry()        { return country; }
    public String getLeague()         { return league; }

    @Override
    public String toString() {
        return name + " | " + league + " | " + country;
    }
}
