package com.appdynamicspilot.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appdynamicspilot.action.DeadLockAction;

/**
 * Servlet implementation class DeadLockServlet
 */
public class DeadLockServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeadLockServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String count = request.getParameter("count");
        int intCount = 1;
        if ((count != null) && ("".equalsIgnoreCase(count.trim()))) {
            intCount = Integer.valueOf(count).intValue();
        }

        DeadLockAction javaLock = new DeadLockAction();
        javaLock.runDeadLock(intCount);
    }

}