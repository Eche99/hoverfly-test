package com.wiremocktest;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(WiremockSimulator.class)
public class WiremockAppTest {

	//@Test
	void testGreece() {
		given()
		.when()
		.get("/v2/country/name/greece")
		.then()
		.statusCode(200);
		//.body("$.size()", is(1), "[0].name",
		//		is("Greece"), "[0].capital", is("Athens"));
	}
}
