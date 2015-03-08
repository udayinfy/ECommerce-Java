package com.appdynamicspilot.oracle.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

/**
 * Created by aleftik on 3/8/15.
 */
public class OracleStatementExecutor extends OracleQueryExecutor {
    private static final Logger logger = Logger.getLogger(OracleStatementExecutor.class.getName());



    @Override
    public void executeOracleQuery() {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = getDataSource().getConnection();
            stmt = connection.createStatement();
            stmt.execute(getOracleQueryString());
        } catch (SQLException sqle) {
            logger.error("This may be ignored in case of Oracle is not setup");
            logger.error(sqle.getMessage());
        }  finally {
            if (connection != null) {try{connection.close();}catch (SQLException sqle) {}}
            if (stmt != null) {try{stmt.close();}catch (SQLException sqle) {}}
        }


    }
}
