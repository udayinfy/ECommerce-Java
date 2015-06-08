package com.appdynamicspilot.sqs;

import com.amazonaws.services.sqs.*;
import com.amazonaws.services.sqs.model.*;
import com.amazonaws.auth.*;
import java.util.Map;
import javax.xml.bind.*;

import java.io.*;

import com.appdynamicspilot.model.FulfillmentOrder;

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
