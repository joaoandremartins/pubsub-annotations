package org.springframework.integration.googlepubsub.channel.outbound;

import com.google.cloud.pubsub.spi.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.core.AbstractMessageSendingTemplate;

/**
 * Created by joaomartins on 5/12/17.
 */
public class PubSubMessagingTemplate<D extends MessageChannel>
    extends AbstractMessageSendingTemplate<D> {

  private Publisher publisher;

  public PubSubMessagingTemplate() {
  }

  public PubSubMessagingTemplate(Publisher publisher) {
    this.publisher = publisher;
  }

  @Override
  protected void doSend(D d, Message<?> message) {
    // TODO(joaomartins): Need to convert the message into pubsubmessage.
    PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(
        ByteString.copyFromUtf8("oix")).build();
    publisher.publish(pubsubMessage);
  }
}
