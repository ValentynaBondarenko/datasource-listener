package com.bondarenko;

import java.util.concurrent.atomic.AtomicInteger;

public class DataSourceListener {
    private static final ThreadLocal<DataSourceListener> dataSourceListener = ThreadLocal.withInitial(DataSourceListener::new);
    private AtomicInteger selectCount;
    private AtomicInteger insertCount;
    private AtomicInteger updateCount;
    private AtomicInteger deleteCount;

    private DataSourceListener() {
        selectCount = new AtomicInteger(0);
        insertCount = new AtomicInteger(0);
        updateCount = new AtomicInteger(0);
        deleteCount = new AtomicInteger(0);
    }

    public static DataSourceListener getInstance() {
        return dataSourceListener.get();
    }

    public void processQuery(String query) {
        if (query.isEmpty()) {
            return;
        }
        query = query.toUpperCase();
        if (query.contains("SELECT ")) {
            selectCount.incrementAndGet();
        }
        if (query.contains("INSERT ")) {
            insertCount.incrementAndGet();
        }
        if (query.contains("UPDATE ")) {
            updateCount.incrementAndGet();
        }
        if (query.contains("DELETE ")) {
            deleteCount.incrementAndGet();
        }
    }

    public int getSelectCount() {
        return selectCount.get();
    }

    public int getInsertCount() {
        return insertCount.get();
    }

    public int getUpdateCount() {
        return updateCount.get();
    }

    public int getDeleteCount() {
        return deleteCount.get();
    }
}