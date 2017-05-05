package org.springframework.integration.googlepubsub.channel;

import org.springframework.integration.channel.AbstractMessageChannel;
import org.springframework.messaging.Message;

/**
 * Created by joaomartins on 5/3/17.
 */
public class PublishSubscribeMessageChannel extends AbstractMessageChannel {

  protected boolean doSend(Message<?> message, long l) {
    return false;
  }
}
