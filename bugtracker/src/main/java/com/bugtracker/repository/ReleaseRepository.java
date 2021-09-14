package com.bugtracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.bugtracker.entity.Release;

public interface ReleaseRepository extends CrudRepository<Release,Long> {

}
