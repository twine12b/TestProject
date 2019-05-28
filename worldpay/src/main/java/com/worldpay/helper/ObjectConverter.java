/*
 * Utility to aid with testing and the creation of content 
 * String parameters
 */
package com.worldpay.helper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectConverter {
	
	ObjectMapper mapper = new ObjectMapper();
	
	
	/**
	 * @param obj
	 * @return
	 */
	public String toJson(Object obj) {
		String jsonString = "";
		
		try{
		jsonString = mapper.writeValueAsString(obj);
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return jsonString;
	}

}
