package com.example.soccermanager.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccermanager.R;
import com.example.soccermanager.model.Match;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    private List<Match> matches;

    public MatchAdapter(List<Match> matches) { this.matches = matches; }

    public void updateData(List<Match> newMatches) {
        this.matches = newMatches;
        notifyDataSetChanged();
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_match, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        Match m = matches.get(position);
        h.tvScore.setText(m.getScore());
        h.tvHomeTeam.setText(m.getHomeTeam());
        h.tvAwayTeam.setText(m.getAwayTeam());
    }

    @Override public int getItemCount() { return matches.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvScore, tvHomeTeam, tvAwayTeam;
        ViewHolder(@NonNull View v) {
            super(v);
            tvScore    = v.findViewById(R.id.tvScore);
            tvHomeTeam = v.findViewById(R.id.tvHomeTeam);
            tvAwayTeam = v.findViewById(R.id.tvAwayTeam);
        }
    }
}
