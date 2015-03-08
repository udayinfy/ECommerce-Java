package com.appdynamicspilot.oracle.jdbc;



import javax.sql.DataSource;

import org.apache.log4j.Logger;


/**
 * 
 * @author Sharat Jagannath
 *
 */
public abstract class OracleQueryExecutor {
    private final static Logger LOGGER = Logger.getLogger(OracleQueryExecutor.class.getName());
    private String oracleQueryString;
    private DataSource ds  = null;



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
    public DataSource getDataSource() {
        return this.ds;
    }

};
