package com.bugtracker.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bugtracker.entity.Application;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ApplicationRepositoryTest {

	@Autowired
	private ApplicationRepository applicationRepository;

	@Test
	public void testFindAllApplication() {

		List<Application> appList = Arrays
				.asList(new Application[] { new Application("Trackzilla_04", "Williams", "A bug tracking application"),
						new Application("Trackzilla_05", "Williams1", "A bug tracking application"),
						new Application("Trackzilla_06", "Williams2", "A bug tracking application") });

		List<Application> appSavedList = (List<Application>) applicationRepository.saveAll(appList);
		List<Application> applications = (List<Application>) applicationRepository.findAll();

		assertThat(applications.size()).isEqualTo(appSavedList.size());

	}

	@Test
	public void testFindByApplicationId() {

		Application application = applicationRepository
				.save(new Application("Trackzilla_04", "Williams", "A bug tracking application"));

		Application findApplication = applicationRepository.findById(application.getId()).get();

		assertThat(findApplication).isEqualTo(application);

	}

	@Test
	public void testSaveApplication() {

		Application application = applicationRepository
				.save(new Application("Trackzilla_04", "Williams", "A bug tracking application"));
		Application findapp = applicationRepository.findById(application.getId()).get();

		assertThat(findapp.getName().equals("Trackzilla_04"));
	}

	@Test
	public void testDeleteAppplication() {

		Application application = applicationRepository
				.save(new Application("Trackzilla_04", "Williams", "A bug tracking application"));
		applicationRepository.delete(application);

		assertThat(applicationRepository.count()).isEqualTo(0);
	}

}
