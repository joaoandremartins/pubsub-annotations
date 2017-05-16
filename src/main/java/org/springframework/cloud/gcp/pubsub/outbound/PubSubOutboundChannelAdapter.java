package org.springframework.cloud.gcp.pubsub.outbound;

import com.google.cloud.pubsub.spi.v1.Publisher;
import com.google.pubsub.v1.PubsubMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

/**
 * Created by joaomartins on 5/8/17.
 */
public class PubSubOutboundChannelAdapter extends AbstractMessageHandler {

  private Publisher publisher;

  public PubSubOutboundChannelAdapter(Publisher publisher) {
    this.publisher = publisher;
  }

  @Override
  protected void handleMessageInternal(Message<?> message) throws Exception {
    if (!(message.getPayload() instanceof PubsubMessage)) {
      throw new IllegalArgumentException("This channel adapter cannot send messages other than"
          + "PubsubMessages.");
    }
    publisher.publish((PubsubMessage) message.getPayload());
  }
}
