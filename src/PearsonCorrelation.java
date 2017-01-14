
public class PearsonCorrelation {
	public float [] first_values;
	public float [] second_values;
	public float  covariance;
	public float correlation;
	
	
	public PearsonCorrelation(float[] first_values, float[] second_values) {
		this.first_values = first_values;
		this.second_values = second_values;	
		calculateCorrelation();
	}


	public void calculateCorrelation(){
		float multiply[] = new float[first_values.length];
		float first_values_avg = 0;
		float second_values_avg = 0;
		float multiply_avg = 0;
		for(int i = 0; i<first_values.length; i++){
			multiply[i] = first_values[i] * second_values[i];
			first_values_avg += first_values[i];
			second_values_avg += second_values[i];
			multiply_avg += multiply[i];
		}
		first_values_avg /= first_values.length;
		second_values_avg /= second_values.length;
		multiply_avg /= multiply.length;
		
		covariance =  multiply_avg - (first_values_avg * second_values_avg);
		float first_values_deviation = calculateStandardDeviation(first_values);
		float second_values_deviation = calculateStandardDeviation(second_values);
		
		correlation = covariance/(first_values_deviation*second_values_deviation);
	}
	
	
	public float calculateStandardDeviation(float[] values){
		float avg = avg(values);
		float sum = 0;
		for(int i=0; i<values.length; i++){
			sum += (float) Math.pow(values[i] - avg, 2);
		}
		return (float) Math.sqrt(sum/values.length);
	}
	
	public float avg(float[] values){
		float result = 0;
		for(float v : values){
			result += v;
		}
		return result /= values.length;
		
	}

}
