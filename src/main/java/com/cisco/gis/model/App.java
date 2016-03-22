package com.cisco.gis.model;

public class App {
	private String user_id;
	private String app_id;
	private String app_name;
	// private String service_offering;
	private String func_group;

	public String getFunc_group() {
		return func_group;
	}

	public void setFunc_group(String func_group) {
		this.func_group = func_group;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	// public String getService_offering() {
	// return service_offering;
	// }
	// public void setService_offering(String service_offering) {
	// this.service_offering = service_offering;
	// }
}
