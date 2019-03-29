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
import java.text.DecimalFormat;
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
import javax.swing.JOptionPane;

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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JEditorPane;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;

import java.awt.GridLayout;
import java.awt.CardLayout;

public class GUIHome {

	private JFrame frame;
	private ArrayList<Project> projects;
	private ArrayList<Staff> staffs;
	private boolean addingProject = false;
	private Project currentProject = null;
	private Event currentEvent = null;
	private Budget currentBudget = new Budget(100);
	
	private int pageDepth; // 0-home 1-projectHome 2-projectBudget 3-eventHome 4-eventBudget
	
	
	// Navigation Components
	private JLabel iconProject;
	private JLabel iconExport;
	private JLabel iconStaff;
	private JLabel iconSettings;
	private JLabel iconBack;
	private JLabel background;

	// Home Page Components
	private JLabel titleProject;
	private JList projectList;
	private JScrollPane scrollPane;
	private JTextField inputProjectName;
	private JPanel panelAddProject;
	private JButton btnProjectSubmit;
	private JButton btnProjectCancel;
	private JPanel panelProjectButtons;
	private JLabel iconAddProject;
	private JLabel iconDelProject;
	
	// Project Home Components
	private JLabel lblProjectName;
	private JLabel lblProjectCharge;
	private JComboBox boxProjectCharge;
	private JLabel lblProjectInfo;
	private JEditorPane inputProjectIntro;
	private JScrollPane introProjectPane;
	private JTextField projectNameInput;
	private JScrollPane eventPane;
	private JScrollPane budgetListProjectPane;
	private JLabel lblEvents;
	private JTextField inputEventName;
	private JPanel panelAddEvent;
	private JButton btnEventSubmit;
	private JButton btnEventCancel;
	private JPanel panelEventButtons;
	private JList eventList;
	private JList budgetProjectList;
	private JPanel budgetProjectPanel;
	private JLabel lblProjectBudget;
	private JLabel btnBudgetManager;
	private JLabel iconAddEvent;
	private JLabel iconDelEvent;
	
	// Event Home Components
	private JLabel lblEventName;
	private JLabel lblEventCharge;
	private JComboBox boxEventCharge;
	private JLabel lblEventInfo;
	private JEditorPane inputEventIntro;
	private JScrollPane introEventPane;
	private JTextField eventNameInput;
	private JPanel rundownPanel;
	private JLabel lblRundown;
	private JTextPane inputRundown;
	private JPanel scenePanel;
	private JLabel lblSceneManagement;
	private Canvas sceneThumb;
	private JScrollPane arrangePane;
	private JList arrangeList;
	private JLabel lblArrange;
	private JPanel budgetEventPanel;
	private JLabel lblEventBudget;
	private JLabel lblEventBudget_1;
	private JLabel butBudgetEventManager;
	
	// Budget Manager Components
	private JLabel lblBudgetName;
	private JTable table;
	private JScrollPane budgetPane;
	private JPanel tableOperation;
	private JButton butAddItem;
	private JButton butDelItem;
	private JButton butBudgetExport;
	private JComboBox boxItemCharge;

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

	// Logic Methods
	
	public void newProject(String name){
		Project p = new Project(name);
		projects.add(p);
	}
	
	public void delProject(int pIndex){
		projects.remove(pIndex);
	}

	public void newEvent(String name){
		Event e = new Event(name);
		currentProject.events.add(e);
	}
	
	public void delEvent(int pIndex){
		currentProject.events.remove(pIndex);
	}
	
	private boolean projectBudgetCheck(double amount){
		if (currentProject.getBudget()==null) return false;
		return currentProject.getBudget().getAvailable()>=amount;
	}
	
	// Scene Home Page
	
