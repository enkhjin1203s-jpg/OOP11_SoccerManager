package com.example.soccermanager.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccermanager.R;
import com.example.soccermanager.model.Team;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private List<Team> teams;

    public TeamAdapter(List<Team> teams) { this.teams = teams; }

    public void updateData(List<Team> newTeams) {
        this.teams = newTeams;
        notifyDataSetChanged();
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_team, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        Team t = teams.get(position);
        // Initial letter in the circle
        h.tvInitial.setText(String.valueOf(t.getName().charAt(0)).toUpperCase());
        h.tvName.setText(t.getName());
        h.tvLeague.setText(t.getLeague());
        h.tvCountry.setText(t.getCountry());
    }

    @Override public int getItemCount() { return teams.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvInitial, tvName, tvLeague, tvCountry;
        ViewHolder(@NonNull View v) {
            super(v);
            tvInitial  = v.findViewById(R.id.tvInitial);
            tvName     = v.findViewById(R.id.tvName);
            tvLeague   = v.findViewById(R.id.tvLeague);
            tvCountry  = v.findViewById(R.id.tvCountry);
        }
    }
}
