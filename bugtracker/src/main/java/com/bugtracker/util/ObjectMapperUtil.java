package com.bugtracker.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperUtil {

@Autowired
@Qualifier("mapper")
private ModelMapper modelMapper;

@PostConstruct
public void init() {
	modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
}
public <D,T>  D map(final T entity, Class<D> outClass) {
	
	return modelMapper.map(entity,outClass);
}

public <D,T> List<D> mapAll(final Collection<T> entityList, Class<D> outClass){

	return entityList.stream().map(entity->map(entity,outClass)).collect(Collectors.toList());	
  }
}
