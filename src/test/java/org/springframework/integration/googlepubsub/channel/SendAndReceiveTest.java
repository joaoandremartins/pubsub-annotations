package org.springframework.integration.googlepubsub.channel;

import com.google.api.core.ApiService.Listener;
import com.google.api.core.ApiService.State;
import com.google.cloud.pubsub.spi.v1.AckReplyConsumer;
import com.google.cloud.pubsub.spi.v1.MessageReceiver;
import com.google.cloud.pubsub.spi.v1.Subscriber;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.SubscriptionName;

/**
 * Created by joaomartins on 5/10/17.
 */
public class SendAndReceiveTest {

  public static void main(String[] args) {
    String projectId = "sodium-gateway-790";
    String subscriptionId = "messages";

    SubscriptionName subscriptionName = SubscriptionName.create(projectId, subscriptionId);
// Instantiate an asynchronous message receiver
    MessageReceiver receiver = new MessageReceiver() {
      @Override
      public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
        // handle incoming message, then ack or nack the received message
        // ...
        consumer.ack();
      }
    };

    Subscriber subscriber = null;
    try {
      // Create a subscriber for "my-subscription-id" bound to the message receiver
      subscriber = Subscriber.defaultBuilder(subscriptionName, receiver).build();
      subscriber.addListener(new Listener() {
        @Override
        public void failed(State from, Throwable failure) {
          super.failed(from, failure);
        }

        @Override
        public void running() {
          super.running();
        }

        @Override
        public void starting() {
          super.starting();
        }

        @Override
        public void stopping(State from) {
          super.stopping(from);
        }

        @Override
        public void terminated(State from) {
          super.terminated(from);
        }
      }, MoreExecutors.directExecutor());
      subscriber.startAsync();
      // ...
    } finally {
      // stop receiving messages
      if (subscriber != null) {
//        subscriber.stopAsync();
      }

      while (true) {}
    }
  }
}
