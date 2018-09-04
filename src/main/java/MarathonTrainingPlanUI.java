
/* This class is used to create a GUI for the
 * Marathon Training Plan. It calculates and
 * displays the Training Plan based on the
 * user inputs on the GUI.
 */
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import javax.swing.JButton;
import java.awt.Font;

public class MarathonTrainingPlanUI {

	private JFrame frmMarathonTrainingPlan;
	private JTextField ageText;
	private JTextField heightText;
	private JTextField weightText;
	private JTextField finishTimeText;

	double ageDouble;
	double heightDouble;
	double weightDouble;
	double finishTimeDouble;
	double genderDouble;
	double bmiDouble;

	private double ageRegressionCoefficient;
	private double genderRegressionCoefficient;
	private double finishTimeCoefficient;
	private double bmiRegressionCoefficient;

	String gender;

	int predictedMiles;

	MarathonTrainingPlanUI(double ageRegressionCoefficient, double genderRegressionCoefficient,
			double bmiRegressionCoefficient, double finishTimeCoefficient) {

		this.ageRegressionCoefficient = ageRegressionCoefficient;
		this.genderRegressionCoefficient = genderRegressionCoefficient;
		this.bmiRegressionCoefficient = bmiRegressionCoefficient;
		this.finishTimeCoefficient = finishTimeCoefficient;
		try {
		initializeUserInterface();
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void planGenerator(MarathonTrainingPlanUI marathonPlanWindow) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					marathonPlanWindow.frmMarathonTrainingPlan.setLocationRelativeTo(null);
					marathonPlanWindow.frmMarathonTrainingPlan.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
		});
	}

	private void initializeUserInterface() {
		frmMarathonTrainingPlan = new JFrame();
		frmMarathonTrainingPlan.getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 20));
		frmMarathonTrainingPlan.setTitle("Marathon Training Plan");
		frmMarathonTrainingPlan.setBounds(100, 100, 595, 453);
		frmMarathonTrainingPlan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMarathonTrainingPlan.getContentPane().setLayout(null);

		String[] genderString = { "Female", "Male" };

		ageText = new JTextField();
		ageText.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		ageText.setBounds(255, 51, 81, 30);
		frmMarathonTrainingPlan.getContentPane().add(ageText);
		ageText.setColumns(10);

		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblAge.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAge.setBounds(118, 54, 126, 30);
		frmMarathonTrainingPlan.getContentPane().add(lblAge);

		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setBounds(118, 96, 126, 30);
		frmMarathonTrainingPlan.getContentPane().add(lblGender);

		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblHeight.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHeight.setBounds(128, 145, 116, 30);
		frmMarathonTrainingPlan.getContentPane().add(lblHeight);

		JComboBox genderComboBox = new JComboBox(genderString);
		genderComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		genderComboBox.setBounds(255, 97, 141, 30);
		frmMarathonTrainingPlan.getContentPane().add(genderComboBox);

		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblWeight.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWeight.setBounds(138, 191, 106, 30);
		frmMarathonTrainingPlan.getContentPane().add(lblWeight);

		JLabel lblDesiredFinishTime = new JLabel("Desired Finish Time:");
		lblDesiredFinishTime.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblDesiredFinishTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesiredFinishTime.setBounds(0, 239, 244, 30);
		frmMarathonTrainingPlan.getContentPane().add(lblDesiredFinishTime);

		heightText = new JTextField();
		heightText.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		heightText.setColumns(10);
		heightText.setBounds(255, 142, 81, 30);
		frmMarathonTrainingPlan.getContentPane().add(heightText);

		weightText = new JTextField();
		weightText.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		weightText.setColumns(10);
		weightText.setBounds(255, 188, 81, 30);
		frmMarathonTrainingPlan.getContentPane().add(weightText);

		finishTimeText = new JTextField();
		finishTimeText.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		finishTimeText.setColumns(10);
		finishTimeText.setBounds(255, 236, 81, 30);
		frmMarathonTrainingPlan.getContentPane().add(finishTimeText);

		JLabel lblYears = new JLabel("years");
		lblYears.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblYears.setBounds(351, 51, 75, 30);
		frmMarathonTrainingPlan.getContentPane().add(lblYears);

		JLabel lblCentimeters = new JLabel("centimeters");
		lblCentimeters.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblCentimeters.setBounds(345, 145, 126, 30);
		frmMarathonTrainingPlan.getContentPane().add(lblCentimeters);

		JLabel lblKilograms = new JLabel("kilograms");
		lblKilograms.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblKilograms.setBounds(345, 191, 106, 30);
		frmMarathonTrainingPlan.getContentPane().add(lblKilograms);

		JLabel lblMinutes = new JLabel("minutes");
		lblMinutes.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblMinutes.setBounds(345, 239, 106, 30);
		frmMarathonTrainingPlan.getContentPane().add(lblMinutes);

		JButton btnGenerateTrainingPlan = new JButton("Generate Plan");
		btnGenerateTrainingPlan.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGenerateTrainingPlan.setBounds(64, 304, 180, 30);
		frmMarathonTrainingPlan.getContentPane().add(btnGenerateTrainingPlan);

		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnExit.setBounds(255, 304, 180, 30);
		frmMarathonTrainingPlan.getContentPane().add(btnExit);
		
		JButton btnHelp = new JButton("HELP");
		btnHelp.setBounds(493, 16, 65, 29);
		frmMarathonTrainingPlan.getContentPane().add(btnHelp);
		

		/*
		 * Event handler to calculate and display the Training Plan when the Generate
		 * Training Plan button is clicked
		 */
		btnGenerateTrainingPlan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				ageDouble = Double.parseDouble(ageText.getText());
				heightDouble = Double.parseDouble(heightText.getText());
				weightDouble = Double.parseDouble(weightText.getText());
				finishTimeDouble = Double.parseDouble(finishTimeText.getText());

				bmiDouble = (weightDouble * 10000) / (heightDouble * heightDouble);

				if (genderComboBox.getSelectedIndex() == 0)
					genderDouble = 0.0;
				else
					genderDouble = 1.0;

				predictedMiles = (int) (ageDouble * ageRegressionCoefficient
						+ genderDouble * genderRegressionCoefficient + bmiDouble * bmiRegressionCoefficient
						+ finishTimeDouble * finishTimeCoefficient);


				if (ageDouble < 20 || ageDouble > 40) // Validate Age Input
				{
					String message = "ERROR: Enter Age between 20 and 40";
					JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
					
				} else if (bmiDouble < 18 || bmiDouble > 25) // Validate BMI
				{
					String message = "Sorry! This program works only for BMI range 18.0 to 25.0";
					JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
				} else if (finishTimeDouble < 130 || finishTimeDouble > 300) // Validate FinishTime
				{
					String message = "ERROR: Enter Finish Time between 130 and 300";
					JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
				} else {
					DisplayTable newTable = new DisplayTable(predictedMiles);
					newTable.createTable(newTable);
				}
				} catch (Exception f) {
					f.printStackTrace();
					JOptionPane.showMessageDialog(null, "Please provide all inputs. Do not leave any fields blank.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		/*
		 * Event handler to exit the program if the Exit Button is clicked
		 */
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		/*
		 * Event handler to launch help
		 */
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Welcome to the Marasist – Marathon’s Training Tool\r\n" + 
						"\nPurpose: Marasist is a marathon runner’s training tool that using regression analysis to develop a marathon training plan or predict a runner’s finish time.. \r\n" + 
						"\nHow to use: \r\n" + 
						"\nPress on the “Marathon Training Plan” button to open the MTPG tool and generate an individual’s marathon training plan.\r\n" + 
						"\nPress on the “Marathon Time Predictor” button to open the MFTG tool and predicts an individual’s finish time.\r\n" + 
						"\nTo exit, click the “X” on the upper right hand corner of the window.\r\n");

			}
		});
	}
}
