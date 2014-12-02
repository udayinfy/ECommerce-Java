package com.appdynamicspilot.jms;

import com.appdynamicspilot.model.FulfillmentOrder;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.support.JmsGatewaySupport;

import javax.jms.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by aleftik on 11/14/14.
 */
public class FulfillmentProducer extends JmsGatewaySupport {

    public void sendFulfillment(final FulfillmentOrder order) {
        getJmsTemplate().send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage msg = session.createObjectMessage(order);
                return msg;
            }
        });
    }

}
