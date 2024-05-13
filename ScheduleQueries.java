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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getWaitlistedStudentsByClass;
    private static PreparedStatement getStudent;
    private static PreparedStatement dropScheduleByCourse;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static ResultSet resultSet;
    
    public static void addScheduleEntry(ScheduleEntry schedule)
    {
        connection = DBConnection.getConnection();
        try
        {
            addScheduleEntry = connection.prepareStatement("INSERT INTO APP.Schedule(semester, coursecode, studentid, status, timestamp) VALUES (?, ?, ?, ?, ?)");
            addScheduleEntry.setString(1, schedule.getSemester());
            addScheduleEntry.setString(2, schedule.getCourseCode());
            addScheduleEntry.setString(3, schedule.getStudentID());
            addScheduleEntry.setString(4, schedule.getStatus());
            addScheduleEntry.setTimestamp(5, schedule.getTimestamp());
            addScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID)
    {
        connection = DBConnection.getConnection();
        ArrayList schedule = new ArrayList<ScheduleEntry>();
        ResultSet resultSet = null;
        
        try
        {
            getScheduleByStudent = connection.prepareStatement("SELECT * FROM APP.SCHEDULE WHERE semester = " + "'" + semester +  "'" + "AND studentID = " + "'" + studentID +  "'");
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {
                schedule.add(new ScheduleEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return schedule;
    }
    
    public static int getScheduledStudentCount(String currentSemester, String courseCode)
    {
        connection = DBConnection.getConnection();
        ResultSet resultSet = null;
        
        try
        {
            getScheduledStudentCount = connection.prepareStatement("SELECT COUNT(studentID) FROM APP.SCHEDULE WHERE SEMESTER =" + "'" + currentSemester +  "'" + "AND COURSECODE = " + "'" + courseCode +  "'");
            resultSet = getScheduledStudentCount.executeQuery();
            
            resultSet.next();
            int count = resultSet.getInt(1);
            return count;
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return 0;
    }
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByClass(String semester, String courseCode) 
    {
        connection = DBConnection.getConnection();
        ArrayList waitlistedStudentsByClass = new ArrayList<ScheduleEntry>();
        ResultSet resultSet1 = null;
        
        try
        {
            getWaitlistedStudentsByClass = connection.prepareStatement("SELECT * FROM APP.SCHEDULE WHERE SEMESTER = " + "'" + semester +  "'" + " AND COURSECODE = " + "'" + courseCode +  "'" + " AND STATUS = 'waitlisted'");
            resultSet1 = getWaitlistedStudentsByClass.executeQuery();
            
            
            while(resultSet1.next())
            {   
                waitlistedStudentsByClass.add(new ScheduleEntry((String) resultSet1.getString(1),(String) resultSet1.getString(2), (String) resultSet1.getString(3), (String) resultSet1.getString(4),resultSet1.getTimestamp(5)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return waitlistedStudentsByClass;
    }
    
    public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode)
    {
        connection = DBConnection.getConnection();
        
        try
        {
            dropStudentScheduleByCourse = connection.prepareStatement("DELETE FROM APP.SCHEDULE WHERE STUDENTID = " + "'" + studentID +  "'" + " AND COURSECODE = " + "'" + courseCode +  "'" + " AND SEMESTER = " + "'" + semester +  "'");
            dropStudentScheduleByCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static void dropScheduleByCourse(String semester, String courseCode) 
    {
        connection = DBConnection.getConnection();
        
        try
        {
            dropScheduleByCourse = connection.prepareStatement("DELETE FROM APP.SCHEDULE WHERE COURSECODE = " + "'" + courseCode +  "'" + " AND SEMESTER = " + "'" + semester +  "'");            
            dropScheduleByCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static void updateScheduleEntry(ScheduleEntry entry) 
    {
        connection = DBConnection.getConnection();
        
        try
        {
            dropStudentScheduleByCourse = connection.prepareStatement("DELETE FROM APP.SCHEDULE WHERE STUDENTID = " + "'" + entry.getStudentID() + "'" + " AND COURSECODE = " + "'" + entry.getCourseCode() +  "'" + " AND SEMESTER = " + "'" + entry.getSemester() +  "'");
            dropStudentScheduleByCourse.executeUpdate();
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
            addScheduleEntry(new ScheduleEntry(entry.getSemester(), entry.getCourseCode(), entry.getStudentID(), "scheduled", currentTimestamp));
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
