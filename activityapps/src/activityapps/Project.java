package activityapps;

import java.util.ArrayList;

public class Project {
	private String name;
	private ArrayList<Event> events;
	private ArrayList<Staff> staffs;
	private ArrayList<Item> globalItems;
	private Staff charge;
	
	
	public Project(String name){
		this.name = name;
		this.events = new ArrayList<Event>();
		this.staffs = new ArrayList<Staff>();
		this.globalItems = new ArrayList<Item>();
		this.charge = null;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Staff getCharge() {
		return charge;
	}


	public void setCharge(Staff charge) {
		this.charge = charge;
	}
	
	
}
