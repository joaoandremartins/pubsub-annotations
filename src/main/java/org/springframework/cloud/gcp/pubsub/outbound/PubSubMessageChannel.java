package org.springframework.cloud.gcp.pubsub.outbound;

import org.springframework.integration.channel.AbstractSubscribableChannel;
import org.springframework.integration.dispatcher.BroadcastingDispatcher;
import org.springframework.integration.dispatcher.MessageDispatcher;

/**
 * Created by joaomartins on 5/16/17.
 */
public class PubSubMessageChannel extends AbstractSubscribableChannel {

  @Override
  protected MessageDispatcher getDispatcher() {
    return new BroadcastingDispatcher();
  }
}
