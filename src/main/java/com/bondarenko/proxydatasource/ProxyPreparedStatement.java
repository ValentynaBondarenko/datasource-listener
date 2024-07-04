package com.bondarenko.proxydatasource;

import com.bondarenko.listener.DataSourceListener;

import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public class ProxyPreparedStatement implements PreparedStatement {
    private final PreparedStatement realPreparedStatement;
    private final String sql;

    public ProxyPreparedStatement(PreparedStatement realPreparedStatement, String sql) {
        this.realPreparedStatement = realPreparedStatement;
        this.sql = sql;
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        DataSourceListener.processQuery(sql);
        return realPreparedStatement.executeQuery();
    }

    @Override
    public int executeUpdate() throws SQLException {
        DataSourceListener.processQuery(sql);
        return realPreparedStatement.executeUpdate();
    }

    @Override
    public boolean execute() throws SQLException {
        DataSourceListener.processQuery(sql);
        return realPreparedStatement.execute();
    }

    @Override
    public void addBatch() throws SQLException {
        DataSourceListener.processQuery(sql);
        realPreparedStatement.addBatch();
    }

    @Override
    public void clearBatch() throws SQLException {
        realPreparedStatement.clearBatch();
    }

    @Override
    public int[] executeBatch() throws SQLException {
        return realPreparedStatement.executeBatch();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return realPreparedStatement.getConnection();
    }

    @Override
    public void close() throws SQLException {
        realPreparedStatement.close();
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return realPreparedStatement.getMaxFieldSize();
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        realPreparedStatement.setMaxFieldSize(max);
    }

    @Override
    public int getMaxRows() throws SQLException {
        return realPreparedStatement.getMaxRows();
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        realPreparedStatement.setMaxRows(max);
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        realPreparedStatement.setEscapeProcessing(enable);
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return realPreparedStatement.getQueryTimeout();
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        realPreparedStatement.setQueryTimeout(seconds);
    }

    @Override
    public void cancel() throws SQLException {
        realPreparedStatement.cancel();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return realPreparedStatement.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        realPreparedStatement.clearWarnings();
    }

    @Override
    public void setCursorName(String name) throws SQLException {
        realPreparedStatement.setCursorName(name);
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return realPreparedStatement.getResultSet();
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return realPreparedStatement.getUpdateCount();
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return realPreparedStatement.getMoreResults();
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        realPreparedStatement.setFetchDirection(direction);
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return realPreparedStatement.getFetchDirection();
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        realPreparedStatement.setFetchSize(rows);
    }

    @Override
    public int getFetchSize() throws SQLException {
        return realPreparedStatement.getFetchSize();
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return realPreparedStatement.getResultSetConcurrency();
    }

    @Override
    public int getResultSetType() throws SQLException {
        return realPreparedStatement.getResultSetType();
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return realPreparedStatement.getMoreResults(current);
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return realPreparedStatement.getGeneratedKeys();
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        DataSourceListener.processQuery(sql);
        return realPreparedStatement.executeUpdate(sql);
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        DataSourceListener.processQuery(sql);
        return realPreparedStatement.execute(sql);
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        DataSourceListener.processQuery(sql);
        return realPreparedStatement.execute(sql, autoGeneratedKeys);
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        DataSourceListener.processQuery(sql);
        return realPreparedStatement.execute(sql, columnIndexes);
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        DataSourceListener.processQuery(sql);
        return realPreparedStatement.execute(sql, columnNames);
    }
    @Override
    public boolean isClosed() throws SQLException {
        return realPreparedStatement.isClosed();
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        realPreparedStatement.setPoolable(poolable);
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return realPreparedStatement.isPoolable();
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        realPreparedStatement.closeOnCompletion();
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return realPreparedStatement.isCloseOnCompletion();
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return realPreparedStatement.getResultSetHoldability();
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        DataSourceListener.processQuery(sql);
        return realPreparedStatement.executeUpdate(sql, autoGeneratedKeys);
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        DataSourceListener.processQuery(sql);
        return realPreparedStatement.executeUpdate(sql, columnIndexes);
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        DataSourceListener.processQuery(sql);
        return realPreparedStatement.executeUpdate(sql, columnNames);
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        DataSourceListener.processQuery(sql);
        realPreparedStatement.addBatch(sql);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        if (iface.isInstance(realPreparedStatement)) {
            return iface.cast(realPreparedStatement);
        } else {
            throw new SQLException("Wrapped statement is not an instance of " + iface);
        }
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return iface.isInstance(realPreparedStatement);
    }


    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        DataSourceListener.processQuery(sql);
        return realPreparedStatement.executeQuery(sql);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        realPreparedStatement.setNull(parameterIndex, sqlType);
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        realPreparedStatement.setBoolean(parameterIndex, x);
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        realPreparedStatement.setByte(parameterIndex, x);
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        realPreparedStatement.setShort(parameterIndex, x);
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        realPreparedStatement.setInt(parameterIndex, x);
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        realPreparedStatement.setLong(parameterIndex, x);
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        realPreparedStatement.setFloat(parameterIndex, x);
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        realPreparedStatement.setDouble(parameterIndex, x);
    }

    @Override
    public void setBigDecimal(int parameterIndex, java.math.BigDecimal x) throws SQLException {
        realPreparedStatement.setBigDecimal(parameterIndex, x);
    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        realPreparedStatement.setString(parameterIndex, x);
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        realPreparedStatement.setBytes(parameterIndex, x);
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        realPreparedStatement.setDate(parameterIndex, x);
    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        realPreparedStatement.setTime(parameterIndex, x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        realPreparedStatement.setTimestamp(parameterIndex, x);
    }

    @Override
    public void setAsciiStream(int parameterIndex, java.io.InputStream x, int length) throws SQLException {
        realPreparedStatement.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setUnicodeStream(int parameterIndex, java.io.InputStream x, int length) throws SQLException {
        realPreparedStatement.setUnicodeStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, java.io.InputStream x, int length) throws SQLException {
        realPreparedStatement.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void clearParameters() throws SQLException {
        realPreparedStatement.clearParameters();
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        realPreparedStatement.setObject(parameterIndex, x, targetSqlType);
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        realPreparedStatement.setObject(parameterIndex, x);
    }


    @Override
    public void setCharacterStream(int parameterIndex, java.io.Reader reader, int length) throws SQLException {
        realPreparedStatement.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        realPreparedStatement.setRef(parameterIndex, x);
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        realPreparedStatement.setBlob(parameterIndex, x);
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        realPreparedStatement.setClob(parameterIndex, x);
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        realPreparedStatement.setArray(parameterIndex, x);
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return realPreparedStatement.getMetaData();

    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        realPreparedStatement.setDate(parameterIndex, x, cal);
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        realPreparedStatement.setTime(parameterIndex, x, cal);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        realPreparedStatement.setTimestamp(parameterIndex, x, cal);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        realPreparedStatement.setNull(parameterIndex, sqlType, typeName);
    }

    @Override
    public void setURL(int parameterIndex, java.net.URL x) throws SQLException {
        realPreparedStatement.setURL(parameterIndex, x);
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return realPreparedStatement.getParameterMetaData();
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        realPreparedStatement.setRowId(parameterIndex, x);
    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        realPreparedStatement.setNString(parameterIndex, value);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, java.io.Reader value, long length) throws SQLException {
        realPreparedStatement.setNCharacterStream(parameterIndex, value, length);
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        realPreparedStatement.setNClob(parameterIndex, value);
    }

    @Override
    public void setClob(int parameterIndex, java.io.Reader reader, long length) throws SQLException {
        realPreparedStatement.setClob(parameterIndex, reader, length);
    }

    @Override
    public void setBlob(int parameterIndex, java.io.InputStream inputStream, long length) throws SQLException {
        realPreparedStatement.setBlob(parameterIndex, inputStream, length);
    }

    @Override
    public void setNClob(int parameterIndex, java.io.Reader reader, long length) throws SQLException {
        realPreparedStatement.setNClob(parameterIndex, reader, length);
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        realPreparedStatement.setSQLXML(parameterIndex, xmlObject);
    }

    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        realPreparedStatement.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType) throws SQLException {
        realPreparedStatement.setObject(parameterIndex, x, targetSqlType);
    }

    @Override
    public long executeLargeUpdate() throws SQLException {
        return realPreparedStatement.executeLargeUpdate();
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        realPreparedStatement.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }


    @Override
    public void setAsciiStream(int parameterIndex, java.io.InputStream x, long length) throws SQLException {
        realPreparedStatement.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, java.io.InputStream x, long length) throws SQLException {
        realPreparedStatement.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void setCharacterStream(int parameterIndex, java.io.Reader reader, long length) throws SQLException {
        realPreparedStatement.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setAsciiStream(int parameterIndex, java.io.InputStream x) throws SQLException {
        realPreparedStatement.setAsciiStream(parameterIndex, x);
    }

    @Override
    public void setBinaryStream(int parameterIndex, java.io.InputStream x) throws SQLException {
        realPreparedStatement.setBinaryStream(parameterIndex, x);
    }

    @Override
    public void setCharacterStream(int parameterIndex, java.io.Reader reader) throws SQLException {
        realPreparedStatement.setCharacterStream(parameterIndex, reader);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, java.io.Reader value) throws SQLException {
        realPreparedStatement.setNCharacterStream(parameterIndex, value);
    }

    @Override
    public void setClob(int parameterIndex, java.io.Reader reader) throws SQLException {
        realPreparedStatement.setClob(parameterIndex, reader);
    }

    @Override
    public void setBlob(int parameterIndex, java.io.InputStream inputStream) throws SQLException {
        realPreparedStatement.setBlob(parameterIndex, inputStream);
    }

    @Override
    public void setNClob(int parameterIndex, java.io.Reader reader) throws SQLException {
        realPreparedStatement.setNClob(parameterIndex, reader);
    }

}
