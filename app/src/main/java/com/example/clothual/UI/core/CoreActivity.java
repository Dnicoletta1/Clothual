package com.example.clothual.UI.core;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.clothual.R;
import com.example.clothual.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CoreActivity extends AppCompatActivity {
    public FragmentHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core_layout);


        Toolbar toolbar = findViewById(R.id.top_appbar);
        setSupportActionBar(toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().
                findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.calendarFragment,
                R.id.photoFragment, R.id.photoFragment, R.id.personalFragment).build();


        // For the Toolbar
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // For the BottomNavigationView
        NavigationUI.setupWithNavController(bottomNav, navController);

    }

}
