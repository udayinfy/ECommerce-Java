package com.appdynamicspilot.action;

import java.util.Map;

import org.apache.log4j.Logger;

import com.appdynamicspilot.model.User;
import com.appdynamicspilot.service.UserService;
import com.appdynamicspilot.util.ArgumentUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

public class UserLoginAction extends ActionSupport implements ServletRequestAware {
    private static final Logger log = Logger.getLogger(UserLoginAction.class);
    private UserService userService;
    private String loginName;
    private String password;
    private HttpServletRequest request;

    public String memberLogin() {
        Map session = ActionContext.getContext().getSession();
        if (!ArgumentUtils.isNull(session.get("USER")))
            return "SUCCESS";
        if (userService.validateMember(loginName, password.trim())) {
            User user = userService.getMemberByLoginName(loginName);
            session.put("USER", user);
            return "SUCCESS";
        }
        addActionError("Invalid username or password");
        return "FAILURE";
    }

    public String memberLogOut() {
        Map session = ActionContext.getContext().getSession();
        session.remove("USER");


        if (session instanceof org.apache.struts2.dispatcher.SessionMap) {
            ((org.apache.struts2.dispatcher.SessionMap) session).invalidate();

        }
        request.getSession().invalidate();
        return "SUCCESS";
    }


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}
