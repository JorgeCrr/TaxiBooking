/**
 * 1. Application Graphical User Interface controls
 * 2. Button Action Listeners
 */
package com.example.taxibooking.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import com.example.taxibooking.model.Model;

public class AppGUI extends JFrame {

	// Main panel of the JFrame
	private JPanel contentPane;
	// Start booking button
	private JButton btnStartBooking;
	// Booking simulation log button
	private JButton btnShowBookingSimulationLog;
	private JLabel lblProcessingSpeed;
	// No. of Worker window selection JDropDown
	private JComboBox cbWorkerWindows;
	private JLabel lblWorkerWindows;
	// Runtime processing speed selection JDropDown
	private JComboBox cbProcessingSpeed;
	private JLabel lblProgressStatus, lblProgressTotalTaxis,
			lblProgressTotalPassengerGroups,
			lblProgressPassengerGroupsUnAllocated, lblProgressStatusEnd;
	private Model model;
	// Number of windows
	private int numOfWindows = 0;
	// Worker windows display in JTextArea
	private JTextArea[] displayWindows;
	// Passenger Groups and Taxis display in JTextArea
	private JTextArea[] displayTaxisNPassengerGroups;
	// Application directory
	String appDir;

	public AppGUI(Model taximodel) {
		model = taximodel;

		try {

			// JFrame GUI

			// Path of project directory
			appDir = System.getProperty("user.dir");
			// Application icon
			ImageIcon img = new ImageIcon(appDir + "\\data\\car-icon.png");
			setIconImage(img.getImage());

			setResizable(false);
			// Set up window title
			setTitle("   TAXI BOOKING SYSTEM   ");
			// Ensure program ends when window closes
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setBounds(100, 50, 1024, 768);
			

			// Parent JPanel layout in JFrame
			contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new MatteBorder(1, 1, 1, 1,
					(Color) new Color(100, 149, 237)));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);

