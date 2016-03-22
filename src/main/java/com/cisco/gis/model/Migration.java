package com.cisco.gis.model;

public class Migration {
	private String user_id;
	private String app_id;
	private String app_name;
	private String func_grp;
	private String svp;
	private String director;
	private String status;
	private String eman_priority;
	private String mig_date;
	private String mig_quarter;
	private String[] mig_pocs;
	private String status_count;
	private String committed;
	private String completed;
	private String uncommitted;
	private String in_progress;
	private String decommissioned;
	private String cutover_type;
	private String migration_lead;
	private String comments;

	public String getMigration_lead() {
		return migration_lead;
	}

	public void setMigration_lead(String migration_lead) {
		this.migration_lead = migration_lead;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCutover_type() {
		return cutover_type;
	}

	public void setCutover_type(String cutover_type) {
		this.cutover_type = cutover_type;
	}

	public String getDecommissioned() {
		return decommissioned;
	}

	public void setDecommissioned(String decommissioned) {
		this.decommissioned = decommissioned;
	}

	public String getStay() {
		return stay;
	}

	public void setStay(String stay) {
		this.stay = stay;
	}

	public String getEol() {
		return eol;
	}

	public void setEol(String eol) {
		this.eol = eol;
	}

	private String stay;
	private String eol;
	private String svp_name;
	private String director_name;

	public String getDirector_name() {
		return director_name;
	}

	public void setDirector_name(String director_name) {
		this.director_name = director_name;
	}

	public String getSvp_name() {
		return svp_name;
	}

	public void setSvp_name(String svp_name) {
		this.svp_name = svp_name;
	}

	public String getUncommitted() {
		return uncommitted;
	}

	public void setUncommitted(String uncommitted) {
		this.uncommitted = uncommitted;
	}

	public String getIn_progress() {
		return in_progress;
	}

	public void setIn_progress(String in_progress) {
		this.in_progress = in_progress;
	}

	public String getCommitted() {
		return committed;
	}

	public void setCommitted(String committed) {
		this.committed = committed;
	}

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}

	public String getStatus_count() {
		return status_count;
	}

	public void setStatus_count(String status_count) {
		this.status_count = status_count;
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

	public String getFunc_grp() {
		return func_grp;
	}

	public void setFunc_grp(String func_grp) {
		this.func_grp = func_grp;
	}

	public String getSvp() {
		return svp;
	}

	public void setSvp(String svp) {
		this.svp = svp;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEman_priority() {
		return eman_priority;
	}

	public void setEman_priority(String eman_priority) {
		this.eman_priority = eman_priority;
	}

	public String getMig_date() {
		return mig_date;
	}

	public void setMig_date(String mig_date) {
		this.mig_date = mig_date;
	}

	public String getMig_quarter() {
		return mig_quarter;
	}

	public void setMig_quarter(String mig_quarter) {
		this.mig_quarter = mig_quarter;
	}

	public String[] getMig_pocs() {
		return mig_pocs;
	}

	public void setMig_pocs(String[] mig_pocs) {
		this.mig_pocs = mig_pocs;
	}

}
