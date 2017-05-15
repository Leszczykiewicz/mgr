
public class CorrelationObject implements Comparable<CorrelationObject>{
	public float value;
	public int id;
	
	public int compareTo(CorrelationObject arg0) {
		if(this.value < (arg0).value){
			return 1;
		}
		else if(this.value > (arg0).value){
			return -1;
		}
		else
			return 0;
	}

	public CorrelationObject(float value, int id) {
		super();
		this.value = value;
		this.id = id;
	}


}
