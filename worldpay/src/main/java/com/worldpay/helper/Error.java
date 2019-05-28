package com.worldpay.helper;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Error {
	
	private String code;

	public Error() {}
	public Error(String code) {
		super();
		this.code = code;
	}
	
	/**
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 */
	public void setCode(String code) {
		this.code = code;
	}
}