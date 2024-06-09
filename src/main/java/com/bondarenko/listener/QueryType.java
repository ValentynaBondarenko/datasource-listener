package com.bondarenko.listener;

public enum QueryType {
    SELECT,
    INSERT,
    UPDATE,
    DELETE,
    UNKNOWN;

    static QueryType fromString(String query) {
        String upperCaseQuery = query.trim().toUpperCase();
        if (upperCaseQuery.contains("SELECT")) {
            return SELECT;
        } else if (upperCaseQuery.contains("INSERT")) {
            return INSERT;
        } else if (upperCaseQuery.contains("UPDATE")) {
            return UPDATE;
        } else if (upperCaseQuery.contains("DELETE")) {
            return DELETE;
        } else {
            return UNKNOWN;
        }
    }
}
