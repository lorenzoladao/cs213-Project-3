package com.example.three;

/**
 * This class extends NonResident class and includes specific data and operations to NonResident students in the Tristate area.
 *
 * @author Asif Haque, Margaret Knoebel, Lorenzo Ladao
 */
class Tristate extends NonResident{

    private static int NY_DISCOUNT = 4000;
    private static int CT_DISCOUNT = 5000;
    private String state;

    /**
     * Create a Tristate object with specified profile, with the name and major of student, credit hours,
     * and the state they occupy in.
     *
     * @param profile the name and major of the student in a Profile object
     * @param creditHours the number of credits the student is taking
     * @param state the state in the Tristate area, either NY or CT
     */
    public Tristate(Profile profile, int creditHours, String state) {
        super(profile, creditHours);
        this.state = state;
    }

    /**
     * Checks if student is a resident of NY or CT
     *
     * @return true if state is in NY, otherwise false
     */
    private boolean isNYState(){
        if (state.toLowerCase().compareTo("ny") == 0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Calculates the amount of tuition due for a Tristate Student. Three different calculations for if the student
     * is taking more than 16 credits, less than 12 credits, and an amount in between 12 and 16.
     * Subtracts the Discount respectively to the Student's state of residency
     */
    @Override
    public void tuitionDue(){
        super.tuitionDue();

        int credits = super.getCreditHours();
        double tuition = getTuition();
        if (credits > FULLTIME_CREDITS) {
            if (isNYState()) {
                super.setTuition(tuition - NY_DISCOUNT);
            }
            else {
                super.setTuition(tuition - CT_DISCOUNT);
            }
        }
    }

    /**
     * Returns a textual representation of a Tristate Student.
     * Uses the toString method from Non-Resident class and add more to the string.
     *
     * @return String that comprises the Profile, credit hours, tuition due, tuition paid,
     * and the latest payment date and ends in the type of residency and the state they reside in
     */
    @Override
    public String toString(){
        if(isNYState()) {
            return super.toString() + "(tri-state):NY";
        }
        else{
            return super.toString() + "(tri-state):CT";
        }
    }

}
