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

public class Utility {
	public void saveStaffs(ArrayList<Staff>staffs) throws IOException {
        try {
			File writename = new File(".\\src\\output.txt"); 
			writename.createNewFile(); 
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			for (Staff staff:staffs) {
				out.write(staff.getName()+"\r\n"); 
				out.write(staff.getGender()+"\r\n"); 
				out.write(staff.getGender()+"\r\n"); 
				out.write(staff.getPosition()+"\r\n"); 
			}

			out.close(); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
	}
	
	public void loadStaffs(ArrayList<Staff>staffs) throws IOException{
		String pathname = ".\\src\\output.txt"; 
		File filename = new File(pathname); 
		InputStreamReader reader = new InputStreamReader(
				new FileInputStream(filename)); 
		BufferedReader br = new BufferedReader(reader); 
		String line = "";
		
		br.close();
	}

}
