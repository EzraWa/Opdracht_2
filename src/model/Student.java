/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Ezra
 */
public class Student implements Comparable<Student>{
    
    private int studentNummer;
    private double cijfer;
    private String klas;

    public Student() {
    }

    

    public Student(int studentNummer, String klas, double cijfer) {
        this.studentNummer = studentNummer;
        this.cijfer = cijfer;
        this.klas = klas;
    }
    

    public int getStudentNummer() {
        return studentNummer;
    }

    public void setStudentNummer(int studentNummer) {
        this.studentNummer = studentNummer;
    }

    public double getCijfer() {
        return cijfer;
    }

    public void setCijfer(double cijfer) {
        this.cijfer = cijfer;
    }

    public String getKlas() {
        return klas;
    }

    public void setKlas(String klas) {
        this.klas = klas;
    }

    @Override
    public String toString() {
        return "Student{" + "studentNummer=" + studentNummer + ", cijfer=" + cijfer + ", klas=" + klas + '}';
    }

    @Override
    public int compareTo(Student t) {
        
        return new Double(t.cijfer).compareTo(this.cijfer);

    }
    
    
}

