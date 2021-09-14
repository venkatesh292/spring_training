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
import com.bugtracker.entity.Release;
import com.bugtracker.exception.RestRequestException;
import com.bugtracker.service.ReleaseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1/releases")
public class ReleaseController {

	private static final Logger logger = LogManager.getLogger(ReleaseController.class.getName());
	@Autowired
	private ReleaseService releaseService;

	@GetMapping
	@ApiOperation(value = "Get all releases", notes = "Provide complete release details", response = List.class)
	public ResponseEntity<List<Release>> findAllReleases() throws RestRequestException {

		List<Release> releases = null;

		logger.info("Inside ReleaseController.findAllReleases");
		
			if (null != (releases = releaseService.findAll())) {

				logger.info(" ReleaseController.findAllReleases Releases: {}", releases);
				return new ResponseEntity<List<Release>>(releases, HttpStatus.OK);
			} else {
				logger.info(" ReleaseController.findAllReleases Releases: {}", releases);
				throw new RestRequestException(HttpStatus.NO_CONTENT,"30004","No content available");
		}
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Find release by Id", notes = "Provide an id to look up specific release deatils", response = Release.class)
	public ResponseEntity<Release> findReleaseById(
			@ApiParam(value = "Id value of the release need to be retrieve", required = true) @PathVariable("id") Long releaseId) throws RestRequestException {

		logger.info("Inside ReleaseController.findReleaseById, Id: {}", releaseId);

		Release release = null;
		
			if (null != (release = releaseService.findById(releaseId))) {
				logger.info("ReleaseController.findReleaseById, release: {}", release);
				return new ResponseEntity<>(release, HttpStatus.OK);
			} else {
				logger.info(" ReleaseController.findReleaseById release: {}", release);
				throw new RestRequestException(HttpStatus.NOT_FOUND,"30006","Release details not found");
			}
	}

	
	@PostMapping
	@ApiOperation(value = "Add Release", notes = "Provide a new release details", response = Release.class)
	public ResponseEntity<Release> submitForm(
			@ApiParam(value = "Required to provide a release details", required = true) @RequestBody Release release) throws RestRequestException {
		Release releaseObj = null;
			logger.info(" Inside ReleaseController.submitForm, release: {} ", release);

			if (null != release && null != (releaseObj = releaseService.save(release))) {

				logger.info("ReleaseController.submitForm  after updating release: {}", releaseObj);
				return new ResponseEntity<Release>(releaseObj, HttpStatus.OK);
			} else {
				logger.info("ReleaseController.submitForm  after updating release: {}", releaseObj);
				throw new RestRequestException(HttpStatus.BAD_REQUEST,"30008","Not able to save the release ");
			}
	}

	@PutMapping
	@ApiOperation(value = "Update Release", notes = " Update the existing release for the provided id ", response = Release.class)
	public ResponseEntity<Release> updateRelease(
			@ApiParam(value = "Release details need to update", required = true) @RequestBody Release release) throws RestRequestException {
		Release releaseObj = null;
	
			logger.info("ReleaseController.updateRelease requested release object to update : {}", release);

			if (null != release && null != release.getId()) {
				
				Release releaseSave = releaseService.save(releaseObj);
				
				if (null != releaseSave) {
					logger.info("ReleaseController.updateRelease saved release Entity : {}", releaseSave);
					return new ResponseEntity<Release>(releaseSave, HttpStatus.OK);
				} else {
					logger.info("ReleaseController.updateRelease saved entity : {}", releaseSave);
					throw new RestRequestException(HttpStatus.BAD_REQUEST,"30009","Not able to update the release");
				}
			} else {
				logger.info("ReleaseController.updateRelease Inavlid update request");
				throw new RestRequestException(HttpStatus.NOT_FOUND,"30010","Inavlid update request");
			}
	}
	/*
	 * @PutMapping
	 * 
	 * @ApiOperation(value = "Save or update Release", notes =
	 * " Save or update the Release Details", response = Release.class) public
	 * ResponseEntity<Release> saveOrUpdateRelease(
	 * 
	 * @ApiParam(value = "Release deatils need to update", required =
	 * true) @RequestBody Release release) { try { logger.
	 * info(" Inside ReleaseController.saveOrUpdateRelease, release to save or update: {} "
	 * , release);
	 * 
	 * Release releaseObj = null;
	 * 
	 * if (null != release && null != (releaseObj = releaseService.save(release))) {
	 * 
	 * logger.info("ReleaseController.saveOrUpdateApp  after updating release: {}",
	 * releaseObj); return new ResponseEntity<Release>(releaseObj, HttpStatus.OK); }
	 * else {
	 * logger.info("ReleaseController.saveOrUpdateApp  after updating release: {}",
	 * releaseObj); throw new RestRequestException("Invalid release object"); } }
	 * catch (RestRequestException ex) { logger.error("Exception: ", ex); return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); } catch (Exception ex) {
	 * logger.error("Exception: ", ex); return new
	 * ResponseEntity<>(HttpStatus.BAD_REQUEST); } }
	 */
}
