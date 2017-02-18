
public class CorrelationObject implements Comparable{
	public float value;
	public int id;
	
	public int compareTo(Object arg0) {
		if(this.value < ((CorrelationObject)arg0).value){
			return 1;
		}
		if(this.value > ((CorrelationObject)arg0).value){
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
