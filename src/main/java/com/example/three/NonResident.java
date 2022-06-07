package com.example.three;

/**
 * This class extends Student class and includes specific data and operations to non-resident students.
 *
 * @author Asif Haque, Margaret Knoebel, Lorenzo Ladao
 */
public class NonResident extends Student{

    protected static final int NONRESIDENT_FULLTIME_TUITION = 29737;
    protected static final int NONRESIDENT_PARTTIME_TUITION = 966;

    /**
     * Create a NonResident object with specified profile, with the name and major of student, and credit hours.
     *
     * @param profile the name and major of the student in a Profile object
     * @param creditHours the number of credits the student is taking
     */
    public NonResident(Profile profile, int creditHours) {
        super(profile, creditHours);
    }

    /**
     * Calculates the amount of tuition due for a Non-Resident Student. Three different calculations for if the student
     * is taking more than 16 credits, less than 12 credits, and an amount in between 12 and 16.
     */
    @Override
    public void tuitionDue(){
        int creditHours = super.getCreditHours();

        if (creditHours > ADDITIONAL_FEE_CREDITS){ //student is fulltime with more than 16 credits
            super.setTuition(NONRESIDENT_FULLTIME_TUITION + NONRESIDENT_PARTTIME_TUITION * (creditHours - ADDITIONAL_FEE_CREDITS)
                    + FULLTIME_UNIVERSITY_FEE - super.getTotalPayment());
        }
        else if (creditHours < FULLTIME_CREDITS){ //student is parttime
            super.setTuition((NONRESIDENT_PARTTIME_TUITION * creditHours) + PARTTIME_UNIVERSITY_FEE - super.getTotalPayment());
        }
        else { //student is fulltime with no more than 16 credits
            super.setTuition(NONRESIDENT_FULLTIME_TUITION + FULLTIME_UNIVERSITY_FEE - super.getTotalPayment());
        }

    }

    /**
     * Returns a textual representation of a Non-Resident Student.
     * Uses the toString method from Student class and add more to the string.
     *
     * @return String that comprises the Profile, credit hours, tuition due, tuition paid,
     * and the latest payment date and ends in the type of residency
     */
    @Override
    public String toString(){
        return super.toString() + ":non-resident";
    }

}
