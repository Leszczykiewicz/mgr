
public class Frame {
	private float C[];
	private int time;
	
	private float avgC;
	
	@Override
	public String toString() {
		String result = "";
		for(float c : C){
			result += c+" ";
		}
		return result+"\n";
	}
	
	
	public float getAvgC(){		
		return this.avgC;
	}


	public float[] getC() {
		return C;
	}

	public void setC(float[] c) {
		C = c;
		float avg = 0;
		for(float cc: C){
			avg += cc;
		}
		this.avgC = avg/C.length;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public void setAvgC(float avg){
		this.avgC = avg;
	}

}
