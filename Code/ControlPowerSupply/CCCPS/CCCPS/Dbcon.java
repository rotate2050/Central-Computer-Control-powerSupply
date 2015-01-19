package CCCPS;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;

public class Dbcon {
	public Connection conn;
	private ResultSet rs;
	private Statement stmt;
	protected static String host;
	protected static String port;
	protected static String sid;
	protected static String uname;
	protected static String pass;

	@SuppressWarnings("finally")
	public int open() {
		int i = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@" + host
					+ ":" + port + ":" + sid, uname, pass);
			conn.setAutoCommit(true);
			i = 1;
		} catch (Exception e) {
			e.printStackTrace();
			// new HandleException(e.getMessage());
		} finally {
			return (i);
		}
	}

	@SuppressWarnings("finally")
	public int close() {
		int i = 0;
		try {
			if (stmt != null)
				stmt.close();
			conn.close();
			i = 1;
		} catch (Exception e) {
			e.printStackTrace();
			new HandleException(e.getMessage());
		} finally {
			return (i);
		}
	}

	@SuppressWarnings("finally")
	public ResultSet selquery(String qry) {
		String query = qry;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
			new HandleException(e.getMessage());
		} finally {
			return (rs);
		}
	}

	@SuppressWarnings("finally")
	public int dmlstmt(String qry) {
		String query = qry;
		int i = -1;
		try {
			stmt = conn.createStatement();
			i = stmt.executeUpdate(query);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new HandleException(e.getMessage());
		} finally {
			return (i);
		}
	}
}

// select count(sub_id),sub_id from attendance where std_id=080410107024 group
// by sub_id to count the number of attendance of typical std_id
// select sub_nm from sub_detail where sub_id=180701 to get sub_nm from sub_id
// select count(sub_id),sub_id from batch_wkday where batch_id=801 group by
// sub_id to count the number of labs ot typica batch
// select sub_id,batch_id,lab_nm from lab_tt where slot_id like '1%' to view day
// wise timetable
// select day,time from slot_map where slot_id='12' to get day and time
// according to slot_id
// select sem,batch from sem_batch where batch_id='801' to get sem and batch
// according to batch_id
// select lab_nm,slot_id,sub_id,batch_id from lab_tt where lab_nm='F1'; to get
// the schedule of individual lab in split pane