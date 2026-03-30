package com.example.soccermanager.iterator;

import com.example.soccermanager.model.Team;

import java.util.List;
import java.util.NoSuchElementException;

public class TeamIterator implements CustomIterator<Team> {

    private final List<Team> teams;
    private int cursor = 0;

    public TeamIterator(List<Team> teams) {
        if (teams == null) throw new IllegalArgumentException("Team list must not be null");
        this.teams = teams;
    }

    @Override
    public boolean hasNext() {
        return cursor < teams.size();
    }

    @Override
    public Team next() {
        if (!hasNext()) throw new NoSuchElementException("No more teams to iterate");
        return teams.get(cursor++);
    }

    public void reset() {
        cursor = 0;
    }
}
