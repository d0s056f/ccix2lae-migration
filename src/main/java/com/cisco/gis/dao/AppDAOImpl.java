package com.cisco.gis.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.cisco.gis.model.App;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/* @author dhsanghv
 * 
 */

public class AppDAOImpl implements AppDAO {

	private JdbcTemplate jdbcTemplate;

	public AppDAOImpl() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring-servlet.xml");
		DataSource dataSource = (DataSource) ac.getBean("dataSource");
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<App> list(String funcGrp) {

		String sql = "SELECT app_id FROM CCIX_MIGRATION_PLAN WHERE functional_group='"
				+ funcGrp + "'";
		List<App> listApps = jdbcTemplate.query(sql, new RowMapper<App>() {

			@Override
			public App mapRow(ResultSet rs, int rowNum) throws SQLException {
				App aApp = new App();

				aApp.setApp_id(rs.getString("app_id"));

				return aApp;
			}

		});
		return listApps;
	}

	@Override
	public List<App> list2() {

		String sql = "SELECT UNIQUE FUNCTIONAL_GROUP FROM CCIX_MIGRATION_PLAN";
		List<App> listOrgs = jdbcTemplate.query(sql, new RowMapper<App>() {

			@Override
			public App mapRow(ResultSet rs, int rowNum) throws SQLException {
				App bApp = new App();

				bApp.setFunc_group(rs.getString("functional_group"));

				return bApp;
			}

		});

		return listOrgs;

	}

}