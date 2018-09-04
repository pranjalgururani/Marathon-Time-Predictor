
/* 
 * This class is defined to get regression coefficients from 
 * MultipleLinearRegression class and pass it to the 
 * MarathonTimePredictorUI Class 
 */

public class MarathonTimePredictor {

	public void predictTime(Database db) {

		
		MultipleLinearRegression regression = new MultipleLinearRegression(db.marathonFactorsArray, db.finishTime);

		MarathonTimePredictorUI marathonTimePredictorWindow = new MarathonTimePredictorUI(regression.beta(0),
				regression.beta(1), regression.beta(2), regression.beta(3), regression.beta(4), regression.beta(5));

		marathonTimePredictorWindow.openUserInterface();

	
	}

}
