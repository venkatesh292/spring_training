package com.bugtracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugtracker.entity.Application;
import com.bugtracker.repository.ApplicationRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	@Override
	public List<Application> findAll() {

		return (List<Application>) applicationRepository.findAll();
	}

	public Application findById(Long id) {

		Optional<Application> findById = applicationRepository.findById(id);

		return findById.isPresent() ? findById.get() : null;
	}

	@Override
	public Application save(Application aplication) {

		return applicationRepository.save(aplication);
	}

}
