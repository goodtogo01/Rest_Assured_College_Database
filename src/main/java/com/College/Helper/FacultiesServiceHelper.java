package com.College.Helper;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;

import com.College.Model.Faculties;
import com.College.Model.PhoneNumber;
import com.College.Utils.ConfigManager;
import com.College.Utils.RandomField;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.unit.objects.Reflect;

public class FacultiesServiceHelper {
	// Fetch the data from the endpoints
	// GET/POST/GET/PATCH/DEETE

	/*
	 * --Fetch the data from the endpoints URL, PORT -- GET/POST/GET/PATCH/DEETE --
	 * Read the config variable -- Make request
	 */
	/*
	 * MockNeat mockNeat = MockNeat.threadLocal(); Gson gson = new GsonBuilder()
	 * .setPrettyPrinting() .create();
	 */

	private static final String BASE_URL = ConfigManager.getInstance().getString("baseURL");
	private static final String PORT = ConfigManager.getInstance().getString("port");
	public Faculties faculties;

	public FacultiesServiceHelper() {
		RestAssured.baseURI = BASE_URL;
		RestAssured.port = Integer.parseInt(PORT);
		RestAssured.useRelaxedHTTPSValidation();
	}

	public List<Faculties> getAll(String endPoint) {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept("appliction/json").when()
				.get(endPoint).then().log().all().extract().response().andReturn();

		Type type = new TypeReference<List<Faculties>>() {
		}.getType();
		List<Faculties> facultiesList = response.as(type);
		return facultiesList;

	}

 
	public void getParticulerRecord(String endPoint, int id)  {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept("application/json")
				.pathParam("id", id)
				.when().get(endPoint)
				.then().log().all().extract().response();

		
	}

	public Response createFaculty(String endpoint) {
		
	
		PhoneNumber phoneNumber = new PhoneNumber();
	 	phoneNumber.setHomePhone(RandomField.randomPhoneNumber());
		phoneNumber.setCellPhone(RandomField.randomPhoneNumber());
		
		
		faculties = new Faculties();
		faculties.setDepartmentsGroup("Group3");
		faculties.setFirstName(RandomField.firstName());
		faculties.setLastName(RandomField.lastName());
		faculties.setId(RandomField.randomId());
		faculties.setEmail(RandomField.emails());
		faculties.setSalary(RandomField.randomSalary());
		faculties.setPhoneNumber(phoneNumber);

		Response response = RestAssured.given().contentType(ContentType.JSON).log().all().accept("application/json")
				.when().body(faculties).post(endpoint).andReturn();
		return response;
	}
	public Response updatefaculties(Integer id, String endpoint) {
		PhoneNumber phoneNumber = new PhoneNumber();
	 	phoneNumber.setHomePhone(RandomField.randomPhoneNumber());
		phoneNumber.setCellPhone(RandomField.randomPhoneNumber());
		
		
		faculties = new Faculties();
		faculties.setDepartmentsGroup("Group3");
		faculties.setFirstName(RandomField.firstName());
		faculties.setLastName(RandomField.lastName());
		faculties.setId(RandomField.randomId());
		faculties.setEmail(RandomField.emails());
		faculties.setSalary(RandomField.randomSalary());
		faculties.setPhoneNumber(phoneNumber);
		
		Response response = RestAssured.given().contentType(ContentType.JSON).accept("application/json")
				.log().all()
				.pathParam("id", id)
				.when().body(faculties)
				.patch(endpoint)
				.andReturn();
		return response;
				
	}

//	public Response deletfacultiesData(Integer id, String endpoint) {
//		Response response = RestAssured.given().contentType(ContentType.JSON)
//				.log().all()
//				.pathParam("id", id)
//				.when().delete(endpoint)
//				.andReturn();
//		assertTrure(response.getStatusCode()==HttpStatus.SC_OK, "Delete operation is succeed");
//		return response;
//	}

}
