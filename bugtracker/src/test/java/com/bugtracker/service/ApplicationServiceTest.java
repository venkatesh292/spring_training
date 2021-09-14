package com.bugtracker.service;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.bugtracker.entity.Application;
import com.bugtracker.repository.ApplicationRepository;


public class ApplicationServiceTest {

	@InjectMocks
	private ApplicationService applicationService = new ApplicationServiceImpl();
	
	@Mock
	private ApplicationRepository applicationRepository;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void getAllApplications() {
		
		/* setup mock */
		List<Application> appList = Arrays.asList(new Application[]{new Application("Trackzilla_04", "Williams", "A bug tracking application")});
		when(applicationRepository.findAll()).thenReturn(appList);
		
		List<Application> applications = applicationService.findAll();
		
		assertEquals(1,applications.size());
	}
	
	@Test
	public void saveApplication() {
		
		/* setup mock */
		Application application = new Application("Trackzilla_04", "Williams", "A bug tracking application");
		when(applicationRepository.save(any(Application.class))).thenReturn(application);
		
		Application applicationSave = applicationService.save(new Application("Trackzilla_04", "Williams", "A bug tracking application"));
		
		assertEquals(application,applicationSave);
		
	}
	
	@Test
	public void getApplicationById() {
		
		Application application = new Application("Trackzilla_04", "Williams", "A bug tracking application");
		when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
		
		assertEquals(application,applicationService.findById(1L));
			
	}
}
