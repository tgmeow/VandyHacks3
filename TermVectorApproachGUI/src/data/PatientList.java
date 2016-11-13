package data;

import java.util.ArrayList;


public class PatientList extends ArrayList<Patient> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Default constructor
	public PatientList(){

	}


	//returns the yth info of xth patient
	public String get(int x, int y){
	//	if(x < numRows() && y < numCols()){
			return this.get(x).get(y);
	//	} else {
//			throw new IndexOutOfBoundsException();
//		}
	}

	//returns patient full name
	public String getName(int n){ 
		return this.get(n).getName();
	}

	//number of rows
	public int numRows(){
		return this.size();
	}

	//returns all indexes of nth patient
	public ArrayList<String> getRow(int n){ 
	//	if (n < this.size()){
			return this.get(n).get();
//		} else {
	//		throw new IndexOutOfBoundsException();
//		}
	}

	//number of columns
	public int numCols(){
		return this.get(0).size();
	}

	//creates a new ArrayList with every nth index of each patient
	public ArrayList<String> getColumn(int n){
		ArrayList<String> column = new ArrayList<String>();
	//	if(n < this.numCols()){
			for(Patient patient: this){
				column.add(patient.get(n));
			}
	//	} else {
	//		throw new IndexOutOfBoundsException();
	//	}
		return column;
	}

	//toString
	public String toString(){
		String res = "";
		for (Patient patient: this){
			res += patient.toString() + "\n";
		}
		return res;
	}

}