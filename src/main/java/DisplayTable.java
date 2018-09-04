
/* 
* This class is used to create and display a table with the training plan
*/

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class DisplayTable {

	private JFrame frmMarathonTrainingPlanTable;
	private JTable planTable;
	private int predictedMiles;

	public void createTable(DisplayTable newTable) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					newTable.frmMarathonTrainingPlanTable.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DisplayTable(int predictedMiles) {
		this.predictedMiles = predictedMiles;
		initializeTable();
	}

	private void initializeTable() {
		frmMarathonTrainingPlanTable = new JFrame();
		frmMarathonTrainingPlanTable.setTitle("Marathon Training Plan");
		frmMarathonTrainingPlanTable.setBounds(100, 100, 1156, 544);
		frmMarathonTrainingPlanTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMarathonTrainingPlanTable.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 60, 1010, 283);
		frmMarathonTrainingPlanTable.setLocationRelativeTo(null);
		frmMarathonTrainingPlanTable.getContentPane().add(scrollPane);

		String[] tableColumns = { "Week Number", "Day1: Slow Short Run", "Day2: Interval Training", "Day3: Tempo Run",
				"Day4:  FartLek", "Day6: Slow Long Run", "Total Distance" };

		String[][] tableData = new String[16][7];

		/*
		 * dataInt is a temporary array which will store integer values for the table.
		 * These integer values will be later converted to String to display in table.
		 */
		int[][] dataInt = new int[16][7];

		for (int i = 0; i < 16; i++) {
			dataInt[i][0] = i + 1;
		}

		// Calling function to generate Integer Array for weekly miles
		dataInt = getIntegerArray(dataInt, predictedMiles);

		// Converting Integer Array to String array for displaying in the Table
		for (int i = 0; i < 16; i++)
			for (int j = 0; j < 7; j++) {
				if (j == 0)
					tableData[i][j] = Integer.toString(dataInt[i][j]);
				else
					tableData[i][j] = Integer.toString(dataInt[i][j]) + " miles";
			}

		planTable = new JTable(tableData, tableColumns);

		// Centering the table
		DefaultTableCellRenderer tableRenderer = (DefaultTableCellRenderer) planTable.getDefaultRenderer(Object.class);
		tableRenderer.setHorizontalAlignment(JLabel.CENTER);

		planTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(planTable);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(449, 421, 115, 29);
		frmMarathonTrainingPlanTable.getContentPane().add(btnExit);

		/*
		 * Event handler to close the application once the OK button is clicked
		 */
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	// Function definition to generate values for Integer Array from predicted miles
	private int[][] getIntegerArray(int[][] dataInt, int predictedMiles) {

		double partMiles = 0;

		for (int i = 0; i < 16; i++)

		{
			if (i < 2)
				partMiles = .3;
			else if (i > 1 && i < 4)
				partMiles = .4;
			else if (i > 5 && i < 8)
				partMiles = .6;
			else if (i > 7 && i < 10)
				partMiles = .7;
			else if (i > 9 && i < 11)
				partMiles = .8;
			else if (i > 10 && i < 12)
				partMiles = .9;
			else if (i == 12)
				partMiles = 1;
			else
				partMiles = .5;

			dataInt[i][1] = (int) (0.15 * predictedMiles * partMiles);
		}

		for (int i = 0; i < 16; i++)

		{
			if (i < 2)
				partMiles = .3;
			else if (i > 1 && i < 4)
				partMiles = .4;
			else if (i > 5 && i < 8)
				partMiles = .6;
			else if (i > 7 && i < 10)
				partMiles = .7;
			else if (i > 9 && i < 11)
				partMiles = .8;
			else if (i > 10 && i < 12)
				partMiles = .9;
			else if (i == 12)
				partMiles = 1;
			else
				partMiles = .5;

			dataInt[i][2] = (int) (0.1 * predictedMiles * partMiles);
		}

		for (int i = 0; i < 16; i++)

		{
			if (i < 2)
				partMiles = .3;
			else if (i > 1 && i < 4)
				partMiles = .4;
			else if (i > 5 && i < 8)
				partMiles = .6;
			else if (i > 7 && i < 10)
				partMiles = .7;
			else if (i > 9 && i < 11)
				partMiles = .8;
			else if (i > 10 && i < 12)
				partMiles = .9;
			else if (i == 12)
				partMiles = 1;
			else
				partMiles = .5;

			dataInt[i][3] = (int) (0.2 * predictedMiles * partMiles);
		}

		for (int i = 0; i < 16; i++)

		{
			if (i < 2)
				partMiles = .3;
			else if (i > 1 && i < 4)
				partMiles = .4;
			else if (i > 5 && i < 8)
				partMiles = .6;
			else if (i > 7 && i < 10)
				partMiles = .7;
			else if (i > 9 && i < 11)
				partMiles = .8;
			else if (i > 10 && i < 12)
				partMiles = .9;
			else if (i == 12)
				partMiles = 1;
			else
				partMiles = .5;

			dataInt[i][4] = (int) (0.15 * predictedMiles * partMiles);
		}

		for (int i = 0; i < 16; i++)

		{
			if (i < 2)
				partMiles = .3;
			else if (i > 1 && i < 4)
				partMiles = .4;
			else if (i > 5 && i < 8)
				partMiles = .6;
			else if (i > 7 && i < 10)
				partMiles = .7;
			else if (i > 9 && i < 11)
				partMiles = .8;
			else if (i > 10 && i < 12)
				partMiles = .9;
			else if (i == 12)
				partMiles = 1;
			else
				partMiles = .5;

			dataInt[i][5] = (int) (0.4 * predictedMiles * partMiles);
		}

		for (int i = 0; i < 16; i++)

		{
			if (i < 2)
				partMiles = .3;
			else if (i > 1 && i < 4)
				partMiles = .4;
			else if (i > 5 && i < 8)
				partMiles = .6;
			else if (i > 7 && i < 10)
				partMiles = .7;
			else if (i > 9 && i < 11)
				partMiles = .8;
			else if (i > 10 && i < 12)
				partMiles = .9;
			else if (i == 12)
				partMiles = 1;
			else
				partMiles = .5;

			dataInt[i][6] = (int) (predictedMiles * partMiles);
		}
		return dataInt;
	}

}
