package com.example.three;

import java.text.DecimalFormat;

/**
 * This class defines the common data items and operations for all student instances.
 *
 * @author Asif Haque, Margaret Knoebel, Lorenzo Ladao
 */
public class Student {

    protected static final int FULLTIME_UNIVERSITY_FEE = 3268;
    protected static final double PARTTIME_UNIVERSITY_FEE = FULLTIME_UNIVERSITY_FEE * .8;
    public static final int FULLTIME_CREDITS = 12;
    protected static final int ADDITIONAL_FEE_CREDITS = 16;

    private Profile profile;
    private int creditHours;
    private double tuition;
    private Date paymentDate;
    private double totalPayment;

    /**
     * Create a Student object with specified profile, with the name and major of student, and credit hours.
     *
     * @param profile the name and major of the student in a Profile object
     * @param creditHours the number of credits the student is taking
     */
    public Student(Profile profile, int creditHours){
        this.profile = profile;
        this.creditHours = creditHours;
        this.tuition = 0;
        totalPayment = 0;
        this.paymentDate = null;
    }

    /**
     * A do nothing method for calculating the tuition. Will be overridden by its sub-classes
     */
    public void tuitionDue(){
    }

    /**
     * Returns a textual representation of a Student.
     *
     * @return String that comprises the Profile, credit hours, tuition due, tuition paid,
     * and the latest payment date
     */
    @Override
    public String toString(){
        DecimalFormat form = new DecimalFormat("#,##0.00");
        if (tuition < 0) //if the tuition is paid off and is negative, keep tuition due at 0
            tuition = 0;
        if (paymentDate == null) {
            return profile.toString() + ":" + creditHours + " credit hours:tuition due:" + form.format(tuition) + ":total payment:"
                    + form.format(totalPayment) + ":last payment date: --/--/--";
        }
        else{
            return profile.toString() + ":" + creditHours + " credit hours:tuition due:" + form.format(tuition) + ":total payment:"
                    + form.format(totalPayment) + ":last payment date: " + paymentDate;
        }
    }

    /**
     * Retrieve the Profile of the Student.
     *
     * @return Profile object of the Student
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Retrieve the number of credits of the Student.
     *
     * @return int number of credits the student is taking
     */
    public int getCreditHours(){
        return this.creditHours;
    }

    /**
     * Sets the lastest payment date to the one provided, adds the payment to the total payment paid,
     * and subtracts the total payment from the tuition that needs to be paid.
     *
     * @param payment the amount that is being paid at this time
     * @param paymentDate the Date when the payment is being made
     */
    public void addPayment(double payment, Date paymentDate){
        this.paymentDate = paymentDate;
        this.totalPayment += payment;
        this.tuition -= payment;
    }

    /**
     * Retrieves the last Date when a payment was made.
     *
     * @return a Date object of when the last payment was made
     */
    public Date getPaymentDate(){
        return paymentDate;
    }

    /**
     * Retrieves the total payment that the student has made.
     *
     * @return the total payment the student has made
     */
    protected double getTotalPayment(){
        return this.totalPayment;
    }

    /**
     * Sets the tuition to the one provided
     *
     * @param tuition the amount of tuition that is to be changed
     */
    protected void setTuition(double tuition){
        this.tuition = tuition;
    }


    /**
     * Sets the creditHours to the one provided
     *
     * @param credits the amount of credits that is to be changed
     */
    protected void setCredits(int credits){
        this.creditHours = credits;
    }

    /**
     * Resets the tuition, total payment to 0, and last payment date to null
     */
    protected void resetTuition(){
        this.tuition = 0;
        this.totalPayment = 0;
        this.paymentDate = null;
    }

    /**
     * Retrieves the tuition due
     *
     * @return the tuition due
     */
    public double getTuition() {
        return this.tuition;
    }

}
