import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



public class ChartUtils
{

	ArrayList<StatisticsDataSeries> dataSeriesBackup = new ArrayList<StatisticsDataSeries>();
	int i = 0;
	
	public ChartUtils(){
		
	}
	
	public void clearCharts(){
		dataSeriesBackup.clear();
		i = 0;
	}
	public void createEmptyChart(JPanel panel, String applicationTitle, String chartTitle, String xName, String yName){
		JFreeChart lineChart = ChartFactory.createXYLineChart(
		         chartTitle,
		         xName,yName,
		         new XYSeriesCollection(),
		         PlotOrientation.VERTICAL,
		         true,true,false);
		         
		      ChartPanel chartPanel = new ChartPanel( lineChart );
		      panel.setLayout(new BorderLayout());
		      panel.add(chartPanel, BorderLayout.CENTER);
	}
	
	public void createChartForAll(JPanel panel, String applicationTitle, String chartTitle, String xName, String yName, Statistics[] statistics, int[] correlation, ArrayList<Frame> frames, ArrayList<Double> stdForAtuocorrelation) throws FileNotFoundException, IOException
	{
		System.out.println("ChartUtils");
	      JFreeChart lineChart = ChartFactory.createXYLineChart(
	         chartTitle,
	         xName,yName,
	         createDataset(statistics, correlation, frames, stdForAtuocorrelation),
	         PlotOrientation.VERTICAL,
	         true,true,false);
	         
	      ChartPanel chartPanel = new ChartPanel( lineChart );
	      panel.setLayout(new BorderLayout());
	      panel.add(chartPanel, BorderLayout.CENTER);
//	      ChartUtilities.saveChartAsJPEG(new File("wyniki/statystyka_"+ Main.dlugosc_okna_czasowego_n+ "_" +Main.file.getName()+".jpg"), lineChart, 1120, 720);
	      
	   }
	
	public void refreshChartForAll(JPanel panel, String applicationTitle, String chartTitle, String xName, String yName) throws FileNotFoundException, IOException
	{
		System.out.println("ChartUtils");
	      JFreeChart lineChart = ChartFactory.createXYLineChart(
	         chartTitle,
	         xName,yName,
	         refreshDataset(),
	         PlotOrientation.VERTICAL,
	         true,true,false);
	         
	      ChartPanel chartPanel = new ChartPanel( lineChart );
	      panel.setLayout(new BorderLayout());
	      panel.add(chartPanel, BorderLayout.CENTER);
//	      ChartUtilities.saveChartAsJPEG(new File("wyniki/statystyka_"+ Main.dlugosc_okna_czasowego_n+ "_" +Main.file.getName()+".jpg"), lineChart, 1120, 720);
	      
	   }
	
	public ChartUtils(JPanel panel, String applicationTitle, String chartTitle, String xName, String yName, Statistics[] statistics) throws FileNotFoundException, IOException
	   {
	      JFreeChart lineChart = ChartFactory.createXYLineChart(
	         chartTitle,
	         xName,yName,
	         createDataset(statistics),
	         PlotOrientation.VERTICAL,
	         true,true,false);
	         
	      ChartPanel chartPanel = new ChartPanel( lineChart );
	      panel.setLayout(new BorderLayout());
	      panel.add(chartPanel, BorderLayout.CENTER);
//	      panel.add(chartPanel);
//	      ChartUtilities.saveChartAsJPEG(new File("wyniki/statystyka_"+ Main.dlugosc_okna_czasowego_n+ "_" +Main.file.getName()+".jpg"), lineChart, 1120, 720);
	      
	   }
	
