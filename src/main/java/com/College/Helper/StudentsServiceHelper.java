package com.College.Helper;

import com.College.Utils.ConfigManager;

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
		
		
	}
}
