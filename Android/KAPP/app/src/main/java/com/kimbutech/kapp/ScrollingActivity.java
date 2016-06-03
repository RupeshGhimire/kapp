package com.kimbutech.kapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ScrollingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static  int count = 0;
private String[] name;
   private String[] key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.nav_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        /*ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        */
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Bundle b = getIntent().getExtras();
        name = b.getStringArray("data");
        int a = Integer.parseInt(name[1]);
        if(a!=0){
            accessDatabase(a);
        }

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        assert fab2 != null;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name[0]="ScrollingActivity";
                Intent intent = new Intent(ScrollingActivity.this, MapsActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putStringArray("data", name);
                intent.putExtras(mBundle);

                startActivity(intent);
                finish();
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });



    }

    @Override
    public void onBackPressed() {

        Bundle b = getIntent().getExtras();
        String[] name = b.getStringArray("data");
        switch (name[0]){
            case "MainActivity":
                Intent intent = new Intent(ScrollingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

            case "MapsActivity":
                //name[1]="0";
                name[0]="MainActivity";
                Bundle mBundle = new Bundle();
                Intent nextIntent = new Intent(ScrollingActivity.this, MapsActivity.class);
                mBundle.putStringArray("data", name);
                nextIntent.putExtras(mBundle);
                startActivity(nextIntent);
                finish();
                break;
        }

    }

    public void displayInfo(String[] data){
        TextView body = (TextView) findViewById(R.id.body);
        TextView title = (TextView) findViewById(R.id.title);
        TextView description = (TextView) findViewById(R.id.description);
        TextView school= (TextView) findViewById(R.id.school);
        TextView dep= (TextView) findViewById(R.id.fixed);
        CollapsingToolbarLayout toolbar= (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        switch (data[0]){
            case "8":case "9":case "11":case "14":
                school.setText("School of Engineering");
            break;
            case "6":case "12":case "34":case "7":
                school.setText("School of Science");
                break;
            case "19":
                school.setText("Engineering/Science");
                break;
            case "3":
                school.setText("Library");


                break;
            case "1":
                school.setText("Central Perk");
                toolbar.setBackgroundResource(R.drawable.square);
                break;
            case "2":
                school.setText("Administration");
                toolbar.setBackgroundResource(R.drawable.ku);
                break;
            case "35":
                dep.setText("Nepal");
                school.setText("Department of Information Technology");
                toolbar.setBackgroundResource(R.drawable.itpark);
                break;
            default:
                school.setText("Blocks");
                break;
        }
        body.setText(data[3]);
        title.setText(data[1]);
        if(data[2]!=null){
            description.setText(data[2]);
        }


    }


    public void accessDatabase (int a ){
        String[] data;
        data =new String[6];
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        //title
        data[1]= databaseAccess.databaseToString(a, 2);
        //description
        data[2]= databaseAccess.databaseToString(a, 6);
        //blockno
        data[0] = databaseAccess.databaseToString(a, 1);
        //topdestination
        data[3] = databaseAccess.databaseToString(a, 5);
        //latitude
        data[4] = databaseAccess.databaseToString(a, 3);
        //longitude
        data[5]= databaseAccess.databaseToString(a, 4);
        //close database
        databaseAccess.close();
        displayInfo(data);

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
            Intent intent = new Intent(ScrollingActivity.this, about_us.class);
            startActivity(intent);
            finish();
            return true;
        } else if(id==android.R.id.home){

            Bundle b = getIntent().getExtras();
            String[] name = b.getStringArray("data");
            switch (name[0]){
                case "MainActivity":
                    Intent intent = new Intent(ScrollingActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;

                case "MapsActivity":
                    //name[1]="0";
                    name[0]="MainActivity";
                    Bundle mBundle = new Bundle();
                    Intent nextIntent = new Intent(ScrollingActivity.this, MapsActivity.class);
                    mBundle.putStringArray("data", name);
                    nextIntent.putExtras(mBundle);
                    startActivity(nextIntent);
                    finish();
                    break;
            }
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



}
