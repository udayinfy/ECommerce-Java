package com.appdynamicspilot.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class BTOverloadAction extends ActionSupport implements Preparable,
        ServletResponseAware, ServletRequestAware {
    private static final Logger log = Logger.getLogger(BTOverloadAction.class);
    private HttpServletRequest request;
    private HttpServletResponse response;

    public void prepare() throws Exception {
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getServletRequest() {
        return request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletResponse getServletResponse() {
        return response;
    }

    public String execute() {
        String input = getServletRequest().getParameter("count");

        int count, j;
        try {
            count = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            count = 1;
        }

        log.debug("count: " + count);

        for (int i = 1; i < count; i++) {
            j = count % i;

            PrintWriter out;
            try {
                out = this.response.getWriter();
                out.println("count: " + count);
                out.println("i: " + i);
                out.println("j: " + j);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return "SUCCESS";
    }
}
