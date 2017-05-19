package org.springframework.cloud.gcp.project;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by joaomartins on 5/18/17.
 */
@ConfigurationProperties("cloud.gcp.project")
public class GcpProject {

  private String id;

  public GcpProject(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
