package com.College.Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.College.Constants.EndPoints;
import com.College.Helper.StudentsServiceHelper;
import com.College.Model.Students;
import com.College.Reports.ExtentReporterNG;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class StudentsCrudTest{
	Logger log;
	private StudentsServiceHelper studentServiceHelper;
	 
	
	
	@BeforeClass
	public void initialization() {
	
		studentServiceHelper = new StudentsServiceHelper();
		
	}
	
	@Test
	public void getAllStudents() {
		List<Students> studentList = studentServiceHelper.getAll(EndPoints.GET_ALL_STUDENTS);
		 assertNotNull(studentList, "List is not empty");
		assertFalse(studentList.isEmpty(), "Student List is not true");

	}
	@Test
	public void getSingleRecordById() {
		studentServiceHelper.getParticulerRecordByID(EndPoints.GET_SINGLE_STUDENT, 1424);

	}
	@Test
	public void getSingleRecordByFiresName() {
		studentServiceHelper.getParticulerRecordByName(EndPoints.GET_STUDENT_BY_FIRST_NAME, "Carmel");
	 }
	@Test
	public void createStudentDataTest() {
		int id = studentServiceHelper.createStudent(EndPoints.CREATE_STUDENT).jsonPath().getInt("id");
		System.out.println("New id found as : "+id);
		assertNotNull(id, "ID is not null");
		
	}
	@Test
	public void updateStudentData() {
		int id =studentServiceHelper.updateStudent(2224, EndPoints.UPDATE_STUDENT).jsonPath().getInt("id");
		System.out.println("According to ID, the student details is changed "+id);
		assertNotNull(id, "ID is not null");
	}
	@Test
	public void deleteSingleRecord() {
	 studentServiceHelper.deletStudentData(38, EndPoints.DELETE_STUDENT);
		System.out.println("According to ID, the student details is changed ");
		//assertNotNull(id, "Id is not present");
	}
}