   public ChartUtils(JPanel panel, String applicationTitle, String chartTitle, String xName, String yName, ArrayList<Frame> framesA, ArrayList<Frame> framesB)
   {
      JFreeChart lineChart = ChartFactory.createXYLineChart(
         chartTitle,
         xName,yName,
         createDataset(framesA, framesB),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 1120 , 720 ) );
      panel.setLayout(new BorderLayout());
      panel.add(chartPanel, BorderLayout.CENTER);
      
   }
   
   public ChartUtils(JPanel panel, String applicationTitle, String chartTitle, String xName, String yName, ArrayList<Frame> frames) throws IOException
   {
      JFreeChart lineChart = ChartFactory.createXYLineChart(
         chartTitle,
         xName,yName,
         createDataset(frames),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      panel.setLayout(new BorderLayout());
      panel.add(chartPanel, BorderLayout.CENTER);
//      ChartUtilities.saveChartAsJPEG(new File("wyniki/przebieg_"+ Main.dlugosc_okna_czasowego_n+ "_" +Main.file.getName()+".jpg"), lineChart, 1120, 720);
   }
   
   public ChartUtils(JPanel panel, String applicationTitle, String chartTitle, String xName, String yName, int[] correlation) throws IOException
   {
      JFreeChart lineChart = ChartFactory.createXYLineChart(
         chartTitle,
         xName,yName,
         createDataset(correlation),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      panel.setLayout(new BorderLayout());
      panel.add(chartPanel, BorderLayout.CENTER);
//      ChartUtilities.saveChartAsJPEG(new File("wyniki/autokorelacja_" +Main.dlugosc_okna_czasowego_n+ "_"+ Main.file.getName()+".jpg"), lineChart, 1120, 720);
      
   }
   public ChartUtils(JPanel panel, String applicationTitle, String chartTitle, String xName, String yName, CorrelationObject[] correlation)
   {
      JFreeChart lineChart = ChartFactory.createXYLineChart(
         chartTitle,
         xName,yName,
         createDataset(correlation),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      panel.setLayout(new BorderLayout());
      panel.add(chartPanel, BorderLayout.CENTER);
   }
   
   public ChartUtils(JPanel panel, String applicationTitle, String chartTitle, String xName, String yName, float[] framesA) throws IOException
   {
      JFreeChart lineChart = ChartFactory.createXYLineChart(
         chartTitle,
         xName,yName,
         createDataset(framesA),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      panel.setLayout(new BorderLayout());
      panel.add(chartPanel, BorderLayout.CENTER);
//      ChartUtilities.saveChartAsJPEG(new File("wyniki/przebieg_" + Main.dlugosc_okna_czasowego_n+"_"+Main.file + ".jpg"), lineChart, 1120, 720);
   }
   
   public ChartUtils(JPanel panel, String applicationTitle, String chartTitle, String xName, String yName, float[] framesA, float[] framesB) throws IOException
   {
      JFreeChart lineChart = ChartFactory.createXYLineChart(
         chartTitle,
         xName,yName,
         createDataset(framesA, framesB),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      panel.setLayout(new BorderLayout());
      panel.add(chartPanel, BorderLayout.CENTER);
//      ChartUtilities.saveChartAsJPEG(new File("wyniki/przebieg_" + Main.dlugosc_okna_czasowego_n+"_"+Main.file + ".jpg"), lineChart, 1120, 720);
   }
   
   public ChartUtils(String applicationTitle, String chartTitle, String xName, String yName, CorrelationObject[] correlation)
   {
      JFreeChart lineChart = ChartFactory.createXYLineChart(
         chartTitle,
         xName,yName,
         createDataset(correlation),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 1120 , 720 ) );
   }

   private XYSeriesCollection createDataset(int[] correlations)
   {
	   int idA = 0;
	   XYSeries seriesA = new XYSeries("Korelacja");
	   for(int i: correlations){
		   seriesA.add(idA, i);
		   idA++;
	   }
	      
	   XYSeriesCollection dataset = new XYSeriesCollection();
	   dataset.addSeries(seriesA);

      return dataset;
   }
   
   private XYSeriesCollection createDataset(ArrayList<Frame> framesA, ArrayList<Frame> framesB)
   {
	   XYSeries seriesA = new XYSeries("P³aszczyzna A");
	   for(Frame f : framesA){
		   seriesA.add(f.getTime(), f.getAvgC());
	   }
	   
	   XYSeries seriesB = new XYSeries("P³aszczyzna B");
	   for(Frame f : framesB){
		   seriesB.add(f.getTime(), f.getAvgC());
	   }
	   XYSeriesCollection dataset = new XYSeriesCollection();
	   dataset.addSeries(seriesA);
	   dataset.addSeries(seriesB);

      return dataset;
   }
   
   private XYSeriesCollection createDataset(Statistics[] statistics)
   {
	   XYSeries seriesA = new XYSeries("œrednia");
	   XYSeries seriesB = new XYSeries("mediana");
	   XYSeries seriesC = new XYSeries("odchylenie standardowe");
	   XYSeries seriesD = new XYSeries("wariancja");
	   for(Statistics s : statistics){
		   seriesA.add(s.getTime(), s.getAvg());
		   seriesB.add(s.getTime(), s.getMedian());
		   seriesC.add(s.getTime(), s.getStd());
		   seriesD.add(s.getTime(), s.getVariance());
	   }
	   
	   XYSeriesCollection dataset = new XYSeriesCollection();

	   dataset.addSeries(seriesA);
	   dataset.addSeries(seriesB);
	   dataset.addSeries(seriesC);
	   dataset.addSeries(seriesD);

      return dataset;
   }
   
   private XYSeriesCollection createDataset(Statistics[] statistics, int[] correlation, ArrayList<Frame> frames, ArrayList<Double> stdForAtuocorrelation)
   {
	   System.out.println("CreateDataset");
	   System.out.println(i);
	   XYSeries seriesA = new XYSeries("œrednia"+i);
	   XYSeries seriesB = new XYSeries("mediana"+i);
	   XYSeries seriesC = new XYSeries("odchylenie standardowe"+i);
	   XYSeries seriesD = new XYSeries("wariancja"+i);
	   XYSeries seriesE = new XYSeries("autokorelacja"+i);
	   XYSeries seriesF = new XYSeries("Przebieg"+i);
	   XYSeries seriesG = new XYSeries("odchylenie autokorelacji"+i);
	   
	   for(Statistics s : statistics){
		   seriesA.add(s.getTime(), s.getAvg()*100);
		   seriesB.add(s.getTime(), s.getMedian()*100);
		   seriesC.add(s.getTime(), s.getStd()*100);
		   seriesD.add(s.getTime(), s.getVariance()*100);
	   }
	   
	   int idE = 0;

	   for(int i: correlation){
		   seriesE.add(idE, i);
		   idE++;
	   }
	    
	   for(Frame f : frames){
		   seriesF.add(f.getTime(), f.getAvgC()*100);
	   }
	   
	   int idG = 0;
	   for(Double f : stdForAtuocorrelation){
		   seriesG.add(idG, f);
		   idG++;
	   }
	   
	   dataSeriesBackup.add(new StatisticsDataSeries(seriesA, STATISTICS_TYPE.AVG));
	   dataSeriesBackup.add(new StatisticsDataSeries(seriesB, STATISTICS_TYPE.MEDIAN));
	   dataSeriesBackup.add(new StatisticsDataSeries(seriesC, STATISTICS_TYPE.STD));
	   dataSeriesBackup.add(new StatisticsDataSeries(seriesD, STATISTICS_TYPE.VARIANCE));
	   dataSeriesBackup.add(new StatisticsDataSeries(seriesE, STATISTICS_TYPE.AUTOCORRELATION));
	   dataSeriesBackup.add(new StatisticsDataSeries(seriesF, STATISTICS_TYPE.MEASUREMENT));
	   dataSeriesBackup.add(new StatisticsDataSeries(seriesG, STATISTICS_TYPE.STD_AUTOCORRELATION));
	   
	   XYSeriesCollection dataset = new XYSeriesCollection();

	   for(StatisticsDataSeries s:dataSeriesBackup){
		   System.out.println(s.series.getKey());
		   if(GUI1.avg.isSelected() && s.type == STATISTICS_TYPE.AVG){
			   dataset.addSeries(s.series);
		   }
		   else if(GUI1.median.isSelected() && s.type == STATISTICS_TYPE.MEDIAN){
			   dataset.addSeries(s.series);
		   }
		   else if(GUI1.std.isSelected() && s.type == STATISTICS_TYPE.STD){
			   dataset.addSeries(s.series);
		   }
		   else if(GUI1.measurement.isSelected() && s.type == STATISTICS_TYPE.MEASUREMENT){
			   dataset.addSeries(s.series);
		   }
		   else if(GUI1.autocorrelationButton.isSelected() && s.type == STATISTICS_TYPE.AUTOCORRELATION){
			   dataset.addSeries(s.series);
		   }
		   else if(GUI1.variance.isSelected() && s.type == STATISTICS_TYPE.VARIANCE){
			   dataset.addSeries(s.series);
		   }
		   else if(GUI1.autocorrelationStd.isSelected() && s.type == STATISTICS_TYPE.STD_AUTOCORRELATION){
			   dataset.addSeries(s.series);
		   }
	   }

	   i++;
      return dataset;
   }
   
   private XYSeriesCollection refreshDataset()
   {
//	   System.out.println("refreshDataset");	   
	   
	   XYSeriesCollection dataset = new XYSeriesCollection();

	   for(StatisticsDataSeries s:dataSeriesBackup){
//		   System.out.println(s.series.getKey());
		   if(GUI1.avg.isSelected() && s.type == STATISTICS_TYPE.AVG){
			   dataset.addSeries(s.series);
		   }
		   else if(GUI1.median.isSelected() && s.type == STATISTICS_TYPE.MEDIAN){
			   dataset.addSeries(s.series);
		   }
		   else if(GUI1.std.isSelected() && s.type == STATISTICS_TYPE.STD){
			   dataset.addSeries(s.series);
		   }
		   else if(GUI1.measurement.isSelected() && s.type == STATISTICS_TYPE.MEASUREMENT){
			   dataset.addSeries(s.series);
		   }
		   else if(GUI1.autocorrelationButton.isSelected() && s.type == STATISTICS_TYPE.AUTOCORRELATION){
			   dataset.addSeries(s.series);
		   }
		   else if(GUI1.variance.isSelected() && s.type == STATISTICS_TYPE.VARIANCE){
			   dataset.addSeries(s.series);
		   }
		   else if(GUI1.autocorrelationStd.isSelected() && s.type == STATISTICS_TYPE.STD_AUTOCORRELATION){
			   dataset.addSeries(s.series);
		   }
	   }

      return dataset;
   }
   
   private XYSeriesCollection createDataset(ArrayList<Frame> frames)
   {
	   XYSeries seriesA = new XYSeries("P³aszczyzna A");
	   for(Frame f : frames){
		   seriesA.add(f.getTime(), f.getAvgC());
	   }
	   
	  
	   XYSeriesCollection dataset = new XYSeriesCollection();
	   dataset.addSeries(seriesA);

      return dataset;
   }
   
   private XYSeriesCollection createDataset(float[] framesA, float[] framesB)
   {
	   XYSeries seriesA = new XYSeries("P³aszczyzna A");
	   int i = 0;
	   for(float f : framesA){
		   seriesA.add(i, f);
		   i++;
	   }
	   i=0;
	   XYSeries seriesB = new XYSeries("P³aszczyzna B");
	   for(float f : framesB){
		   seriesB.add(i, f);
		   i++;
	   }
	   
	   XYSeriesCollection dataset = new XYSeriesCollection();
	   dataset.addSeries(seriesA);
	   dataset.addSeries(seriesB);

      return dataset;
   }
   
   private XYSeriesCollection createDataset(float[] framesA)
   {
	   XYSeries seriesA = new XYSeries("P³aszczyzna A");
	   int i = 0;
	   for(float f : framesA){
		   seriesA.add(i, f);
		   i++;
	   }
	  
	   
	   XYSeriesCollection dataset = new XYSeriesCollection();
	   dataset.addSeries(seriesA);

      return dataset;
   }
   
   private XYSeriesCollection createDataset(CorrelationObject[] correlation)
   {
	   XYSeries series = new XYSeries("Korelacja");
	   for(CorrelationObject c : correlation){
		   series.add(c.id, c.value);
	   }
	   
	   XYSeriesCollection dataset = new XYSeriesCollection();
	   dataset.addSeries(series);

      return dataset;
   }
   
   
   
}