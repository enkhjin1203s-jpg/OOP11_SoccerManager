package com.example.soccermanager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.example.soccermanager.ui.MatchesFragment;
import com.example.soccermanager.ui.PlayersFragment;
import com.example.soccermanager.ui.TeamsFragment;
import com.example.soccermanager.ui.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Manual toolbar setup (theme is NoActionBar)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // ViewPager2 + Adapter
        viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        // Attach tab labels to ViewPager2 — lambda inside TabLayoutMediator
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0: tab.setText("TEAMS");   break;
                case 1: tab.setText("PLAYERS"); break;
                case 2: tab.setText("MATCHES"); break;
            }
        }).attach();
    }

    // -----------------------------------------------------------------------
    // Toolbar menu — search + sort
    // -----------------------------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search…");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dispatchSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.trim().isEmpty()) dispatchReset();
                else dispatchSearch(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if      (id == R.id.sort_by_name)      { dispatchSortByName();      return true; }
        else if (id == R.id.sort_by_secondary) { dispatchSortBySecondary(); return true; }
        else if (id == R.id.sort_reset)        { dispatchReset();           return true; }
        return super.onOptionsItemSelected(item);
    }

    private void dispatchSearch(String query) {
        switch (viewPager.getCurrentItem()) {
            case 0: teams().search(query);   break;
            case 1: players().search(query); break;
            case 2: matches().search(query); break;
        }
    }

    private void dispatchReset() {
        switch (viewPager.getCurrentItem()) {
            case 0: teams().showAll();   break;
            case 1: players().showAll(); break;
            case 2: matches().showAll(); break;
        }
    }

    private void dispatchSortByName() {
        switch (viewPager.getCurrentItem()) {
            case 0: teams().sortByName();       break;
            case 1: players().sortByName();     break;
            case 2: matches().sortByHomeTeam(); break;
        }
    }

    private void dispatchSortBySecondary() {
        switch (viewPager.getCurrentItem()) {
            case 0: teams().sortByLeague();      break;
            case 1: players().sortByPosition();  break;
            case 2: matches().sortByAwayTeam();  break;
        }
    }

    private TeamsFragment teams() {
        return (TeamsFragment) getSupportFragmentManager().findFragmentByTag("f0");
    }

    private PlayersFragment players() {
        return (PlayersFragment) getSupportFragmentManager().findFragmentByTag("f1");
    }

    private MatchesFragment matches() {
        return (MatchesFragment) getSupportFragmentManager().findFragmentByTag("f2");
    }
}
