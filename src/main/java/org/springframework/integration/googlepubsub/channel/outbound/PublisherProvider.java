package org.springframework.integration.googlepubsub.channel.outbound;

import com.google.cloud.pubsub.spi.v1.Publisher;
import com.google.pubsub.v1.TopicName;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by joaomartins on 5/10/17.
 */
@Configuration
public class PublisherProvider {

  @Bean
  public Publisher newPublisher() throws IOException {
    return Publisher.defaultBuilder(
        TopicName.create("sodium-gateway-790", "test"))
        .build();
  }
}
