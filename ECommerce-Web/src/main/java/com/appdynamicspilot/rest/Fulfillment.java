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

package com.appdynamicspilot.rest;

import com.appdynamicspilot.model.FulfillmentOrder;
import com.appdynamicspilot.oracle.jdbc.OracleQueryExecutor;
import com.appdynamicspilot.sqs.SQSFullfilmentSender;
import com.appdynamicspilot.util.SpringContext;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by aleftik on 11/15/14.
 */
@Path("fulfillment")
public class Fulfillment {

    private String queryType = "join";
    private boolean slowQueryParam;
    private SQSFullfilmentSender sender;
    private static Logger logger = Logger.getLogger(Fulfillment.class.getName());

    Client dbClient = ClientBuilder.newClient();
    WebTarget webTarget = dbClient
            .target("http://rds-dbwrapper:8080/rds-dbwrapper/query/execute");
    Invocation.Builder invocationBuilder = null;


    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void fulfillOrder(FulfillmentOrder order) {
        OracleQueryExecutor oracleItems = (OracleQueryExecutor) SpringContext
                .getBean("oracleQueryExecutor");
        oracleItems.executeOracleQuery();
        sendFulfillmentOrder(order);
    }

    private void sendFulfillmentOrder(FulfillmentOrder order) {

        sender = new SQSFullfilmentSender();
        Random randInteger = new Random();
        int randomizeSlowQuery = randInteger.nextInt(5);

        if (randomizeSlowQuery == 0) {
            this.slowQueryParam = true;
            dbQuery(this.queryType, this.slowQueryParam, "oracle");
        } else {
            this.slowQueryParam = false;
            dbQuery(this.queryType, this.slowQueryParam, "oracle");
        }
        sender.sendOrder(order);
    }

    public void dbQuery(String queryType, boolean slowQueryParam, String dbName) {
        logger.info(queryType + " " + slowQueryParam + " " + dbName);
        WebTarget queryWebTarget = webTarget.path(queryType + "/" + slowQueryParam + "/" + dbName);
        invocationBuilder = queryWebTarget
                .request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        logger.info("the response for the target is: " + response.getStatus());
        logger.info(response.readEntity(String.class));
    }
}
