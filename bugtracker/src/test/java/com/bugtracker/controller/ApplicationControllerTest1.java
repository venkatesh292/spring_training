package com.bugtracker.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bugtracker.BugtrackerApplication;
import com.bugtracker.entity.Application;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BugtrackerApplication.class)
@SpringBootTest
public class ApplicationControllerTest1 {

private MockMvc mockMvc;

@Autowired
private WebApplicationContext webApplicationContext;

@BeforeEach
public void setup() {

	this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
}

@Test
public void getAllApplications() throws Exception {
	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications")
			.contentType(MediaType.APPLICATION_JSON);
	mockMvc.perform(requestBuilder).andExpect(jsonPath("$",Matchers.hasSize(14))).andDo(print());
}

@Test
public void getApplicationById() throws Exception {
	
	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications/1")
			.accept(MediaType.APPLICATION_JSON);
	
	mockMvc.perform(requestBuilder).andExpect(jsonPath("id").exists())
	.andExpect(jsonPath("$.name").exists())
	.andExpect(jsonPath("$.owner").value("Williams_056"))
	.andDo(print());
}


@Test
public void shaveApplication() throws Exception {
	
	RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/applications")
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(new Application("Trackzilla 9.0", "Williams", "A bug tracking application")))
			.accept(MediaType.APPLICATION_JSON);
	
	mockMvc.perform(requestBuilder).andExpect(status().isOk());
	
}

@Test
public void UpdateApplication() throws Exception {
	
	RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/applications")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"id\":1,\"name\":\"Trackzilla 10.0\",\"description\":\"A bug tracking application\",\"owner\":\"Williams\"}")
			.accept(MediaType.APPLICATION_JSON);
	
	mockMvc.perform(requestBuilder).andExpect(status().isOk());
	
}

public String asJsonString(final Object obj) {

	try {
		final ObjectMapper mapper = new ObjectMapper();

		final String jsonContent = mapper.writeValueAsString(obj);

		return jsonContent;
	} catch (Exception e) {
		throw new RuntimeException();
	}
}
	
}
