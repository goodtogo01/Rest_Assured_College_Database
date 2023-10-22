package com.College.Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.College.Constants.EndPoints;
import com.College.Helper.StudentsServiceHelper;
import com.College.Model.Students;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class StudentsCrudTest {
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
	public void createStudentDataTest() {
		int id = studentServiceHelper.createStudent(EndPoints.CREATE_STUDENT).jsonPath().getInt("id");
		System.out.println("New id found as : "+id);
		assertNotNull(id, "ID is not null");
		
	}
	@Test
	public void updateStudentData() {
		int id =studentServiceHelper.updateStudent(3394, EndPoints.UPDATE_STUDENT).jsonPath().getInt("id");
		System.out.println("According to ID, the student details is changed "+id);
		assertNotNull(id, "ID is not null");
	}

	@Test
	public void deleteSingleRecord() {
	 studentServiceHelper.deletStudentData(2221, EndPoints.DELETE_STUDENT);
		System.out.println("According to ID, the student details is changed ");
		//assertNotNull(id, "Id is not present");
	}
}
