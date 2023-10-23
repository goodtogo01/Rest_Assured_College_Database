package com.College.Tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.College.Constants.EndPoints;
import com.College.Helper.FacultiesServiceHelper;

import com.College.Model.Faculties;

public class FacultiesCrudOps {

	private FacultiesServiceHelper facultiesServiceHelper;

	@BeforeClass
	public void initialization() {
		facultiesServiceHelper = new FacultiesServiceHelper();
	}

	@Test
	public void getFaculties() {
		List<Faculties> facultiesList = facultiesServiceHelper.getAll(EndPoints.GET_ALL_FACULTIES);
		assertNotNull(facultiesList, "List is not empty");
		assertFalse(facultiesList.isEmpty(), "Student List is not true");
	}

	@Test
	public void getSingleRecord() {
		facultiesServiceHelper.getParticulerRecord(EndPoints.GET_SINGLE_FACULTIE, 301 );
	

	}

	@Test
	public void createFaculty() {
		int id = facultiesServiceHelper.createFaculty(EndPoints.CREATE_FACULTIE).jsonPath().getInt("id");
		System.out.println("New id found as : " + id);
		assertNotNull(id, "ID is not null");
		//facultiesServiceHelper.getParticulerRecord(EndPoints.GET_SINGLE_FACULTIE, id);
	}
	
	@Test
	public void updateFacultyData() {
		int id = facultiesServiceHelper.updatefaculties(301, EndPoints.UPDATE_FACULTIE).jsonPath().getInt("id");
		System.out.println("According to ID, the student details is changed "+id);
		assertNotNull(id, "ID is not null");
		System.out.println("After updated: ");
		//facultiesServiceHelper.getParticulerRecord(EndPoints.GET_SINGLE_FACULTIE, id);
	}

	
}
