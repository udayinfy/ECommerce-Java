package com.appdynamicspilot.jms;

import com.appdynamicspilot.model.FulfillmentOrder;

import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.StringWriter;
import java.net.URL;
import java.io.InputStream;
import java.util.logging.Logger;
import javax.jms.ObjectMessage;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 * Created by aleftik on 12/2/14.
 */
public class FulfillmentConsumer implements MessageListener {
    private static final Logger logger = Logger.getLogger(FulfillmentConsumer.class.getName());
    private String restUrl = null;

    public void onMessage(Message message) {
        try {
            ObjectMessage msg = (ObjectMessage)message;
            FulfillmentOrder order = (FulfillmentOrder) msg.getObject();
            postToRest(order);
        } catch (Exception e) {
            logger.severe(e.toString());
        }

    }

    public void setRestUrl(String restUrl) {
        this.restUrl = restUrl;
    }

    public String getRestUrl () {
        return this.restUrl;
    }


    public void postToRest(FulfillmentOrder order) {
        WebTarget target = ClientBuilder.newClient().target(getRestUrl());
        final String mediaType = MediaType.APPLICATION_XML;
        final Entity<FulfillmentOrder> entity = Entity.entity(order, mediaType);
        Response response = target.request().post(entity, Response.class);
    }


    private String orderToString(FulfillmentOrder order) {
        StringWriter writer = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FulfillmentOrder.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(order, writer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return writer.toString();
    }
}
