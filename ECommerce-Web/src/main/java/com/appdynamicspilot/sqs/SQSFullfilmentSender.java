package com.appdynamicspilot.sqs;

import com.amazonaws.services.sqs.*;
import com.amazonaws.services.sqs.model.*;
import com.amazonaws.auth.*;

import javax.xml.bind.*;

import java.io.*;

import com.appdynamicspilot.model.FulfillmentOrder;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by aleftik on 4/15/15.
 */
public class SQSFullfilmentSender {
    private static Logger logger = Logger.getLogger(SQSFullfilmentSender.class.getName());
    private static String AWS_ACCESS_KEY = "AWS_ACCESS_KEY";
    private static String AWS_SECRET_KEY = "AWS_SECRET_KEY";
    private  BasicAWSCredentials creds = null;

    public SQSFullfilmentSender()  {
        Map<String,String> env = System.getenv();
        String awsAccessKey = env.get(AWS_ACCESS_KEY);
        String awsSecretKey = env.get(AWS_SECRET_KEY);
        if ((awsSecretKey != null) && (awsAccessKey != null))  {
            creds = new BasicAWSCredentials(awsAccessKey,awsSecretKey);
        }
    }

    public void sendOrder(FulfillmentOrder order) {
        if (creds != null) {
            AmazonSQSClient aws = new AmazonSQSClient(creds);
            CreateQueueResult queueResult = aws.createQueue("FulfillmentQueue");
            aws.sendMessage(queueResult.getQueueUrl(), toString(order));
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
