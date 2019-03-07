package activityapps;

import java.util.ArrayList;
import java.util.Scanner;

public class CMDtester {
	
	private static final Scanner scan = new Scanner(System.in);

	public static int main(ArrayList<Project> projects, ArrayList<Staff> staffs) {
		
		System.out.println("UoB Chinese Society");
		System.out.println("Activity App �����������԰�");
		
		System.out.println("\n Data Loaded ��������������� \n");
		
		Integer exitNumber = null;
		while (exitNumber==null) {
			System.out.println("\n========Main===========\nCurrent Projects ������Ŀ��");
			for (int i=0; i<projects.size(); i++) {
				System.out.println(i+"\t: "+projects.get(i));
			}
			if (projects.size()==0) System.out.println("  No Project ��ʱû����Ŀ");
			
			System.out.println("\nUser Input �û�����: ");
			System.out.println("(Input project number to choose a project;  ��a�� to create a project;)");
			System.out.println("(Input 's' to manage the staff;                   Input ��q�� to quit;)");
			System.out.println("(������Ŀ������ѡ��һ����Ŀ�� ����  a ������Ŀ�� ����  q �˳�������  s ��������Ա)");
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
		System.out.print("Project Name ��Ŀ����: ");
		String projectName = scan.nextLine();
		Project p = new Project(projectName);
		return p;
	}

	private static int staffMenu(ArrayList<Staff> staffs){
		Integer exitNumber = null;
		while (exitNumber == null) {
			System.out.println("\n========Staff List========");
			System.out.println("Staff List ������Ա����: ");
			if (staffs.size()==0) System.out.println(" No Staff ��ʱû�еǼ���Ա");
			else
				for (int i=0; i<staffs.size(); i++) {
					System.out.println(i+"\t"+staffs.get(i));
				}
			System.out.println("\nInput the staff number to pick, input 'q' to go back");
			System.out.println("input 'a' to add staff. ������Ա������ѡ������q ���أ�����a�����Ա");
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
				System.out.println("TASK ����: ");
				for (int i=0; i<tasks.length; i++)
					System.out.println(i + "\t" + tasks[i]);
			}
			System.out.println("\nUser Input �û�����");
			System.out.println("1. reset staff info  ������Ա��Ϣ");
			System.out.println("2. delete staff      ɾ����Ա��Ϣ");
			System.out.println("3. save and go back  ���沢����");
			
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
		System.out.println("���빤����Ա������ѡ������q������");
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
			
			if (project.getCharge()==null) System.out.println("(No Manager ���޸�����)");
			else System.out.println("\nProject Manager ��Ŀ����: "+ project.getCharge().getName());
			
			if (project.getBudget()==null) System.out.println("(No Budget Info ����Ԥ����Ϣ)");
			else System.out.println("\n"+project.getBudget());
			
			System.out.println("\nUser Input �û�����: ");
			System.out.println("1 - Enter Project          ������Ŀ");
			System.out.println("2 - Change Project Name    �޸���Ŀ����");
			System.out.println("3 - Change Project Manager �޸���Ŀ������");
			System.out.println("4 - Delete Project         ɾ����Ŀ");
			System.out.println("5 - Go Back                ����");
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
				System.out.println("Invalid Input, please use 1,2,3,4. ������1-4֮�������");
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
