import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;


public class Statistics {
	
	private DescriptiveStatistics stats = new DescriptiveStatistics();
	public int time;
	
	public double avg;
	public double std;
	public double variance;
	public double median;
	
	public Statistics(int time, float[] t1){
		this.time = time;
		for(float f : t1){
			stats.addValue(f);
		}
		this.avg = stats.getMean();
		this.std = stats.getStandardDeviation();
		this.variance = stats.getPopulationVariance();
		this.median = stats.getPercentile(50);
				
	}
	public Statistics(float avg, float std, float variance, float median) {
		super();
		this.avg = avg;
		this.std = std;
		this.variance = variance;
		this.median = median;
	}
	

	public DescriptiveStatistics getStats() {
		return stats;
	}
	public void setStats(DescriptiveStatistics stats) {
		this.stats = stats;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public double getStd() {
		return std;
	}
	public void setStd(double std) {
		this.std = std;
	}
	public double getVariance() {
		return variance;
	}
	public void setVariance(double variance) {
		this.variance = variance;
	}
	public double getMedian() {
		return median;
	}
	public void setMedian(double median) {
		this.median = median;
	}
	
	

}
