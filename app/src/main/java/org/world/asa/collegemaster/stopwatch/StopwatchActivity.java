package org.world.asa.collegemaster.stopwatch;

import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import org.world.asa.collegemaster.R;
import java.util.ArrayList;

public class StopwatchActivity extends AppCompatActivity {

    ArrayList<String> list =new ArrayList<String>();
    ArrayAdapter<String> adapter;

        Button butnstart, butnreset , btnlap;
        TextView time;
        ListView listView;
        long starttime = 0L;
        long timeInMillis = 0L;
        long timeSwapBuff = 0L;
        long updatedtime = 0L;
        int t = 1;
        int secs = 0;
        int mins = 0;
        int millis = 0;
        boolean running = false;
        Handler handler = new Handler();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_stopwatch);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

            listView = (ListView)findViewById(R.id.list);

            listView.setAdapter(adapter);
            butnstart = (Button) findViewById(R.id.start);
            butnreset = (Button) findViewById(R.id.reset);
            time = (TextView) findViewById(R.id.timer);
            btnlap = (Button)findViewById(R.id.lap);
            butnstart.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    if (t == 1) {
                        butnstart.setText("PAUSE");
                        starttime = SystemClock.uptimeMillis();
                        handler.postDelayed(updateTimer, 0);
                        t = 0;
                        running = true;
                    } else {
                        butnstart.setText("START");
                        time.setTextColor(Color.BLUE);
                        timeSwapBuff += timeInMillis;
                        handler.removeCallbacks(updateTimer);
                        t = 1;
                        running = false;
                    }
                }
            });



            btnlap.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {



                    if(running == true) {
                        timeInMillis = SystemClock.uptimeMillis() - starttime;
                        updatedtime = timeSwapBuff + timeInMillis;
                        secs = (int) (updatedtime / 1000);
                        mins = secs / 60;
                        secs = secs % 60;
                        millis = (int) (updatedtime % 1000);
                        String s = (String.format("%02d", mins) + " " + ":" + " " + String.format("%02d", secs) + " " + ":"
                                + " " + String.format("%03d", millis));

                        list.add(s);
                        adapter.notifyDataSetChanged();
                    }


                }


            });



    butnreset.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    starttime = 0L;
                    timeInMillis = 0L;
                    timeSwapBuff = 0L;
                    updatedtime = 0L;
                    t = 1;
                    secs = 0;
                    mins = 0;
                    millis = 0;
                    butnstart.setText("START");
                    handler.removeCallbacks(updateTimer);
                    time.setText("00 : 00 : 000");
                    running = false;
                }
            });
        }
        public Runnable updateTimer = new Runnable() {
            public void run() {
                timeInMillis = SystemClock.uptimeMillis() - starttime;
                updatedtime = timeSwapBuff + timeInMillis;
                secs = (int) (updatedtime / 1000);
                mins = secs / 60;
                secs = secs % 60;
                millis = (int) (updatedtime % 1000);
                time.setText(String.format("%02d", mins) + " " + ":" + " " + String.format("%02d", secs) + " " + ":"
                        + " " + String.format("%03d", millis));
                time.setTextColor(Color.CYAN);
                handler.postDelayed(this, 0);
            }};


}
