package org.springframework.integration.googlepubsub.channel;

import java.util.List;
import java.util.Map;
import javax.lang.model.element.Element;

/**
 * Created by joaomartins on 5/4/17.
 */
//@RestController
public class TopicASubscriberActivator {

  private List<Element> topicSubscribers;

  public TopicASubscriberActivator(List<Element> topicSubscribers) {
    this.topicSubscribers = topicSubscribers;
  }

  public void receive(String pubsubMessage) {
    Subscriber.class.getMethods()
  }
}
