package com.bugtracker.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bugtracker.BugtrackerApplication;
import com.bugtracker.entity.Release;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BugtrackerApplication.class)
@SpringBootTest
public class ReleaseControllerTest1 {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	public void init() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getAllRelease() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/releases").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", Matchers.hasSize(16))).andDo(print());
	}

	@Test
	public void getReleaseById() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/releases/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("id").exists()).andExpect(jsonPath("$.releaseDate").value("2030-02-16"))
				.andDo(print());
	}

	@Test
	public void saveRelease() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/releases").accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(new Release("Q2 Release Containing High Priority Bugs", "2031-02-18")))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}
	
	@Test
	public void updateRelease() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/releases").accept(MediaType.APPLICATION_JSON)
				.content("{\"id\":1,\"releaseDate\":\"2032-02-16\",\"description\":\"Q1 Release Containing High Priority Bugs\"}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

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
