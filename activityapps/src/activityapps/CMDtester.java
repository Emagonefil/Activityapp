package activityapps;

import java.util.ArrayList;
import java.util.Scanner;

public class CMDtester {
	
	private static final Scanner scan = new Scanner(System.in);

	public static int main(ArrayList<Project> projects, ArrayList<Staff> staffs) {
		
		System.out.println("UoB Chinese Society");
		System.out.println("Activity App 活动管理软件测试版");
		
		System.out.println("\n Data Loaded 本地数据载入完毕 \n");
		
		Integer exitNumber = null;
		while (exitNumber==null) {
			System.out.println("\n========Main===========\nCurrent Projects 现有项目：");
			for (int i=0; i<projects.size(); i++) {
				System.out.println(i+"\t: "+projects.get(i));
			}
			if (projects.size()==0) System.out.println("  No Project 暂时没有项目");
			
			System.out.println("\nUser Input 用户操作: ");
			System.out.println("(Input project number to choose a project;  ‘a’ to create a project;)");
			System.out.println("(Input 's' to manage the staff;                   Input ‘q’ to quit;)");
			System.out.println("(输入项目代号来选择一个项目； 输入  a 创建项目； 输入  q 退出；输入  s 管理工作人员)");
			System.out.print("(Main)INPUT: ");
			String input = scan.nextLine();
			
			if (input.equals("a")) {
				Project p = createProject();
				projects.add(p);
			} else
			if (input.equals("q")) {
				exitNumber = 0;
			} else
			if (input.equals("s")) {
				int e = staffMenu(staffs);
			} else {
				int projectIndex = -1;
				boolean validProjectIndex = true;
				try {
					projectIndex = Integer.parseInt(input);
				} catch (NumberFormatException e){
					validProjectIndex = false;
				}
				if (projectIndex<0 || projectIndex>= projects.size()) validProjectIndex = false;
				
				if (validProjectIndex) {
					int e = projectManage(projects.get(projectIndex), staffs);
					if (e==-1) {
						projects.remove(projectIndex);
					}
				} else
					System.out.println("Incorrect input format, please redo\n");
			}
		}
		
		return exitNumber;
	}

	private static Project createProject() {
		System.out.println("\n(remember you can always change it later)");
		System.out.print("Project Name 项目名称: ");
		String projectName = scan.nextLine();
		Project p = new Project(projectName);
		return p;
	}

	private static int staffMenu(ArrayList<Staff> staffs){
		Integer exitNumber = null;
		while (exitNumber == null) {
			System.out.println("\n========Staff List========");
			System.out.println("Staff List 工作人员名单: ");
			if (staffs.size()==0) System.out.println(" No Staff 暂时没有登记人员");
			else
				for (int i=0; i<staffs.size(); i++) {
					System.out.println(i+"\t"+staffs.get(i));
				}
			System.out.println("\nInput the staff number to pick, input 'q' to go back");
			System.out.println("input 'a' to add staff. 输入人员代号来选择，输入q 返回，输入a添加人员");
			String input = scan.nextLine();
			if (input.equals("q")) exitNumber = 0;
			else if (input.equals("a")) {
				Staff staff = createStaff();
				if (staff!=null) {
					staffs.add(staff);
					Staff.reorder(staffs);
					Utility.saveStaffs(staffs);
				}
			} else {
				int index = -1;
				boolean validIndex = true;
				try {
					index = Integer.parseInt(input);
				} catch (NumberFormatException e){
					validIndex = false;
				}
				if (index<0 || index>= staffs.size()) validIndex = false;
				if (validIndex) {
					int e = staffManage(staffs.get(index));
					if (e==-1) {
						staffs.remove(index);
					}
					Utility.saveStaffs(staffs);
				} else
					System.out.println("Incorrect input format\n");
			}
		}
		return exitNumber;
	}
	
