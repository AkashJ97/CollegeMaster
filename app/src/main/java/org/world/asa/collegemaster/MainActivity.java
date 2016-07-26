package org.world.asa.collegemaster;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import org.world.asa.collegemaster.notes.MyNotes;
import org.world.asa.collegemaster.stopwatch.StopwatchActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    ListView listView;
    String[] activities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.drawerList);
        activities = getResources().getStringArray(R.array.activities);
        listView.setAdapter(new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 , activities));
        listView.setOnItemClickListener(this);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this , drawerLayout ,R.mipmap.ic_menu,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_menu);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        drawerToggle.syncState();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0) {
            Intent intent = new Intent(this, MyNotes.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, StopwatchActivity.class);
            startActivity(intent);

        }


    }
}
