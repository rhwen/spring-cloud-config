package com.redhat.sample.config.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redhat.sample.config.redis.Person;
import com.redhat.sample.config.redis.Person.Gender;
import com.redhat.sample.config.redis.PersonRepo;

@RefreshScope
@RestController
public class TestController {
	@Autowired
	private PersonRepo personRepo;

	@Autowired
	private ApplicationContext context;

	@Value("${redis.host}")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PersonRepo getPersonRepo() {
		return personRepo;
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	public void setPersonRepo(PersonRepo personRepo) {
		this.personRepo = personRepo;
	}

	@RequestMapping("/hello")
	public String hello() {
		return "hello, " + this.name;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save() {
		Person person = new Person();
		person.setId("1");
		person.setAge(55);
		person.setGender(Gender.Female);
		person.setName("RedHatter");
		personRepo.save(person);
		return "ok";
	}

	@RequestMapping(value = "/retrieve", method = RequestMethod.GET)
	public Map<Object, Object> retrieve() {
		Map<Object, Object> personMatrixMap = personRepo.findAll();
		return personMatrixMap;
	}

	@RequestMapping(value = "/reload", method = RequestMethod.POST)
	public String reload() {
		Object obj = this.context.getBean("personRepo");
		return (obj == null) ? "null" : obj.toString();
	}
}
