package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
 /**
 * @author Rich Burke - rmburke2@buffalo.edu
 */
public class DBManager {
private  String sDriver = "org.sqlite.JDBC"; 
private  String sUrl = "jdbc:sqlite:DISCO_DB.db";
private  int iTimeout = 30;
private  Connection conn = null;

 
public DBManager() throws Exception
{
    setConnection();
    checkRecordTableExistence();
    checkLessonTableExistence();
}

public  void setConnection() throws Exception {
    try{
        Class.forName(sDriver);
        conn = DriverManager.getConnection(sUrl);
    } catch(Exception e) {
        System.err.println(e);
    }
}
 
public  Connection getConnection() {
    return conn;
}
 
 
public void closeConnection() {
    if (conn!=null) { try { conn.close(); } catch (Exception e) {} }
}

//this method is used to pull records from the database when a user requests a printout of data
public ArrayList<Record> getRecord(String data, String field) throws SQLException{
    Statement statement = conn.createStatement();
    ArrayList<Record> list = new ArrayList<Record>();
    try{
    ResultSet results = statement.executeQuery("select * from recordTable where "+field+"  = '" + data + "';");
    while(results.next()){
    Record record = new Record();
    record.setName(results.getString(DBHelper.NAME)); 
    record.setLessonName(results.getString(DBHelper.LESSON_NAME));
    record.setCorrectChoice(results.getInt(DBHelper.CORRECT_CHOICE));
    record.setReward(results.getInt(DBHelper.REWARD));
    record.setAudioReward(results.getString(DBHelper.AUDIO_REWARD));
    record.setVideoReward(results.getString(DBHelper.VIDEO_REWARD));
    record.setImage1path(results.getString(DBHelper.IMAGE_ONE_PATH));
    record.setImage2path(results.getString(DBHelper.IMAGE_TWO_PATH));
    record.setImage3path(results.getString(DBHelper.IMAGE_THREE_PATH));
    record.setImage4path(results.getString(DBHelper.IMAGE_FOUR_PATH));
    record.setComments(results.getString(DBHelper.COMMENTS));
    list.add(record);
    }
    } catch(SQLException e) { System.err.println(e);}
    return list;
}

//this method pulls an incomplete record from the database to be completed in a session
public Record getLesson(String data, String field) throws SQLException{
    //@TODO SQL statements to pull the data from the database and populate record
    //@TODO load the image from image path into record
    Record record = new Record();
    Statement statement = conn.createStatement();
    try {
    ResultSet results = statement.executeQuery("select * from lessonTable where " + field + " = '"+data+"';");
    record.setName(results.getString(DBHelper.NAME)); 
    record.setLessonName(results.getString(DBHelper.LESSON_NAME));
    record.setCorrectChoice(results.getInt(DBHelper.CORRECT_CHOICE));
    record.setReward(results.getInt(DBHelper.REWARD));
    record.setAudioReward(results.getString(DBHelper.AUDIO_REWARD));
    record.setVideoReward(results.getString(DBHelper.VIDEO_REWARD));
    record.setImage1path(results.getString(DBHelper.IMAGE_ONE_PATH));
    record.setImage2path(results.getString(DBHelper.IMAGE_TWO_PATH));
    record.setImage3path(results.getString(DBHelper.IMAGE_THREE_PATH));
    record.setImage4path(results.getString(DBHelper.IMAGE_FOUR_PATH));
    record.setComments(results.getString(DBHelper.COMMENTS));
    } catch(SQLException e) { System.err.println(e); }
    return record;
}

//this method inserts an incomplete record into the database for future use
public void insertLesson(Record record) throws SQLException{
    PreparedStatement ps;
    try{
    ps = conn.prepareStatement("insert into lessonTable values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
    ps.setString(2, record.getName());
    ps.setString(3, record.getLessonName());
    ps.setInt(4, record.getCorrectChoice());
    ps.setInt(5, record.getReward());
    ps.setString(6, record.getAudioReward());
    ps.setString(7, record.getVideoReward());
    ps.setString(8, record.getImage1path());
    ps.setString(9, record.getImage2path());
    ps.setString(10, record.getImage3path());
    ps.setString(11, record.getImage4path());
    ps.setString(12, record.getComments());
    ps.execute(); //updates the DB
    } catch(SQLException e) { System.err.println(e); }
}

//this method saves a completed record to the database
public void insertRecord(Record record) throws SQLException{
    PreparedStatement ps;
    try{
    ps = conn.prepareStatement("insert into recordTable values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
    ps.setString(2, record.getName());
    ps.setString(3, record.getLessonName());
    ps.setInt(4, record.getCorrectChoice());
    ps.setInt(5, record.getReward());
    ps.setString(6, record.getAudioReward());
    ps.setString(7, record.getVideoReward());
    ps.setString(8, record.getImage1path());
    ps.setString(9, record.getImage2path());
    ps.setString(10, record.getImage3path());
    ps.setString(11, record.getImage4path());
    ps.setString(12, record.getComments());
    ps.setInt(13, record.getResponseTime());
    ps.setInt(14, record.getActualChoice());
    ps.execute(); //updates the DB
    } catch(SQLException e) { System.err.println(e); }
}

public void checkRecordTableExistence() throws SQLException {
    try{
    Statement stat = conn.createStatement();
    String makeTable = DBHelper.RECORD_TABLE_CREATE;
    stat.executeUpdate(makeTable);
    
    } catch(SQLException e) { System.err.println(e);  }
    }

private void checkLessonTableExistence() throws SQLException {
try{
    Statement stat = conn.createStatement();
    String makeTable = DBHelper.LESSON_TABLE_CREATE;
    stat.executeUpdate(makeTable);
    
    } catch(SQLException e) { System.err.println(e);  }
    }

}



