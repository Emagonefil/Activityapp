package activityapps;

import java.util.ArrayList;
import java.util.Date;

public class Event {
	private String name;
	private Date date;
	private Staff charge;
	private Scene scene;
	protected Budget budget;
	
	private ArrayList<Item> eventItems;
	
	public Event(String name){
		super();
		this.name = name;
	}

	public Event(String name, Date date, Staff charge) {
		super();
		this.name = name;
		this.date = date;
		this.charge = charge;
		this.scene = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Staff getCharge() {
		return charge;
	}

	public void setCharge(Staff charge) {
		this.charge = charge;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer(this.name + "  ");
		if (this.date==null) sb.append("NO DATE"); else sb.append(date);
		sb.append("  ");
		if (this.charge!=null) sb.append(this.charge.getName());
		return sb.toString();
	}
	
	
}
