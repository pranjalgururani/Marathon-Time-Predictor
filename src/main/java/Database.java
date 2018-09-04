
/* 
 * This class is used to load the Marathon Runner Data
 * in a MongoDB database instance.
 */
import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Database {

	double[][] marathonFactorsArray = new double[1000][6];
	double[][] trainingFactorsArray = new double[1000][4];

	public double[] age = new double[1000];
	public double[] gender = new double[1000];
	public double[] milesPerWeek = new double[1000];
	public double[] bestTime21k = new double[1000];
	public double[] bestTime10k = new double[1000];
	public double[] trackDifficulty = new double[1000];
	public double[] finishTime = new double[1000];
	public double[] bmi = new double[1000];

	public void generateDatabase() {

		int i = 0;

		String ageString;
		String genderString;
		String milesPerWeekString;
		String bestTime21kString;
		String bestTime10kString;
		String trackDifficultyString;
		String finishTimeString;
		String bmiString;

		try {

			MongoClient marathonMongoClient = new MongoClient("127.0.0.1", 27017);
			MongoDatabase trainingDatabase = marathonMongoClient.getDatabase("Marathon");

			MongoCollection<Document> marathonCollection = trainingDatabase.getCollection("MarathonData");
			marathonCollection.drop();

			/*
			 * Calling function to insert documents to the database. This function has more
			 * than 1000 lines as it creates each of the document in the collection
			 * manually. We have created a database with only 500 documents as a proof of
			 * architecture.
			 */
			createDatabase(marathonCollection);

			FindIterable<Document> iterDoc = marathonCollection.find();

			Iterator<Document> it = iterDoc.iterator();

			// Get all documents from the collection and store in the Array
			while (it.hasNext()) {
				Document document = (Document) it.next();

				ageString = document.getString("Age");
				genderString = document.getString("Gender");
				bmiString = document.getString("BMI");
				milesPerWeekString = document.getString("MilesPerWeek");
				bestTime21kString = document.getString("BestTime21K");
				bestTime10kString = document.getString("BestTime10K");
				trackDifficultyString = document.getString("TrackDifficulty");
				finishTimeString = document.getString("Finish Time");

				age[i] = Double.parseDouble(ageString);
				bmi[i] = Double.parseDouble(bmiString);
				if (genderString.equalsIgnoreCase("Male"))
					gender[i] = 0.0;
				else
					gender[i] = 1.0;
				milesPerWeek[i] = Double.parseDouble(milesPerWeekString);
				bestTime21k[i] = Double.parseDouble(bestTime21kString);
				bestTime10k[i] = Double.parseDouble(bestTime10kString);
				if (trackDifficultyString.equalsIgnoreCase("Low"))
					trackDifficulty[i] = 0.0;
				else if (trackDifficultyString.equalsIgnoreCase("Medium"))
					trackDifficulty[i] = 1.0;
				else
					trackDifficulty[i] = 2.0;
				finishTime[i] = Double.parseDouble(finishTimeString);
				i++;

			}

			// Populating Marathon factors Array
			for (int k = 0; k < 1000; k++) {
				marathonFactorsArray[k][0] = age[k];
				marathonFactorsArray[k][1] = gender[k];
				marathonFactorsArray[k][2] = milesPerWeek[k];
				marathonFactorsArray[k][3] = bestTime21k[k];
				marathonFactorsArray[k][4] = bestTime10k[k];
				marathonFactorsArray[k][5] = trackDifficulty[k];

			}

			// Populating Training factors Array
			for (int k = 0; k < 1000; k++) {
				trainingFactorsArray[k][0] = age[k];
				trainingFactorsArray[k][1] = gender[k];
				trainingFactorsArray[k][2] = bmi[k];
				trainingFactorsArray[k][3] = finishTime[k];

			}

			marathonMongoClient.close();
			System.out.println("Database connection closed");

		} catch (Exception e) {
			System.out.println("An exception has occurred");
		}

	}

	// Function definition to put documents in the database
	private static void createDatabase(MongoCollection<Document> collection) {

		Document marathonRunner1 = new Document("Finish Time", "268").append("Age", "23").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "35").append("BestTime21K", "112")
				.append("BestTime10K", "55").append("TrackDifficulty", "High");
		Document marathonRunner2 = new Document("Finish Time", "181").append("Age", "32").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "76").append("BestTime21K", "101")
				.append("BestTime10K", "41").append("TrackDifficulty", "Low");
		Document marathonRunner3 = new Document("Finish Time", "199").append("Age", "35").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "50").append("BestTime21K", "104")
				.append("BestTime10K", "40").append("TrackDifficulty", "High");
		Document marathonRunner4 = new Document("Finish Time", "287").append("Age", "25").append("Gender", "Male")
				.append("BMI", "25").append("MilesPerWeek", "31").append("BestTime21K", "143")
				.append("BestTime10K", "60").append("TrackDifficulty", "Low");
		Document marathonRunner5 = new Document("Finish Time", "164").append("Age", "33").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "88").append("BestTime21K", "72")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner6 = new Document("Finish Time", "177").append("Age", "23").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "80").append("BestTime21K", "84")
				.append("BestTime10K", "38").append("TrackDifficulty", "Medium");
		Document marathonRunner7 = new Document("Finish Time", "290").append("Age", "31").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "40").append("BestTime21K", "126")
				.append("BestTime10K", "62").append("TrackDifficulty", "Medium");
		Document marathonRunner8 = new Document("Finish Time", "195").append("Age", "24").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "65").append("BestTime21K", "87")
				.append("BestTime10K", "38").append("TrackDifficulty", "Medium");
		Document marathonRunner9 = new Document("Finish Time", "294").append("Age", "26").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "38").append("BestTime21K", "137")
				.append("BestTime10K", "56").append("TrackDifficulty", "Low");
		Document marathonRunner10 = new Document("Finish Time", "169").append("Age", "29").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "76").append("BestTime21K", "72")
				.append("BestTime10K", "37").append("TrackDifficulty", "Medium");
		Document marathonRunner11 = new Document("Finish Time", "182").append("Age", "37").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "54").append("BestTime21K", "95")
				.append("BestTime10K", "40").append("TrackDifficulty", "Low");
		Document marathonRunner12 = new Document("Finish Time", "290").append("Age", "35").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "39").append("BestTime21K", "143")
				.append("BestTime10K", "62").append("TrackDifficulty", "Low");
		Document marathonRunner13 = new Document("Finish Time", "279").append("Age", "26").append("Gender", "Male")
				.append("BMI", "24").append("MilesPerWeek", "36").append("BestTime21K", "134")
				.append("BestTime10K", "65").append("TrackDifficulty", "High");
		Document marathonRunner14 = new Document("Finish Time", "132").append("Age", "25").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "94").append("BestTime21K", "60")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner15 = new Document("Finish Time", "153").append("Age", "29").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "83").append("BestTime21K", "70")
				.append("BestTime10K", "37").append("TrackDifficulty", "Low");
		Document marathonRunner16 = new Document("Finish Time", "202").append("Age", "32").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "52").append("BestTime21K", "88")
				.append("BestTime10K", "40").append("TrackDifficulty", "Low");
		Document marathonRunner17 = new Document("Finish Time", "158").append("Age", "37").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "77").append("BestTime21K", "79")
				.append("BestTime10K", "33").append("TrackDifficulty", "Medium");
		Document marathonRunner18 = new Document("Finish Time", "137").append("Age", "29").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "91").append("BestTime21K", "60")
				.append("BestTime10K", "30").append("TrackDifficulty", "Low");
		Document marathonRunner19 = new Document("Finish Time", "178").append("Age", "30").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "90").append("BestTime21K", "79")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner20 = new Document("Finish Time", "262").append("Age", "29").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "40").append("BestTime21K", "129")
				.append("BestTime10K", "53").append("TrackDifficulty", "Medium");
		Document marathonRunner21 = new Document("Finish Time", "205").append("Age", "32").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "73").append("BestTime21K", "95")
				.append("BestTime10K", "40").append("TrackDifficulty", "High");
		Document marathonRunner22 = new Document("Finish Time", "254").append("Age", "26").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "36").append("BestTime21K", "123")
				.append("BestTime10K", "53").append("TrackDifficulty", "Medium");
		Document marathonRunner23 = new Document("Finish Time", "285").append("Age", "37").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "38").append("BestTime21K", "141")
				.append("BestTime10K", "56").append("TrackDifficulty", "Medium");
		Document marathonRunner24 = new Document("Finish Time", "253").append("Age", "33").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "45").append("BestTime21K", "128")
				.append("BestTime10K", "56").append("TrackDifficulty", "Medium");
		Document marathonRunner25 = new Document("Finish Time", "174").append("Age", "26").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "80").append("BestTime21K", "83")
				.append("BestTime10K", "37").append("TrackDifficulty", "Low");
		Document marathonRunner26 = new Document("Finish Time", "191").append("Age", "24").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "74").append("BestTime21K", "104")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner27 = new Document("Finish Time", "164").append("Age", "33").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "74").append("BestTime21K", "79")
				.append("BestTime10K", "36").append("TrackDifficulty", "Low");
		Document marathonRunner28 = new Document("Finish Time", "257").append("Age", "20").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "37").append("BestTime21K", "128")
				.append("BestTime10K", "50").append("TrackDifficulty", "Low");
		Document marathonRunner29 = new Document("Finish Time", "291").append("Age", "23").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "38").append("BestTime21K", "129")
				.append("BestTime10K", "60").append("TrackDifficulty", "Medium");
		Document marathonRunner30 = new Document("Finish Time", "270").append("Age", "40").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "40").append("BestTime21K", "144")
				.append("BestTime10K", "54").append("TrackDifficulty", "Medium");
		Document marathonRunner31 = new Document("Finish Time", "269").append("Age", "33").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "44").append("BestTime21K", "116")
				.append("BestTime10K", "51").append("TrackDifficulty", "Low");
		Document marathonRunner32 = new Document("Finish Time", "143").append("Age", "21").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "95").append("BestTime21K", "76")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner33 = new Document("Finish Time", "153").append("Age", "24").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "90").append("BestTime21K", "71")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner34 = new Document("Finish Time", "191").append("Age", "30").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "54").append("BestTime21K", "85")
				.append("BestTime10K", "38").append("TrackDifficulty", "Medium");
		Document marathonRunner35 = new Document("Finish Time", "251").append("Age", "27").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "37").append("BestTime21K", "116")
				.append("BestTime10K", "58").append("TrackDifficulty", "High");
		Document marathonRunner36 = new Document("Finish Time", "300").append("Age", "30").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "36").append("BestTime21K", "137")
				.append("BestTime10K", "57").append("TrackDifficulty", "Low");
		Document marathonRunner37 = new Document("Finish Time", "250").append("Age", "36").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "44").append("BestTime21K", "116")
				.append("BestTime10K", "49").append("TrackDifficulty", "High");
		Document marathonRunner38 = new Document("Finish Time", "244").append("Age", "33").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "43").append("BestTime21K", "125")
				.append("BestTime10K", "53").append("TrackDifficulty", "High");
		Document marathonRunner39 = new Document("Finish Time", "163").append("Age", "30").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "71").append("BestTime21K", "76")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner40 = new Document("Finish Time", "273").append("Age", "30").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "36").append("BestTime21K", "127")
				.append("BestTime10K", "64").append("TrackDifficulty", "Medium");
		Document marathonRunner41 = new Document("Finish Time", "297").append("Age", "38").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "38").append("BestTime21K", "131")
				.append("BestTime10K", "54").append("TrackDifficulty", "High");
		Document marathonRunner42 = new Document("Finish Time", "177").append("Age", "20").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "75").append("BestTime21K", "84")
				.append("BestTime10K", "35").append("TrackDifficulty", "Medium");
		Document marathonRunner43 = new Document("Finish Time", "133").append("Age", "38").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "84").append("BestTime21K", "72")
				.append("BestTime10K", "30").append("TrackDifficulty", "Low");
		Document marathonRunner44 = new Document("Finish Time", "266").append("Age", "32").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "45").append("BestTime21K", "114")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner45 = new Document("Finish Time", "195").append("Age", "35").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "76").append("BestTime21K", "96")
				.append("BestTime10K", "38").append("TrackDifficulty", "Low");
		Document marathonRunner46 = new Document("Finish Time", "230").append("Age", "40").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "55").append("BestTime21K", "98")
				.append("BestTime10K", "49").append("TrackDifficulty", "Medium");
		Document marathonRunner47 = new Document("Finish Time", "295").append("Age", "34").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "36").append("BestTime21K", "138")
				.append("BestTime10K", "54").append("TrackDifficulty", "High");
		Document marathonRunner48 = new Document("Finish Time", "143").append("Age", "35").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "99").append("BestTime21K", "63")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner49 = new Document("Finish Time", "180").append("Age", "30").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "61").append("BestTime21K", "107")
				.append("BestTime10K", "40").append("TrackDifficulty", "High");
		Document marathonRunner50 = new Document("Finish Time", "223").append("Age", "35").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "51").append("BestTime21K", "115")
				.append("BestTime10K", "43").append("TrackDifficulty", "High");
		Document marathonRunner51 = new Document("Finish Time", "268").append("Age", "34").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "45").append("BestTime21K", "118")
				.append("BestTime10K", "51").append("TrackDifficulty", "High");
		Document marathonRunner52 = new Document("Finish Time", "288").append("Age", "26").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "35").append("BestTime21K", "126")
				.append("BestTime10K", "55").append("TrackDifficulty", "Medium");
		Document marathonRunner53 = new Document("Finish Time", "154").append("Age", "24").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "80").append("BestTime21K", "85")
				.append("BestTime10K", "36").append("TrackDifficulty", "Medium");
		Document marathonRunner54 = new Document("Finish Time", "188").append("Age", "20").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "71").append("BestTime21K", "104")
				.append("BestTime10K", "38").append("TrackDifficulty", "Medium");
		Document marathonRunner55 = new Document("Finish Time", "278").append("Age", "27").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "34").append("BestTime21K", "131")
				.append("BestTime10K", "61").append("TrackDifficulty", "Medium");
		Document marathonRunner56 = new Document("Finish Time", "131").append("Age", "33").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "90").append("BestTime21K", "73")
				.append("BestTime10K", "32").append("TrackDifficulty", "High");
		Document marathonRunner57 = new Document("Finish Time", "202").append("Age", "39").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "57").append("BestTime21K", "86")
				.append("BestTime10K", "39").append("TrackDifficulty", "Low");
		Document marathonRunner58 = new Document("Finish Time", "150").append("Age", "20").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "87").append("BestTime21K", "62")
				.append("BestTime10K", "31").append("TrackDifficulty", "High");
		Document marathonRunner59 = new Document("Finish Time", "282").append("Age", "22").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "37").append("BestTime21K", "138")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner60 = new Document("Finish Time", "264").append("Age", "25").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "40").append("BestTime21K", "127")
				.append("BestTime10K", "49").append("TrackDifficulty", "High");
		Document marathonRunner61 = new Document("Finish Time", "174").append("Age", "35").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "79").append("BestTime21K", "77")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner62 = new Document("Finish Time", "299").append("Age", "27").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "37").append("BestTime21K", "126")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner63 = new Document("Finish Time", "257").append("Age", "23").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "38").append("BestTime21K", "111")
				.append("BestTime10K", "49").append("TrackDifficulty", "Medium");
		Document marathonRunner64 = new Document("Finish Time", "212").append("Age", "36").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "56").append("BestTime21K", "100")
				.append("BestTime10K", "44").append("TrackDifficulty", "Medium");
		Document marathonRunner65 = new Document("Finish Time", "143").append("Age", "39").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "97").append("BestTime21K", "69")
				.append("BestTime10K", "32").append("TrackDifficulty", "High");
		Document marathonRunner66 = new Document("Finish Time", "252").append("Age", "31").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "45").append("BestTime21K", "115")
				.append("BestTime10K", "51").append("TrackDifficulty", "Medium");
		Document marathonRunner67 = new Document("Finish Time", "239").append("Age", "33").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "60").append("BestTime21K", "97")
				.append("BestTime10K", "50").append("TrackDifficulty", "High");
		Document marathonRunner68 = new Document("Finish Time", "137").append("Age", "30").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "100").append("BestTime21K", "68")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner69 = new Document("Finish Time", "187").append("Age", "34").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "47").append("BestTime21K", "98")
				.append("BestTime10K", "41").append("TrackDifficulty", "Low");
		Document marathonRunner70 = new Document("Finish Time", "123").append("Age", "29").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "94").append("BestTime21K", "64")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner71 = new Document("Finish Time", "154").append("Age", "33").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "85").append("BestTime21K", "75")
				.append("BestTime10K", "34").append("TrackDifficulty", "Low");
		Document marathonRunner72 = new Document("Finish Time", "287").append("Age", "36").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "35").append("BestTime21K", "137")
				.append("BestTime10K", "54").append("TrackDifficulty", "Medium");
		Document marathonRunner73 = new Document("Finish Time", "206").append("Age", "26").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "74").append("BestTime21K", "87")
				.append("BestTime10K", "40").append("TrackDifficulty", "Low");
		Document marathonRunner74 = new Document("Finish Time", "177").append("Age", "30").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "77").append("BestTime21K", "74")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner75 = new Document("Finish Time", "213").append("Age", "40").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "51").append("BestTime21K", "97")
				.append("BestTime10K", "50").append("TrackDifficulty", "Medium");
		Document marathonRunner76 = new Document("Finish Time", "199").append("Age", "36").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "73").append("BestTime21K", "90")
				.append("BestTime10K", "39").append("TrackDifficulty", "Low");
		Document marathonRunner77 = new Document("Finish Time", "129").append("Age", "24").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "94").append("BestTime21K", "69")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner78 = new Document("Finish Time", "293").append("Age", "23").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "36").append("BestTime21K", "127")
				.append("BestTime10K", "56").append("TrackDifficulty", "Low");
		Document marathonRunner79 = new Document("Finish Time", "188").append("Age", "26").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "76").append("BestTime21K", "99")
				.append("BestTime10K", "41").append("TrackDifficulty", "Medium");
		Document marathonRunner80 = new Document("Finish Time", "139").append("Age", "30").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "96").append("BestTime21K", "63")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner81 = new Document("Finish Time", "203").append("Age", "25").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "66").append("BestTime21K", "100")
				.append("BestTime10K", "38").append("TrackDifficulty", "Low");
		Document marathonRunner82 = new Document("Finish Time", "282").append("Age", "22").append("Gender", "Male")
				.append("BMI", "25").append("MilesPerWeek", "37").append("BestTime21K", "144")
				.append("BestTime10K", "59").append("TrackDifficulty", "Medium");
		Document marathonRunner83 = new Document("Finish Time", "156").append("Age", "22").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "85").append("BestTime21K", "79")
				.append("BestTime10K", "36").append("TrackDifficulty", "Low");
		Document marathonRunner84 = new Document("Finish Time", "183").append("Age", "40").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "70").append("BestTime21K", "90")
				.append("BestTime10K", "39").append("TrackDifficulty", "Low");
		Document marathonRunner85 = new Document("Finish Time", "178").append("Age", "30").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "80").append("BestTime21K", "77")
				.append("BestTime10K", "37").append("TrackDifficulty", "High");
		Document marathonRunner86 = new Document("Finish Time", "218").append("Age", "39").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "57").append("BestTime21K", "97")
				.append("BestTime10K", "51").append("TrackDifficulty", "High");
		Document marathonRunner87 = new Document("Finish Time", "186").append("Age", "37").append("Gender", "feMale")
				.append("BMI", "22").append("MilesPerWeek", "62").append("BestTime21K", "103")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner88 = new Document("Finish Time", "128").append("Age", "37").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "95").append("BestTime21K", "62")
				.append("BestTime10K", "32").append("TrackDifficulty", "Medium");
		Document marathonRunner89 = new Document("Finish Time", "283").append("Age", "21").append("Gender", "Male")
				.append("BMI", "25").append("MilesPerWeek", "39").append("BestTime21K", "134")
				.append("BestTime10K", "55").append("TrackDifficulty", "High");
		Document marathonRunner90 = new Document("Finish Time", "172").append("Age", "29").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "84").append("BestTime21K", "85")
				.append("BestTime10K", "35").append("TrackDifficulty", "Medium");
		Document marathonRunner91 = new Document("Finish Time", "279").append("Age", "33").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "38").append("BestTime21K", "125")
				.append("BestTime10K", "60").append("TrackDifficulty", "High");
		Document marathonRunner92 = new Document("Finish Time", "209").append("Age", "20").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "60").append("BestTime21K", "97")
				.append("BestTime10K", "39").append("TrackDifficulty", "Low");
		Document marathonRunner93 = new Document("Finish Time", "208").append("Age", "29").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "72").append("BestTime21K", "91")
				.append("BestTime10K", "44").append("TrackDifficulty", "High");
		Document marathonRunner94 = new Document("Finish Time", "204").append("Age", "24").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "70").append("BestTime21K", "94")
				.append("BestTime10K", "42").append("TrackDifficulty", "High");
		Document marathonRunner95 = new Document("Finish Time", "240").append("Age", "39").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "50").append("BestTime21K", "112")
				.append("BestTime10K", "42").append("TrackDifficulty", "High");
		Document marathonRunner96 = new Document("Finish Time", "132").append("Age", "25").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "88").append("BestTime21K", "66")
				.append("BestTime10K", "30").append("TrackDifficulty", "Low");
		Document marathonRunner97 = new Document("Finish Time", "279").append("Age", "24").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "39").append("BestTime21K", "139")
				.append("BestTime10K", "54").append("TrackDifficulty", "Low");
		Document marathonRunner98 = new Document("Finish Time", "299").append("Age", "30").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "38").append("BestTime21K", "142")
				.append("BestTime10K", "54").append("TrackDifficulty", "High");
		Document marathonRunner99 = new Document("Finish Time", "291").append("Age", "20").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "36").append("BestTime21K", "145")
				.append("BestTime10K", "61").append("TrackDifficulty", "Medium");
		Document marathonRunner100 = new Document("Finish Time", "290").append("Age", "33").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "41").append("BestTime21K", "137")
				.append("BestTime10K", "57").append("TrackDifficulty", "Low");
		Document marathonRunner101 = new Document("Finish Time", "282").append("Age", "29").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "35").append("BestTime21K", "141")
				.append("BestTime10K", "59").append("TrackDifficulty", "High");
		Document marathonRunner102 = new Document("Finish Time", "151").append("Age", "20").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "88").append("BestTime21K", "90")
				.append("BestTime10K", "35").append("TrackDifficulty", "High");
		Document marathonRunner103 = new Document("Finish Time", "250").append("Age", "24").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "42").append("BestTime21K", "123")
				.append("BestTime10K", "54").append("TrackDifficulty", "Low");
		Document marathonRunner104 = new Document("Finish Time", "178").append("Age", "23").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "72").append("BestTime21K", "73")
				.append("BestTime10K", "33").append("TrackDifficulty", "Medium");
		Document marathonRunner105 = new Document("Finish Time", "257").append("Age", "21").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "38").append("BestTime21K", "115")
				.append("BestTime10K", "53").append("TrackDifficulty", "High");
		Document marathonRunner106 = new Document("Finish Time", "167").append("Age", "35").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "76").append("BestTime21K", "77")
				.append("BestTime10K", "35").append("TrackDifficulty", "Medium");
		Document marathonRunner107 = new Document("Finish Time", "292").append("Age", "37").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "41").append("BestTime21K", "132")
				.append("BestTime10K", "61").append("TrackDifficulty", "Low");
		Document marathonRunner108 = new Document("Finish Time", "295").append("Age", "30").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "37").append("BestTime21K", "132")
				.append("BestTime10K", "60").append("TrackDifficulty", "Medium");
		Document marathonRunner109 = new Document("Finish Time", "293").append("Age", "37").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "37").append("BestTime21K", "135")
				.append("BestTime10K", "58").append("TrackDifficulty", "Medium");
		Document marathonRunner110 = new Document("Finish Time", "218").append("Age", "37").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "50").append("BestTime21K", "98")
				.append("BestTime10K", "50").append("TrackDifficulty", "High");
		Document marathonRunner111 = new Document("Finish Time", "127").append("Age", "40").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "95").append("BestTime21K", "71")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner112 = new Document("Finish Time", "284").append("Age", "29").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "39").append("BestTime21K", "142")
				.append("BestTime10K", "55").append("TrackDifficulty", "Low");
		Document marathonRunner113 = new Document("Finish Time", "284").append("Age", "21").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "34").append("BestTime21K", "129")
				.append("BestTime10K", "66").append("TrackDifficulty", "High");
		Document marathonRunner114 = new Document("Finish Time", "120").append("Age", "32").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "85").append("BestTime21K", "71")
				.append("BestTime10K", "30").append("TrackDifficulty", "Low");
		Document marathonRunner115 = new Document("Finish Time", "295").append("Age", "25").append("Gender", "Male")
				.append("BMI", "25").append("MilesPerWeek", "39").append("BestTime21K", "133")
				.append("BestTime10K", "58").append("TrackDifficulty", "Low");
		Document marathonRunner116 = new Document("Finish Time", "260").append("Age", "30").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "42").append("BestTime21K", "125")
				.append("BestTime10K", "57").append("TrackDifficulty", "High");
		Document marathonRunner117 = new Document("Finish Time", "134").append("Age", "26").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "85").append("BestTime21K", "62")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner118 = new Document("Finish Time", "134").append("Age", "35").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "90").append("BestTime21K", "72")
				.append("BestTime10K", "32").append("TrackDifficulty", "High");
		Document marathonRunner119 = new Document("Finish Time", "192").append("Age", "29").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "75").append("BestTime21K", "100")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner120 = new Document("Finish Time", "229").append("Age", "30").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "59").append("BestTime21K", "101")
				.append("BestTime10K", "49").append("TrackDifficulty", "High");
		Document marathonRunner121 = new Document("Finish Time", "179").append("Age", "28").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "78").append("BestTime21K", "86")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner122 = new Document("Finish Time", "198").append("Age", "39").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "53").append("BestTime21K", "95")
				.append("BestTime10K", "38").append("TrackDifficulty", "Low");
		Document marathonRunner123 = new Document("Finish Time", "223").append("Age", "33").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "53").append("BestTime21K", "109")
				.append("BestTime10K", "53").append("TrackDifficulty", "High");
		Document marathonRunner124 = new Document("Finish Time", "120").append("Age", "30").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "90").append("BestTime21K", "63")
				.append("BestTime10K", "31").append("TrackDifficulty", "Medium");
		Document marathonRunner125 = new Document("Finish Time", "129").append("Age", "22").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "94").append("BestTime21K", "69")
				.append("BestTime10K", "31").append("TrackDifficulty", "High");
		Document marathonRunner126 = new Document("Finish Time", "171").append("Age", "22").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "88").append("BestTime21K", "77")
				.append("BestTime10K", "34").append("TrackDifficulty", "Medium");
		Document marathonRunner127 = new Document("Finish Time", "133").append("Age", "30").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "88").append("BestTime21K", "67")
				.append("BestTime10K", "30").append("TrackDifficulty", "Low");
		Document marathonRunner128 = new Document("Finish Time", "206").append("Age", "20").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "67").append("BestTime21K", "103")
				.append("BestTime10K", "39").append("TrackDifficulty", "Low");
		Document marathonRunner129 = new Document("Finish Time", "240").append("Age", "30").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "45").append("BestTime21K", "126")
				.append("BestTime10K", "56").append("TrackDifficulty", "Low");
		Document marathonRunner130 = new Document("Finish Time", "168").append("Age", "21").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "79").append("BestTime21K", "81")
				.append("BestTime10K", "36").append("TrackDifficulty", "High");
		Document marathonRunner131 = new Document("Finish Time", "277").append("Age", "35").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "41").append("BestTime21K", "136")
				.append("BestTime10K", "54").append("TrackDifficulty", "Low");
		Document marathonRunner132 = new Document("Finish Time", "242").append("Age", "31").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "44").append("BestTime21K", "122")
				.append("BestTime10K", "55").append("TrackDifficulty", "Low");
		Document marathonRunner133 = new Document("Finish Time", "256").append("Age", "39").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "44").append("BestTime21K", "112")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner134 = new Document("Finish Time", "285").append("Age", "28").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "40").append("BestTime21K", "130")
				.append("BestTime10K", "54").append("TrackDifficulty", "High");
		Document marathonRunner135 = new Document("Finish Time", "280").append("Age", "25").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "35").append("BestTime21K", "131")
				.append("BestTime10K", "57").append("TrackDifficulty", "Medium");
		Document marathonRunner136 = new Document("Finish Time", "246").append("Age", "34").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "44").append("BestTime21K", "123")
				.append("BestTime10K", "54").append("TrackDifficulty", "Low");
		Document marathonRunner137 = new Document("Finish Time", "188").append("Age", "26").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "77").append("BestTime21K", "99")
				.append("BestTime10K", "42").append("TrackDifficulty", "Medium");
		Document marathonRunner138 = new Document("Finish Time", "192").append("Age", "23").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "62").append("BestTime21K", "97")
				.append("BestTime10K", "42").append("TrackDifficulty", "High");
		Document marathonRunner139 = new Document("Finish Time", "201").append("Age", "23").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "70").append("BestTime21K", "91")
				.append("BestTime10K", "40").append("TrackDifficulty", "Low");
		Document marathonRunner140 = new Document("Finish Time", "137").append("Age", "23").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "82").append("BestTime21K", "61")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner141 = new Document("Finish Time", "225").append("Age", "33").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "60").append("BestTime21K", "106")
				.append("BestTime10K", "45").append("TrackDifficulty", "High");
		Document marathonRunner142 = new Document("Finish Time", "162").append("Age", "31").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "71").append("BestTime21K", "82")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner143 = new Document("Finish Time", "239").append("Age", "30").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "57").append("BestTime21K", "106")
				.append("BestTime10K", "46").append("TrackDifficulty", "High");
		Document marathonRunner144 = new Document("Finish Time", "284").append("Age", "28").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "36").append("BestTime21K", "139")
				.append("BestTime10K", "54").append("TrackDifficulty", "Low");
		Document marathonRunner145 = new Document("Finish Time", "270").append("Age", "39").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "45").append("BestTime21K", "127")
				.append("BestTime10K", "50").append("TrackDifficulty", "Low");
		Document marathonRunner146 = new Document("Finish Time", "218").append("Age", "34").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "54").append("BestTime21K", "113")
				.append("BestTime10K", "52").append("TrackDifficulty", "High");
		Document marathonRunner147 = new Document("Finish Time", "125").append("Age", "32").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "98").append("BestTime21K", "72")
				.append("BestTime10K", "32").append("TrackDifficulty", "Medium");
		Document marathonRunner148 = new Document("Finish Time", "263").append("Age", "32").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "45").append("BestTime21K", "126")
				.append("BestTime10K", "54").append("TrackDifficulty", "High");
		Document marathonRunner149 = new Document("Finish Time", "257").append("Age", "25").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "36").append("BestTime21K", "113")
				.append("BestTime10K", "49").append("TrackDifficulty", "Medium");
		Document marathonRunner150 = new Document("Finish Time", "190").append("Age", "35").append("Gender", "feMale")
				.append("BMI", "18").append("MilesPerWeek", "64").append("BestTime21K", "86")
				.append("BestTime10K", "38").append("TrackDifficulty", "Medium");
		Document marathonRunner151 = new Document("Finish Time", "199").append("Age", "25").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "72").append("BestTime21K", "106")
				.append("BestTime10K", "41").append("TrackDifficulty", "Medium");
		Document marathonRunner152 = new Document("Finish Time", "243").append("Age", "36").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "43").append("BestTime21K", "125")
				.append("BestTime10K", "50").append("TrackDifficulty", "Low");
		Document marathonRunner153 = new Document("Finish Time", "155").append("Age", "30").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "74").append("BestTime21K", "82")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner154 = new Document("Finish Time", "142").append("Age", "38").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "81").append("BestTime21K", "60")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner155 = new Document("Finish Time", "157").append("Age", "39").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "77").append("BestTime21K", "77")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner156 = new Document("Finish Time", "132").append("Age", "31").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "99").append("BestTime21K", "74")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner157 = new Document("Finish Time", "245").append("Age", "34").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "43").append("BestTime21K", "117")
				.append("BestTime10K", "55").append("TrackDifficulty", "Low");
		Document marathonRunner158 = new Document("Finish Time", "280").append("Age", "40").append("Gender", "Male")
				.append("BMI", "24").append("MilesPerWeek", "38").append("BestTime21K", "135")
				.append("BestTime10K", "65").append("TrackDifficulty", "High");
		Document marathonRunner159 = new Document("Finish Time", "271").append("Age", "38").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "38").append("BestTime21K", "126")
				.append("BestTime10K", "62").append("TrackDifficulty", "Medium");
		Document marathonRunner160 = new Document("Finish Time", "272").append("Age", "27").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "31").append("BestTime21K", "133")
				.append("BestTime10K", "60").append("TrackDifficulty", "Medium");
		Document marathonRunner161 = new Document("Finish Time", "149").append("Age", "38").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "99").append("BestTime21K", "72")
				.append("BestTime10K", "32").append("TrackDifficulty", "Medium");
		Document marathonRunner162 = new Document("Finish Time", "210").append("Age", "40").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "58").append("BestTime21K", "102")
				.append("BestTime10K", "48").append("TrackDifficulty", "Medium");
		Document marathonRunner163 = new Document("Finish Time", "275").append("Age", "29").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "37").append("BestTime21K", "126")
				.append("BestTime10K", "66").append("TrackDifficulty", "High");
		Document marathonRunner164 = new Document("Finish Time", "135").append("Age", "31").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "86").append("BestTime21K", "64")
				.append("BestTime10K", "31").append("TrackDifficulty", "High");
		Document marathonRunner165 = new Document("Finish Time", "122").append("Age", "32").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "100").append("BestTime21K", "60")
				.append("BestTime10K", "32").append("TrackDifficulty", "Medium");
		Document marathonRunner166 = new Document("Finish Time", "195").append("Age", "36").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "61").append("BestTime21K", "90")
				.append("BestTime10K", "38").append("TrackDifficulty", "Low");
		Document marathonRunner167 = new Document("Finish Time", "300").append("Age", "27").append("Gender", "Male")
				.append("BMI", "24").append("MilesPerWeek", "38").append("BestTime21K", "134")
				.append("BestTime10K", "63").append("TrackDifficulty", "Low");
		Document marathonRunner168 = new Document("Finish Time", "287").append("Age", "39").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "40").append("BestTime21K", "128")
				.append("BestTime10K", "64").append("TrackDifficulty", "High");
		Document marathonRunner169 = new Document("Finish Time", "134").append("Age", "29").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "78").append("BestTime21K", "68")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner170 = new Document("Finish Time", "156").append("Age", "34").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "75").append("BestTime21K", "76")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner171 = new Document("Finish Time", "180").append("Age", "27").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "76").append("BestTime21K", "79")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner172 = new Document("Finish Time", "150").append("Age", "22").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "86").append("BestTime21K", "73")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner173 = new Document("Finish Time", "167").append("Age", "37").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "75").append("BestTime21K", "75")
				.append("BestTime10K", "33").append("TrackDifficulty", "Medium");
		Document marathonRunner174 = new Document("Finish Time", "279").append("Age", "26").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "32").append("BestTime21K", "146")
				.append("BestTime10K", "55").append("TrackDifficulty", "Medium");
		Document marathonRunner175 = new Document("Finish Time", "148").append("Age", "30").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "98").append("BestTime21K", "73")
				.append("BestTime10K", "31").append("TrackDifficulty", "Medium");
		Document marathonRunner176 = new Document("Finish Time", "298").append("Age", "21").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "36").append("BestTime21K", "144")
				.append("BestTime10K", "54").append("TrackDifficulty", "Medium");
		Document marathonRunner177 = new Document("Finish Time", "289").append("Age", "29").append("Gender", "Male")
				.append("BMI", "25").append("MilesPerWeek", "33").append("BestTime21K", "140")
				.append("BestTime10K", "62").append("TrackDifficulty", "Low");
		Document marathonRunner178 = new Document("Finish Time", "176").append("Age", "38").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "70").append("BestTime21K", "81")
				.append("BestTime10K", "35").append("TrackDifficulty", "High");
		Document marathonRunner179 = new Document("Finish Time", "156").append("Age", "31").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "83").append("BestTime21K", "78")
				.append("BestTime10K", "36").append("TrackDifficulty", "High");
		Document marathonRunner180 = new Document("Finish Time", "145").append("Age", "25").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "82").append("BestTime21K", "60")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner181 = new Document("Finish Time", "146").append("Age", "26").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "89").append("BestTime21K", "61")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner182 = new Document("Finish Time", "156").append("Age", "33").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "79").append("BestTime21K", "73")
				.append("BestTime10K", "35").append("TrackDifficulty", "Medium");
		Document marathonRunner183 = new Document("Finish Time", "270").append("Age", "20").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "44").append("BestTime21K", "123")
				.append("BestTime10K", "52").append("TrackDifficulty", "High");
		Document marathonRunner184 = new Document("Finish Time", "154").append("Age", "38").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "75").append("BestTime21K", "73")
				.append("BestTime10K", "34").append("TrackDifficulty", "High");
		Document marathonRunner185 = new Document("Finish Time", "166").append("Age", "30").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "83").append("BestTime21K", "78")
				.append("BestTime10K", "38").append("TrackDifficulty", "Medium");
		Document marathonRunner186 = new Document("Finish Time", "174").append("Age", "30").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "87").append("BestTime21K", "87")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner187 = new Document("Finish Time", "292").append("Age", "31").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "36").append("BestTime21K", "128")
				.append("BestTime10K", "57").append("TrackDifficulty", "Medium");
		Document marathonRunner188 = new Document("Finish Time", "143").append("Age", "36").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "81").append("BestTime21K", "76")
				.append("BestTime10K", "32").append("TrackDifficulty", "High");
		Document marathonRunner189 = new Document("Finish Time", "248").append("Age", "26").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "41").append("BestTime21K", "114")
				.append("BestTime10K", "57").append("TrackDifficulty", "High");
		Document marathonRunner190 = new Document("Finish Time", "179").append("Age", "37").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "80").append("BestTime21K", "86")
				.append("BestTime10K", "36").append("TrackDifficulty", "Medium");
		Document marathonRunner191 = new Document("Finish Time", "176").append("Age", "33").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "71").append("BestTime21K", "81")
				.append("BestTime10K", "34").append("TrackDifficulty", "Low");
		Document marathonRunner192 = new Document("Finish Time", "294").append("Age", "31").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "38").append("BestTime21K", "137")
				.append("BestTime10K", "61").append("TrackDifficulty", "Low");
		Document marathonRunner193 = new Document("Finish Time", "247").append("Age", "31").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "45").append("BestTime21K", "114")
				.append("BestTime10K", "53").append("TrackDifficulty", "Low");
		Document marathonRunner194 = new Document("Finish Time", "294").append("Age", "27").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "38").append("BestTime21K", "130")
				.append("BestTime10K", "64").append("TrackDifficulty", "High");
		Document marathonRunner195 = new Document("Finish Time", "266").append("Age", "33").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "44").append("BestTime21K", "126")
				.append("BestTime10K", "48").append("TrackDifficulty", "Low");
		Document marathonRunner196 = new Document("Finish Time", "154").append("Age", "25").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "88").append("BestTime21K", "87")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner197 = new Document("Finish Time", "160").append("Age", "35").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "84").append("BestTime21K", "83")
				.append("BestTime10K", "34").append("TrackDifficulty", "High");
		Document marathonRunner198 = new Document("Finish Time", "252").append("Age", "38").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "43").append("BestTime21K", "126")
				.append("BestTime10K", "50").append("TrackDifficulty", "High");
		Document marathonRunner199 = new Document("Finish Time", "141").append("Age", "36").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "98").append("BestTime21K", "61")
				.append("BestTime10K", "31").append("TrackDifficulty", "Medium");
		Document marathonRunner200 = new Document("Finish Time", "286").append("Age", "31").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "42").append("BestTime21K", "139")
				.append("BestTime10K", "62").append("TrackDifficulty", "Low");
		Document marathonRunner201 = new Document("Finish Time", "279").append("Age", "36").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "38").append("BestTime21K", "136")
				.append("BestTime10K", "65").append("TrackDifficulty", "High");
		Document marathonRunner202 = new Document("Finish Time", "265").append("Age", "39").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "43").append("BestTime21K", "115")
				.append("BestTime10K", "56").append("TrackDifficulty", "Low");
		Document marathonRunner203 = new Document("Finish Time", "174").append("Age", "23").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "90").append("BestTime21K", "80")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner204 = new Document("Finish Time", "139").append("Age", "30").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "100").append("BestTime21K", "71")
				.append("BestTime10K", "32").append("TrackDifficulty", "Medium");
		Document marathonRunner205 = new Document("Finish Time", "138").append("Age", "21").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "84").append("BestTime21K", "61")
				.append("BestTime10K", "31").append("TrackDifficulty", "Medium");
		Document marathonRunner206 = new Document("Finish Time", "290").append("Age", "38").append("Gender", "Male")
				.append("BMI", "24").append("MilesPerWeek", "39").append("BestTime21K", "126")
				.append("BestTime10K", "60").append("TrackDifficulty", "Medium");
		Document marathonRunner207 = new Document("Finish Time", "201").append("Age", "40").append("Gender", "feMale")
				.append("BMI", "21").append("MilesPerWeek", "73").append("BestTime21K", "100")
				.append("BestTime10K", "41").append("TrackDifficulty", "High");
		Document marathonRunner208 = new Document("Finish Time", "145").append("Age", "29").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "87").append("BestTime21K", "71")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner209 = new Document("Finish Time", "186").append("Age", "26").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "62").append("BestTime21K", "94")
				.append("BestTime10K", "44").append("TrackDifficulty", "High");
		Document marathonRunner210 = new Document("Finish Time", "196").append("Age", "28").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "63").append("BestTime21K", "100")
				.append("BestTime10K", "40").append("TrackDifficulty", "Low");
		Document marathonRunner211 = new Document("Finish Time", "289").append("Age", "24").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "37").append("BestTime21K", "142")
				.append("BestTime10K", "56").append("TrackDifficulty", "Medium");
		Document marathonRunner212 = new Document("Finish Time", "262").append("Age", "25").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "43").append("BestTime21K", "122")
				.append("BestTime10K", "48").append("TrackDifficulty", "High");
		Document marathonRunner213 = new Document("Finish Time", "249").append("Age", "29").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "39").append("BestTime21K", "123")
				.append("BestTime10K", "49").append("TrackDifficulty", "Low");
		Document marathonRunner214 = new Document("Finish Time", "133").append("Age", "20").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "91").append("BestTime21K", "68")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner215 = new Document("Finish Time", "155").append("Age", "40").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "79").append("BestTime21K", "80")
				.append("BestTime10K", "34").append("TrackDifficulty", "Medium");
		Document marathonRunner216 = new Document("Finish Time", "291").append("Age", "29").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "35").append("BestTime21K", "127")
				.append("BestTime10K", "62").append("TrackDifficulty", "Low");
		Document marathonRunner217 = new Document("Finish Time", "271").append("Age", "30").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "36").append("BestTime21K", "133")
				.append("BestTime10K", "62").append("TrackDifficulty", "Medium");
		Document marathonRunner218 = new Document("Finish Time", "149").append("Age", "23").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "93").append("BestTime21K", "67")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner219 = new Document("Finish Time", "189").append("Age", "30").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "77").append("BestTime21K", "98")
				.append("BestTime10K", "38").append("TrackDifficulty", "Low");
		Document marathonRunner220 = new Document("Finish Time", "189").append("Age", "34").append("Gender", "feMale")
				.append("BMI", "19").append("MilesPerWeek", "54").append("BestTime21K", "94")
				.append("BestTime10K", "41").append("TrackDifficulty", "Medium");
		Document marathonRunner221 = new Document("Finish Time", "199").append("Age", "30").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "69").append("BestTime21K", "86")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner222 = new Document("Finish Time", "120").append("Age", "25").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "83").append("BestTime21K", "67")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner223 = new Document("Finish Time", "280").append("Age", "39").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "41").append("BestTime21K", "134")
				.append("BestTime10K", "58").append("TrackDifficulty", "Low");
		Document marathonRunner224 = new Document("Finish Time", "189").append("Age", "38").append("Gender", "feMale")
				.append("BMI", "18").append("MilesPerWeek", "56").append("BestTime21K", "92")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner225 = new Document("Finish Time", "283").append("Age", "20").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "36").append("BestTime21K", "130")
				.append("BestTime10K", "57").append("TrackDifficulty", "High");
		Document marathonRunner226 = new Document("Finish Time", "197").append("Age", "27").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "69").append("BestTime21K", "101")
				.append("BestTime10K", "40").append("TrackDifficulty", "Medium");
		Document marathonRunner227 = new Document("Finish Time", "194").append("Age", "28").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "73").append("BestTime21K", "92")
				.append("BestTime10K", "41").append("TrackDifficulty", "Low");
		Document marathonRunner228 = new Document("Finish Time", "207").append("Age", "38").append("Gender", "feMale")
				.append("BMI", "18").append("MilesPerWeek", "61").append("BestTime21K", "93")
				.append("BestTime10K", "40").append("TrackDifficulty", "High");
		Document marathonRunner229 = new Document("Finish Time", "249").append("Age", "36").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "45").append("BestTime21K", "126")
				.append("BestTime10K", "50").append("TrackDifficulty", "Low");
		Document marathonRunner230 = new Document("Finish Time", "257").append("Age", "34").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "45").append("BestTime21K", "114")
				.append("BestTime10K", "52").append("TrackDifficulty", "High");
		Document marathonRunner231 = new Document("Finish Time", "198").append("Age", "30").append("Gender", "feMale")
				.append("BMI", "19").append("MilesPerWeek", "72").append("BestTime21K", "96")
				.append("BestTime10K", "39").append("TrackDifficulty", "Medium");
		Document marathonRunner232 = new Document("Finish Time", "190").append("Age", "23").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "59").append("BestTime21K", "99")
				.append("BestTime10K", "39").append("TrackDifficulty", "Medium");
		Document marathonRunner233 = new Document("Finish Time", "222").append("Age", "37").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "54").append("BestTime21K", "106")
				.append("BestTime10K", "47").append("TrackDifficulty", "Medium");
		Document marathonRunner234 = new Document("Finish Time", "246").append("Age", "32").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "44").append("BestTime21K", "124")
				.append("BestTime10K", "50").append("TrackDifficulty", "Low");
		Document marathonRunner235 = new Document("Finish Time", "297").append("Age", "20").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "35").append("BestTime21K", "135")
				.append("BestTime10K", "61").append("TrackDifficulty", "High");
		Document marathonRunner236 = new Document("Finish Time", "281").append("Age", "25").append("Gender", "Male")
				.append("BMI", "24").append("MilesPerWeek", "33").append("BestTime21K", "135")
				.append("BestTime10K", "60").append("TrackDifficulty", "High");
		Document marathonRunner237 = new Document("Finish Time", "142").append("Age", "23").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "84").append("BestTime21K", "66")
				.append("BestTime10K", "32").append("TrackDifficulty", "High");
		Document marathonRunner238 = new Document("Finish Time", "291").append("Age", "28").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "35").append("BestTime21K", "136")
				.append("BestTime10K", "64").append("TrackDifficulty", "Medium");
		Document marathonRunner239 = new Document("Finish Time", "244").append("Age", "21").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "45").append("BestTime21K", "120")
				.append("BestTime10K", "50").append("TrackDifficulty", "Medium");
		Document marathonRunner240 = new Document("Finish Time", "130").append("Age", "26").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "83").append("BestTime21K", "76")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner241 = new Document("Finish Time", "165").append("Age", "38").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "73").append("BestTime21K", "70")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner242 = new Document("Finish Time", "277").append("Age", "38").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "41").append("BestTime21K", "128")
				.append("BestTime10K", "57").append("TrackDifficulty", "Low");
		Document marathonRunner243 = new Document("Finish Time", "185").append("Age", "38").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "63").append("BestTime21K", "95")
				.append("BestTime10K", "38").append("TrackDifficulty", "Medium");
		Document marathonRunner244 = new Document("Finish Time", "166").append("Age", "22").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "76").append("BestTime21K", "70")
				.append("BestTime10K", "34").append("TrackDifficulty", "Low");
		Document marathonRunner245 = new Document("Finish Time", "276").append("Age", "24").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "34").append("BestTime21K", "138")
				.append("BestTime10K", "61").append("TrackDifficulty", "Medium");
		Document marathonRunner246 = new Document("Finish Time", "175").append("Age", "20").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "78").append("BestTime21K", "81")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner247 = new Document("Finish Time", "279").append("Age", "27").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "39").append("BestTime21K", "135")
				.append("BestTime10K", "54").append("TrackDifficulty", "High");
		Document marathonRunner248 = new Document("Finish Time", "291").append("Age", "39").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "39").append("BestTime21K", "137")
				.append("BestTime10K", "62").append("TrackDifficulty", "Medium");
		Document marathonRunner249 = new Document("Finish Time", "183").append("Age", "40").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "45").append("BestTime21K", "101")
				.append("BestTime10K", "41").append("TrackDifficulty", "Medium");
		Document marathonRunner250 = new Document("Finish Time", "251").append("Age", "27").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "41").append("BestTime21K", "110")
				.append("BestTime10K", "49").append("TrackDifficulty", "Medium");
		Document marathonRunner251 = new Document("Finish Time", "288").append("Age", "23").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "38").append("BestTime21K", "133")
				.append("BestTime10K", "64").append("TrackDifficulty", "Medium");
		Document marathonRunner252 = new Document("Finish Time", "285").append("Age", "25").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "38").append("BestTime21K", "133")
				.append("BestTime10K", "64").append("TrackDifficulty", "Medium");
		Document marathonRunner253 = new Document("Finish Time", "206").append("Age", "40").append("Gender", "feMale")
				.append("BMI", "20").append("MilesPerWeek", "64").append("BestTime21K", "92")
				.append("BestTime10K", "40").append("TrackDifficulty", "Medium");
		Document marathonRunner254 = new Document("Finish Time", "152").append("Age", "39").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "72").append("BestTime21K", "76")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner255 = new Document("Finish Time", "261").append("Age", "29").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "45").append("BestTime21K", "112")
				.append("BestTime10K", "52").append("TrackDifficulty", "Low");
		Document marathonRunner256 = new Document("Finish Time", "180").append("Age", "34").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "88").append("BestTime21K", "74")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner257 = new Document("Finish Time", "184").append("Age", "36").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "52").append("BestTime21K", "91")
				.append("BestTime10K", "38").append("TrackDifficulty", "Low");
		Document marathonRunner258 = new Document("Finish Time", "149").append("Age", "32").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "94").append("BestTime21K", "63")
				.append("BestTime10K", "31").append("TrackDifficulty", "Medium");
		Document marathonRunner259 = new Document("Finish Time", "217").append("Age", "34").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "52").append("BestTime21K", "110")
				.append("BestTime10K", "44").append("TrackDifficulty", "High");
		Document marathonRunner260 = new Document("Finish Time", "273").append("Age", "30").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "38").append("BestTime21K", "139")
				.append("BestTime10K", "60").append("TrackDifficulty", "Low");
		Document marathonRunner261 = new Document("Finish Time", "128").append("Age", "20").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "91").append("BestTime21K", "68")
				.append("BestTime10K", "32").append("TrackDifficulty", "Medium");
		Document marathonRunner262 = new Document("Finish Time", "207").append("Age", "30").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "69").append("BestTime21K", "92")
				.append("BestTime10K", "40").append("TrackDifficulty", "Low");
		Document marathonRunner263 = new Document("Finish Time", "260").append("Age", "22").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "35").append("BestTime21K", "121")
				.append("BestTime10K", "53").append("TrackDifficulty", "Medium");
		Document marathonRunner264 = new Document("Finish Time", "135").append("Age", "39").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "91").append("BestTime21K", "66")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner265 = new Document("Finish Time", "198").append("Age", "29").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "60").append("BestTime21K", "105")
				.append("BestTime10K", "39").append("TrackDifficulty", "Medium");
		Document marathonRunner266 = new Document("Finish Time", "167").append("Age", "23").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "82").append("BestTime21K", "87")
				.append("BestTime10K", "36").append("TrackDifficulty", "Medium");
		Document marathonRunner267 = new Document("Finish Time", "177").append("Age", "36").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "71").append("BestTime21K", "80")
				.append("BestTime10K", "36").append("TrackDifficulty", "Low");
		Document marathonRunner268 = new Document("Finish Time", "300").append("Age", "38").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "38").append("BestTime21K", "137")
				.append("BestTime10K", "60").append("TrackDifficulty", "Medium");
		Document marathonRunner269 = new Document("Finish Time", "299").append("Age", "27").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "39").append("BestTime21K", "133")
				.append("BestTime10K", "58").append("TrackDifficulty", "Low");
		Document marathonRunner270 = new Document("Finish Time", "296").append("Age", "28").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "35").append("BestTime21K", "126")
				.append("BestTime10K", "64").append("TrackDifficulty", "High");
		Document marathonRunner271 = new Document("Finish Time", "253").append("Age", "28").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "45").append("BestTime21K", "115")
				.append("BestTime10K", "49").append("TrackDifficulty", "Low");
		Document marathonRunner272 = new Document("Finish Time", "200").append("Age", "27").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "74").append("BestTime21K", "100")
				.append("BestTime10K", "38").append("TrackDifficulty", "Low");
		Document marathonRunner273 = new Document("Finish Time", "192").append("Age", "30").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "47").append("BestTime21K", "85")
				.append("BestTime10K", "38").append("TrackDifficulty", "Low");
		Document marathonRunner274 = new Document("Finish Time", "170").append("Age", "30").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "78").append("BestTime21K", "83")
				.append("BestTime10K", "34").append("TrackDifficulty", "Medium");
		Document marathonRunner275 = new Document("Finish Time", "223").append("Age", "38").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "52").append("BestTime21K", "100")
				.append("BestTime10K", "44").append("TrackDifficulty", "Medium");
		Document marathonRunner276 = new Document("Finish Time", "250").append("Age", "24").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "35").append("BestTime21K", "118")
				.append("BestTime10K", "55").append("TrackDifficulty", "Low");
		Document marathonRunner277 = new Document("Finish Time", "200").append("Age", "27").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "66").append("BestTime21K", "89")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner278 = new Document("Finish Time", "188").append("Age", "21").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "61").append("BestTime21K", "106")
				.append("BestTime10K", "43").append("TrackDifficulty", "High");
		Document marathonRunner279 = new Document("Finish Time", "253").append("Age", "20").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "39").append("BestTime21K", "117")
				.append("BestTime10K", "59").append("TrackDifficulty", "High");
		Document marathonRunner280 = new Document("Finish Time", "156").append("Age", "30").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "77").append("BestTime21K", "79")
				.append("BestTime10K", "34").append("TrackDifficulty", "Low");
		Document marathonRunner281 = new Document("Finish Time", "291").append("Age", "31").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "40").append("BestTime21K", "136")
				.append("BestTime10K", "60").append("TrackDifficulty", "Low");
		Document marathonRunner282 = new Document("Finish Time", "281").append("Age", "37").append("Gender", "Male")
				.append("BMI", "24").append("MilesPerWeek", "39").append("BestTime21K", "139")
				.append("BestTime10K", "58").append("TrackDifficulty", "High");
		Document marathonRunner283 = new Document("Finish Time", "297").append("Age", "33").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "37").append("BestTime21K", "144")
				.append("BestTime10K", "59").append("TrackDifficulty", "High");
		Document marathonRunner284 = new Document("Finish Time", "282").append("Age", "37").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "39").append("BestTime21K", "129")
				.append("BestTime10K", "64").append("TrackDifficulty", "High");
		Document marathonRunner285 = new Document("Finish Time", "196").append("Age", "26").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "72").append("BestTime21K", "92")
				.append("BestTime10K", "39").append("TrackDifficulty", "Low");
		Document marathonRunner286 = new Document("Finish Time", "225").append("Age", "31").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "60").append("BestTime21K", "114")
				.append("BestTime10K", "46").append("TrackDifficulty", "Medium");
		Document marathonRunner287 = new Document("Finish Time", "242").append("Age", "20").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "40").append("BestTime21K", "120")
				.append("BestTime10K", "48").append("TrackDifficulty", "Low");
		Document marathonRunner288 = new Document("Finish Time", "259").append("Age", "33").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "44").append("BestTime21K", "113")
				.append("BestTime10K", "52").append("TrackDifficulty", "Medium");
		Document marathonRunner289 = new Document("Finish Time", "126").append("Age", "36").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "80").append("BestTime21K", "71")
				.append("BestTime10K", "30").append("TrackDifficulty", "Low");
		Document marathonRunner290 = new Document("Finish Time", "270").append("Age", "34").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "38").append("BestTime21K", "129")
				.append("BestTime10K", "60").append("TrackDifficulty", "Low");
		Document marathonRunner291 = new Document("Finish Time", "160").append("Age", "30").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "77").append("BestTime21K", "80")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner292 = new Document("Finish Time", "242").append("Age", "40").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "44").append("BestTime21K", "114")
				.append("BestTime10K", "49").append("TrackDifficulty", "Low");
		Document marathonRunner293 = new Document("Finish Time", "145").append("Age", "32").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "91").append("BestTime21K", "70")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner294 = new Document("Finish Time", "198").append("Age", "34").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "54").append("BestTime21K", "89")
				.append("BestTime10K", "39").append("TrackDifficulty", "Low");
		Document marathonRunner295 = new Document("Finish Time", "206").append("Age", "20").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "63").append("BestTime21K", "95")
				.append("BestTime10K", "42").append("TrackDifficulty", "Medium");
		Document marathonRunner296 = new Document("Finish Time", "159").append("Age", "40").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "73").append("BestTime21K", "83")
				.append("BestTime10K", "35").append("TrackDifficulty", "High");
		Document marathonRunner297 = new Document("Finish Time", "132").append("Age", "25").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "87").append("BestTime21K", "61")
				.append("BestTime10K", "32").append("TrackDifficulty", "Low");
		Document marathonRunner298 = new Document("Finish Time", "277").append("Age", "20").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "39").append("BestTime21K", "146")
				.append("BestTime10K", "59").append("TrackDifficulty", "High");
		Document marathonRunner299 = new Document("Finish Time", "156").append("Age", "39").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "72").append("BestTime21K", "72")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner300 = new Document("Finish Time", "277").append("Age", "25").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "31").append("BestTime21K", "129")
				.append("BestTime10K", "63").append("TrackDifficulty", "Low");
		Document marathonRunner301 = new Document("Finish Time", "183").append("Age", "36").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "56").append("BestTime21K", "106")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner302 = new Document("Finish Time", "180").append("Age", "21").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "79").append("BestTime21K", "90")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner303 = new Document("Finish Time", "164").append("Age", "38").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "80").append("BestTime21K", "71")
				.append("BestTime10K", "36").append("TrackDifficulty", "Medium");
		Document marathonRunner304 = new Document("Finish Time", "257").append("Age", "30").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "45").append("BestTime21K", "124")
				.append("BestTime10K", "58").append("TrackDifficulty", "High");
		Document marathonRunner305 = new Document("Finish Time", "245").append("Age", "29").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "44").append("BestTime21K", "123")
				.append("BestTime10K", "50").append("TrackDifficulty", "Medium");
		Document marathonRunner306 = new Document("Finish Time", "189").append("Age", "29").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "61").append("BestTime21K", "96")
				.append("BestTime10K", "38").append("TrackDifficulty", "Low");
		Document marathonRunner307 = new Document("Finish Time", "252").append("Age", "26").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "35").append("BestTime21K", "128")
				.append("BestTime10K", "51").append("TrackDifficulty", "Low");
		Document marathonRunner308 = new Document("Finish Time", "172").append("Age", "33").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "74").append("BestTime21K", "70")
				.append("BestTime10K", "34").append("TrackDifficulty", "Medium");
		Document marathonRunner309 = new Document("Finish Time", "166").append("Age", "34").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "76").append("BestTime21K", "78")
				.append("BestTime10K", "37").append("TrackDifficulty", "High");
		Document marathonRunner310 = new Document("Finish Time", "147").append("Age", "24").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "81").append("BestTime21K", "77")
				.append("BestTime10K", "32").append("TrackDifficulty", "Medium");
		Document marathonRunner311 = new Document("Finish Time", "287").append("Age", "37").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "40").append("BestTime21K", "126")
				.append("BestTime10K", "62").append("TrackDifficulty", "Medium");
		Document marathonRunner312 = new Document("Finish Time", "281").append("Age", "28").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "37").append("BestTime21K", "131")
				.append("BestTime10K", "61").append("TrackDifficulty", "High");
		Document marathonRunner313 = new Document("Finish Time", "292").append("Age", "37").append("Gender", "Male")
				.append("BMI", "24").append("MilesPerWeek", "40").append("BestTime21K", "142")
				.append("BestTime10K", "56").append("TrackDifficulty", "Low");
		Document marathonRunner314 = new Document("Finish Time", "177").append("Age", "25").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "72").append("BestTime21K", "75")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner315 = new Document("Finish Time", "162").append("Age", "36").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "82").append("BestTime21K", "74")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner316 = new Document("Finish Time", "275").append("Age", "30").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "38").append("BestTime21K", "133")
				.append("BestTime10K", "63").append("TrackDifficulty", "Low");
		Document marathonRunner317 = new Document("Finish Time", "289").append("Age", "39").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "39").append("BestTime21K", "134")
				.append("BestTime10K", "58").append("TrackDifficulty", "High");
		Document marathonRunner318 = new Document("Finish Time", "134").append("Age", "33").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "87").append("BestTime21K", "67")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner319 = new Document("Finish Time", "270").append("Age", "29").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "45").append("BestTime21K", "118")
				.append("BestTime10K", "55").append("TrackDifficulty", "Medium");
		Document marathonRunner320 = new Document("Finish Time", "261").append("Age", "23").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "40").append("BestTime21K", "128")
				.append("BestTime10K", "49").append("TrackDifficulty", "High");
		Document marathonRunner321 = new Document("Finish Time", "202").append("Age", "29").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "69").append("BestTime21K", "99")
				.append("BestTime10K", "41").append("TrackDifficulty", "Low");
		Document marathonRunner322 = new Document("Finish Time", "138").append("Age", "30").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "87").append("BestTime21K", "73")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner323 = new Document("Finish Time", "272").append("Age", "30").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "40").append("BestTime21K", "134")
				.append("BestTime10K", "55").append("TrackDifficulty", "Medium");
		Document marathonRunner324 = new Document("Finish Time", "252").append("Age", "30").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "43").append("BestTime21K", "121")
				.append("BestTime10K", "48").append("TrackDifficulty", "Low");
		Document marathonRunner325 = new Document("Finish Time", "198").append("Age", "33").append("Gender", "feMale")
				.append("BMI", "18").append("MilesPerWeek", "73").append("BestTime21K", "85")
				.append("BestTime10K", "41").append("TrackDifficulty", "High");
		Document marathonRunner326 = new Document("Finish Time", "229").append("Age", "34").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "51").append("BestTime21K", "103")
				.append("BestTime10K", "47").append("TrackDifficulty", "Medium");
		Document marathonRunner327 = new Document("Finish Time", "182").append("Age", "22").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "69").append("BestTime21K", "86")
				.append("BestTime10K", "39").append("TrackDifficulty", "Medium");
		Document marathonRunner328 = new Document("Finish Time", "239").append("Age", "38").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "56").append("BestTime21K", "107")
				.append("BestTime10K", "52").append("TrackDifficulty", "High");
		Document marathonRunner329 = new Document("Finish Time", "124").append("Age", "33").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "97").append("BestTime21K", "71")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner330 = new Document("Finish Time", "280").append("Age", "21").append("Gender", "Male")
				.append("BMI", "25").append("MilesPerWeek", "36").append("BestTime21K", "144")
				.append("BestTime10K", "59").append("TrackDifficulty", "High");
		Document marathonRunner331 = new Document("Finish Time", "177").append("Age", "35").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "90").append("BestTime21K", "73")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner332 = new Document("Finish Time", "180").append("Age", "33").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "87").append("BestTime21K", "79")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner333 = new Document("Finish Time", "298").append("Age", "22").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "36").append("BestTime21K", "129")
				.append("BestTime10K", "64").append("TrackDifficulty", "High");
		Document marathonRunner334 = new Document("Finish Time", "280").append("Age", "35").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "40").append("BestTime21K", "135")
				.append("BestTime10K", "63").append("TrackDifficulty", "High");
		Document marathonRunner335 = new Document("Finish Time", "192").append("Age", "32").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "72").append("BestTime21K", "101")
				.append("BestTime10K", "41").append("TrackDifficulty", "Low");
		Document marathonRunner336 = new Document("Finish Time", "180").append("Age", "36").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "73").append("BestTime21K", "86")
				.append("BestTime10K", "34").append("TrackDifficulty", "High");
		Document marathonRunner337 = new Document("Finish Time", "179").append("Age", "34").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "85").append("BestTime21K", "80")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner338 = new Document("Finish Time", "129").append("Age", "31").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "89").append("BestTime21K", "69")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner339 = new Document("Finish Time", "209").append("Age", "37").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "74").append("BestTime21K", "85")
				.append("BestTime10K", "42").append("TrackDifficulty", "Medium");
		Document marathonRunner340 = new Document("Finish Time", "125").append("Age", "32").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "88").append("BestTime21K", "69")
				.append("BestTime10K", "30").append("TrackDifficulty", "Low");
		Document marathonRunner341 = new Document("Finish Time", "278").append("Age", "33").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "38").append("BestTime21K", "140")
				.append("BestTime10K", "62").append("TrackDifficulty", "High");
		Document marathonRunner342 = new Document("Finish Time", "297").append("Age", "29").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "39").append("BestTime21K", "133")
				.append("BestTime10K", "63").append("TrackDifficulty", "Medium");
		Document marathonRunner343 = new Document("Finish Time", "168").append("Age", "20").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "88").append("BestTime21K", "88")
				.append("BestTime10K", "33").append("TrackDifficulty", "Medium");
		Document marathonRunner344 = new Document("Finish Time", "278").append("Age", "37").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "38").append("BestTime21K", "137")
				.append("BestTime10K", "57").append("TrackDifficulty", "High");
		Document marathonRunner345 = new Document("Finish Time", "123").append("Age", "21").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "85").append("BestTime21K", "68")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner346 = new Document("Finish Time", "154").append("Age", "23").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "82").append("BestTime21K", "76")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner347 = new Document("Finish Time", "125").append("Age", "24").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "89").append("BestTime21K", "65")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner348 = new Document("Finish Time", "299").append("Age", "27").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "40").append("BestTime21K", "126")
				.append("BestTime10K", "57").append("TrackDifficulty", "Low");
		Document marathonRunner349 = new Document("Finish Time", "245").append("Age", "37").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "45").append("BestTime21K", "124")
				.append("BestTime10K", "55").append("TrackDifficulty", "Low");
		Document marathonRunner350 = new Document("Finish Time", "196").append("Age", "36").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "65").append("BestTime21K", "104")
				.append("BestTime10K", "40").append("TrackDifficulty", "Medium");
		Document marathonRunner351 = new Document("Finish Time", "275").append("Age", "28").append("Gender", "Male")
				.append("BMI", "25").append("MilesPerWeek", "30").append("BestTime21K", "137")
				.append("BestTime10K", "57").append("TrackDifficulty", "High");
		Document marathonRunner352 = new Document("Finish Time", "182").append("Age", "30").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "77").append("BestTime21K", "93")
				.append("BestTime10K", "40").append("TrackDifficulty", "High");
		Document marathonRunner353 = new Document("Finish Time", "149").append("Age", "32").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "95").append("BestTime21K", "62")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner354 = new Document("Finish Time", "292").append("Age", "23").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "35").append("BestTime21K", "142")
				.append("BestTime10K", "56").append("TrackDifficulty", "Low");
		Document marathonRunner355 = new Document("Finish Time", "285").append("Age", "27").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "37").append("BestTime21K", "135")
				.append("BestTime10K", "60").append("TrackDifficulty", "High");
		Document marathonRunner356 = new Document("Finish Time", "253").append("Age", "30").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "43").append("BestTime21K", "119")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner357 = new Document("Finish Time", "285").append("Age", "21").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "37").append("BestTime21K", "134")
				.append("BestTime10K", "54").append("TrackDifficulty", "High");
		Document marathonRunner358 = new Document("Finish Time", "271").append("Age", "21").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "31").append("BestTime21K", "136")
				.append("BestTime10K", "57").append("TrackDifficulty", "High");
		Document marathonRunner359 = new Document("Finish Time", "144").append("Age", "34").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "100").append("BestTime21K", "66")
				.append("BestTime10K", "32").append("TrackDifficulty", "High");
		Document marathonRunner360 = new Document("Finish Time", "186").append("Age", "36").append("Gender", "feMale")
				.append("BMI", "21").append("MilesPerWeek", "66").append("BestTime21K", "102")
				.append("BestTime10K", "41").append("TrackDifficulty", "Medium");
		Document marathonRunner361 = new Document("Finish Time", "227").append("Age", "31").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "53").append("BestTime21K", "95")
				.append("BestTime10K", "49").append("TrackDifficulty", "High");
		Document marathonRunner362 = new Document("Finish Time", "268").append("Age", "26").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "41").append("BestTime21K", "124")
				.append("BestTime10K", "50").append("TrackDifficulty", "Low");
		Document marathonRunner363 = new Document("Finish Time", "134").append("Age", "30").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "71").append("BestTime21K", "72")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner364 = new Document("Finish Time", "253").append("Age", "36").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "45").append("BestTime21K", "116")
				.append("BestTime10K", "48").append("TrackDifficulty", "High");
		Document marathonRunner365 = new Document("Finish Time", "120").append("Age", "26").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "88").append("BestTime21K", "67")
				.append("BestTime10K", "31").append("TrackDifficulty", "Medium");
		Document marathonRunner366 = new Document("Finish Time", "158").append("Age", "23").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "82").append("BestTime21K", "72")
				.append("BestTime10K", "34").append("TrackDifficulty", "Low");
		Document marathonRunner367 = new Document("Finish Time", "280").append("Age", "23").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "40").append("BestTime21K", "135")
				.append("BestTime10K", "65").append("TrackDifficulty", "Medium");
		Document marathonRunner368 = new Document("Finish Time", "163").append("Age", "40").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "73").append("BestTime21K", "75")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner369 = new Document("Finish Time", "284").append("Age", "27").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "37").append("BestTime21K", "137")
				.append("BestTime10K", "60").append("TrackDifficulty", "Medium");
		Document marathonRunner370 = new Document("Finish Time", "300").append("Age", "30").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "37").append("BestTime21K", "136")
				.append("BestTime10K", "58").append("TrackDifficulty", "High");
		Document marathonRunner371 = new Document("Finish Time", "299").append("Age", "30").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "39").append("BestTime21K", "128")
				.append("BestTime10K", "59").append("TrackDifficulty", "Low");
		Document marathonRunner372 = new Document("Finish Time", "272").append("Age", "39").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "37").append("BestTime21K", "143")
				.append("BestTime10K", "60").append("TrackDifficulty", "Medium");
		Document marathonRunner373 = new Document("Finish Time", "189").append("Age", "21").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "76").append("BestTime21K", "101")
				.append("BestTime10K", "39").append("TrackDifficulty", "Low");
		Document marathonRunner374 = new Document("Finish Time", "192").append("Age", "37").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "64").append("BestTime21K", "96")
				.append("BestTime10K", "40").append("TrackDifficulty", "Low");
		Document marathonRunner375 = new Document("Finish Time", "145").append("Age", "32").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "90").append("BestTime21K", "65")
				.append("BestTime10K", "31").append("TrackDifficulty", "Medium");
		Document marathonRunner376 = new Document("Finish Time", "245").append("Age", "31").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "45").append("BestTime21K", "121")
				.append("BestTime10K", "54").append("TrackDifficulty", "High");
		Document marathonRunner377 = new Document("Finish Time", "297").append("Age", "22").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "38").append("BestTime21K", "145")
				.append("BestTime10K", "65").append("TrackDifficulty", "High");
		Document marathonRunner378 = new Document("Finish Time", "135").append("Age", "37").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "94").append("BestTime21K", "73")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner379 = new Document("Finish Time", "293").append("Age", "32").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "38").append("BestTime21K", "131")
				.append("BestTime10K", "59").append("TrackDifficulty", "Low");
		Document marathonRunner380 = new Document("Finish Time", "126").append("Age", "35").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "100").append("BestTime21K", "68")
				.append("BestTime10K", "32").append("TrackDifficulty", "Medium");
		Document marathonRunner381 = new Document("Finish Time", "184").append("Age", "30").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "69").append("BestTime21K", "101")
				.append("BestTime10K", "39").append("TrackDifficulty", "Low");
		Document marathonRunner382 = new Document("Finish Time", "294").append("Age", "38").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "40").append("BestTime21K", "127")
				.append("BestTime10K", "59").append("TrackDifficulty", "Medium");
		Document marathonRunner383 = new Document("Finish Time", "297").append("Age", "35").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "39").append("BestTime21K", "137")
				.append("BestTime10K", "56").append("TrackDifficulty", "Medium");
		Document marathonRunner384 = new Document("Finish Time", "138").append("Age", "27").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "99").append("BestTime21K", "69")
				.append("BestTime10K", "30").append("TrackDifficulty", "Low");
		Document marathonRunner385 = new Document("Finish Time", "256").append("Age", "29").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "39").append("BestTime21K", "123")
				.append("BestTime10K", "52").append("TrackDifficulty", "Medium");
		Document marathonRunner386 = new Document("Finish Time", "142").append("Age", "40").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "97").append("BestTime21K", "60")
				.append("BestTime10K", "32").append("TrackDifficulty", "High");
		Document marathonRunner387 = new Document("Finish Time", "257").append("Age", "21").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "42").append("BestTime21K", "111")
				.append("BestTime10K", "55").append("TrackDifficulty", "High");
		Document marathonRunner388 = new Document("Finish Time", "176").append("Age", "27").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "71").append("BestTime21K", "78")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner389 = new Document("Finish Time", "270").append("Age", "21").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "41").append("BestTime21K", "126")
				.append("BestTime10K", "55").append("TrackDifficulty", "High");
		Document marathonRunner390 = new Document("Finish Time", "194").append("Age", "31").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "49").append("BestTime21K", "93")
				.append("BestTime10K", "40").append("TrackDifficulty", "Medium");
		Document marathonRunner391 = new Document("Finish Time", "258").append("Age", "37").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "43").append("BestTime21K", "126")
				.append("BestTime10K", "49").append("TrackDifficulty", "Medium");
		Document marathonRunner392 = new Document("Finish Time", "143").append("Age", "38").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "90").append("BestTime21K", "60")
				.append("BestTime10K", "31").append("TrackDifficulty", "Medium");
		Document marathonRunner393 = new Document("Finish Time", "282").append("Age", "29").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "36").append("BestTime21K", "138")
				.append("BestTime10K", "60").append("TrackDifficulty", "Low");
		Document marathonRunner394 = new Document("Finish Time", "191").append("Age", "28").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "62").append("BestTime21K", "107")
				.append("BestTime10K", "40").append("TrackDifficulty", "High");
		Document marathonRunner395 = new Document("Finish Time", "140").append("Age", "25").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "98").append("BestTime21K", "75")
				.append("BestTime10K", "32").append("TrackDifficulty", "Medium");
		Document marathonRunner396 = new Document("Finish Time", "179").append("Age", "26").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "77").append("BestTime21K", "70")
				.append("BestTime10K", "34").append("TrackDifficulty", "Low");
		Document marathonRunner397 = new Document("Finish Time", "271").append("Age", "33").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "38").append("BestTime21K", "143")
				.append("BestTime10K", "58").append("TrackDifficulty", "High");
		Document marathonRunner398 = new Document("Finish Time", "171").append("Age", "29").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "72").append("BestTime21K", "84")
				.append("BestTime10K", "37").append("TrackDifficulty", "Low");
		Document marathonRunner399 = new Document("Finish Time", "279").append("Age", "39").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "39").append("BestTime21K", "127")
				.append("BestTime10K", "63").append("TrackDifficulty", "Low");
		Document marathonRunner400 = new Document("Finish Time", "279").append("Age", "32").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "38").append("BestTime21K", "131")
				.append("BestTime10K", "54").append("TrackDifficulty", "Medium");
		Document marathonRunner401 = new Document("Finish Time", "202").append("Age", "38").append("Gender", "feMale")
				.append("BMI", "21").append("MilesPerWeek", "77").append("BestTime21K", "88")
				.append("BestTime10K", "40").append("TrackDifficulty", "High");
		Document marathonRunner402 = new Document("Finish Time", "136").append("Age", "34").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "93").append("BestTime21K", "68")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner403 = new Document("Finish Time", "224").append("Age", "32").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "55").append("BestTime21K", "107")
				.append("BestTime10K", "50").append("TrackDifficulty", "Medium");
		Document marathonRunner404 = new Document("Finish Time", "271").append("Age", "22").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "40").append("BestTime21K", "127")
				.append("BestTime10K", "58").append("TrackDifficulty", "Medium");
		Document marathonRunner405 = new Document("Finish Time", "288").append("Age", "20").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "34").append("BestTime21K", "131")
				.append("BestTime10K", "60").append("TrackDifficulty", "Medium");
		Document marathonRunner406 = new Document("Finish Time", "203").append("Age", "31").append("Gender", "feMale")
				.append("BMI", "19").append("MilesPerWeek", "63").append("BestTime21K", "91")
				.append("BestTime10K", "39").append("TrackDifficulty", "Medium");
		Document marathonRunner407 = new Document("Finish Time", "126").append("Age", "23").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "95").append("BestTime21K", "70")
				.append("BestTime10K", "32").append("TrackDifficulty", "Low");
		Document marathonRunner408 = new Document("Finish Time", "120").append("Age", "28").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "94").append("BestTime21K", "71")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner409 = new Document("Finish Time", "139").append("Age", "24").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "93").append("BestTime21K", "70")
				.append("BestTime10K", "33").append("TrackDifficulty", "Medium");
		Document marathonRunner410 = new Document("Finish Time", "182").append("Age", "39").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "61").append("BestTime21K", "95")
				.append("BestTime10K", "40").append("TrackDifficulty", "High");
		Document marathonRunner411 = new Document("Finish Time", "150").append("Age", "23").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "73").append("BestTime21K", "76")
				.append("BestTime10K", "32").append("TrackDifficulty", "Medium");
		Document marathonRunner412 = new Document("Finish Time", "208").append("Age", "30").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "51").append("BestTime21K", "102")
				.append("BestTime10K", "38").append("TrackDifficulty", "Medium");
		Document marathonRunner413 = new Document("Finish Time", "230").append("Age", "34").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "60").append("BestTime21K", "107")
				.append("BestTime10K", "42").append("TrackDifficulty", "Medium");
		Document marathonRunner414 = new Document("Finish Time", "161").append("Age", "34").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "70").append("BestTime21K", "75")
				.append("BestTime10K", "34").append("TrackDifficulty", "Medium");
		Document marathonRunner415 = new Document("Finish Time", "300").append("Age", "37").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "35").append("BestTime21K", "142")
				.append("BestTime10K", "59").append("TrackDifficulty", "Medium");
		Document marathonRunner416 = new Document("Finish Time", "143").append("Age", "35").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "97").append("BestTime21K", "71")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner417 = new Document("Finish Time", "131").append("Age", "31").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "91").append("BestTime21K", "73")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner418 = new Document("Finish Time", "173").append("Age", "40").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "83").append("BestTime21K", "78")
				.append("BestTime10K", "36").append("TrackDifficulty", "High");
		Document marathonRunner419 = new Document("Finish Time", "298").append("Age", "27").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "37").append("BestTime21K", "125")
				.append("BestTime10K", "60").append("TrackDifficulty", "High");
		Document marathonRunner420 = new Document("Finish Time", "155").append("Age", "32").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "71").append("BestTime21K", "74")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner421 = new Document("Finish Time", "123").append("Age", "32").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "100").append("BestTime21K", "69")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner422 = new Document("Finish Time", "245").append("Age", "40").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "44").append("BestTime21K", "120")
				.append("BestTime10K", "55").append("TrackDifficulty", "Medium");
		Document marathonRunner423 = new Document("Finish Time", "185").append("Age", "36").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "69").append("BestTime21K", "101")
				.append("BestTime10K", "39").append("TrackDifficulty", "Low");
		Document marathonRunner424 = new Document("Finish Time", "168").append("Age", "27").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "83").append("BestTime21K", "83")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner425 = new Document("Finish Time", "140").append("Age", "36").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "92").append("BestTime21K", "61")
				.append("BestTime10K", "31").append("TrackDifficulty", "High");
		Document marathonRunner426 = new Document("Finish Time", "249").append("Age", "32").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "43").append("BestTime21K", "127")
				.append("BestTime10K", "53").append("TrackDifficulty", "High");
		Document marathonRunner427 = new Document("Finish Time", "158").append("Age", "26").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "83").append("BestTime21K", "85")
				.append("BestTime10K", "37").append("TrackDifficulty", "High");
		Document marathonRunner428 = new Document("Finish Time", "153").append("Age", "22").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "70").append("BestTime21K", "86")
				.append("BestTime10K", "34").append("TrackDifficulty", "Medium");
		Document marathonRunner429 = new Document("Finish Time", "269").append("Age", "24").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "42").append("BestTime21K", "124")
				.append("BestTime10K", "51").append("TrackDifficulty", "Low");
		Document marathonRunner430 = new Document("Finish Time", "209").append("Age", "26").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "68").append("BestTime21K", "90")
				.append("BestTime10K", "39").append("TrackDifficulty", "Low");
		Document marathonRunner431 = new Document("Finish Time", "294").append("Age", "25").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "34").append("BestTime21K", "145")
				.append("BestTime10K", "65").append("TrackDifficulty", "Medium");
		Document marathonRunner432 = new Document("Finish Time", "285").append("Age", "26").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "39").append("BestTime21K", "127")
				.append("BestTime10K", "56").append("TrackDifficulty", "Low");
		Document marathonRunner433 = new Document("Finish Time", "146").append("Age", "34").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "90").append("BestTime21K", "67")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner434 = new Document("Finish Time", "279").append("Age", "39").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "39").append("BestTime21K", "141")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner435 = new Document("Finish Time", "282").append("Age", "35").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "42").append("BestTime21K", "140")
				.append("BestTime10K", "58").append("TrackDifficulty", "Low");
		Document marathonRunner436 = new Document("Finish Time", "252").append("Age", "30").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "38").append("BestTime21K", "110")
				.append("BestTime10K", "59").append("TrackDifficulty", "High");
		Document marathonRunner437 = new Document("Finish Time", "180").append("Age", "33").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "57").append("BestTime21K", "94")
				.append("BestTime10K", "38").append("TrackDifficulty", "Low");
		Document marathonRunner438 = new Document("Finish Time", "248").append("Age", "27").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "37").append("BestTime21K", "112")
				.append("BestTime10K", "58").append("TrackDifficulty", "Medium");
		Document marathonRunner439 = new Document("Finish Time", "300").append("Age", "36").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "39").append("BestTime21K", "130")
				.append("BestTime10K", "55").append("TrackDifficulty", "Medium");
		Document marathonRunner440 = new Document("Finish Time", "169").append("Age", "36").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "74").append("BestTime21K", "76")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner441 = new Document("Finish Time", "166").append("Age", "33").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "78").append("BestTime21K", "80")
				.append("BestTime10K", "35").append("TrackDifficulty", "High");
		Document marathonRunner442 = new Document("Finish Time", "242").append("Age", "25").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "43").append("BestTime21K", "116")
				.append("BestTime10K", "54").append("TrackDifficulty", "Low");
		Document marathonRunner443 = new Document("Finish Time", "181").append("Age", "28").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "55").append("BestTime21K", "93")
				.append("BestTime10K", "40").append("TrackDifficulty", "Medium");
		Document marathonRunner444 = new Document("Finish Time", "132").append("Age", "24").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "88").append("BestTime21K", "60")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner445 = new Document("Finish Time", "206").append("Age", "26").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "68").append("BestTime21K", "86")
				.append("BestTime10K", "40").append("TrackDifficulty", "High");
		Document marathonRunner446 = new Document("Finish Time", "193").append("Age", "32").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "78").append("BestTime21K", "91")
				.append("BestTime10K", "40").append("TrackDifficulty", "Low");
		Document marathonRunner447 = new Document("Finish Time", "252").append("Age", "40").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "43").append("BestTime21K", "126")
				.append("BestTime10K", "57").append("TrackDifficulty", "High");
		Document marathonRunner448 = new Document("Finish Time", "122").append("Age", "21").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "83").append("BestTime21K", "69")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner449 = new Document("Finish Time", "285").append("Age", "25").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "34").append("BestTime21K", "126")
				.append("BestTime10K", "64").append("TrackDifficulty", "Medium");
		Document marathonRunner450 = new Document("Finish Time", "257").append("Age", "28").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "37").append("BestTime21K", "114")
				.append("BestTime10K", "56").append("TrackDifficulty", "Medium");
		Document marathonRunner451 = new Document("Finish Time", "122").append("Age", "22").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "80").append("BestTime21K", "67")
				.append("BestTime10K", "33").append("TrackDifficulty", "Medium");
		Document marathonRunner452 = new Document("Finish Time", "287").append("Age", "21").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "33").append("BestTime21K", "129")
				.append("BestTime10K", "61").append("TrackDifficulty", "Medium");
		Document marathonRunner453 = new Document("Finish Time", "196").append("Age", "32").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "74").append("BestTime21K", "98")
				.append("BestTime10K", "38").append("TrackDifficulty", "Low");
		Document marathonRunner454 = new Document("Finish Time", "205").append("Age", "26").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "76").append("BestTime21K", "103")
				.append("BestTime10K", "41").append("TrackDifficulty", "Low");
		Document marathonRunner455 = new Document("Finish Time", "182").append("Age", "25").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "74").append("BestTime21K", "104")
				.append("BestTime10K", "41").append("TrackDifficulty", "Medium");
		Document marathonRunner456 = new Document("Finish Time", "176").append("Age", "29").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "70").append("BestTime21K", "84")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner457 = new Document("Finish Time", "206").append("Age", "20").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "74").append("BestTime21K", "85")
				.append("BestTime10K", "38").append("TrackDifficulty", "Low");
		Document marathonRunner458 = new Document("Finish Time", "190").append("Age", "21").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "70").append("BestTime21K", "88")
				.append("BestTime10K", "42").append("TrackDifficulty", "Medium");
		Document marathonRunner459 = new Document("Finish Time", "205").append("Age", "21").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "79").append("BestTime21K", "104")
				.append("BestTime10K", "38").append("TrackDifficulty", "Medium");
		Document marathonRunner460 = new Document("Finish Time", "123").append("Age", "30").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "99").append("BestTime21K", "69")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner461 = new Document("Finish Time", "214").append("Age", "32").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "57").append("BestTime21K", "106")
				.append("BestTime10K", "46").append("TrackDifficulty", "Medium");
		Document marathonRunner462 = new Document("Finish Time", "246").append("Age", "23").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "45").append("BestTime21K", "112")
				.append("BestTime10K", "51").append("TrackDifficulty", "Medium");
		Document marathonRunner463 = new Document("Finish Time", "201").append("Age", "28").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "76").append("BestTime21K", "99")
				.append("BestTime10K", "38").append("TrackDifficulty", "Low");
		Document marathonRunner464 = new Document("Finish Time", "280").append("Age", "34").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "38").append("BestTime21K", "134")
				.append("BestTime10K", "54").append("TrackDifficulty", "High");
		Document marathonRunner465 = new Document("Finish Time", "181").append("Age", "30").append("Gender", "feMale")
				.append("BMI", "21").append("MilesPerWeek", "68").append("BestTime21K", "87")
				.append("BestTime10K", "41").append("TrackDifficulty", "Medium");
		Document marathonRunner466 = new Document("Finish Time", "127").append("Age", "30").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "99").append("BestTime21K", "64")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner467 = new Document("Finish Time", "142").append("Age", "22").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "98").append("BestTime21K", "66")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner468 = new Document("Finish Time", "171").append("Age", "20").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "78").append("BestTime21K", "81")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner469 = new Document("Finish Time", "199").append("Age", "25").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "74").append("BestTime21K", "98")
				.append("BestTime10K", "41").append("TrackDifficulty", "High");
		Document marathonRunner470 = new Document("Finish Time", "281").append("Age", "35").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "40").append("BestTime21K", "136")
				.append("BestTime10K", "63").append("TrackDifficulty", "High");
		Document marathonRunner471 = new Document("Finish Time", "183").append("Age", "31").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "72").append("BestTime21K", "85")
				.append("BestTime10K", "41").append("TrackDifficulty", "Low");
		Document marathonRunner472 = new Document("Finish Time", "295").append("Age", "22").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "32").append("BestTime21K", "137")
				.append("BestTime10K", "58").append("TrackDifficulty", "Low");
		Document marathonRunner473 = new Document("Finish Time", "288").append("Age", "35").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "36").append("BestTime21K", "142")
				.append("BestTime10K", "58").append("TrackDifficulty", "Medium");
		Document marathonRunner474 = new Document("Finish Time", "299").append("Age", "38").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "40").append("BestTime21K", "141")
				.append("BestTime10K", "63").append("TrackDifficulty", "High");
		Document marathonRunner475 = new Document("Finish Time", "144").append("Age", "35").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "95").append("BestTime21K", "65")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner476 = new Document("Finish Time", "176").append("Age", "39").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "75").append("BestTime21K", "76")
				.append("BestTime10K", "34").append("TrackDifficulty", "Medium");
		Document marathonRunner477 = new Document("Finish Time", "258").append("Age", "20").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "44").append("BestTime21K", "116")
				.append("BestTime10K", "54").append("TrackDifficulty", "Low");
		Document marathonRunner478 = new Document("Finish Time", "121").append("Age", "37").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "89").append("BestTime21K", "68")
				.append("BestTime10K", "30").append("TrackDifficulty", "Low");
		Document marathonRunner479 = new Document("Finish Time", "210").append("Age", "30").append("Gender", "feMale")
				.append("BMI", "21").append("MilesPerWeek", "77").append("BestTime21K", "92")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner480 = new Document("Finish Time", "191").append("Age", "23").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "75").append("BestTime21K", "96")
				.append("BestTime10K", "42").append("TrackDifficulty", "High");
		Document marathonRunner481 = new Document("Finish Time", "278").append("Age", "37").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "39").append("BestTime21K", "128")
				.append("BestTime10K", "56").append("TrackDifficulty", "Medium");
		Document marathonRunner482 = new Document("Finish Time", "173").append("Age", "20").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "86").append("BestTime21K", "78")
				.append("BestTime10K", "35").append("TrackDifficulty", "Medium");
		Document marathonRunner483 = new Document("Finish Time", "162").append("Age", "33").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "74").append("BestTime21K", "73")
				.append("BestTime10K", "37").append("TrackDifficulty", "Medium");
		Document marathonRunner484 = new Document("Finish Time", "240").append("Age", "33").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "53").append("BestTime21K", "106")
				.append("BestTime10K", "51").append("TrackDifficulty", "Medium");
		Document marathonRunner485 = new Document("Finish Time", "273").append("Age", "32").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "38").append("BestTime21K", "143")
				.append("BestTime10K", "64").append("TrackDifficulty", "High");
		Document marathonRunner486 = new Document("Finish Time", "135").append("Age", "21").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "87").append("BestTime21K", "76")
				.append("BestTime10K", "32").append("TrackDifficulty", "Medium");
		Document marathonRunner487 = new Document("Finish Time", "292").append("Age", "40").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "38").append("BestTime21K", "134")
				.append("BestTime10K", "57").append("TrackDifficulty", "High");
		Document marathonRunner488 = new Document("Finish Time", "297").append("Age", "37").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "39").append("BestTime21K", "136")
				.append("BestTime10K", "55").append("TrackDifficulty", "Low");
		Document marathonRunner489 = new Document("Finish Time", "144").append("Age", "29").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "80").append("BestTime21K", "70")
				.append("BestTime10K", "33").append("TrackDifficulty", "Medium");
		Document marathonRunner490 = new Document("Finish Time", "269").append("Age", "31").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "45").append("BestTime21K", "123")
				.append("BestTime10K", "53").append("TrackDifficulty", "Medium");
		Document marathonRunner491 = new Document("Finish Time", "216").append("Age", "30").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "60").append("BestTime21K", "106")
				.append("BestTime10K", "46").append("TrackDifficulty", "High");
		Document marathonRunner492 = new Document("Finish Time", "265").append("Age", "32").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "44").append("BestTime21K", "119")
				.append("BestTime10K", "55").append("TrackDifficulty", "Low");
		Document marathonRunner493 = new Document("Finish Time", "274").append("Age", "28").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "36").append("BestTime21K", "137")
				.append("BestTime10K", "60").append("TrackDifficulty", "High");
		Document marathonRunner494 = new Document("Finish Time", "284").append("Age", "35").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "40").append("BestTime21K", "136")
				.append("BestTime10K", "55").append("TrackDifficulty", "Low");
		Document marathonRunner495 = new Document("Finish Time", "142").append("Age", "36").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "81").append("BestTime21K", "74")
				.append("BestTime10K", "31").append("TrackDifficulty", "Medium");
		Document marathonRunner496 = new Document("Finish Time", "196").append("Age", "33").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "52").append("BestTime21K", "90")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner497 = new Document("Finish Time", "179").append("Age", "26").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "83").append("BestTime21K", "76")
				.append("BestTime10K", "33").append("TrackDifficulty", "Medium");
		Document marathonRunner498 = new Document("Finish Time", "254").append("Age", "20").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "41").append("BestTime21K", "111")
				.append("BestTime10K", "57").append("TrackDifficulty", "Low");
		Document marathonRunner499 = new Document("Finish Time", "160").append("Age", "27").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "83").append("BestTime21K", "78")
				.append("BestTime10K", "34").append("TrackDifficulty", "High");
		Document marathonRunner500 = new Document("Finish Time", "177").append("Age", "30").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "74").append("BestTime21K", "83")
				.append("BestTime10K", "34").append("TrackDifficulty", "High");

		collection.insertOne(marathonRunner1);
		collection.insertOne(marathonRunner2);
		collection.insertOne(marathonRunner3);
		collection.insertOne(marathonRunner4);
		collection.insertOne(marathonRunner5);
		collection.insertOne(marathonRunner6);
		collection.insertOne(marathonRunner7);
		collection.insertOne(marathonRunner8);
		collection.insertOne(marathonRunner9);
		collection.insertOne(marathonRunner10);
		collection.insertOne(marathonRunner11);
		collection.insertOne(marathonRunner12);
		collection.insertOne(marathonRunner13);
		collection.insertOne(marathonRunner14);
		collection.insertOne(marathonRunner15);
		collection.insertOne(marathonRunner16);
		collection.insertOne(marathonRunner17);
		collection.insertOne(marathonRunner18);
		collection.insertOne(marathonRunner19);
		collection.insertOne(marathonRunner20);
		collection.insertOne(marathonRunner21);
		collection.insertOne(marathonRunner22);
		collection.insertOne(marathonRunner23);
		collection.insertOne(marathonRunner24);
		collection.insertOne(marathonRunner25);
		collection.insertOne(marathonRunner26);
		collection.insertOne(marathonRunner27);
		collection.insertOne(marathonRunner28);
		collection.insertOne(marathonRunner29);
		collection.insertOne(marathonRunner30);
		collection.insertOne(marathonRunner31);
		collection.insertOne(marathonRunner32);
		collection.insertOne(marathonRunner33);
		collection.insertOne(marathonRunner34);
		collection.insertOne(marathonRunner35);
		collection.insertOne(marathonRunner36);
		collection.insertOne(marathonRunner37);
		collection.insertOne(marathonRunner38);
		collection.insertOne(marathonRunner39);
		collection.insertOne(marathonRunner40);
		collection.insertOne(marathonRunner41);
		collection.insertOne(marathonRunner42);
		collection.insertOne(marathonRunner43);
		collection.insertOne(marathonRunner44);
		collection.insertOne(marathonRunner45);
		collection.insertOne(marathonRunner46);
		collection.insertOne(marathonRunner47);
		collection.insertOne(marathonRunner48);
		collection.insertOne(marathonRunner49);
		collection.insertOne(marathonRunner50);
		collection.insertOne(marathonRunner51);
		collection.insertOne(marathonRunner52);
		collection.insertOne(marathonRunner53);
		collection.insertOne(marathonRunner54);
		collection.insertOne(marathonRunner55);
		collection.insertOne(marathonRunner56);
		collection.insertOne(marathonRunner57);
		collection.insertOne(marathonRunner58);
		collection.insertOne(marathonRunner59);
		collection.insertOne(marathonRunner60);
		collection.insertOne(marathonRunner61);
		collection.insertOne(marathonRunner62);
		collection.insertOne(marathonRunner63);
		collection.insertOne(marathonRunner64);
		collection.insertOne(marathonRunner65);
		collection.insertOne(marathonRunner66);
		collection.insertOne(marathonRunner67);
		collection.insertOne(marathonRunner68);
		collection.insertOne(marathonRunner69);
		collection.insertOne(marathonRunner70);
		collection.insertOne(marathonRunner71);
		collection.insertOne(marathonRunner72);
		collection.insertOne(marathonRunner73);
		collection.insertOne(marathonRunner74);
		collection.insertOne(marathonRunner75);
		collection.insertOne(marathonRunner76);
		collection.insertOne(marathonRunner77);
		collection.insertOne(marathonRunner78);
		collection.insertOne(marathonRunner79);
		collection.insertOne(marathonRunner80);
		collection.insertOne(marathonRunner81);
		collection.insertOne(marathonRunner82);
		collection.insertOne(marathonRunner83);
		collection.insertOne(marathonRunner84);
		collection.insertOne(marathonRunner85);
		collection.insertOne(marathonRunner86);
		collection.insertOne(marathonRunner87);
		collection.insertOne(marathonRunner88);
		collection.insertOne(marathonRunner89);
		collection.insertOne(marathonRunner90);
		collection.insertOne(marathonRunner91);
		collection.insertOne(marathonRunner92);
		collection.insertOne(marathonRunner93);
		collection.insertOne(marathonRunner94);
		collection.insertOne(marathonRunner95);
		collection.insertOne(marathonRunner96);
		collection.insertOne(marathonRunner97);
		collection.insertOne(marathonRunner98);
		collection.insertOne(marathonRunner99);
		collection.insertOne(marathonRunner100);
		collection.insertOne(marathonRunner101);
		collection.insertOne(marathonRunner102);
		collection.insertOne(marathonRunner103);
		collection.insertOne(marathonRunner104);
		collection.insertOne(marathonRunner105);
		collection.insertOne(marathonRunner106);
		collection.insertOne(marathonRunner107);
		collection.insertOne(marathonRunner108);
		collection.insertOne(marathonRunner109);
		collection.insertOne(marathonRunner110);
		collection.insertOne(marathonRunner111);
		collection.insertOne(marathonRunner112);
		collection.insertOne(marathonRunner113);
		collection.insertOne(marathonRunner114);
		collection.insertOne(marathonRunner115);
		collection.insertOne(marathonRunner116);
		collection.insertOne(marathonRunner117);
		collection.insertOne(marathonRunner118);
		collection.insertOne(marathonRunner119);
		collection.insertOne(marathonRunner120);
		collection.insertOne(marathonRunner121);
		collection.insertOne(marathonRunner122);
		collection.insertOne(marathonRunner123);
		collection.insertOne(marathonRunner124);
		collection.insertOne(marathonRunner125);
		collection.insertOne(marathonRunner126);
		collection.insertOne(marathonRunner127);
		collection.insertOne(marathonRunner128);
		collection.insertOne(marathonRunner129);
		collection.insertOne(marathonRunner130);
		collection.insertOne(marathonRunner131);
		collection.insertOne(marathonRunner132);
		collection.insertOne(marathonRunner133);
		collection.insertOne(marathonRunner134);
		collection.insertOne(marathonRunner135);
		collection.insertOne(marathonRunner136);
		collection.insertOne(marathonRunner137);
		collection.insertOne(marathonRunner138);
		collection.insertOne(marathonRunner139);
		collection.insertOne(marathonRunner140);
		collection.insertOne(marathonRunner141);
		collection.insertOne(marathonRunner142);
		collection.insertOne(marathonRunner143);
		collection.insertOne(marathonRunner144);
		collection.insertOne(marathonRunner145);
		collection.insertOne(marathonRunner146);
		collection.insertOne(marathonRunner147);
		collection.insertOne(marathonRunner148);
		collection.insertOne(marathonRunner149);
		collection.insertOne(marathonRunner150);
		collection.insertOne(marathonRunner151);
		collection.insertOne(marathonRunner152);
		collection.insertOne(marathonRunner153);
		collection.insertOne(marathonRunner154);
		collection.insertOne(marathonRunner155);
		collection.insertOne(marathonRunner156);
		collection.insertOne(marathonRunner157);
		collection.insertOne(marathonRunner158);
		collection.insertOne(marathonRunner159);
		collection.insertOne(marathonRunner160);
		collection.insertOne(marathonRunner161);
		collection.insertOne(marathonRunner162);
		collection.insertOne(marathonRunner163);
		collection.insertOne(marathonRunner164);
		collection.insertOne(marathonRunner165);
		collection.insertOne(marathonRunner166);
		collection.insertOne(marathonRunner167);
		collection.insertOne(marathonRunner168);
		collection.insertOne(marathonRunner169);
		collection.insertOne(marathonRunner170);
		collection.insertOne(marathonRunner171);
		collection.insertOne(marathonRunner172);
		collection.insertOne(marathonRunner173);
		collection.insertOne(marathonRunner174);
		collection.insertOne(marathonRunner175);
		collection.insertOne(marathonRunner176);
		collection.insertOne(marathonRunner177);
		collection.insertOne(marathonRunner178);
		collection.insertOne(marathonRunner179);
		collection.insertOne(marathonRunner180);
		collection.insertOne(marathonRunner181);
		collection.insertOne(marathonRunner182);
		collection.insertOne(marathonRunner183);
		collection.insertOne(marathonRunner184);
		collection.insertOne(marathonRunner185);
		collection.insertOne(marathonRunner186);
		collection.insertOne(marathonRunner187);
		collection.insertOne(marathonRunner188);
		collection.insertOne(marathonRunner189);
		collection.insertOne(marathonRunner190);
		collection.insertOne(marathonRunner191);
		collection.insertOne(marathonRunner192);
		collection.insertOne(marathonRunner193);
		collection.insertOne(marathonRunner194);
		collection.insertOne(marathonRunner195);
		collection.insertOne(marathonRunner196);
		collection.insertOne(marathonRunner197);
		collection.insertOne(marathonRunner198);
		collection.insertOne(marathonRunner199);
		collection.insertOne(marathonRunner200);
		collection.insertOne(marathonRunner201);
		collection.insertOne(marathonRunner202);
		collection.insertOne(marathonRunner203);
		collection.insertOne(marathonRunner204);
		collection.insertOne(marathonRunner205);
		collection.insertOne(marathonRunner206);
		collection.insertOne(marathonRunner207);
		collection.insertOne(marathonRunner208);
		collection.insertOne(marathonRunner209);
		collection.insertOne(marathonRunner210);
		collection.insertOne(marathonRunner211);
		collection.insertOne(marathonRunner212);
		collection.insertOne(marathonRunner213);
		collection.insertOne(marathonRunner214);
		collection.insertOne(marathonRunner215);
		collection.insertOne(marathonRunner216);
		collection.insertOne(marathonRunner217);
		collection.insertOne(marathonRunner218);
		collection.insertOne(marathonRunner219);
		collection.insertOne(marathonRunner220);
		collection.insertOne(marathonRunner221);
		collection.insertOne(marathonRunner222);
		collection.insertOne(marathonRunner223);
		collection.insertOne(marathonRunner224);
		collection.insertOne(marathonRunner225);
		collection.insertOne(marathonRunner226);
		collection.insertOne(marathonRunner227);
		collection.insertOne(marathonRunner228);
		collection.insertOne(marathonRunner229);
		collection.insertOne(marathonRunner230);
		collection.insertOne(marathonRunner231);
		collection.insertOne(marathonRunner232);
		collection.insertOne(marathonRunner233);
		collection.insertOne(marathonRunner234);
		collection.insertOne(marathonRunner235);
		collection.insertOne(marathonRunner236);
		collection.insertOne(marathonRunner237);
		collection.insertOne(marathonRunner238);
		collection.insertOne(marathonRunner239);
		collection.insertOne(marathonRunner240);
		collection.insertOne(marathonRunner241);
		collection.insertOne(marathonRunner242);
		collection.insertOne(marathonRunner243);
		collection.insertOne(marathonRunner244);
		collection.insertOne(marathonRunner245);
		collection.insertOne(marathonRunner246);
		collection.insertOne(marathonRunner247);
		collection.insertOne(marathonRunner248);
		collection.insertOne(marathonRunner249);
		collection.insertOne(marathonRunner250);
		collection.insertOne(marathonRunner251);
		collection.insertOne(marathonRunner252);
		collection.insertOne(marathonRunner253);
		collection.insertOne(marathonRunner254);
		collection.insertOne(marathonRunner255);
		collection.insertOne(marathonRunner256);
		collection.insertOne(marathonRunner257);
		collection.insertOne(marathonRunner258);
		collection.insertOne(marathonRunner259);
		collection.insertOne(marathonRunner260);
		collection.insertOne(marathonRunner261);
		collection.insertOne(marathonRunner262);
		collection.insertOne(marathonRunner263);
		collection.insertOne(marathonRunner264);
		collection.insertOne(marathonRunner265);
		collection.insertOne(marathonRunner266);
		collection.insertOne(marathonRunner267);
		collection.insertOne(marathonRunner268);
		collection.insertOne(marathonRunner269);
		collection.insertOne(marathonRunner270);
		collection.insertOne(marathonRunner271);
		collection.insertOne(marathonRunner272);
		collection.insertOne(marathonRunner273);
		collection.insertOne(marathonRunner274);
		collection.insertOne(marathonRunner275);
		collection.insertOne(marathonRunner276);
		collection.insertOne(marathonRunner277);
		collection.insertOne(marathonRunner278);
		collection.insertOne(marathonRunner279);
		collection.insertOne(marathonRunner280);
		collection.insertOne(marathonRunner281);
		collection.insertOne(marathonRunner282);
		collection.insertOne(marathonRunner283);
		collection.insertOne(marathonRunner284);
		collection.insertOne(marathonRunner285);
		collection.insertOne(marathonRunner286);
		collection.insertOne(marathonRunner287);
		collection.insertOne(marathonRunner288);
		collection.insertOne(marathonRunner289);
		collection.insertOne(marathonRunner290);
		collection.insertOne(marathonRunner291);
		collection.insertOne(marathonRunner292);
		collection.insertOne(marathonRunner293);
		collection.insertOne(marathonRunner294);
		collection.insertOne(marathonRunner295);
		collection.insertOne(marathonRunner296);
		collection.insertOne(marathonRunner297);
		collection.insertOne(marathonRunner298);
		collection.insertOne(marathonRunner299);
		collection.insertOne(marathonRunner300);
		collection.insertOne(marathonRunner301);
		collection.insertOne(marathonRunner302);
		collection.insertOne(marathonRunner303);
		collection.insertOne(marathonRunner304);
		collection.insertOne(marathonRunner305);
		collection.insertOne(marathonRunner306);
		collection.insertOne(marathonRunner307);
		collection.insertOne(marathonRunner308);
		collection.insertOne(marathonRunner309);
		collection.insertOne(marathonRunner310);
		collection.insertOne(marathonRunner311);
		collection.insertOne(marathonRunner312);
		collection.insertOne(marathonRunner313);
		collection.insertOne(marathonRunner314);
		collection.insertOne(marathonRunner315);
		collection.insertOne(marathonRunner316);
		collection.insertOne(marathonRunner317);
		collection.insertOne(marathonRunner318);
		collection.insertOne(marathonRunner319);
		collection.insertOne(marathonRunner320);
		collection.insertOne(marathonRunner321);
		collection.insertOne(marathonRunner322);
		collection.insertOne(marathonRunner323);
		collection.insertOne(marathonRunner324);
		collection.insertOne(marathonRunner325);
		collection.insertOne(marathonRunner326);
		collection.insertOne(marathonRunner327);
		collection.insertOne(marathonRunner328);
		collection.insertOne(marathonRunner329);
		collection.insertOne(marathonRunner330);
		collection.insertOne(marathonRunner331);
		collection.insertOne(marathonRunner332);
		collection.insertOne(marathonRunner333);
		collection.insertOne(marathonRunner334);
		collection.insertOne(marathonRunner335);
		collection.insertOne(marathonRunner336);
		collection.insertOne(marathonRunner337);
		collection.insertOne(marathonRunner338);
		collection.insertOne(marathonRunner339);
		collection.insertOne(marathonRunner340);
		collection.insertOne(marathonRunner341);
		collection.insertOne(marathonRunner342);
		collection.insertOne(marathonRunner343);
		collection.insertOne(marathonRunner344);
		collection.insertOne(marathonRunner345);
		collection.insertOne(marathonRunner346);
		collection.insertOne(marathonRunner347);
		collection.insertOne(marathonRunner348);
		collection.insertOne(marathonRunner349);
		collection.insertOne(marathonRunner350);
		collection.insertOne(marathonRunner351);
		collection.insertOne(marathonRunner352);
		collection.insertOne(marathonRunner353);
		collection.insertOne(marathonRunner354);
		collection.insertOne(marathonRunner355);
		collection.insertOne(marathonRunner356);
		collection.insertOne(marathonRunner357);
		collection.insertOne(marathonRunner358);
		collection.insertOne(marathonRunner359);
		collection.insertOne(marathonRunner360);
		collection.insertOne(marathonRunner361);
		collection.insertOne(marathonRunner362);
		collection.insertOne(marathonRunner363);
		collection.insertOne(marathonRunner364);
		collection.insertOne(marathonRunner365);
		collection.insertOne(marathonRunner366);
		collection.insertOne(marathonRunner367);
		collection.insertOne(marathonRunner368);
		collection.insertOne(marathonRunner369);
		collection.insertOne(marathonRunner370);
		collection.insertOne(marathonRunner371);
		collection.insertOne(marathonRunner372);
		collection.insertOne(marathonRunner373);
		collection.insertOne(marathonRunner374);
		collection.insertOne(marathonRunner375);
		collection.insertOne(marathonRunner376);
		collection.insertOne(marathonRunner377);
		collection.insertOne(marathonRunner378);
		collection.insertOne(marathonRunner379);
		collection.insertOne(marathonRunner380);
		collection.insertOne(marathonRunner381);
		collection.insertOne(marathonRunner382);
		collection.insertOne(marathonRunner383);
		collection.insertOne(marathonRunner384);
		collection.insertOne(marathonRunner385);
		collection.insertOne(marathonRunner386);
		collection.insertOne(marathonRunner387);
		collection.insertOne(marathonRunner388);
		collection.insertOne(marathonRunner389);
		collection.insertOne(marathonRunner390);
		collection.insertOne(marathonRunner391);
		collection.insertOne(marathonRunner392);
		collection.insertOne(marathonRunner393);
		collection.insertOne(marathonRunner394);
		collection.insertOne(marathonRunner395);
		collection.insertOne(marathonRunner396);
		collection.insertOne(marathonRunner397);
		collection.insertOne(marathonRunner398);
		collection.insertOne(marathonRunner399);
		collection.insertOne(marathonRunner400);
		collection.insertOne(marathonRunner401);
		collection.insertOne(marathonRunner402);
		collection.insertOne(marathonRunner403);
		collection.insertOne(marathonRunner404);
		collection.insertOne(marathonRunner405);
		collection.insertOne(marathonRunner406);
		collection.insertOne(marathonRunner407);
		collection.insertOne(marathonRunner408);
		collection.insertOne(marathonRunner409);
		collection.insertOne(marathonRunner410);
		collection.insertOne(marathonRunner411);
		collection.insertOne(marathonRunner412);
		collection.insertOne(marathonRunner413);
		collection.insertOne(marathonRunner414);
		collection.insertOne(marathonRunner415);
		collection.insertOne(marathonRunner416);
		collection.insertOne(marathonRunner417);
		collection.insertOne(marathonRunner418);
		collection.insertOne(marathonRunner419);
		collection.insertOne(marathonRunner420);
		collection.insertOne(marathonRunner421);
		collection.insertOne(marathonRunner422);
		collection.insertOne(marathonRunner423);
		collection.insertOne(marathonRunner424);
		collection.insertOne(marathonRunner425);
		collection.insertOne(marathonRunner426);
		collection.insertOne(marathonRunner427);
		collection.insertOne(marathonRunner428);
		collection.insertOne(marathonRunner429);
		collection.insertOne(marathonRunner430);
		collection.insertOne(marathonRunner431);
		collection.insertOne(marathonRunner432);
		collection.insertOne(marathonRunner433);
		collection.insertOne(marathonRunner434);
		collection.insertOne(marathonRunner435);
		collection.insertOne(marathonRunner436);
		collection.insertOne(marathonRunner437);
		collection.insertOne(marathonRunner438);
		collection.insertOne(marathonRunner439);
		collection.insertOne(marathonRunner440);
		collection.insertOne(marathonRunner441);
		collection.insertOne(marathonRunner442);
		collection.insertOne(marathonRunner443);
		collection.insertOne(marathonRunner444);
		collection.insertOne(marathonRunner445);
		collection.insertOne(marathonRunner446);
		collection.insertOne(marathonRunner447);
		collection.insertOne(marathonRunner448);
		collection.insertOne(marathonRunner449);
		collection.insertOne(marathonRunner450);
		collection.insertOne(marathonRunner451);
		collection.insertOne(marathonRunner452);
		collection.insertOne(marathonRunner453);
		collection.insertOne(marathonRunner454);
		collection.insertOne(marathonRunner455);
		collection.insertOne(marathonRunner456);
		collection.insertOne(marathonRunner457);
		collection.insertOne(marathonRunner458);
		collection.insertOne(marathonRunner459);
		collection.insertOne(marathonRunner460);
		collection.insertOne(marathonRunner461);
		collection.insertOne(marathonRunner462);
		collection.insertOne(marathonRunner463);
		collection.insertOne(marathonRunner464);
		collection.insertOne(marathonRunner465);
		collection.insertOne(marathonRunner466);
		collection.insertOne(marathonRunner467);
		collection.insertOne(marathonRunner468);
		collection.insertOne(marathonRunner469);
		collection.insertOne(marathonRunner470);
		collection.insertOne(marathonRunner471);
		collection.insertOne(marathonRunner472);
		collection.insertOne(marathonRunner473);
		collection.insertOne(marathonRunner474);
		collection.insertOne(marathonRunner475);
		collection.insertOne(marathonRunner476);
		collection.insertOne(marathonRunner477);
		collection.insertOne(marathonRunner478);
		collection.insertOne(marathonRunner479);
		collection.insertOne(marathonRunner480);
		collection.insertOne(marathonRunner481);
		collection.insertOne(marathonRunner482);
		collection.insertOne(marathonRunner483);
		collection.insertOne(marathonRunner484);
		collection.insertOne(marathonRunner485);
		collection.insertOne(marathonRunner486);
		collection.insertOne(marathonRunner487);
		collection.insertOne(marathonRunner488);
		collection.insertOne(marathonRunner489);
		collection.insertOne(marathonRunner490);
		collection.insertOne(marathonRunner491);
		collection.insertOne(marathonRunner492);
		collection.insertOne(marathonRunner493);
		collection.insertOne(marathonRunner494);
		collection.insertOne(marathonRunner495);
		collection.insertOne(marathonRunner496);
		collection.insertOne(marathonRunner497);
		collection.insertOne(marathonRunner498);
		collection.insertOne(marathonRunner499);
		collection.insertOne(marathonRunner500);

		createmorecollection(collection);

	}

	private static void createmorecollection(MongoCollection<Document> collection) {

		Document marathonRunner501 = new Document("Finish Time", "253").append("Age", "35").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "44").append("BestTime21K", "122")
				.append("BestTime10K", "55").append("TrackDifficulty", "Medium");
		Document marathonRunner502 = new Document("Finish Time", "243").append("Age", "27").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "44").append("BestTime21K", "123")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner503 = new Document("Finish Time", "151").append("Age", "24").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "70").append("BestTime21K", "88")
				.append("BestTime10K", "34").append("TrackDifficulty", "Medium");
		Document marathonRunner504 = new Document("Finish Time", "133").append("Age", "30").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "98").append("BestTime21K", "68")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner505 = new Document("Finish Time", "167").append("Age", "22").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "82").append("BestTime21K", "71")
				.append("BestTime10K", "35").append("TrackDifficulty", "Medium");
		Document marathonRunner506 = new Document("Finish Time", "170").append("Age", "32").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "87").append("BestTime21K", "85")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner507 = new Document("Finish Time", "289").append("Age", "33").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "42").append("BestTime21K", "136")
				.append("BestTime10K", "61").append("TrackDifficulty", "Low");
		Document marathonRunner508 = new Document("Finish Time", "198").append("Age", "40").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "52").append("BestTime21K", "92")
				.append("BestTime10K", "40").append("TrackDifficulty", "High");
		Document marathonRunner509 = new Document("Finish Time", "281").append("Age", "29").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "40").append("BestTime21K", "131")
				.append("BestTime10K", "57").append("TrackDifficulty", "Medium");
		Document marathonRunner510 = new Document("Finish Time", "265").append("Age", "37").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "45").append("BestTime21K", "114")
				.append("BestTime10K", "54").append("TrackDifficulty", "Low");
		Document marathonRunner511 = new Document("Finish Time", "147").append("Age", "31").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "95").append("BestTime21K", "62")
				.append("BestTime10K", "31").append("TrackDifficulty", "High");
		Document marathonRunner512 = new Document("Finish Time", "277").append("Age", "34").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "39").append("BestTime21K", "131")
				.append("BestTime10K", "58").append("TrackDifficulty", "High");
		Document marathonRunner513 = new Document("Finish Time", "164").append("Age", "36").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "71").append("BestTime21K", "70")
				.append("BestTime10K", "34").append("TrackDifficulty", "Low");
		Document marathonRunner514 = new Document("Finish Time", "280").append("Age", "37").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "40").append("BestTime21K", "144")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner515 = new Document("Finish Time", "298").append("Age", "37").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "38").append("BestTime21K", "129")
				.append("BestTime10K", "63").append("TrackDifficulty", "High");
		Document marathonRunner516 = new Document("Finish Time", "291").append("Age", "35").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "39").append("BestTime21K", "139")
				.append("BestTime10K", "61").append("TrackDifficulty", "High");
		Document marathonRunner517 = new Document("Finish Time", "169").append("Age", "23").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "80").append("BestTime21K", "74")
				.append("BestTime10K", "37").append("TrackDifficulty", "Medium");
		Document marathonRunner518 = new Document("Finish Time", "176").append("Age", "34").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "75").append("BestTime21K", "72")
				.append("BestTime10K", "34").append("TrackDifficulty", "Low");
		Document marathonRunner519 = new Document("Finish Time", "251").append("Age", "40").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "44").append("BestTime21K", "110")
				.append("BestTime10K", "51").append("TrackDifficulty", "Medium");
		Document marathonRunner520 = new Document("Finish Time", "278").append("Age", "24").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "40").append("BestTime21K", "140")
				.append("BestTime10K", "59").append("TrackDifficulty", "Low");
		Document marathonRunner521 = new Document("Finish Time", "288").append("Age", "39").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "39").append("BestTime21K", "126")
				.append("BestTime10K", "62").append("TrackDifficulty", "High");
		Document marathonRunner522 = new Document("Finish Time", "190").append("Age", "40").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "73").append("BestTime21K", "99")
				.append("BestTime10K", "38").append("TrackDifficulty", "Low");
		Document marathonRunner523 = new Document("Finish Time", "139").append("Age", "22").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "85").append("BestTime21K", "67")
				.append("BestTime10K", "32").append("TrackDifficulty", "Medium");
		Document marathonRunner524 = new Document("Finish Time", "278").append("Age", "30").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "38").append("BestTime21K", "141")
				.append("BestTime10K", "61").append("TrackDifficulty", "Low");
		Document marathonRunner525 = new Document("Finish Time", "205").append("Age", "39").append("Gender", "feMale")
				.append("BMI", "20").append("MilesPerWeek", "64").append("BestTime21K", "90")
				.append("BestTime10K", "39").append("TrackDifficulty", "Medium");
		Document marathonRunner526 = new Document("Finish Time", "169").append("Age", "38").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "84").append("BestTime21K", "82")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner527 = new Document("Finish Time", "129").append("Age", "28").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "70").append("BestTime21K", "75")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner528 = new Document("Finish Time", "120").append("Age", "29").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "86").append("BestTime21K", "61")
				.append("BestTime10K", "31").append("TrackDifficulty", "Medium");
		Document marathonRunner529 = new Document("Finish Time", "124").append("Age", "33").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "98").append("BestTime21K", "63")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner530 = new Document("Finish Time", "292").append("Age", "32").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "40").append("BestTime21K", "130")
				.append("BestTime10K", "59").append("TrackDifficulty", "Medium");
		Document marathonRunner531 = new Document("Finish Time", "274").append("Age", "21").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "35").append("BestTime21K", "131")
				.append("BestTime10K", "57").append("TrackDifficulty", "High");
		Document marathonRunner532 = new Document("Finish Time", "168").append("Age", "30").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "80").append("BestTime21K", "73")
				.append("BestTime10K", "36").append("TrackDifficulty", "Low");
		Document marathonRunner533 = new Document("Finish Time", "258").append("Age", "38").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "44").append("BestTime21K", "128")
				.append("BestTime10K", "54").append("TrackDifficulty", "High");
		Document marathonRunner534 = new Document("Finish Time", "170").append("Age", "31").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "71").append("BestTime21K", "75")
				.append("BestTime10K", "37").append("TrackDifficulty", "Medium");
		Document marathonRunner535 = new Document("Finish Time", "290").append("Age", "27").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "35").append("BestTime21K", "135")
				.append("BestTime10K", "61").append("TrackDifficulty", "Low");
		Document marathonRunner536 = new Document("Finish Time", "297").append("Age", "32").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "38").append("BestTime21K", "140")
				.append("BestTime10K", "56").append("TrackDifficulty", "Medium");
		Document marathonRunner537 = new Document("Finish Time", "261").append("Age", "30").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "38").append("BestTime21K", "110")
				.append("BestTime10K", "48").append("TrackDifficulty", "Low");
		Document marathonRunner538 = new Document("Finish Time", "246").append("Age", "34").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "44").append("BestTime21K", "120")
				.append("BestTime10K", "51").append("TrackDifficulty", "Medium");
		Document marathonRunner539 = new Document("Finish Time", "122").append("Age", "32").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "99").append("BestTime21K", "67")
				.append("BestTime10K", "31").append("TrackDifficulty", "High");
		Document marathonRunner540 = new Document("Finish Time", "155").append("Age", "37").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "75").append("BestTime21K", "88")
				.append("BestTime10K", "37").append("TrackDifficulty", "High");
		Document marathonRunner541 = new Document("Finish Time", "288").append("Age", "24").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "36").append("BestTime21K", "127")
				.append("BestTime10K", "57").append("TrackDifficulty", "High");
		Document marathonRunner542 = new Document("Finish Time", "145").append("Age", "31").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "86").append("BestTime21K", "73")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner543 = new Document("Finish Time", "286").append("Age", "24").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "34").append("BestTime21K", "129")
				.append("BestTime10K", "61").append("TrackDifficulty", "High");
		Document marathonRunner544 = new Document("Finish Time", "189").append("Age", "20").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "71").append("BestTime21K", "87")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner545 = new Document("Finish Time", "271").append("Age", "39").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "40").append("BestTime21K", "136")
				.append("BestTime10K", "63").append("TrackDifficulty", "High");
		Document marathonRunner546 = new Document("Finish Time", "134").append("Age", "37").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "88").append("BestTime21K", "67")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner547 = new Document("Finish Time", "189").append("Age", "32").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "56").append("BestTime21K", "99")
				.append("BestTime10K", "40").append("TrackDifficulty", "Low");
		Document marathonRunner548 = new Document("Finish Time", "279").append("Age", "25").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "38").append("BestTime21K", "130")
				.append("BestTime10K", "59").append("TrackDifficulty", "Medium");
		Document marathonRunner549 = new Document("Finish Time", "277").append("Age", "20").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "38").append("BestTime21K", "135")
				.append("BestTime10K", "57").append("TrackDifficulty", "High");
		Document marathonRunner550 = new Document("Finish Time", "149").append("Age", "30").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "85").append("BestTime21K", "61")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner551 = new Document("Finish Time", "294").append("Age", "36").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "40").append("BestTime21K", "136")
				.append("BestTime10K", "62").append("TrackDifficulty", "High");
		Document marathonRunner552 = new Document("Finish Time", "192").append("Age", "23").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "61").append("BestTime21K", "90")
				.append("BestTime10K", "41").append("TrackDifficulty", "Low");
		Document marathonRunner553 = new Document("Finish Time", "271").append("Age", "25").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "39").append("BestTime21K", "140")
				.append("BestTime10K", "57").append("TrackDifficulty", "Low");
		Document marathonRunner554 = new Document("Finish Time", "293").append("Age", "37").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "39").append("BestTime21K", "128")
				.append("BestTime10K", "55").append("TrackDifficulty", "Medium");
		Document marathonRunner555 = new Document("Finish Time", "280").append("Age", "31").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "39").append("BestTime21K", "131")
				.append("BestTime10K", "61").append("TrackDifficulty", "High");
		Document marathonRunner556 = new Document("Finish Time", "284").append("Age", "20").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "35").append("BestTime21K", "133")
				.append("BestTime10K", "61").append("TrackDifficulty", "Medium");
		Document marathonRunner557 = new Document("Finish Time", "271").append("Age", "38").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "38").append("BestTime21K", "125")
				.append("BestTime10K", "56").append("TrackDifficulty", "Low");
		Document marathonRunner558 = new Document("Finish Time", "298").append("Age", "30").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "40").append("BestTime21K", "128")
				.append("BestTime10K", "63").append("TrackDifficulty", "High");
		Document marathonRunner559 = new Document("Finish Time", "286").append("Age", "39").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "40").append("BestTime21K", "141")
				.append("BestTime10K", "62").append("TrackDifficulty", "Low");
		Document marathonRunner560 = new Document("Finish Time", "175").append("Age", "21").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "79").append("BestTime21K", "71")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner561 = new Document("Finish Time", "190").append("Age", "29").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "77").append("BestTime21K", "95")
				.append("BestTime10K", "40").append("TrackDifficulty", "Low");
		Document marathonRunner562 = new Document("Finish Time", "137").append("Age", "27").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "99").append("BestTime21K", "64")
				.append("BestTime10K", "32").append("TrackDifficulty", "Low");
		Document marathonRunner563 = new Document("Finish Time", "264").append("Age", "28").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "40").append("BestTime21K", "119")
				.append("BestTime10K", "50").append("TrackDifficulty", "Low");
		Document marathonRunner564 = new Document("Finish Time", "274").append("Age", "23").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "35").append("BestTime21K", "135")
				.append("BestTime10K", "62").append("TrackDifficulty", "Medium");
		Document marathonRunner565 = new Document("Finish Time", "289").append("Age", "20").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "37").append("BestTime21K", "130")
				.append("BestTime10K", "60").append("TrackDifficulty", "High");
		Document marathonRunner566 = new Document("Finish Time", "285").append("Age", "40").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "39").append("BestTime21K", "132")
				.append("BestTime10K", "58").append("TrackDifficulty", "Medium");
		Document marathonRunner567 = new Document("Finish Time", "274").append("Age", "29").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "39").append("BestTime21K", "125")
				.append("BestTime10K", "56").append("TrackDifficulty", "Low");
		Document marathonRunner568 = new Document("Finish Time", "263").append("Age", "25").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "43").append("BestTime21K", "113")
				.append("BestTime10K", "53").append("TrackDifficulty", "Low");
		Document marathonRunner569 = new Document("Finish Time", "194").append("Age", "27").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "75").append("BestTime21K", "105")
				.append("BestTime10K", "40").append("TrackDifficulty", "Medium");
		Document marathonRunner570 = new Document("Finish Time", "136").append("Age", "29").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "98").append("BestTime21K", "62")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner571 = new Document("Finish Time", "171").append("Age", "26").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "82").append("BestTime21K", "77")
				.append("BestTime10K", "36").append("TrackDifficulty", "Low");
		Document marathonRunner572 = new Document("Finish Time", "291").append("Age", "25").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "35").append("BestTime21K", "130")
				.append("BestTime10K", "56").append("TrackDifficulty", "Low");
		Document marathonRunner573 = new Document("Finish Time", "274").append("Age", "21").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "40").append("BestTime21K", "145")
				.append("BestTime10K", "56").append("TrackDifficulty", "Low");
		Document marathonRunner574 = new Document("Finish Time", "207").append("Age", "23").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "71").append("BestTime21K", "95")
				.append("BestTime10K", "43").append("TrackDifficulty", "High");
		Document marathonRunner575 = new Document("Finish Time", "160").append("Age", "39").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "70").append("BestTime21K", "80")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner576 = new Document("Finish Time", "289").append("Age", "24").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "34").append("BestTime21K", "141")
				.append("BestTime10K", "58").append("TrackDifficulty", "High");
		Document marathonRunner577 = new Document("Finish Time", "294").append("Age", "37").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "37").append("BestTime21K", "130")
				.append("BestTime10K", "63").append("TrackDifficulty", "Medium");
		Document marathonRunner578 = new Document("Finish Time", "248").append("Age", "25").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "45").append("BestTime21K", "127")
				.append("BestTime10K", "54").append("TrackDifficulty", "Low");
		Document marathonRunner579 = new Document("Finish Time", "165").append("Age", "29").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "89").append("BestTime21K", "82")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner580 = new Document("Finish Time", "278").append("Age", "20").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "39").append("BestTime21K", "142")
				.append("BestTime10K", "59").append("TrackDifficulty", "Medium");
		Document marathonRunner581 = new Document("Finish Time", "292").append("Age", "38").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "39").append("BestTime21K", "126")
				.append("BestTime10K", "60").append("TrackDifficulty", "Low");
		Document marathonRunner582 = new Document("Finish Time", "287").append("Age", "22").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "39").append("BestTime21K", "143")
				.append("BestTime10K", "56").append("TrackDifficulty", "Low");
		Document marathonRunner583 = new Document("Finish Time", "279").append("Age", "31").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "37").append("BestTime21K", "134")
				.append("BestTime10K", "56").append("TrackDifficulty", "Medium");
		Document marathonRunner584 = new Document("Finish Time", "298").append("Age", "36").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "40").append("BestTime21K", "136")
				.append("BestTime10K", "55").append("TrackDifficulty", "Low");
		Document marathonRunner585 = new Document("Finish Time", "146").append("Age", "21").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "100").append("BestTime21K", "64")
				.append("BestTime10K", "30").append("TrackDifficulty", "Low");
		Document marathonRunner586 = new Document("Finish Time", "259").append("Age", "26").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "35").append("BestTime21K", "112")
				.append("BestTime10K", "57").append("TrackDifficulty", "Low");
		Document marathonRunner587 = new Document("Finish Time", "292").append("Age", "30").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "39").append("BestTime21K", "135")
				.append("BestTime10K", "55").append("TrackDifficulty", "High");
		Document marathonRunner588 = new Document("Finish Time", "198").append("Age", "35").append("Gender", "feMale")
				.append("BMI", "22").append("MilesPerWeek", "66").append("BestTime21K", "88")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner589 = new Document("Finish Time", "150").append("Age", "23").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "95").append("BestTime21K", "70")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner590 = new Document("Finish Time", "251").append("Age", "30").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "44").append("BestTime21K", "116")
				.append("BestTime10K", "57").append("TrackDifficulty", "High");
		Document marathonRunner591 = new Document("Finish Time", "298").append("Age", "32").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "40").append("BestTime21K", "128")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner592 = new Document("Finish Time", "136").append("Age", "35").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "92").append("BestTime21K", "67")
				.append("BestTime10K", "32").append("TrackDifficulty", "Medium");
		Document marathonRunner593 = new Document("Finish Time", "209").append("Age", "30").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "60").append("BestTime21K", "107")
				.append("BestTime10K", "41").append("TrackDifficulty", "High");
		Document marathonRunner594 = new Document("Finish Time", "271").append("Age", "26").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "40").append("BestTime21K", "125")
				.append("BestTime10K", "61").append("TrackDifficulty", "Low");
		Document marathonRunner595 = new Document("Finish Time", "251").append("Age", "40").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "43").append("BestTime21K", "127")
				.append("BestTime10K", "52").append("TrackDifficulty", "Low");
		Document marathonRunner596 = new Document("Finish Time", "295").append("Age", "38").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "38").append("BestTime21K", "127")
				.append("BestTime10K", "55").append("TrackDifficulty", "High");
		Document marathonRunner597 = new Document("Finish Time", "174").append("Age", "29").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "85").append("BestTime21K", "70")
				.append("BestTime10K", "33").append("TrackDifficulty", "Medium");
		Document marathonRunner598 = new Document("Finish Time", "173").append("Age", "30").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "75").append("BestTime21K", "82")
				.append("BestTime10K", "37").append("TrackDifficulty", "High");
		Document marathonRunner599 = new Document("Finish Time", "161").append("Age", "34").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "70").append("BestTime21K", "77")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner600 = new Document("Finish Time", "278").append("Age", "38").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "38").append("BestTime21K", "136")
				.append("BestTime10K", "59").append("TrackDifficulty", "Medium");
		Document marathonRunner601 = new Document("Finish Time", "295").append("Age", "22").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "39").append("BestTime21K", "138")
				.append("BestTime10K", "59").append("TrackDifficulty", "Low");
		Document marathonRunner602 = new Document("Finish Time", "240").append("Age", "20").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "38").append("BestTime21K", "121")
				.append("BestTime10K", "51").append("TrackDifficulty", "Low");
		Document marathonRunner603 = new Document("Finish Time", "155").append("Age", "26").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "70").append("BestTime21K", "71")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner604 = new Document("Finish Time", "288").append("Age", "35").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "37").append("BestTime21K", "144")
				.append("BestTime10K", "60").append("TrackDifficulty", "High");
		Document marathonRunner605 = new Document("Finish Time", "257").append("Age", "24").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "44").append("BestTime21K", "128")
				.append("BestTime10K", "59").append("TrackDifficulty", "High");
		Document marathonRunner606 = new Document("Finish Time", "172").append("Age", "24").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "73").append("BestTime21K", "70")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner607 = new Document("Finish Time", "184").append("Age", "25").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "70").append("BestTime21K", "94")
				.append("BestTime10K", "40").append("TrackDifficulty", "Low");
		Document marathonRunner608 = new Document("Finish Time", "257").append("Age", "33").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "45").append("BestTime21K", "113")
				.append("BestTime10K", "51").append("TrackDifficulty", "Medium");
		Document marathonRunner609 = new Document("Finish Time", "127").append("Age", "34").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "87").append("BestTime21K", "67")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner610 = new Document("Finish Time", "286").append("Age", "20").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "37").append("BestTime21K", "132")
				.append("BestTime10K", "54").append("TrackDifficulty", "Medium");
		Document marathonRunner611 = new Document("Finish Time", "203").append("Age", "30").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "55").append("BestTime21K", "90")
				.append("BestTime10K", "38").append("TrackDifficulty", "Low");
		Document marathonRunner612 = new Document("Finish Time", "156").append("Age", "29").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "90").append("BestTime21K", "71")
				.append("BestTime10K", "37").append("TrackDifficulty", "Low");
		Document marathonRunner613 = new Document("Finish Time", "255").append("Age", "27").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "45").append("BestTime21K", "111")
				.append("BestTime10K", "53").append("TrackDifficulty", "High");
		Document marathonRunner614 = new Document("Finish Time", "242").append("Age", "20").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "45").append("BestTime21K", "123")
				.append("BestTime10K", "49").append("TrackDifficulty", "Low");
		Document marathonRunner615 = new Document("Finish Time", "300").append("Age", "38").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "39").append("BestTime21K", "144")
				.append("BestTime10K", "63").append("TrackDifficulty", "Medium");
		Document marathonRunner616 = new Document("Finish Time", "125").append("Age", "21").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "86").append("BestTime21K", "71")
				.append("BestTime10K", "32").append("TrackDifficulty", "Low");
		Document marathonRunner617 = new Document("Finish Time", "261").append("Age", "33").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "45").append("BestTime21K", "114")
				.append("BestTime10K", "51").append("TrackDifficulty", "Medium");
		Document marathonRunner618 = new Document("Finish Time", "151").append("Age", "21").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "77").append("BestTime21K", "84")
				.append("BestTime10K", "38").append("TrackDifficulty", "Medium");
		Document marathonRunner619 = new Document("Finish Time", "122").append("Age", "36").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "93").append("BestTime21K", "62")
				.append("BestTime10K", "30").append("TrackDifficulty", "Low");
		Document marathonRunner620 = new Document("Finish Time", "186").append("Age", "23").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "63").append("BestTime21K", "107")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner621 = new Document("Finish Time", "189").append("Age", "21").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "63").append("BestTime21K", "85")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner622 = new Document("Finish Time", "281").append("Age", "25").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "37").append("BestTime21K", "130")
				.append("BestTime10K", "59").append("TrackDifficulty", "Low");
		Document marathonRunner623 = new Document("Finish Time", "283").append("Age", "31").append("Gender", "Male")
				.append("BMI", "25").append("MilesPerWeek", "39").append("BestTime21K", "137")
				.append("BestTime10K", "59").append("TrackDifficulty", "Low");
		Document marathonRunner624 = new Document("Finish Time", "150").append("Age", "39").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "77").append("BestTime21K", "76")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner625 = new Document("Finish Time", "281").append("Age", "38").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "38").append("BestTime21K", "132")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner626 = new Document("Finish Time", "286").append("Age", "21").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "32").append("BestTime21K", "141")
				.append("BestTime10K", "64").append("TrackDifficulty", "Low");
		Document marathonRunner627 = new Document("Finish Time", "291").append("Age", "25").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "40").append("BestTime21K", "134")
				.append("BestTime10K", "61").append("TrackDifficulty", "Medium");
		Document marathonRunner628 = new Document("Finish Time", "133").append("Age", "30").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "94").append("BestTime21K", "72")
				.append("BestTime10K", "30").append("TrackDifficulty", "Low");
		Document marathonRunner629 = new Document("Finish Time", "173").append("Age", "26").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "74").append("BestTime21K", "89")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner630 = new Document("Finish Time", "289").append("Age", "29").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "30").append("BestTime21K", "145")
				.append("BestTime10K", "62").append("TrackDifficulty", "Low");
		Document marathonRunner631 = new Document("Finish Time", "245").append("Age", "30").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "42").append("BestTime21K", "120")
				.append("BestTime10K", "52").append("TrackDifficulty", "Low");
		Document marathonRunner632 = new Document("Finish Time", "209").append("Age", "34").append("Gender", "feMale")
				.append("BMI", "18").append("MilesPerWeek", "67").append("BestTime21K", "91")
				.append("BestTime10K", "40").append("TrackDifficulty", "Medium");
		Document marathonRunner633 = new Document("Finish Time", "293").append("Age", "26").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "31").append("BestTime21K", "128")
				.append("BestTime10K", "58").append("TrackDifficulty", "Low");
		Document marathonRunner634 = new Document("Finish Time", "226").append("Age", "36").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "56").append("BestTime21K", "97")
				.append("BestTime10K", "48").append("TrackDifficulty", "High");
		Document marathonRunner635 = new Document("Finish Time", "149").append("Age", "21").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "72").append("BestTime21K", "75")
				.append("BestTime10K", "31").append("TrackDifficulty", "High");
		Document marathonRunner636 = new Document("Finish Time", "253").append("Age", "26").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "40").append("BestTime21K", "121")
				.append("BestTime10K", "53").append("TrackDifficulty", "High");
		Document marathonRunner637 = new Document("Finish Time", "125").append("Age", "38").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "90").append("BestTime21K", "64")
				.append("BestTime10K", "31").append("TrackDifficulty", "High");
		Document marathonRunner638 = new Document("Finish Time", "175").append("Age", "28").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "78").append("BestTime21K", "78")
				.append("BestTime10K", "34").append("TrackDifficulty", "Low");
		Document marathonRunner639 = new Document("Finish Time", "275").append("Age", "40").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "40").append("BestTime21K", "144")
				.append("BestTime10K", "63").append("TrackDifficulty", "Medium");
		Document marathonRunner640 = new Document("Finish Time", "270").append("Age", "24").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "37").append("BestTime21K", "126")
				.append("BestTime10K", "57").append("TrackDifficulty", "Low");
		Document marathonRunner641 = new Document("Finish Time", "196").append("Age", "30").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "77").append("BestTime21K", "87")
				.append("BestTime10K", "40").append("TrackDifficulty", "Medium");
		Document marathonRunner642 = new Document("Finish Time", "276").append("Age", "21").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "36").append("BestTime21K", "130")
				.append("BestTime10K", "60").append("TrackDifficulty", "Low");
		Document marathonRunner643 = new Document("Finish Time", "139").append("Age", "29").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "90").append("BestTime21K", "77")
				.append("BestTime10K", "32").append("TrackDifficulty", "Medium");
		Document marathonRunner644 = new Document("Finish Time", "203").append("Age", "24").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "64").append("BestTime21K", "94")
				.append("BestTime10K", "39").append("TrackDifficulty", "Medium");
		Document marathonRunner645 = new Document("Finish Time", "207").append("Age", "23").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "77").append("BestTime21K", "98")
				.append("BestTime10K", "44").append("TrackDifficulty", "High");
		Document marathonRunner646 = new Document("Finish Time", "167").append("Age", "27").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "86").append("BestTime21K", "89")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner647 = new Document("Finish Time", "122").append("Age", "38").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "95").append("BestTime21K", "66")
				.append("BestTime10K", "32").append("TrackDifficulty", "High");
		Document marathonRunner648 = new Document("Finish Time", "295").append("Age", "39").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "39").append("BestTime21K", "132")
				.append("BestTime10K", "56").append("TrackDifficulty", "Low");
		Document marathonRunner649 = new Document("Finish Time", "160").append("Age", "22").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "80").append("BestTime21K", "87")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner650 = new Document("Finish Time", "276").append("Age", "26").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "32").append("BestTime21K", "147")
				.append("BestTime10K", "64").append("TrackDifficulty", "High");
		Document marathonRunner651 = new Document("Finish Time", "180").append("Age", "28").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "78").append("BestTime21K", "84")
				.append("BestTime10K", "34").append("TrackDifficulty", "High");
		Document marathonRunner652 = new Document("Finish Time", "277").append("Age", "20").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "37").append("BestTime21K", "126")
				.append("BestTime10K", "58").append("TrackDifficulty", "Medium");
		Document marathonRunner653 = new Document("Finish Time", "200").append("Age", "32").append("Gender", "feMale")
				.append("BMI", "22").append("MilesPerWeek", "58").append("BestTime21K", "99")
				.append("BestTime10K", "40").append("TrackDifficulty", "High");
		Document marathonRunner654 = new Document("Finish Time", "201").append("Age", "35").append("Gender", "feMale")
				.append("BMI", "21").append("MilesPerWeek", "60").append("BestTime21K", "102")
				.append("BestTime10K", "39").append("TrackDifficulty", "Medium");
		Document marathonRunner655 = new Document("Finish Time", "130").append("Age", "22").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "84").append("BestTime21K", "66")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner656 = new Document("Finish Time", "264").append("Age", "28").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "43").append("BestTime21K", "115")
				.append("BestTime10K", "52").append("TrackDifficulty", "Low");
		Document marathonRunner657 = new Document("Finish Time", "298").append("Age", "34").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "38").append("BestTime21K", "131")
				.append("BestTime10K", "54").append("TrackDifficulty", "High");
		Document marathonRunner658 = new Document("Finish Time", "203").append("Age", "38").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "55").append("BestTime21K", "93")
				.append("BestTime10K", "38").append("TrackDifficulty", "Medium");
		Document marathonRunner659 = new Document("Finish Time", "174").append("Age", "36").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "74").append("BestTime21K", "71")
				.append("BestTime10K", "34").append("TrackDifficulty", "Low");
		Document marathonRunner660 = new Document("Finish Time", "178").append("Age", "26").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "80").append("BestTime21K", "78")
				.append("BestTime10K", "36").append("TrackDifficulty", "Low");
		Document marathonRunner661 = new Document("Finish Time", "233").append("Age", "36").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "51").append("BestTime21K", "97")
				.append("BestTime10K", "44").append("TrackDifficulty", "Medium");
		Document marathonRunner662 = new Document("Finish Time", "149").append("Age", "29").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "89").append("BestTime21K", "71")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner663 = new Document("Finish Time", "279").append("Age", "30").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "34").append("BestTime21K", "137")
				.append("BestTime10K", "65").append("TrackDifficulty", "High");
		Document marathonRunner664 = new Document("Finish Time", "151").append("Age", "33").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "79").append("BestTime21K", "83")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner665 = new Document("Finish Time", "266").append("Age", "32").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "45").append("BestTime21K", "113")
				.append("BestTime10K", "57").append("TrackDifficulty", "High");
		Document marathonRunner666 = new Document("Finish Time", "146").append("Age", "31").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "92").append("BestTime21K", "74")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner667 = new Document("Finish Time", "193").append("Age", "25").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "62").append("BestTime21K", "91")
				.append("BestTime10K", "40").append("TrackDifficulty", "High");
		Document marathonRunner668 = new Document("Finish Time", "289").append("Age", "26").append("Gender", "Male")
				.append("BMI", "25").append("MilesPerWeek", "39").append("BestTime21K", "144")
				.append("BestTime10K", "60").append("TrackDifficulty", "Low");
		Document marathonRunner669 = new Document("Finish Time", "289").append("Age", "28").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "39").append("BestTime21K", "139")
				.append("BestTime10K", "59").append("TrackDifficulty", "High");
		Document marathonRunner670 = new Document("Finish Time", "135").append("Age", "38").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "80").append("BestTime21K", "65")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner671 = new Document("Finish Time", "177").append("Age", "26").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "89").append("BestTime21K", "76")
				.append("BestTime10K", "36").append("TrackDifficulty", "Low");
		Document marathonRunner672 = new Document("Finish Time", "284").append("Age", "32").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "40").append("BestTime21K", "133")
				.append("BestTime10K", "57").append("TrackDifficulty", "Medium");
		Document marathonRunner673 = new Document("Finish Time", "261").append("Age", "20").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "41").append("BestTime21K", "116")
				.append("BestTime10K", "52").append("TrackDifficulty", "High");
		Document marathonRunner674 = new Document("Finish Time", "297").append("Age", "29").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "38").append("BestTime21K", "128")
				.append("BestTime10K", "59").append("TrackDifficulty", "Low");
		Document marathonRunner675 = new Document("Finish Time", "240").append("Age", "34").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "44").append("BestTime21K", "126")
				.append("BestTime10K", "56").append("TrackDifficulty", "Medium");
		Document marathonRunner676 = new Document("Finish Time", "141").append("Age", "28").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "89").append("BestTime21K", "73")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner677 = new Document("Finish Time", "281").append("Age", "39").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "39").append("BestTime21K", "139")
				.append("BestTime10K", "62").append("TrackDifficulty", "High");
		Document marathonRunner678 = new Document("Finish Time", "195").append("Age", "25").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "60").append("BestTime21K", "100")
				.append("BestTime10K", "38").append("TrackDifficulty", "Low");
		Document marathonRunner679 = new Document("Finish Time", "152").append("Age", "20").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "77").append("BestTime21K", "84")
				.append("BestTime10K", "34").append("TrackDifficulty", "Low");
		Document marathonRunner680 = new Document("Finish Time", "272").append("Age", "33").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "40").append("BestTime21K", "129")
				.append("BestTime10K", "63").append("TrackDifficulty", "Low");
		Document marathonRunner681 = new Document("Finish Time", "234").append("Age", "32").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "54").append("BestTime21K", "112")
				.append("BestTime10K", "50").append("TrackDifficulty", "Medium");
		Document marathonRunner682 = new Document("Finish Time", "201").append("Age", "23").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "56").append("BestTime21K", "101")
				.append("BestTime10K", "41").append("TrackDifficulty", "Medium");
		Document marathonRunner683 = new Document("Finish Time", "171").append("Age", "25").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "75").append("BestTime21K", "74")
				.append("BestTime10K", "35").append("TrackDifficulty", "Medium");
		Document marathonRunner684 = new Document("Finish Time", "299").append("Age", "24").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "40").append("BestTime21K", "133")
				.append("BestTime10K", "57").append("TrackDifficulty", "Medium");
		Document marathonRunner685 = new Document("Finish Time", "210").append("Age", "36").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "55").append("BestTime21K", "109")
				.append("BestTime10K", "43").append("TrackDifficulty", "Medium");
		Document marathonRunner686 = new Document("Finish Time", "134").append("Age", "40").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "93").append("BestTime21K", "63")
				.append("BestTime10K", "31").append("TrackDifficulty", "High");
		Document marathonRunner687 = new Document("Finish Time", "193").append("Age", "30").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "59").append("BestTime21K", "86")
				.append("BestTime10K", "41").append("TrackDifficulty", "High");
		Document marathonRunner688 = new Document("Finish Time", "138").append("Age", "37").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "84").append("BestTime21K", "61")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner689 = new Document("Finish Time", "290").append("Age", "32").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "40").append("BestTime21K", "140")
				.append("BestTime10K", "63").append("TrackDifficulty", "Medium");
		Document marathonRunner690 = new Document("Finish Time", "155").append("Age", "33").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "85").append("BestTime21K", "72")
				.append("BestTime10K", "37").append("TrackDifficulty", "Medium");
		Document marathonRunner691 = new Document("Finish Time", "294").append("Age", "30").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "36").append("BestTime21K", "137")
				.append("BestTime10K", "62").append("TrackDifficulty", "Medium");
		Document marathonRunner692 = new Document("Finish Time", "244").append("Age", "26").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "45").append("BestTime21K", "126")
				.append("BestTime10K", "58").append("TrackDifficulty", "High");
		Document marathonRunner693 = new Document("Finish Time", "188").append("Age", "27").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "76").append("BestTime21K", "103")
				.append("BestTime10K", "41").append("TrackDifficulty", "High");
		Document marathonRunner694 = new Document("Finish Time", "262").append("Age", "21").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "41").append("BestTime21K", "115")
				.append("BestTime10K", "50").append("TrackDifficulty", "Low");
		Document marathonRunner695 = new Document("Finish Time", "136").append("Age", "31").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "90").append("BestTime21K", "67")
				.append("BestTime10K", "30").append("TrackDifficulty", "Low");
		Document marathonRunner696 = new Document("Finish Time", "300").append("Age", "38").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "37").append("BestTime21K", "127")
				.append("BestTime10K", "58").append("TrackDifficulty", "High");
		Document marathonRunner697 = new Document("Finish Time", "172").append("Age", "36").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "89").append("BestTime21K", "79")
				.append("BestTime10K", "35").append("TrackDifficulty", "High");
		Document marathonRunner698 = new Document("Finish Time", "278").append("Age", "38").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "39").append("BestTime21K", "136")
				.append("BestTime10K", "58").append("TrackDifficulty", "High");
		Document marathonRunner699 = new Document("Finish Time", "139").append("Age", "34").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "95").append("BestTime21K", "74")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner700 = new Document("Finish Time", "135").append("Age", "31").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "97").append("BestTime21K", "66")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner701 = new Document("Finish Time", "152").append("Age", "21").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "70").append("BestTime21K", "80")
				.append("BestTime10K", "36").append("TrackDifficulty", "Low");
		Document marathonRunner702 = new Document("Finish Time", "263").append("Age", "39").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "44").append("BestTime21K", "118")
				.append("BestTime10K", "57").append("TrackDifficulty", "Medium");
		Document marathonRunner703 = new Document("Finish Time", "270").append("Age", "37").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "38").append("BestTime21K", "137")
				.append("BestTime10K", "62").append("TrackDifficulty", "Medium");
		Document marathonRunner704 = new Document("Finish Time", "134").append("Age", "28").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "95").append("BestTime21K", "75")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner705 = new Document("Finish Time", "274").append("Age", "22").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "37").append("BestTime21K", "130")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner706 = new Document("Finish Time", "204").append("Age", "21").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "62").append("BestTime21K", "101")
				.append("BestTime10K", "43").append("TrackDifficulty", "High");
		Document marathonRunner707 = new Document("Finish Time", "270").append("Age", "24").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "37").append("BestTime21K", "119")
				.append("BestTime10K", "55").append("TrackDifficulty", "Medium");
		Document marathonRunner708 = new Document("Finish Time", "288").append("Age", "34").append("Gender", "Male")
				.append("BMI", "24").append("MilesPerWeek", "39").append("BestTime21K", "129")
				.append("BestTime10K", "57").append("TrackDifficulty", "Medium");
		Document marathonRunner709 = new Document("Finish Time", "291").append("Age", "36").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "43").append("BestTime21K", "130")
				.append("BestTime10K", "55").append("TrackDifficulty", "Low");
		Document marathonRunner710 = new Document("Finish Time", "296").append("Age", "38").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "36").append("BestTime21K", "128")
				.append("BestTime10K", "54").append("TrackDifficulty", "High");
		Document marathonRunner711 = new Document("Finish Time", "135").append("Age", "36").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "100").append("BestTime21K", "72")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner712 = new Document("Finish Time", "128").append("Age", "31").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "93").append("BestTime21K", "65")
				.append("BestTime10K", "32").append("TrackDifficulty", "Medium");
		Document marathonRunner713 = new Document("Finish Time", "128").append("Age", "30").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "85").append("BestTime21K", "70")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner714 = new Document("Finish Time", "298").append("Age", "33").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "40").append("BestTime21K", "140")
				.append("BestTime10K", "60").append("TrackDifficulty", "Low");
		Document marathonRunner715 = new Document("Finish Time", "202").append("Age", "37").append("Gender", "feMale")
				.append("BMI", "18").append("MilesPerWeek", "50").append("BestTime21K", "96")
				.append("BestTime10K", "42").append("TrackDifficulty", "High");
		Document marathonRunner716 = new Document("Finish Time", "277").append("Age", "37").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "40").append("BestTime21K", "133")
				.append("BestTime10K", "54").append("TrackDifficulty", "High");
		Document marathonRunner717 = new Document("Finish Time", "167").append("Age", "32").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "77").append("BestTime21K", "72")
				.append("BestTime10K", "35").append("TrackDifficulty", "Medium");
		Document marathonRunner718 = new Document("Finish Time", "274").append("Age", "34").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "39").append("BestTime21K", "127")
				.append("BestTime10K", "55").append("TrackDifficulty", "Medium");
		Document marathonRunner719 = new Document("Finish Time", "158").append("Age", "37").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "89").append("BestTime21K", "73")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner720 = new Document("Finish Time", "240").append("Age", "30").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "37").append("BestTime21K", "121")
				.append("BestTime10K", "53").append("TrackDifficulty", "High");
		Document marathonRunner721 = new Document("Finish Time", "254").append("Age", "23").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "41").append("BestTime21K", "111")
				.append("BestTime10K", "54").append("TrackDifficulty", "Low");
		Document marathonRunner722 = new Document("Finish Time", "198").append("Age", "32").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "78").append("BestTime21K", "102")
				.append("BestTime10K", "40").append("TrackDifficulty", "Low");
		Document marathonRunner723 = new Document("Finish Time", "238").append("Age", "40").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "52").append("BestTime21K", "113")
				.append("BestTime10K", "49").append("TrackDifficulty", "High");
		Document marathonRunner724 = new Document("Finish Time", "164").append("Age", "25").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "82").append("BestTime21K", "90")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner725 = new Document("Finish Time", "250").append("Age", "23").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "39").append("BestTime21K", "112")
				.append("BestTime10K", "56").append("TrackDifficulty", "Low");
		Document marathonRunner726 = new Document("Finish Time", "165").append("Age", "33").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "89").append("BestTime21K", "79")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner727 = new Document("Finish Time", "154").append("Age", "24").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "72").append("BestTime21K", "73")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner728 = new Document("Finish Time", "158").append("Age", "27").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "70").append("BestTime21K", "75")
				.append("BestTime10K", "34").append("TrackDifficulty", "High");
		Document marathonRunner729 = new Document("Finish Time", "282").append("Age", "25").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "37").append("BestTime21K", "142")
				.append("BestTime10K", "60").append("TrackDifficulty", "Low");
		Document marathonRunner730 = new Document("Finish Time", "185").append("Age", "27").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "64").append("BestTime21K", "103")
				.append("BestTime10K", "43").append("TrackDifficulty", "High");
		Document marathonRunner731 = new Document("Finish Time", "225").append("Age", "38").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "54").append("BestTime21K", "110")
				.append("BestTime10K", "42").append("TrackDifficulty", "High");
		Document marathonRunner732 = new Document("Finish Time", "184").append("Age", "28").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "61").append("BestTime21K", "85")
				.append("BestTime10K", "42").append("TrackDifficulty", "High");
		Document marathonRunner733 = new Document("Finish Time", "187").append("Age", "30").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "73").append("BestTime21K", "85")
				.append("BestTime10K", "40").append("TrackDifficulty", "Low");
		Document marathonRunner734 = new Document("Finish Time", "290").append("Age", "27").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "37").append("BestTime21K", "136")
				.append("BestTime10K", "63").append("TrackDifficulty", "Medium");
		Document marathonRunner735 = new Document("Finish Time", "290").append("Age", "32").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "39").append("BestTime21K", "135")
				.append("BestTime10K", "63").append("TrackDifficulty", "Medium");
		Document marathonRunner736 = new Document("Finish Time", "268").append("Age", "36").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "44").append("BestTime21K", "118")
				.append("BestTime10K", "58").append("TrackDifficulty", "High");
		Document marathonRunner737 = new Document("Finish Time", "282").append("Age", "23").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "36").append("BestTime21K", "128")
				.append("BestTime10K", "63").append("TrackDifficulty", "Low");
		Document marathonRunner738 = new Document("Finish Time", "299").append("Age", "37").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "43").append("BestTime21K", "142")
				.append("BestTime10K", "60").append("TrackDifficulty", "Low");
		Document marathonRunner739 = new Document("Finish Time", "194").append("Age", "36").append("Gender", "feMale")
				.append("BMI", "20").append("MilesPerWeek", "75").append("BestTime21K", "105")
				.append("BestTime10K", "41").append("TrackDifficulty", "High");
		Document marathonRunner740 = new Document("Finish Time", "130").append("Age", "24").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "89").append("BestTime21K", "77")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner741 = new Document("Finish Time", "283").append("Age", "21").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "39").append("BestTime21K", "143")
				.append("BestTime10K", "56").append("TrackDifficulty", "Low");
		Document marathonRunner742 = new Document("Finish Time", "130").append("Age", "30").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "95").append("BestTime21K", "66")
				.append("BestTime10K", "31").append("TrackDifficulty", "High");
		Document marathonRunner743 = new Document("Finish Time", "241").append("Age", "26").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "42").append("BestTime21K", "111")
				.append("BestTime10K", "49").append("TrackDifficulty", "Medium");
		Document marathonRunner744 = new Document("Finish Time", "155").append("Age", "31").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "81").append("BestTime21K", "77")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner745 = new Document("Finish Time", "130").append("Age", "28").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "76").append("BestTime21K", "61")
				.append("BestTime10K", "31").append("TrackDifficulty", "High");
		Document marathonRunner746 = new Document("Finish Time", "125").append("Age", "25").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "97").append("BestTime21K", "62")
				.append("BestTime10K", "31").append("TrackDifficulty", "Medium");
		Document marathonRunner747 = new Document("Finish Time", "286").append("Age", "34").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "39").append("BestTime21K", "143")
				.append("BestTime10K", "62").append("TrackDifficulty", "High");
		Document marathonRunner748 = new Document("Finish Time", "121").append("Age", "33").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "96").append("BestTime21K", "61")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner749 = new Document("Finish Time", "198").append("Age", "37").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "75").append("BestTime21K", "85")
				.append("BestTime10K", "42").append("TrackDifficulty", "Medium");
		Document marathonRunner750 = new Document("Finish Time", "149").append("Age", "20").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "86").append("BestTime21K", "67")
				.append("BestTime10K", "32").append("TrackDifficulty", "Medium");
		Document marathonRunner751 = new Document("Finish Time", "262").append("Age", "27").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "38").append("BestTime21K", "128")
				.append("BestTime10K", "56").append("TrackDifficulty", "Medium");
		Document marathonRunner752 = new Document("Finish Time", "256").append("Age", "27").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "39").append("BestTime21K", "122")
				.append("BestTime10K", "57").append("TrackDifficulty", "Medium");
		Document marathonRunner753 = new Document("Finish Time", "287").append("Age", "39").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "40").append("BestTime21K", "143")
				.append("BestTime10K", "64").append("TrackDifficulty", "Medium");
		Document marathonRunner754 = new Document("Finish Time", "268").append("Age", "26").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "43").append("BestTime21K", "130")
				.append("BestTime10K", "57").append("TrackDifficulty", "High");
		Document marathonRunner755 = new Document("Finish Time", "166").append("Age", "24").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "81").append("BestTime21K", "76")
				.append("BestTime10K", "37").append("TrackDifficulty", "High");
		Document marathonRunner756 = new Document("Finish Time", "265").append("Age", "40").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "45").append("BestTime21K", "114")
				.append("BestTime10K", "55").append("TrackDifficulty", "Low");
		Document marathonRunner757 = new Document("Finish Time", "297").append("Age", "30").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "35").append("BestTime21K", "141")
				.append("BestTime10K", "63").append("TrackDifficulty", "Low");
		Document marathonRunner758 = new Document("Finish Time", "152").append("Age", "27").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "73").append("BestTime21K", "76")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner759 = new Document("Finish Time", "252").append("Age", "29").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "44").append("BestTime21K", "114")
				.append("BestTime10K", "49").append("TrackDifficulty", "Medium");
		Document marathonRunner760 = new Document("Finish Time", "196").append("Age", "35").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "50").append("BestTime21K", "91")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner761 = new Document("Finish Time", "276").append("Age", "36").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "38").append("BestTime21K", "130")
				.append("BestTime10K", "62").append("TrackDifficulty", "Low");
		Document marathonRunner762 = new Document("Finish Time", "219").append("Age", "37").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "58").append("BestTime21K", "109")
				.append("BestTime10K", "43").append("TrackDifficulty", "High");
		Document marathonRunner763 = new Document("Finish Time", "288").append("Age", "34").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "39").append("BestTime21K", "141")
				.append("BestTime10K", "54").append("TrackDifficulty", "Medium");
		Document marathonRunner764 = new Document("Finish Time", "177").append("Age", "33").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "71").append("BestTime21K", "79")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner765 = new Document("Finish Time", "152").append("Age", "25").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "78").append("BestTime21K", "71")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner766 = new Document("Finish Time", "158").append("Age", "36").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "74").append("BestTime21K", "71")
				.append("BestTime10K", "36").append("TrackDifficulty", "Low");
		Document marathonRunner767 = new Document("Finish Time", "271").append("Age", "33").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "38").append("BestTime21K", "140")
				.append("BestTime10K", "58").append("TrackDifficulty", "High");
		Document marathonRunner768 = new Document("Finish Time", "293").append("Age", "21").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "39").append("BestTime21K", "131")
				.append("BestTime10K", "54").append("TrackDifficulty", "Medium");
		Document marathonRunner769 = new Document("Finish Time", "288").append("Age", "27").append("Gender", "Male")
				.append("BMI", "25").append("MilesPerWeek", "35").append("BestTime21K", "144")
				.append("BestTime10K", "63").append("TrackDifficulty", "Medium");
		Document marathonRunner770 = new Document("Finish Time", "126").append("Age", "22").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "98").append("BestTime21K", "62")
				.append("BestTime10K", "31").append("TrackDifficulty", "Medium");
		Document marathonRunner771 = new Document("Finish Time", "169").append("Age", "21").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "88").append("BestTime21K", "76")
				.append("BestTime10K", "37").append("TrackDifficulty", "High");
		Document marathonRunner772 = new Document("Finish Time", "298").append("Age", "30").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "37").append("BestTime21K", "125")
				.append("BestTime10K", "54").append("TrackDifficulty", "Medium");
		Document marathonRunner773 = new Document("Finish Time", "273").append("Age", "27").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "30").append("BestTime21K", "134")
				.append("BestTime10K", "64").append("TrackDifficulty", "High");
		Document marathonRunner774 = new Document("Finish Time", "181").append("Age", "31").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "55").append("BestTime21K", "103")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner775 = new Document("Finish Time", "212").append("Age", "40").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "59").append("BestTime21K", "95")
				.append("BestTime10K", "45").append("TrackDifficulty", "Medium");
		Document marathonRunner776 = new Document("Finish Time", "160").append("Age", "22").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "74").append("BestTime21K", "81")
				.append("BestTime10K", "37").append("TrackDifficulty", "Low");
		Document marathonRunner777 = new Document("Finish Time", "294").append("Age", "32").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "37").append("BestTime21K", "138")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner778 = new Document("Finish Time", "125").append("Age", "35").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "97").append("BestTime21K", "64")
				.append("BestTime10K", "32").append("TrackDifficulty", "High");
		Document marathonRunner779 = new Document("Finish Time", "252").append("Age", "23").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "35").append("BestTime21K", "111")
				.append("BestTime10K", "49").append("TrackDifficulty", "High");
		Document marathonRunner780 = new Document("Finish Time", "206").append("Age", "33").append("Gender", "feMale")
				.append("BMI", "18").append("MilesPerWeek", "67").append("BestTime21K", "100")
				.append("BestTime10K", "42").append("TrackDifficulty", "High");
		Document marathonRunner781 = new Document("Finish Time", "285").append("Age", "38").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "39").append("BestTime21K", "125")
				.append("BestTime10K", "57").append("TrackDifficulty", "Medium");
		Document marathonRunner782 = new Document("Finish Time", "298").append("Age", "26").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "38").append("BestTime21K", "131")
				.append("BestTime10K", "62").append("TrackDifficulty", "High");
		Document marathonRunner783 = new Document("Finish Time", "158").append("Age", "21").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "75").append("BestTime21K", "70")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner784 = new Document("Finish Time", "280").append("Age", "21").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "39").append("BestTime21K", "131")
				.append("BestTime10K", "54").append("TrackDifficulty", "Medium");
		Document marathonRunner785 = new Document("Finish Time", "265").append("Age", "22").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "38").append("BestTime21K", "124")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner786 = new Document("Finish Time", "299").append("Age", "24").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "39").append("BestTime21K", "139")
				.append("BestTime10K", "54").append("TrackDifficulty", "Low");
		Document marathonRunner787 = new Document("Finish Time", "180").append("Age", "35").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "90").append("BestTime21K", "72")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner788 = new Document("Finish Time", "158").append("Age", "29").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "87").append("BestTime21K", "85")
				.append("BestTime10K", "34").append("TrackDifficulty", "Medium");
		Document marathonRunner789 = new Document("Finish Time", "131").append("Age", "30").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "97").append("BestTime21K", "70")
				.append("BestTime10K", "31").append("TrackDifficulty", "High");
		Document marathonRunner790 = new Document("Finish Time", "265").append("Age", "37").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "43").append("BestTime21K", "117")
				.append("BestTime10K", "57").append("TrackDifficulty", "Medium");
		Document marathonRunner791 = new Document("Finish Time", "193").append("Age", "30").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "48").append("BestTime21K", "102")
				.append("BestTime10K", "40").append("TrackDifficulty", "Medium");
		Document marathonRunner792 = new Document("Finish Time", "278").append("Age", "36").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "40").append("BestTime21K", "129")
				.append("BestTime10K", "62").append("TrackDifficulty", "Medium");
		Document marathonRunner793 = new Document("Finish Time", "291").append("Age", "31").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "37").append("BestTime21K", "128")
				.append("BestTime10K", "61").append("TrackDifficulty", "High");
		Document marathonRunner794 = new Document("Finish Time", "273").append("Age", "22").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "40").append("BestTime21K", "144")
				.append("BestTime10K", "58").append("TrackDifficulty", "Medium");
		Document marathonRunner795 = new Document("Finish Time", "140").append("Age", "31").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "100").append("BestTime21K", "73")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner796 = new Document("Finish Time", "183").append("Age", "23").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "73").append("BestTime21K", "88")
				.append("BestTime10K", "40").append("TrackDifficulty", "Low");
		Document marathonRunner797 = new Document("Finish Time", "155").append("Age", "22").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "73").append("BestTime21K", "77")
				.append("BestTime10K", "36").append("TrackDifficulty", "Low");
		Document marathonRunner798 = new Document("Finish Time", "262").append("Age", "30").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "44").append("BestTime21K", "128")
				.append("BestTime10K", "55").append("TrackDifficulty", "Low");
		Document marathonRunner799 = new Document("Finish Time", "176").append("Age", "28").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "76").append("BestTime21K", "83")
				.append("BestTime10K", "38").append("TrackDifficulty", "Medium");
		Document marathonRunner800 = new Document("Finish Time", "281").append("Age", "20").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "32").append("BestTime21K", "136")
				.append("BestTime10K", "54").append("TrackDifficulty", "Low");
		Document marathonRunner801 = new Document("Finish Time", "168").append("Age", "39").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "80").append("BestTime21K", "73")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner802 = new Document("Finish Time", "174").append("Age", "40").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "89").append("BestTime21K", "70")
				.append("BestTime10K", "33").append("TrackDifficulty", "Medium");
		Document marathonRunner803 = new Document("Finish Time", "300").append("Age", "36").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "38").append("BestTime21K", "134")
				.append("BestTime10K", "61").append("TrackDifficulty", "Medium");
		Document marathonRunner804 = new Document("Finish Time", "155").append("Age", "30").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "77").append("BestTime21K", "72")
				.append("BestTime10K", "36").append("TrackDifficulty", "High");
		Document marathonRunner805 = new Document("Finish Time", "249").append("Age", "38").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "44").append("BestTime21K", "111")
				.append("BestTime10K", "53").append("TrackDifficulty", "Low");
		Document marathonRunner806 = new Document("Finish Time", "163").append("Age", "24").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "74").append("BestTime21K", "86")
				.append("BestTime10K", "35").append("TrackDifficulty", "Medium");
		Document marathonRunner807 = new Document("Finish Time", "156").append("Age", "20").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "74").append("BestTime21K", "81")
				.append("BestTime10K", "33").append("TrackDifficulty", "Medium");
		Document marathonRunner808 = new Document("Finish Time", "220").append("Age", "34").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "60").append("BestTime21K", "112")
				.append("BestTime10K", "44").append("TrackDifficulty", "Medium");
		Document marathonRunner809 = new Document("Finish Time", "134").append("Age", "20").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "83").append("BestTime21K", "61")
				.append("BestTime10K", "32").append("TrackDifficulty", "Low");
		Document marathonRunner810 = new Document("Finish Time", "284").append("Age", "38").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "36").append("BestTime21K", "137")
				.append("BestTime10K", "60").append("TrackDifficulty", "Medium");
		Document marathonRunner811 = new Document("Finish Time", "210").append("Age", "25").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "61").append("BestTime21K", "95")
				.append("BestTime10K", "41").append("TrackDifficulty", "High");
		Document marathonRunner812 = new Document("Finish Time", "181").append("Age", "22").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "78").append("BestTime21K", "94")
				.append("BestTime10K", "39").append("TrackDifficulty", "Low");
		Document marathonRunner813 = new Document("Finish Time", "128").append("Age", "33").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "94").append("BestTime21K", "62")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner814 = new Document("Finish Time", "123").append("Age", "37").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "99").append("BestTime21K", "61")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner815 = new Document("Finish Time", "297").append("Age", "30").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "35").append("BestTime21K", "128")
				.append("BestTime10K", "58").append("TrackDifficulty", "Low");
		Document marathonRunner816 = new Document("Finish Time", "131").append("Age", "38").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "95").append("BestTime21K", "69")
				.append("BestTime10K", "32").append("TrackDifficulty", "Medium");
		Document marathonRunner817 = new Document("Finish Time", "179").append("Age", "24").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "81").append("BestTime21K", "74")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner818 = new Document("Finish Time", "171").append("Age", "35").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "77").append("BestTime21K", "74")
				.append("BestTime10K", "34").append("TrackDifficulty", "Low");
		Document marathonRunner819 = new Document("Finish Time", "143").append("Age", "38").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "91").append("BestTime21K", "62")
				.append("BestTime10K", "31").append("TrackDifficulty", "High");
		Document marathonRunner820 = new Document("Finish Time", "253").append("Age", "31").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "45").append("BestTime21K", "122")
				.append("BestTime10K", "50").append("TrackDifficulty", "Medium");
		Document marathonRunner821 = new Document("Finish Time", "283").append("Age", "20").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "38").append("BestTime21K", "144")
				.append("BestTime10K", "55").append("TrackDifficulty", "Low");
		Document marathonRunner822 = new Document("Finish Time", "217").append("Age", "35").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "57").append("BestTime21K", "110")
				.append("BestTime10K", "44").append("TrackDifficulty", "High");
		Document marathonRunner823 = new Document("Finish Time", "278").append("Age", "31").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "38").append("BestTime21K", "129")
				.append("BestTime10K", "57").append("TrackDifficulty", "Medium");
		Document marathonRunner824 = new Document("Finish Time", "294").append("Age", "30").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "36").append("BestTime21K", "125")
				.append("BestTime10K", "62").append("TrackDifficulty", "Medium");
		Document marathonRunner825 = new Document("Finish Time", "195").append("Age", "27").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "60").append("BestTime21K", "107")
				.append("BestTime10K", "40").append("TrackDifficulty", "High");
		Document marathonRunner826 = new Document("Finish Time", "251").append("Age", "28").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "40").append("BestTime21K", "127")
				.append("BestTime10K", "55").append("TrackDifficulty", "High");
		Document marathonRunner827 = new Document("Finish Time", "178").append("Age", "39").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "90").append("BestTime21K", "75")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner828 = new Document("Finish Time", "199").append("Age", "35").append("Gender", "feMale")
				.append("BMI", "19").append("MilesPerWeek", "72").append("BestTime21K", "100")
				.append("BestTime10K", "41").append("TrackDifficulty", "Medium");
		Document marathonRunner829 = new Document("Finish Time", "270").append("Age", "37").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "45").append("BestTime21K", "115")
				.append("BestTime10K", "50").append("TrackDifficulty", "High");
		Document marathonRunner830 = new Document("Finish Time", "183").append("Age", "28").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "76").append("BestTime21K", "103")
				.append("BestTime10K", "38").append("TrackDifficulty", "Medium");
		Document marathonRunner831 = new Document("Finish Time", "180").append("Age", "32").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "60").append("BestTime21K", "85")
				.append("BestTime10K", "39").append("TrackDifficulty", "Medium");
		Document marathonRunner832 = new Document("Finish Time", "132").append("Age", "40").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "92").append("BestTime21K", "73")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner833 = new Document("Finish Time", "184").append("Age", "29").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "79").append("BestTime21K", "102")
				.append("BestTime10K", "41").append("TrackDifficulty", "Low");
		Document marathonRunner834 = new Document("Finish Time", "177").append("Age", "34").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "79").append("BestTime21K", "72")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner835 = new Document("Finish Time", "246").append("Age", "21").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "40").append("BestTime21K", "116")
				.append("BestTime10K", "56").append("TrackDifficulty", "Low");
		Document marathonRunner836 = new Document("Finish Time", "144").append("Age", "26").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "92").append("BestTime21K", "68")
				.append("BestTime10K", "32").append("TrackDifficulty", "High");
		Document marathonRunner837 = new Document("Finish Time", "275").append("Age", "22").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "38").append("BestTime21K", "135")
				.append("BestTime10K", "59").append("TrackDifficulty", "High");
		Document marathonRunner838 = new Document("Finish Time", "250").append("Age", "24").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "35").append("BestTime21K", "119")
				.append("BestTime10K", "58").append("TrackDifficulty", "Medium");
		Document marathonRunner839 = new Document("Finish Time", "261").append("Age", "24").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "43").append("BestTime21K", "129")
				.append("BestTime10K", "57").append("TrackDifficulty", "Low");
		Document marathonRunner840 = new Document("Finish Time", "178").append("Age", "21").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "90").append("BestTime21K", "75")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner841 = new Document("Finish Time", "182").append("Age", "25").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "61").append("BestTime21K", "96")
				.append("BestTime10K", "38").append("TrackDifficulty", "Low");
		Document marathonRunner842 = new Document("Finish Time", "272").append("Age", "34").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "40").append("BestTime21K", "142")
				.append("BestTime10K", "63").append("TrackDifficulty", "High");
		Document marathonRunner843 = new Document("Finish Time", "121").append("Age", "22").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "93").append("BestTime21K", "64")
				.append("BestTime10K", "31").append("TrackDifficulty", "Medium");
		Document marathonRunner844 = new Document("Finish Time", "169").append("Age", "38").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "77").append("BestTime21K", "81")
				.append("BestTime10K", "36").append("TrackDifficulty", "Low");
		Document marathonRunner845 = new Document("Finish Time", "137").append("Age", "37").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "95").append("BestTime21K", "64")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner846 = new Document("Finish Time", "268").append("Age", "34").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "44").append("BestTime21K", "122")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner847 = new Document("Finish Time", "171").append("Age", "38").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "88").append("BestTime21K", "78")
				.append("BestTime10K", "35").append("TrackDifficulty", "Low");
		Document marathonRunner848 = new Document("Finish Time", "130").append("Age", "39").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "92").append("BestTime21K", "73")
				.append("BestTime10K", "31").append("TrackDifficulty", "High");
		Document marathonRunner849 = new Document("Finish Time", "135").append("Age", "29").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "100").append("BestTime21K", "63")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner850 = new Document("Finish Time", "288").append("Age", "27").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "40").append("BestTime21K", "127")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner851 = new Document("Finish Time", "144").append("Age", "37").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "97").append("BestTime21K", "63")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner852 = new Document("Finish Time", "132").append("Age", "25").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "83").append("BestTime21K", "67")
				.append("BestTime10K", "31").append("TrackDifficulty", "Medium");
		Document marathonRunner853 = new Document("Finish Time", "267").append("Age", "29").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "38").append("BestTime21K", "128")
				.append("BestTime10K", "57").append("TrackDifficulty", "Low");
		Document marathonRunner854 = new Document("Finish Time", "132").append("Age", "31").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "99").append("BestTime21K", "63")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner855 = new Document("Finish Time", "280").append("Age", "25").append("Gender", "Male")
				.append("BMI", "25").append("MilesPerWeek", "36").append("BestTime21K", "126")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner856 = new Document("Finish Time", "291").append("Age", "29").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "34").append("BestTime21K", "129")
				.append("BestTime10K", "55").append("TrackDifficulty", "Medium");
		Document marathonRunner857 = new Document("Finish Time", "265").append("Age", "31").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "44").append("BestTime21K", "111")
				.append("BestTime10K", "58").append("TrackDifficulty", "High");
		Document marathonRunner858 = new Document("Finish Time", "275").append("Age", "23").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "34").append("BestTime21K", "133")
				.append("BestTime10K", "61").append("TrackDifficulty", "High");
		Document marathonRunner859 = new Document("Finish Time", "285").append("Age", "23").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "39").append("BestTime21K", "141")
				.append("BestTime10K", "64").append("TrackDifficulty", "High");
		Document marathonRunner860 = new Document("Finish Time", "299").append("Age", "32").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "39").append("BestTime21K", "128")
				.append("BestTime10K", "60").append("TrackDifficulty", "Low");
		Document marathonRunner861 = new Document("Finish Time", "289").append("Age", "30").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "37").append("BestTime21K", "138")
				.append("BestTime10K", "62").append("TrackDifficulty", "High");
		Document marathonRunner862 = new Document("Finish Time", "270").append("Age", "35").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "44").append("BestTime21K", "115")
				.append("BestTime10K", "49").append("TrackDifficulty", "Low");
		Document marathonRunner863 = new Document("Finish Time", "250").append("Age", "38").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "44").append("BestTime21K", "113")
				.append("BestTime10K", "50").append("TrackDifficulty", "High");
		Document marathonRunner864 = new Document("Finish Time", "294").append("Age", "20").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "35").append("BestTime21K", "133")
				.append("BestTime10K", "63").append("TrackDifficulty", "High");
		Document marathonRunner865 = new Document("Finish Time", "170").append("Age", "27").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "84").append("BestTime21K", "79")
				.append("BestTime10K", "34").append("TrackDifficulty", "Low");
		Document marathonRunner866 = new Document("Finish Time", "257").append("Age", "31").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "43").append("BestTime21K", "113")
				.append("BestTime10K", "50").append("TrackDifficulty", "Medium");
		Document marathonRunner867 = new Document("Finish Time", "270").append("Age", "36").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "35").append("BestTime21K", "140")
				.append("BestTime10K", "58").append("TrackDifficulty", "High");
		Document marathonRunner868 = new Document("Finish Time", "191").append("Age", "26").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "57").append("BestTime21K", "94")
				.append("BestTime10K", "40").append("TrackDifficulty", "Low");
		Document marathonRunner869 = new Document("Finish Time", "161").append("Age", "33").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "88").append("BestTime21K", "81")
				.append("BestTime10K", "36").append("TrackDifficulty", "Medium");
		Document marathonRunner870 = new Document("Finish Time", "151").append("Age", "21").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "84").append("BestTime21K", "79")
				.append("BestTime10K", "37").append("TrackDifficulty", "Medium");
		Document marathonRunner871 = new Document("Finish Time", "162").append("Age", "29").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "70").append("BestTime21K", "79")
				.append("BestTime10K", "34").append("TrackDifficulty", "Low");
		Document marathonRunner872 = new Document("Finish Time", "150").append("Age", "39").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "84").append("BestTime21K", "71")
				.append("BestTime10K", "36").append("TrackDifficulty", "Low");
		Document marathonRunner873 = new Document("Finish Time", "204").append("Age", "38").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "63").append("BestTime21K", "100")
				.append("BestTime10K", "42").append("TrackDifficulty", "High");
		Document marathonRunner874 = new Document("Finish Time", "268").append("Age", "31").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "45").append("BestTime21K", "117")
				.append("BestTime10K", "51").append("TrackDifficulty", "Low");
		Document marathonRunner875 = new Document("Finish Time", "256").append("Age", "26").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "43").append("BestTime21K", "121")
				.append("BestTime10K", "48").append("TrackDifficulty", "High");
		Document marathonRunner876 = new Document("Finish Time", "194").append("Age", "40").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "62").append("BestTime21K", "90")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner877 = new Document("Finish Time", "259").append("Age", "36").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "45").append("BestTime21K", "124")
				.append("BestTime10K", "54").append("TrackDifficulty", "Low");
		Document marathonRunner878 = new Document("Finish Time", "137").append("Age", "35").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "81").append("BestTime21K", "62")
				.append("BestTime10K", "30").append("TrackDifficulty", "Low");
		Document marathonRunner879 = new Document("Finish Time", "132").append("Age", "21").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "99").append("BestTime21K", "75")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner880 = new Document("Finish Time", "264").append("Age", "34").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "45").append("BestTime21K", "118")
				.append("BestTime10K", "55").append("TrackDifficulty", "Low");
		Document marathonRunner881 = new Document("Finish Time", "288").append("Age", "31").append("Gender", "Male")
				.append("BMI", "24").append("MilesPerWeek", "38").append("BestTime21K", "134")
				.append("BestTime10K", "62").append("TrackDifficulty", "Low");
		Document marathonRunner882 = new Document("Finish Time", "189").append("Age", "40").append("Gender", "feMale")
				.append("BMI", "21").append("MilesPerWeek", "77").append("BestTime21K", "87")
				.append("BestTime10K", "42").append("TrackDifficulty", "High");
		Document marathonRunner883 = new Document("Finish Time", "244").append("Age", "35").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "45").append("BestTime21K", "122")
				.append("BestTime10K", "50").append("TrackDifficulty", "Medium");
		Document marathonRunner884 = new Document("Finish Time", "283").append("Age", "21").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "33").append("BestTime21K", "130")
				.append("BestTime10K", "64").append("TrackDifficulty", "Low");
		Document marathonRunner885 = new Document("Finish Time", "212").append("Age", "39").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "55").append("BestTime21K", "106")
				.append("BestTime10K", "43").append("TrackDifficulty", "Medium");
		Document marathonRunner886 = new Document("Finish Time", "255").append("Age", "40").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "45").append("BestTime21K", "127")
				.append("BestTime10K", "54").append("TrackDifficulty", "Low");
		Document marathonRunner887 = new Document("Finish Time", "139").append("Age", "23").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "88").append("BestTime21K", "64")
				.append("BestTime10K", "31").append("TrackDifficulty", "Medium");
		Document marathonRunner888 = new Document("Finish Time", "127").append("Age", "21").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "85").append("BestTime21K", "73")
				.append("BestTime10K", "32").append("TrackDifficulty", "Low");
		Document marathonRunner889 = new Document("Finish Time", "278").append("Age", "22").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "38").append("BestTime21K", "139")
				.append("BestTime10K", "57").append("TrackDifficulty", "Medium");
		Document marathonRunner890 = new Document("Finish Time", "134").append("Age", "24").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "79").append("BestTime21K", "66")
				.append("BestTime10K", "31").append("TrackDifficulty", "Medium");
		Document marathonRunner891 = new Document("Finish Time", "165").append("Age", "24").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "84").append("BestTime21K", "74")
				.append("BestTime10K", "34").append("TrackDifficulty", "Medium");
		Document marathonRunner892 = new Document("Finish Time", "290").append("Age", "40").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "38").append("BestTime21K", "127")
				.append("BestTime10K", "59").append("TrackDifficulty", "Medium");
		Document marathonRunner893 = new Document("Finish Time", "274").append("Age", "30").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "31").append("BestTime21K", "132")
				.append("BestTime10K", "57").append("TrackDifficulty", "High");
		Document marathonRunner894 = new Document("Finish Time", "277").append("Age", "34").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "38").append("BestTime21K", "134")
				.append("BestTime10K", "56").append("TrackDifficulty", "Low");
		Document marathonRunner895 = new Document("Finish Time", "142").append("Age", "22").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "95").append("BestTime21K", "64")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner896 = new Document("Finish Time", "145").append("Age", "25").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "91").append("BestTime21K", "70")
				.append("BestTime10K", "30").append("TrackDifficulty", "Low");
		Document marathonRunner897 = new Document("Finish Time", "262").append("Age", "31").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "43").append("BestTime21K", "114")
				.append("BestTime10K", "55").append("TrackDifficulty", "Low");
		Document marathonRunner898 = new Document("Finish Time", "270").append("Age", "31").append("Gender", "Male")
				.append("BMI", "24").append("MilesPerWeek", "39").append("BestTime21K", "127")
				.append("BestTime10K", "63").append("TrackDifficulty", "Medium");
		Document marathonRunner899 = new Document("Finish Time", "189").append("Age", "28").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "71").append("BestTime21K", "97")
				.append("BestTime10K", "40").append("TrackDifficulty", "High");
		Document marathonRunner900 = new Document("Finish Time", "270").append("Age", "37").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "45").append("BestTime21K", "111")
				.append("BestTime10K", "48").append("TrackDifficulty", "Medium");
		Document marathonRunner901 = new Document("Finish Time", "139").append("Age", "20").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "89").append("BestTime21K", "70")
				.append("BestTime10K", "31").append("TrackDifficulty", "Low");
		Document marathonRunner902 = new Document("Finish Time", "165").append("Age", "39").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "72").append("BestTime21K", "80")
				.append("BestTime10K", "34").append("TrackDifficulty", "High");
		Document marathonRunner903 = new Document("Finish Time", "139").append("Age", "40").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "99").append("BestTime21K", "70")
				.append("BestTime10K", "30").append("TrackDifficulty", "Low");
		Document marathonRunner904 = new Document("Finish Time", "145").append("Age", "28").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "85").append("BestTime21K", "60")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner905 = new Document("Finish Time", "140").append("Age", "24").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "84").append("BestTime21K", "69")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner906 = new Document("Finish Time", "255").append("Age", "22").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "35").append("BestTime21K", "123")
				.append("BestTime10K", "53").append("TrackDifficulty", "Medium");
		Document marathonRunner907 = new Document("Finish Time", "201").append("Age", "36").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "45").append("BestTime21K", "98")
				.append("BestTime10K", "40").append("TrackDifficulty", "Low");
		Document marathonRunner908 = new Document("Finish Time", "162").append("Age", "33").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "77").append("BestTime21K", "78")
				.append("BestTime10K", "36").append("TrackDifficulty", "Low");
		Document marathonRunner909 = new Document("Finish Time", "182").append("Age", "30").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "62").append("BestTime21K", "105")
				.append("BestTime10K", "43").append("TrackDifficulty", "High");
		Document marathonRunner910 = new Document("Finish Time", "135").append("Age", "34").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "94").append("BestTime21K", "62")
				.append("BestTime10K", "31").append("TrackDifficulty", "Medium");
		Document marathonRunner911 = new Document("Finish Time", "200").append("Age", "40").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "55").append("BestTime21K", "96")
				.append("BestTime10K", "39").append("TrackDifficulty", "Low");
		Document marathonRunner912 = new Document("Finish Time", "249").append("Age", "35").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "43").append("BestTime21K", "126")
				.append("BestTime10K", "55").append("TrackDifficulty", "Low");
		Document marathonRunner913 = new Document("Finish Time", "298").append("Age", "36").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "38").append("BestTime21K", "140")
				.append("BestTime10K", "60").append("TrackDifficulty", "High");
		Document marathonRunner914 = new Document("Finish Time", "226").append("Age", "37").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "58").append("BestTime21K", "106")
				.append("BestTime10K", "48").append("TrackDifficulty", "Medium");
		Document marathonRunner915 = new Document("Finish Time", "188").append("Age", "24").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "73").append("BestTime21K", "100")
				.append("BestTime10K", "42").append("TrackDifficulty", "Low");
		Document marathonRunner916 = new Document("Finish Time", "275").append("Age", "39").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "36").append("BestTime21K", "135")
				.append("BestTime10K", "55").append("TrackDifficulty", "High");
		Document marathonRunner917 = new Document("Finish Time", "152").append("Age", "26").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "88").append("BestTime21K", "88")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner918 = new Document("Finish Time", "300").append("Age", "31").append("Gender", "Male")
				.append("BMI", "24").append("MilesPerWeek", "38").append("BestTime21K", "139")
				.append("BestTime10K", "62").append("TrackDifficulty", "Medium");
		Document marathonRunner919 = new Document("Finish Time", "135").append("Age", "25").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "82").append("BestTime21K", "62")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner920 = new Document("Finish Time", "268").append("Age", "27").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "42").append("BestTime21K", "119")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner921 = new Document("Finish Time", "244").append("Age", "30").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "39").append("BestTime21K", "127")
				.append("BestTime10K", "49").append("TrackDifficulty", "Medium");
		Document marathonRunner922 = new Document("Finish Time", "295").append("Age", "28").append("Gender", "Female")
				.append("BMI", "24").append("MilesPerWeek", "36").append("BestTime21K", "126")
				.append("BestTime10K", "65").append("TrackDifficulty", "High");
		Document marathonRunner923 = new Document("Finish Time", "218").append("Age", "31").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "57").append("BestTime21K", "113")
				.append("BestTime10K", "44").append("TrackDifficulty", "High");
		Document marathonRunner924 = new Document("Finish Time", "289").append("Age", "38").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "39").append("BestTime21K", "141")
				.append("BestTime10K", "56").append("TrackDifficulty", "Medium");
		Document marathonRunner925 = new Document("Finish Time", "157").append("Age", "37").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "82").append("BestTime21K", "79")
				.append("BestTime10K", "35").append("TrackDifficulty", "Medium");
		Document marathonRunner926 = new Document("Finish Time", "244").append("Age", "34").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "43").append("BestTime21K", "111")
				.append("BestTime10K", "57").append("TrackDifficulty", "Medium");
		Document marathonRunner927 = new Document("Finish Time", "263").append("Age", "34").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "45").append("BestTime21K", "119")
				.append("BestTime10K", "49").append("TrackDifficulty", "High");
		Document marathonRunner928 = new Document("Finish Time", "146").append("Age", "31").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "91").append("BestTime21K", "68")
				.append("BestTime10K", "30").append("TrackDifficulty", "Low");
		Document marathonRunner929 = new Document("Finish Time", "245").append("Age", "30").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "44").append("BestTime21K", "120")
				.append("BestTime10K", "51").append("TrackDifficulty", "Medium");
		Document marathonRunner930 = new Document("Finish Time", "139").append("Age", "36").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "95").append("BestTime21K", "68")
				.append("BestTime10K", "30").append("TrackDifficulty", "Medium");
		Document marathonRunner931 = new Document("Finish Time", "273").append("Age", "32").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "38").append("BestTime21K", "134")
				.append("BestTime10K", "59").append("TrackDifficulty", "Medium");
		Document marathonRunner932 = new Document("Finish Time", "247").append("Age", "40").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "45").append("BestTime21K", "125")
				.append("BestTime10K", "50").append("TrackDifficulty", "Medium");
		Document marathonRunner933 = new Document("Finish Time", "142").append("Age", "30").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "100").append("BestTime21K", "68")
				.append("BestTime10K", "30").append("TrackDifficulty", "Low");
		Document marathonRunner934 = new Document("Finish Time", "176").append("Age", "22").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "89").append("BestTime21K", "83")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner935 = new Document("Finish Time", "261").append("Age", "30").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "36").append("BestTime21K", "112")
				.append("BestTime10K", "52").append("TrackDifficulty", "Medium");
		Document marathonRunner936 = new Document("Finish Time", "246").append("Age", "27").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "44").append("BestTime21K", "124")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner937 = new Document("Finish Time", "204").append("Age", "29").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "77").append("BestTime21K", "93")
				.append("BestTime10K", "42").append("TrackDifficulty", "High");
		Document marathonRunner938 = new Document("Finish Time", "221").append("Age", "40").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "50").append("BestTime21K", "109")
				.append("BestTime10K", "43").append("TrackDifficulty", "High");
		Document marathonRunner939 = new Document("Finish Time", "289").append("Age", "33").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "39").append("BestTime21K", "128")
				.append("BestTime10K", "57").append("TrackDifficulty", "Medium");
		Document marathonRunner940 = new Document("Finish Time", "267").append("Age", "32").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "43").append("BestTime21K", "125")
				.append("BestTime10K", "51").append("TrackDifficulty", "Low");
		Document marathonRunner941 = new Document("Finish Time", "128").append("Age", "33").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "96").append("BestTime21K", "67")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner942 = new Document("Finish Time", "276").append("Age", "37").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "40").append("BestTime21K", "143")
				.append("BestTime10K", "61").append("TrackDifficulty", "High");
		Document marathonRunner943 = new Document("Finish Time", "274").append("Age", "30").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "41").append("BestTime21K", "137")
				.append("BestTime10K", "62").append("TrackDifficulty", "Low");
		Document marathonRunner944 = new Document("Finish Time", "195").append("Age", "22").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "62").append("BestTime21K", "100")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner945 = new Document("Finish Time", "277").append("Age", "21").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "34").append("BestTime21K", "125")
				.append("BestTime10K", "58").append("TrackDifficulty", "Medium");
		Document marathonRunner946 = new Document("Finish Time", "251").append("Age", "26").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "35").append("BestTime21K", "123")
				.append("BestTime10K", "50").append("TrackDifficulty", "Low");
		Document marathonRunner947 = new Document("Finish Time", "297").append("Age", "25").append("Gender", "Male")
				.append("BMI", "25").append("MilesPerWeek", "33").append("BestTime21K", "142")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner948 = new Document("Finish Time", "278").append("Age", "21").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "37").append("BestTime21K", "143")
				.append("BestTime10K", "55").append("TrackDifficulty", "Medium");
		Document marathonRunner949 = new Document("Finish Time", "295").append("Age", "33").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "38").append("BestTime21K", "139")
				.append("BestTime10K", "62").append("TrackDifficulty", "Low");
		Document marathonRunner950 = new Document("Finish Time", "254").append("Age", "36").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "44").append("BestTime21K", "124")
				.append("BestTime10K", "53").append("TrackDifficulty", "High");
		Document marathonRunner951 = new Document("Finish Time", "232").append("Age", "39").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "57").append("BestTime21K", "105")
				.append("BestTime10K", "42").append("TrackDifficulty", "Medium");
		Document marathonRunner952 = new Document("Finish Time", "272").append("Age", "39").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "39").append("BestTime21K", "131")
				.append("BestTime10K", "54").append("TrackDifficulty", "Medium");
		Document marathonRunner953 = new Document("Finish Time", "244").append("Age", "33").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "45").append("BestTime21K", "116")
				.append("BestTime10K", "51").append("TrackDifficulty", "Medium");
		Document marathonRunner954 = new Document("Finish Time", "297").append("Age", "32").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "39").append("BestTime21K", "127")
				.append("BestTime10K", "54").append("TrackDifficulty", "Medium");
		Document marathonRunner955 = new Document("Finish Time", "276").append("Age", "30").append("Gender", "Male")
				.append("BMI", "25").append("MilesPerWeek", "38").append("BestTime21K", "129")
				.append("BestTime10K", "58").append("TrackDifficulty", "Low");
		Document marathonRunner956 = new Document("Finish Time", "138").append("Age", "30").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "96").append("BestTime21K", "63")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner957 = new Document("Finish Time", "274").append("Age", "22").append("Gender", "Male")
				.append("BMI", "23").append("MilesPerWeek", "32").append("BestTime21K", "138")
				.append("BestTime10K", "62").append("TrackDifficulty", "Low");
		Document marathonRunner958 = new Document("Finish Time", "289").append("Age", "25").append("Gender", "Male")
				.append("BMI", "24").append("MilesPerWeek", "36").append("BestTime21K", "137")
				.append("BestTime10K", "64").append("TrackDifficulty", "Low");
		Document marathonRunner959 = new Document("Finish Time", "298").append("Age", "25").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "40").append("BestTime21K", "135")
				.append("BestTime10K", "61").append("TrackDifficulty", "High");
		Document marathonRunner960 = new Document("Finish Time", "275").append("Age", "36").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "38").append("BestTime21K", "132")
				.append("BestTime10K", "56").append("TrackDifficulty", "Medium");
		Document marathonRunner961 = new Document("Finish Time", "205").append("Age", "39").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "67").append("BestTime21K", "94")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner962 = new Document("Finish Time", "156").append("Age", "34").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "86").append("BestTime21K", "83")
				.append("BestTime10K", "36").append("TrackDifficulty", "Low");
		Document marathonRunner963 = new Document("Finish Time", "175").append("Age", "20").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "70").append("BestTime21K", "79")
				.append("BestTime10K", "36").append("TrackDifficulty", "Low");
		Document marathonRunner964 = new Document("Finish Time", "252").append("Age", "29").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "39").append("BestTime21K", "113")
				.append("BestTime10K", "51").append("TrackDifficulty", "High");
		Document marathonRunner965 = new Document("Finish Time", "129").append("Age", "31").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "91").append("BestTime21K", "66")
				.append("BestTime10K", "31").append("TrackDifficulty", "Medium");
		Document marathonRunner966 = new Document("Finish Time", "151").append("Age", "24").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "71").append("BestTime21K", "76")
				.append("BestTime10K", "33").append("TrackDifficulty", "Low");
		Document marathonRunner967 = new Document("Finish Time", "187").append("Age", "36").append("Gender", "feMale")
				.append("BMI", "18").append("MilesPerWeek", "59").append("BestTime21K", "101")
				.append("BestTime10K", "41").append("TrackDifficulty", "High");
		Document marathonRunner968 = new Document("Finish Time", "247").append("Age", "34").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "45").append("BestTime21K", "117")
				.append("BestTime10K", "54").append("TrackDifficulty", "Medium");
		Document marathonRunner969 = new Document("Finish Time", "235").append("Age", "39").append("Gender", "Male")
				.append("BMI", "21").append("MilesPerWeek", "51").append("BestTime21K", "111")
				.append("BestTime10K", "42").append("TrackDifficulty", "Medium");
		Document marathonRunner970 = new Document("Finish Time", "279").append("Age", "30").append("Gender", "Female")
				.append("BMI", "23").append("MilesPerWeek", "36").append("BestTime21K", "130")
				.append("BestTime10K", "58").append("TrackDifficulty", "High");
		Document marathonRunner971 = new Document("Finish Time", "157").append("Age", "39").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "79").append("BestTime21K", "72")
				.append("BestTime10K", "36").append("TrackDifficulty", "Low");
		Document marathonRunner972 = new Document("Finish Time", "150").append("Age", "31").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "89").append("BestTime21K", "80")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner973 = new Document("Finish Time", "186").append("Age", "36").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "70").append("BestTime21K", "95")
				.append("BestTime10K", "41").append("TrackDifficulty", "High");
		Document marathonRunner974 = new Document("Finish Time", "134").append("Age", "40").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "96").append("BestTime21K", "76")
				.append("BestTime10K", "33").append("TrackDifficulty", "High");
		Document marathonRunner975 = new Document("Finish Time", "195").append("Age", "27").append("Gender", "Female")
				.append("BMI", "20").append("MilesPerWeek", "69").append("BestTime21K", "85")
				.append("BestTime10K", "38").append("TrackDifficulty", "High");
		Document marathonRunner976 = new Document("Finish Time", "226").append("Age", "37").append("Gender", "Male")
				.append("BMI", "22").append("MilesPerWeek", "50").append("BestTime21K", "95")
				.append("BestTime10K", "49").append("TrackDifficulty", "Medium");
		Document marathonRunner977 = new Document("Finish Time", "182").append("Age", "39").append("Gender", "feMale")
				.append("BMI", "22").append("MilesPerWeek", "76").append("BestTime21K", "93")
				.append("BestTime10K", "41").append("TrackDifficulty", "Medium");
		Document marathonRunner978 = new Document("Finish Time", "172").append("Age", "30").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "89").append("BestTime21K", "77")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner979 = new Document("Finish Time", "283").append("Age", "27").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "36").append("BestTime21K", "145")
				.append("BestTime10K", "64").append("TrackDifficulty", "Medium");
		Document marathonRunner980 = new Document("Finish Time", "189").append("Age", "28").append("Gender", "Female")
				.append("BMI", "22").append("MilesPerWeek", "68").append("BestTime21K", "93")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");
		Document marathonRunner981 = new Document("Finish Time", "276").append("Age", "37").append("Gender", "Female")
				.append("BMI", "19").append("MilesPerWeek", "41").append("BestTime21K", "135")
				.append("BestTime10K", "59").append("TrackDifficulty", "Low");
		Document marathonRunner982 = new Document("Finish Time", "201").append("Age", "34").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "51").append("BestTime21K", "85")
				.append("BestTime10K", "39").append("TrackDifficulty", "Medium");
		Document marathonRunner983 = new Document("Finish Time", "249").append("Age", "40").append("Gender", "Female")
				.append("BMI", "25").append("MilesPerWeek", "43").append("BestTime21K", "115")
				.append("BestTime10K", "48").append("TrackDifficulty", "Medium");
		Document marathonRunner984 = new Document("Finish Time", "199").append("Age", "33").append("Gender", "feMale")
				.append("BMI", "22").append("MilesPerWeek", "52").append("BestTime21K", "102")
				.append("BestTime10K", "40").append("TrackDifficulty", "High");
		Document marathonRunner985 = new Document("Finish Time", "131").append("Age", "36").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "100").append("BestTime21K", "62")
				.append("BestTime10K", "32").append("TrackDifficulty", "High");
		Document marathonRunner986 = new Document("Finish Time", "208").append("Age", "21").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "68").append("BestTime21K", "103")
				.append("BestTime10K", "38").append("TrackDifficulty", "Medium");
		Document marathonRunner987 = new Document("Finish Time", "253").append("Age", "39").append("Gender", "Female")
				.append("BMI", "18").append("MilesPerWeek", "45").append("BestTime21K", "112")
				.append("BestTime10K", "56").append("TrackDifficulty", "High");
		Document marathonRunner988 = new Document("Finish Time", "284").append("Age", "39").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "38").append("BestTime21K", "132")
				.append("BestTime10K", "62").append("TrackDifficulty", "Low");
		Document marathonRunner989 = new Document("Finish Time", "299").append("Age", "35").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "38").append("BestTime21K", "135")
				.append("BestTime10K", "55").append("TrackDifficulty", "Medium");
		Document marathonRunner990 = new Document("Finish Time", "150").append("Age", "38").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "86").append("BestTime21K", "66")
				.append("BestTime10K", "32").append("TrackDifficulty", "Medium");
		Document marathonRunner991 = new Document("Finish Time", "295").append("Age", "27").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "36").append("BestTime21K", "133")
				.append("BestTime10K", "63").append("TrackDifficulty", "High");
		Document marathonRunner992 = new Document("Finish Time", "180").append("Age", "34").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "62").append("BestTime21K", "96")
				.append("BestTime10K", "40").append("TrackDifficulty", "Medium");
		Document marathonRunner993 = new Document("Finish Time", "190").append("Age", "35").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "65").append("BestTime21K", "88")
				.append("BestTime10K", "39").append("TrackDifficulty", "Low");
		Document marathonRunner994 = new Document("Finish Time", "148").append("Age", "23").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "78").append("BestTime21K", "69")
				.append("BestTime10K", "32").append("TrackDifficulty", "Low");
		Document marathonRunner995 = new Document("Finish Time", "220").append("Age", "32").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "57").append("BestTime21K", "104")
				.append("BestTime10K", "44").append("TrackDifficulty", "Medium");
		Document marathonRunner996 = new Document("Finish Time", "167").append("Age", "38").append("Gender", "Female")
				.append("BMI", "21").append("MilesPerWeek", "83").append("BestTime21K", "83")
				.append("BestTime10K", "34").append("TrackDifficulty", "Medium");
		Document marathonRunner997 = new Document("Finish Time", "146").append("Age", "38").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "100").append("BestTime21K", "61")
				.append("BestTime10K", "30").append("TrackDifficulty", "High");
		Document marathonRunner998 = new Document("Finish Time", "290").append("Age", "30").append("Gender", "Male")
				.append("BMI", "20").append("MilesPerWeek", "40").append("BestTime21K", "136")
				.append("BestTime10K", "60").append("TrackDifficulty", "High");
		Document marathonRunner999 = new Document("Finish Time", "201").append("Age", "31").append("Gender", "Male")
				.append("BMI", "18").append("MilesPerWeek", "50").append("BestTime21K", "90")
				.append("BestTime10K", "38").append("TrackDifficulty", "Medium");
		Document marathonRunner1000 = new Document("Finish Time", "208").append("Age", "27").append("Gender", "Male")
				.append("BMI", "19").append("MilesPerWeek", "63").append("BestTime21K", "89")
				.append("BestTime10K", "39").append("TrackDifficulty", "High");

		collection.insertOne(marathonRunner501);
		collection.insertOne(marathonRunner502);
		collection.insertOne(marathonRunner503);
		collection.insertOne(marathonRunner504);
		collection.insertOne(marathonRunner505);
		collection.insertOne(marathonRunner506);
		collection.insertOne(marathonRunner507);
		collection.insertOne(marathonRunner508);
		collection.insertOne(marathonRunner509);
		collection.insertOne(marathonRunner510);
		collection.insertOne(marathonRunner511);
		collection.insertOne(marathonRunner512);
		collection.insertOne(marathonRunner513);
		collection.insertOne(marathonRunner514);
		collection.insertOne(marathonRunner515);
		collection.insertOne(marathonRunner516);
		collection.insertOne(marathonRunner517);
		collection.insertOne(marathonRunner518);
		collection.insertOne(marathonRunner519);
		collection.insertOne(marathonRunner520);
		collection.insertOne(marathonRunner521);
		collection.insertOne(marathonRunner522);
		collection.insertOne(marathonRunner523);
		collection.insertOne(marathonRunner524);
		collection.insertOne(marathonRunner525);
		collection.insertOne(marathonRunner526);
		collection.insertOne(marathonRunner527);
		collection.insertOne(marathonRunner528);
		collection.insertOne(marathonRunner529);
		collection.insertOne(marathonRunner530);
		collection.insertOne(marathonRunner531);
		collection.insertOne(marathonRunner532);
		collection.insertOne(marathonRunner533);
		collection.insertOne(marathonRunner534);
		collection.insertOne(marathonRunner535);
		collection.insertOne(marathonRunner536);
		collection.insertOne(marathonRunner537);
		collection.insertOne(marathonRunner538);
		collection.insertOne(marathonRunner539);
		collection.insertOne(marathonRunner540);
		collection.insertOne(marathonRunner541);
		collection.insertOne(marathonRunner542);
		collection.insertOne(marathonRunner543);
		collection.insertOne(marathonRunner544);
		collection.insertOne(marathonRunner545);
		collection.insertOne(marathonRunner546);
		collection.insertOne(marathonRunner547);
		collection.insertOne(marathonRunner548);
		collection.insertOne(marathonRunner549);
		collection.insertOne(marathonRunner550);
		collection.insertOne(marathonRunner551);
		collection.insertOne(marathonRunner552);
		collection.insertOne(marathonRunner553);
		collection.insertOne(marathonRunner554);
		collection.insertOne(marathonRunner555);
		collection.insertOne(marathonRunner556);
		collection.insertOne(marathonRunner557);
		collection.insertOne(marathonRunner558);
		collection.insertOne(marathonRunner559);
		collection.insertOne(marathonRunner560);
		collection.insertOne(marathonRunner561);
		collection.insertOne(marathonRunner562);
		collection.insertOne(marathonRunner563);
		collection.insertOne(marathonRunner564);
		collection.insertOne(marathonRunner565);
		collection.insertOne(marathonRunner566);
		collection.insertOne(marathonRunner567);
		collection.insertOne(marathonRunner568);
		collection.insertOne(marathonRunner569);
		collection.insertOne(marathonRunner570);
		collection.insertOne(marathonRunner571);
		collection.insertOne(marathonRunner572);
		collection.insertOne(marathonRunner573);
		collection.insertOne(marathonRunner574);
		collection.insertOne(marathonRunner575);
		collection.insertOne(marathonRunner576);
		collection.insertOne(marathonRunner577);
		collection.insertOne(marathonRunner578);
		collection.insertOne(marathonRunner579);
		collection.insertOne(marathonRunner580);
		collection.insertOne(marathonRunner581);
		collection.insertOne(marathonRunner582);
		collection.insertOne(marathonRunner583);
		collection.insertOne(marathonRunner584);
		collection.insertOne(marathonRunner585);
		collection.insertOne(marathonRunner586);
		collection.insertOne(marathonRunner587);
		collection.insertOne(marathonRunner588);
		collection.insertOne(marathonRunner589);
		collection.insertOne(marathonRunner590);
		collection.insertOne(marathonRunner591);
		collection.insertOne(marathonRunner592);
		collection.insertOne(marathonRunner593);
		collection.insertOne(marathonRunner594);
		collection.insertOne(marathonRunner595);
		collection.insertOne(marathonRunner596);
		collection.insertOne(marathonRunner597);
		collection.insertOne(marathonRunner598);
		collection.insertOne(marathonRunner599);
		collection.insertOne(marathonRunner600);
		collection.insertOne(marathonRunner601);
		collection.insertOne(marathonRunner602);
		collection.insertOne(marathonRunner603);
		collection.insertOne(marathonRunner604);
		collection.insertOne(marathonRunner605);
		collection.insertOne(marathonRunner606);
		collection.insertOne(marathonRunner607);
		collection.insertOne(marathonRunner608);
		collection.insertOne(marathonRunner609);
		collection.insertOne(marathonRunner610);
		collection.insertOne(marathonRunner611);
		collection.insertOne(marathonRunner612);
		collection.insertOne(marathonRunner613);
		collection.insertOne(marathonRunner614);
		collection.insertOne(marathonRunner615);
		collection.insertOne(marathonRunner616);
		collection.insertOne(marathonRunner617);
		collection.insertOne(marathonRunner618);
		collection.insertOne(marathonRunner619);
		collection.insertOne(marathonRunner620);
		collection.insertOne(marathonRunner621);
		collection.insertOne(marathonRunner622);
		collection.insertOne(marathonRunner623);
		collection.insertOne(marathonRunner624);
		collection.insertOne(marathonRunner625);
		collection.insertOne(marathonRunner626);
		collection.insertOne(marathonRunner627);
		collection.insertOne(marathonRunner628);
		collection.insertOne(marathonRunner629);
		collection.insertOne(marathonRunner630);
		collection.insertOne(marathonRunner631);
		collection.insertOne(marathonRunner632);
		collection.insertOne(marathonRunner633);
		collection.insertOne(marathonRunner634);
		collection.insertOne(marathonRunner635);
		collection.insertOne(marathonRunner636);
		collection.insertOne(marathonRunner637);
		collection.insertOne(marathonRunner638);
		collection.insertOne(marathonRunner639);
		collection.insertOne(marathonRunner640);
		collection.insertOne(marathonRunner641);
		collection.insertOne(marathonRunner642);
		collection.insertOne(marathonRunner643);
		collection.insertOne(marathonRunner644);
		collection.insertOne(marathonRunner645);
		collection.insertOne(marathonRunner646);
		collection.insertOne(marathonRunner647);
		collection.insertOne(marathonRunner648);
		collection.insertOne(marathonRunner649);
		collection.insertOne(marathonRunner650);
		collection.insertOne(marathonRunner651);
		collection.insertOne(marathonRunner652);
		collection.insertOne(marathonRunner653);
		collection.insertOne(marathonRunner654);
		collection.insertOne(marathonRunner655);
		collection.insertOne(marathonRunner656);
		collection.insertOne(marathonRunner657);
		collection.insertOne(marathonRunner658);
		collection.insertOne(marathonRunner659);
		collection.insertOne(marathonRunner660);
		collection.insertOne(marathonRunner661);
		collection.insertOne(marathonRunner662);
		collection.insertOne(marathonRunner663);
		collection.insertOne(marathonRunner664);
		collection.insertOne(marathonRunner665);
		collection.insertOne(marathonRunner666);
		collection.insertOne(marathonRunner667);
		collection.insertOne(marathonRunner668);
		collection.insertOne(marathonRunner669);
		collection.insertOne(marathonRunner670);
		collection.insertOne(marathonRunner671);
		collection.insertOne(marathonRunner672);
		collection.insertOne(marathonRunner673);
		collection.insertOne(marathonRunner674);
		collection.insertOne(marathonRunner675);
		collection.insertOne(marathonRunner676);
		collection.insertOne(marathonRunner677);
		collection.insertOne(marathonRunner678);
		collection.insertOne(marathonRunner679);
		collection.insertOne(marathonRunner680);
		collection.insertOne(marathonRunner681);
		collection.insertOne(marathonRunner682);
		collection.insertOne(marathonRunner683);
		collection.insertOne(marathonRunner684);
		collection.insertOne(marathonRunner685);
		collection.insertOne(marathonRunner686);
		collection.insertOne(marathonRunner687);
		collection.insertOne(marathonRunner688);
		collection.insertOne(marathonRunner689);
		collection.insertOne(marathonRunner690);
		collection.insertOne(marathonRunner691);
		collection.insertOne(marathonRunner692);
		collection.insertOne(marathonRunner693);
		collection.insertOne(marathonRunner694);
		collection.insertOne(marathonRunner695);
		collection.insertOne(marathonRunner696);
		collection.insertOne(marathonRunner697);
		collection.insertOne(marathonRunner698);
		collection.insertOne(marathonRunner699);
		collection.insertOne(marathonRunner700);
		collection.insertOne(marathonRunner701);
		collection.insertOne(marathonRunner702);
		collection.insertOne(marathonRunner703);
		collection.insertOne(marathonRunner704);
		collection.insertOne(marathonRunner705);
		collection.insertOne(marathonRunner706);
		collection.insertOne(marathonRunner707);
		collection.insertOne(marathonRunner708);
		collection.insertOne(marathonRunner709);
		collection.insertOne(marathonRunner710);
		collection.insertOne(marathonRunner711);
		collection.insertOne(marathonRunner712);
		collection.insertOne(marathonRunner713);
		collection.insertOne(marathonRunner714);
		collection.insertOne(marathonRunner715);
		collection.insertOne(marathonRunner716);
		collection.insertOne(marathonRunner717);
		collection.insertOne(marathonRunner718);
		collection.insertOne(marathonRunner719);
		collection.insertOne(marathonRunner720);
		collection.insertOne(marathonRunner721);
		collection.insertOne(marathonRunner722);
		collection.insertOne(marathonRunner723);
		collection.insertOne(marathonRunner724);
		collection.insertOne(marathonRunner725);
		collection.insertOne(marathonRunner726);
		collection.insertOne(marathonRunner727);
		collection.insertOne(marathonRunner728);
		collection.insertOne(marathonRunner729);
		collection.insertOne(marathonRunner730);
		collection.insertOne(marathonRunner731);
		collection.insertOne(marathonRunner732);
		collection.insertOne(marathonRunner733);
		collection.insertOne(marathonRunner734);
		collection.insertOne(marathonRunner735);
		collection.insertOne(marathonRunner736);
		collection.insertOne(marathonRunner737);
		collection.insertOne(marathonRunner738);
		collection.insertOne(marathonRunner739);
		collection.insertOne(marathonRunner740);
		collection.insertOne(marathonRunner741);
		collection.insertOne(marathonRunner742);
		collection.insertOne(marathonRunner743);
		collection.insertOne(marathonRunner744);
		collection.insertOne(marathonRunner745);
		collection.insertOne(marathonRunner746);
		collection.insertOne(marathonRunner747);
		collection.insertOne(marathonRunner748);
		collection.insertOne(marathonRunner749);
		collection.insertOne(marathonRunner750);
		collection.insertOne(marathonRunner751);
		collection.insertOne(marathonRunner752);
		collection.insertOne(marathonRunner753);
		collection.insertOne(marathonRunner754);
		collection.insertOne(marathonRunner755);
		collection.insertOne(marathonRunner756);
		collection.insertOne(marathonRunner757);
		collection.insertOne(marathonRunner758);
		collection.insertOne(marathonRunner759);
		collection.insertOne(marathonRunner760);
		collection.insertOne(marathonRunner761);
		collection.insertOne(marathonRunner762);
		collection.insertOne(marathonRunner763);
		collection.insertOne(marathonRunner764);
		collection.insertOne(marathonRunner765);
		collection.insertOne(marathonRunner766);
		collection.insertOne(marathonRunner767);
		collection.insertOne(marathonRunner768);
		collection.insertOne(marathonRunner769);
		collection.insertOne(marathonRunner770);
		collection.insertOne(marathonRunner771);
		collection.insertOne(marathonRunner772);
		collection.insertOne(marathonRunner773);
		collection.insertOne(marathonRunner774);
		collection.insertOne(marathonRunner775);
		collection.insertOne(marathonRunner776);
		collection.insertOne(marathonRunner777);
		collection.insertOne(marathonRunner778);
		collection.insertOne(marathonRunner779);
		collection.insertOne(marathonRunner780);
		collection.insertOne(marathonRunner781);
		collection.insertOne(marathonRunner782);
		collection.insertOne(marathonRunner783);
		collection.insertOne(marathonRunner784);
		collection.insertOne(marathonRunner785);
		collection.insertOne(marathonRunner786);
		collection.insertOne(marathonRunner787);
		collection.insertOne(marathonRunner788);
		collection.insertOne(marathonRunner789);
		collection.insertOne(marathonRunner790);
		collection.insertOne(marathonRunner791);
		collection.insertOne(marathonRunner792);
		collection.insertOne(marathonRunner793);
		collection.insertOne(marathonRunner794);
		collection.insertOne(marathonRunner795);
		collection.insertOne(marathonRunner796);
		collection.insertOne(marathonRunner797);
		collection.insertOne(marathonRunner798);
		collection.insertOne(marathonRunner799);
		collection.insertOne(marathonRunner800);
		collection.insertOne(marathonRunner801);
		collection.insertOne(marathonRunner802);
		collection.insertOne(marathonRunner803);
		collection.insertOne(marathonRunner804);
		collection.insertOne(marathonRunner805);
		collection.insertOne(marathonRunner806);
		collection.insertOne(marathonRunner807);
		collection.insertOne(marathonRunner808);
		collection.insertOne(marathonRunner809);
		collection.insertOne(marathonRunner810);
		collection.insertOne(marathonRunner811);
		collection.insertOne(marathonRunner812);
		collection.insertOne(marathonRunner813);
		collection.insertOne(marathonRunner814);
		collection.insertOne(marathonRunner815);
		collection.insertOne(marathonRunner816);
		collection.insertOne(marathonRunner817);
		collection.insertOne(marathonRunner818);
		collection.insertOne(marathonRunner819);
		collection.insertOne(marathonRunner820);
		collection.insertOne(marathonRunner821);
		collection.insertOne(marathonRunner822);
		collection.insertOne(marathonRunner823);
		collection.insertOne(marathonRunner824);
		collection.insertOne(marathonRunner825);
		collection.insertOne(marathonRunner826);
		collection.insertOne(marathonRunner827);
		collection.insertOne(marathonRunner828);
		collection.insertOne(marathonRunner829);
		collection.insertOne(marathonRunner830);
		collection.insertOne(marathonRunner831);
		collection.insertOne(marathonRunner832);
		collection.insertOne(marathonRunner833);
		collection.insertOne(marathonRunner834);
		collection.insertOne(marathonRunner835);
		collection.insertOne(marathonRunner836);
		collection.insertOne(marathonRunner837);
		collection.insertOne(marathonRunner838);
		collection.insertOne(marathonRunner839);
		collection.insertOne(marathonRunner840);
		collection.insertOne(marathonRunner841);
		collection.insertOne(marathonRunner842);
		collection.insertOne(marathonRunner843);
		collection.insertOne(marathonRunner844);
		collection.insertOne(marathonRunner845);
		collection.insertOne(marathonRunner846);
		collection.insertOne(marathonRunner847);
		collection.insertOne(marathonRunner848);
		collection.insertOne(marathonRunner849);
		collection.insertOne(marathonRunner850);
		collection.insertOne(marathonRunner851);
		collection.insertOne(marathonRunner852);
		collection.insertOne(marathonRunner853);
		collection.insertOne(marathonRunner854);
		collection.insertOne(marathonRunner855);
		collection.insertOne(marathonRunner856);
		collection.insertOne(marathonRunner857);
		collection.insertOne(marathonRunner858);
		collection.insertOne(marathonRunner859);
		collection.insertOne(marathonRunner860);
		collection.insertOne(marathonRunner861);
		collection.insertOne(marathonRunner862);
		collection.insertOne(marathonRunner863);
		collection.insertOne(marathonRunner864);
		collection.insertOne(marathonRunner865);
		collection.insertOne(marathonRunner866);
		collection.insertOne(marathonRunner867);
		collection.insertOne(marathonRunner868);
		collection.insertOne(marathonRunner869);
		collection.insertOne(marathonRunner870);
		collection.insertOne(marathonRunner871);
		collection.insertOne(marathonRunner872);
		collection.insertOne(marathonRunner873);
		collection.insertOne(marathonRunner874);
		collection.insertOne(marathonRunner875);
		collection.insertOne(marathonRunner876);
		collection.insertOne(marathonRunner877);
		collection.insertOne(marathonRunner878);
		collection.insertOne(marathonRunner879);
		collection.insertOne(marathonRunner880);
		collection.insertOne(marathonRunner881);
		collection.insertOne(marathonRunner882);
		collection.insertOne(marathonRunner883);
		collection.insertOne(marathonRunner884);
		collection.insertOne(marathonRunner885);
		collection.insertOne(marathonRunner886);
		collection.insertOne(marathonRunner887);
		collection.insertOne(marathonRunner888);
		collection.insertOne(marathonRunner889);
		collection.insertOne(marathonRunner890);
		collection.insertOne(marathonRunner891);
		collection.insertOne(marathonRunner892);
		collection.insertOne(marathonRunner893);
		collection.insertOne(marathonRunner894);
		collection.insertOne(marathonRunner895);
		collection.insertOne(marathonRunner896);
		collection.insertOne(marathonRunner897);
		collection.insertOne(marathonRunner898);
		collection.insertOne(marathonRunner899);
		collection.insertOne(marathonRunner900);
		collection.insertOne(marathonRunner901);
		collection.insertOne(marathonRunner902);
		collection.insertOne(marathonRunner903);
		collection.insertOne(marathonRunner904);
		collection.insertOne(marathonRunner905);
		collection.insertOne(marathonRunner906);
		collection.insertOne(marathonRunner907);
		collection.insertOne(marathonRunner908);
		collection.insertOne(marathonRunner909);
		collection.insertOne(marathonRunner910);
		collection.insertOne(marathonRunner911);
		collection.insertOne(marathonRunner912);
		collection.insertOne(marathonRunner913);
		collection.insertOne(marathonRunner914);
		collection.insertOne(marathonRunner915);
		collection.insertOne(marathonRunner916);
		collection.insertOne(marathonRunner917);
		collection.insertOne(marathonRunner918);
		collection.insertOne(marathonRunner919);
		collection.insertOne(marathonRunner920);
		collection.insertOne(marathonRunner921);
		collection.insertOne(marathonRunner922);
		collection.insertOne(marathonRunner923);
		collection.insertOne(marathonRunner924);
		collection.insertOne(marathonRunner925);
		collection.insertOne(marathonRunner926);
		collection.insertOne(marathonRunner927);
		collection.insertOne(marathonRunner928);
		collection.insertOne(marathonRunner929);
		collection.insertOne(marathonRunner930);
		collection.insertOne(marathonRunner931);
		collection.insertOne(marathonRunner932);
		collection.insertOne(marathonRunner933);
		collection.insertOne(marathonRunner934);
		collection.insertOne(marathonRunner935);
		collection.insertOne(marathonRunner936);
		collection.insertOne(marathonRunner937);
		collection.insertOne(marathonRunner938);
		collection.insertOne(marathonRunner939);
		collection.insertOne(marathonRunner940);
		collection.insertOne(marathonRunner941);
		collection.insertOne(marathonRunner942);
		collection.insertOne(marathonRunner943);
		collection.insertOne(marathonRunner944);
		collection.insertOne(marathonRunner945);
		collection.insertOne(marathonRunner946);
		collection.insertOne(marathonRunner947);
		collection.insertOne(marathonRunner948);
		collection.insertOne(marathonRunner949);
		collection.insertOne(marathonRunner950);
		collection.insertOne(marathonRunner951);
		collection.insertOne(marathonRunner952);
		collection.insertOne(marathonRunner953);
		collection.insertOne(marathonRunner954);
		collection.insertOne(marathonRunner955);
		collection.insertOne(marathonRunner956);
		collection.insertOne(marathonRunner957);
		collection.insertOne(marathonRunner958);
		collection.insertOne(marathonRunner959);
		collection.insertOne(marathonRunner960);
		collection.insertOne(marathonRunner961);
		collection.insertOne(marathonRunner962);
		collection.insertOne(marathonRunner963);
		collection.insertOne(marathonRunner964);
		collection.insertOne(marathonRunner965);
		collection.insertOne(marathonRunner966);
		collection.insertOne(marathonRunner967);
		collection.insertOne(marathonRunner968);
		collection.insertOne(marathonRunner969);
		collection.insertOne(marathonRunner970);
		collection.insertOne(marathonRunner971);
		collection.insertOne(marathonRunner972);
		collection.insertOne(marathonRunner973);
		collection.insertOne(marathonRunner974);
		collection.insertOne(marathonRunner975);
		collection.insertOne(marathonRunner976);
		collection.insertOne(marathonRunner977);
		collection.insertOne(marathonRunner978);
		collection.insertOne(marathonRunner979);
		collection.insertOne(marathonRunner980);
		collection.insertOne(marathonRunner981);
		collection.insertOne(marathonRunner982);
		collection.insertOne(marathonRunner983);
		collection.insertOne(marathonRunner984);
		collection.insertOne(marathonRunner985);
		collection.insertOne(marathonRunner986);
		collection.insertOne(marathonRunner987);
		collection.insertOne(marathonRunner988);
		collection.insertOne(marathonRunner989);
		collection.insertOne(marathonRunner990);
		collection.insertOne(marathonRunner991);
		collection.insertOne(marathonRunner992);
		collection.insertOne(marathonRunner993);
		collection.insertOne(marathonRunner994);
		collection.insertOne(marathonRunner995);
		collection.insertOne(marathonRunner996);
		collection.insertOne(marathonRunner997);
		collection.insertOne(marathonRunner998);
		collection.insertOne(marathonRunner999);
		collection.insertOne(marathonRunner1000);

	}

}