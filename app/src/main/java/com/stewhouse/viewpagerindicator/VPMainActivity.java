package com.stewhouse.viewpagerindicator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class VPMainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPagerIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.button);
        btn1.setOnClickListener(this);
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(this);
        Button btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(this);
        Button btn4 = (Button) findViewById(R.id.button4);
        btn4.setOnClickListener(this);
        Button btn5 = (Button) findViewById(R.id.button5);
        btn5.setOnClickListener(this);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        indicator = (ViewPagerIndicator) findViewById(R.id.indicator);
        if (indicator != null) {
            indicator.setBackgroundColor(Color.BLACK);
            indicator.setIndicatorBGColor(Color.BLUE);
            indicator.setIndicatorColor(Color.GRAY);
            indicator.setIndicatorPage(5);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            indicator.setIndicatorIndex(1);
        } else if (v.getId() == R.id.button2) {
            indicator.setIndicatorIndex(2);
        } else if (v.getId() == R.id.button3) {
            indicator.setIndicatorIndex(3);
        } else if (v.getId() == R.id.button4) {
            indicator.setIndicatorIndex(4);
        } else if (v.getId() == R.id.button5) {
            indicator.setIndicatorIndex(5);
        }
    }
}
