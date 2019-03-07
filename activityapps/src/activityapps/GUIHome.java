package activityapps;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Canvas;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.Font;

public class GUIHome {

	private JFrame frame;
	private ArrayList<Project> projects;
	private ArrayList<Staff> staffs;
	private JLabel iconProject;
	private JLabel iconCalendar;
	private JLabel iconSettings;
	private JLabel iconAddProject;
	private JLabel titleProject;
	private JList list;
	private JLabel background;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<Project> projects = new ArrayList<Project>();
					ArrayList<Staff> staffs = Utility.loadStaffs();
					Project p = new Project("Singing Contest");
					projects.add(p);
					GUIHome window = new GUIHome(projects, staffs);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIHome(ArrayList<Project> projects, ArrayList<Staff> staffs) {
		this.projects = projects;
		this.staffs = staffs;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		iconProject = new JLabel("");
		iconProject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				iconProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\projectIconBright.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				iconProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\projectIcon.png"));
			}
		});
		iconProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\projectIcon.png"));
		iconProject.setBounds(15, 210, 100, 100);
		frame.getContentPane().add(iconProject);
		
		iconCalendar = new JLabel("");
		iconCalendar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				iconCalendar.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\calendarIconBright.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				iconCalendar.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\calendarIcon.png"));
			}
		});
		iconCalendar.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\calendarIcon.png"));
		iconCalendar.setBounds(14, 360, 100, 100);
		frame.getContentPane().add(iconCalendar);
		
		iconSettings = new JLabel("");
		iconSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				iconSettings.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\SettingsIconBright.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				iconSettings.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\SettingsIcon.png"));
			}
		});
		iconSettings.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\SettingsIcon.png"));
		iconSettings.setBounds(17, 785, 100, 100);
		frame.getContentPane().add(iconSettings);
		
		iconAddProject = new JLabel("");
		iconAddProject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				iconAddProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\addIconBright.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				iconAddProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\addIcon.png"));
			}
		});
		iconAddProject.setBounds(239, 736, 312, 80);
		frame.getContentPane().add(iconAddProject);
		iconAddProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\addIcon.png"));
		
		titleProject = new JLabel("");
		titleProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\Projects.png"));
		titleProject.setBounds(186, 169, 1035, 200);
		frame.getContentPane().add(titleProject);
		
		list = new JList();
		list.setFont(new Font("Arial", Font.PLAIN, 26));
		list.setListData(projects.toArray());
		
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(239, 321, 929, 495);
		frame.getContentPane().add(scrollPane);
		
		background = new JLabel("");
		background.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\background.png"));
		background.setBounds(0, 0, 1280, 960);
		frame.getContentPane().add(background);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
		frame.setBackground(Color.WHITE);
		frame.setBounds(0, 0, 1280, 960);
	}
}
