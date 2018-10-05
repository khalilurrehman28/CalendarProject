package com.app.calendarproject.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.calendarproject.Model.DateData;
import com.app.calendarproject.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by mandeep on 4/9/17.
 */

public class calendarAdapter extends RecyclerView.Adapter<calendarAdapter.MyViewHolder>  {
    private Context mContext;
    public List<DateData> dateDataList;
    Integer year,month;
    private Integer currentDayOfMonth;
    private Integer daysInMonth;
    private final List<String> list;
    private final String[] weekdays = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
    private final String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
    private final int[] daysOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView setDate;

        //public CardView card_view;
        public MyViewHolder(View view) {
            super(view);
            setDate = view.findViewById(R.id.setDate);
        }
    }

    public calendarAdapter(Context mContext, int month, int year) {
        this.mContext = mContext;
        this.month = month;
        this.year = year;
        this.dateDataList = new ArrayList<>();
        this.list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        this.currentDayOfMonth =  calendar.get(Calendar.DAY_OF_MONTH);
        printCalendar(month,year);
    }

    public String getMonth(){
        return months[month]+"-"+year;
    }


    private void printCalendar(int mm, int yy) {
        // The number of days to leave blank at
        // the start of this month.
        int trailingSpaces = 0;
        int daysInPrevMonth = 0;
        int prevMonth = 0;
        int prevYear = 0;
        int nextMonth = 0;
        int nextYear = 0;

        GregorianCalendar cal = new GregorianCalendar(yy, mm, currentDayOfMonth);

        // Days in Current Month
        daysInMonth = daysOfMonth[mm];
        int currentMonth = mm;
        if (currentMonth == 11){
            prevMonth = 10;
            daysInPrevMonth = daysOfMonth[prevMonth];
            nextMonth = 0;
            prevYear = yy;
            nextYear = yy + 1;
        } else if (currentMonth == 0){
            prevMonth = 11;
            prevYear = yy - 1;
            nextYear = yy;
            daysInPrevMonth = daysOfMonth[prevMonth];
            nextMonth = 1;
        } else{

            prevMonth = currentMonth - 1;
            nextMonth = currentMonth + 1;
            nextYear = yy;
            prevYear = yy;
            daysInPrevMonth = daysOfMonth[prevMonth];
        }

        // Compute how much to leave before before the first day of the
        // month.
        // getDay() returns 0 for Sunday.
        GregorianCalendar cal1 = new GregorianCalendar(yy, mm, 1);
        trailingSpaces = cal1.get(Calendar.DAY_OF_WEEK);
        if(trailingSpaces == 2){
            Log.d("Calendar", "trailingSpaces: Day Mon");
            trailingSpaces -=2;
        } else if (trailingSpaces==3){
            Log.d("Calendar", "printCalendar: Day Tues");
            trailingSpaces -=2;
        } else if (trailingSpaces==4){
            Log.d("Calendar", "printCalendar: Day Wed");
            trailingSpaces -=2;
        } else if (trailingSpaces==5){
            Log.d("Calendar", "printCalendar: Day thrus");
            trailingSpaces -=2;
        } else if (trailingSpaces==6){
            Log.d("Calendar", "printCalendar: Day fri");
            trailingSpaces -=2;
        } else if (trailingSpaces==7){
            Log.d("Calendar", "printCalendar: Day sat");
            trailingSpaces -=2;
        } else if (trailingSpaces==1){
            Log.d("Calendar", "printCalendar: Day sun");
            trailingSpaces =1;
        }
        Log.d("CalendarAdapter", "printCalendar: trainling "+trailingSpaces);
        if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 1) {
            ++daysInMonth;
        }

        // Trailing Month days
        for (int i = 0; i < trailingSpaces; i++) {
            DateData data = new DateData(R.color.colorGrey,(daysInPrevMonth - trailingSpaces + 1) + i,prevYear,months[prevMonth]);
            dateDataList.add(data);
            //list.add(String.valueOf((daysInPrevMonth - trailingSpaces + 1) + i) + "-GREY" + "-" + months[prevMonth] + "-" + prevYear);
        }

        // Current Month Days
        for (int i = 1; i <= daysInMonth; i++) {
            //list.add(String.valueOf(i) + "-WHITE" + "-" + months[mm] + "-" + yy);
            DateData data = new DateData(R.color.colorBlack,i,yy,months[mm]);
            dateDataList.add(data);

        }

        // Leading Month days
        for (int i = 0; i < dateDataList.size() % 7; i++) {
            Log.d("Calendar", "NEXT MONTH:= " + months[nextMonth]);
            //list.add(String.valueOf(i + 1) + "-GREY" + "-" + months[nextMonth] + "-" + nextYear);
            DateData data = new DateData(R.color.colorGrey,(i+1),nextYear,months[nextMonth]);
            dateDataList.add(data);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_date, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        DateData dateData= dateDataList.get(position);
        holder.setDate.setText(String.valueOf(dateData.getDate()));
        holder.setDate.setTag(String.valueOf(dateData.getDate()) + "-" + String.valueOf(dateData.getMonth()) + "-" + String.valueOf(dateData.getYear()));

        /*if (day_color[1].equals("GREY")) {
            holder.setDate.setTextColor(Color.LTGRAY);
        }
        if (day_color[1].equals("WHITE")) {
            holder.setDate.setTextColor(Color.BLACK);
        }
        if (position == currentDayOfMonth) {
            holder.setDate.setTextColor(Color.BLUE);
        }*/

        holder.setDate.setTextColor(mContext.getResources().getColor(dateData.getColor()));

    }

    @Override
    public int getItemCount() {
        return dateDataList.size();
    }


}
