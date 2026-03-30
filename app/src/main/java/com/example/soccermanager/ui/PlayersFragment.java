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
import com.example.soccermanager.model.Player;
import com.example.soccermanager.repository.PlayerRepository;
import com.google.android.material.chip.Chip;

import java.util.Comparator;
import java.util.List;

public class PlayersFragment extends Fragment {

    private PlayerRepository playerRepository;
    private PlayerAdapter adapter;
    private TextView tvCount;

    public static PlayersFragment newInstance() { return new PlayersFragment(); }

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

        playerRepository = new PlayerRepository();
        for (Player p : new DataProvider().createSamplePlayers()) playerRepository.add(p);

        RecyclerView rv = view.findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new PlayerAdapter(playerRepository.getAll());
        rv.setAdapter(adapter);
        updateCount(playerRepository.getAll().size());

        ((Chip) view.findViewById(R.id.chipSortName))
                .setOnClickListener(v -> sortByName());
        ((Chip) view.findViewById(R.id.chipSortSecondary))
                .setOnClickListener(v -> sortByPosition());
        ((Chip) view.findViewById(R.id.chipSortReset))
                .setOnClickListener(v -> showAll());
    }

    private void loadPlayers(List<Player> players) {
        adapter.updateData(players);
        updateCount(players.size());
    }

    public void search(String query) {
        loadPlayers(playerRepository.filter(
                p -> p.getName().toLowerCase().contains(query.toLowerCase()) ||
                     p.getPosition().toLowerCase().contains(query.toLowerCase()) ||
                     p.getTeam().toLowerCase().contains(query.toLowerCase())));
    }

    public void sortByName() {
        List<Player> s = playerRepository.getAll();
        s.sort(Comparator.comparing(p -> p.getName()));
        loadPlayers(s);
    }

    public void sortByPosition() {
        List<Player> s = playerRepository.getAll();
        s.sort(Comparator.comparing(p -> p.getPosition()));
        loadPlayers(s);
    }

    public void showAll() { loadPlayers(playerRepository.getAll()); }

    private void updateCount(int n) {
        if (tvCount != null) tvCount.setText(n + " players");
    }
}
