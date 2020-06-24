package com.hovertest;


import java.util.Collections;
import java.util.Map;


import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.HoverflyMode;

public class HoverflySimulator implements QuarkusTestResourceLifecycleManager{
	
	Hoverfly hoverfly;
	
	@Override
	public Map<String, String> start() {
		System.out.println("Hoverfly Started");
		
		hoverfly = new Hoverfly(HoverflyMode.SIMULATE);
		return Collections.singletonMap("service",
				"result");
	}

	@Override
	public void stop() {
		hoverfly.close();
		System.out.println("Hoverfly Stopped");
//		if (null != wireMockServer) {
//			wireMockServer.stop();
//		}
	}
}