	private static int staffManage(Staff staff){
		Integer exitNumber = null;
		while (exitNumber == null) {
			System.out.println("\n======="+staff.getName()+"============");
			System.out.println(staff);
			String[] tasks = staff.getTaskStrList();
			if (tasks.length>0) {
				System.out.println("TASK 任务: ");
				for (int i=0; i<tasks.length; i++)
					System.out.println(i + "\t" + tasks[i]);
			}
			System.out.println("\nUser Input 用户操作");
			System.out.println("1. reset staff info  重设人员信息");
			System.out.println("2. delete staff      删除人员信息");
			System.out.println("3. save and go back  保存并返回");
			
			System.out.print("(Staff "+staff.getName()+") INPUT: ");
			String input = scan.nextLine();
			
			if (input.equals("1")) {
				Staff s2 = createStaff();
				staff.setName(s2.getName());
				staff.setDepartment(s2.getDepartment());
				staff.setPosition(s2.getPosition());
				staff.setGender(s2.getGender());
			} else if (input.equals("2")) {
				exitNumber = -1;
			} else if (input.equals("3")) {
				exitNumber = 0;
			} else {
				System.out.println("Incorrect Input format");
			}
		}
		return exitNumber;
	}
	
	private static Staff createStaff(){
		System.out.println("\n-------Staff Info--------");
		System.out.print("NAME       : ");
		String name = scan.nextLine();
		System.out.print("GENDER     : ");
		String gender = scan.nextLine();
		System.out.print("DEPARTMENT : ");
		String depart = scan.nextLine();
		System.out.print("POSITION   : ");
		String position = scan.nextLine();
		Staff staff = new Staff(name, gender, depart, position);
		System.out.println("Staff "+name+" created");
		return staff;
	}
	
	private static Staff chooseStaff(ArrayList<Staff> staffs){
		Staff staff = null;
		System.out.println("========staff list=========");
		for (int i=0; i<staffs.size(); i++) {
			System.out.println(i+"\t"+staffs.get(i));
		}
		System.out.println("\nInput the staff number to pick, input 'q' to go back");
		System.out.println("输入工作人员代号来选择，输入q来返回");
		String input = scan.nextLine();
		if (input.equals("q")) return null;
		int index = -1;
		boolean validIndex = true;
		try {
			index = Integer.parseInt(input);
		} catch (NumberFormatException e){
			validIndex = false;
		}
		if (index<0 || index>= staffs.size()) validIndex = false;
		
		if (validIndex) {
			staff = staffs.get(index);
		} else
			System.out.println("Incorrect input format\n");
		return staff;
	}
	
	private static int projectManage(Project project, ArrayList<Staff> staffs) {
		Integer exitNumber = null;
		while (exitNumber==null) {
			System.out.println("\n\nProject "+project.getName());
			
			if (project.getCharge()==null) System.out.println("(No Manager 暂无负责人)");
			else System.out.println("\nProject Manager 项目负责: "+ project.getCharge().getName());
			
			if (project.getBudget()==null) System.out.println("(No Budget Info 暂无预算信息)");
			else System.out.println("\n"+project.getBudget());
			
			System.out.println("\nUser Input 用户操作: ");
			System.out.println("1 - Enter Project          进入项目");
			System.out.println("2 - Change Project Name    修改项目名称");
			System.out.println("3 - Change Project Manager 修改项目负责人");
			System.out.println("4 - Delete Project         删除项目");
			System.out.println("5 - Go Back                返回");
			System.out.print("\n("+project.getName()+")INPUT:");
			String input = scan.nextLine();
			
			if (input.equals("1")) {
				int e = projectMain(project, staffs);
			} else if (input.equals("2")) {
				System.out.print("\n("+project.getName()+") New Name: ");
				project.setName(scan.nextLine());
			} else if (input.equals("3")) {
				Staff pm = chooseStaff(staffs);
				if (pm!=null) project.setCharge(pm);
			} else if (input.equals("4")) {
				exitNumber = -1; // Delete Project
			} else if (input.equals("5")) {
				exitNumber = 0;
			} else {
				System.out.println("Invalid Input, please use 1,2,3,4. 请输入1-4之间的数字");
			}
		}
		return exitNumber;
	}
	
	private static int projectMain(Project project, ArrayList<Staff> staffs){
		Integer exitNumber = null;
		while (exitNumber == null) {
			System.out.println("\n\n========Project "+project.getName()+"===========");
			System.out.println();
		}
		return exitNumber;
	}

}
