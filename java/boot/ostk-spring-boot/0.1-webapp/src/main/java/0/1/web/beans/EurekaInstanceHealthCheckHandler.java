package 0.1.web.beans;

import javax.inject.Inject;
import javax.inject.Named;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import 0.1.service.HealthCheckService;

/**
 * Eureka Client HealthCheckHandler
 * 
 */
@Named
public class EurekaInstanceHealthCheckHandler implements HealthCheckHandler {
  private HealthCheckService healthCheckService;
  
  @Inject
  public EurekaInstanceHealthCheckHandler(HealthCheckService healthCheckService) {
    this.healthCheckService = healthCheckService;
  }
  
  @Override
  public InstanceStatus getStatus(InstanceStatus currentStatus) {
   if (InstanceInfo.InstanceStatus.STARTING == currentStatus
        || InstanceInfo.InstanceStatus.OUT_OF_SERVICE == currentStatus) {
     return currentStatus;
   }
  
   return healthCheckService.isHealthy() ? InstanceStatus.UP : InstanceStatus.DOWN;
  }
}
