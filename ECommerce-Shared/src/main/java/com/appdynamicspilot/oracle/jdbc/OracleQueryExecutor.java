package com.appdynamicspilot.oracleJDBC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 * 
 * @author Sharat Jagannath
 *
 */
public class abstract OracleQueryExecutor {
    private final static Logger LOGGER = Logger.getLogger(OracleQueryExecutor.class.getName());
    private String oracleQueryString;
    private DataSource ds  = null;

    
//    public void executeOracleQuery() {
//			Connection connection = null;
//			CallableStatement cs = null;
//			try {
//				connection = ds.getConnection();
//				cs = connection.prepareCall("{ call getItem(?) }");
//                cs.setInt(1,200);
//				cs.execute();
//			} catch (Exception ex) {
//				LOGGER.error("This may be ignored in case of Oracle is not setup");
//				LOGGER.error(ex.getMessage());
//			} finally {
//				if (cs != null) try {cs.close();} catch (Exception ex) {}
//				if (connection != null) try {connection.close();} catch (Exception ex) {}
//			}
//		LOGGER.info("Done");
//    }

    public abstract void  executeOracleQuery();
    
    public void setOracleQueryString(String oracleQueryString) {
        this.oracleQueryString = oracleQueryString;
    }

    public String getOracleQueryString() {
        return oracleQueryString;
    }
    
    public void setDataSource(DataSource dataSource) {
       this.ds = dataSource;
    }

}
