package com.cisco.gis.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.cisco.gis.model.Migration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/* @author dhsanghv
 * 
 */

public class MigrationDAOImpl implements MigrationDAO {

	private JdbcTemplate jdbcTemplate;

	public MigrationDAOImpl() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring-servlet.xml");
		DataSource dataSource = (DataSource) ac.getBean("dataSource");
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void insertOrUpdatePlan(Migration migration) {
		if (migration.getApp_id() != null) {
			// update
			String sql = "UPDATE ccix_migration_plan SET functional_group=?, svp=?, director=?, plan_date=?, status=?, cutover_type=?, migration_lead=?, comments=? WHERE app_id=?";

			jdbcTemplate.update(sql, 
				//	migration.getApp_name(),
					migration.getFunc_grp(), 
				//	migration.getEman_priority(),
					migration.getSvp(),
					migration.getDirector(), 
					migration.getMig_date(),
					migration.getStatus(),
					migration.getCutover_type(),
					migration.getMigration_lead(),
					migration.getComments(),
				//	migration.getMig_quarter(),
				//	migration.getMig_pocs(), 
					migration.getApp_id());
				//	migration.getStatus());
				//	migration.getUser_id());
		} else {
			// insert
			String sql = "INSERT INTO ccix_migration_plan (app_id, functional_group, svp, director, plan_date, status, cutover_type, migration_lead, comments)"
					+ " VALUES (?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, 
				//	migration.getApp_name(),
					migration.getApp_id(),
					migration.getFunc_grp(), 
				//	migration.getEman_priority(),
					migration.getSvp(),
					migration.getDirector(), 
					migration.getMig_date(),
					migration.getStatus(),
					migration.getCutover_type(),
					migration.getMigration_lead(),
					migration.getComments()
				//	migration.getMig_quarter(),
				//	migration.getMig_pocs(), 
					);
		}
	}

	@Override
	public Migration getPlan(String appId) {

		String sql = "SELECT * FROM ccix_migration_plan WHERE app_id='" + appId
				+ "'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Migration>() {

			@Override
			public Migration extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Migration migration = new Migration();
					migration.setApp_id(rs.getString("app_id"));
					// migration.setApp_name(rs.getString("app_name"));
					migration.setFunc_grp(rs.getString("functional_group"));
					// migration.setEman_priority(rs.getString("eman"));
					migration.setSvp(rs.getString("svp"));
					migration.setDirector(rs.getString("director"));
					migration.setMig_date(rs.getString("plan_date"));
					// migration.setMig_quarter(rs.getString("quarter"));
					// migration.setPocs(rs.getArray("pocs"));
					migration.setStatus(rs.getString("status"));
					migration.setCutover_type(rs.getString("cutover_type"));
					migration.setMigration_lead(rs.getString("migration_lead"));
					migration.setComments(rs.getString("comments"));

					return migration;
				}

				return null;
			}

		});

	}

}