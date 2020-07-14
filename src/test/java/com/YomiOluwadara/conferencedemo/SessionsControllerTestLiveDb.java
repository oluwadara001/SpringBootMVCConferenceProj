package com.YomiOluwadara.conferencedemo;

import javax.sql.DataSource;
import java.sql.Connection;

public class SessionsControllerTestLiveDb {
	//test listAllSessions
	static DataSource dataSource;
	static Connection connection;
	//set values for variables that will be inserted into the live db
	private static final int session_id = 100;
	private static final String session_name = "testSessionName";
	private static final String session_description = "testSessionDesc";
	private static final int session_length = 60;

	private static final String sessionTableInsertionStatement =
			"insert into sessions" + "(session_id,session_name,session_description,session_length)"
					+ "values (?,?,?,?)";

}
