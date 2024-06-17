package com.bondarenko.listener;

import com.bondarenko.proxydatasource.*;

import javax.sql.*;
import java.util.concurrent.atomic.*;

public class DataSourceListener {
    private static DataSource dataSource;
    private static AtomicInteger selectCount = new AtomicInteger(0);
    private static AtomicInteger insertCount = new AtomicInteger(0);
    private static AtomicInteger updateCount = new AtomicInteger(0);
    private static AtomicInteger deleteCount = new AtomicInteger(0);

    public static void setDataSource(DataSource dataSource) {
        DataSourceListener.dataSource = dataSource;
    }

    public static void reset() {
        selectCount.set(0);
        insertCount.set(0);
        updateCount.set(0);
        deleteCount.set(0);
    }


    public static void assertSelectCount(int expectedCount) {
        if (getSelectCount() != expectedCount) {
            throw new AssertionError("Expected " + expectedCount + " SELECT queries, but found " + getSelectCount());
        }
    }

    public static void assertInsertCount(int expectedCount) {
        if (getInsertCount() != expectedCount) {
            throw new AssertionError("Expected " + expectedCount + " INSERT queries, but found " + getInsertCount());
        }
    }

    public static void assertUpdateCount(int expectedCount) {
        if (getUpdateCount() != expectedCount) {
            throw new AssertionError("Expected " + expectedCount + " UPDATE queries, but found " + getUpdateCount());
        }
    }

    public static void assertDeleteCount(int expectedCount) {
        if (getDeleteCount() != expectedCount) {
            throw new AssertionError("Expected " + expectedCount + " DELETE queries, but found " + getDeleteCount());
        }
    }

    public static void processQuery(String query) {
        QueryType queryType = QueryType.fromString(query);
        switch (queryType) {
            case SELECT:
                selectCount.incrementAndGet();
                break;
            case INSERT:
                insertCount.incrementAndGet();
                break;
            case UPDATE:
                updateCount.incrementAndGet();
                break;
            case DELETE:
                deleteCount.incrementAndGet();
                break;
        }
    }

    public static int getSelectCount() {
        return selectCount.get();
    }

    public static int getInsertCount() {
        return insertCount.get();
    }

    public static int getUpdateCount() {
        return updateCount.get();
    }

    public static int getDeleteCount() {
        return deleteCount.get();
    }
}