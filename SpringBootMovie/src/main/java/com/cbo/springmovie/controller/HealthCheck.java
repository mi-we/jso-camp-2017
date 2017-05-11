package com.cbo.springmovie.controller;

import org.springframework.boot.actuate.health.DiskSpaceHealthIndicator;
import org.springframework.boot.actuate.health.DiskSpaceHealthIndicatorProperties;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by cbo on 11.05.17.
 */
@Component
public class HealthCheck implements HealthIndicator {


  @Override
  public Health health() {

    DiskSpaceHealthIndicatorProperties prop = new DiskSpaceHealthIndicatorProperties();
    prop.setPath(new File("/Users/cbo"));
    prop.setThreshold(9999999999999999L);
    DiskSpaceHealthIndicator diskSpaceHealthIndicator = new DiskSpaceHealthIndicator(prop);
    Health health = diskSpaceHealthIndicator.health();

    return health;


  }
}
