
/* 
 * This class is used to create a GUI for the
 * Marathon Time Predictor. It calculates and
 * displays the final Most Likely Finish time
 * based on the user inputs on the GUI
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;

public class MarathonTimePredictorUI {

	protected Shell shlMarathonPlanner;
	private Text ageText;
	private Text last10KText;
	private Text last21KText;
	private Text milesPerWeekText;

	double gender;
	double trackDifficulty;
	double ageDouble;
	double last10KDouble;
	double last21KDouble;
	double milesPerWeekDouble;

	private double ageRegressionCoefficient;
	private double genderRegressionCoefficient;
	private double milesPerWeekRegressionCoefficient;
	private double last21kRegressionCoefficient;
	private double last10kRegressionCoefficient;
	private double trackDifficultyRegressionCoefficient;

	double predictedTime;

	MarathonTimePredictorUI(double ageRegressionCoefficient, double genderRegressionCoefficient,
			double milesPerWeekRegressionCoefficient, double bestTime21kRegressionCoefficient,
			double bestTime10kRegressionCoefficient, double trackDifficultyRegressionCoefficient) {

		this.ageRegressionCoefficient = ageRegressionCoefficient;
		this.genderRegressionCoefficient = genderRegressionCoefficient;
		this.milesPerWeekRegressionCoefficient = milesPerWeekRegressionCoefficient;
		this.last21kRegressionCoefficient = bestTime21kRegressionCoefficient;
		this.last10kRegressionCoefficient = bestTime10kRegressionCoefficient;
		this.trackDifficultyRegressionCoefficient = trackDifficultyRegressionCoefficient;

	}

	public void openUserInterface() {

		Display display = Display.getDefault();
		createUserInterfaceContents();
		Monitor primaryDisplay = display.getPrimaryMonitor();
		Rectangle screenBounds = primaryDisplay.getBounds();
		Rectangle shellBounds = shlMarathonPlanner.getBounds();

		int x = screenBounds.x + (screenBounds.width - shellBounds.width) / 2;
		int y = screenBounds.y + (screenBounds.height - shellBounds.height) / 2;

		shlMarathonPlanner.setLocation(x, y);

		shlMarathonPlanner.open();
		shlMarathonPlanner.layout();
		shlMarathonPlanner.forceActive();
		while (!shlMarathonPlanner.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createUserInterfaceContents() {

		shlMarathonPlanner = new Shell();
		shlMarathonPlanner.setSize(820, 636);
		shlMarathonPlanner.setText("Marathon Time Predictor");
		shlMarathonPlanner.setLayout(null);

		Label lblNewLabel = new Label(shlMarathonPlanner, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblNewLabel.setBounds(331, 60, 41, 30);
		lblNewLabel.setText("Age:");

		Label lblSex = new Label(shlMarathonPlanner, SWT.NONE);
		lblSex.setAlignment(SWT.RIGHT);
		lblSex.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblSex.setBounds(331, 122, 41, 30);
		lblSex.setText("Sex:");

		Label lblLastkTiming = new Label(shlMarathonPlanner, SWT.NONE);
		lblLastkTiming.setAlignment(SWT.RIGHT);
		lblLastkTiming.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblLastkTiming.setBounds(212, 174, 160, 30);
		lblLastkTiming.setText("Last 10km Timing:");

		Label lblLastHalfMarathon = new Label(shlMarathonPlanner, SWT.NONE);
		lblLastHalfMarathon.setAlignment(SWT.RIGHT);
		lblLastHalfMarathon.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblLastHalfMarathon.setBounds(102, 223, 270, 30);
		lblLastHalfMarathon.setText("Last Half Marathon Timing:");

		Label lblTrackDifficulty = new Label(shlMarathonPlanner, SWT.NONE);
		lblTrackDifficulty.setAlignment(SWT.RIGHT);
		lblTrackDifficulty.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblTrackDifficulty.setBounds(112, 273, 260, 30);
		lblTrackDifficulty.setText("Track Difficult Half Marathon:");

		Label lblDistancePerWeek = new Label(shlMarathonPlanner, SWT.NONE);
		lblDistancePerWeek.setAlignment(SWT.RIGHT);
		lblDistancePerWeek.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblDistancePerWeek.setBounds(172, 389, 200, 30);
		lblDistancePerWeek.setText("Distance Per Week:");

		Label lblYears = new Label(shlMarathonPlanner, SWT.NONE);
		lblYears.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		lblYears.setBounds(478, 60, 81, 25);
		lblYears.setText("years");

		Label lbl10KMinutes = new Label(shlMarathonPlanner, SWT.NONE);
		lbl10KMinutes.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		lbl10KMinutes.setBounds(478, 174, 81, 25);
		lbl10KMinutes.setText("minutes");

		Label lblHalfMarathonMinutes = new Label(shlMarathonPlanner, SWT.NONE);
		lblHalfMarathonMinutes.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		lblHalfMarathonMinutes.setBounds(478, 226, 81, 25);
		lblHalfMarathonMinutes.setText("minutes");

		Label lblMiles = new Label(shlMarathonPlanner, SWT.NONE);
		lblMiles.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		lblMiles.setBounds(479, 389, 81, 25);
		lblMiles.setText("miles");

		Group groupGender = new Group(shlMarathonPlanner, SWT.NONE);
		groupGender.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		groupGender.setBounds(392, 94, 176, 55);

		Group groupTrackDifficulty = new Group(shlMarathonPlanner, SWT.NONE);
		groupTrackDifficulty.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		groupTrackDifficulty.setBounds(392, 260, 176, 107);

		ageText = new Text(shlMarathonPlanner, SWT.BORDER);
		ageText.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		ageText.setBounds(392, 57, 80, 31);

		last10KText = new Text(shlMarathonPlanner, SWT.BORDER);
		last10KText.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		last10KText.setBounds(392, 171, 80, 31);

		last21KText = new Text(shlMarathonPlanner, SWT.BORDER);
		last21KText.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		last21KText.setBounds(392, 223, 80, 31);

		milesPerWeekText = new Text(shlMarathonPlanner, SWT.BORDER);
		milesPerWeekText.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		milesPerWeekText.setBounds(393, 386, 80, 31);

		Button btnMale = new Button(groupGender, SWT.RADIO);
		btnMale.setBounds(106, 27, 67, 25);
		btnMale.setText("Male");

		Button btnFemale = new Button(groupGender, SWT.RADIO);
		btnFemale.setBounds(0, 27, 85, 25);
		btnFemale.setText("Female");

		Button btnLow = new Button(groupTrackDifficulty, SWT.RADIO);
		btnLow.setBounds(11, 10, 61, 25);
		btnLow.setText("Low");

		Button btnMedium = new Button(groupTrackDifficulty, SWT.RADIO);
		btnMedium.setBounds(11, 40, 95, 25);
		btnMedium.setText("Medium");

		Button btnHigh = new Button(groupTrackDifficulty, SWT.RADIO);
		btnHigh.setBounds(10, 71, 134, 25);
		btnHigh.setText("High");

		Button btnPredictTime = new Button(shlMarathonPlanner, SWT.NONE);
		btnPredictTime.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnPredictTime.setBounds(264, 455, 115, 35);
		btnPredictTime.setText("Predict Time");

		Button btnExit = new Button(shlMarathonPlanner, SWT.NONE);
		btnExit.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnExit.setBounds(393, 455, 115, 35);
		btnExit.setText("Exit");

		Button btnHelp = new Button(shlMarathonPlanner, SWT.NONE);
		btnHelp.setBounds(713, 10, 64, 35);
		btnHelp.setText("HELP");

		/*
		 * Event handler to calculate and display the Predicted Time when the Predict
		 * Time button is clicked
		 */

		btnPredictTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ageDouble = Double.parseDouble(ageText.getText());
					last10KDouble = Double.parseDouble(last10KText.getText());
					last21KDouble = Double.parseDouble(last21KText.getText());
					milesPerWeekDouble = Double.parseDouble(milesPerWeekText.getText());

					if (btnFemale.getSelection())
						gender = 1.0;

					if (btnMale.getSelection())
						gender = 0.0;

					if (btnLow.getSelection())
						trackDifficulty = 0.0;

					if (btnMedium.getSelection())
						trackDifficulty = 1.0;

					if (btnHigh.getSelection())
						trackDifficulty = 2.0;

					// Calculate Most Likely Finish Time
					predictedTime = ageDouble * ageRegressionCoefficient + gender * genderRegressionCoefficient
							+ milesPerWeekDouble * milesPerWeekRegressionCoefficient
							+ last21KDouble * last21kRegressionCoefficient
							+ last10KDouble * last10kRegressionCoefficient
							+ trackDifficulty * trackDifficultyRegressionCoefficient;

					MessageBox messageBox = new MessageBox(new Shell(), SWT.OK);
					messageBox.setText("Marathon Time Prediction");

					if (ageDouble < 20 || ageDouble > 40) // Validate Age Input
					{
						String message = "ERROR: Enter Age between 20 and 40";
						messageBox.setMessage(message);
						messageBox.open();
					} else if (last10KDouble < 30 || last10KDouble > 80) // Validate Last 10K Input
					{
						String message = "ERROR: Enter 10K Time between 30 and 80";
						messageBox.setMessage(message);
						messageBox.open();
					} else if (last21KDouble < 59 || last21KDouble > 150) // Validate Half Marathon Time Input
					{
						String message = "ERROR: Enter Half Marathon Time between 59 and 150";
						messageBox.setMessage(message);
						messageBox.open();
					} else if (milesPerWeekDouble < 20 || milesPerWeekDouble > 110) // Validate Distance Input
					{
						String message = "ERROR: Enter Distance between 20 and 110";
						messageBox.setMessage(message);
						messageBox.open();
					} else {
						String message = "Your predicted timing for marathon is " + Math.round(predictedTime)
								+ " minutes.";
						messageBox.setMessage(message);
						messageBox.open();
					}
				} catch (Exception f) {
					f.printStackTrace();
					MessageBox messageBox = new MessageBox(new Shell(), SWT.OK);
					String message = "Please provide all inputs. Do not leave any fields blank.";
					messageBox.setMessage(message);
					messageBox.setText("ERROR!");
					messageBox.open();
				}
			}
		});

		/*
		 * Event handler to exit the program if the Exit Button is clicked
		 */
		btnExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});

		/*
		 * Event handler to launch help
		 */
		btnHelp.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MessageBox messageBox = new MessageBox(new Shell(), SWT.OK);
				String message = "Welcome to the Marathon Finished Time Generator\r\n"
						+ "\nPurpose: The Marathon Finished Time Generator (MFTG) predicts a runner’s finish time based on an individual’s Age, Gender, Training Run Times (10k and half-marathon), Track Difficulty Level, and Distance Ran Per Week. \r\n"
						+ "\nHow to use: \r\n"
						+ "Please enter the following information within the specified parameters\r\n"
						+ "Age: Enter the runner’s age. Age range is from 22 to 44 years old. All ages outside the range will result in error.\r\n"
						+ "Gender: Enter runner’s gender – Male or Female.\r\n"
						+ "Training Run Times (10k): Enter runner’s average 10k run time in minutes. Run-time range is from xx to xx minutes. All run-times outside the range will result in error.\r\n"
						+ "Training Run Times (Half Marathon): Enter runner’s average half-marathon run time in minutes. Run-time range is from xx to xx minutes. All run-times outside the range will result in error.\r\n"
						+ "Track Difficulty Level: Enter runner’s average track difficulty level. Select one: Low, Medium, Hard.\r\n"
						+ "Distance Per Week Enter runner’s average distance per week in miles. Distance range is from xx to xx miles. All distance outside the range will result in error.\r\n"
						+ "\r\n"
						+ "Once all fields have been inputted, press the “Predict Time” button. The MFTG will predict the runner’s finish time in minutes\r\n"
						+ "\nTo exit the MFTG, press the exit button.\r\n" + "";
				messageBox.setMessage(message);
				messageBox.setText("Help");
				messageBox.open();
				messageBox.setText("Help");

			}
		});

	}
}
