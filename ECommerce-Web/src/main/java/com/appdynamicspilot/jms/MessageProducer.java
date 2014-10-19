package com.appdynamicspilot.jms;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.support.JmsGatewaySupport;

/**
 * Service for sending jms messages.
 *
 * @author Vikash
 */
public class MessageProducer extends JmsGatewaySupport {

    protected final static String CMD_MAIL = "SEND_MAIL";
    protected final static String EMAIL_ID = "EMAIL_ID";
    protected final static String ORDER_ID = "ITEM_ID";
    protected final static String COMMAND = "COMMAND";

    private static Logger log = Logger.getLogger(MessageProducer.class.getName());

    /**
     * Send message to for email to user by given orderid
     */
    public void sendMessageWithOrderId(final String orderId, final String emailId) {
        getJmsTemplate().send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                MapMessage msg = session.createMapMessage();
                msg.setString(COMMAND, CMD_MAIL);
                msg.setString(EMAIL_ID, emailId);
                msg.setString(ORDER_ID, orderId);
                msg.setIntProperty("testproperty", 21);
                return msg;
            }
        });
    }

    public void sendTextMessageWithOrderId() {
        getJmsTemplate().send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                Message msg = session.createTextMessage("You Sent a order message");
                msg.setStringProperty("testString", "test123");

			    	
			    	/*MapMessage msg = session.createMapMessage();
				msg.setString(COMMAND, CMD_MAIL);
				msg.setString(EMAIL_ID, emailId);
				msg.setString(ORDER_ID, orderId);
				msg.setIntProperty("testproperty", 21);
*/
                return msg;
            }
        });
    }
}
