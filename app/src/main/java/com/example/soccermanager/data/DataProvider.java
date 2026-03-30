package com.example.soccermanager.data;

import com.example.soccermanager.model.Match;
import com.example.soccermanager.model.Player;
import com.example.soccermanager.model.Team;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    // ----- Teams ------------------------------------------------------------

    public List<Team> createSampleTeams() {
        List<Team> teams = new ArrayList<>();
        teams.add(new Team("FC Barcelona",        "Spain",       "La Liga"));
        teams.add(new Team("Real Madrid",          "Spain",       "La Liga"));
        teams.add(new Team("Manchester United",    "England",     "Premier League"));
        teams.add(new Team("Liverpool",            "England",     "Premier League"));
        teams.add(new Team("Bayern Munich",        "Germany",     "Bundesliga"));
        teams.add(new Team("Borussia Dortmund",    "Germany",     "Bundesliga"));
        teams.add(new Team("Juventus",             "Italy",       "Serie A"));
        teams.add(new Team("AC Milan",             "Italy",       "Serie A"));
        teams.add(new Team("Paris Saint-Germain",  "France",      "Ligue 1"));
        teams.add(new Team("Ajax Amsterdam",       "Netherlands", "Eredivisie"));
        return teams;
    }

    // ----- Players ----------------------------------------------------------

    public List<Player> createSamplePlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Lionel Messi",        "Forward",    "FC Barcelona"));
        players.add(new Player("Cristiano Ronaldo",   "Forward",    "Juventus"));
        players.add(new Player("Robert Lewandowski",  "Forward",    "Bayern Munich"));
        players.add(new Player("Kevin De Bruyne",     "Midfielder", "Manchester United"));
        players.add(new Player("Virgil van Dijk",     "Defender",   "Liverpool"));
        players.add(new Player("Manuel Neuer",        "Goalkeeper", "Bayern Munich"));
        players.add(new Player("Kylian Mbappé",       "Forward",    "Paris Saint-Germain"));
        players.add(new Player("Erling Haaland",      "Forward",    "Borussia Dortmund"));
        players.add(new Player("Bruno Fernandes",     "Midfielder", "Manchester United"));
        players.add(new Player("Joshua Kimmich",      "Midfielder", "Bayern Munich"));
        players.add(new Player("Neymar Jr.",          "Forward",    "Paris Saint-Germain"));
        players.add(new Player("Mohamed Salah",       "Forward",    "Liverpool"));
        return players;
    }

    // ----- Matches ----------------------------------------------------------

    public List<Match> createSampleMatches() {
        List<Match> matches = new ArrayList<>();
        matches.add(new Match("FC Barcelona",       "Real Madrid",        "2-1"));
        matches.add(new Match("Manchester United",  "Liverpool",          "0-3"));
        matches.add(new Match("Bayern Munich",      "Borussia Dortmund",  "4-2"));
        matches.add(new Match("Juventus",           "AC Milan",           "1-1"));
        matches.add(new Match("Paris Saint-Germain","Ajax Amsterdam",     "3-0"));
        matches.add(new Match("FC Barcelona",       "Bayern Munich",      "0-3"));
        matches.add(new Match("Liverpool",          "Ajax Amsterdam",     "1-0"));
        matches.add(new Match("Real Madrid",        "Manchester United",  "2-1"));
        return matches;
    }
}
