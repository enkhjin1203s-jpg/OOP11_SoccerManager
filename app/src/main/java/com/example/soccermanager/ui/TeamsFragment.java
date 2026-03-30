package com.example.soccermanager.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccermanager.R;
import com.example.soccermanager.data.DataProvider;
import com.example.soccermanager.iterator.TeamIterator;
import com.example.soccermanager.model.Team;
import com.example.soccermanager.repository.TeamRepository;
import com.google.android.material.chip.Chip;

import java.util.Comparator;
import java.util.List;

public class TeamsFragment extends Fragment {

    private TeamRepository teamRepository;
    private TeamAdapter adapter;
    private TextView tvCount;

    public static TeamsFragment newInstance() { return new TeamsFragment(); }

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvCount = view.findViewById(R.id.tvCount);

        teamRepository = new TeamRepository();
        for (Team t : new DataProvider().createSampleTeams()) teamRepository.add(t);

        RecyclerView rv = view.findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new TeamAdapter(teamRepository.getAll());
        rv.setAdapter(adapter);
        updateCount(teamRepository.getAll().size());

        ((Chip) view.findViewById(R.id.chipSortName))
                .setOnClickListener(v -> sortByName());
        ((Chip) view.findViewById(R.id.chipSortSecondary))
                .setOnClickListener(v -> sortByLeague());
        ((Chip) view.findViewById(R.id.chipSortReset))
                .setOnClickListener(v -> showAll());
    }

    public void loadTeams(List<Team> teams) {
        java.util.List<Team> ordered = new java.util.ArrayList<>();
        TeamIterator it = new TeamIterator(teams);
        while (it.hasNext()) ordered.add(it.next());

        adapter.updateData(ordered);
        updateCount(ordered.size());
    }

    public void search(String query) {
        loadTeams(teamRepository.filter(
                t -> t.getName().toLowerCase().contains(query.toLowerCase()) ||
                     t.getLeague().toLowerCase().contains(query.toLowerCase())));
    }

    public void sortByName() {
        List<Team> sorted = teamRepository.getAll();
        sorted.sort(Comparator.comparing(t -> t.getName()));
        loadTeams(sorted);
    }

    public void sortByLeague() {
        List<Team> sorted = teamRepository.getAll();
        sorted.sort(Comparator.comparing(t -> t.getLeague()));
        loadTeams(sorted);
    }

    public void showAll() { loadTeams(teamRepository.getAll()); }

    private void updateCount(int n) {
        if (tvCount != null) tvCount.setText(n + " teams");
    }
}
