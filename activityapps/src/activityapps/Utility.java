package activityapps;

import java.util.ArrayList;

import com.sun.xml.internal.ws.util.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import activityapps.Staff.Task;

public class Utility {
	public static void saveStaffs(ArrayList<Staff>staffs) {
        try {
			File writename = new File("staffs.txt"); 
			writename.createNewFile(); 
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			for (Staff staff:staffs) {
				out.write(staff.getName()+"\r\n"); 
				out.write(staff.getGender()+"\r\n"); 
				out.write(staff.getDepartment()+"\r\n"); 
				out.write(staff.getPosition()+"\r\n"); 		
				ArrayList<Task> tasks = staff.getTasks();
				out.write(tasks.size()+"\r\n");
				for (Task task : tasks) {
					out.write(task.name+"\r\n");
					out.write(task.startDate+"\r\n");
					out.write(task.endDate+"\r\n");
					out.write(task.description+"\r\n");
				}
			}
			out.flush();
			out.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	public static ArrayList<Staff> loadStaffs(){
		ArrayList<Staff> staffs = new ArrayList<Staff>();
		String pathname = "staffs.txt"; 
		File filename = new File(pathname); 
		InputStreamReader reader;
		try {
			reader = new InputStreamReader(
					new FileInputStream(filename)); 
		} catch (FileNotFoundException e) {
			return staffs;
		}
		BufferedReader br = new BufferedReader(reader); 
		String input;
		try{
			while ((input=br.readLine())!=null){
				String name = input;
				String gender = br.readLine();
				String department = br.readLine();
				String position = br.readLine();
				Staff s = new Staff(name, gender, department, position);		
			
				int taskCount = Integer.parseInt(br.readLine());
				for (int i=0; i<taskCount; i++) {
					String taskName = br.readLine();
					String startDate = br.readLine();
					String endDate = br.readLine();
					String description = br.readLine();
					s.addTask(taskName, startDate, endDate, description);
				}
				staffs.add(s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return staffs;
	}

}
