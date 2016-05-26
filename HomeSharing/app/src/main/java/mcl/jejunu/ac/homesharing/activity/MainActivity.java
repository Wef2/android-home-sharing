package mcl.jejunu.ac.homesharing.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import mcl.jejunu.ac.homesharing.R;
import mcl.jejunu.ac.homesharing.fragment.FindFragment;
import mcl.jejunu.ac.homesharing.fragment.MyHomeFragment;
import mcl.jejunu.ac.homesharing.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getFragmentManager().beginTransaction().replace(R.id.fragment_layout, new FindFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_my_home) {
            getFragmentManager().beginTransaction().replace(R.id.fragment_layout, new MyHomeFragment()).commit();
            toolbar.setTitle("내 방 관리");
        } else if (id == R.id.nav_home_find) {
            getFragmentManager().beginTransaction().replace(R.id.fragment_layout, new FindFragment()).commit();
            toolbar.setTitle("숙소 찾기");
        } else if (id == R.id.nav_profile) {
            getFragmentManager().beginTransaction().replace(R.id.fragment_layout, new ProfileFragment()).commit();
            toolbar.setTitle("내 프로필");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
