/**
 * 
 */
package com.example.taxibooking.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.example.taxibooking.interfaces.Observer;
import com.example.taxibooking.logging.TaxiBookingLogger;
import com.example.taxibooking.model.TaxiBookingModel;
import com.example.taxibooking.model.Worker;

/**
 * @author Jorge
 *
 */
public class TaxiBookingGUI extends JFrame {

	private TaxiBookingModel model;
	private int numofwindows = 0;
	JButton processButton;
    private JTextArea [] workerwindow ; 
    private JTextArea [] inventory ; 
    
    public TaxiBookingGUI(TaxiBookingModel taximodel)
    {
    	model = taximodel;
    	
    	
    	 //set up window title
        setTitle("Taxi Booking");
        //ensure program ends when window closes
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(100,600);
        setLocation(10,20);
        addWindowListener(new WindowAdapter() {		
            @Override
            public void windowClosing(WindowEvent e)
            {
                TaxiBookingLogger.getInstance().closeLog();
                e.getWindow().dispose();
            }
        });
        
        //add button panel and result field to the content pane
        Container contentPane = getContentPane();
        contentPane.add(createNorthPanel(), BorderLayout.NORTH);
        contentPane.add(createCustPanel(), BorderLayout.CENTER);
        contentPane.add(createinventoryPanel(), BorderLayout.SOUTH);
        //pack and set visible
        pack();
        setVisible(true);
    }
    
   

	private JPanel createNorthPanel() {
        //north panel shows the button to start processing
        processButton = new JButton("Start Booking");
        JPanel northPanel = new JPanel(new GridLayout (1,4));
        northPanel.add(new JLabel("Window 1"));
        northPanel.add(processButton);
        northPanel.add(new JLabel("Window 2"));
        northPanel.add(new JLabel("Window 3"));
        return northPanel;
    }
	
    private JPanel createCustPanel() {
    	//cheating - know there are 6 customers
    	JPanel custPanel = new JPanel(new GridLayout (1,3));
    	workerwindow  = new JTextArea [3];
		for (int i = 0; i < 3; i++) {
			workerwindow [i]= new JTextArea(15,80);
			//monospaced allows nice tabular layout
			workerwindow[i].setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
			workerwindow [i].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.LIGHT_GRAY));			
			custPanel.add(workerwindow[i]);
		}
		return custPanel;
    }
    
    private Component createinventoryPanel() {
    	JPanel custPanel = new JPanel(new GridLayout (1,2));
    	inventory  = new JTextArea [2];
		for (int i = 0; i < 2; i++) {
			inventory [i]= new JTextArea(15,80);
			//monospaced allows nice tabular layout
			inventory[i].setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
			inventory [i].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.LIGHT_GRAY));
			
			custPanel.add(inventory[i]);
		}
		return custPanel;
	}
    
    public void addButtonListener(ActionListener al) {
        processButton.addActionListener(al);
    }
    
    public void disableProcessButton() {
    	processButton.setEnabled(false);
    }

    public void enableProcessButton() {
    	processButton.setEnabled(true);
    }


	public JTextArea[] getWorkerwindow() {
		return workerwindow;
	}



	public void setWorkerwindow(JTextArea[] workerwindow) {
		this.workerwindow = workerwindow;
	}



	public JTextArea[] getInventory() {
		return inventory;
	}



	public void setInventory(JTextArea[] inventory) {
		this.inventory = inventory;
	}
    
	

}
