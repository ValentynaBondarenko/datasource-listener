package com.bondarenko;

import com.bondarenko.listener.*;
import org.h2.jdbcx.*;
import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class DataSourceListenerTest {
    private DataSourceListener dataSourceListener;

    @BeforeEach
    void setUp() {
        JdbcDataSource h2DataSource = new JdbcDataSource();
        h2DataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        h2DataSource.setUser("sa");
        dataSourceListener = new DataSourceListener(h2DataSource);
    }


    @Test
    void assertQueryCount() {
        //prepare
        DataSourceListener.reset();

        //when
        performSelectQueries();

        //then
        assertEquals(3, DataSourceListener.getSelectCount());
        assertEquals(3, DataSourceListener.getInsertCount());
        assertEquals(1, DataSourceListener.getUpdateCount());
        assertEquals(2, DataSourceListener.getDeleteCount());

        //when
        DataSourceListener.reset();

        //then
        assertEquals(0, DataSourceListener.getSelectCount());
        assertEquals(0, DataSourceListener.getInsertCount());
        assertEquals(0, DataSourceListener.getUpdateCount());
        assertEquals(0, DataSourceListener.getDeleteCount());
    }

    private void performSelectQueries() {
        try (Connection connection = DataSourceListener.getDataSource().getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS table1 (id INT PRIMARY KEY, name VARCHAR(255))");

            // INSERT queries
            statement.executeUpdate("INSERT INTO table1 (id, name) VALUES (1, 'Name1')");
            statement.executeUpdate("INSERT INTO table1 (id, name) VALUES (2, 'Name2')");
            statement.executeUpdate("INSERT INTO table1 (id, name) VALUES (3, 'Name3')");

            // SELECT queries
            statement.executeQuery("SELECT * FROM table1");
            statement.executeQuery("SELECT * FROM table1");
            statement.executeQuery("SELECT * FROM table1");

            // UPDATE query
            statement.executeUpdate("UPDATE table1 SET name = 'UpdatedName' WHERE id = 1");

            // DELETE queries
            statement.executeUpdate("DELETE FROM table1 WHERE id = 2");
            statement.executeUpdate("DELETE FROM table1 WHERE id = 3");
        } catch (SQLException e) {
            throw new RuntimeException("Can`t execute query", e);
        }

    }
}