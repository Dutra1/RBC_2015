import javax.swing.JPanel;
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
import javax.swing.JPanel;


public class Command extends JFrame {

	private     JSplitPane  splitPaneV;
	private     JSplitPane  splitPaneH;
	private     JPanel      mapPanel;
	private     JPanel      infoPanel;
	private     JPanel      messagePanel;
	static public JTextField textCompass;
	static public JTextField textBehavior;
	static public JTextField textDistance;
	static public JTextArea messageArea;
	static public JProgressBar progressBattery;
	static public JProgressBar progressSpeed;
	
public Command(){
		
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 370);
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
	
	    splitPaneV.setLeftComponent( messagePanel );
	    splitPaneV.setRightComponent(infoPanel);
	    infoPanel.setLayout(null);
	    
	    JLabel lblCompass = new JLabel("Compass:");
	    lblCompass.setBounds(16, 75, 71, 22);
	    infoPanel.add(lblCompass);
	    
	    JLabel lblDistance = new JLabel("Distance:");
	    lblDistance.setBounds(16, 47, 61, 16);
	    infoPanel.add(lblDistance);
	    
	    JLabel lblBattery = new JLabel("Battery:");
	    lblBattery.setBounds(176, 19, 61, 16);
	    infoPanel.add(lblBattery);
	    
	    JLabel lblBehaviour = new JLabel("Behavior:");
	    lblBehaviour.setBounds(16, 19, 61, 16);
	    infoPanel.add(lblBehaviour);
	        
	    JLabel lblSpeed = new JLabel("Speed:");
	    lblSpeed.setBounds(176, 47, 61, 16);
	    infoPanel.add(lblSpeed);
	    
	    
	    textBehavior = new JTextField();
	    textBehavior.setBounds(84, 11, 80, 28);
	    infoPanel.add(textBehavior);
	    textBehavior.setColumns(10);
	    textBehavior.setEditable(false);
	    
	    textCompass = new JTextField();
	    textCompass.setBounds(84, 72, 80, 29);
	    infoPanel.add(textCompass);
	    textCompass.setColumns(10);
	    textCompass.setEditable(false);
	    
	    textDistance = new JTextField();
	    textDistance.setColumns(10);
	    textDistance.setBounds(84, 41, 80, 28);
	    infoPanel.add(textDistance);
	    textDistance.setEditable(false);
	    
	    progressBattery = new JProgressBar();
	    progressBattery.setBounds(232, 19, 128, 20);
	    progressBattery.setMaximum((int)Globals.MAX_BATTERY);
	    infoPanel.add(progressBattery);

	    progressSpeed = new JProgressBar();
	    progressSpeed.setBounds(232, 47, 128, 20);
	    infoPanel.add(progressSpeed);
	    
	}

	
	
	public void createInfoPanel(){
		
	    infoPanel = new JPanel();

	}
	
	
	public void createMessagePanel(){
		
	    messagePanel = new JPanel();
	    messagePanel.setLayout( new BorderLayout() );
	    messagePanel.setPreferredSize( new Dimension( 190, 200 ) );
	    messagePanel.setMinimumSize( new Dimension( 190, 200 ) );
	
	    messagePanel.add( new JLabel( "Robot Messages:" ), BorderLayout.NORTH );
	    messageArea = new JTextArea();
	    messageArea.setColumns(20);
	    messagePanel.add( messageArea, BorderLayout.CENTER );
	   
	}
}