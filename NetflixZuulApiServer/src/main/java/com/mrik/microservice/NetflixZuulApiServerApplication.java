package com.mrik.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import brave.sampler.Sampler;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class NetflixZuulApiServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixZuulApiServerApplication.class, args);
	}
	
	public Sampler defaultSapler() {
		return Sampler.ALWAYS_SAMPLE;
	}

}

