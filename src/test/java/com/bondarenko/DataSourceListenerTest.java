package com.bondarenko;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataSourceListenerTest {
    @Test
    public void testDataSourceWrapper() throws Exception {
        try (PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")) {
            postgres.start();

            String jdbcUrl = postgres.getJdbcUrl();
            String username = postgres.getUsername();
            String password = postgres.getPassword();


            try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
                try (Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate("CREATE TABLE test_table (id SERIAL PRIMARY KEY, name VARCHAR(255))");
                }

                try (Statement stmt = conn.createStatement()) {
                    stmt.executeQuery("SELECT * FROM test_table");
                    DataSourceListener.getInstance().processQuery("SELECT * FROM test_table");

                    stmt.executeUpdate("INSERT INTO test_table (name) VALUES ('test')");
                    DataSourceListener.getInstance().processQuery("INSERT INTO test_table (name) VALUES ('test')");

                    stmt.executeUpdate("UPDATE test_table SET name = 'updated' WHERE id = 1");
                    DataSourceListener.getInstance().processQuery("UPDATE test_table SET name = 'updated' WHERE id = 1");
                }

                DataSourceListener dataSourceListener = DataSourceListener.getInstance();
                assertEquals(1, dataSourceListener.getSelectCount());
                assertEquals(1, dataSourceListener.getInsertCount());
                assertEquals(1, dataSourceListener.getUpdateCount());
                assertEquals(0, dataSourceListener.getDeleteCount());
            }

        }
    }
}