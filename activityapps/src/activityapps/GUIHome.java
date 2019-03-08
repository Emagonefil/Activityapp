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
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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
	private JLabel iconStaff;
	private JTextField textField;
	private JPanel panelAddProject;
	private JButton btnSubmit;
	private JButton btnCancel;
	private JPanel panelButtons;
	private JLabel iconDelProject;

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

	public void newProject(String name){
		Project p = new Project(name);
		projects.add(p);
	}
	
	public void delProject(int pIndex){
		projects.remove(pIndex);
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
		iconProject.setBounds(15, 150, 100, 100);
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
		iconCalendar.setBounds(14, 310, 100, 100);
		frame.getContentPane().add(iconCalendar);
		
		iconStaff = new JLabel("");
		iconStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				iconStaff.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\staffIconBright.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				iconStaff.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\staffIcon.png"));
			}
		});
		iconStaff.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\staffIcon.png"));
		iconStaff.setBounds(15, 470, 100, 100);
		frame.getContentPane().add(iconStaff);
		
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
		iconSettings.setBounds(14, 785, 100, 100);
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
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panelAddProject.show();
			}
		});
		iconAddProject.setBounds(239, 736, 80, 80);
		frame.getContentPane().add(iconAddProject);
		iconAddProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\addIcon.png"));
		
		iconDelProject = new JLabel("");
		iconDelProject.setBounds(1100, 736, 80, 80);
		iconDelProject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (list.isSelectionEmpty()) return;
				iconDelProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\delIconBright.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (list.isSelectionEmpty()) return;
				iconDelProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\delIconActive.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if (list.isSelectionEmpty()) return;
				delProject(list.getSelectedIndex());
				list.setListData(projects.toArray());
				list.setSelectedIndex(-1);
				iconAddProject.updateUI();
				iconDelProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\delIcon.png"));
				iconDelProject.updateUI();
			}
		});
		frame.getContentPane().add(iconDelProject);
		iconDelProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\delIcon.png"));
		
		titleProject = new JLabel("");
		titleProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\Projects.png"));
		titleProject.setBounds(186, 169, 1035, 200);
		frame.getContentPane().add(titleProject);
		
		panelAddProject = new JPanel();
		panelAddProject.setBounds(360, 755, 631, 46);
		frame.getContentPane().add(panelAddProject);
		panelAddProject.setLayout(new BorderLayout(0, 0));
		panelAddProject.hide();
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()=='\n') {
					newProject(textField.getText());
					panelAddProject.hide();
					list.setListData(projects.toArray());
				}
			}
		});
		textField.setForeground(new Color(0, 0, 128));
		panelAddProject.add(textField);
		textField.setText("<Project Name>");
		textField.setFont(new Font("ºÚÌå", Font.PLAIN, 28));
		textField.setColumns(10);
		
		panelButtons = new JPanel();
		panelButtons.setForeground(Color.WHITE);
		panelButtons.setBackground(Color.WHITE);
		panelAddProject.add(panelButtons, BorderLayout.EAST);
		panelButtons.setLayout(new BorderLayout(0, 0));
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newProject(textField.getText());
				panelAddProject.hide();
				list.setListData(projects.toArray());
			}
		});
		btnSubmit.setBackground(Color.WHITE);
		panelButtons.add(btnSubmit, BorderLayout.WEST);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelAddProject.hide();
			}
		});
		btnCancel.setBackground(Color.WHITE);
		panelButtons.add(btnCancel, BorderLayout.EAST);
		
		list = new JList();
		list.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				list.setSelectedIndex(-1);
			}
		});
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (list.isSelectionEmpty())
					iconDelProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\delIcon.png"));
				else {
					iconDelProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\delIconActive.png"));
				}
			}
		});
		list.setFont(new Font("Arial", Font.PLAIN, 26));
		list.setListData(projects.toArray());
		
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(239, 321, 938, 495);
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
