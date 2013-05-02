package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.DatabaseMetaData;
import Database.Record;
import Database.DBHelper;
 /**
 * @author Rich
 */
public class DBManager {
private  String sDriver = "org.sqlite.JDBC"; 
private  String sUrl = "jdbc:sqlite:DISCO_DB.db";
private  int iTimeout = 30;
private  Connection conn = null;
private  PreparedStatement prepStatement = null;
private Statement statement;
 
public DBManager() throws Exception
{
    
    setConnection();
    setStatement();
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
 

  public  void setStatement() throws Exception {
    if (conn == null) {
        setConnection();
    }
    statement =  conn.createStatement();
    statement.setQueryTimeout(iTimeout);  // set timeout to 30 sec.
}
 
public  Statement getStatement() {
    return statement;
}

public  void executeStmt(String instruction) throws SQLException {
    statement.executeUpdate(instruction); 
}

public ResultSet executeQry(String instruction) throws SQLException {
    return statement.executeQuery(instruction);
} 
 
public void closeConnection() {
    if (statement!=null) { try { statement.close(); } catch (Exception e) {} }
    if (conn!=null) { try { conn.close(); } catch (Exception e) {} }
}

//this method is used to pull records from the database when a user requests a printout of data
public Record getRecord(String data, int field) throws SQLException{
    //@TODO SQL statements to pull the data from the database and populate record
    //@TODO get it to return all records with the matching field for 'data'
    Record record = new Record();
    ResultSet results = statement.executeQuery("select * from records where " + data + " = n;");
    results.getString(data); //this accesses the data in that field
    return record;
}

//this method pulls an incomplete record from the database to be completed in a session
public Record getLesson(String data, int field) throws SQLException{
    //@TODO SQL statements to pull the data from the database and populate record
    //@TODO load the image from image path into record
    Record record = new Record();
    ResultSet results = statement.executeQuery("select * from lessonTable where " + data + " = n;");
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
    return record;
}

//this method inserts an incomplete record into the database for future use
public void insertLesson(Record record) throws SQLException{
    prepStatement = conn.prepareStatement("insert into lessonTable values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
    int i, i2; String s1, s2, s3, s4, s5, s6, s7, s8, s9;
    s1 = record.getName();
    prepStatement.setString(2, s1);
    s2 = record.getLessonName();
    prepStatement.setString(3, s2);
    i = record.getCorrectChoice();
    prepStatement.setInt(4, i);
    i2 = record.getReward();
    prepStatement.setInt(5, i2);
    s3 = record.getAudioReward();
    prepStatement.setString(6, s3);
    s4 = record.getVideoReward();
    prepStatement.setString(7, s4);
    s5 = record.getImage1path();
    prepStatement.setString(8, s5);
    s6 = record.getImage2path();
    prepStatement.setString(9, s6);
    s7 = record.getImage3path();
    prepStatement.setString(10, s7);
    s8 = record.getImage4path();
    prepStatement.setString(11, s8);
    s9 = record.getComments();
    prepStatement.setString(12, s9);

    prepStatement.executeUpdate(); //updates the DB
}

//this method saves a completed record to the database
public void insertRecord(Record record) throws SQLException{
    prepStatement = conn.prepareStatement("insert into lessonTable values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
    int i, i2, i3, i4; String s1, s2, s3, s4, s5, s6, s7, s8, s9;
    s1 = record.getName();
    prepStatement.setString(2, s1);
    s2 = record.getLessonName();
    prepStatement.setString(3, s2);
    i = record.getCorrectChoice();
    prepStatement.setInt(4, i);
    i2 = record.getReward();
    prepStatement.setInt(5, i2);
    s3 = record.getAudioReward();
    prepStatement.setString(6, s3);
    s4 = record.getVideoReward();
    prepStatement.setString(7, s4);
    s5 = record.getImage1path();
    prepStatement.setString(8, s5);
    s6 = record.getImage2path();
    prepStatement.setString(9, s6);
    s7 = record.getImage3path();
    prepStatement.setString(10, s7);
    s8 = record.getImage4path();
    prepStatement.setString(11, s8);
    s9 = record.getComments();
    prepStatement.setString(12, s9);
    i3 = record.getResponseTime();
    prepStatement.setInt(13, i3);
    i4 = record.getActualChoice();
    prepStatement.setInt(14, i4);

    prepStatement.executeUpdate(); //updates the DB
}

private void checkRecordTableExistence() throws SQLException {
    String makeTable = DBHelper.RECORD_TABLE_CREATE;
    PreparedStatement ps = conn.prepareStatement(makeTable);
    ps.executeUpdate();
    }

private void checkLessonTableExistence() throws SQLException {
    String makeTable = DBHelper.LESSON_TABLE_CREATE;
    PreparedStatement ps = conn.prepareStatement(makeTable);
    ps.executeUpdate();
}

}



