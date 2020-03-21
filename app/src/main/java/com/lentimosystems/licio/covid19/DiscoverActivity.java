package com.lentimosystems.licio.covid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import www.sanju.motiontoast.MotionToast;

public class DiscoverActivity extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        chipNavigationBar = findViewById(R.id.bottom_nav);
        chipNavigationBar.setItemSelected(R.id.home,true);

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.home:
                        Intent intent = new Intent(DiscoverActivity.this,MainActivity.class);
                        startActivity(intent);
                    case R.id.discover:
                        MotionToast.Companion.createToast(DiscoverActivity.this, "Coming Soon!",
                                MotionToast.Companion.getTOAST_INFO(),
                                MotionToast.Companion.getGRAVITY_CENTER(),
                                MotionToast.Companion.getLONG_DURATION(),
                                ResourcesCompat.getFont(DiscoverActivity.this, R.font.helvetica_regular));
                    case R.id.learn:
                        MotionToast.Companion.createToast(DiscoverActivity.this, "Coming Soon!",
                                MotionToast.Companion.getTOAST_INFO(),
                                MotionToast.Companion.getGRAVITY_CENTER(),
                                MotionToast.Companion.getLONG_DURATION(),
                                ResourcesCompat.getFont(DiscoverActivity.this, R.font.helvetica_regular));
                }
            }
        });
    }
}
