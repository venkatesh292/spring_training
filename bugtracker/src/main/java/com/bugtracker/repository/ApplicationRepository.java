package com.bugtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bugtracker.entity.Application;

public interface ApplicationRepository extends CrudRepository<Application,Long> {
	
	
//	@Query(value = "select a from Application a where name = :name")
//	List<Application> findByAppName(@Param("name") String name);
	
}
