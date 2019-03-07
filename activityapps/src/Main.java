import java.util.ArrayList;

import activityapps.CMDtester;
import activityapps.Project;
import activityapps.Staff;
import activityapps.Utility;

public class Main {
	
	public static void main(String[] args){
		// 1 Load Local Projects data using Utility
		
		
		// 2 Turn on user interface
		ArrayList<Project> projects = new ArrayList<Project>();
		ArrayList<Staff> staffs = Utility.loadStaffs();
		CMDtester.main(projects, staffs);
	}
	
}
