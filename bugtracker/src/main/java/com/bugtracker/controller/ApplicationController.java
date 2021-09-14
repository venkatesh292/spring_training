package com.bugtracker.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugtracker.entity.Application;
import com.bugtracker.exception.RestRequestException;
import com.bugtracker.model.ApplicationDto;
import com.bugtracker.service.ApplicationService;
import com.bugtracker.util.ObjectMapperUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {

	private static final Logger logger = LogManager.getLogger(ApplicationController.class.getName());

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private ObjectMapperUtil objectMapperUtil;
	
	@GetMapping
	@ApiOperation(value = "Get all applications", notes = "Provide complete list of application deatils", response = List.class)
	public ResponseEntity<List<Application>> getApplications() throws RestRequestException {

		List<Application> applications = null;

		logger.info("Inside ApplicationController.getApplications");
		
			if (null != (applications = applicationService.findAll())) {
				       
				logger.info(" ApplicationController.getApplications Aapplications: {}",applications);
				
				return new ResponseEntity<List<Application>>(applications, HttpStatus.OK);
			} else {
				logger.info(" ApplicationController.getApplications Aapplications: {}");
				throw new RestRequestException(HttpStatus.NO_CONTENT,"30004","No content available");
			}
	
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Find application by id", notes = "Provide an id to look up specific application deatils", response = Application.class)
	public ResponseEntity<Application> getApplicationById(
			@ApiParam(value = "Id value of the application you need to retrieve", required = true) @PathVariable("id") Long appId) throws RestRequestException {

		logger.info("Inside ApplicationController.getApplicationById, Id: {}", appId);

		Application application = null;
		
			if (null != (application = applicationService.findById(appId))) {
				logger.info("ApplicationController.getApplicationById, application: {}", application);
				return new ResponseEntity<>(application, HttpStatus.OK);
			} else {
				logger.info(" ApplicationController.getApplications Aapplications: {}");
				throw new RestRequestException(HttpStatus.NOT_FOUND,"30006","Application details not found");
			}

	}

	@PostMapping
	@ApiOperation(value = "Add Application", notes = "Insert an application", response = Application.class)
	public ResponseEntity<Application> submitForm(
			@ApiParam(value = "Required to provide an application details", required = true) @RequestBody Application application) throws RestRequestException {
		Application applicationObj = null;
			logger.info(" Inside ApplicationController.submitForm, application: {} ", application);

			if (null != application && null != (applicationObj = applicationService.save(application))) {
		
				logger.info("ApplicationController.submitForm  after adding the  application: {}", applicationObj);
				
				return new ResponseEntity<Application>(applicationObj, HttpStatus.OK);
			} else {
				logger.info("ApplicationController.submitForm while adding application: {}");
				throw new RestRequestException(HttpStatus.BAD_REQUEST,"30008","Not able to save the application");
			}
			
	}

	@PutMapping
	@ApiOperation(value = "Update App", notes = " Update the existing application for the provided id ", response = Application.class)
	public ResponseEntity<Application> updateApp(
			@ApiParam(value = "application details need to update", required = true) @RequestBody Application application) throws RestRequestException {
		Application applicationObj = null;

			logger.info("ApplicationController.updateApp requested application object to update : {}", application);

			if (null != application && null != application.getId()) {
				
				Application applicationSave = applicationService.save(application);
				
				if (null != applicationSave) {
					logger.info("ApplicationController.updateApp saved Application Entity : {}", applicationSave);
					return new ResponseEntity<Application>(applicationSave, HttpStatus.OK);
				} else {
					logger.info("ApplicationController.updateApp saved entity : {}");
					throw new RestRequestException(HttpStatus.BAD_REQUEST,"30008","Not able to update the application");
				}
			} else {
				logger.info("ApplicationController.updateApp Inavlid update request");
				throw new RestRequestException(HttpStatus.NOT_FOUND,"30010","Inavlid update request");
			}
		}
	/*
	 * @PutMapping
	 * 
	 * @ApiOperation(value = "Save or update Application", notes =
	 * " Save or update the application", response = Application.class) public
	 * ResponseEntity<Application> saveOrUpdateApp(
	 * 
	 * @ApiParam(value = "Application deatils need to update", required =
	 * true) @RequestBody Application application) { try { logger.
	 * info(" Inside ApplicationController.saveOrUpdateApp, application to save or update: {} "
	 * , application);
	 * 
	 * Application applicationObj = null;
	 * 
	 * if (null != application && null != (applicationObj =
	 * applicationService.save(application))) {
	 * 
	 * logger.
	 * info("ApplicationController.saveOrUpdateApp  after updating application: {}",
	 * applicationObj); return new ResponseEntity<Application>(applicationObj,
	 * HttpStatus.OK); } else { logger.
	 * info("ApplicationController.saveOrUpdateApp  after updating application: {}",
	 * applicationObj); throw new
	 * RestRequestException("Invalid application object"); } } catch
	 * (RestRequestException ex) { logger.error("Exception: ", ex); return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); } catch (Exception ex) {
	 * logger.error("Exception: ", ex); return new
	 * ResponseEntity<>(HttpStatus.BAD_REQUEST); } }
	 */
}
