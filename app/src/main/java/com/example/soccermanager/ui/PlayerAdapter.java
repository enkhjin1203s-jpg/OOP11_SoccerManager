package com.example.soccermanager.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccermanager.R;
import com.example.soccermanager.model.Player;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    private List<Player> players;

    public PlayerAdapter(List<Player> players) { this.players = players; }

    public void updateData(List<Player> newPlayers) {
        this.players = newPlayers;
        notifyDataSetChanged();
    }

    private String positionAbbr(String pos) {
        switch (pos) {
            case "Forward":    return "FW";
            case "Midfielder": return "MF";
            case "Defender":   return "DF";
            case "Goalkeeper": return "GK";
            default:           return pos.substring(0, Math.min(2, pos.length())).toUpperCase();
        }
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_player, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        Player p = players.get(position);
        h.tvPositionBadge.setText(positionAbbr(p.getPosition()));
        h.tvName.setText(p.getName());
        h.tvPosition.setText(p.getPosition());
        h.tvTeam.setText(p.getTeam());
    }

    @Override public int getItemCount() { return players.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPositionBadge, tvName, tvPosition, tvTeam;
        ViewHolder(@NonNull View v) {
            super(v);
            tvPositionBadge = v.findViewById(R.id.tvPositionBadge);
            tvName          = v.findViewById(R.id.tvName);
            tvPosition      = v.findViewById(R.id.tvPosition);
            tvTeam          = v.findViewById(R.id.tvTeam);
        }
    }
}
