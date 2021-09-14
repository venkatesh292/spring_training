package com.bugtracker.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bugtracker.entity.Application;
import com.bugtracker.entity.Release;
import com.bugtracker.repository.ReleaseRepository;

public class ReleaseServiceTest {

	@InjectMocks
	private ReleaseService releaseService = new ReleaseServiceImpl();
	
	@Mock
	private ReleaseRepository releaseRepository;
	
	@BeforeEach
	void init() {
		
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void getAllReleases() {
		
		/* setup mock */
		List<Release> releaseList = Arrays.asList(new Release[]{new Release("Q1 Release Containing High Priority Bugs","2030-02-16")});
		when(releaseRepository.findAll()).thenReturn(releaseList);
		
		List<Release> releases = releaseService.findAll();
		
		assertEquals(1,releases.size());
	}
	
	@Test
	public void saveRelease() {
		
		/* setup mock */
		Release release = new Release("Q1 Release Containing High Priority Bugs","2030-02-16");
		when(releaseRepository.save(any(Release.class))).thenReturn(release);
		
		Release saveRelease = releaseService.save(new Release("Q1 Release Containing High Priority Bugs","2030-02-16"));
		
		assertEquals(release,saveRelease);
		
	}
	
	@Test
	public void getReleaseById() {
		
		Release release = new Release("Q1 Release Containing High Priority Bugs","2030-02-16");
		when(releaseRepository.findById(1L)).thenReturn(Optional.of(release));
		
		assertEquals(release,releaseService.findById(1L));
			
	}
}
