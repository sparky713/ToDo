package model;

// Represents a date with year, month, and day of the month
public class Date {

    private int year;
    private int month;
    private int day;

    // REQUIRES: year >= current year (2021), 0 < month <= 12, 0 < day <= 31
    // EFFECTS: makes a new date with year, month, and day
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // setters & getters
    // REQUIRES: year >= current year (2021)
    // MODIFIES: this
    // EFFECTS: sets the year for this date to given year
    public void setYear(int year) {
        this.year = year;
    }

    // REQUIRES: 0 < month <= 12
    // MODIFIES: this
    // EFFECTS: sets the month for this date to given month
    public void setMonth(int month) {
        this.month = month;
    }

    // REQUIRES: 0 < day <= 31
    // MODIFIES: this
    // EFFECTS: sets the day for this date to given day
    public void setDay(int day) {
        this.day = day;
    }

    // EFFECTS: returns the year
    public int getYear() {
        return this.year;
    }

    // EFFECTS: returns the month
    public int getMonth() {
        return this.month;
    }

    // EFFECTS: returns the day
    public int getDay() {
        return this.day;
    }
}
