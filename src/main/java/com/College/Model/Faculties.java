package com.College.Model;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "departmentsGroup", "firstName", "lastName", "id", "email", "salary", "phoneNumber" })
@Generated("jsonschema2pojo")

@JsonIgnoreProperties(ignoreUnknown = true)
public class Faculties {

	@JsonProperty("departmentsGroup")
	private String departmentsGroup;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("email")
	private String email;
	@JsonProperty("salary")
	private Integer salary;
	@JsonProperty("phoneNumber")
	private PhoneNumber phoneNumber;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("departmentsGroup")
	public String getDepartmentsGroup() {
		return departmentsGroup;
	}

	@JsonProperty("departmentsGroup")
	public void setDepartmentsGroup(String departmentsGroup) {
		this.departmentsGroup = departmentsGroup;
	}

	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("salary")
	public Integer getSalary() {
		return salary;
	}

	@JsonProperty("salary")
	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@JsonProperty("phoneNumber")
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	@JsonProperty("phoneNumber")
	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}



}