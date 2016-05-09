package com.bplustree.database;

public class Department {
	private int Id;
	private String Department_name;
	private String mgr_name;
	private int number_of_employees;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getDepartment_name() {
		return Department_name;
	}
	public void setDepartment_name(String department_name) {
		Department_name = department_name;
	}
	public String getMgr_name() {
		return mgr_name;
	}
	public void setMgr_name(String mgr_name) {
		this.mgr_name = mgr_name;
	}
	public int getNumber_of_employees() {
		return number_of_employees;
	}
	public void setNumber_of_employees(int number_of_employees) {
		this.number_of_employees = number_of_employees;
	}
	
}
