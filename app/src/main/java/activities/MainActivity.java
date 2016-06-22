package activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.aqurytech.pinetree.R;
import com.parse.ParseUser;

import fragments.DiscoverFragment;
import fragments.FavoritesFragment;
import fragments.InboxFragment;
import fragments.MyAccountFragment;
import fragments.MyTreesFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser == null){
            logout();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PlantTreeActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);




        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DiscoverFragment fragment = new DiscoverFragment();
        fragmentTransaction.replace(R.id.content_fragment, fragment).commit();

        navigationView.setCheckedItem(R.id.nav_discover);

        // get location
        // update title with location
        // retrieve posts in location
        // display posts in location

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (id == R.id.nav_discover) {
            DiscoverFragment fragment = new DiscoverFragment();
            fragmentTransaction.replace(R.id.content_fragment, fragment).commit();
        } else if (id == R.id.nav_my_trees) {
            MyTreesFragment fragment = new MyTreesFragment();
            fragmentTransaction.replace(R.id.content_fragment, fragment).commit();
        } else if (id == R.id.nav_favorites) {
            FavoritesFragment fragment = new FavoritesFragment();
            fragmentTransaction.replace(R.id.content_fragment, fragment).commit();
        } else if (id == R.id.nav_inbox) {
            InboxFragment fragment = new InboxFragment();
            fragmentTransaction.replace(R.id.content_fragment, fragment).commit();
        } else if (id == R.id.nav_account) {
            MyAccountFragment fragment = new MyAccountFragment();
            fragmentTransaction.replace(R.id.content_fragment, fragment).commit();
        } else if (id == R.id.nav_signout) {
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    private void logout(){
        ParseUser.logOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }


}
