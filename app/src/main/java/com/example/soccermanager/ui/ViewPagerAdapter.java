package com.example.soccermanager.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return TeamsFragment.newInstance();
            case 1: return PlayersFragment.newInstance();
            case 2: return MatchesFragment.newInstance();
            default: throw new IllegalArgumentException("Unknown tab position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