			// Adds GUI sections to parent JPanel
			Container contentPane = getContentPane();
			contentPane.add(createHeaderPanel(), BorderLayout.NORTH);
			contentPane.add(createTaxisNPassengerGroupsPanel(), BorderLayout.WEST);
			contentPane.add(createWindowsPanel(), BorderLayout.EAST);
			// pack and set visible
			pack();
			setVisible(true);
		} catch (Exception ex) {
			final JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel,
					"Application GUI could not be initialisation error",
					"Taxi Booking System", JOptionPane.ERROR_MESSAGE);
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	}

	/**
	 * Header GUI controls
	 * 
	 */
	private JPanel createHeaderPanel() {

		// Header Panel
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(Color.WHITE);
		contentPane.add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new GridLayout(1, 1, 10, 10));

		// Toolbar
		JToolBar toolBarHeader = new JToolBar();
		panelHeader.add(toolBarHeader);

		// App Header Image
		JLabel lblNewLabel = new JLabel("");
		toolBarHeader.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(appDir + "\\data\\header.png"));

		// Buttons Panel holding JToolbar
		JPanel panelButtons = new JPanel();
		panelButtons.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		toolBarHeader.add(panelButtons);

		// Worker Window Dropdown
		lblWorkerWindows = new JLabel("");
		lblWorkerWindows
				.setText("<html><body>&nbsp;No. of <br />&nbsp;windows&nbsp;</body></html>");
		lblWorkerWindows.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblWorkerWindows.setBackground(Color.DARK_GRAY);
		panelButtons.add(lblWorkerWindows);

		cbWorkerWindows = new JComboBox();
		cbWorkerWindows.setModel(new DefaultComboBoxModel(new String[] { "1",
				"2", "3", "4", "5", "6" }));
		cbWorkerWindows.setSelectedIndex(1);
		panelButtons.add(cbWorkerWindows);

		// Runtime Speed Label and Dropdown
		lblProcessingSpeed = new JLabel("");
		lblProcessingSpeed
				.setText("<html><body>&nbsp;Processing <br />&nbsp;Speed&nbsp;</body></html>");
		lblProcessingSpeed.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProcessingSpeed.setBackground(Color.DARK_GRAY);
		panelButtons.add(lblProcessingSpeed);

		cbProcessingSpeed = new JComboBox();
		cbProcessingSpeed.setModel(new DefaultComboBoxModel(new String[] {
				"Slow", "Normal", "Fast", "Superfast" }));
		cbProcessingSpeed.setSelectedIndex(1);
		panelButtons.add(cbProcessingSpeed);

		// Start Booking button
		btnStartBooking = new JButton("  Start Booking  ");
		btnStartBooking.setCursor(Cursor
				.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelButtons.add(btnStartBooking);
		btnStartBooking.setToolTipText("Press button to start taxi booking");
		btnStartBooking.setIcon(new ImageIcon(appDir
				+ "\\data\\start-booking.png"));
		btnStartBooking.setForeground(Color.DARK_GRAY);
		btnStartBooking.setBackground(new Color(192, 192, 192));
		btnStartBooking.setFont(new Font("Arial", Font.BOLD, 12));

		// Show Simulation Log
		btnShowBookingSimulationLog = new JButton("  Show Simulation Log  ");
		btnShowBookingSimulationLog.setCursor(Cursor
				.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelButtons.add(btnShowBookingSimulationLog);
		btnShowBookingSimulationLog
				.setToolTipText("Press button to show the booking simulation log");
		btnShowBookingSimulationLog.setIcon(new ImageIcon(appDir
				+ "\\data\\simulation-log.png"));
		btnShowBookingSimulationLog.setForeground(Color.DARK_GRAY);
		btnShowBookingSimulationLog.setBackground(new Color(192, 192, 192));
		btnShowBookingSimulationLog.setFont(new Font("Arial", Font.BOLD, 12));

		return panelHeader;
	}

	/**
	 * Worker window display controls
	 * 
	 */
	private JPanel createWindowsPanel() {

		JPanel panelRight = new JPanel(new FlowLayout());
		panelRight.setBorder(null);
		panelRight.setPreferredSize(new Dimension(670, 595));
		// Booking progress display controls
		JPanel progressPanel = new JPanel(new GridLayout(8, 1, 10, 10));
		progressPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "  PROGRESS  ",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(64, 64,
						64)));
		progressPanel.setPreferredSize(new Dimension(660, 230));

		lblProgressStatus = new JLabel("");
		lblProgressStatus.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProgressStatus.setForeground(Color.RED);
		lblProgressStatus.setBackground(Color.DARK_GRAY);
		progressPanel.add(lblProgressStatus);

		lblProgressTotalTaxis = new JLabel("");
		lblProgressTotalTaxis.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblProgressTotalTaxis.setBackground(Color.DARK_GRAY);
		progressPanel.add(lblProgressTotalTaxis);

		lblProgressTotalPassengerGroups = new JLabel("");
		lblProgressTotalPassengerGroups.setFont(new Font("Tahoma", Font.PLAIN,
				12));
		lblProgressTotalPassengerGroups.setBackground(Color.DARK_GRAY);
		progressPanel.add(lblProgressTotalPassengerGroups);

		lblProgressPassengerGroupsUnAllocated = new JLabel("");
		lblProgressPassengerGroupsUnAllocated.setFont(new Font("Tahoma",
				Font.PLAIN, 12));
		lblProgressPassengerGroupsUnAllocated.setBackground(Color.DARK_GRAY);
		progressPanel.add(lblProgressPassengerGroupsUnAllocated);

		lblProgressStatusEnd = new JLabel("");
		lblProgressStatusEnd.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProgressStatusEnd.setForeground(Color.BLUE);
		lblProgressStatusEnd.setBackground(Color.DARK_GRAY);
		progressPanel.add(lblProgressStatusEnd);

		panelRight.add(progressPanel);

		// Worker windows display controls
		JPanel windowPanel = new JPanel();
		windowPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "  WINDOW(S)  ",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(64, 64,
						64)));
		windowPanel.setPreferredSize(new Dimension(660, 360));
		displayWindows = new JTextArea[6];
		for (int i = 0; i < 6; i++) {
			displayWindows[i] = new JTextArea(6, 45);
			displayWindows[i]
					.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
			displayWindows[i].setBorder(BorderFactory.createMatteBorder(1, 1,
					1, 1, Color.LIGHT_GRAY));
			windowPanel.add(displayWindows[i]);
		}
		panelRight.add(windowPanel);
		return panelRight;
	}
	
	/**
	 * Taxis and Passenger Groups Queue display controls
	 * 
	 */
	private Component createTaxisNPassengerGroupsPanel() {

		displayTaxisNPassengerGroups = new JTextArea[2];
		JPanel panelLeft = new JPanel(new FlowLayout());
		// Passenger Groups panel
		JPanel panelPassengerGroups = new JPanel();
		panelPassengerGroups.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "  PASSENGER GROUPS(S)  ",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(64, 64,
						64)));
		panelPassengerGroups.setPreferredSize(new Dimension(295, 595));
		displayTaxisNPassengerGroups[0] = new JTextArea(33, 37);
		displayTaxisNPassengerGroups[0].setFont(new Font(Font.MONOSPACED,
				Font.PLAIN, 12));
		JScrollPane spPassengerGroupss = new JScrollPane(
				displayTaxisNPassengerGroups[0]);
		spPassengerGroupss
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelPassengerGroups.add(spPassengerGroupss);
		panelLeft.add(panelPassengerGroups);
		// Taxis panel
		JPanel panelTaxis = new JPanel();
		panelTaxis.setBorder(new TitledBorder(null, "  TAXI(S)  ",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		panelTaxis.setPreferredSize(new Dimension(165, 595));
		displayTaxisNPassengerGroups[1] = new JTextArea(33, 20);
		displayTaxisNPassengerGroups[1].setFont(new Font(Font.MONOSPACED,
				Font.PLAIN, 12));
		JScrollPane spTaxis = new JScrollPane(displayTaxisNPassengerGroups[1]);
		spTaxis.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelTaxis.add(spTaxis);
		panelLeft.add(panelTaxis);

		return panelLeft;
	}

	// Button event listener
	public void addButtonListener(ActionListener al) {
		btnStartBooking.addActionListener(al);
		btnShowBookingSimulationLog.addActionListener(al);

	}

	// Disable start booking button
	public void disableStartBooking() {
		btnStartBooking.setEnabled(false);
	}

	// Enable start booking drop down
	public void enableStartBookingButton() {
		btnStartBooking.setEnabled(true);
	}

	// Disable show simulation log button
	public void disableSimulationLogButton() {
		btnShowBookingSimulationLog.setEnabled(false);
	}

	// Enable show simulation log button
	public void enableSimulationLogButton() {
		btnShowBookingSimulationLog.setEnabled(true);
	}

	// Disable no. of Worker windows dropdown
	public void disableCbWorkerWindows() {
		cbWorkerWindows.setEnabled(false);
	}

	// Enable no. of Worker windows dropdown
	public void enableCbWorkerWindows() {
		cbWorkerWindows.setEnabled(true);
	}

	// Disable Processing speed dropdown
	public void disableCbProcessingSpeed() {
		cbProcessingSpeed.setEnabled(false);
	}

	// Enable Processing speed drop down
	public void enableCbProcessingSpeed() {
		cbProcessingSpeed.setEnabled(true);
	}

	// JTextArea[0] for Window 1, JTextArea[1] for Window 2, .., JTextArea[n-1]
	// for Window n
	public JTextArea[] getWorkerwindow() {
		return displayWindows;
	}

	// JTextArea[0] for Window 1, JTextArea[1] for Window 2, .., JTextArea[n-1]
	// for Window n
	public void setWorkerwindow(JTextArea[] workerWindow) {
		this.displayWindows = workerWindow;
	}

	// JTextArea[0] for PassengerGroups, JTextArea[1] for Taxis
	public JTextArea[] getTaxisNPassengerGroups() {
		return displayTaxisNPassengerGroups;
	}

	// JTextArea[0] for PassengerGroups, JTextArea[1] for Taxis
	public void setTaxisNPassengerGroups(JTextArea[] taxisNPassengerGroups) {
		this.displayTaxisNPassengerGroups = taxisNPassengerGroups;
	}

	// Get the processing speed from the drop down
	public String getProcessingSpeed() {
		return this.cbProcessingSpeed.getSelectedItem().toString();
	}

	// Get the no. of workers windows from the drop down
	public int getNoOfWorkerWindows() {
		return Integer.parseInt(this.cbWorkerWindows.getSelectedItem()
				.toString());
	}

	public void setProgressStatus(String progress) {
		this.lblProgressStatus.setText(progress);
	}

	public void setProgressTotalTaxis(String totalTaxis) {
		this.lblProgressTotalTaxis.setText(totalTaxis);
	}

	public void setProgressTotalPassengerGroups(String totalPassengerGroups) {
		this.lblProgressTotalPassengerGroups.setText(totalPassengerGroups);
	}

	public void setProgressPassengerGroupsUnAllocated(
			int passengerGroupsUnAllocated) {
		this.lblProgressPassengerGroupsUnAllocated
				.setText("  Un-Allocated Passenger Group(s): "
						+ Integer.toString(passengerGroupsUnAllocated));
	}

	public void setProgressStatusEndMonitor(String progress) {
		if (this.lblProgressStatusEnd
				.getText()
				.equals("  Please wait  .    .    .    .    .    .    .    .    .    .    .    .    .")) {
			this.lblProgressStatusEnd.setText("  Please wait  .");
		} else {
			this.lblProgressStatusEnd.setText("  Please wait  ."
					+ this.lblProgressStatusEnd.getText().replace(
							"Please wait", ""));
		}
	}

	public void setProgressStatusEnd(String progress) {
		this.lblProgressStatusEnd.setText(progress);
	}

}
