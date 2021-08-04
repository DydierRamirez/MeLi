package com.meli.ipvalidator.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class ApplicationEndpointTest {
   
	@Autowired
	private MockMvc mockMvc;

	protected void assertResponse(
			String endPoint,
			Integer expectedStatusCode,
			String expectedResponse) throws Exception {
		ResultMatcher response = expectedResponse.isEmpty()
				? content().string("")
				: content().json(expectedResponse);
		mockMvc.perform(get(endPoint))
		       .andExpect(status().is(expectedStatusCode))
		       .andExpect(response);
	}
	
	protected void assertResponse(
			String method,
			String endPoint,
			Integer expectedStatusCode
			) throws Exception {
	
		mockMvc.perform(request(HttpMethod.valueOf(method),endPoint))
		       .andExpect(status().is(expectedStatusCode));
	}
	
	protected void assertResponse(
			String method,
			String endPoint,
			Integer expectedStatusCode,
			String expectedResponse,
			HttpHeaders headers) throws Exception {
		ResultMatcher response = expectedResponse.isEmpty()
				? content().string("")
				: content().json(expectedResponse);
		mockMvc.perform(request(HttpMethod.valueOf(method),endPoint)
				.headers(headers))
		       .andExpect(status().is(expectedStatusCode))
		       .andExpect(response);
	}
	
	
	protected void assertResponse(
			String method,
			String endPoint,
			String body,
			Integer expectedStatusCode,
			String expectedResponse) throws Exception {
		ResultMatcher response = expectedResponse.isEmpty()
				? content().string("")
				: content().json(expectedResponse);
		mockMvc.perform(request(HttpMethod.valueOf(method),endPoint)
				.content(body)
				.contentType(MediaType.APPLICATION_JSON))
		       .andExpect(status().is(expectedStatusCode))
		       .andExpect(response);
	}
	
	protected void assertResponse(
			String method,
			String endPoint,
			String body,
			Integer expectedStatusCode,
			String expectedResponse,
			HttpHeaders headers) throws Exception {
		ResultMatcher response = expectedResponse.isEmpty()
				? content().string("")
				: content().json(expectedResponse);
		mockMvc.perform(request(HttpMethod.valueOf(method),endPoint)
				.content(body)
				.contentType(MediaType.APPLICATION_JSON).headers(headers))
		       .andExpect(status().is(expectedStatusCode))
		       .andExpect(response);
	}
	
	
	protected void assertResponse(
			String method,
			String endPoint,
			String body,
			Integer expectedStatusCode) throws Exception {
		mockMvc.perform(request(HttpMethod.valueOf(method),endPoint)
				.content(body)
				.contentType(MediaType.APPLICATION_JSON))
		       .andExpect(status().is(expectedStatusCode));
	}
	
	protected MvcResult assertResponseResult(
			String method,
			String endPoint,
			String body,
			Integer expectedStatusCode) throws Exception {
		return mockMvc.perform(request(HttpMethod.valueOf(method),endPoint)
				.content(body)
				.contentType(MediaType.APPLICATION_JSON))
		       .andExpect(status().is(expectedStatusCode))
		       .andReturn();
	}
	
	protected MvcResult assertResponseResult(
			String method,
			String endPoint,
			Integer expectedStatusCode) throws Exception {
		return mockMvc.perform(request(HttpMethod.valueOf(method),endPoint)
				.contentType(MediaType.APPLICATION_JSON))
		       .andExpect(status().is(expectedStatusCode))
		       .andReturn();
	}
	
	
	
	
	
	
	
}
