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

public class ClassQueries {
    private static Connection connection;
    private static PreparedStatement addClass;
    private static PreparedStatement getAllCourseCodes;
    private static PreparedStatement getSeats;
    private static PreparedStatement dropClass;
    private static ResultSet resultSet;
    private static ResultSet resultSet2;
    
    public static void addClass(ClassEntry class_)
    {
        connection = DBConnection.getConnection();
        try
        {
            addClass = connection.prepareStatement("INSERT INTO APP.CLASS(semester, coursecode, seats) VALUES (?, ?, ?)");
            addClass.setString(1, class_.getSemester());
            addClass.setString(2, class_.getCourseCode());
            addClass.setInt(3, class_.getSeats());
            addClass.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<String> getAllCourseCodes(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList courseID = new ArrayList<String>();
        ResultSet resultSet = null;
        
        try
        {
            getAllCourseCodes = connection.prepareStatement("SELECT * FROM APP.CLASS WHERE SEMESTER = " + "'" + semester +  "'");
            resultSet = getAllCourseCodes.executeQuery();
            
            while(resultSet.next())
            {
                courseID.add(resultSet.getString(2));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courseID;
    }
    
    public static int getClassSeats(String semester, String courseCode) 
    {
        connection = DBConnection.getConnection();
        
        try
        {
            getAllCourseCodes = connection.prepareStatement("SELECT * FROM APP.CLASS WHERE SEMESTER = " + "'" + semester +  "'" + " AND COURSECODE = " + "'" + courseCode +  "'");
            resultSet = getAllCourseCodes.executeQuery();
            
            resultSet.next();
            int seats = resultSet.getInt(3);
            return seats;
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return 0;
    }
    
    public static void dropClass(String semester, String courseCode) 
    {
        connection = DBConnection.getConnection();
        try
        {
            dropClass = connection.prepareStatement("DELETE FROM APP.CLASS WHERE SEMESTER = " + "'" + semester +  "'" + " AND COURSECODE = " + "'" + courseCode +  "'");
            dropClass.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
