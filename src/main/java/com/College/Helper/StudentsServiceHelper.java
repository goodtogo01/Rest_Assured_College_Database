package com.College.Helper;

import com.College.Model.Students;
import com.College.Utils.ConfigManager;
import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

 

import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.HttpStatus;

public class StudentsServiceHelper {
	/*
	 --Fetch the data from the endpoints URL, PORT  
	 -- GET/POST/GET/PATCH/DEETE
	 -- Read the config variable
	 -- Make request 	 
*/
	
	private static final String BASE_URL = ConfigManager.getInstance().getString("baseURL");
	private static final String PORT = ConfigManager.getInstance().getString("port");
	
	public StudentsServiceHelper() {
		RestAssured.baseURI=BASE_URL;
		RestAssured.port=Integer.parseInt(PORT);
		RestAssured.useRelaxedHTTPSValidation();
	}
	public List<Students> getAll(String endPoint){
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept("appliction/json")
				.when().get(endPoint)
				.then().log().all().extract().response().andReturn();
 
		Type type =new TypeReference<List<Students>>() {}.getType();
 		List<Students> studentsList = response.as(type);
		return studentsList;

	}
	public Response createStudent(String endpoint) {
		Students student =new Students();
		student.setDepartmentsGroup("Group3");
		student.setId(3394);
		student.setFirstName("Kazol");
		student.setLastName("Kamar");
		student.setGrade("6");
		student.setEmail("kazolkamar@zsolution.com");
		
		Response response= RestAssured.given().contentType(ContentType.JSON)
				.log().all()
				.accept("application/json")
				.when().body(student)
				.post(endpoint)
				.andReturn();
		return response;
	}
	public Response updateStudent(Integer id, String endpoint) {
		Students student =new Students();
		student.setDepartmentsGroup("Group2");
		student.setId(020);
		student.setFirstName("Dozol");
		student.setLastName("Kamar");
		student.setGrade("6");
		student.setEmail("kazolkamar@zsolution.com");
		
		Response response = RestAssured.given().contentType(ContentType.JSON).accept("application/json")
				.log().all()
				.pathParam("id", id)
				.when().body(student)
				.patch(endpoint)
				.andReturn();
		return response;
				
	}

	public Response deletStudentData(Integer id, String endpoint) {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.log().all()
				.pathParam("id", id)
				.when().delete(endpoint)
				.andReturn();
		assertTrure(response.getStatusCode()==HttpStatus.SC_OK, "Delete operation is succeed");
		return response;
	}

	private void assertTrure(boolean b, String delete_operation_is_succeed) {
			}
}
