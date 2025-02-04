package com.umax.client.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LsotoLoadBalancerConfiguration {
	
	
	private static final Logger log = LoggerFactory.getLogger(LsotoLoadBalancerConfiguration.class);

	@Bean
	public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(ConfigurableApplicationContext context) {
		log.info("Configuring Load balancer to prefer same instance");
		return ServiceInstanceListSupplier
				.builder()
				.withBlockingDiscoveryClient()
				.withSameInstancePreference()
				.build(context);
	}

}
