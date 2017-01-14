import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


public class ChartUtils
{
   public ChartUtils(JPanel panel, String applicationTitle, String chartTitle, String xName, String yName, ArrayList<Frame> framesA, ArrayList<Frame> framesB)
   {
      JFreeChart lineChart = ChartFactory.createXYLineChart(
         chartTitle,
         xName,yName,
         createDataset(framesA, framesB),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 1120 , 734 ) );
      panel.add(chartPanel);
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
      chartPanel.setPreferredSize( new java.awt.Dimension( 1120 , 734 ) );
      panel.add(chartPanel);
   }
   
   public ChartUtils(JPanel panel, String applicationTitle, String chartTitle, String xName, String yName, float[] framesA, float[] framesB)
   {
      JFreeChart lineChart = ChartFactory.createXYLineChart(
         chartTitle,
         xName,yName,
         createDataset(framesA, framesB),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 1120 , 734 ) );
      panel.add(chartPanel);
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
      chartPanel.setPreferredSize( new java.awt.Dimension( 1120 , 734 ) );
   }

   private XYSeriesCollection createDataset(ArrayList<Frame> framesA, ArrayList<Frame> framesB)
   {
	   int idA = 0, idB=0;
	   XYSeries seriesA = new XYSeries("frameA");
	   for(Frame f : framesA){
		   seriesA.add(f.getTime(), f.getAvgC());
		   idA++;
	   }
	   
	   XYSeries seriesB = new XYSeries("frameB");
	   for(Frame f : framesB){
		   seriesB.add(f.getTime(), f.getAvgC());
		   idB++;
	   }
	   XYSeriesCollection dataset = new XYSeriesCollection();
	   dataset.addSeries(seriesA);
	   dataset.addSeries(seriesB);

      return dataset;
   }
   
   private XYSeriesCollection createDataset(float[] framesA, float[] framesB)
   {
	   XYSeries seriesA = new XYSeries("frameA");
	   int i = 0;
	   for(float f : framesA){
		   seriesA.add(i, f);
		   i++;
	   }
	   i=0;
	   XYSeries seriesB = new XYSeries("frameB");
	   for(float f : framesB){
		   seriesB.add(i, f);
		   i++;
	   }
	   
	   XYSeriesCollection dataset = new XYSeriesCollection();
	   dataset.addSeries(seriesA);
	   dataset.addSeries(seriesB);

      return dataset;
   }
   
   private XYSeriesCollection createDataset(CorrelationObject[] correlation)
   {
	   XYSeries series = new XYSeries("correlation");
	   for(CorrelationObject c : correlation){
		   series.add(c.id, c.value);
	   }
	   
	   XYSeriesCollection dataset = new XYSeriesCollection();
	   dataset.addSeries(series);

      return dataset;
   }
   
   
   
}