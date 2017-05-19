package org.springframework.cloud.gcp.project;

import com.google.common.base.Strings;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by joaomartins on 5/15/17.
 */
@Configuration
@EnableConfigurationProperties(GcpProject.class)
public class GcpProjectAutoConfiguration {

  private static final Logger LOGGER = Logger.getLogger(GcpProjectAutoConfiguration.class.getName());

  private static final String PROJECT_ID_ENV_VAR_NAME = "GCP_PROJECT_ID";

  @Autowired
  private GcpProject gcpProjectFromProperties;

//  @Autowired
//  public GcpProjectAutoConfiguration(GcpProject gcpProjectFromProperties) {
//    this.gcpProjectFromProperties = gcpProjectFromProperties;
//  }

//  private static final String PROJECT_ID_PROPERTY_NAME = "cloud.gcp.project.id";
//
//  @Value("${" + PROJECT_ID_PROPERTY_NAME + "}")
//  private String propertiesProjectId;

  @Bean
  public String gcpProjectId() {
    String projectId = System.getProperty(PROJECT_ID_ENV_VAR_NAME);
    if (!Strings.isNullOrEmpty(projectId)) {
      LOGGER.info("Getting project ID from an environment variable.");
      return projectId;
    }

    if (gcpProjectFromProperties != null) {
      LOGGER.info("Getting project ID from the properties file.");
      return gcpProjectFromProperties.getId();
    }

    // TODO(joaomartins): Try to get project ID in credentials.

    // TODO(joaomartins): Try to get project ID from Metadata server.

    return null;
  }
}
