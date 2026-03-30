package com.example.soccermanager.model;

public class Match implements SoccerEntity {

    private final String id;
    private final String homeTeam;
    private final String awayTeam;
    private final String score;

    public Match(String homeTeam, String awayTeam, String score) {
        if (homeTeam == null || homeTeam.trim().isEmpty())
            throw new IllegalArgumentException("Home team must not be empty");
        if (awayTeam == null || awayTeam.trim().isEmpty())
            throw new IllegalArgumentException("Away team must not be empty");
        if (score == null || score.trim().isEmpty())
            throw new IllegalArgumentException("Score must not be empty");

        this.id       = (homeTeam + "_vs_" + awayTeam).toLowerCase().replaceAll("\\s+", "_");
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score    = score;
    }

    @Override public String getId()   { return id; }
    @Override public String getName() { return homeTeam + " vs " + awayTeam; }
    public String getHomeTeam()       { return homeTeam; }
    public String getAwayTeam()       { return awayTeam; }
    public String getScore()          { return score; }

    @Override
    public String toString() {
        return homeTeam + " vs " + awayTeam + " | " + score;
    }
}
