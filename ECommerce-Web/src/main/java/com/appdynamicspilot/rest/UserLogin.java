package com.appdynamicspilot.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.appdynamicspilot.model.User;
import com.appdynamicspilot.service.UserService;
import com.appdynamicspilot.util.SpringContext;

@Path("/user")
public class UserLogin {
    private static final Logger logger = Logger.getLogger(UserLogin.class.getName());

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void post(@Context HttpServletRequest req, @FormParam("username") String name, @FormParam("password") String password) {
        HttpSession session = req.getSession(true);
        UserService userSvc = getUserService();
        boolean valid = userSvc.validateMember(name, password);

        if (userSvc.validateMember(name, password.trim())) {
            User user = userSvc.getMemberByLoginName(name);
            session.setAttribute("USER", user);
        }
    }

    @Path("/login")
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
    public String validateUser(@Context HttpServletRequest req, @FormParam("username") String name, @FormParam("password") String password) {
        HttpSession session = req.getSession(true);
        UserService userSvc = getUserService();
        boolean valid = userSvc.validateMember(name, password.trim());
        logger.debug("*** Is user valid** " + valid);
        if (valid) {
            User user = userSvc.getMemberByLoginName(name);
            logger.debug("*** user email is ** " + user.getEmail());
            session.setAttribute("USER", user);
        }

        return valid ? "success" : "fail";
    }

    public UserService getUserService() {
        return (UserService) SpringContext.getBean("userService");
    }
}
