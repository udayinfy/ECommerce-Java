package com.appdynamicspilot.jms;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.support.JmsGatewaySupport;

import com.appdynamicspilot.model.User;

public class CustomerMessageProducer extends JmsGatewaySupport {
	
	public void sendCustomerMesssage(final User user){
		getJmsTemplate().send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage msg = session.createTextMessage();
				StringBuilder builder = new StringBuilder();
				builder.append("user.id=");
				builder.append('\n');
			    builder.append(user.getId());
			    builder.append("user.email=");			    
				builder.append(user.getEmail());
				builder.append('\n');
				builder.append("user.password");
				builder.append(user.getPassword());
				msg.setText(builder.toString());
				return msg;
			}
		});
	}
}
