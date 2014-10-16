/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appdynamics.jdbc;

import java.sql.Connection;


/**
 *
 * @author Owner
 */
public class MDriverManager {

    public static Connection getConnection(String url, String username, String password){
        return new MConnection();
    }
}