	private void initHomePage() {		
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
				addingProject = true;
				inputProjectName.requestFocus();
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
				if (projectList.isSelectionEmpty()) return;
				iconDelProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\delIconBright.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (projectList.isSelectionEmpty()) return;
				iconDelProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\delIconActive.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if (projectList.isSelectionEmpty()) return;
				delProject(projectList.getSelectedIndex());
				projectList.setListData(projects.toArray());
				projectList.setSelectedIndex(-1);
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
		
		inputProjectName = new JTextField();
		inputProjectName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				inputProjectName.selectAll();
			}
		});
		inputProjectName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()=='\n') {
					newProject(inputProjectName.getText());
					panelAddProject.hide();
					addingProject= false;
					projectList.setListData(projects.toArray());
				} else 
				if (e.getKeyCode() == 27) {
					panelAddProject.hide();
				}
			}
		});
		inputProjectName.setForeground(new Color(0, 0, 128));
		panelAddProject.add(inputProjectName);
		inputProjectName.setText("<Project Name>");
		inputProjectName.setFont(new Font("黑体", Font.PLAIN, 28));
		inputProjectName.setColumns(10);
		
		panelProjectButtons = new JPanel();
		panelProjectButtons.setForeground(Color.WHITE);
		panelProjectButtons.setBackground(Color.WHITE);
		panelAddProject.add(panelProjectButtons, BorderLayout.EAST);
		panelProjectButtons.setLayout(new BorderLayout(0, 0));
		
		btnProjectSubmit = new JButton("Submit");
		btnProjectSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newProject(inputProjectName.getText());
				panelAddProject.hide();
				addingProject = false;
				projectList.setListData(projects.toArray());
			}
		});
		btnProjectSubmit.setBackground(Color.WHITE);
		panelProjectButtons.add(btnProjectSubmit, BorderLayout.WEST);
		
		btnProjectCancel = new JButton("Cancel");
		btnProjectCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelAddProject.hide();
			}
		});
		btnProjectCancel.setBackground(Color.WHITE);
		panelProjectButtons.add(btnProjectCancel, BorderLayout.EAST);
		
		projectList = new JList();
		projectList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int clickTimes = e.getClickCount();
				if (clickTimes >= 2) homeToProject();
			}
		});
		projectList.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				projectList.setSelectedIndex(-1);
			}
		});
		projectList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (projectList.isSelectionEmpty())
					iconDelProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\delIcon.png"));
				else {
					iconDelProject.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\delIconActive.png"));
				}
			}
		});
		projectList.setFont(new Font("黑体", Font.PLAIN, 26));
		projectList.setListData(projects.toArray());
		
		scrollPane = new JScrollPane(projectList);
		scrollPane.setBounds(239, 321, 938, 495);
		frame.getContentPane().add(scrollPane);
		
	}
	
	private void hideHomePage() {
		titleProject.hide();    
		projectList.hide();             
		scrollPane.hide();   
		panelAddProject.hide();      
		iconAddProject.hide();  
		iconDelProject.hide();  
	}
	
	private void showHomePage() {
		titleProject.show();    
		projectList.show();                  
		scrollPane.show();   
		if (addingProject) panelAddProject.show();      
		iconAddProject.show();  
		iconDelProject.show();  
	}
	
	// Scene Project Home
	
	private void initProjectHome() {
		projectNameInput = new JTextField();
		projectNameInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == '\n') {
					currentProject.setName(projectNameInput.getText());
					lblProjectName.setText(projectNameInput.getText());
					projectNameInput.hide();
				} else
				if (e.getKeyCode() == 27) {
					projectNameInput.hide();
				}
			}
		});
		projectNameInput.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				projectNameInput.hide();
			}
		});
		projectNameInput.setFont(new Font("黑体", Font.PLAIN, 64));
		projectNameInput.setBounds(171, 106, 757, 106);
		frame.getContentPane().add(projectNameInput);
		projectNameInput.setColumns(15);
		projectNameInput.hide();
		
		lblProjectName = new JLabel("Project Name");
		lblProjectName.setToolTipText("\u70B9\u51FB\u66F4\u6539\u9879\u76EE\u540D\u79F0");
		lblProjectName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				projectNameInput.setText(lblProjectName.getText());
				projectNameInput.show();
				projectNameInput.requestFocus();
				projectNameInput.selectAll();
			}
		});
		lblProjectName.setFont(new Font("黑体", Font.PLAIN, 64));
		lblProjectName.setBounds(171, 106, 757, 106);
		frame.getContentPane().add(lblProjectName);
		
		boxProjectCharge = new JComboBox(staffs.toArray());
		boxProjectCharge.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (boxProjectCharge.getSelectedIndex() == 0) currentProject.setCharge(null);
				else currentProject.setCharge((Staff) boxProjectCharge.getSelectedItem());
			}
		});
		boxProjectCharge.setFont(new Font("黑体", Font.PLAIN, 24));
		boxProjectCharge.setBounds(966, 155, 271, 37);
		frame.getContentPane().add(boxProjectCharge);
		
		introProjectPane = new JScrollPane();
		introProjectPane.setBounds(171, 227, 1066, 144);
		frame.getContentPane().add(introProjectPane);
		
		inputProjectIntro = new JEditorPane();
		inputProjectIntro.setToolTipText("\u8F93\u5165\u9879\u76EE\u8BF4\u660E\uFF0C\u6309Esc\u9000\u51FA\u7F16\u8F91");
		inputProjectIntro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==27) inputProjectIntro.nextFocus();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				currentProject.setIntro(inputProjectIntro.getText());
			}
		});
		inputProjectIntro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				inputProjectIntro.setForeground(new Color(51,115,173));
			}
		});
		inputProjectIntro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				inputProjectIntro.setForeground(new Color(0,0,0));
			}
		});
		introProjectPane.setViewportView(inputProjectIntro);
		inputProjectIntro.setBackground(new Color(255, 250, 250));
		inputProjectIntro.setFont(new Font("黑体", Font.PLAIN, 22));
		
		lblProjectInfo = new JLabel("\u8BF4\u660E\u4E0E\u4FE1\u606F");
		introProjectPane.setRowHeaderView(lblProjectInfo);
		lblProjectInfo.setFont(new Font("黑体", Font.PLAIN, 24));
		
		iconAddEvent = new JLabel("");
		iconAddEvent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				iconAddEvent.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\addIconBright.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				iconAddEvent.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\addIcon.png"));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panelAddEvent.show();
				inputEventName.requestFocus();
			}
		});
		
		panelAddEvent = new JPanel();
		panelAddEvent.setBounds(171, 809, 517, 46);
		frame.getContentPane().add(panelAddEvent);
		panelAddEvent.setLayout(new BorderLayout(0, 0));
		panelAddEvent.hide();
		
		inputEventName = new JTextField();
		inputEventName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				inputEventName.selectAll();
			}
		});
		inputEventName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()=='\n') {
					newEvent(inputEventName.getText());
					panelAddEvent.hide();
					eventList.setListData(currentProject.events.toArray());
				} else 
				if (e.getKeyCode() == 27) {
					panelAddEvent.hide();
				}
			}
		});
		inputEventName.setForeground(new Color(0, 0, 128));
		panelAddEvent.add(inputEventName);
		inputEventName.setText("<Event Name>");
		inputEventName.setFont(new Font("黑体", Font.PLAIN, 28));
		inputEventName.setColumns(10);
		
		panelEventButtons = new JPanel();
		panelEventButtons.setForeground(Color.WHITE);
		panelEventButtons.setBackground(Color.WHITE);
		panelAddEvent.add(panelEventButtons, BorderLayout.EAST);
		panelEventButtons.setLayout(new BorderLayout(0, 0));
		
		btnEventSubmit = new JButton("Submit");
		btnEventSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newEvent(inputEventName.getText());
				panelAddEvent.hide();
				eventList.setListData(currentProject.events.toArray());
			}
		});
		btnEventSubmit.setBackground(Color.WHITE);
		panelEventButtons.add(btnEventSubmit, BorderLayout.WEST);
		
		btnEventCancel = new JButton("Cancel");
		btnEventCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelAddEvent.hide();
			}
		});
		btnEventCancel.setBackground(Color.WHITE);
		panelEventButtons.add(btnEventCancel, BorderLayout.EAST);
		iconAddEvent.setBounds(171, 775, 80, 80);
		frame.getContentPane().add(iconAddEvent);
		iconAddEvent.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\addIcon.png"));
		
		iconDelEvent = new JLabel("");
		iconDelEvent.setBounds(610, 775, 80, 80);
		iconDelEvent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (eventList.isSelectionEmpty()) return;
				iconDelEvent.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\delIconBright.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (eventList.isSelectionEmpty()) return;
				iconDelEvent.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\delIconActive.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if (projectList.isSelectionEmpty()) return;
				delEvent(projectList.getSelectedIndex());
				eventList.setListData(currentProject.events.toArray());
				eventList.setSelectedIndex(-1);
				iconAddEvent.updateUI();
				iconDelEvent.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\delIcon.png"));
				iconDelEvent.updateUI();
			}
		});
		frame.getContentPane().add(iconDelEvent);
		iconDelEvent.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\delIcon.png"));

		
		eventPane = new JScrollPane();
		eventPane.setBounds(171, 393, 517, 462);
		frame.getContentPane().add(eventPane);
		
		lblEvents = new JLabel("\u9879\u76EE\u4E8B\u4EF6\u5217\u8868");
		lblEvents.setHorizontalAlignment(0);
		lblEvents.setFont(new Font("黑体", Font.PLAIN, 24));
		eventPane.setColumnHeaderView(lblEvents);
		
		eventList = new JList();
		eventList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int clickTimes = e.getClickCount();
				if (clickTimes >= 2) projectToEvent();
			}
		});
		eventList.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				eventList.setSelectedIndex(-1);
			}
		});
		eventList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (eventList.isSelectionEmpty())
					iconDelEvent.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\delIcon.png"));
				else {
					iconDelEvent.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\delIconActive.png"));
				}
			}
		});
		eventList.setFont(new Font("黑体", Font.PLAIN, 24));
		eventPane.setViewportView(eventList);
		
		budgetProjectPanel = new JPanel();
		budgetProjectPanel.setBackground(new Color(224, 255, 255));
		budgetProjectPanel.setBounds(731, 391, 504, 463);
		frame.getContentPane().add(budgetProjectPanel);
		budgetProjectPanel.setLayout(new BorderLayout(0, 0));
		
		btnBudgetManager = new JLabel("Budget Manager\r\n \u9884\u7B97\u7269\u8D44\u7BA1\u7406\u5668");
		btnBudgetManager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (currentProject.getBudget() == null) {
					String in = JOptionPane.showInputDialog("Please set up your MAX budget first. \n GBP:");
					if (in==null) return;
					try {
						double total = Double.valueOf(in);
						Budget budget = new Budget(total);
						currentProject.setBudget(budget);
						DecimalFormat df=new DecimalFormat("#.00");
						lblProjectBudget.setText((currentProject.getBudget()==null ? " 0 / 0 " : 
						    	df.format(currentProject.getBudget().getAvailable()) + " / " +df.format(currentProject.getBudget().getTotal())) 
						    );
					} catch (Exception exc){
						exc.printStackTrace();
						JOptionPane.showMessageDialog(budgetProjectPanel, "Please put a number");
					}
				} else {
					projectToBudget();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBudgetManager.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\iconCalculatorBright.png"));
				btnBudgetManager.setFont(new Font("黑体", Font.PLAIN, 26));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBudgetManager.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\iconCalculator.png"));
				btnBudgetManager.setFont(new Font("黑体", Font.PLAIN, 24));
			}
		});
		btnBudgetManager.setFont(new Font("黑体", Font.PLAIN, 24));
		btnBudgetManager.setHorizontalAlignment(SwingConstants.CENTER);
		btnBudgetManager.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\iconCalculator.png"));
		btnBudgetManager.setBackground(new Color(255, 255, 0));
		budgetProjectPanel.add(btnBudgetManager, BorderLayout.SOUTH);
		
		budgetListProjectPane = new JScrollPane();
		budgetProjectPanel.add(budgetListProjectPane, BorderLayout.CENTER);
		

		
		budgetProjectList = new JList();
		budgetProjectList.setFont(new Font("黑体", Font.PLAIN, 24));
		budgetListProjectPane.setViewportView(budgetProjectList);
		
		lblProjectBudget = new JLabel("Budget");
		lblProjectBudget.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\iconMoney.png"));
		lblProjectBudget.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjectBudget.setFont(new Font("宋体", Font.PLAIN, 36));
		budgetProjectPanel.add(lblProjectBudget, BorderLayout.NORTH);
		
		lblProjectCharge = new JLabel("\u8D1F\u8D23\u4EBA");
		lblProjectCharge.setFont(new Font("黑体", Font.PLAIN, 36));
		lblProjectCharge.setBounds(966, 108, 134, 52);
		frame.getContentPane().add(lblProjectCharge);
	}
	
	private void showProjectHome(){
		lblProjectName.show();
		lblProjectCharge.show();
		boxProjectCharge.show();
		introProjectPane.show();
		eventPane.show();
		budgetListProjectPane.show();
		lblEvents.show();
		budgetProjectPanel.show();
		iconAddEvent.show();
		iconDelEvent.show();
	}
	
	private void hideProjectHome(){
		lblProjectName.hide();
		lblProjectCharge.hide();
		boxProjectCharge.hide();
		introProjectPane.hide();
		eventPane.hide();
		budgetListProjectPane.hide();
		lblEvents.hide();
		budgetProjectPanel.hide();
		iconAddEvent.hide();
		iconDelEvent.hide();
	}
	
	// Scene Event Home
	
	private void initEventHome(){
		eventNameInput = new JTextField();
		eventNameInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == '\n') {
					currentProject.setName(eventNameInput.getText());
					lblEventName.setText(eventNameInput.getText());
					eventNameInput.hide();
				} else
				if (e.getKeyCode() == 27) {
					eventNameInput.hide();
				}
			}
		});
		eventNameInput.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				eventNameInput.hide();
			}
		});
		eventNameInput.setFont(new Font("黑体", Font.PLAIN, 64));
		eventNameInput.setBounds(148, 106, 757, 106);
		frame.getContentPane().add(eventNameInput);
		eventNameInput.setColumns(15);
		eventNameInput.hide();
		
		lblEventName = new JLabel("Event Name");
		lblEventName.setToolTipText("\u70B9\u51FB\u66F4\u6539\u9879\u76EE\u540D\u79F0");
		lblEventName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eventNameInput.setText(lblEventName.getText());
				eventNameInput.show();
				eventNameInput.requestFocus();
				eventNameInput.selectAll();
			}
		});
		lblEventName.setFont(new Font("黑体", Font.PLAIN, 64));
		lblEventName.setBounds(171, 106, 757, 106);
		frame.getContentPane().add(lblEventName);
		
		boxEventCharge = new JComboBox(staffs.toArray());
		boxEventCharge.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (boxEventCharge.getSelectedIndex() == 0) currentProject.setCharge(null);
				else currentEvent.setCharge((Staff) boxEventCharge.getSelectedItem());
			}
		});
		boxEventCharge.setFont(new Font("黑体", Font.PLAIN, 24));
		boxEventCharge.setBounds(966, 155, 271, 37);
		frame.getContentPane().add(boxEventCharge);
		
		introEventPane = new JScrollPane();
		introEventPane.setBounds(148, 227, 650, 106);
		frame.getContentPane().add(introEventPane);
		
		inputEventIntro = new JEditorPane();
		inputEventIntro.setToolTipText("\u8F93\u5165\u9879\u76EE\u8BF4\u660E\uFF0C\u6309Esc\u9000\u51FA\u7F16\u8F91");
		inputEventIntro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==27) inputEventIntro.nextFocus();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				currentProject.setIntro(inputEventIntro.getText());
			}
		});
		inputEventIntro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				inputEventIntro.setForeground(new Color(51,115,173));
			}
		});
		inputEventIntro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				inputEventIntro.setForeground(new Color(0,0,0));
			}
		});
		introEventPane.setViewportView(inputEventIntro);
		inputEventIntro.setBackground(new Color(255, 250, 250));
		inputEventIntro.setFont(new Font("黑体", Font.PLAIN, 22));
		
		lblEventInfo = new JLabel("\u8BF4\u660E");
		introEventPane.setRowHeaderView(lblEventInfo);
		lblEventInfo.setFont(new Font("黑体", Font.PLAIN, 24));
		
		rundownPanel = new JPanel();
		rundownPanel.setBounds(813, 227, 424, 658);
		frame.getContentPane().add(rundownPanel);
		rundownPanel.setLayout(new BorderLayout(0, 0));
		
		lblRundown = new JLabel("Rundown \u6D3B\u52A8\u7EC6\u8282");
		lblRundown.setFont(new Font("黑体", Font.PLAIN, 24));
		lblRundown.setHorizontalAlignment(SwingConstants.CENTER);
		rundownPanel.add(lblRundown, BorderLayout.NORTH);
		
		inputRundown = new JTextPane();
		inputRundown.setFont(new Font("宋体", Font.PLAIN, 20));
		inputRundown.setBackground(new Color(255, 250, 250));
		rundownPanel.add(inputRundown, BorderLayout.CENTER);
		
		scenePanel = new JPanel();
		scenePanel.setToolTipText("\u70B9\u51FB\u7565\u7F29\u56FE - \u573A\u5730\u5B89\u6392\u8BBE\u8BA1\u5668");
		scenePanel.setBounds(148, 348, 650, 413);
		frame.getContentPane().add(scenePanel);
		scenePanel.setLayout(new BorderLayout(0, 0));
		
		lblSceneManagement = new JLabel("\u573A\u5730\u4E0E\u5B89\u6392 Scene Management");
		lblSceneManagement.setFont(new Font("黑体", Font.PLAIN, 24));
		lblSceneManagement.setHorizontalAlignment(SwingConstants.CENTER);
		scenePanel.add(lblSceneManagement, BorderLayout.NORTH);
		
		sceneThumb = new Canvas();
		scenePanel.add(sceneThumb, BorderLayout.CENTER);
		
		arrangePane = new JScrollPane();
		scenePanel.add(arrangePane, BorderLayout.EAST);
		
		arrangeList = new JList();
		arrangeList.setFont(new Font("宋体", Font.PLAIN, 20));
		arrangePane.setViewportView(arrangeList);
		
		lblArrange = new JLabel("\u5B89\u6392 \u9884\u89C8");
		lblArrange.setHorizontalAlignment(SwingConstants.CENTER);
		arrangePane.setColumnHeaderView(lblArrange);
		
		budgetEventPanel = new JPanel();
		budgetEventPanel.setBounds(148, 776, 650, 109);
		frame.getContentPane().add(budgetEventPanel);
		budgetEventPanel.setLayout(new BorderLayout(0, 0));
		
		lblEventBudget = new JLabel(" 0 / 0  ");
		lblEventBudget.setFont(new Font("宋体", Font.PLAIN, 28));
		lblEventBudget.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\iconMoney.png"));
		budgetEventPanel.add(lblEventBudget, BorderLayout.WEST);
		
		lblEventBudget_1 = new JLabel("\u672C\u4E8B\u4EF6\u9884\u7B97 Event Budget");
		lblEventBudget_1.setHorizontalAlignment(SwingConstants.CENTER);
		budgetEventPanel.add(lblEventBudget_1, BorderLayout.NORTH);
		
		butBudgetEventManager = new JLabel("\u9884\u7B97\u7269\u8D44\u7BA1\u7406\u5668Budget Manager");
		butBudgetEventManager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				butBudgetEventManager.setFont(new Font("黑体", Font.PLAIN, 22));
				butBudgetEventManager.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\iconCalculatorBright.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				butBudgetEventManager.setFont(new Font("黑体", Font.PLAIN, 20));
				butBudgetEventManager.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\iconCalculator.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if (currentProject.getBudget()==null) {
					JOptionPane.showMessageDialog(budgetEventPanel, 
						"No project budget! Please set up project budget first");
					return;
				}
				if (currentEvent.getBudget() == null) {
					String in = JOptionPane.showInputDialog(budgetEventPanel,"Please set up your MAX budget for this event. \n GBP:");
					if (in==null) return;
					try {
						double total = Double.valueOf(in);
						if (projectBudgetCheck(total)) {
							currentProject.getBudget().addItem(
									new Item(currentEvent.getName(), total, 1, "EVENT BUDGET", staffs.get(0)));
							System.out.println(currentProject.getBudget().expenses.size());
							Budget budget = new Budget(total);
							currentEvent.setBudget(budget);
							DecimalFormat df=new DecimalFormat("#.00");
							lblEventBudget.setText((currentEvent.getBudget()==null ? " 0 / 0 " : 
							    	df.format(currentEvent.getBudget().getAvailable()) + " / " +df.format(currentEvent.getBudget().getTotal())) 
							    );
						} else {
							JOptionPane.showMessageDialog(budgetEventPanel, 
									"Exceed project budget!! Project available: "+currentProject.getBudget().getAvailable());
						}
					} catch (Exception exc){
						exc.printStackTrace();
						JOptionPane.showMessageDialog(budgetEventPanel,"Please put a number");
					}
				} else {
					eventToBudget();
				}
			}
		});
		butBudgetEventManager.setFont(new Font("黑体", Font.PLAIN, 20));
		butBudgetEventManager.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\iconCalculator.png"));
		budgetEventPanel.add(butBudgetEventManager, BorderLayout.EAST);
		
		lblEventCharge = new JLabel("\u8D1F\u8D23\u4EBA");
		lblEventCharge.setFont(new Font("黑体", Font.PLAIN, 36));
		lblEventCharge.setBounds(966, 108, 134, 52);
		frame.getContentPane().add(lblEventCharge);
	}
	
	private void showEventHome() {
		lblEventName.show();
		lblEventCharge.show();
		boxEventCharge.show();
		introEventPane.show();
		rundownPanel.show();
		scenePanel.show();
		budgetEventPanel.show();
	}
	
	private void hideEventHome() {
		lblEventName.hide();
		lblEventCharge.hide();
		boxEventCharge.hide();
		introEventPane.hide();
		rundownPanel.hide();
		scenePanel.hide();
		budgetEventPanel.hide();
	}
	
	// Scene Budget Manager
	
	private void initBudgetManager() {
		lblBudgetName = new JLabel("Budget Name");
		lblBudgetName.setFont(new Font("黑体", Font.PLAIN, 64));
		lblBudgetName.setBounds(165, 105, 1000, 106);
		frame.getContentPane().add(lblBudgetName);
		
		budgetPane = new JScrollPane();
		budgetPane.setBounds(165, 212, 1048, 639);
		frame.getContentPane().add(budgetPane);
		
		boxItemCharge = new JComboBox(staffs.toArray());
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("黑体", Font.PLAIN, 22));
		table.setModel(new BudgetTableModel(currentBudget.expenses, currentBudget));
		table.setRowHeight(22);
		table.getColumnModel().getColumn(0).setWidth(2000);
		table.getColumnModel().getColumn(1).setWidth(500);
		table.getColumnModel().getColumn(2).setWidth(500);
		table.getColumnModel().getColumn(3).setWidth(500);
		table.getColumnModel().getColumn(4).setWidth(500);
		table.getColumnModel().getColumn(5).setWidth(200);
		table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(boxItemCharge));
		table.getModel().addTableModelListener(new TableModelListener(){
			@Override
			public void tableChanged(TableModelEvent e) {
				BudgetTableModel model = (BudgetTableModel) table.getModel();
				System.out.println("HI");
				if (!model.lastChangeSuccess) {
					JOptionPane.showMessageDialog(table, 
							"Exceed budget!!    Available: "+currentBudget.getAvailable());

					model.lastChangeSuccess = true;
				}
			}
		});
		
		budgetPane.setViewportView(table);
		
		tableOperation = new JPanel();
		budgetPane.setRowHeaderView(tableOperation);
		tableOperation.setLayout(new GridLayout(0, 1, 0, 0));
		
		butAddItem = new JButton("\u6DFB\u52A0");
		butAddItem.setFont(new Font("黑体", Font.PLAIN, 22));
		butAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Item item = new Item("", 0, 0, "", staffs.get(0));
				currentBudget.expenses.add(item);
				table.setModel(new BudgetTableModel(currentBudget.expenses, currentBudget));
				table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(boxItemCharge));
			}
		});
		tableOperation.add(butAddItem);
		
		butDelItem = new JButton("\u5220\u9664");
		butDelItem.setFont(new Font("黑体", Font.PLAIN, 22));
		butDelItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selected = table.getSelectedRow();
				if (selected!=-1) {
					int choice = JOptionPane.showConfirmDialog(
							null,"Delete "+currentBudget.expenses.get(selected).getName()+"?",
							"",JOptionPane.OK_OPTION);
					if(choice==JOptionPane.OK_OPTION){
						currentBudget.expenses.remove(selected);
						table.setModel(new BudgetTableModel(currentBudget.expenses, currentBudget));
						table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(boxItemCharge));
					}
				}
			}
		});
		tableOperation.add(butDelItem);
		
		butBudgetExport = new JButton("\u5BFC\u51FA");
		butBudgetExport.setFont(new Font("黑体", Font.PLAIN, 22));
		tableOperation.add(butBudgetExport);
	}
	
	private void hideBudgetManager(){
		lblBudgetName.hide();                
		budgetPane.hide();;   
	}
	
	private void showBudgetManager(){
		lblBudgetName.show();                
		budgetPane.show();;   
	}
	
	// Scene Transition Methods
	
	private void homeToProject() {
		hideHomePage();
		currentProject = (Project) projectList.getSelectedValue();
		lblProjectName.setText(currentProject.getName());
		eventList.setListData(currentProject.events.toArray());
		if (currentProject.getBudget()!=null){
			budgetProjectList.setListData(currentProject.getBudget().expenses.toArray());
			DecimalFormat df=new DecimalFormat("#.00");
			lblProjectBudget.setText((currentProject.getBudget()==null ? " 0 / 0 " : 
		    	df.format(currentProject.getBudget().getAvailable()) + " / " +df.format(currentProject.getBudget().getTotal())) 
		    );
		}
		iconBack.enable();
		iconBack.repaint();
		iconExport.enable();
		iconExport.repaint();
		DecimalFormat df=new DecimalFormat("#.00");
		lblProjectBudget.setText((currentProject.getBudget()==null ? " 0 / 0 " : 
		    	df.format(currentProject.getBudget().getAvailable()) + " / " +df.format(currentProject.getBudget().getTotal())) 
		    );
		pageDepth = 1;
		showProjectHome();
	}

	private void projectToHome() {
		hideProjectHome();
		currentProject = null;
		iconBack.disable();
		iconBack.repaint();
		iconExport.disable();
		iconExport.repaint();
		pageDepth = 0;
		showHomePage();
	}
	
	private void projectToEvent() {
		hideProjectHome();
		currentEvent = (Event) eventList.getSelectedValue();
		lblEventName.setText(currentEvent.getName());
		pageDepth = 3;
		DecimalFormat df=new DecimalFormat("#.00");
		lblEventBudget.setText((currentEvent.getBudget()==null ? " 0 / 0 " : 
	    	df.format(currentEvent.getBudget().getAvailable()) + " / " +df.format(currentEvent.getBudget().getTotal())) 
	    );
		showEventHome();
	}
	
	private void eventToProject() {
		hideEventHome();
		currentEvent = null;
		eventList.setListData(currentProject.events.toArray());
		if (currentProject.getBudget()!=null){
			budgetProjectList.setListData(currentProject.getBudget().expenses.toArray());
			DecimalFormat df=new DecimalFormat("#.00");
			lblProjectBudget.setText((currentProject.getBudget()==null ? " 0 / 0 " : 
		    	df.format(currentProject.getBudget().getAvailable()) + " / " +df.format(currentProject.getBudget().getTotal())) 
		    );
		}
		pageDepth = 1;
		showProjectHome();
	}
	
	private void projectToBudget() {
		hideProjectHome();
		currentBudget = currentProject.getBudget();
		((BudgetTableModel) table.getModel()).updateItems(currentBudget.expenses);
		pageDepth = 2;
		lblBudgetName.setText("BUDGET of "+currentProject.getName());
		showBudgetManager();
	}
	
	private void budgetToProject() {
		hideBudgetManager();
		eventList.setListData(currentProject.events.toArray());
		if (currentProject.getBudget()!=null){
			budgetProjectList.setListData(currentProject.getBudget().expenses.toArray());
			DecimalFormat df=new DecimalFormat("#.00");
			lblProjectBudget.setText((currentProject.getBudget()==null ? " 0 / 0 " : 
		    	df.format(currentProject.getBudget().getAvailable()) + " / " +df.format(currentProject.getBudget().getTotal())) 
		    );
		}
		currentBudget = null;
		pageDepth = 1;
		showProjectHome();
	}
	
	private void eventToBudget() {
		hideEventHome();
		currentBudget = currentEvent.getBudget();
		((BudgetTableModel) table.getModel()).updateItems(currentBudget.expenses);
		pageDepth = 4;
		lblBudgetName.setText("BUDGET of "+currentEvent.getName());
		showBudgetManager();
	}
	
	private void budgetToEvent() {
		hideBudgetManager();
		currentBudget = null;
		pageDepth = 3;
		DecimalFormat df=new DecimalFormat("#.00");
		lblEventBudget.setText((currentEvent.getBudget()==null ? " 0 / 0 " : 
	    	df.format(currentEvent.getBudget().getAvailable()) + " / " +df.format(currentEvent.getBudget().getTotal())) 
	    );
		showEventHome();
	}
	
	private void backGroundNavigation() {
		iconBack = new JLabel("");
		iconBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				iconBack.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\iconBackBright.png"));
			}
			public void mouseExited(MouseEvent e) {
				iconBack.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\iconBackActive.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				switch (pageDepth) {
				case 1: projectToHome(); break;
				case 2: budgetToProject(); break;
				case 3: eventToProject(); break;
				case 4: budgetToEvent(); break;
				}
			}
		});
		
		
		iconBack.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\iconBackActive.png"));
		iconBack.setDisabledIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\iconBack.png"));
		iconBack.setEnabled(false);
		iconBack.setBounds(15, -10, 100, 100);
		frame.getContentPane().add(iconBack);
		
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
		
		iconExport = new JLabel("");
		iconExport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (currentProject != null)
					iconExport.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\exportIconBright.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (currentProject != null)
					iconExport.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\exportIconActive.png"));
				else
					iconExport.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\exportIcon.png"));
			}
		});
		iconExport.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\exportIconActive.png"));
		iconExport.setDisabledIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\exportIcon.png"));
		iconExport.setBounds(14, 310, 100, 100);
		iconExport.disable();
		frame.getContentPane().add(iconExport);
		
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
		
		background = new JLabel("");
		background.setBackground(new Color(255, 250, 250));
		background.setIcon(new ImageIcon("C:\\Users\\ZX50V\\OneDrive\\Prog\\Java\\Activityapp\\background.png"));
		background.setBounds(0, 0, 1280, 960);
		frame.getContentPane().add(background);
		

	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u4F2F\u5927\u4E2D\u56FD\u5B66\u751F\u4F1A\u6D3B\u52A8\u7BA1\u7406\u8F6F\u4EF6 \u6D4B\u8BD5\u7248");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
		frame.setBackground(Color.WHITE);
		frame.setBounds(0, 0, 1280, 960);
		
		initHomePage();
		initProjectHome();
		initEventHome();
		initBudgetManager();
		
		hideProjectHome();
		hideEventHome();
		hideBudgetManager();
			
		backGroundNavigation();
	}
}
