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
 /**
 * @author Rich
 */
public class DBManager {
private  String sDriver = "org.sqlite.JDBC"; 
private  String sUrl = "jdbc:sqlite:DISCO_DB.db";
private  int iTimeout = 30;
private  Connection conn = null;
private  PreparedStatement insertStatement = null;
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
    ResultSet results = statement.executeQuery("select * from records where " + data + " = n;");
    results.getString(data); //this accesses the data in that field
    return record;
}

//this method inserts an incomplete record into the database for future use
public void insertLesson(Record record) throws SQLException{
    insertStatement = conn.prepareStatement("insert into lessons values (?);");
    int i; String s;
    i = record.getID();
    insertStatement.setInt(1, i); 
    /*call an insert for every lesson field in record.  to save disk space, images are
    saved by their image path instead of the image itself.  same for video and audio
    use setInt(spot in row, data to insert) to do it.  use setString for strings*/
    insertStatement.executeUpdate(); //updates the DB
}

//this method saves a completed record to the database
public void insertRecord(Record record) throws SQLException{
    insertStatement = conn.prepareStatement("insert into records values (?);");
    int i; String s;
    i = record.getID();
    insertStatement.setInt(1, i); 
    /*call an insert for every field in record.  to save disk space, images are
    saved by their image path instead of the image itself.  same for video and audio
    use setInt(spot in row, data to insert) to do it.  use setString for strings*/
    insertStatement.executeUpdate(); //updates the DB
}

private void checkRecordTableExistence() throws SQLException {
    //@TODO SQL statements to make the Record table, with only some of the fields from Record
    String makeTable = "SQL statement to make the 'records' table. should check if the table exists first)";
    PreparedStatement ps = conn.prepareStatement(makeTable);
    ps.executeUpdate();
    }

private void checkLessonTableExistence() throws SQLException {
    //@TODO SQL statements to make the Lesson table, which stores only the fields
    //needed to bring a lesson up to be completed.  For storing lessons for future use
    String makeTable = "SQL statement to make the 'records' table. should check if the table exists first)";
    PreparedStatement ps = conn.prepareStatement(makeTable);
    ps.executeUpdate();
}

}



