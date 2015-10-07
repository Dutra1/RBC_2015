import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;


public class Command extends JFrame {

	private     JSplitPane  splitPaneV;
	private     JSplitPane  splitPaneH;
	private     JPanel      mapPanel;
	private     JPanel      infoPanel;
	private     JPanel      messagePanel;
	private JButton btnAddtile;
	static public  JTextField textX;
	static public JTextField textY;
	static public JTextField textWorld;
	static public JTextField textBehavior;
	static public JTextField textAngle;
	static public JTextArea messageArea;
	static public JProgressBar progressBattery;
	
public Command(){
		
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
	    setTitle( "Command Center" );

	    JPanel topPanel = new JPanel();
	    BorderLayout bl_topPanel = new BorderLayout();

	    topPanel.setLayout( bl_topPanel );
	    getContentPane().add( topPanel );
	
	    // Create the panels
	    createInfoPanel();
	    createMessagePanel();
	
	    // Create a splitter pane
	    splitPaneV = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
	    topPanel.add( splitPaneV, BorderLayout.CENTER );
	
	    //splitPaneH = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
	    //splitPaneH.setLeftComponent( messagePanel );
	    //splitPaneH.setRightComponent( mapPanel );
	
	    splitPaneV.setLeftComponent( messagePanel );
	    splitPaneV.setRightComponent(infoPanel);
	    infoPanel.setLayout(null);
	    
	    //infoPanel.add(btnAddtile);
	    
	    textX = new JTextField();
	    textX.setBounds(32, 83, 35, 29);
	    infoPanel.add(textX);
	    textX.setColumns(10);
	    
	    textY = new JTextField();
	    textY.setBounds(97, 83, 35, 29);
	    infoPanel.add(textY);
	    textY.setColumns(10);
	    
	    JLabel lblWorld = new JLabel("World:");
	    lblWorld.setBounds(6, 16, 61, 22);
	    infoPanel.add(lblWorld);
	    
	    textWorld = new JTextField();
	    textWorld.setBounds(52, 13, 47, 29);
	    infoPanel.add(textWorld);
	    textWorld.setColumns(10);
	    
	    progressBattery = new JProgressBar();
	    progressBattery.setBounds(63, 124, 128, 20);
	    progressBattery.setMaximum((int)Globals.MAX_BATTERY);
	    infoPanel.add(progressBattery);
	    
	    JLabel lblPosition = new JLabel("Position:");
	    lblPosition.setBounds(6, 55, 61, 16);
	    infoPanel.add(lblPosition);
	    
	    JLabel lblX = new JLabel("X:");
	    lblX.setBounds(13, 89, 18, 16);
	    infoPanel.add(lblX);
	    
	    JLabel lblY = new JLabel("Y:");
	    lblY.setBounds(79, 89, 18, 16);
	    infoPanel.add(lblY);
	    
	    JLabel lblBattery = new JLabel("Battery:");
	    lblBattery.setBounds(6, 124, 61, 16);
	    infoPanel.add(lblBattery);
	    
	    JLabel lblBehaviour = new JLabel("Behavior:");
	    lblBehaviour.setBounds(245, 19, 61, 16);
	    infoPanel.add(lblBehaviour);
	    
	    JLabel lblAngle = new JLabel("Angle:");
	    lblAngle.setBounds(144, 89, 45, 16);
	    infoPanel.add(lblAngle);
	    
	    textBehavior = new JTextField();
	    textBehavior.setBounds(310, 13, 100, 28);
	    infoPanel.add(textBehavior);
	    textBehavior.setColumns(10);
	    
	    textAngle = new JTextField();
	    textAngle.setBounds(189, 83, 45, 28);
	    infoPanel.add(textAngle);
	    textAngle.setColumns(10);
	    
	    JLabel lblSpeed = new JLabel("Speed:");
	    lblSpeed.setBounds(231, 124, 61, 16);
	    infoPanel.add(lblSpeed);
	    
	    JProgressBar progressSpeed = new JProgressBar();
	    progressSpeed.setBounds(282, 124, 128, 20);
	    infoPanel.add(progressSpeed);
	}

	
	
	public void createInfoPanel(){
		
	    infoPanel = new JPanel();

	}
	
	
	public void createMessagePanel(){
		
	    messagePanel = new JPanel();
	    messagePanel.setLayout( new BorderLayout() );
	    messagePanel.setPreferredSize( new Dimension( 190, 400 ) );
	    messagePanel.setMinimumSize( new Dimension( 190, 400 ) );
	
	    messagePanel.add( new JLabel( "Robot Messages:" ), BorderLayout.NORTH );
	    messageArea = new JTextArea();
	    messageArea.setColumns(20);
	    messagePanel.add( messageArea, BorderLayout.CENTER );
	}
}