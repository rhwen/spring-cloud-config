package com.redhat.sample.config.redis;

import java.util.Map;

public interface PersonRepo {
	
	public void save(Person person);
	
	public Person find(String id);
	
	public Map<Object , Object> findAll();
	
	public void delete(String id);
}
