package datastuctures_opdracht2;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;
import model.*;

/**
 *
 * @author Ezra
 * @since 30-09-2014
 * @version 0.3
 */
public class Datastuctures_Opdracht2 {

    private static final int aantalStudenten = 800;

    private static String[] richting = {"IS", "IT", "IN", "IG"};

    public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<>();
        ArrayList<String> klassen = new ArrayList<>();
        Random r = new Random();

        int aantalKlassen = 4;
        int studentenPerRichting = aantalStudenten / 4;
        aantalKlassen = aantalPerKlas(studentenPerRichting, aantalKlassen);
        System.out.println(aantalKlassen);
        
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
//        students = bucket(students);

        students.stream().forEach((student) -> {
            System.out.println(" | Student number:  " + student.getStudentNummer() + " Student group: " + student.getKlas() + " Student grade: " + student.getCijfer() + " | ");
        });

    }

    public static int aantalPerKlas(int studentenPerRichting, int aantalKlassen) {
        if (studentenPerRichting < 33) {
            return aantalKlassen;
        } else {
            studentenPerRichting = studentenPerRichting - 32;
            aantalKlassen = aantalKlassen + 4;
            return aantalPerKlas(studentenPerRichting, aantalKlassen);
        }
    }

    public static ArrayList insertion(ArrayList students) {
        double t1 = System.nanoTime();
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
        double t2 = System.nanoTime();
        System.out.println("Tijd: " + (t2-t1) / 1000000000);
        return student;
    }

    public static ArrayList bucket(ArrayList students) {
        double t1 = System.nanoTime();
        Student[] studentArray = new Student[students.size()];
        studentArray = (Student[]) students.toArray(studentArray);

        ArrayList<Klas> klassen = new ArrayList<Klas>();

        for (Student student : studentArray) {
            
            if (klassen.isEmpty()) {
                Klas newKlas = new Klas();
                newKlas.setNaam(student.getKlas());
                newKlas.addStudent((Student) student);
                klassen.add(newKlas);
            } else {
                boolean zitErin = false;
                boolean appel = false;
                int index = -1;

                for (int j = 0; j < klassen.size(); j++) {
                    if (klassen.get(j).getNaam().equals(student.getKlas())) {

                        boolean kiwi = false;
                        int indx = -1;
                        for (int k = 0; k < klassen.get(j).getSize(); k++) {
                            Student[] studentInKlas = new Student[students.size()];
                            studentInKlas = (Student[]) klassen.get(j).getStudents().toArray(studentArray);                           

                            if ((student.getStudentNummer() < studentInKlas[k].getStudentNummer()) && (kiwi == false)) {
                                indx = k;
                                kiwi = true;
                            }
                        }

                        if(indx == -1){
                            klassen.get(j).addStudent((Student) student);
                        } else{
                            klassen.get(j).addStudent(indx, (Student) student);
                        }
                        zitErin = true;
                    }
                    if ((appel == false) && (student.getKlas().compareTo(klassen.get(j).getNaam()) < 0)) {
                        index = j;
                        appel = true;
                    }
                }
                if (!zitErin) {
                    Klas newKlas = new Klas();
                    newKlas.setNaam(student.getKlas());
                    newKlas.addStudent((Student) student);

                    if (index == -1) {
                        klassen.add(newKlas);
                    } else {
                        klassen.add(index, newKlas);
                    }
                }
            }
        }
        ArrayList<Student> outputArray = new ArrayList<Student>();
        for (int u = 0; u < klassen.size(); u++) {
            for (int y = 0; y < klassen.get(u).getStudents().size(); y++) {
                outputArray.add((Student) klassen.get(u).getStudents().get(y));
            }
        }
        double t2 = System.nanoTime();
        System.out.println("Tijd: " + (t2-t1) / 1000000000);
        return outputArray; 
    }
}
