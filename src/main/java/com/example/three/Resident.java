package com.example.three;

import java.text.DecimalFormat;

/**
 * This class extends Student class and includes specific data and operations to resident students.
 *
 * @author Asif Haque, Margaret Knoebel, Lorenzo Ladao
 */
public class Resident extends Student{

    private static final int RESIDENT_FULLTIME_TUITION = 12536;
    private static final int RESIDENT_PARTIME_TUITION = 404;

    private double financialAid;
    private boolean recievedAid;

    /**
     * Create a Resident object with specified profile, with the name and major of student, and credit hours.
     * It also sets the amount of financial aid to 0.
     *
     * @param profile the name and major of the student in a Profile object
     * @param creditHours the number of credits the student is taking
     */
    public Resident(Profile profile, int creditHours){
        super(profile, creditHours);
        this.financialAid = 0;
        this.recievedAid = false;
    }

    /**
     * Calculates the amount of tuition due for a Resident Student. Three different calculations for if the student
     * is taking more than 16 credits, less than 12 credits, and an amount in between 12 and 16.
     */
    @Override
    public void tuitionDue(){
        int creditHours = super.getCreditHours();

        if (creditHours > ADDITIONAL_FEE_CREDITS){ //student is fulltime with more than 16 credits
            super.setTuition(RESIDENT_FULLTIME_TUITION + RESIDENT_PARTIME_TUITION * (creditHours - ADDITIONAL_FEE_CREDITS)
                    + FULLTIME_UNIVERSITY_FEE - financialAid - super.getTotalPayment());
        }
        else if (creditHours < FULLTIME_CREDITS){ //student is parttime
            super.setTuition((RESIDENT_PARTIME_TUITION * creditHours) + (PARTTIME_UNIVERSITY_FEE) - financialAid - super.getTotalPayment());
        }
        else { //student is fulltime with no more than 16 credits
            super.setTuition(RESIDENT_FULLTIME_TUITION + FULLTIME_UNIVERSITY_FEE - financialAid - super.getTotalPayment());
        }

    }

    /**
     * Sets the amount of financial aid to the specified amount.
     *
     * @param financialAid the amount of financial aid that needs to be set
     */
    public void setFinancialAid(double financialAid){
        this.financialAid = financialAid;
        this.recievedAid = true;
    }

    /**
     * Retrieves the amount of financial aid for the Student
     *
     * @return the amount of financial aid
     */
    public double getFinancialAid(){
        return this.financialAid;
    }

    public boolean isReceivedAid(){
        return this.recievedAid;
    }

    /**
     * Returns a textual representation of a Resident Student.
     * Uses the toString method from Student class and add more to the string.
     *
     *@return String that comprises the Profile, credit hours, tuition due, tuition paid,
     *and the latest payment date and ends in the type of residency
     */
    @Override
    public String toString() {
        DecimalFormat form = new DecimalFormat("#,##0.00");
        if(recievedAid){
            return super.toString() + ":resident:financial aid $" + form.format(financialAid);
        }
        return super.toString() + ":resident";
    }

}
