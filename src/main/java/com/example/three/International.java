package com.example.three;

/**
 * This class extends NonResident class and includes specific data and operations for NonResident students that are International.
 *
 * @author Asif Haque, Margaret Knoebel, Lorenzo Ladao
 */
class International extends NonResident{
    private static final int ADDITIONAL_FEE = 2650;

    private boolean inStudyAbroad;

    /**
     * Create an International object with specified profile, with the name and major of student,credit hours,
     * and if the student is in study abroad.
     *
     * @param profile the name and major of the student in a Profile object
     * @param creditHours the number of credits the student is taking
     * @param inStudyAbroad boolean if student is in study abroad
     */
    public International(Profile profile, int creditHours, boolean inStudyAbroad) {
        super(profile, creditHours);
        this.inStudyAbroad = inStudyAbroad;
    }

    /**
     * Calculates the amount of tuition due for an International Student. Two different calculations for if the student
     * is in study abroad and if they are not in study abroad.
     */
    @Override
    public void tuitionDue(){
        if (inStudyAbroad){
            super.setTuition( FULLTIME_UNIVERSITY_FEE + ADDITIONAL_FEE - super.getTotalPayment());
        }
        else{
            int credits = super.getCreditHours();
            if (credits > ADDITIONAL_FEE_CREDITS)
                super.setTuition(NONRESIDENT_FULLTIME_TUITION + FULLTIME_UNIVERSITY_FEE + (NONRESIDENT_PARTTIME_TUITION * (credits - ADDITIONAL_FEE_CREDITS))
                        + ADDITIONAL_FEE - super.getTotalPayment());
            else
                super.setTuition(NONRESIDENT_FULLTIME_TUITION + FULLTIME_UNIVERSITY_FEE + ADDITIONAL_FEE - super.getTotalPayment());
        }
    }

    /**
     * Resets the tuition, total payment to 0, and last payment date to null.
     * Uses the method from Student class.
     */
    @Override
    public void resetTuition(){
        super.resetTuition();
    }

    /**
     * Sets the status of Study Abroad for the student to be true;
     */
    public void setStudyAbroadTrue(){
        this.inStudyAbroad = true;
        super.setCredits(FULLTIME_CREDITS);
    }

    public void setStudyAbroadFalse(){
        this.inStudyAbroad = false;
    }

    public boolean isStudyAbroad(){
        if(this.inStudyAbroad)
            return true;
        else
            return false;
    }
    /**
     * Returns a textual representation of an International Student.
     * Uses the toString method from Non-Resident class and add more to the string.
     *
     * @return String that comprises the Profile, credit hours, tuition due, tuition paid,
     * and the latest payment date and ends in the type of residency and if the student is study abroad
     */
    @Override
    public String toString(){
        if (inStudyAbroad) {
            return super.toString() + ":international:study abroad";
        }
        else{
            return super.toString() + ":international";
        }
    }


}
