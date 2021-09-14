package com.bugtracker.service;

import java.util.List;

import com.bugtracker.entity.Release;

public interface ReleaseService {

	List<Release> findAll();
    Release findById(Long id);
    Release save(Release release);
}
