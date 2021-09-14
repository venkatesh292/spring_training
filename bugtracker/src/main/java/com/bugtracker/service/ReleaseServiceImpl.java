package com.bugtracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bugtracker.entity.Release;
import com.bugtracker.repository.ReleaseRepository;

@Service
public class ReleaseServiceImpl implements ReleaseService {

	@Autowired
	private ReleaseRepository releaseRepository;
	
	@Override
	public List<Release> findAll() {
		
		return (List<Release>) releaseRepository.findAll() ;
	}

	@Override
	public Release findById(Long id) {
		
		 Optional<Release> findById = releaseRepository.findById(id);
		 
		   return findById.isPresent()? findById.get():null;
	}

	@Override
	public Release save(Release release) {
		
		return releaseRepository.save(release);
	}

}
