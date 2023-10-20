package com.College.Helper;

import com.College.Constants.EndPoints;
import com.College.Model.Students;
import com.College.Utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.internal.org.objectweb.asm.TypeReference;

import java.lang.reflect.Type;
import java.util.List;

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
	public List<Students> getAllStudents(){
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.get(EndPoints.GET_ALL_STUDENTS)
				.andReturn();

		Type type = new TypeReference<List<Students>>(){}.getType();
		List<Students> studentsList = response.as(type);
		return studentsList;

	}
}
