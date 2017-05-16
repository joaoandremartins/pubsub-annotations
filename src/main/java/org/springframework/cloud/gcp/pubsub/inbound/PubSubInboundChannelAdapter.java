package org.springframework.cloud.gcp.pubsub.inbound;

import com.google.cloud.pubsub.spi.v1.AckReplyConsumer;
import com.google.cloud.pubsub.spi.v1.Subscriber;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.SubscriptionName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.messaging.support.GenericMessage;

/**
 * Created by joaomartins on 5/10/17.
 */
public class PubSubInboundChannelAdapter extends MessageProducerSupport {

  private String subscriptionName;
  @Value("${cloud.gcp.project.id}")
  private String projectId;
  private Subscriber subscriber;

  @Override
  protected void onInit() {
    super.onInit();

    subscriber = Subscriber.defaultBuilder(
        SubscriptionName.create(projectId, this.subscriptionName), this::receiveMessage).build();
    subscriber.startAsync();
  }

  private void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
    sendMessage(new GenericMessage<>(message.getData()));
    consumer.ack();
  }

  @Override
  protected void doStop() {
    if (subscriber != null) {
      subscriber.stopAsync();
    }
    super.doStop();
  }

  public String getSubscriptionName() {
    return subscriptionName;
  }

  public void setSubscriptionName(String subscriptionName) {
    this.subscriptionName = subscriptionName;
  }

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }
}
