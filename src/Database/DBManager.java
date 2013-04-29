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
 public  String sDriver = "org.sqlite.JDBC"; 
public  String sUrl = "jdbc:sqlite:DISCO_DB.db";
public  int iTimeout = 30;
public  Connection conn = null;
public  Statement statement = null;
 
 
public DBManager() throws Exception
{
    //setDriver(sDriver);
    //setUrl(sUrl);
    setConnection();
    setStatement();
    checkTableExistence();
}
 
 /*keep this around in case we need to use multiple databases
private void setDriver(String sDriverVar){
    sDriver = sDriverVar;
}
 
private void setUrl(String sUrlVar){
    sUrl = sUrlVar;
}*/
 
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
    statement = conn.createStatement();
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

private void checkTableExistence() throws SQLException {
    String makeTable = "SQL statement to make the table. should check if the table exists first)";
    PreparedStatement ps = conn.prepareStatement(makeTable);
    ps.executeUpdate();
    }

}



