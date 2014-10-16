package com.appdynamicspilot.jms;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;

import com.appdynamicspilot.service.MailService;
import com.appdynamicspilot.util.QueryExecutor;

/**
 * Service for receiving jms messages.
 *
 * @author Vikash
 */
public class MessageConsumer implements MessageListener {

    private MailService mailService;

    private static Logger log = Logger.getLogger(MessageConsumer.class.getName());

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }


    public void onMessage(Message message) {
        //	log.info("**************** Request time(ms) in MessageConsumer :: " + System.currentTimeMillis());
        if (message instanceof MapMessage) {
            try {
                MapMessage mapMessage = (MapMessage) message;
                String command = mapMessage.getString("COMMAND");
                QueryExecutor qe=new QueryExecutor();
                qe.executeQuery(10l,mapMessage.getString("ORDER_ID"),mapMessage.getString("EMAIL_ID")+" user has placed order request");
                log.info("received message=" + command);

                if ("CMD_MAIL".equals(command)) {
                    mailService.sendOrderMail(mapMessage.getString("EMAIL_ID"),mapMessage.getString("ORDER_ID"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("error on message", e);
            }
        }
    }
}
