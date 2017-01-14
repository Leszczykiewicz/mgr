
public class Frame {
	
	@Override
	public String toString() {
		String result = "";
		for(float c : C){
			result += c+" ";
		}
		return result+"\n";
	}

	private float C[];
	private int time;
	
	public float getAvgC(){
		float avg = 0;
		for(float c: C){
			avg += c;
		}
		return avg/C.length;
	}

	public float[] getC() {
		return C;
	}

	public void setC(float[] c) {
		C = c;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
