package com.bitcakecodes.kapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    String [] key;
    DatabaseAccess databaseAccess;


    private static int x=1;
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    List<String> departName;
    List<String> departNo;
    ArrayAdapter<String> mAdapter;
    ArrayAdapter<String> mAdapterNum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (x == 1) {
            Intent startScreen = new Intent(this, splash.class);
            startActivity(startScreen);
            x=2;
        }


        setContentView(R.layout.activity_main);

        String[] data = {
                "Main Square",
                "Administration",
                "Library",
                "C.V. Raman Auditorium",
                "Khetan Park"

        };
        String[] depno = {
                "01",
                "02",
                "03",
                "04",
                "04",
                "06",
                "07"
        };

        departName = new ArrayList<String>(Arrays.asList(data));
        departNo = new ArrayList<String>(Arrays.asList(depno));

        mAdapter =
                new ArrayAdapter<String>(this,
                        R.layout.home_screen,
                        R.id.header,
                        departName
                );
        mAdapterNum =
                new ArrayAdapter<String>(this,
                        R.layout.list_block,
                        R.id.departmentNumber,
                        departNo
                );
        //inflate the header view
        View header= LayoutInflater.from(this).inflate(R.layout.list_header,null);
        //create a list view
        ListView listView= (ListView)findViewById(R.id.list_view);
        //add header view to the list view
        listView.addHeaderView(header, null, false);
        //set adapter to display the data from the array declared above

        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dispDepartmentInfo(position);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key = new String[2];
                Bundle mBundle = new Bundle();
                key[0]="MainActivity";

                key[1]="MM";
                Intent intent= new Intent(MainActivity.this , MapsActivity.class);
                mBundle.putStringArray("data", key);
                intent.putExtras(mBundle);
                startActivity(intent);
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
            {
                super.onBackPressed();
                return;
            }
            else { Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show(); }

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

        if (id == R.id.nav_ku) {
            Toast.makeText(this, "About Kathmandu University",
                    Toast.LENGTH_LONG).show();

        }



        else if(id == R.id.nav_blocks){
            Intent intent_block = new Intent(MainActivity.this, BlocksActivity.class);
            startActivity(intent_block);
            finish();
        }
        else if (id == R.id.nav_itPark) {
            Bundle mBundle = new Bundle();
            key = new String[3];
            key[0]="MainActivity";
            key[1]="35";
            Intent intent = new Intent(this, ScrollingActivity.class);
            mBundle.putStringArray("data", key);
            intent.putExtras(mBundle);
            startActivity(intent);
            finish();

        }

        else if (id == R.id.nav_us) {
            Intent intent = new Intent(this, about_us.class);
            startActivity(intent);
            finish();

        }

        else if (id == R.id.nav_share) {

            // Create the text message with a string
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "I am in Computer Department");
            sendIntent.setType("text/plain");

            // Verify that the intent will resolve to an activity
            if (sendIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(sendIntent);
            }
            else{
                Toast.makeText(this, "Cannot open SMS app.", Toast.LENGTH_SHORT).show();
            }



        }
        else if (id == R.id.nav_send) {


            Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
            sendIntent.setData(Uri.parse("smsto:"));

            sendIntent.putExtra("sms_body", "I am in computer department");

            if (sendIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(sendIntent);
            }
            else{
                Toast.makeText(this, "Cannot open SMS app.", Toast.LENGTH_SHORT).show();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void dispDepartmentInfo(int position){

        key = new String[3];
        Intent intent = new Intent(this, ScrollingActivity.class);
        Bundle mBundle = new Bundle();
        key[0]="MainActivity";
        //this is different than the original code because of the list view
        switch (position) {
            case 1:

                key[1]="1";

                break;
            case 2:

                key[1]="2";

                break;
            case 3:

                key[1]="3";

                break;
            case 4:

                key[1]="4";

                break;
            case 5:

                key[1]="5";

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
public void searchDatabase(View view){
    EditText searchView = (EditText) findViewById(R.id.search);
    String text = searchView.getText().toString();
    
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        ArrayList<String> list = new ArrayList<String>();

        String [] listString;
        int x = databaseAccess.noofsearch(text);
        listString = new String[x];


        databaseAccess.close();
        if (x==0){
            Snackbar.make(view, "Payaena Hai Arko Khoja sathi :P", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else{
            list = databaseAccess.searchrecord(text);
            int i=0;
            for (String s : list)
            {
                listString[i]= s;
                i++;
            }
            Toast.makeText(MainActivity.this,listString[0], Toast.LENGTH_SHORT).show();
            displayMarker(listString);
        }




}

    public void displayMarker(String[] data){
        String []name;
        name = new String[3];
        name[1]=data[0];
        name[0]="MainActivity";
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putStringArray("data", name);
        intent.putExtras(mBundle);

        startActivity(intent);
        finish();
    }

}