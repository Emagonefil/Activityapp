package activityapps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Staff{
	
	public class Task {
		public String name;
		public String description; // 因为存储数据关系，暂时不支持换行
		public String startDate;
		public String endDate;
		public String toString(){
			return name + "(" +
					(startDate==null? startDate : "?") +
					" to " +
					(endDate==null? endDate : "?") +
					(description==null? "?" : ", "+description+")");
		}
	}
	private String name;
	private String gender;
	private String department;
	private String position;
	private ArrayList<Task> tasks;
	
	public Staff(String name, String gender, String department, String position) {
		super();
		this.name = name;
		this.gender = gender;
		this.department = department;
		this.position = position;
		this.tasks = new ArrayList<Task>();
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

	public static void reorder(ArrayList<Staff> staffs) {
		Comparator c = new Comparator<Staff>(){
			@Override
			public int compare(Staff s1, Staff s2) {
				int departmentCompare = s1.department.compareTo(s2.getDepartment());
				if (departmentCompare>0) return 3;
				else if (departmentCompare<0) return -3;
				else {
					int positionCompare = s1.position.compareTo(s2.getPosition());
					if (positionCompare>0) return 2;
					else if (positionCompare<0) return -2;
					else if (s1.name.equals(s2.getName()) &&
							s1.gender.equals(s2.getGender())) {
						return 0;
					} else return 1;
				}
			}
		};
		staffs.sort(c);
	}


	public String[] getTaskStrList(){
		String[] taskList = new String[tasks.size()];
		for (int i=0; i<tasks.size(); i++) 
			taskList[i] = tasks.get(i).toString();
		return taskList;
	}
	
	public ArrayList<Task> getTasks(){
		return tasks;
	}
	
	public void addTask(String taskName, String startDate, String endDate, String description) {
		Task t = new Task();
		t.name = taskName;
		t.startDate = startDate;
		t.endDate = endDate;
		t.description = description;
		tasks.add(t);
	}
	
	public String toString(){
		return name + "\t(" +
				department + ", " +
				position + ", " +
				gender + ")";
	}

	
}
