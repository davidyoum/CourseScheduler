/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author david
 */
public class StudentEntry {
    private String studentID;
    private String firstName;
    private String lastName;
    
    StudentEntry(String studentID, String firstName, String lastName) 
    {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getStudentID(){
        return this.studentID;
    }
    
    public String getFirstName(){
        return this.firstName;
    }
    
    public String getLastName(){
        return this.lastName;
    }
}
