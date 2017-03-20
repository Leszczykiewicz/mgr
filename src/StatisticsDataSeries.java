import org.jfree.data.xy.XYSeries;


public class StatisticsDataSeries {
	
	XYSeries series;
	STATISTICS_TYPE type;

	public StatisticsDataSeries(XYSeries series, STATISTICS_TYPE type) {
		super();
		this.series = series;
		this.type = type;
	}

}
