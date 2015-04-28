package com.appdynamicspilot.sqs;
import com.amazonaws.services.sqs.*;
import com.amazonaws.services.sqs.model.*;
import com.amazonaws.auth.*;
import com.appdynamicspilot.jms.FulfillmentConsumer;
import com.appdynamicspilot.rest.Fulfillment;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by aleftik on 4/28/15.
 */
public class FullfillmentClient extends AbstractSQSClient {
    Logger logger = Logger.getLogger(FullfillmentClient.class);


    public FullfillmentClient()  {
        super();
    }

    private void recieveMessages() {
        CreateQueueResult  queue = super.getQueue();
        ReceiveMessageResult result = client.receiveMessage(new ReceiveMessageRequest(queue.getQueueUrl()));
        List <Message> messages  = result.getMessages();
        for(Message message:messages) {
            client.deleteMessage(new DeleteMessageRequest(getQueue().getQueueUrl(), message.getReceiptHandle()));
            logger.info("Message Deleted:" + message.getReceiptHandle());
        }

    }

    public static void main(String [] args) {
        FullfillmentClient client = new FullfillmentClient();
        client.recieveMessages();
    }
}
