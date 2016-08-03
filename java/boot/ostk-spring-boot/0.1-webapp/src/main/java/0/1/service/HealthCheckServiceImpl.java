package 0.1.service;

import javax.inject.Named;

@Named
public class HealthCheckServiceImpl implements HealthCheckService {
  @Override
  public boolean isHealthy() {
	//TODO Set up a meaningful health check here
    return true;
  }
}
