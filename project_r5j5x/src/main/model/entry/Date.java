package model.entry;

// Represents a date in which is the date that the entry is written for with year month and day
public class Date {
    private int year;
    private int month;
    private int day;

    // EFFECTS: constructs Date with given argument year, month, and day
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // REQUIRES: year must be an integer greater or equal to 0
    public void setYear(int year) {
        this.year = year;
    }

    // REQUIRES: month must be an integer between 1-12 (inclusive)
    public void setMonth(int month) {
        this.month = month;
    }

    // REQUIRES: day must be an integer between 1-31 (inclusive)
    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    // EFFECTS: returns a string that represents the date of this object with the formating of
    //          year/month/day
    public String viewDate() {
        return (Integer.toString(this.year) + "/" + Integer.toString(this.month) + "/" + Integer.toString(this.day));
    }
}
