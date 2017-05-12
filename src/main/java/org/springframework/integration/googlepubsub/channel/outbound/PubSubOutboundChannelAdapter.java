package org.springframework.integration.googlepubsub.channel.outbound;

import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.messaging.Message;

/**
 * Created by joaomartins on 5/8/17.
 */
public class PubSubOutboundChannelAdapter extends AbstractMessageHandler {

  private PubSubMessagingTemplate pubSubMessagingTemplate;

  public PubSubOutboundChannelAdapter(PubSubMessagingTemplate pubSubMessagingTemplate) {
    this.pubSubMessagingTemplate = pubSubMessagingTemplate;
  }

  @Override
  protected void handleMessageInternal(Message<?> message) throws Exception {
    pubSubMessagingTemplate.send(message);
  }
}
