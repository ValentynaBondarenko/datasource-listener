package com.bondarenko.proxydatasource;

import com.bondarenko.listener.DataSourceListener;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class ProxyConnection implements Connection {
    private final Connection realConnection;

    public ProxyConnection(Connection realConnection) {
        this.realConnection = realConnection;
    }

    @Override
    public Statement createStatement() throws SQLException {
        return new ProxyStatement(realConnection.createStatement());
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        DataSourceListener.processQuery(sql);
        return realConnection.prepareStatement(sql);
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        DataSourceListener.processQuery(sql);
        return realConnection.prepareCall(sql);
    }

    @Override
    public void close() throws SQLException {
        realConnection.close();
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return realConnection.isWrapperFor(iface);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return realConnection.unwrap(iface);
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        realConnection.abort(executor);
    }

    @Override
    public void clearWarnings() throws SQLException {
        realConnection.clearWarnings();
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return new ProxyStatement(realConnection.createStatement(resultSetType, resultSetConcurrency));
    }

    @Override
    public void commit() throws SQLException {
        realConnection.commit();
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return realConnection.createArrayOf(typeName, elements);
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return null;
    }

    @Override
    public Blob createBlob() throws SQLException {
        return realConnection.createBlob();
    }

    @Override
    public Clob createClob() throws SQLException {
        return realConnection.createClob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        return realConnection.createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return realConnection.createSQLXML();
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return realConnection.getAutoCommit();
    }

    @Override
    public String getCatalog() throws SQLException {
        return realConnection.getCatalog();
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return realConnection.getClientInfo();
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return realConnection.getClientInfo(name);
    }

    @Override
    public int getHoldability() throws SQLException {
        return realConnection.getHoldability();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return realConnection.getMetaData();
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return realConnection.getNetworkTimeout();
    }

    @Override
    public String getSchema() throws SQLException {
        return realConnection.getSchema();
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return realConnection.getTransactionIsolation();
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return realConnection.getTypeMap();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return realConnection.getWarnings();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return realConnection.isClosed();
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return realConnection.isReadOnly();
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return realConnection.isValid(timeout);
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        return realConnection.nativeSQL(sql);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return realConnection.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return realConnection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {

        DataSourceListener.processQuery(sql);
        return realConnection.prepareStatement(sql, autoGeneratedKeys);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {

        DataSourceListener.processQuery(sql);
        return realConnection.prepareStatement(sql, columnIndexes);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {

        DataSourceListener.processQuery(sql);
        return realConnection.prepareStatement(sql, columnNames);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return realConnection.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return realConnection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        realConnection.releaseSavepoint(savepoint);
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return new ProxyStatement(realConnection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability));
    }

    @Override
    public void rollback() throws SQLException {
        realConnection.rollback();
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        realConnection.rollback(savepoint);
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        realConnection.setAutoCommit(autoCommit);
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        realConnection.setCatalog(catalog);
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        realConnection.setClientInfo(properties);
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        realConnection.setClientInfo(name, value);
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
        realConnection.setHoldability(holdability);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        realConnection.setNetworkTimeout(executor, milliseconds);
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        realConnection.setReadOnly(readOnly);
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return realConnection.setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return realConnection.setSavepoint(name);
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        realConnection.setSchema(schema);
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        realConnection.setTransactionIsolation(level);
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        realConnection.setTypeMap(map);
    }


}
