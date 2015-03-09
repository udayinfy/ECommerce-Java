/*
 * Copyright 2015 AppDynamics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appdynamicspilot.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appdynamicspilot.model.User;
import com.appdynamicspilot.service.CartService;
import com.appdynamicspilot.service.UserService;
import com.appdynamicspilot.util.SpringContext;
import com.appdynamicspilot.webserviceclient.SoapUtils;

public class CheckTransactionAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
    }

    private SoapUtils soapUtil;
    private UserService userService;


    public void setSoapUtil(SoapUtils soapUtil) {
        this.soapUtil = soapUtil;
    }

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        userService = (UserService) SpringContext.getBean("userService");
        User user = userService.getMemberByLoginName("ravi");
        CartService cartService = (CartService) SpringContext.getBean("cartService");
        cartService.getAllItemsByUser(user.getId());

        System.out.println("<<<<<<<<<<<<<<PASSWORD FOR RAVI IS >>>>>>>>>>>" + user.getPassword());
        System.out.println("<<<<<<<<<<<<<<INSIDE JMS TRANSACTIONS>>>>>>>>>>>");

        //       MessageProducer messageProducer=(MessageProducer)SpringContext.getBean("messageProducer");
        //       messageProducer.sendTextMessageWithOrderId();
        System.out.println("<<<<<<<<<<<<<<CALLING AXIS STUB>>>>>>>>>>>");
        System.out.println("Making ws2  call ");

        soapUtil = (SoapUtils) SpringContext.getBean("soapUtil");
        Long orderId = soapUtil.raiseJMSPO();
        //StockQuoteServiceSoapBindingStub stub=new StockQuoteServiceSoapBindingStub(new URL(soapUtil.getAxis14Url()),null);

        //System.out.println("Making ws1  call ");

        PrintWriter out = response.getWriter();
        out.println("Request Type : " + request.getMethod());
        out.println("Hello " + request.getParameter("firstName") + " " + request.getParameter("lastName") + ". Roses are red.");
        out.print("Violets are blue.");
        //out.print("Web Services are fetching  " +stub.getQuote("XXX") +" for you");
        System.out.println("<<<<<<<<<<<<<<SENT MESSAGE>>>>>>>>>>>");

        out.print("\nWS Axis2 fetched : " + orderId + " ");
        //   out.print("\nWS Axis1.4 fetched : "+stub.getQuote("XXX"));
        Cookie mycookie = new Cookie("firstCookie", "appdynamics");
        response.addCookie(mycookie);
    }

    public void destroy() {
        System.out.println("destroy");
    }

    public String getServletInfo() {
        return null;
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


}
