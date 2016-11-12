import java.util.ArrayList;
import java.util.HashMap;

public class TermVectors {
	private static ArrayList<HashMap<String, Integer>> terms  = new ArrayList<HashMap<String, Integer>>();
	private static ArrayList<Integer> totalInstances = new ArrayList<Integer>();	//the total number of value exists 

	public static void main(String[] args) {
		
		PatientList profiles = new PatientList("test.txt");
		

		//iterate through the array to create a hashmap of term frequencies
		//column first
		for(int i = 0; i < profiles.numCols(); i++){
			HashMap<String, Integer> columnFreq = new HashMap<String, Integer>();
			int count = 0;	//count of term exists
			totalInstances.add(0);	//initialize arraylist value
			for(int j = 0; j < profiles.numRows(); j++){
				String key = profiles.get(j, i);
				//if the string has a length of greater than 0
				if(key != null && key.length() != 0){
					//increment value
					if(columnFreq.containsKey(key)){
						columnFreq.put(key, columnFreq.get(key)+1);
					} else{	//put key into hashmap
						columnFreq.put(key, 1);
					}
					count++;
				}
			}
			totalInstances.set(i, count);	//set arraylist value to final count
			terms.add(columnFreq);	//set arraylist value to final map of column
		}
		//END term frequencies

		//Vector Calculation
		for(int i = 0; i < profiles.numRows(); i++){
			ArrayList<Double> vector1 = getVector(profiles.getRow(i));
			for(int j = i + 1; j < profiles.numRows(); j++){
				ArrayList<Double> vector2 = getVector(profiles.getRow(j));
				double cosAngle = getAngle(vector1, vector2);
				//little testing done, but >0.999 seems to return most of the duplicates
				if(cosAngle > 0.999){
					System.out.println(profiles.getRow(i).toString());
					System.out.println(profiles.getRow(j).toString());
					System.out.println("Cos angle of " + i + " and " + j + " is " + cosAngle);
				}
			}
		}
		
		//Save the data, sort it for viewing??
	}

	//returns a length dimensional vector of the ArrayList document
	private static ArrayList<Double> getVector(ArrayList<String> document){
		ArrayList<Double> vector = new ArrayList<Double>();
		for(int i = 0; i < document.size(); i++){
			//to simplify TF-IDF calculation, assume that count of term T in
			//document D is 1, where T is the data entry and D is the profile.
			//TF = 1 and IDF = ln(number of documents in C)/(number of docs containing t)
			double weight = 0;
			double totalDocs = totalInstances.get(i);
			String term = document.get(i);
			if(term != null && term.length() != 0){
			double occurancesOfName = terms.get(i).get(term);
			weight = Math.log(totalDocs/occurancesOfName);
			}

			//Count of each term is assumed to be 1 for each document
			vector.add(weight);
		}
		return vector;
	}
	
	//return the cos of the angle between the two vectors
	private static double getAngle(ArrayList<Double> vector1, ArrayList<Double> vector2){
		//check inputs
		if(vector1 != null && vector2 != null && vector1.size() == vector2.size()){
			
			//cos(theta) = sum(p*q) / (sqrt(sum(p*p))*sqrt(sum(q*q))
			
			double numSum = 0;	//Sum of doc1*doc2
			double vec1Sum = 0;	//sum of doc1 squared
			double vec2Sum = 0; //Sum of doc2 squared
			
			//iterate through and multiply the values
			for(int i = 0; i < vector1.size(); i++){
				double p = vector1.get(i);
				double q = vector2.get(i);
				numSum += p * q;
				vec1Sum += p * p;
				vec2Sum += q * q;
			}
			double angle = numSum / (Math.sqrt(vec1Sum) * Math.sqrt(vec2Sum));
			return angle;
		}
		return 0;
	}

}