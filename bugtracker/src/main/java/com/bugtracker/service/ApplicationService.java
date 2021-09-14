package com.bugtracker.service;

import java.util.List;
import java.util.Optional;

import com.bugtracker.entity.Application;

public interface ApplicationService {

	List<Application> findAll();
	
	Application findById(Long id);
	
	Application save(Application application);
}
