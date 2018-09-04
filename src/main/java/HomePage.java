
/*
 * This class is used to create and display the HomePage for MarathonPlanner application
 */

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;

public class HomePage {

	private JFrame frmWelcome;

	public static void main(String[] args) {

		Database db = new Database();
		db.generateDatabase();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage homePageWindow = new HomePage(db);
					homePageWindow.frmWelcome.setLocationRelativeTo(null);
					homePageWindow.frmWelcome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HomePage(Database db) {
		initializeHomePage(db);
	}

	private void initializeHomePage(Database db) {
		frmWelcome = new JFrame();
		frmWelcome.setTitle("Welcome!");
		frmWelcome.setBounds(100, 100, 772, 538);
		frmWelcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcome.getContentPane().setLayout(null);

		String[] optionString = { "Generate Training Plan", "Predict Marathon Time" };

		JLabel lblPleaseSelect = new JLabel("Please Select Option");
		lblPleaseSelect.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblPleaseSelect.setBounds(259, 134, 289, 26);
		frmWelcome.getContentPane().add(lblPleaseSelect);

		JComboBox optionsComboBox = new JComboBox(optionString);
		optionsComboBox.setFont(new Font("Segoe UI", Font.BOLD, 28));
		optionsComboBox.setBounds(187, 200, 370, 44);
		frmWelcome.getContentPane().add(optionsComboBox);

		JButton btnGo = new JButton("GO!");
		btnGo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		btnGo.setBounds(317, 282, 102, 44);
		frmWelcome.getContentPane().add(btnGo);

		JButton btnHelp = new JButton("HELP");
		btnHelp.setBounds(662, 16, 73, 26);
		frmWelcome.getContentPane().add(btnHelp);

		/*
		 * Event handler to instantiate one of the two classes based on user selection
		 * after the Go button is clicked
		 */

		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedOption = optionsComboBox.getSelectedItem().toString();
				if (selectedOption.equals("Generate Training Plan")) {
					frmWelcome.setVisible(false);
					MarathonTrainingPlan trainingPlanGenerator = new MarathonTrainingPlan();
					trainingPlanGenerator.generatePlan(db);
				}
				if (selectedOption.equals("Predict Marathon Time")) {
					frmWelcome.setVisible(false);
					MarathonTimePredictor marathonPredictor = new MarathonTimePredictor();
					marathonPredictor.predictTime(db);
				}

			}
		});

		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Welcome to the Marathon Training Plan Generator\r\n" + 
						"\nPurpose: The Marathon Training Plan Generator (MTPG) generates a customized 16-week training plan based on an individual’s Age, Gender, Height, Weight, and Desired Finish Time. \r\n" + 
						"\nHow to use: \r\n" + 
						"Please enter the following information within the specified parameters\r\n" + 
						"Age: Enter the runner’s age. Age range is from 22 to 44 years old. All ages outside the range will result in error.\r\n" + 
						"Gender: Enter runner’s gender – Male or Female.\r\n" + 
						"Height: Enter runner’s height in centimeters. Height range is from xx to xx centimeters. All heights outside the range will result in error.\r\n" + 
						"Weight: Enter runner’s height in kilograms. Weight range is from xx to xx kilograms. All weights outside the range will result in error.\r\n" + 
						"Desired finished time: Enter runner’s desired finish time in minutes. Run-times range is from xx to xx minutes. All run-times outside the range will result in error.\r\n" + 
						"\nOnce all fields have been inputted, press the “Generate Plan” button. The MTPG will generate a 16-week training plan in a new window. \r\n" + 
						"\nTo exit the MTPG, press the exit button.\r\n");

			}
		});

	}
}
