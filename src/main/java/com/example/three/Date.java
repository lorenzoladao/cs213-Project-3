package com.example.three;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 *This class defines the Date abstract data type with year, month, and day.
 *
 * @author Asif Haque, Margaret Knoebel, Lorenzo Ladao
 */
public class Date implements Comparable<Date> {

    private int year;
    private int month;
    private int day;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;
    public static final int THIS_CURRENT_YEAR = 2021;
    public static final int NUM_OF_TOKENS = 3;
    public static final int MONTH_ADJUSTER = 1;
    public static final int NUM_LEAP_YEAR_DAYS = 29;
    public static final int NUM_REG_FEB_DAYS = 28;
    public static final int THIRTY_MONTH_DAYS = 30;
    public static final int THIRTY_ONE_MONTH_DAYS = 31;
    public static final int EMPTY = 0;

    /**
     * Constructs a Date with the specified month, day, and year.
     * It tries Integer.parseInt but catches NumberFormatException if fails.
     *
     * @param date in the form "mm/dd/yyyy"
     */
    public Date(String date) { //take mm/dd/yyyy and create a Date object
        StringTokenizer token = new StringTokenizer(date, "/");
        if (token.countTokens() == NUM_OF_TOKENS) {
            try {
                month = Integer.parseInt(token.nextToken());
                day = Integer.parseInt(token.nextToken());
                year = Integer.parseInt(token.nextToken().trim());
            }
            catch (NumberFormatException error){
                month = EMPTY;
                day = EMPTY;
                year = EMPTY;
            }
        }
        else{
            month = EMPTY;
            day = EMPTY;
            year = EMPTY;
        }
    }

    /**
     * Constructs a Date with the current month, day, and year using the Java Calendar class
     */
    public Date() { //create an object with today’s date (see Calendar class)
        Calendar rightNow = Calendar.getInstance();
        year = rightNow.get(Calendar.YEAR);
        month = rightNow.get(Calendar.MONTH) + MONTH_ADJUSTER;
        day = rightNow.get(Calendar.DATE);
    }

    /**
     * Returns the string representation of Date
     *
     * @return a string in the form "mm/dd/yyyy"
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    /**
     * Returns what the day is of the Date
     *
     * @return the day of the Date
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns what the month is of the Date
     *
     * @return the month of the Date
     */
    public int getMonth() {
        return month;
    }

    /**
     * Returns what the year is of the Date
     *
     * @return the year of the Date
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns whether the year, month, and day are valid dates as an existing leap year
     *
     * @return true if the Date is a leap year; false otherwise
     */
    private boolean isLeapYear() {
        if ( year % QUADRENNIAL == 0 ) {
            if ( year % CENTENNIAL == 0 ) {
                if ( year % QUARTERCENTENNIAL == 0 ) {
                    return true;
                }
            }
            else{
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether the Date is within the year 2021
     *
     * @return true if the year is equal to 2021; false otherwise
     */
    private boolean withinTwentyTwentyOne(){
        if ( year != THIS_CURRENT_YEAR){
            return false;
        }
        return true;
    }

    /**
     * Returns whether the Date  before the current date
     *
     * @return true if the Date is older than the current date; false otherwise
     */
    private boolean isBeforeCurrentDate(){
        Date today = new Date();
        if (year > today.getYear()){
            return false;
        }
        if (year == today.getYear()) { //Not valid if the date is newer than today's date
            if (month > today.getMonth()) {
                return false;
            } else if (month == today.getMonth()) {
                if (day > today.getDay()) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Returns whether the Date is within the time frame given, the month is a valid number (1-12), and
     * the day is a valid number for its corresponding month (1-31).
     *
     * @return true if Date is within time frame, valid month, and valid day; false otherwise
     */
    public boolean isValid() {
        int adjust_month = month - MONTH_ADJUSTER;
        if (!withinTwentyTwentyOne() || !isBeforeCurrentDate()){
            return false;
        }
        if ( adjust_month < Calendar.JANUARY || adjust_month > Calendar.DECEMBER ){
            return false;
        }
        if ( adjust_month == Calendar.FEBRUARY ){
            if ( isLeapYear() && day > NUM_LEAP_YEAR_DAYS ){
                return false;
            }
            else if( !isLeapYear() && day > NUM_REG_FEB_DAYS ){
                return false;
            }
        }
        else if ( adjust_month == Calendar.APRIL || adjust_month == Calendar.JUNE || adjust_month == Calendar.SEPTEMBER
                || adjust_month == Calendar.NOVEMBER ){
            if ( day > THIRTY_MONTH_DAYS ){
                return false;
            }
        }
        else{
            if ( day > THIRTY_ONE_MONTH_DAYS) {
                return false;
            }
        }
        return true;
    }

    private static final int EQUAL_TO = 0;
    private static final int RECENT_DATE = 1;
    private static final int OLDER_DATE = -1;

    /**
     * Compares the year, month, and day values represented by two Date objects
     *
     * @param date the Date to be compared with
     * @return the value 0 if the dates are equal; the value 1 if the date is more recent than the Date represented
     * by the argument; and the value of -1 if the date is older than the Date represented by the argument
     */
    @Override
    public int compareTo(Date date) {
        if ( this.year > date.getYear() ){
            return RECENT_DATE;
        }
        else if ( this.year < date.getYear() ){
            return OLDER_DATE;
        }
        else {
            if ( this.month > date.getMonth() ){
                return RECENT_DATE;
            }
            else if ( this.month < date.getMonth() ){
                return OLDER_DATE;
            }
            else{
                if ( this.day > date.getDay() ){
                    return RECENT_DATE;
                }
                else if ( this.day < date.getDay() ){
                    return OLDER_DATE;
                }
            }
        }
        return EQUAL_TO;
    }

}
