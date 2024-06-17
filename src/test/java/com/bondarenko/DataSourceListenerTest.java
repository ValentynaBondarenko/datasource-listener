package com.bondarenko;

import com.bondarenko.listener.*;
import com.bondarenko.proxydatasource.*;
import org.h2.jdbcx.*;
import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class DataSourceListenerTest {
    private ProxyDataSource proxyDataSource;

    @BeforeEach
    void setUp() {
        JdbcDataSource h2DataSource = new JdbcDataSource();
        h2DataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        h2DataSource.setUser("sa");
        proxyDataSource = new ProxyDataSource(h2DataSource);
        DataSourceListener.setDataSource(proxyDataSource);
    }

    @Test
    void assertQueryCount() {
        // Prepare
        DataSourceListener.reset();

        // When
        performSelectQueries();

        // Then
        assertEquals(3, DataSourceListener.getSelectCount());
        assertEquals(3, DataSourceListener.getInsertCount());
        assertEquals(1, DataSourceListener.getUpdateCount());
        assertEquals(2, DataSourceListener.getDeleteCount());

        // When
        DataSourceListener.reset();

        // Then
        assertEquals(0, DataSourceListener.getSelectCount());
        assertEquals(0, DataSourceListener.getInsertCount());
        assertEquals(0, DataSourceListener.getUpdateCount());
        assertEquals(0, DataSourceListener.getDeleteCount());
    }

    private void performSelectQueries() {
        try (Connection connection = proxyDataSource.getConnection()) {
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
            throw new RuntimeException("Can't execute query", e);
        }
    }
}