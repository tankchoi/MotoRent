package com.example.motorentmobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.motorentmobile.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayoutResourceId();
    protected abstract int getNavigationMenuItemId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        FrameLayout container = findViewById(R.id.container);
        LayoutInflater.from(this).inflate(getLayoutResourceId(), container, true);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setSelectedItemId(getNavigationMenuItemId());

        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == getNavigationMenuItemId()) return true;

            if (itemId == R.id.nav_home) {
//                startActivity(new Intent(this, HomeActivity.class));
            } else if (itemId == R.id.nav_history) {

            } else if (itemId == R.id.nav_rental) {

            } else if (itemId == R.id.nav_account) {

            }
            finish(); // tránh chồng Activity
            return true;
        });
    }
}
