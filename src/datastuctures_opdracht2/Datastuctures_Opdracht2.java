/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastuctures_opdracht2;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;
import model.Student;

/**
 *
 * @author Ezra
 * @since 30-09-2014
 * @version 0.3
 */
public class Datastuctures_Opdracht2 {

    private static int aantalKlassen = 4;
    private static int aantalStudenten = 1600;

    private static String[] richting = {"IS", "IT", "IB", "GD"};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<>();
        ArrayList<String> klassen = new ArrayList<>();
        Random r = new Random();

        int studentenPerRichting = aantalStudenten / 4;
        int studentenPerKlas = aantalPerKlas(studentenPerRichting);

//        String[] klassen = new String[aantalKlassen];
        for (int i = 0; i < aantalKlassen / 4; i++) {
            klassen.add(richting[0] + "_20" + (i + 1));
            klassen.add(richting[1] + "_20" + (i + 1));
            klassen.add(richting[2] + "_20" + (i + 1));
            klassen.add(richting[3] + "_20" + (i + 1));
        }
        System.out.println(klassen.toString());

        int j = 0;

        for (int i = 0; i < aantalStudenten; i++) {

            double cijfer = 1 + ((10 - 1) * r.nextDouble());

            cijfer = Double.parseDouble(new DecimalFormat("##.#", new DecimalFormatSymbols(Locale.US)).format(cijfer));

            if (j < klassen.size()) {

                Student s = new Student(500600001 + i, klassen.get(j), cijfer);
                students.add(s);
                j++;

            } else {
                j = 0;
                Student s = new Student(500600001 + i, klassen.get(j), cijfer);
                students.add(s);
            }

        }

        Collections.shuffle(students);
        students = insertion(students);

        students.stream().forEach((student) -> {
            System.out.println(" | Student number:  " + student.getStudentNummer() + " Student group: " + student.getKlas() + " Student grade: " + student.getCijfer() + " | ");
        });
    }

    public static int aantalPerKlas(int studentenPerRichting) {
        if (studentenPerRichting < 33) {
            return studentenPerRichting;
        } else {
            studentenPerRichting = studentenPerRichting / 2;
            aantalKlassen = aantalKlassen + 4;
            return aantalPerKlas(studentenPerRichting);
        }
    }

    public static ArrayList insertion(ArrayList students) {

        Student[] studentArray = new Student[students.size()];
        studentArray = (Student[]) students.toArray(studentArray);
        
        int n = students.size();
        for (int unsorted = 1; unsorted < n; ++unsorted) {
            Comparable nextItem = studentArray[unsorted];
            int loc = unsorted;
            while ((loc > 0) && (studentArray[loc - 1].compareTo((Student) nextItem) > 0)) {
                studentArray[loc] = studentArray[loc - 1];
                loc--;
            }
            studentArray[loc] = (Student) nextItem;
        } 
        
        ArrayList<Student> student = new ArrayList<Student>(Arrays.asList(studentArray));

        return student;
    }
}
