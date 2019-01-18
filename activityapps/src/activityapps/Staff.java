package activityapps;

import java.util.ArrayList;

public class Staff {
	private String name;
	private String gender;
	private String department;
	private String position;
	private ArrayList<String> tasks;
	
	public Staff(String name, String gender, String department, String position) {
		super();
		this.name = name;
		this.gender = gender;
		this.department = department;
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
