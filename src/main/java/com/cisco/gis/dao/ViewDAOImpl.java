package com.cisco.gis.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.cisco.gis.model.Migration;

public class ViewDAOImpl implements ViewDAO {

	private JdbcTemplate jdbcTemplate;

	public ViewDAOImpl() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring-servlet.xml");
		DataSource dataSource = (DataSource) ac.getBean("dataSource");
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public HashMap<String, String> applist() {

		String sql = "SELECT STATUS, COUNT(CCIX_MIGRATION_PLAN.STATUS) AS STATUS_COUNT FROM CCIX_MIGRATION_PLAN  WHERE APP_TYPE='IT' GROUP BY STATUS";
		HashMap<String, String> reports = jdbcTemplate.query(sql,
				new ResultSetExtractor<HashMap>() {
					@Override
					public HashMap extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						HashMap<String, String> mapRet = new HashMap<String, String>();
						while (rs.next()) {
							mapRet.put(
									rs.getString("status"),
									rs.getString("status_count"));
						}
						return mapRet;
					}
				});

		return reports;

	}

	@Override
	public List<Migration> decommlist() {

		String sql = "select count(status) statuscount from instance_group where status='decommission' and env_id='ccix'";

		List<Migration> reports15 = jdbcTemplate.query(sql,
				new RowMapper<Migration>() {

					@Override
					public Migration mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						Migration view13 = new Migration();

						view13.setDecommissioned(rs.getString("statuscount"));

						return view13;
					}

				});

		return reports15;

	}

	@Override
	public List<Migration> dir_table() {

		String sql = "SELECT DIRECTOR, SVP, SUM(COMMITTED) COMMITTED, SUM(COMPLETED) COMPLETED, SUM(UNCOMMITTED) UNCOMMITTED FROM "
				+ "(SELECT DIRECTOR, SVP, COUNT(CCIX_MIGRATION_PLAN.STATUS) \"COMMITTED\", 0 \"COMPLETED\", 0 \"UNCOMMITTED\" FROM CCIX_MIGRATION_PLAN "
				+ "WHERE STATUS = 'committed' AND APP_TYPE='IT' AND SVP!='manville' GROUP BY DIRECTOR, SVP, STATUS "
				+ "UNION "
				+ "SELECT DIRECTOR, SVP, 0 \"COMMITTED\", COUNT(CCIX_MIGRATION_PLAN.STATUS) \"COMPLETED\", 0 \"UNCOMMITTED\" FROM CCIX_MIGRATION_PLAN "
				+ "WHERE STATUS = 'completed' AND APP_TYPE='IT' AND SVP!='manville' GROUP BY DIRECTOR, SVP, STATUS "
				+ "UNION "
				+ "SELECT DIRECTOR, SVP, 0 \"COMMITTED\", 0 \"COMPLETED\", COUNT(CCIX_MIGRATION_PLAN.STATUS) \"UNCOMMITTED\" FROM CCIX_MIGRATION_PLAN "
				+ "WHERE STATUS = 'uncommitted' AND APP_TYPE='IT' AND SVP!='manville' GROUP BY DIRECTOR, SVP, STATUS) "
				+ "GROUP BY DIRECTOR, SVP ORDER BY DIRECTOR";

		List<Migration> reports2 = jdbcTemplate.query(sql,
				new RowMapper<Migration>() {

					@Override
					public Migration mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						Migration view2 = new Migration();

						view2.setDirector(rs.getString("director"));
						view2.setSvp(rs.getString("svp"));
						view2.setCommitted(rs.getString("committed"));
						view2.setCompleted(rs.getString("completed"));
						view2.setUncommitted(rs.getString("uncommitted"));

						return view2;
					}

				});

		return reports2;

	}

	@Override
	public List<Migration> svp_chart() {

		String sql = "SELECT SVP, SUM(COMMITTED) COMMITTED, SUM(COMPLETED) COMPLETED, SUM(UNCOMMITTED) UNCOMMITTED, SUM(IN_PROGRESS) IN_PROGRESS FROM "+
				"(SELECT SVP, COUNT(CCIX_MIGRATION_PLAN.STATUS) \"COMMITTED\", 0 \"COMPLETED\", 0 \"UNCOMMITTED\", 0 \"IN_PROGRESS\" FROM CCIX_MIGRATION_PLAN WHERE STATUS = 'committed' AND APP_TYPE='IT' GROUP BY SVP, STATUS "+
				"UNION "+
				"SELECT SVP, 0 \"COMMITTED\", COUNT(CCIX_MIGRATION_PLAN.STATUS) \"COMPLETED\", 0 \"UNCOMMITTED\", 0 \"IN_PROGRESS\" FROM CCIX_MIGRATION_PLAN WHERE STATUS = 'completed' AND APP_TYPE='IT' GROUP BY SVP, STATUS "+
				"UNION "+
				"SELECT SVP, 0 \"COMMITTED\", 0 \"COMPLETED\", COUNT(CCIX_MIGRATION_PLAN.STATUS) \"UNCOMMITTED\", 0 \"IN_PROGRESS\" FROM CCIX_MIGRATION_PLAN WHERE STATUS = 'uncommitted' AND APP_TYPE='IT' GROUP BY SVP, STATUS "+
				"UNION "+
				"SELECT SVP, 0 \"COMMITTED\", 0 \"COMPLETED\", 0 \"UNCOMMITTED\", COUNT(CCIX_MIGRATION_PLAN.STATUS) \"IN_PROGRESS\" FROM CCIX_MIGRATION_PLAN WHERE STATUS = 'in progress' AND APP_TYPE='IT' GROUP BY SVP, STATUS) "+
				"GROUP BY SVP ORDER BY SVP";

		List<Migration> reports3 = jdbcTemplate.query(sql,
				new RowMapper<Migration>() {

					@Override
					public Migration mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						Migration view3 = new Migration();

						view3.setSvp(rs.getString("svp"));
						view3.setCommitted(rs.getString("committed"));
						view3.setCompleted(rs.getString("completed"));
						view3.setUncommitted(rs.getString("uncommitted"));
						view3.setIn_progress(rs.getString("in_progress"));

						return view3;
					}

				});

		return reports3;

	}

	@Override
	public HashMap<String, String> dir_page(String dir) {

		String sql = "SELECT DIRECTOR, STATUS, COUNT(CCIX_MIGRATION_PLAN.STATUS) AS STATUS_COUNT FROM CCIX_MIGRATION_PLAN WHERE DIRECTOR='"
				+ dir + "' GROUP BY DIRECTOR, STATUS ORDER BY DIRECTOR";

		HashMap<String, String> reports4 = jdbcTemplate.query(sql,
				new ResultSetExtractor<HashMap>() {
					@Override
					public HashMap extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						HashMap<String, String> mapRet = new HashMap<String, String>();
						while (rs.next()) {
							mapRet.put("director", rs.getString("director"));
							mapRet.put(
									rs.getString("status").replace(" ", "_"),
									rs.getString("status_count"));
						}
						return mapRet;
					}
				});

		return reports4;

	}

	@Override
	public List<Migration> offering_table(String dir) {

		String sql = "SELECT FUNCTIONAL_GROUP, SVP, DIRECTOR, SUM(COMMITTED) COMMITTED, SUM(COMPLETED) COMPLETED, SUM(UNCOMMITTED) UNCOMMITTED FROM "
				+ "(SELECT FUNCTIONAL_GROUP, SVP, DIRECTOR, COUNT(CCIX_MIGRATION_PLAN.STATUS) \"COMMITTED\", 0 \"COMPLETED\", 0 \"UNCOMMITTED\" FROM CCIX_MIGRATION_PLAN WHERE STATUS = 'committed' GROUP BY FUNCTIONAL_GROUP, SVP, DIRECTOR, STATUS "
				+ "UNION "
				+ "SELECT FUNCTIONAL_GROUP, SVP, DIRECTOR, 0 \"COMMITTED\", COUNT(CCIX_MIGRATION_PLAN.STATUS) \"COMPLETED\", 0 \"UNCOMMITTED\" FROM CCIX_MIGRATION_PLAN WHERE STATUS = 'completed' GROUP BY FUNCTIONAL_GROUP, SVP, DIRECTOR, STATUS "
				+ "UNION "
				+ "SELECT FUNCTIONAL_GROUP, SVP, DIRECTOR, 0 \"COMMITTED\", 0 \"COMPLETED\", COUNT(CCIX_MIGRATION_PLAN.STATUS) \"UNCOMMITTED\" FROM CCIX_MIGRATION_PLAN WHERE STATUS = 'uncommitted' GROUP BY FUNCTIONAL_GROUP, SVP, DIRECTOR, STATUS) "
				+ "WHERE DIRECTOR='"
				+ dir
				+ "' GROUP BY FUNCTIONAL_GROUP, SVP, DIRECTOR ORDER BY FUNCTIONAL_GROUP";

		List<Migration> reports5 = jdbcTemplate.query(sql,
				new RowMapper<Migration>() {

					@Override
					public Migration mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						Migration view4 = new Migration();

						view4.setFunc_grp(rs.getString("functional_group"));
						view4.setSvp(rs.getString("svp"));
						view4.setDirector(rs.getString("director"));
						view4.setCommitted(rs.getString("committed"));
						view4.setCompleted(rs.getString("completed"));
						view4.setUncommitted(rs.getString("uncommitted"));

						return view4;
					}

				});

		return reports5;

	}

	@Override
	public HashMap<String, String> offering_page(String offering) {

		String sql = "SELECT FUNCTIONAL_GROUP, STATUS, COUNT(CCIX_MIGRATION_PLAN.STATUS) AS STATUS_COUNT FROM CCIX_MIGRATION_PLAN WHERE FUNCTIONAL_GROUP='"
				+ offering
				+ "' GROUP BY FUNCTIONAL_GROUP, STATUS ORDER BY FUNCTIONAL_GROUP";

		HashMap<String, String> reports6 = jdbcTemplate.query(sql,
				new ResultSetExtractor<HashMap>() {
					@Override
					public HashMap extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						HashMap<String, String> mapRet = new HashMap<String, String>();
						while (rs.next()) {
							mapRet.put("offering",
									rs.getString("functional_group"));
							mapRet.put(
									rs.getString("status").replace(" ", "_"),
									rs.getString("status_count"));
						}
						return mapRet;
					}
				});

		return reports6;

	}

	@Override
	public List<Migration> app_table(String fgrp) {

		String sql = "SELECT APP_ID, STATUS, PLAN_DATE, DIRECTOR FROM CCIX_MIGRATION_PLAN WHERE FUNCTIONAL_GROUP='"
				+ fgrp + "' ORDER BY STATUS";

		List<Migration> reports7 = jdbcTemplate.query(sql,
				new RowMapper<Migration>() {

					@Override
					public Migration mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						Migration view5 = new Migration();

						view5.setApp_id(rs.getString("app_id"));
						view5.setStatus(rs.getString("status"));
						view5.setMig_date(rs.getString("plan_date"));
						view5.setDirector(rs.getString("director"));

						return view5;
					}

				});

		return reports7;

	}

	@Override
	public List<Migration> uncomm_table() {

		String sql = "SELECT APP_ID, FUNCTIONAL_GROUP, SVP, DIRECTOR FROM CCIX_MIGRATION_PLAN WHERE STATUS='uncommitted' AND APP_TYPE='IT' ORDER BY APP_ID";

		List<Migration> reports8 = jdbcTemplate.query(sql,
				new RowMapper<Migration>() {

					@Override
					public Migration mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						Migration view6 = new Migration();

						view6.setApp_id(rs.getString("app_id"));
						view6.setFunc_grp(rs.getString("functional_group"));
						view6.setSvp(rs.getString("svp"));
						view6.setDirector(rs.getString("director"));

						return view6;
					}

				});

		return reports8;

	}

	@Override
	public List<Migration> completed_table() {

		String sql = "SELECT APP_ID, FUNCTIONAL_GROUP, SVP, DIRECTOR, PLAN_DATE FROM CCIX_MIGRATION_PLAN WHERE STATUS='completed' AND APP_TYPE='IT' ORDER BY APP_ID";

		List<Migration> reports9 = jdbcTemplate.query(sql,
				new RowMapper<Migration>() {

					@Override
					public Migration mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						Migration view7 = new Migration();

						view7.setApp_id(rs.getString("app_id"));
						view7.setFunc_grp(rs.getString("functional_group"));
						view7.setSvp(rs.getString("svp"));
						view7.setDirector(rs.getString("director"));
						view7.setMig_date(rs.getString("plan_date"));

						return view7;
					}

				});

		return reports9;

	}

	@Override
	public List<Migration> committed_table() {

		String sql = "SELECT APP_ID, FUNCTIONAL_GROUP, SVP, DIRECTOR, PLAN_DATE FROM CCIX_MIGRATION_PLAN WHERE STATUS='committed' AND APP_TYPE='IT' ORDER BY APP_ID";

		List<Migration> reports10 = jdbcTemplate.query(sql,
				new RowMapper<Migration>() {

					@Override
					public Migration mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						Migration view8 = new Migration();

						view8.setApp_id(rs.getString("app_id"));
						view8.setFunc_grp(rs.getString("functional_group"));
						view8.setSvp(rs.getString("svp"));
						view8.setDirector(rs.getString("director"));
						view8.setMig_date(rs.getString("plan_date"));

						return view8;
					}

				});

		return reports10;

	}

	@Override
	public List<Migration> progress_table() {

		String sql = "SELECT APP_ID, FUNCTIONAL_GROUP, SVP, DIRECTOR FROM CCIX_MIGRATION_PLAN WHERE STATUS='in progress' ORDER BY APP_ID";

		List<Migration> reports11 = jdbcTemplate.query(sql,
				new RowMapper<Migration>() {

					@Override
					public Migration mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						Migration view9 = new Migration();

						view9.setApp_id(rs.getString("app_id"));
						view9.setFunc_grp(rs.getString("functional_group"));
						view9.setSvp(rs.getString("svp"));
						view9.setDirector(rs.getString("director"));

						return view9;
					}

				});

		return reports11;

	}

	@Override
	public List<Migration> dircomm_table(String dir) {

		String sql = "select a.app_id, get_eman_name(a.app_id,'ccix') app_name, a.functional_group, a.svp, get_eman_priority(a.app_id,'ccix') priority, a.plan_date from ccix_migration_plan a where a.status='committed' and a.director = '"
				+ dir + "'";

		List<Migration> reports12 = jdbcTemplate.query(sql,
				new RowMapper<Migration>() {

					@Override
					public Migration mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						Migration view10 = new Migration();

						view10.setApp_id(rs.getString("app_id"));
						view10.setApp_name(rs.getString("app_name"));
						view10.setFunc_grp(rs.getString("functional_group"));
						view10.setSvp(rs.getString("svp"));
						view10.setEman_priority(rs.getString("priority"));
						view10.setMig_date(rs.getString("plan_date"));

						return view10;
					}

				});

		return reports12;

	}

	@Override
	public List<Migration> diruncomm_table(String dir) {

		String sql = "select a.app_id, get_eman_name(a.app_id,'ccix') app_name, a.functional_group, a.svp, get_eman_priority(a.app_id,'ccix') priority, a.plan_date from ccix_migration_plan a where a.status='uncommitted' and a.director = '"
				+ dir + "'";

		List<Migration> reports13 = jdbcTemplate.query(sql,
				new RowMapper<Migration>() {

					@Override
					public Migration mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						Migration view11 = new Migration();

						view11.setApp_id(rs.getString("app_id"));
						view11.setApp_name(rs.getString("app_name"));
						view11.setFunc_grp(rs.getString("functional_group"));
						view11.setSvp(rs.getString("svp"));
						view11.setEman_priority(rs.getString("priority"));
						view11.setMig_date(rs.getString("plan_date"));

						return view11;
					}

				});

		return reports13;

	}

	@Override
	public List<Migration> dircomp_table(String dir) {

		String sql = "select a.app_id, get_eman_name(a.app_id,'ccix') app_name, a.functional_group, a.svp, get_eman_priority(a.app_id,'ccix') priority, a.plan_date from ccix_migration_plan a where a.status='completed' and a.director = '"
				+ dir + "'";

		List<Migration> reports14 = jdbcTemplate.query(sql,
				new RowMapper<Migration>() {

					@Override
					public Migration mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						Migration view12 = new Migration();

						view12.setApp_id(rs.getString("app_id"));
						view12.setApp_name(rs.getString("app_name"));
						view12.setFunc_grp(rs.getString("functional_group"));
						view12.setSvp(rs.getString("svp"));
						view12.setEman_priority(rs.getString("priority"));
						view12.setMig_date(rs.getString("plan_date"));

						return view12;
					}

				});

		return reports14;

	}

	@Override
	public List<Migration> dirprog_table(String dir) {

		String sql = "select a.app_id, get_eman_name(a.app_id,'ccix') app_name, a.functional_group, a.svp, get_eman_priority(a.app_id,'ccix') priority, a.plan_date from ccix_migration_plan a where a.status='in progress' and a.director = '"
				+ dir + "'";

		List<Migration> reports16 = jdbcTemplate.query(sql,
				new RowMapper<Migration>() {

					@Override
					public Migration mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						Migration view14 = new Migration();

						view14.setApp_id(rs.getString("app_id"));
						view14.setApp_name(rs.getString("app_name"));
						view14.setFunc_grp(rs.getString("functional_group"));
						view14.setSvp(rs.getString("svp"));
						view14.setEman_priority(rs.getString("priority"));
						view14.setMig_date(rs.getString("plan_date"));

						return view14;
					}

				});

		return reports16;

	}

	// @Override
	// public List<Migration> offcomm_table(String off, String dir) {
	//
	// String sql =
	// "select a.app_id, get_eman_name(a.app_id,'ccix') app_name, a.director, a.svp, get_eman_priority(a.app_id,'ccix') priority, a.plan_date from ccix_migration_plan a where a.status='committed' and a.functional_group = '"
	// + off + "' and a.director='" +dir+ "'";
	//
	// List<Migration> reports12 = jdbcTemplate.query(sql, new
	// RowMapper<Migration>() {
	//
	// @Override
	// public Migration mapRow(ResultSet rs, int rowNum) throws SQLException {
	//
	// Migration view10 = new Migration();
	//
	// view10.setApp_id(rs.getString("app_id"));
	// view10.setApp_name(rs.getString("app_name"));
	// view10.setFunc_grp(rs.getString("functional_group"));
	// view10.setSvp(rs.getString("svp"));
	// view10.setEman_priority(rs.getString("priority"));
	// view10.setMig_date(rs.getString("plan_date"));
	//
	// return view10;
	// }
	//
	// });
	//
	// return reports12;
	//
	// }

}
