package org.springframework.integration.googlepubsub.channel.inbound;

import com.google.cloud.pubsub.spi.v1.AckReplyConsumer;
import com.google.cloud.pubsub.spi.v1.Subscriber;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.SubscriptionName;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.messaging.support.GenericMessage;

/**
 * Created by joaomartins on 5/10/17.
 */
public class PubSubInboundChannelAdapter extends MessageProducerSupport {

  @Override
  protected void onInit() {
    super.onInit();

    SubscriptionName subscriptionName =
        SubscriptionName.create("sodium-gateway-790", "messages");

    Subscriber subscriber = null;
    try {
      subscriber = Subscriber.defaultBuilder(subscriptionName, this::receiveMessage).build();
      subscriber.startAsync();
    } finally {
      if (subscriber != null) {
//        subscriber.stopAsync();
      }
    }
  }

  private void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
    sendMessage(new GenericMessage<>(message.getData()));
    consumer.ack();
  }
}
