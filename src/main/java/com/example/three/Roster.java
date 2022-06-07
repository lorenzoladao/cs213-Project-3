package com.example.three;

/**
 * This class holds the array of students and provides operations to manage the roster
 * The roster class can add, delete, and print students.
 * @author Asif Haque, Margaret Knoebel, Lorenzo Ladao
 */
public class Roster {
    private Student[] roster;
    private int size; //keep track of the number of students in the roster ...

    /**
     * Sets size to zero and declares the student array
     */
    public Roster() {
        size = 0;
        roster = new Student[4];
    }

    /**
     * Finds student in the roster and returns its index.
     * If the student is not in the array, it returns -1.
     * @param student the name of student
     * @return Integer
     */
    private int find(Student student) {
        for(int i = 0; i < roster.length; i++) {
            if(roster[i] !=null) {
                if(student.getProfile().equals(roster[i].getProfile())) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * If the roster has filled, the array will grow by 4
     */
    private void grow() {
        Student[] newList = new Student[roster.length + 4];

        for(int i = 0; i < roster.length; i++) {
            newList[i] = roster[i];
        }
        roster = new Student[newList.length];
        for(int i = 0; i < roster.length; i++) {
            roster[i] = newList[i];
        }
    }

    /**
     * This method will add a student to the roster if it is not already there
     * @param student the namne of student
     * @return true or false
     */
    public boolean add(Student student) {

        if(find(student) != -1) {
            return false;
        }

        for (int i = 0; i < roster.length; i++) {
            if (roster[i] == null) {
                roster[i] = student;
                size++;
                return true;
            }
        }

        if(size == roster.length) {
            grow();

            roster[size] = student;
            size++;
            return true;
        }
        return false;

    }

    /**
     * This method will remove a student from the roster if it is there
     * @param student the name of student
     * @return true or false
     */
    public boolean remove(Student student) {
        int index = find(student);
        if(index == -1) {
            return false;
        }

        if(roster[index] == null) {
            return false;
        }

        for(int i = index; i < roster.length - 1; i++) {
            roster[i] = roster[i + 1];
        }
        roster[roster.length - 1] = null;
        size--;
        return true;
    }

    /**
     * This method will print the roster in no specific order.
     */
    public void print() {
        for(int i = 0; i < roster.length; i++) {
            if(roster[i] != null) {
               // System.out.println(roster[i].toString());

            }
        }
    }

    /**
     * This method will return a student in the roster at a specific index
     * @param index the specific index to find student
     * @return student
     */
    public Student getRoster(int index){
        if(roster[index] != null)
            return roster[index];
        else return null;
    }

    /**
     * Returns the size of the roster
     * @return the size of the roster
     */
    public int getSize(){
        return roster.length;
    }

    /**
     * Will change the roster and order by the students' names in alphebetical order
     * @return Student[]
     */
    public Student[] NameRoster() {
        Student[] orderName = new Student[this.size];
        for(int i = 0; i < size; i++) {
            orderName[i] = roster[i];
        }
        for(int i = 0; i < size - 1; i++) {//puts names in orderName is alphabetical order
            int min_index = i;
            for(int j = i + 1; j < size; j++) {
                if(orderName[j].getProfile().getName().charAt(0) < orderName[min_index].getProfile().getName().charAt(0)) {
                    min_index = j;
                }
                else if(orderName[j].getProfile().getName().charAt(0) == orderName[min_index].getProfile().getName().charAt(0)) {
                    int length = orderName[j].getProfile().getName().length();
                    if(length > orderName[min_index].getProfile().getName().length()) {
                        length = orderName[min_index].getProfile().getName().length();
                    }
                    for(int k = 0; k < length; k++) {
                        if(orderName[j].getProfile().getName().charAt(k) < orderName[min_index].getProfile().getName().charAt(k)) {
                            min_index = j;
                            break;
                        }
                        else if(orderName[j].getProfile().getName().charAt(k) > orderName[min_index].getProfile().getName().charAt(k)) {

                            break;
                        }
                    }
                }
            }
            Student save = orderName[i];
            orderName[i] = orderName[min_index];
            orderName[min_index] = save;
        }

        return orderName;
    }



    /**
     * Will change the roster and order by the students' dates
     * @return Student[]
     */
    public Student[] printPayments() {//not sure if right yet

        Student[] orderDate = new Student[this.size];
        for(int i = 0; i < size; i++) {
            if(roster[i].getPaymentDate() != null)
                orderDate[i] = roster[i];
            else orderDate[i] = null;
        }

        for(int i = 0; i < size - 1; i++) {//puts students in orderDate is date order
            if(orderDate[i] == null) {
                continue;
            }
            int min_index = i;
            for(int j = i + 1; j < size; j++) {
                if(orderDate[j] == null) {
                    continue;
                }
                if(orderDate[j].getPaymentDate().compareTo(orderDate[min_index].getPaymentDate()) == -1) {
                    min_index = j;
                }
            }
            Student save = orderDate[i];
            orderDate[i] = orderDate[min_index];
            orderDate[min_index] = save;
        }
        return orderDate;

    }

    /**
     * Gets the student that matches with the one provided.
     *
     * @param student the student that you want to get
     * @return the matching student; otherwise null
     */
    public Student getStudent(Student student) { //RETURNS Student in the roster that can be modified
        int index = find(student);
        if(index == -1) {
            return null;
        }
        return roster[index];
    }

    /**
     * Iterates through roster and calls tuitionDue to update the students Tuition.
     */
    public void updateTuition(){ //Updates the tuition of the students
        for(int i = 0; i < roster.length; i++) {
            if(roster[i] != null) {
                roster[i].tuitionDue();
            }
        }
    }

    /**
     * Checks if the Roster contains any Students
     *
     * @return true is the number of student greater than 0; otherwise return false
     */
    public boolean isEmpty(){
        if (size == 0) {
            return true;
        }
        else{
            return false;
        }
    }



}
