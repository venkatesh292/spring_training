package com.bugtracker.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bugtracker.entity.Application;
import com.bugtracker.entity.Release;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReleaseRepositoryTest {

	@Autowired
	private ReleaseRepository releaseRepository;

	@Test
	public void testFindAllReleases() {

		List<Release> releaseList = Arrays
				.asList(new Release[] { new Release("Q1 Release Containing High Priority Bugs","2030-02-16"),
						new Release("Q2 Release Containing High Priority Enhancements","2030-05-27") });

		List<Release> releaseSavedList = (List<Release>) releaseRepository.saveAll(releaseList);
		List<Release> releases = (List<Release>) releaseRepository.findAll();

		assertThat(releases.size()).isEqualTo(releaseSavedList.size());

	}

	@Test
	public void testFindReleaseById() {

		Release release = releaseRepository
				.save(new Release("Q1 Release Containing High Priority Bugs","2030-02-16"));

		Release findRelease = releaseRepository.findById(release.getId()).get();

		assertThat(findRelease).isEqualTo(release);

	}

	@Test
	public void testSaveRelease() {

		Release release = releaseRepository
				.save(new Release("Q1 Release Containing High Priority Bugs","2030-02-16"));
		Release findRelease = releaseRepository.findById(release.getId()).get();

		assertThat(findRelease.getId().equals(release.getId()));
	}

	@Test
	public void testDeleteRelease() {

		Release release = releaseRepository
				.save(new Release("Q1 Release Containing High Priority Bugs","2030-02-16"));
		releaseRepository.delete(release);

		assertThat(releaseRepository.count()).isEqualTo(0);
	}
}
