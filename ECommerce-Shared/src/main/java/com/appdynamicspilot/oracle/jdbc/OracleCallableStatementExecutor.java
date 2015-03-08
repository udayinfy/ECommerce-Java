package com.appdynamicspilot.oracle.jdbc;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;


public class OracleCallableStatementExecutor extends OracleQueryExecutor {
    private final static Logger LOGGER = Logger.getLogger(OracleCallableStatementExecutor.class.getName());

        public void executeOracleQuery() {
			Connection connection = null;
			CallableStatement cs = null;
			try {
				connection = getDataSource().getConnection();
				cs = connection.prepareCall("{ call getItem(?) }");
                cs.setInt(1,200);
				cs.execute();
			} catch (Exception ex) {
				LOGGER.error("This may be ignored in case of Oracle is not setup");
				LOGGER.error(ex.getMessage());
			} finally {
				if (cs != null) try {cs.close();} catch (Exception ex) {}
				if (connection != null) try {connection.close();} catch (Exception ex) {}
			}
		LOGGER.info("Done");
    }
}