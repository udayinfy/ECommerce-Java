package com.appdynamicspilot.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import java.net.URL;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * Created by aleftik on 12/2/14.
 */
public class FulfillmentConsumer implements MessageListener {
    private static final Logger logger = Logger.getLogger(FulfillmentConsumer.class.getName());
    private String restUrl = null;

    public void onMessage(Message message) {
        try {
            URL url = new URL(getRestUrl());
            InputStream in = url.openStream();
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = in.read(buf)) != -1) {}
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
}
