package vn.edu.tlu;

import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.edu.tlu.ui.BaseActivity;

public class MainActivity extends BaseActivity {

    private BottomNavigationView bottomMenu;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        bottomMenu = findViewById(R.id.bottomMenu);

    }

    @Override
    protected void initData() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();
        bottomMenu.setSelectedItemId(R.id.nav_home); // đồng bộ trạng thái menu
    }

    @Override
    protected void initListeners() {
        bottomMenu.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.nav_history) {
                selectedFragment = new HistoryFragment();
            } else if (id == R.id.nav_rental) {
                selectedFragment = new RentalFragment();
            } else if (id == R.id.nav_account) {
                selectedFragment = new AccountFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }

            return true;
        });
    }



}