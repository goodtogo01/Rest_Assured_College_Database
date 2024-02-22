package com.College.Tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.College.Constants.EndPoints;
import com.College.Helper.FacultiesServiceHelper;

import com.College.Model.Faculties;

import com.College.Utils.Utilities;

import io.restassured.response.Response;

public class FacultiesCrudOps {
	Utilities utils = new Utilities();
	private FacultiesServiceHelper facultiesServiceHelper;
	public Response response;

	@BeforeClass
	public void initialization() {
		facultiesServiceHelper = new FacultiesServiceHelper();
	}

	@Test
	public void getFaculties() {

		List<Faculties> facultiesList = facultiesServiceHelper.getAll(EndPoints.GET_ALL_FACULTIES);
		assertNotNull(facultiesList, "List is not empty");
		assertFalse(facultiesList.isEmpty(), "Student List is not true");
		facultiesList.stream().filter(f -> f.getDepartmentsGroup() == "Group3").forEach(System.out::println);
	}

	@Test
	public void getSingleRecordById() {
		facultiesServiceHelper.getParticulerRecordByID(EndPoints.GET_SINGLE_FACULTIE, 128);

	}

	@Test
	public void getSingleRecordByFiresName() {
		List<Faculties> getSearchValue = facultiesServiceHelper
				.getParticulerRecordByName(EndPoints.GET_FACULTIE_BY_FIRST_NAME, "Lamar");
		assertNotNull(getSearchValue, "List is not empty");

	}

	@Test
	public void createFaculty() {
		int id = facultiesServiceHelper.createFaculty(EndPoints.CREATE_FACULTIE).jsonPath().getInt("id");
		System.out.println("New id found as : " + id);
		assertNotNull(id, "ID is not null");
	}

	@Test
	public void updateFacultyData() {
		int id = facultiesServiceHelper.updatefaculties(EndPoints.UPDATE_FACULTIE, 128).jsonPath().getInt("id");
		System.out.println("According to ID, the student details is changed " + id);
		assertNotNull(id, "ID is not null");
		System.out.println("After updated: ");
		// facultiesServiceHelper.getParticulerRecord(EndPoints.GET_SINGLE_FACULTIE,
		// id);
	}

	@Test
	public void deleteSingleRecord() {
		facultiesServiceHelper.deletFacultyData(EndPoints.DELETE_FACULTIE, 116);
		System.out.println("According to ID, the student details is changed ");
		// assertNotNull(id, "Id is not present");
	}
}
