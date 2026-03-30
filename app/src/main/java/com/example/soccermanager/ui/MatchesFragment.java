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
import com.example.soccermanager.model.Match;
import com.example.soccermanager.repository.MatchRepository;
import com.google.android.material.chip.Chip;

import java.util.Comparator;
import java.util.List;


public class MatchesFragment extends Fragment {

    private MatchRepository matchRepository;
    private MatchAdapter adapter;
    private TextView tvCount;

    public static MatchesFragment newInstance() { return new MatchesFragment(); }

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

        matchRepository = new MatchRepository();
        for (Match m : new DataProvider().createSampleMatches()) matchRepository.add(m);

        RecyclerView rv = view.findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new MatchAdapter(matchRepository.getAll());
        rv.setAdapter(adapter);
        updateCount(matchRepository.getAll().size());

        ((Chip) view.findViewById(R.id.chipSortName))
                .setOnClickListener(v -> sortByHomeTeam());
        ((Chip) view.findViewById(R.id.chipSortSecondary))
                .setOnClickListener(v -> sortByAwayTeam());
        ((Chip) view.findViewById(R.id.chipSortReset))
                .setOnClickListener(v -> showAll());

        ((Chip) view.findViewById(R.id.chipSortName)).setText("Home Team");
        ((Chip) view.findViewById(R.id.chipSortSecondary)).setText("Away Team");
    }

    private void loadMatches(List<Match> matches) {
        adapter.updateData(matches);
        updateCount(matches.size());
    }


    public void search(String query) {
        loadMatches(matchRepository.filter(
                m -> m.getHomeTeam().toLowerCase().contains(query.toLowerCase()) ||
                     m.getAwayTeam().toLowerCase().contains(query.toLowerCase())
        ));
    }

    public void sortByHomeTeam() {
        List<Match> sorted = matchRepository.getAll();
        sorted.sort(Comparator.comparing(m -> m.getHomeTeam()));
        loadMatches(sorted);
    }

    public void sortByAwayTeam() {
        List<Match> sorted = matchRepository.getAll();
        sorted.sort(Comparator.comparing(m -> m.getAwayTeam()));
        loadMatches(sorted);
    }

    public void showAll() { loadMatches(matchRepository.getAll()); }

    private void updateCount(int n) {
        if (tvCount != null) tvCount.setText(n + " matches");
    }
}
