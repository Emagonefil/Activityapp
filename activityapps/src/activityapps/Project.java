package activityapps;

import java.util.ArrayList;

public class Project {
	private String name;
	private Staff charge;
	protected ArrayList<Event> events;
	private ArrayList<Staff> staffs;
	protected ArrayList<Item> globalItems;
	
	private String intro;
	
	private Budget budget;
	
	
	public Project(String name){
		this.name = name;
		this.events = new ArrayList<Event>();
		this.staffs = new ArrayList<Staff>();
		this.globalItems = new ArrayList<Item>();
		this.charge = null;
		
		this.budget = null;
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
		if (!staffs.contains(charge)) staffs.add(charge);
	}


	public Budget getBudget() {
		return budget;
	}
	
	
	
	public int addGlobalItem(Item item){
		if (budget.addItem(item)!=0) {
			return -1;
		}
		globalItems.add(item);
		return 0;
	}
	
	public Item getGlobalItem(int i){
		return globalItems.get(i);
	}
	
	public int getGlobalItemSize(){
		return globalItems.size();
	}
	
	public int removeGlobalItem(int i){
		if (i<0 || i>globalItems.size()) return -1;
		globalItems.remove(i);
		return 0;
	}
	
	
	
	public String getIntro() {
		return intro;
	}


	public void setIntro(String intro) {
		this.intro = intro;
	}


	public String toString(){
		return "Project    :    " + this.name + " (" +
				(charge == null? "NO Manager, " : charge.getName()) + 
				(budget == null? "NO Budget" : 
					(budget.getAvailable())+"/"+budget.getTotal()) +
				")";
	}
}
