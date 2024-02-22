package com.College.Helper;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;

import com.College.Model.Faculties;
import com.College.Model.PhoneNumber;

import com.College.Utils.ConfigManager;
import com.College.Utils.RandomField;

import com.fasterxml.jackson.core.type.TypeReference;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

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
	public Response response;
	public static RequestSpecification requestSpecifications;

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

	public void getParticulerRecordByID(String endPoint, int id) {
		response = RestAssured.given().contentType(ContentType.JSON).accept("application/json").pathParam("id", id)
				.when().get(endPoint).then().log().all().extract().response();
		if (response.statusCode() > 300) {
			System.err.println("\nThis ID: \'" + id + "\' is not available or Deleted!!\n");
			System.out.println("With Status Code is : " + response.getStatusCode());

		} else if (response.statusCode() <= 201) {

			System.out.println("Record Founded as per id is : " + id);
			System.out.println("With Status Code is : " + response.getStatusCode());
		}

	}

	public List<Faculties> getParticulerRecordByName(String endPoint, String name) {

		response = RestAssured.given().contentType(ContentType.JSON).accept("application/json")
				.pathParam("firstName", name).when().get(endPoint).then().assertThat()
				.body("firstName[1]", Matchers.equalTo(name.toString()))
				.log().all().extract().response();

		Type type = new TypeReference<List<Faculties>>() {
		}.getType();
		List<Faculties> facultiesList = response.as(type);
		List<Faculties> firstNameValidation = facultiesList.stream().filter(f->f.getFirstName().equals(name)).collect(Collectors.toList());
		// can be found first and last name
		 System.out.println("Name found as : "+name);
	
		return facultiesList;
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
		response = requestSpecifications.post();
		return response;
	}

	public Response updatefaculties(String endpoint, Integer id) {
//		PhoneNumber phoneNumber = new PhoneNumber();
//		phoneNumber.setHomePhone(RandomField.randomPhoneNumber());
//		phoneNumber.setCellPhone(RandomField.randomPhoneNumber());

		faculties = new Faculties();
		// faculties.setDepartmentsGroup("Group3");
		faculties.setFirstName("Dula");
//		faculties.setLastName(RandomField.lastName());
//		faculties.setId(RandomField.randomId());
//		faculties.setEmail(RandomField.emails());
//		faculties.setSalary(RandomField.randomSalary());
//		faculties.setPhoneNumber(phoneNumber);

		Response response = RestAssured.given().contentType(ContentType.JSON).accept("application/json").log().all()
				.pathParam("id", id).when().body(faculties).patch(endpoint).andReturn();

		return response;

	}

	public Response deletFacultyData(String endpoint, Integer id) {
		response = RestAssured.given().contentType(ContentType.JSON).log().all().pathParam("id", id).when()
				.delete(endpoint).thenReturn();
		return response;
	}

}
