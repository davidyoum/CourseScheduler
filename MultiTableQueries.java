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

public class MultiTableQueries {
    private static Connection connection;
    private static PreparedStatement getAllClass;
    private static PreparedStatement getClassSeats;
    private static PreparedStatement getAllSchedules;
    private static PreparedStatement getStudent;
    private static PreparedStatement getAllWaitlistedSchedules;
    private static PreparedStatement getWaitlistedStudent;
    private static ResultSet resultSet1;
    private static ResultSet resultSet2;
    
    public static ArrayList<ClassDescription> getAllClassDescriptions(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<ClassDescription> classes = new ArrayList<ClassDescription>();
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;
        
        try
        {
            getAllClass = connection.prepareStatement("SELECT * FROM APP.CLASS WHERE SEMESTER = " + "'" + semester +  "'");
            resultSet1 = getAllClass.executeQuery();
            
            while(resultSet1.next())
            {
                getClassSeats = connection.prepareStatement("SELECT * FROM APP.COURSE WHERE COURSECODE = " + "'" + resultSet1.getString(2) +  "'");
                resultSet2 = getClassSeats.executeQuery();
                while(resultSet2.next())
                {
                    classes.add(new ClassDescription(resultSet2.getString(2), resultSet2.getString(1), resultSet1.getInt(3)));
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return classes;
    }
    
    
    public static ArrayList<StudentEntry> getScheduledStudentsByClass(String semester, String courseCode) 
    {
        connection = DBConnection.getConnection();
        ArrayList scheduledStudentsByClass = new ArrayList<ScheduleEntry>();
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;
        
        try
        {
            getAllSchedules = connection.prepareStatement("SELECT * FROM APP.SCHEDULE WHERE SEMESTER = " + "'" + semester +  "'" + " AND COURSECODE = " + "'" + courseCode +  "'" + " AND STATUS = 'scheduled'");
            resultSet1 = getAllSchedules.executeQuery();
            
            
            while(resultSet1.next())
            {   
                getStudent = connection.prepareStatement("SELECT * FROM APP.STUDENT WHERE STUDENTID = " + "'" + (String) resultSet1.getString(3) + "'");
                resultSet2 = getStudent.executeQuery();
                while(resultSet2.next()) 
                {
                    scheduledStudentsByClass.add(new StudentEntry(resultSet2.getString(1), resultSet2.getString(2), (String) resultSet2.getString(3)));
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        
        return scheduledStudentsByClass;
    }
    
    public static ArrayList<StudentEntry> getWaitlistedStudentsByClass(String semester, String courseCode) 
    {
        connection = DBConnection.getConnection();
        ArrayList waitlistedStudentsByClass = new ArrayList<ScheduleEntry>();
        
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;
        
        try
        {
            getAllSchedules = connection.prepareStatement("SELECT * FROM APP.SCHEDULE WHERE SEMESTER = " + "'" + semester +  "'" + " AND COURSECODE = " + "'" + courseCode +  "'" + " AND STATUS = 'waitlisted'");
            resultSet1 = getAllSchedules.executeQuery();
            
            
            while(resultSet1.next())
            {   
                getStudent = connection.prepareStatement("SELECT * FROM APP.STUDENT WHERE STUDENTID = " + "'" + (String) resultSet1.getString(3) + "'");
                resultSet2 = getStudent.executeQuery();
                while(resultSet2.next()) 
                {
                    waitlistedStudentsByClass.add(new StudentEntry(resultSet2.getString(1), resultSet2.getString(2), (String) resultSet2.getString(3)));
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return waitlistedStudentsByClass;
    }
}
