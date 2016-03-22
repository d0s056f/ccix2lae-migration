package com.cisco.gis.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisco.gis.dao.AppDAOImpl;
import com.cisco.gis.dao.MigrationDAO;
import com.cisco.gis.dao.MigrationDAOImpl;
import com.cisco.gis.dao.ViewDAOImpl;
import com.cisco.gis.model.App;
import com.cisco.gis.model.Migration;
import com.cisco.gis.service.ldap;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView("redirect:/view");
		LOGGER.info("Login Page");
		return model;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("home");
		LOGGER.info("Welcome to the CCIX to LAE migration dashboard");

		String userName = request.getHeader("AUTH_USER");
		// String userName = "dhsanghv";
		// System.out.println(userName);
		model.addObject("username", userName);

		AppDAOImpl app = new AppDAOImpl();
		List<App> app1 = app.list2();
		model.addObject("orgs", app1);
		return model;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("edit");
		LOGGER.info("Welcome to the edit section");

		String userName = request.getHeader("AUTH_USER");
		// String userName = "dhsanghv";
		// System.out.println(userName);
		model.addObject("username", userName);

		AppDAOImpl app = new AppDAOImpl();
		List<App> app1 = app.list2();
		model.addObject("orgs", app1);
		return model;
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("view");
		LOGGER.info("Welcome to the view reports section");

		String userName = request.getHeader("AUTH_USER");
		 //String userName = "dhsanghv";
		// System.out.println(userName);
		model.addObject("username", userName);

		ViewDAOImpl app = new ViewDAOImpl();
		HashMap<String, String> app1 = app.applist();
		model.addObject("status", app1);

		List<Migration> decomm1 = app.decommlist();
		model.addObject("decommstatus", decomm1);

		JSONObject jsonData = new JSONObject();
		for (int i = 0; i < app1.size(); i++) {
			jsonData.put("committed", app1.get("committed"));
			jsonData.put("uncommitted", app1.get("uncommitted"));
			jsonData.put("in_progress", app1.get("in_progress"));
			jsonData.put("completed", app1.get("completed"));
		}
		model.addObject("pieData", jsonData);

		ViewDAOImpl dir = new ViewDAOImpl();
		List<Migration> dir1 = dir.dir_table();

		// ldap retrieveUserAttributes = new ldap();
		// for(int i=0; i<dir1.size(); i++){
		// if (dir1.get(i).getDirector() == null ||
		// "unknown".equals(dir1.get(i).getDirector())){
		// dir1.get(i).setDirector_name("unknown");
		// }else{
		// Attributes dirInfo =
		// retrieveUserAttributes.getUserBasicAttributes(dir1.get(i).getDirector(),
		// retrieveUserAttributes.getLdapContext());
		// dir1.get(i).setDirector_name(dirInfo.get("description").toString());
		// }
		// }
		// for(int i=0; i<dir1.size(); i++){
		// if (dir1.get(i).getSvp() == null ||
		// "unknown".equals(dir1.get(i).getSvp())){
		// dir1.get(i).setSvp_name("unknown");
		// }else{
		// Attributes svpInfo =
		// retrieveUserAttributes.getUserBasicAttributes(dir1.get(i).getSvp(),
		// retrieveUserAttributes.getLdapContext());
		// dir1.get(i).setSvp_name(svpInfo.get("description").toString());
		// }
		// }
		model.addObject("dir_table", dir1);

		ViewDAOImpl svp = new ViewDAOImpl();
		List<Migration> svp1 = svp.svp_chart();
		JSONArray arr = new JSONArray();

		for (int i = 0; i < svp1.size(); i++) {
			JSONObject json = new JSONObject();
			json.put("svp", svp1.get(i).getSvp());
			json.put("committed", svp1.get(i).getCommitted());
			json.put("completed", svp1.get(i).getCompleted());
			json.put("uncommitted", svp1.get(i).getUncommitted());
			json.put("in_progress", svp1.get(i).getIn_progress());
			arr.put(json);
		}
		model.addObject("svp_chart", arr);
		return model;
	}

	@RequestMapping(value = "/groupView/{id}", method = RequestMethod.GET)
	public ModelAndView groupView(@PathVariable("id") String dirId,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView("group_view");
		LOGGER.info("Welcome to the view reports section");

		String userName = request.getHeader("AUTH_USER");
		// String userName = "dhsanghv";
		// System.out.println(userName);
		model.addObject("username", userName);

		ViewDAOImpl dir = new ViewDAOImpl();
		HashMap<String, String> dirpage = dir.dir_page(dirId);

		model.addObject("dirpage", dirpage);

		JSONObject jsonData = new JSONObject();
		for (int i = 0; i < dirpage.size(); i++) {
			jsonData.put("committed", dirpage.get("committed"));
			jsonData.put("uncommitted", dirpage.get("uncommitted"));
			jsonData.put("in_progress", dirpage.get("in_progress"));
			jsonData.put("completed", dirpage.get("completed"));
		}
		model.addObject("pieData2", jsonData);

		ViewDAOImpl offering = new ViewDAOImpl();
		List<Migration> offering1 = offering.offering_table(dirId);

		ldap retrieveUserAttributes = new ldap();

		Attributes userInfo = retrieveUserAttributes.getUserBasicAttributes(
				dirId, retrieveUserAttributes.getLdapContext());
		model.addObject("dirname", userInfo.get("description").toString());

		for (int i = 0; i < offering1.size(); i++) {
			if (offering1.get(i).getSvp() == null
					|| "unknown".equals(offering1.get(i).getSvp())) {
				offering1.get(i).setSvp_name("unknown");
			} else {
				Attributes svpInfo = retrieveUserAttributes
						.getUserBasicAttributes(offering1.get(i).getSvp(),
								retrieveUserAttributes.getLdapContext());
				offering1.get(i).setSvp_name(
						svpInfo.get("description").toString());
			}
		}
		model.addObject("offering_table", offering1);

		return model;
	}

	@RequestMapping(value = "/offeringView/{offering}", method = RequestMethod.GET)
	public ModelAndView offeringView(@PathVariable("offering") String offering,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView("offering_view");
		LOGGER.info("Welcome to the view reports section");

		String userName = request.getHeader("AUTH_USER");
		// String userName = "dhsanghv";
		// System.out.println(userName);
		model.addObject("username", userName);

		ViewDAOImpl off = new ViewDAOImpl();
		HashMap<String, String> offeringpage = off.offering_page(offering);
		model.addObject("offeringpage", offeringpage);

		JSONObject jsonData = new JSONObject();
		for (int i = 0; i < offeringpage.size(); i++) {
			jsonData.put("committed", offeringpage.get("committed"));
			jsonData.put("uncommitted", offeringpage.get("uncommitted"));
			jsonData.put("in_progress", offeringpage.get("in_progress"));
			jsonData.put("completed", offeringpage.get("completed"));
		}
		model.addObject("pieData3", jsonData);

		ViewDAOImpl apps = new ViewDAOImpl();
		List<Migration> apps1 = apps.app_table(offering);

		ldap retrieveUserAttributes = new ldap();

		for (int i = 0; i < apps1.size(); i++) {
			if (apps1.get(i).getDirector() == null
					|| "unknown".equals(apps1.get(i).getDirector())) {
				apps1.get(i).setDirector_name("unknown");
			} else {
				Attributes dirInfo = retrieveUserAttributes
						.getUserBasicAttributes(apps1.get(i).getDirector(),
								retrieveUserAttributes.getLdapContext());
				apps1.get(i).setDirector_name(
						dirInfo.get("description").toString());
			}
		}
		model.addObject("app_table", apps1);

		return model;
	}

	@RequestMapping(value = "/uncommittedApps", method = RequestMethod.GET)
	public ModelAndView uncommittedApps(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("uncommitted");
		LOGGER.info("Welcome to the view reports section");

		String userName = request.getHeader("AUTH_USER");
		// String userName = "dhsanghv";
		// System.out.println(userName);
		model.addObject("username", userName);

		ViewDAOImpl uncomm = new ViewDAOImpl();
		List<Migration> uncomm1 = uncomm.uncomm_table();

		ldap retrieveUserAttributes = new ldap();

		for (int i = 0; i < uncomm1.size(); i++) {

			if (uncomm1.get(i).getDirector() == null
					|| "unknown".equals(uncomm1.get(i).getDirector())) {
				uncomm1.get(i).setDirector_name("unknown");
			} else {
				Attributes dirInfo = retrieveUserAttributes
						.getUserBasicAttributes(uncomm1.get(i).getDirector(),
								retrieveUserAttributes.getLdapContext());
				uncomm1.get(i).setDirector_name(
						dirInfo.get("description").toString());
			}
		}
		model.addObject("uncomm_table", uncomm1);

		return model;
	}

	@RequestMapping(value = "/completedApps", method = RequestMethod.GET)
	public ModelAndView completedApps(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("completed");
		LOGGER.info("Welcome to the view reports section");

		String userName = request.getHeader("AUTH_USER");
		// String userName = "dhsanghv";
		// System.out.println(userName);
		model.addObject("username", userName);

		ViewDAOImpl completed = new ViewDAOImpl();
		List<Migration> completed1 = completed.completed_table();

		ldap retrieveUserAttributes = new ldap();

		for (int i = 0; i < completed1.size(); i++) {

			if (completed1.get(i).getDirector() == null
					|| "unknown".equals(completed1.get(i).getDirector())) {
				completed1.get(i).setDirector_name("unknown");
			} else {
				Attributes dirInfo = retrieveUserAttributes
						.getUserBasicAttributes(
								completed1.get(i).getDirector(),
								retrieveUserAttributes.getLdapContext());
				completed1.get(i).setDirector_name(
						dirInfo.get("description").toString());
			}
		}
		model.addObject("completed_table", completed1);

		return model;
	}

	@RequestMapping(value = "/committedApps", method = RequestMethod.GET)
	public ModelAndView committedApps(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("committed");
		LOGGER.info("Welcome to the view reports section");

		String userName = request.getHeader("AUTH_USER");
		// String userName = "dhsanghv";
		// System.out.println(userName);
		model.addObject("username", userName);

		ViewDAOImpl committed = new ViewDAOImpl();
		List<Migration> committed1 = committed.committed_table();

		ldap retrieveUserAttributes = new ldap();

		for (int i = 0; i < committed1.size(); i++) {

			if (committed1.get(i).getDirector() == null
					|| "unknown".equals(committed1.get(i).getDirector())) {
				committed1.get(i).setDirector_name("unknown");
			} else {
				Attributes dirInfo = retrieveUserAttributes
						.getUserBasicAttributes(
								committed1.get(i).getDirector(),
								retrieveUserAttributes.getLdapContext());
				committed1.get(i).setDirector_name(
						dirInfo.get("description").toString());
			}
		}
		model.addObject("committed_table", committed1);

		return model;
	}

	@RequestMapping(value = "/inProgressApps", method = RequestMethod.GET)
	public ModelAndView inProgressApps(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("in_progress");
		LOGGER.info("Welcome to the view reports section");

		String userName = request.getHeader("AUTH_USER");
		// String userName = "dhsanghv";
		// System.out.println(userName);
		model.addObject("username", userName);

		ViewDAOImpl progress = new ViewDAOImpl();
		List<Migration> progress1 = progress.progress_table();

		ldap retrieveUserAttributes = new ldap();

		for (int i = 0; i < progress1.size(); i++) {

			if (progress1.get(i).getDirector() == null
					|| "unknown".equals(progress1.get(i).getDirector())) {
				progress1.get(i).setDirector_name("unknown");
			} else {
				Attributes dirInfo = retrieveUserAttributes
						.getUserBasicAttributes(progress1.get(i).getDirector(),
								retrieveUserAttributes.getLdapContext());
				progress1.get(i).setDirector_name(
						dirInfo.get("description").toString());
			}
		}
		model.addObject("progress_table", progress1);

		return model;
	}

	@RequestMapping(value = "/decommApps", method = RequestMethod.GET)
	public ModelAndView decommApps(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("decomm");
		LOGGER.info("Welcome to the view reports section");

		String userName = request.getHeader("AUTH_USER");
		// String userName = "dhsanghv";
		// System.out.println(userName);
		model.addObject("username", userName);

		return model;
	}

	@RequestMapping(value = "/eolApps", method = RequestMethod.GET)
	public ModelAndView eolApps(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("eol");
		LOGGER.info("Welcome to the view reports section");

		String userName = request.getHeader("AUTH_USER");
		// String userName = "dhsanghv";
		// System.out.println(userName);
		model.addObject("username", userName);

		return model;
	}

	@RequestMapping(value = "/stayApps", method = RequestMethod.GET)
	public ModelAndView stayApps(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("stay");
		LOGGER.info("Welcome to the view reports section");

		String userName = request.getHeader("AUTH_USER");
		// String userName = "dhsanghv";
		// System.out.println(userName);
		model.addObject("username", userName);

		return model;
	}

	// using this to make an ajax call for listing apps
	@ResponseBody
	@RequestMapping(value = "/get-app", method = RequestMethod.POST)
	public List<App> appList(
			@RequestParam(value = "functionalGroup", required = true) String functionalGroup,
			HttpServletResponse httpServletResponse) throws IOException {

		LOGGER.info("Welcome to the view reports section " + functionalGroup);
		List<App> app1 = null;
		try {
			AppDAOImpl app = new AppDAOImpl();
			app1 = app.list(functionalGroup);
		} catch (Exception e) {
			LOGGER.error("Error: ", e);
		}
		// LOGGER.info("size is: " + app1.size());

		return app1;
	}

	@ResponseBody
	@RequestMapping(value = "/get-app-info")
	public Migration appInfo(
			@RequestParam(value = "appId", required = true) String appId,
			HttpServletResponse httpServletResponse) throws IOException {

		LOGGER.info("Welcome to the submit reports section " + appId);
		Migration app2 = null;
		try {
			MigrationDAOImpl app = new MigrationDAOImpl();
			app2 = app.getPlan(appId);
		} catch (Exception e) {
			LOGGER.error("Error: ", e);
		}

		return app2;
	}

	// private MigrationDAO migrationDAO;

	@RequestMapping(value = "/savePlan", method = RequestMethod.POST)
	public ModelAndView savePlan(@ModelAttribute Migration migration,
			HttpServletRequest request) {
		MigrationDAOImpl mig = new MigrationDAOImpl();
		// System.out.println(migration.getSvp());
		// System.out.println(migration.getApp_id());
		mig.insertOrUpdatePlan(migration);

		return new ModelAndView("redirect:/edit");

	}

	@RequestMapping(value = "/dirCommittedView/{dir}", method = RequestMethod.GET)
	public ModelAndView directorView(@PathVariable("dir") String dir,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView("dir_comm");
		LOGGER.info("Welcome to the view reports section");

		String userName = request.getHeader("AUTH_USER");
		// String userName = "dhsanghv";
		// System.out.println(userName);
		model.addObject("username", userName);

		ViewDAOImpl dircomm = new ViewDAOImpl();
		List<Migration> dircomm1 = dircomm.dircomm_table(dir);

		ldap retrieveUserAttributes = new ldap();

		Attributes userInfo = retrieveUserAttributes.getUserBasicAttributes(
				dir, retrieveUserAttributes.getLdapContext());
		model.addObject("dir_name", userInfo.get("description").toString());

		for (int i = 0; i < dircomm1.size(); i++) {
			if (dircomm1.get(i).getSvp() == null
					|| "unknown".equals(dircomm1.get(i).getSvp())) {
				dircomm1.get(i).setSvp_name("unknown");
			} else {
				Attributes svpInfo = retrieveUserAttributes
						.getUserBasicAttributes(dircomm1.get(i).getSvp(),
								retrieveUserAttributes.getLdapContext());
				dircomm1.get(i).setSvp_name(
						svpInfo.get("description").toString());
			}
		}
		model.addObject("dircomm_table", dircomm1);

		return model;
	}

	@RequestMapping(value = "/dirUncommittedView/{dir}", method = RequestMethod.GET)
	public ModelAndView dirUncommittedView(@PathVariable("dir") String dir,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView("dir_uncomm");
		LOGGER.info("Welcome to the view reports section");

		String userName = request.getHeader("AUTH_USER");
		// String userName = "dhsanghv";
		// System.out.println(userName);
		model.addObject("username", userName);

		ViewDAOImpl diruncomm = new ViewDAOImpl();
		List<Migration> diruncomm1 = diruncomm.diruncomm_table(dir);

		ldap retrieveUserAttributes = new ldap();

		Attributes userInfo = retrieveUserAttributes.getUserBasicAttributes(
				dir, retrieveUserAttributes.getLdapContext());
		model.addObject("dir_name", userInfo.get("description").toString());

		for (int i = 0; i < diruncomm1.size(); i++) {
			if (diruncomm1.get(i).getSvp() == null
					|| "unknown".equals(diruncomm1.get(i).getSvp())) {
				diruncomm1.get(i).setSvp_name("unknown");
			} else {
				Attributes svpInfo = retrieveUserAttributes
						.getUserBasicAttributes(diruncomm1.get(i).getSvp(),
								retrieveUserAttributes.getLdapContext());
				diruncomm1.get(i).setSvp_name(
						svpInfo.get("description").toString());
			}
		}
		model.addObject("diruncomm_table", diruncomm1);

		return model;
	}

	@RequestMapping(value = "/dirCompletedView/{dir}", method = RequestMethod.GET)
	public ModelAndView dirCompletedView(@PathVariable("dir") String dir,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView("dir_comp");
		LOGGER.info("Welcome to the view reports section");

		String userName = request.getHeader("AUTH_USER");
		// String userName = "dhsanghv";
		// System.out.println(userName);
		model.addObject("username", userName);

		ViewDAOImpl dircomp = new ViewDAOImpl();
		List<Migration> dircomp1 = dircomp.dircomp_table(dir);

		ldap retrieveUserAttributes = new ldap();

		Attributes userInfo = retrieveUserAttributes.getUserBasicAttributes(
				dir, retrieveUserAttributes.getLdapContext());
		model.addObject("dir_name", userInfo.get("description").toString());

		for (int i = 0; i < dircomp1.size(); i++) {
			if (dircomp1.get(i).getSvp() == null
					|| "unknown".equals(dircomp1.get(i).getSvp())) {
				dircomp1.get(i).setSvp_name("unknown");
			} else {
				Attributes svpInfo = retrieveUserAttributes
						.getUserBasicAttributes(dircomp1.get(i).getSvp(),
								retrieveUserAttributes.getLdapContext());
				dircomp1.get(i).setSvp_name(
						svpInfo.get("description").toString());
			}
		}
		model.addObject("dircomp_table", dircomp1);

		return model;
	}

	@RequestMapping(value = "/dirProgressView/{dir}", method = RequestMethod.GET)
	public ModelAndView dirProgressView(@PathVariable("dir") String dir,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView("dir_prog");
		LOGGER.info("Welcome to the view reports section");

		String userName = request.getHeader("AUTH_USER");
		// String userName = "dhsanghv";
		// System.out.println(userName);
		model.addObject("username", userName);

		ViewDAOImpl dirprog = new ViewDAOImpl();
		List<Migration> dirprog1 = dirprog.dirprog_table(dir);

		ldap retrieveUserAttributes = new ldap();

		Attributes userInfo = retrieveUserAttributes.getUserBasicAttributes(
				dir, retrieveUserAttributes.getLdapContext());
		model.addObject("dir_name", userInfo.get("description").toString());

		for (int i = 0; i < dirprog1.size(); i++) {
			if (dirprog1.get(i).getSvp() == null
					|| "unknown".equals(dirprog1.get(i).getSvp())) {
				dirprog1.get(i).setSvp_name("unknown");
			} else {
				Attributes svpInfo = retrieveUserAttributes
						.getUserBasicAttributes(dirprog1.get(i).getSvp(),
								retrieveUserAttributes.getLdapContext());
				dirprog1.get(i).setSvp_name(
						svpInfo.get("description").toString());
			}
		}
		model.addObject("dirprog_table", dirprog1);

		return model;
	}

	// @RequestMapping(value = "/offeringCommittedView/{off}", method =
	// RequestMethod.GET)
	// public ModelAndView offeringCommView(@PathVariable("off") String off,
	// String dir, HttpServletRequest request) {
	// ModelAndView model = new ModelAndView("off_comm");
	// LOGGER.info("Welcome to the view reports section");
	//
	// String userName = request.getHeader("AUTH_USER");
	// // String userName = "dhsanghv";
	// // System.out.println(userName);
	// model.addObject("username", userName);
	//
	// ViewDAOImpl offcomm = new ViewDAOImpl();
	// List<Migration> offcomm1 = offcomm.offcomm_table(off, dir);
	//
	// ldap retrieveUserAttributes = new ldap();
	//
	// Attributes userInfo = retrieveUserAttributes.getUserBasicAttributes(dir,
	// retrieveUserAttributes.getLdapContext());
	// model.addObject("dir_name", userInfo.get("description").toString());
	//
	// for(int i=0; i<offcomm1.size(); i++){
	// if (offcomm1.get(i).getSvp() == null ||
	// "unknown".equals(offcomm1.get(i).getSvp())){
	// offcomm1.get(i).setSvp_name("unknown");
	// }else{
	// Attributes svpInfo =
	// retrieveUserAttributes.getUserBasicAttributes(offcomm1.get(i).getSvp(),
	// retrieveUserAttributes.getLdapContext());
	// offcomm1.get(i).setSvp_name(svpInfo.get("description").toString());
	// }
	// }
	// model.addObject("offcomm_table", offcomm1);
	//
	// return model;
	// }

}
