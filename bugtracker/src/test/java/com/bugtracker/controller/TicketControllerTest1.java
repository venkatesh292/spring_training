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
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BugtrackerApplication.class)
@SpringBootTest
public class TicketControllerTest1 {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	public void init() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getAllTckets() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tickets").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", Matchers.hasSize(4))).andDo(print());
	}

	@Test
	public void getTicketById() throws Exception {

		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tickets/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.title").value("Sort Feature"))
				.andDo(print());
	}

	@Test
	public void saveTicket() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tickets").accept(MediaType.APPLICATION_JSON)
				.content("{\"title\":\"Sort Feature\",\"description\":\"Add the ability to sort tickets by severity\","
						+ "\"application\":{\"id\":2,\"name\":\"Trackzilla\",\"description\":\"A bug tracking application\",\"owner\":\"Williams\"},"
						+ "\"release\":{\"id\":3,\"releaseDate\":\"2030-09-14\",\"description\":\"Q3 Release Containing Bugs\"},\"status\":\"OPEN\"}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void updateTicket() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/tickets").accept(MediaType.APPLICATION_JSON).content(
				"{\"id\":4,\"title\":\"Sort Feature\",\"description\":\"Add the ability to sort tickets by severity\",\"application\":{\"id\":1,\"name\":\"Trackzilla\","
				+ "\"description\":\"A bug tracking application\",\"owner\":\"Williams\"},\"release\":{\"id\":3,\"releaseDate\":\"2030-09-14\",\"description\":\"Q3 Release Containing Bugs\"},"
				+ "\"status\":\"OPEN\"}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void DeleteTicket() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/tickets?id=5").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
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
