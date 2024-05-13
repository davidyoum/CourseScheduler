/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author david
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentQueries {
    private static Connection connection;
    private static PreparedStatement addStudent;
    private static PreparedStatement getAllStudents;
    private static PreparedStatement dropStudent;
    private static PreparedStatement dropSchedule;
    private static ResultSet resultSet;
    
    public static void addStudent(StudentEntry student)
    {
        connection = DBConnection.getConnection();
        try
        {
            addStudent = connection.prepareStatement("INSERT INTO APP.STUDENT(studentID, firstName, lastName) VALUES (?, ?, ?)");
            addStudent.setString(1, student.getStudentID());
            addStudent.setString(2, student.getFirstName());
            addStudent.setString(3, student.getLastName());
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<StudentEntry> getAllStudents()
    {
        connection = DBConnection.getConnection();
        ArrayList students = new ArrayList<StudentEntry>();
        ResultSet resultSet = null;
        
        try
        {
            getAllStudents = connection.prepareStatement("SELECT * FROM APP.Student");
            resultSet = getAllStudents.executeQuery();
            
            while(resultSet.next())
            {
                students.add(new StudentEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return students;
    }
    
    public static StudentEntry getStudent(String studentID) 
    {
        connection = DBConnection.getConnection();
        ResultSet resultSet = null;
        StudentEntry student = null;
        
        try
        {
            getAllStudents = connection.prepareStatement("SELECT * FROM APP.Student WHERE STUDENTID = " + "'" + studentID + "'");
            resultSet = getAllStudents.executeQuery();
            
            resultSet.next();       
            student = new StudentEntry(studentID, resultSet.getString(2), resultSet.getString(3));
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return student;
    }
    
    public static void dropStudent(String studentID) 
    {
        connection = DBConnection.getConnection();
        resultSet = null;
        
        try
        {
            dropSchedule = connection.prepareStatement("DELETE FROM APP.SCHEDULE WHERE STUDENTID = " + "'" + studentID + "'");
            dropStudent = connection.prepareStatement("DELETE FROM APP.Student WHERE STUDENTID = " + "'" + studentID + "'");
            dropStudent.executeUpdate();
            dropSchedule.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
}
