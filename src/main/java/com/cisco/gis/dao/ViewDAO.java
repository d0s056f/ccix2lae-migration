package com.cisco.gis.dao;

import java.util.HashMap;
import java.util.List;

import com.cisco.gis.model.Migration;

public interface ViewDAO {
	
	public HashMap<String, String> applist();

	List<Migration> dir_table();

	List<Migration> svp_chart();

	HashMap<String, String> dir_page(String dir);

	List<Migration> offering_table(String dir);

	HashMap<String, String> offering_page(String offering);

	List<Migration> app_table(String fgrp);

	List<Migration> uncomm_table();

	List<Migration> completed_table();

	List<Migration> committed_table();

	List<Migration> progress_table();

	List<Migration> dircomm_table(String dir);

	List<Migration> diruncomm_table(String dir);

	List<Migration> dircomp_table(String dir);

	List<Migration> decommlist();

	List<Migration> dirprog_table(String dir);

//	List<Migration> offcomm_table(String off, String dir);

}
