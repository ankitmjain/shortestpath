package com.dhs.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestApiCall {

	@Value("${rest.api.url}")
	private String apiUrl;

	public ResponseEntity<String> getEndPointResponse(String service,HttpMethod method,String params) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<String> entity = new HttpEntity<>(params, headers);
		ResponseEntity<String> responseRequest = restTemplate.exchange(apiUrl + service,method,entity,
				String.class);
		return responseRequest;
	}
}
