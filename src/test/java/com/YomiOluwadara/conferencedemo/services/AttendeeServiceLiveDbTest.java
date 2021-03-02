
package com.YomiOluwadara.conferencedemo.services;

import com.YomiOluwadara.conferencedemo.ConferenceDemoApplication;
import com.YomiOluwadara.conferencedemo.Config.DataSourceConfig;
import com.YomiOluwadara.conferencedemo.model.Attendee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest(classes = {DataSourceConfig.class, ConferenceDemoApplication.class})
public class AttendeeServiceLiveDbTest {

	@Autowired
	static DataSource dataSource;
	static Connection connection;
	static String selectAllStatement = "select session_id," +
									 "session_name," +
									 "session_description," +
									 "session_length," +
									 "attendees_attendee_id"+
									 "from sessions";

	static String selectCountStatement = "select count(*) from attendees";

	@BeforeAll
	public static void setUp() throws SQLException {
		connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(selectAllStatement);
		PreparedStatement preparedStatement1= connection.prepareStatement(selectCountStatement);
		preparedStatement.execute();
		preparedStatement1.execute();
	}

	@Test
	@DisplayName("All users in users table should be returned")
	void testAllAttendees(){
		AttendeeService attendeeService = new AttendeeService();
		assertThat(attendeeService.allAttendees().size(),is(selectCountStatement));


	}

}



