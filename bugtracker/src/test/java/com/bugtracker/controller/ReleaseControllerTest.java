package com.bugtracker.controller;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bugtracker.entity.Release;
import com.bugtracker.service.ReleaseService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReleaseControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ReleaseService releaseService;
	
	@Test
	public void getAllRelease() throws Exception {
		
		/* setup mock */
		List<Release> releaseList = Arrays.asList(new Release[]{new Release("Q1 Release Containing High Priority Bugs","2030-02-16")});
		when(releaseService.findAll()).thenReturn(releaseList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/releases")
				.contentType(MediaType.APPLICATION_JSON);
		
		  mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}
	
	@Test
	public void getReleaseByIdTest() throws Exception {

		/* setup mock */
		Release release = new Release("Q1 Release Containing High Priority Bugs","2030-02-16");
		when(releaseService.findById(1L)).thenReturn(release);
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/releases/1")
				.contentType(MediaType.APPLICATION_JSON);
		
	      mockMvc.perform(requestBuilder).andExpect(status().isOk());

	}
	
	@Test
	public void saveReleaseTest() throws Exception{
		
		/* setup mock */
		Release release = new Release("Q1 Release Containing High Priority Bugs","2030-02-16");
		when(releaseService.save(any(Release.class))).thenReturn(release);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/releases")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(new Release("Q1 Release Containing High Priority Bugs","2030-02-16")))
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}
	
	
	public String asJsonString(final Object obj) {

		try {
			final ObjectMapper mapper = new ObjectMapper();

			final String jsonContent = mapper.writeValueAsString(obj);

			System.out.println(jsonContent);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
