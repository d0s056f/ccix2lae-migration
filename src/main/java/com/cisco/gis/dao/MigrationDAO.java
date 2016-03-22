package com.cisco.gis.dao;

import java.util.List;
import com.cisco.gis.model.Migration;

public interface MigrationDAO {

	public void insertOrUpdatePlan(Migration migration);

	public Migration getPlan(String appId);

	/*
	 * public void deletePlan(int appId);
	 * 
	 * public List<Migration> list();
	 */

}
