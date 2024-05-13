/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Timestamp;
/**
 *
 * @author david
 */
public class ScheduleEntry {
    private String semester;
    private String courseCode;
    private String studentID;
    private String status;
    private Timestamp timestamp;
    
    ScheduleEntry(String semester,String courseCode, String studentID, String status, Timestamp timestamp) 
    {
        this.semester = semester;
        this.courseCode = courseCode;
        this.studentID = studentID;
        this.status = status;
        this.timestamp = timestamp;
    }
    
    public String getSemester(){
        return this.semester;
    }
    
    public String getCourseCode(){
        return this.courseCode;
    }
    
    public String getStudentID(){
        return this.studentID;
    }
    
    public String getStatus(){
        return this.status;
    }
    
    public Timestamp getTimestamp(){
        return this.timestamp;
    }
}
