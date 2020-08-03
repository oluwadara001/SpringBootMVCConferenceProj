package com.YomiOluwadara.conferencedemo;

import ch.qos.logback.core.util.CloseUtil;
import com.YomiOluwadara.conferencedemo.Config.DataSourceConfig;
import com.YomiOluwadara.conferencedemo.models.Session;
import com.YomiOluwadara.conferencedemo.services.SessionsService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(classes = {DataSourceConfig.class, ConferenceDemoApplication.class})
public class SessionsControllerTestLiveDb {
	@Autowired
	DataSource dataSource;
	static Connection connection;
	static SessionsService sessionsService = new SessionsService();

	//set values for variables that will be inserted into the live db
	private static final int session_id = 100;
	private static final String session_name = "testSessionName";
	private static final String session_description = "testSessionDesc";
	private static final int session_length = 60;

	private static final String sessionTableInsertionStatement =
			"insert into sessions" + "(session_id,session_name,session_description,session_length)"
					+ "values (?,?,?,?)";
	private static final String sessionTableDeletionStatement = "delete from sessions s " + "where s.session_id = ?";

	private static CloseUtil DbUtils;
	public Session session = new Session();
	public SessionsController sessionsController = new SessionsController(sessionsService);
	public List<Session> listAllSessions = new ArrayList<Session>(); //holds the list of all sessions

//	@BeforeAll
//	public static void insertDataIntoSessionsTable() throws SQLException {
//			 dataSource = getDataSource();
//			 connection = dataSource.getConnection();
//		try (PreparedStatement statement = connection.prepareStatement(sessionTableInsertionStatement)) {
//			statement.setInt(1, session_id);
//			statement.setString(2, session_name);
//			statement.setString(3, session_description);
//			statement.setInt(4, session_length);
//			statement.executeUpdate();
//		}
//	}

	@AfterAll
	public static void deleteQuery() throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(sessionTableDeletionStatement)) {
			statement.setInt(1, session_id);
			statement.executeUpdate();
		} catch (Exception e) {
			fail("could not delete test code values from database", e);
		} finally {
			DbUtils.closeQuietly((Closeable) connection);
		}
	}

	@BeforeEach
	public void setup() throws SQLException {
		connection = dataSource.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(sessionTableInsertionStatement)) {
			statement.setInt(1, session_id);
			statement.setString(2, session_name);
			statement.setString(3, session_description);
			statement.setInt(4, session_length);
			statement.executeUpdate();

			session.setSession_id(session_id);
			session.setSession_description(session_description);
			session.setSession_name(session_name);
			session.setSession_length(session_length);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

//		@AfterEach   //check if listAllSessions.clear(); is needed
//	public void cleanUp() {
//		listAllSessions.clear();
//	}

	@DisplayName("Test to verify that listAll from controller class has the same content as what was inserted into db")
	@Test
	public void verifyControllerAndDbMatches() throws SQLException {
//		SessionsController sessionsController = new SessionsController(sessionsService);
		List<Session> fetchedResultFromLiveDb = sessionsController.listAllSessions(); //for sessionController
		session.getSession_id();
		session.getSession_description();
		session.getSession_name();
		session.getSession_length();
		fetchedResultFromLiveDb.add(session);
		assertThat(fetchedResultFromLiveDb.size(), is(sessionsController.listAllSessions().size()));
	}
}
