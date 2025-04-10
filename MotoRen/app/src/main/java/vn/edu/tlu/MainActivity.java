package vn.edu.tlu;

import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

import vn.edu.tlu.R;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.edu.tlu.ui.BaseActivity;

public class MainActivity extends BaseActivity {

    private BottomNavigationView bottomMenu;
    private Fragment selectedFragment;
    private FrameLayout fragmentContainer;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        bottomMenu = findViewById(R.id.bottomMenu);

        fragmentContainer = findViewById(R.id.fragment_container);

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
//        bottomMenu.setOnItemSelectedListener(item -> {
//            selectedFragment = null;
//            switch (item.getItemId()) {
//                case R.id.nav_home:
//                    selectedFragment = new HomeFragment();
//                    break;
//                case R.id.nav_account:
//                    selectedFragment = new AccountFragment();
//                    break;
//                case R.id.nav_history:
//                    selectedFragment = new HistoryFragment();
//                    break;
//                case R.id.nav_rental:
//                    selectedFragment = new RentalFragment();
//                    break;
//
//
//            }
//
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_container, selectedFragment)
//                    .commit();
//
//            return true;
//        });

    }



}