package com.bugtracker.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ApplicationActuatorIndicator implements HealthIndicator {

	private String message_key ="bugtracker";
	
	@Override
	public Health health() {
		
		if(!isRunningApplication()) {
			return Health.down().withDetail(message_key,"Not available").build();
		}
		return Health.up().withDetail(message_key,"Available").build();
	}
	
	public boolean isRunningApplication() {
		boolean isRunning = false;
		
		return isRunning;
	}

}
