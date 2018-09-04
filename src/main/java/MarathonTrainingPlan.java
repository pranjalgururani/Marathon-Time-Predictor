
/* 
 * This class is defined to get regression coefficients from 
 * MultipleLinearRegression class and pass it to the 
 * MarathonTrainingPlanUI Class 
 */
public class MarathonTrainingPlan {

	public void generatePlan(Database db) {

		MultipleLinearRegression regression = new MultipleLinearRegression(db.trainingFactorsArray, db.milesPerWeek);

		MarathonTrainingPlanUI marathonPlanWindow = new MarathonTrainingPlanUI(regression.beta(0), regression.beta(1),
				regression.beta(2), regression.beta(3));

		marathonPlanWindow.planGenerator(marathonPlanWindow);

	}

}
