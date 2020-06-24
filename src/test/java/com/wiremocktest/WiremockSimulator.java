package com.wiremocktest;

import java.util.Collections;
import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WiremockSimulator implements QuarkusTestResourceLifecycleManager {

	private WireMockServer wireMockServer;

	@Override
	public Map<String, String> start() {
		System.out.println("Wiremock Started");
		wireMockServer = new WireMockServer();
		wireMockServer.start();

		stubFor(get(urlEqualTo("/v2/country/name/greece"))
				.willReturn(aResponse()
						.withHeader("Content-Type", "application/json")
						.withBody("[{\"name\":\"Greece\",\"capital\":\"Athens\"}]")));

//		stubFor(get(urlMatching(".*")).atPriority(10)
//				.willReturn(aResponse().proxiedFrom("https://restcountries.eu/rest")));

		return Collections.singletonMap("org.acme.getting.started.country.CountriesService/mp-rest/url",
				wireMockServer.baseUrl());
	}

	@Override
	public void stop() {
		System.out.println("Wiremock Stopped");
		if (null != wireMockServer) {
			wireMockServer.stop();
		}
	}
}
