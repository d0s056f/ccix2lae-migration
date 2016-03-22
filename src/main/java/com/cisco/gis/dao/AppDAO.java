package com.cisco.gis.dao;

import java.util.List;

import com.cisco.gis.model.App;

public interface AppDAO {

	public List<App> list(String funcGrp);
	
	public List<App> list2();

}
