package com.College.Helper;

import com.College.Constants.EndPoints;
import com.College.Model.Students;
import com.College.Utils.ConfigManager;
import com.College.Utils.RandomField;
import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

 

import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

public class StudentsServiceHelper {
	/*
	 --Fetch the data from the endpoints URL, PORT  
	 -- GET/POST/GET/PATCH/DEETE
	 -- Read the config variable
	 -- Make request 	 
*/
	
	private static final String BASE_URL = ConfigManager.getInstance().getString("baseURL");
	private static final String PORT = ConfigManager.getInstance().getString("port");
	static Response response;
	
	public StudentsServiceHelper() {
		RestAssured.baseURI=BASE_URL;
		RestAssured.port=Integer.parseInt(PORT);
		RestAssured.useRelaxedHTTPSValidation();
	}
	public List<Students> getAll(String endPoint){
		 response = RestAssured.given().contentType(ContentType.JSON)
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
		student.setId(RandomField.randomId());
		student.setFirstName(RandomField.firstName());
		student.setLastName(RandomField.lastName());
		student.setGrade(RandomField.randmGrade());
		student.setEmail(RandomField.emails());
		
		 response= RestAssured.given().contentType(ContentType.JSON)
				.log().all()
				.accept("application/json")
				.when().body(student)
				.post(endpoint)
				.andReturn();
		return response;
	}
	public Response updateStudent(Integer id, String endpoint) {
		Students student =new Students();
//		student.setDepartmentsGroup("Group2");
//		student.setId(020);
		student.setFirstName("Khanch");
//		student.setLastName("Kamar");
//		student.setGrade(6);
//		student.setEmail("kazolkamar@zsolution.com");
		
		 response = RestAssured.given().contentType(ContentType.JSON).accept("application/json")
				.log().all()
				.pathParam("id", id)
				.when().body(student)
				.patch(endpoint)
				.andReturn();
		return response;
				
	}
	public Response deletStudentData(Integer id, String endpoint) {
		 response = RestAssured.given().contentType(ContentType.JSON)
				.log().all()
				.pathParam("id", id)
				.when().delete(endpoint)
				.thenReturn();
		return response;
	}
	public void getParticulerRecordByName(String endPoint, String name) {
		response = RestAssured.given().contentType(ContentType.JSON).accept("application/json")
				.pathParam("firstName", name).when().get(endPoint).then().assertThat()
				.body("firstName[1]", Matchers.equalTo(name.toString())).extract().response();
	}
	public void getParticulerRecordByID(String endPoint, int id) {
		response = RestAssured.given().contentType(ContentType.JSON).accept("application/json").pathParam("id", id)
				.when().get(endPoint).then().log().all().extract().response();
		if (response.statusCode() >300) {
			System.err.println("\nThis ID: \'"+id+"\' is not available or Deleted!!\n");

		}else if(response.statusCode()<=201) {
			
			System.out.println("Record Founded as per id is : "+id);
		}

	}


}





