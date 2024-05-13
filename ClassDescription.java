/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author david
 */
public class ClassDescription {
    private String description;
    private String courseCode;
    private int seats;
    
    
    ClassDescription(String description,String courseCode, int seats) 
    {
        this.description = description;
        this.courseCode = courseCode;
        this.seats = seats;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public String getCourseCode(){
        return this.courseCode;
    }
    
    public int getSeats(){
        return this.seats;
    }
}
