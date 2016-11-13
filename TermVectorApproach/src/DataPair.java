//data pair class to map the double value to the index and allow for sorting
public class DataPair implements Comparable<DataPair>{
	private double cosVal = 0;
	private int index1 = 0;
	private int index2 = 0;

	public DataPair(double cosVal, int index1, int index2){
		this.cosVal = cosVal;
		this.index1 = index1;
		this.index2 = index2;
	}

	//return cos value
	public double getCos(){
		return cosVal;
	}

	//return index value
	public int getIndex1(){
		return index1;
	}
	//return index value
	public int getIndex2(){
		return index2;
	}

	@Override
	public int compareTo(DataPair o) {
		if (this.getCos() - o.getCos() < 0) return -1;
		if (this.getCos() - o.getCos() > 0) return 1;
		return 0;
	}



}
