package com.app.calendarproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.calendarproject.Adapter.calendarAdapter;

import java.util.Calendar;
import java.util.Locale;

public class CalendarAcivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView prevMonth;
    private ImageView nextMonth;
    RecyclerView calendarRecycler;
    calendarAdapter adapter;
    private Calendar _calendar;
    private int month, year;
    TextView currentMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_acivity);
        _calendar = Calendar.getInstance(Locale.getDefault());
        month = _calendar.get(Calendar.MONTH);
        year = _calendar.get(Calendar.YEAR);
        currentMonth = findViewById(R.id.currentMonth);
        prevMonth = findViewById(R.id.previousMonth);
        nextMonth = findViewById(R.id.nextMonth);
        prevMonth.setOnClickListener(this);
        nextMonth.setOnClickListener(this);
        calendarRecycler = findViewById(R.id.calenderRecycler);
        adapter = new calendarAdapter(this, month, year);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 7);
        calendarRecycler.setLayoutManager(mLayoutManager);
        calendarRecycler.setItemAnimator(new DefaultItemAnimator());
        calendarRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        currentMonth.setText(adapter.getMonth());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.previousMonth:
                    if (month <= 1){
                        month = 11;
                        year--;
                    } else{
                        month--;
                    }
                    adapter = new calendarAdapter(getApplicationContext(), month, year);
                    currentMonth.setText(adapter.getMonth());
                    _calendar.set(year, month, _calendar.get(Calendar.DAY_OF_MONTH));
                    calendarRecycler.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                break;
            case R.id.nextMonth:
                if (month >= 11){
                    month = 0;
                    year++;
                }else{
                    month++;
                }

                adapter = new calendarAdapter(getApplicationContext(), month, year);
                currentMonth.setText(adapter.getMonth());
                _calendar.set(year, month, _calendar.get(Calendar.DAY_OF_MONTH));
                calendarRecycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
