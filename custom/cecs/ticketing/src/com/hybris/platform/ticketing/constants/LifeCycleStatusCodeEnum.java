package com.hybris.platform.ticketing.constants;

public enum LifeCycleStatusCodeEnum {
	
	E1("Open"), E2("In Process"), E3("Completed"), E4("Closed");
	
	
	private LifeCycleStatusCodeEnum(String code) {
		this.code = code;
	}
	
	private final String code;

	/**
	 * @return the name
	 */
	public String getCode() {
		return code;
	}
	
	
}
