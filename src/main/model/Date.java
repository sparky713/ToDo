package model;

public class Date {

    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // setters & getters
    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear(int year) {
        return this.year;
    }

    public int getMonth(int month) {
        return this.month;
    }

    public int getDay(int day) {
        return this.day;
    }
}
