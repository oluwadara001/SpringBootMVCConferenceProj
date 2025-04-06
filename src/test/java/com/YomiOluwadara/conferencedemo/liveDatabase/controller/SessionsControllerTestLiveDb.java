/*

/*
The test here has nit passed, due to multiple json found in class path
https://stackoverflow.com/questions/56560826/found-multiple-occurrences-of-org-json-jsonobject-on-the-class-path/56562534
 */
package com.YomiOluwadara.conferencedemo.liveDatabase.controller;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.YomiOluwadara.conferencedemo.ConferenceDemoApplication;
import com.YomiOluwadara.conferencedemo.Config.DataSourceConfig;
import com.YomiOluwadara.conferencedemo.controllers.SessionsController;
import com.YomiOluwadara.conferencedemo.dao.RegistrationDAO;
import com.YomiOluwadara.conferencedemo.dao.SessionDAO;
import com.YomiOluwadara.conferencedemo.dao.UserDao;
import com.YomiOluwadara.conferencedemo.models.Session;
import com.YomiOluwadara.conferencedemo.repositories.SpeakerRepository;
import com.YomiOluwadara.conferencedemo.services.SessionDetailsService;
import com.YomiOluwadara.conferencedemo.services.SessionService;
import com.YomiOluwadara.conferencedemo.services.SpeakerService;
import com.YomiOluwadara.conferencedemo.services.impl.SessionDetailsServiceImpl;
import com.YomiOluwadara.conferencedemo.services.impl.SessionServiceImpl;
import com.YomiOluwadara.conferencedemo.services.impl.SpeakerServiceImpl;

import ch.qos.logback.core.util.CloseUtil;

@SpringBootTest(classes = {DataSourceConfig.class, ConferenceDemoApplication.class})
public class SessionsControllerTestLiveDb {
    @Autowired
    DataSource dataSource;
    static Connection connection;
    
    @Autowired
    private SessionDAO sessionDAO;
    
    @Autowired
    private RegistrationDAO registrationDAO;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private SpeakerRepository speakerRepository;
    
    private SessionService sessionService;
    private SpeakerService speakerService;
    private SessionDetailsService sessionDetailsService;
    private SessionsController sessionsController;

    //set values for variables that will be inserted into the live db
    private static final int session_id = 100;
    private static final String session_name = "testSessionName";
    private static final String session_description = "testSessionDesc";
    private static final int session_capacity = 60;

    private static final String sessionTableInsertionStatement =
            "insert into sessions" + "(id,title,description,capacity)"
                    + "values (?,?,?,?)";
    private static final String sessionTableDeletionStatement = "delete from sessions s " + "where s.id = ?";

    private static CloseUtil DbUtils;
    public Session session = new Session();
    public List<Session> listAllSessions = new ArrayList<Session>(); //holds the list of all sessions

    @BeforeEach
    public void setup() throws SQLException {
        // Initialize services
        sessionService = new SessionServiceImpl(sessionDAO, registrationDAO, userDao);
        speakerService = new SpeakerServiceImpl(speakerRepository);
        sessionDetailsService = new SessionDetailsServiceImpl();
        sessionsController = new SessionsController(sessionService, speakerService, sessionDetailsService);
        
        // Setup database connection and test data
        connection = dataSource.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sessionTableInsertionStatement)) {
            statement.setInt(1, session_id);
            statement.setString(2, session_name);
            statement.setString(3, session_description);
            statement.setInt(4, session_capacity);
            statement.executeUpdate();

            session.setId((long) session_id);
            session.setDescription(session_description);
            session.setTitle(session_name);
            session.setCapacity(session_capacity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @AfterEach
    public void cleanup() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sessionTableDeletionStatement)) {
            statement.setInt(1, session_id);
            statement.executeUpdate();
        } catch (Exception e) {
            fail("could not delete test code values from database", e);
        } finally {
            DbUtils.closeQuietly((Closeable) connection);
        }
    }

    @DisplayName("Test to verify that listAll from controller class has the same content as what was inserted into db")
    @Test
    public void verifyControllerAndDbMatches() throws SQLException {
        List<Session> fetchedResultFromLiveDb = sessionsController.listSessionsApi();
        session.getId();
        session.getDescription();
        session.getTitle();
        session.getCapacity();
        fetchedResultFromLiveDb.add(session);
        assertThat(fetchedResultFromLiveDb.size(), is(sessionsController.listSessionsApi().size()));
    }
}




