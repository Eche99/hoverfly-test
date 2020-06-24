package com.hovertest;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(HoverflySimulator.class)
public class HoverflySimulatorTest {

	@Test
	@Order(1)
	public void getLeveranserTest() {
		System.out.println("Start test !");
	}
}
