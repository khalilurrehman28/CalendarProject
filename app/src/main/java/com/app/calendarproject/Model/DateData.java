package com.app.calendarproject.Model;

public class DateData {
    int color;
    int totalDays;
    int year;
    String month;

    public DateData(int color, int totalDays, int year, String month) {
        this.color = color;
        this.totalDays = totalDays;
        this.year = year;
        this.month = month;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getDate() {
        return totalDays;
    }

    public void setDate(int date) {
        this.totalDays = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
