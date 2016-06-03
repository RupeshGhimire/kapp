package com.kimbutech.kapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class
MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String[] key;
    DatabaseAccess databaseAccess;

    int content;


    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    List<String> departName;
    List<String> departNo;
    ArrayAdapter<String> mAdapter;
    ArrayAdapter<String> mAdapterNum;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    Location userLocation;

    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key = new String[2];
                Bundle mBundle = new Bundle();
                key[0] = "MainActivity";

                key[1] = "MM";
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                mBundle.putStringArray("data", key);
                intent.putExtras(mBundle);
                startActivity(intent);
                finish();
            }
        });
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed();
                return;
            } else {

                Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show();
            }

            mBackPressed = System.currentTimeMillis();
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
        if (id == R.id.about) {
            Intent intent = new Intent(MainActivity.this, about_us.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ku) {
            Toast.makeText(this, "About Kathmandu University",
                    Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_blocks_KUSOA) {
            Toast.makeText(this, "About Kathmandu University",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_blocks_KUSOL) {
            Toast.makeText(this, "About Kathmandu University",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_blocks_KUSOM) {
            Toast.makeText(this, "About Kathmandu University",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_blocks_KUSMS) {
            Toast.makeText(this, "About Kathmandu University",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.calendar) {
            Intent intent = new Intent(this, calendar.class);
           startActivity(intent);
            finish();
        } else if (id == R.id.nav_itPark) {
            Bundle mBundle = new Bundle();
            key = new String[3];
            key[0] = "MainActivity";
            key[1] = "35";
            Intent intent = new Intent(this, ScrollingActivity.class);
            mBundle.putStringArray("data", key);
            intent.putExtras(mBundle);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_us) {
            Intent intent = new Intent(this, about_us.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void dispDepartmentInfo(int position) {

        key = new String[3];
        Intent intent = new Intent(this, ScrollingActivity.class);
        Bundle mBundle = new Bundle();
        key[0] = "MainActivity";
        //this is different than the original code because of the list view
        switch (position) {
            case 1:

                key[1] = "1";

                break;
            case 2:

                key[1] = "2";

                break;
            case 3:

                key[1] = "3";

                break;
            case 4:

                key[1] = "4";

                break;
            case 5:

                key[1] = "5";

                break;

            /*case R.id.fourteen:

                key[1]="14";

                break;*/

        }
        mBundle.putStringArray("data", key);
        intent.putExtras(mBundle);
        startActivity(intent);
        finish();

    }

    public void searchDatabase(View view) {
        EditText searchView = (EditText) findViewById(R.id.search);
        String text = searchView.getText().toString();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        ArrayList<String> list = new ArrayList<String>();

        String[] listString;
        int x = databaseAccess.noofsearch(text);
        listString = new String[x];


        databaseAccess.close();
        if (x == 0) {
            Snackbar.make(view, "Not Found! Try Again!!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            list = databaseAccess.searchrecord(text);
            int i = 0;
            for (String s : list) {
                listString[i] = s;
                i++;
            }
            Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
            displayMarker(listString);
        }


    }

    public void displayMarker(String[] data) {
        String[] name;
        name = new String[3];
        name[1] = data[0];
        name[0] = "MainActivity";
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putStringArray("data", name);
        intent.putExtras(mBundle);

        startActivity(intent);
        finish();
    }

    /*public Location getLocation() {
        LocationManager mLocationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (!isGPSEnabled && !isNetworkEnabled) {
            Toast.makeText(MainActivity.this, "Enable GPS or Network", Toast.LENGTH_SHORT).show();
        } else {
            if (isGPSEnabled) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                }

            }
            else if(isNetworkEnabled){

            }
        }
        return userLocation;
    }*/


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    return KUMain.newInstance(position + 1);
                case 1:
                    return KuSms.newInstance(position + 1);
                case 2:
                    return KUSom.newInstance(position + 1);
                case 3:
                    return KUSoa.newInstance(position + 1);
                case 4:
                    return KUSol.newInstance(position + 1);
            }

            return null;
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "KU";
                case 1:
                    return "KUSMS";
                case 2:
                    return "KUSOM";
                case 3:
                    return "KUSOA";
                case 4:
                    return "KUSOL";
            }
            return null;
        }
    }



}