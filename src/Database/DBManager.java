package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.DatabaseMetaData;
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
    //setDriver(sDriver);
    //setUrl(sUrl);
    setConnection();
    //setStatement();
    checkTableExistence();
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

public void getRecord(String data, int field) throws SQLException{
    ResultSet results = statement.executeQuery("select * from records where " + data + " = n;");
    results.getString(data); //this accesses the data in that field
}

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

private void checkTableExistence() throws SQLException {
    String makeTable = "SQL statement to make the 'records' table. should check if the table exists first)";
    PreparedStatement ps = conn.prepareStatement(makeTable);
    ps.executeUpdate();
    }

}



