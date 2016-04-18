package com.bitcakecodes.kapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BlocksActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    List<String> departName;
    ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_block);

        String[] data = {
                "Main Square",
                "Administration",
                "Library",
                "C.V. Raman Auditorium",
                "Khetan Park",
                "School of Science",
                "School of Engineering"
        };
        departName = new ArrayList<String>(Arrays.asList(data));

        mAdapter =
                new ArrayAdapter<String>(this,
                        R.layout.list_block,
                        R.id.block,
                        departName
                );


        ListView listView= (ListView)findViewById(R.id.list_view);

        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dispDepartmentInfo(++position);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        } else {
            Intent intent_to_main = new Intent(BlocksActivity.this, MainActivity.class);
            startActivity(intent_to_main);
            finish();
            //super.onBackPressed();
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
            Intent intent_main = new Intent(BlocksActivity.this, MainActivity.class);
            startActivity(intent_main);
            finish();
        }

        else if(id == R.id.nav_blocks){
            Toast.makeText(this, "Blocks",
                    Toast.LENGTH_LONG).show();
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



        } else if (id == R.id.nav_send) {


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

        String [] key;
        key = new String[2];
        Intent intent = new Intent(this, ScrollingActivity.class);
        Bundle mBundle = new Bundle();
        key[0]="BlocksActivity";
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

            case 6:
                key[1]="6";
                break;
            case 7:
                key[1]="7";
                break;
            /*case R.id.eight:
                key[1]="8";
                break;
            case R.id.nine:
                key[1]="10";
                break;
            case R.id.ten:
                key[1]="10";
                break;
            case R.id.eleven:
                key[1]="9";
                break;
            case R.id.twelve:
                key[1]="12";
                break;
            case R.id.thirteen:
                key[1]="13";
                break;
            case R.id.fourteen:
                key[1]="14";
                break;
            case R.id.fifteen:
                key[1]="15";
                break;
            case R.id.sixteen:
                key[1]="16";
                break;
            case R.id.seventeen:
                key[1]="17";
                break;
            case R.id.eighteen:
                key[1]="18";
                break;
            case R.id.nineteen:
                key[1]="19";
                break;
            case R.id.twenty:
                key[1]="20";
                break;
            case R.id.twentyone:
                key[1]="21";
                break;
            case R.id.twentytwo:
                key[1]="22";
                break;
            case R.id.twentythree:
                key[1]="23";
                break;
            case R.id.twentyfour:
                key[1]="24";
                break;
            case R.id.twentyfive:
                key[1]="25";
                break;
            case R.id.twentysix:
                key[1]="26";
                break;
            case R.id.twentyseven:
                key[1]="27";
                break;
            case R.id.twentyeight:
                key[1]="28";
                break;
            case R.id.twentynine:
                key[1]="29";
                break;
            case R.id.thirty:
                key[1]="30";
                break;
            case R.id.thirtyone:
                key[1]="31";
                break;
            case R.id.thirtytwo:
                key[1]="32";
                break;
            case R.id.thirtythree:
                key[1]="33";
                break;
            case R.id.thirtyfour:
                key[1]="34";
                break;
*/
        }
        mBundle.putStringArray("data", key);
        intent.putExtras(mBundle);
        startActivity(intent);
        finish();

    }


}
