package com.bondarenko.datasource;

import com.bondarenko.listener.DataSourceListener;

import javax.sql.*;
import java.lang.reflect.*;
import java.sql.*;


public class ProxyDataSourceFactory {
    public static DataSource createProxyDataSource(DataSource realDataSource, DataSourceListener listener) {
        return (DataSource) Proxy.newProxyInstance(
                DataSource.class.getClassLoader(),
                new Class<?>[]{DataSource.class},
                (proxy, method, args) -> {
                    if ("getConnection".equals(method.getName())) {
                        Connection realConnection = (args == null || args.length == 0) ?
                                realDataSource.getConnection() :
                                realDataSource.getConnection((String) args[0], (String) args[1]);
                        return createProxyConnection(realConnection, listener);
                    }
                    return method.invoke(realDataSource, args);
                }
        );
    }

    private static Connection createProxyConnection(Connection realConnection, DataSourceListener listener) {
        return (Connection) Proxy.newProxyInstance(
                Connection.class.getClassLoader(),
                new Class<?>[]{Connection.class},
                (proxy, method, args) -> {
                    if ("createStatement".equals(method.getName())) {
                        Statement realStatement = realConnection.createStatement();
                        return createProxyStatement(realStatement, listener);
                    }
                    return method.invoke(realConnection, args);
                }
        );
    }

    private static Statement createProxyStatement(Statement realStatement, DataSourceListener listener) {
        return (Statement) Proxy.newProxyInstance(
                Statement.class.getClassLoader(),
                new Class<?>[]{Statement.class},
                (proxy, method, args) -> {
                    if ("executeQuery".equals(method.getName()) && args != null && args.length > 0) {
                        String sql = (String) args[0];
                        listener.processQuery(sql);
                    } else if (("executeUpdate".equals(method.getName()) || "execute".equals(method.getName())) && args != null && args.length > 0) {
                        String sql = (String) args[0];
                        listener.processQuery(sql);
                    }
                    return method.invoke(realStatement, args);
                }
        );
    }
}
