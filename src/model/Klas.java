package model;

import java.util.ArrayList;

/**
 *
 * @author Ezra
 */
public class Klas implements Comparable<Klas>{
    
    private String naam;
    private ArrayList<Student> students;

    public Klas() {
        students = new ArrayList<Student>();
    }
    
    public String getNaam(){
        return this.naam;
    }

    public void setNaam(String naam){
        this.naam = naam;
    }
    
    public void addStudent(Student student){
        this.students.add(student);
    }
    
    public void addStudent(int index, Student student){
        this.students.add(index, student);
    }
    
    public ArrayList getStudents(){
        return this.students;
    }
    
    public int getSize(){
        return students.size();
    }

    @Override
    public String toString() {
        return "Klas{" + "students=" + students + '}';
    }

    @Override
    public int compareTo(Klas t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
