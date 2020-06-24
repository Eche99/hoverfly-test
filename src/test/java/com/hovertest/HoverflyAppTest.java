package com.hovertest;

import static io.restassured.RestAssured.given;
import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.HttpBodyConverter.jsonWithSingleQuotes;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import javax.inject.Inject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import com.wiremocktest.WiremockSimulator;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit5.HoverflyExtension;
import io.specto.hoverfly.junit5.api.HoverflyConfig;
import io.specto.hoverfly.junit5.api.HoverflyCore;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@HoverflyCore(config = @HoverflyConfig(proxyLocalHost = true, destination = "0.0.0.0:8890/rest/v1"))
@ExtendWith(HoverflyExtension.class)
public class HoverflyAppTest {

	@BeforeEach
	public void setup(Hoverfly hoverfly) {
		System.out.println("Setup  Hoverfly");
		setupHoverfly(hoverfly);
	}

	//@Test
	@Order(1)
	public void getLeveranserTest() {
		System.out.println("Start test !");

		String release = "2018040000";
		assertEquals(127, this.getLeveranser("2018040000"));
		assertEquals(49, this.getLeveranser("2018050000"));
		assertEquals(0, this.getLeveranser("2017117831"));
	}

	private static void setupHoverfly(Hoverfly hoverfly) {
		System.out.println("Start Hoverfly  !");

		hoverfly.simulate(dsl(service("0.0.0.0:8890").get("/rest/v1/release")
				.willReturn((success().body(jsonWithSingleQuotes(
						"{" + "\"2018040000\": 127," + "\"2018050000\": 49," + "\"2017117831\": 0" + "}"))))));
	}
	   private int getLeveranser(String release)
	   {
	      LinkedHashMap<String, Integer> lista = given()
	            .when()
	            .get("/rest/v1/release")
	            .then()
	            .statusCode(200)
	            .extract()
	            .body()
	            .jsonPath()
	            .get(".");
	      System.out.println("---------------------------------");
	      System.out.println("Antal leveranser för:  " + release + " " + lista.get(release));

	      return lista.get(release);
	   }
}
