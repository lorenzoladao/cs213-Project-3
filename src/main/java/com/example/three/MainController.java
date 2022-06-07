package com.example.three;


import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

/**
 *This is the user interface, controller class that performs Input/Output (read/write.)
 *This class uses an instance of Roster class to process the tuitions, add/remove students,
 *assign financial aid and print the roster in varying ways.
 *
 * @author Asif Haque, Margaret Knoebel, Lorenzo Ladao
 */
    public class MainController {

        private static final int MAX_FINANCIAL_AID = 10000;
        private static final int PARTTIME_CREDIT_HOURS = 12;
        private static final int MIN_CREDIT_HOURS = 3;
        private static final int MAX_CREDIT_HOURS = 24;
        private static final double TWO_DECIMAL_PLACES = 100;

        private Roster roster;
        @FXML
        private RadioButton res;
        @FXML
        private RadioButton non;
        @FXML
        private RadioButton tri;
        @FXML
        private RadioButton inter;
        @FXML
        private RadioButton newy;
        @FXML
        private RadioButton connect;
        @FXML
        private CheckBox sa;
        @FXML
        private TextField name;
        @FXML
        private TextField credits;
        @FXML
        private RadioButton BA;
        @FXML
        private RadioButton EE;
        @FXML
        private RadioButton CS;
        @FXML
        private RadioButton ME;
        @FXML
        private RadioButton IT;
        @FXML
        private TextArea printLocation;
        @FXML
        private CheckBox tuitionAll;
        @FXML
        private DatePicker date;

        @FXML // ResourceBundle that was given to the FXMLLoader
        private ResourceBundle resources;

        @FXML // URL location of the FXML file that was given to the FXMLLoader
        private URL location;

        @FXML
            // This method is called by the FXMLLoader when initialization is complete
        void initialize() {
            tri.setDisable(true);
            inter.setDisable(true);
            newy.setDisable(true);
            connect.setDisable(true);
            sa.setDisable(true);
            date.getEditor().setDisable(true);
            date.getEditor().setOpacity(1);
            roster = new Roster();
        }

        /**
         * Lets the user click certain buttons that were unavailable before
         * @param event button Nonresident, Tristate, International
         */
        @FXML
        public void enable(ActionEvent event) {
            if (non.isSelected()) {
                tri.setDisable(false);
                inter.setDisable(false);
            }
            if (tri.isSelected()) {
                newy.setDisable(false);
                connect.setDisable(false);
                sa.setDisable(true);
                sa.setSelected(false);
            }
            if (inter.isSelected()) {
                sa.setDisable(false);

                newy.setSelected(false);
                connect.setSelected(false);
            }

        }

        /**
         * Deselects buttons if Resident is selected
         * @param event button Resident
         */
        @FXML
        public void disable(ActionEvent event) {
            tri.setSelected(false);
            inter.setSelected(false);
            newy.setSelected(false);
            connect.setSelected(false);
            sa.setSelected(false);
            tri.setDisable(true);
            inter.setDisable(true);
            newy.setDisable(true);
            connect.setDisable(true);
            sa.setDisable(true);
        }

        /**
         * Converts the elements from the input into a Student object and adds it to the roster.
         *
         * @param event the button clicked by user
         */
        @FXML
        public void addStudent(ActionEvent event) {
            String names = "";
            try {
                names = name.getText();
                if (names.compareTo("") == 0) {
                    printLocation.appendText("Student's name is missing\n");
                    return;
                }
            } catch (Exception ex) {
                printLocation.appendText("Student's name is missing\n");
            }
            Major major;
            if (BA.isSelected()) {
                major = Major.BA;
            } else if (EE.isSelected()) {
                major = Major.EE;
            } else if (IT.isSelected()) {
                major = Major.IT;
            } else if (ME.isSelected()) {
                major = Major.ME;
            } else if (CS.isSelected()) {
                major = Major.CS;
            } else {
                printLocation.appendText("Student's major is missing\n");
                return;
            }
            int credit = 0;
            try {
                credit = Integer.parseInt(credits.getText());
            } catch (Exception ex) {
                printLocation.appendText("Student's credits are missing\n");
                return;
            }
            if (credit < MIN_CREDIT_HOURS) {
                printLocation.appendText("Student's credits are less than 3.\n");
                return;
            }
            else if ( credit > MAX_CREDIT_HOURS){
                printLocation.appendText("Student's credits are greater than 24.\n");
                return;
            }

            Profile profile = new Profile(names, major);
            Student student = null;

            if (res.isSelected()) {//resident
                student = new Resident(profile, credit);
            } else if (non.isSelected()) {//nonresident
                if (inter.isSelected()) {//international
                    if (credit < PARTTIME_CREDIT_HOURS) {
                        printLocation.appendText("International Students must enroll with at least 12 credits\n");
                        return;
                    }
                    if (sa.isSelected()) {//study abroad
                        student = new International(profile, credit, true);
                    } else {
                        student = new International(profile, credit, false);
                    }
                } else if (tri.isSelected()) {//tristate
                    if (newy.isSelected()) {//newyork
                        student = new Tristate(profile, credit, "ny");
                    } else if (connect.isSelected()) {//connecticut
                        student = new Tristate(profile, credit, "ct");
                    }
                } else {//nonresident
                    student = new NonResident(profile, credit);
                }
            }
            if (student == null) {
                printLocation.appendText("Student's status is missing\n");
                return;
            }
            boolean inRoster = roster.add(student);
            if (inRoster) {
                printLocation.appendText(student.toString());
                printLocation.appendText("\n");
            } else printLocation.appendText("Student in roster\n");
        }
        /**
         * Converts the elements from the input into a Student object and removes it from the roster.
         *
         * @param event the button clicked by user
         */

        @FXML
        public void removeStudent(ActionEvent event) {
            String names = "";
            try {
                names = name.getText();
                if (names.compareTo("") == 0) {
                    printLocation.appendText("Student's name missing.\n");
                    return;
                }
            } catch (Exception ex) {
                printLocation.appendText("Student's name missing.\n");
            }

            Major major;
            if (BA.isSelected()) {
                major = Major.BA;
            } else if (EE.isSelected()) {
                major = Major.EE;
            } else if (IT.isSelected()) {
                major = Major.IT;
            } else if (ME.isSelected()) {
                major = Major.ME;
            } else if (CS.isSelected()) {
                major = Major.CS;
            } else {
                printLocation.setText("Major missing.");
                return;
            }

            Profile profile = new Profile(names, major);
            Student student = new Student(profile, 0);
            boolean removeStatus = roster.remove(student);
            if (removeStatus) {
                printLocation.appendText("Student removed\n");
            } else printLocation.appendText("Student not in roster\n");
        }


        @FXML
        private Button rosterCurrent;
        @FXML
        private Button rosterName;
        @FXML
        private Button rosterDate;
        /**
         * Prints the whole Roster of Students that is provided.
         * If the user selects the Print Roster tab, and user selected Print Roster, roster prints regardless of order
         *
         * @param event specific print option selected by the user
         */
        @FXML
        public void printRoster(ActionEvent event) {
            if(roster.isEmpty()){
                printLocation.appendText("Student roster is empty.\n");
                return;
            }
            printLocation.appendText("* list of students in the roster **\n");
            for (int i = 0; i < roster.getSize(); i++) {
                Student student = roster.getRoster(i);
                if (student != null) {
                    printLocation.appendText(student.toString());
                    printLocation.appendText("\n");
                }
            }
            printLocation.appendText("* end of roster **\n");
        }
        /**
         * Prints the whole Roster of Students that is provided.
         * If the user selects the Print Roster tab, and user selected Print By Roster,
         * roster prints by alphabetical order
         *
         * @param event specific print option selected by the user
         */
        @FXML
        public void printRosterName(ActionEvent event) {
            if(roster.isEmpty()){
                printLocation.appendText("Student roster is empty.\n");
                return;
            }
            Student[] ordername = roster.NameRoster(); //new Student[]
            printLocation.appendText("* list of students ordered by name **\n");
            for (int i = 0; i < ordername.length; i++) {
                Student student = ordername[i];
                if (student != null) {
                    printLocation.appendText(student.toString());
                    printLocation.appendText("\n");
                }
            }
            printLocation.appendText("* end of roster **\n");
        }
        /**
         * Prints the whole Roster of Students that is provided.
         * If the user selects the Print Roster tab, and user selected Print Roster by Date,
         * roster prints by order of Date
         *
         * @param event specific print option selected by the user
         */
        @FXML
        public void printRosterDate(ActionEvent event) {
            if(roster.isEmpty()){
                printLocation.appendText("Student roster is empty.\n");
                return;
            }
            Student[] orderdate = roster.printPayments(); //new Student[]
            for (int i = 0; i < orderdate.length; i++) {
                Student student = orderdate[i];
                if (student != null) {
                    break;
                }
                if (i == orderdate.length - 1){
                    printLocation.appendText("No students have made a payment yet.\n");
                    return;
                }
            }
            printLocation.appendText("* list of students made payments ordered by payment date **\n");
            for (int i = 0; i < orderdate.length; i++) {
                Student student = orderdate[i];
                if (student != null) {
                    printLocation.appendText(student.toString());
                    printLocation.appendText("\n");
                }
            }
            printLocation.appendText("* end of roster **\n");
        }



        /**
         * Once information by user is entered, the user can change the study aboard status
         * as well as updates tuition balance for the student entered
         *
         * @param event specific button selected by the user
         */

        @FXML
        public void onChangeAbroadButton(ActionEvent event) {
            String name;
            Major major;

            name = this.name.getText();
            if (name.isEmpty()) {
                printLocation.appendText("Student's name missing.\n");
                return;
            }

            if (CS.isSelected()) {
                major = Major.CS;
            } else if (IT.isSelected()) {
                major = Major.IT;
            } else if (BA.isSelected()) {
                major = Major.BA;
            } else if (EE.isSelected()) {
                major = Major.EE;
            } else if (ME.isSelected()) {
                major = Major.ME;
            } else {
                printLocation.appendText("Major missing.\n");
                return;
            }

            Profile profile = new Profile(name, major);
            Student student = new Student(profile, 0);

            try {
                International international = (International) roster.getStudent(student);
                if (sa.isSelected() && !international.isStudyAbroad()) {
                    international.resetTuition();
                    international.setStudyAbroadTrue();
                } else if (!sa.isSelected() && international.isStudyAbroad()) {
                    international.resetTuition();
                    international.setStudyAbroadFalse();
                } else {
                    printLocation.appendText("Student already has this Study Abroad status.\n");
                    return;
                }
                international.tuitionDue();
                printLocation.appendText("Tuition updated.\n");
            } catch (ClassCastException e) {
                printLocation.appendText("Not an International student.\n");
                return;
            } catch (NullPointerException e) {
                printLocation.appendText("Student not in the roster.\n");
                return;
            }
        }

        @FXML
        private TextField studentName;
        @FXML
        private RadioButton CS2;
        @FXML
        private RadioButton EE2;
        @FXML
        private RadioButton ME2;
        @FXML
        private RadioButton IT2;
        @FXML
        private RadioButton BA2;
        @FXML
        private TextField payAmount;
        @FXML
        private TextField aidAmount;

        /**
         * Once information by user is entered, the user can pay tuition balance for entered student
         * as well as checks if amount entered is valid and notes the date of the payment
         *
         * @param event specific button selected by the user
         */
        @FXML
        public void onPayButtonClick(ActionEvent event) {

            String name;
            Major major;
            double tuition;
            Date thisDate;

            name = studentName.getText();
            if (name.isEmpty()) {
                printLocation.appendText("Student's name missing.\n");
                return;
            }

            if (CS2.isSelected()) {
                major = Major.CS;
            } else if (IT2.isSelected()) {
                major = Major.IT;
            } else if (BA2.isSelected()) {
                major = Major.BA;
            } else if (EE2.isSelected()) {
                major = Major.EE;
            } else if (ME2.isSelected()) {
                major = Major.ME;
            } else {
                printLocation.appendText("Major missing.\n");
                return;
            }

            Profile profile = new Profile(name, major);
            Student student = new Student(profile, 0);

            String str_tuition = payAmount.getText();
            if (!checkDouble(str_tuition)){
                return;
            }

            try {
                tuition = Double.parseDouble(str_tuition);
                if (tuition <= 0) {
                    printLocation.appendText("Invalid payment amount.\n");
                    return;
                }
            } catch (NumberFormatException error) {
                printLocation.appendText("Payment amount missing.\n");
                return;
            } catch (Exception error) {
                printLocation.appendText("Invalid payment amount.\n");
                return;
            }


            String pattern = "MM/dd/yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            try {
                LocalDate localDate = date.getValue();
                thisDate = new Date(dateFormatter.format(localDate));
                if (!thisDate.isValid()){
                    printLocation.appendText("Payment date is invalid.\n");
                    return;
                }

            } catch (NullPointerException e) {
                printLocation.appendText("Payment date missing.\n");
                return;
            }

            try {
                Student foundStudent = roster.getStudent(student);
                if (tuition > foundStudent.getTuition()) {
                    printLocation.appendText("Amount is greater than amount due.\n");
                    return;
                }
            } catch (NullPointerException e) {
                printLocation.appendText("Student not in the roster.\n");
                return;
            }

            roster.getStudent(student).addPayment(tuition, thisDate);
            printLocation.appendText("Payment applied.\n");

        }
        /**
         * Once information by user is entered, the user can add financial aid for entered student
         * as well as checks if amount entered is valid, updates tuition due and checks if student meets requirements
         * for financial aid
         *
         * @param event specific button selected by the user
         */
        @FXML
        public void onSetButtonClick(ActionEvent event) {
            String name;
            Major major;
            double finAid;
            Date thisDate;

            name = studentName.getText();
            if (name.isEmpty()) {
                printLocation.appendText("Student's name missing.\n");
                return;
            }

            if (CS2.isSelected()) {
                major = Major.CS;
            } else if (IT2.isSelected()) {
                major = Major.IT;
            } else if (BA2.isSelected()) {
                major = Major.BA;
            } else if (EE2.isSelected()) {
                major = Major.EE;
            } else if (ME2.isSelected()) {
                major = Major.ME;
            } else {
                printLocation.appendText("Major missing.\n");
                return;
            }

            Profile profile = new Profile(name, major);
            Student student = new Student(profile, 0);

            String str_finAid = aidAmount.getText();
            if (!checkDouble(str_finAid)){
                return;
            }

            try {
                finAid = Double.parseDouble(str_finAid);
                if (finAid > MAX_FINANCIAL_AID || finAid <= 0) {
                    printLocation.appendText("Invalid amount.\n");
                    return;
                }
            } catch (NumberFormatException error) {
                printLocation.appendText("Financial aid amount missing.\n");
                return;
            }

            try {
                Resident resident = (Resident) roster.getStudent(student);
                if (resident.isReceivedAid()) {
                    printLocation.appendText("Awarded once already.\n");
                    return;
                }
                if (resident.getCreditHours() < PARTTIME_CREDIT_HOURS) {
                    printLocation.appendText("Parttime student does not qualify for the award.\n");
                    return;
                }
                resident.setFinancialAid(finAid);
                resident.tuitionDue();
                printLocation.appendText("Tuition updated.\n");
            } catch (ClassCastException e) {
                printLocation.appendText("Not a resident student.\n");
                return;
            } catch (NullPointerException e) {
                printLocation.appendText("Student not in the roster.\n");
                return;
            }

        }
        /**
         * Once information by user is entered, the user update tuition due amounts for a specific entered student,
         * or the whole roster, as well as checks if the student is in the roster, if the roster is empty then,
         * updates tuition due amounts and returns a corresponding message
         *
         * @param event specific button selected by the user
         */
        @FXML
        public void onUpdateTuitionButtonClick(ActionEvent event) {
            String stuName;
            Major major;

            if(roster.isEmpty()){
                printLocation.appendText("Student roster is empty.\n");
                return;
            }

            if(tuitionAll.isSelected()){
                roster.updateTuition();
                printLocation.appendText("Tuition Updated for Roster.\n");
                return;
            }
            else {
                stuName = name.getText();
                if (stuName.isEmpty()) {
                    printLocation.appendText("Student's name missing.\n");
                    return;
                }

                if (CS.isSelected()) {
                    major = Major.CS;
                } else if (IT.isSelected()) {
                    major = Major.IT;
                } else if (BA.isSelected()) {
                    major = Major.BA;
                } else if (EE.isSelected()) {
                    major = Major.EE;
                } else if (ME.isSelected()) {
                    major = Major.ME;
                } else {
                    printLocation.appendText("Major missing.\n");
                    return;
                }

                Profile profile = new Profile(stuName, major);
                Student student = new Student(profile, 0);

                if (roster.isEmpty()) {
                    printLocation.appendText("Student roster is empty.\n");
                    return;
                }
                try {
                    // looks for student using student class
                    Student foundStudent = (Student) roster.getStudent(student);
                    foundStudent.tuitionDue();
                    printLocation.appendText("Tuition Updated for Student.\n");
                } catch (NullPointerException e) {
                    printLocation.appendText("Student not in the roster.\n");
                    return;
                }
            }

        }

        /**
         * Verifies if the String number is able to parse as a double.
         * Also checks if the number is a valid amount to be paid (ie Only has 2 decimal places)
         *
         * @param number the string to be verified
         * @return false if number has more than 2 decimals or is not a valid number; otherwise true;
         */
        private boolean checkDouble(String number) {
            try{
                double db = Double.parseDouble(number);
                if ((db * TWO_DECIMAL_PLACES) % 1 != 0){
                    printLocation.appendText("'" + number + "' is not a valid amount.\n");
                    return false;
                }
            }
            catch(NumberFormatException error){
                printLocation.appendText("Not a valid amount.\n");
                return false;
            }
            return true;
        }
    }
