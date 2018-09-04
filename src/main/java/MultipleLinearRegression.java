
/* This class is used to store and calculate the
 * regression coefficient using Multiple Linear Regression 
 * for each of the factors affecting Marathon timing of a
 * runner. It uses Jama library to perform Matrix calculations.
 */

import Jama.Matrix;
import Jama.QRDecomposition;

public class MultipleLinearRegression {
	private final int predictionMatrixLength;
	private final Matrix beta;
	private double SSE;
	private double SST;

	// Function to calculate regression coefficients for each of the factors
	public MultipleLinearRegression(double[][] marathonFactorsArray, double[] finishTime) {
		if (marathonFactorsArray.length != finishTime.length)
			throw new RuntimeException("dimensions don't agree");
		predictionMatrixLength = finishTime.length;

		Matrix factorMatrix = new Matrix(marathonFactorsArray);

		// Create matrix from vector
		Matrix finishTimeMatrix = new Matrix(finishTime, predictionMatrixLength);

		// Find least squares solution
		QRDecomposition qr = new QRDecomposition(factorMatrix);
		beta = qr.solve(finishTimeMatrix);

		// Mean of finishTime
		double sum = 0.0;
		for (int i = 0; i < predictionMatrixLength; i++)
			sum += finishTime[i];
		double mean = sum / predictionMatrixLength;

		// Total variations
		for (int i = 0; i < predictionMatrixLength; i++) {
			double dev = finishTime[i] - mean;
			SST += dev * dev;
		}

		// Unaccounted variations
		Matrix residuals = factorMatrix.times(beta).minus(finishTimeMatrix);
		SSE = residuals.norm2() * residuals.norm2();

	}

	public double beta(int j) {
		return beta.get(j, 0);
	}

	public double R2() {
		return 1.0 - SSE / SST;
	}

}
