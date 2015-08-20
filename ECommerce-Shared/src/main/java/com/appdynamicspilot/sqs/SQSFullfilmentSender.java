package com.appdynamicspilot.sqs;

import com.amazonaws.services.sqs.*;
import com.amazonaws.services.sqs.model.*;
import com.amazonaws.auth.*;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.bind.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.*;

import com.appdynamicspilot.model.FulfillmentOrder;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by aleftik on 4/15/15.
 */
public class SQSFullfilmentSender extends AbstractSQSClient {

    private static Logger logger = Logger.getLogger(SQSFullfilmentSender.class.getName());


    public SQSFullfilmentSender()  {
        super();
    }

    public void sendOrder(FulfillmentOrder order) {
        if (creds != null) {
            client.sendMessage(getQueue().getQueueUrl(), toString(order));
        }
    }

    public String toString(FulfillmentOrder order) {
        StringWriter writer = new StringWriter();

        try {
            JAXBContext context = JAXBContext.newInstance(FulfillmentOrder.class);
            Marshaller m = context.createMarshaller();
            m.marshal(order, writer);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe(ex.getMessage());
        }

        return writer.toString();

    }

}
