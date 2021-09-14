package com.bugtracker.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bugtracker.entity.Application;
import com.bugtracker.service.ApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class ApplicationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ApplicationService applicationService;

	@Test
	public void getAllApplicationTest() throws Exception {

		/* setup mock */
		List<Application> appList = Arrays.asList(new Application[]{new Application("Trackzilla_04", "Williams", "A bug tracking application")});
		when(applicationService.findAll()).thenReturn(appList);
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void getApplicationByIdTest() throws Exception {

		/* setup mock */
		Application application = new Application("Trackzilla_04", "Williams", "A bug tracking application");
		when(applicationService.findById(1L)).thenReturn(application);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications/1")
				.contentType(MediaType.APPLICATION_JSON);
		
	      mockMvc.perform(requestBuilder).andExpect(status().isOk());
				
	}

	@Test
	public void saveApplicationTest() throws Exception {

		/* setup mock */
		Application application = new Application("Trackzilla_04", "Williams", "A bug tracking application");
		
		when(applicationService.save(any(Application.class))).thenReturn(application);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/applications")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(new Application("Trackzilla_04", "Williams", "A bug tracking application")))
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
