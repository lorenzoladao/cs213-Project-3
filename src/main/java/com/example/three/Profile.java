package com.example.three;

/**
 * The profile class defines the profile of the student.
 * The class assigns a student's name and major to a profile
 * @author Asif Haque, Margaret Knoebel, Lorenzo Ladao
 */
public class Profile {
    private String name;
    private Major major; //5 majors and 2-charater each: CS, IT, BA, EE, ME

    /**
     * Takes the String name and Major major to create a Profile object
     * @param name of student
     * @param major of student
     */
    public Profile(String name, Major major) {
        this.name = name;
        this.major = major;
    }

    /**
     * Takes an Object obj and determines whether it is a profile.
     * If the object is a profile, it will see if the two profiles are the same.
     * @param obj the Object being checked if it is an instance of a Profile
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Profile) {
            Profile profile = (Profile)obj;
            if(this.name.equals(profile.name) && this.major.equals(profile.major)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Turns the profile into a string
     * @return String profile
     */
    @Override
    public String toString() {
        String profile = this.name + ":" + this.major;
        return profile;
    }

    /**
     * Returns the name of the Profile
     * @return String name
     */
    public String getName() {
        return this.name;
    }
}
