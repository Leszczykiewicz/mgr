import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.tc33.jheatchart.HeatChart;


public class Main {
	
	public static int dlugosc_okna_czasowego_n = 100; //300
	public static int dlugosc_okna_czasowego_dla_autokorelacji = 100;
	public static int d = 0;
	public static int dlugosc_przesuniecia_k = 20;  //iloœæ ramek przesuniêcia, d³ugoœæ przesuniêcia w ramkach
	public static int k_poczatkowe;
	public static int czas_poczatkowy_t = 3000; //2000
	public static int przesuniecie_do_testow = 0;
	public static int timeForPixelMap = 0;
	public static GUI gui;
	public static int temp = 0;
	public static int pixel = 0;
	public static boolean isVector = true;
	public static boolean isAutocorrelation = false;
	public static boolean isManchester = true;
	public static boolean isPixel = false;
	public static boolean isGenerate = false;
	public static File file;
	public static ArrayList<CorrelationObject[]> correlationForTest;
	public static int[] correlations;
	public static ArrayList<Statistics> statistic_for_t;
	public static ArrayList<Double> stdForAtuocorrelation;
	public static ArrayList<Frame> framesA;
	public static ArrayList<Integer> pixelsToAutocorrelation = new ArrayList<Integer>();
	public static ChartUtils chart = new ChartUtils();
	
	
	public static void clearCharts(){
		chart.clearCharts();
		GUI.chartAllPanel.removeAll();
		chart.createEmptyChart( 
				   GUI.chartAllPanel,
			      "Dane statystyczne" ,
			      "Dane statystyczne",
			      "czas[ramka]",
			      "wartoœæ");
		GUI.chartAllPanel.repaint();
	}
	
	public static void test(){
		correlationForTest = new ArrayList<CorrelationObject[]>();
		FileUtils fu = new FileUtils();
		String pomiar = fu.loadFile(file);
		ArrayList<Frame> frames;
		ArrayList<Frame> framesA = new ArrayList<Frame>();
		
		if(isManchester){
			frames = fu.getFramesForManchester(pomiar, 28);
			framesA = frames;
		}
		else if(isGenerate){
			frames = fu.getFramesForGenerate(pomiar);
			framesA = frames;
		}
		else{
			frames = fu.getFrames(pomiar, 28);
			
			int i = 0, idA =0;
			for(Frame f : frames){
				if(i%2 == 0){
					f.setTime(idA);
					framesA.add(f);
					idA++;
				}
				i++;
			}		
		}
				
		try {
			createChartForFrames(framesA);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
//		createTestChart(framesA);		
		CorrelationObject[] correlations;
		float[] second_values;
		float[] first_values;
		if(przesuniecie_do_testow > framesA.size()-dlugosc_okna_czasowego_n-dlugosc_przesuniecia_k-1)
		{
//			System.out.println("if");
			 correlations = new CorrelationObject[framesA.size()-przesuniecie_do_testow-1];
			 second_values = new float[framesA.size()-dlugosc_okna_czasowego_n-dlugosc_przesuniecia_k-1];
			 first_values = new float[framesA.size()-dlugosc_okna_czasowego_n-dlugosc_przesuniecia_k-1];

		}
		else{
			correlations = new CorrelationObject[dlugosc_przesuniecia_k];
			first_values = new float[dlugosc_okna_czasowego_n];
			second_values = new float[dlugosc_okna_czasowego_n];
		}
		
		
				
		int id = 0;
		for(int i = przesuniecie_do_testow; i<przesuniecie_do_testow+dlugosc_okna_czasowego_n; i++){
			if(i> framesA.size()-dlugosc_okna_czasowego_n-dlugosc_przesuniecia_k-1){
				// System.out.println("Break");
				 break;
			 }
			first_values[id] = framesA.get(i).getAvgC();
			id++;
		}
		id = 0;
		for(int j=0; j<dlugosc_przesuniecia_k; j++){
//			System.out.println(j);
			if(j> framesA.size()-dlugosc_okna_czasowego_n-dlugosc_przesuniecia_k-1){
//				 System.out.println("Break");
				 break;
			 }
			
			
			int id2 = 0;
			for(int i = przesuniecie_do_testow+j; i<przesuniecie_do_testow+j+dlugosc_okna_czasowego_n; i++){
				if(i> framesA.size()-dlugosc_okna_czasowego_n-dlugosc_przesuniecia_k-1){
//					 System.out.println("Break");
					 break;
				 }
				second_values[id2] = framesA.get(i).getAvgC();
				id2++;
			}
//			System.out.println(j);
//			System.out.println("first: ");
//			for(float f: first_values){
//				System.out.println(f);
//			}
//			
//			System.out.println("second: ");
//			for(float f: second_values){
//				System.out.println(f);
//			}
			
			
			PearsonCorrelation pc = new PearsonCorrelation(first_values, second_values);
//	    	System.out.println("korelacja: "+pc.correlation); 	    		    	  
			correlations[j] = new CorrelationObject(pc.correlation, j);
//			System.out.println(correlations[j].value);
		
		}
//		czas_poczatkowy_t = przesuniecie_do_testow;
//  	  	CorrelationObject[] correlation_for_t = calculateAutocorrelation(framesA);
//		System.out.println("d³ugoœæ correlation: "+correlations.length);
		float[] correlationValues = new float[correlations.length];
		int i=0;
		
		for(CorrelationObject c : correlations){
//			System.out.println(c.value);
			correlationValues[i] = c.value;
			i++;
		}
		
  	 	createChartForCorrelation(correlations);
  	 	FindPeak fp = new FindPeak();
  	 	fp.peaks.clear();
  	 	FindPeak.peak(correlationValues);
//  	 	System.out.println("Max:");
//  	 	for(int f: fp.peaks){
//  	 		System.out.println(f);
//  	 	}
  	 	GUI.tabbedPanel.setSelectedComponent(GUI.chartCorrelationPanel);
	  	  	
//	  	createChartForCorrelation(correlationForTest.get(przesuniecie_do_testow));
	}
	public static void calculateForAutocorrelation(){
		correlationForTest = new ArrayList<CorrelationObject[]>();
		FileUtils fu = new FileUtils();
		String pomiar = fu.loadFile(file);
		ArrayList<Frame> frames;
		framesA = new ArrayList<Frame>();
		
		if(isManchester){
			frames = fu.getFramesForManchester(pomiar, 28);
			framesA = frames;
		}
		else if(isGenerate){
			frames = fu.getFramesForGenerate(pomiar);
			framesA = frames;
		}
		else{
//			System.out.println("normal file");
			frames = fu.getFrames(pomiar, 28);
			
			int i = 0, idA =0;
			for(Frame f : frames){
//				System.out.println(f.getAvgC());
				if(i%2 == 0){
					f.setTime(idA);
					framesA.add(f);
					idA++;
				}
				i++;
			}		
		}
				
		try {
			createChartForFrames(framesA);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
//		createTestChart(framesA);			     
		
		
		correlations = new int[framesA.size()-dlugosc_okna_czasowego_n];
		CorrelationObject[] correlations_for_test_chart = new CorrelationObject[framesA.size()-dlugosc_okna_czasowego_n];
		statistic_for_t = new ArrayList<Statistics>();

  	  	int id = 0;
  	  	int temp_czas_poczatkowy = czas_poczatkowy_t;	
  	  	while(czas_poczatkowy_t < framesA.size() - dlugosc_okna_czasowego_n*2-1){
  	  		CorrelationObject[] correlation_for_t = calculateAutocorrelation(framesA);
//  	  	System.out.println("result");
//  	  	for(CorrelationObject c : correlation_for_t){
//  	  		System.out.println(c.id);
//  	  	}
	  	  	if(czas_poczatkowy_t == przesuniecie_do_testow){
//	  	  		System.out.println("Wykres korelacji do testów...");
	  	  		correlations_for_test_chart = correlation_for_t;
	  	  		createChartForCorrelation(correlations_for_test_chart);
	  	  		}
	  	  correlationForTest.add(correlation_for_t);
	  	  if(k_poczatkowe<correlation_for_t.length){
	  		CorrelationObject[] correlation_for_sort = Arrays.copyOfRange(correlation_for_t, k_poczatkowe, correlation_for_t.length);
//  	  		Arrays.sort(correlation_for_sort);
//  	  		System.out.println("czas poczatkowy: "+czas_poczatkowy_t);
  	  		
//  	  		CorrelationObject maxCorrelation = correlation_for_sort[0];
//  	  		for(int i = 0; i<correlation_for_sort.length; i++){
//  	  			if(correlation_for_sort[i].value == maxCorrelation.value){
//  	  				System.out.println("zmiana");
//  	  				maxCorrelation = correlation_for_sort[i];
//  	  			}
//  	  			else{
//  	  				correlations[czas_poczatkowy_t] = maxCorrelation.id;   
//  	  				System.out.println(maxCorrelation.id);
//  	  				break;
//  	  			}
  	  		
//  	  		}
//  	  		correlations[czas_poczatkowy_t] = maxCorrelation.id; 
//	  		System.out.println("id: "+czas_poczatkowy_t);
	  	
  	  	correlations[czas_poczatkowy_t] = getMaxAutocorrelation(correlation_for_sort);
	  	  }

	  	  
//  	  		System.out.println(correlation_for_t[0].id);
  	  		czas_poczatkowy_t++;
  	  		statistic_for_t.add(calculateStatistic(framesA));
  	  	}
  	  	
  	  	czas_poczatkowy_t = temp_czas_poczatkowy;
  	    stdForAtuocorrelation = new ArrayList<Double>();
	  	while(czas_poczatkowy_t < correlations.length - dlugosc_okna_czasowego_dla_autokorelacji*2-1){
  	  		czas_poczatkowy_t++;
  	  		stdForAtuocorrelation.add(calculateAutocorrelationStd(correlations));
  		
  	  	}
//	  	  System.out.println("correlation:");
//	  	  int i=0;
//	  	  for(int c : correlations){
//	  		  System.out.println(i+" "+c);
//	  		  i++;
//	  	  }
	  	  	
//		int [] correlations = new int[framesA.size()-dlugosc_okna_czasowego_n-dlugosc_przesuniecia_k-1-czas_poczatkowy_t];
//		
//		System.out.println("frooom: " +framesA.size()+" "+ dlugosc_okna_czasowego_n +" "+ " " +dlugosc_przesuniecia_k + " "+ czas_poczatkowy_t);
//		for(int t=czas_poczatkowy_t; t<framesA.size()-dlugosc_okna_czasowego_n-1-dlugosc_przesuniecia_k-czas_poczatkowy_t; t++)
//		{
////			System.out.println("t from: "+ t);
//			System.out.println("frooom: " +framesA.size()+" "+ dlugosc_okna_czasowego_n +" "+ " " +dlugosc_przesuniecia_k + " "+ czas_poczatkowy_t);
//			CorrelationObject[] correlation = calculateAutocorrelation(framesA, t);
////			System.out.println("rozmiar correlation: "+correlation.length);
//			Arrays.sort(correlation);
//			correlations[t] = correlation[correlation.length-1].id;
////		}
//		System.out.println("korelacje:");
//		for(int c: correlations){
//			System.out.println(c);
//		}
		try {
			createCorrelationInTimeChart(correlations);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			createStatisticsChart(statistic_for_t);
			createAllChart(statistic_for_t, correlations, framesA, stdForAtuocorrelation);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for(Frame f : framesA){
			stats.addValue(f.getAvgC());
		}
		
//		Double mean = stats.getMean();
//		Double std = stats.getStandardDeviation();
//		Double variance = stats.getPopulationVariance();
//		Double median = stats.getPercentile(50);
//		
//		System.out.println("œrednia: "+ mean);
//		System.out.println("odchylenie: "+ std);
//		System.out.println("wariancja: "+variance);
//		System.out.println("mediana: "+ median);
		
	}
	public static void generateAllChart(){
		try {
			System.out.println("generatAllChart");
			createAllChart(statistic_for_t, correlations, framesA, stdForAtuocorrelation);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int getMaxAutocorrelation(CorrelationObject[] correlation){
		
		float values[] = new float[correlation.length];
		int i = 0;
		for(CorrelationObject c : correlation){
			values[i] = c.value;
			i++;
		}
		FindPeak fp = new FindPeak();
		fp.peaks.clear();
		fp.peak(values);
		if(fp.peaks.size() == 0){
			return 0;
		}
		return correlation[fp.peaks.get(0)].id;
//		CorrelationObject maxCorrelation = correlation[0];
//		float avg = 0;
//		int numberDown = 0;
//		int numberUp = 0;
//		boolean isDown = false;
//		boolean isUp = false;
//		
//		for(CorrelationObject c: correlation){
//			avg += c.value;			
//		}
//		avg /= correlation.length;
//		System.out.println("avg "+ avg);
//		for(CorrelationObject c: correlation){
//			System.out.println("c: "+c.value+" max: "+maxCorrelation.value);
//			if(c.value > avg){
//				if(isDown){
//					break;
//				}
//				if(Math.abs(c.value - maxCorrelation.value) < 0.005){
//					//nic nie rób
//				}					
//				else if(c.value > maxCorrelation.value){
//					maxCorrelation = c;
//					numberUp++;
//					if(numberUp>20){
//						isUp = true;
//						numberDown = 0;
//					}
//				}
//				else{
//					numberDown++;
//					if(numberDown>20 && isUp){
//						isDown=true;
//						numberUp = 0;
//					}
//				}
//					
////				else if(c.value - maxCorrelation.value < -0.005)
////					break;

//			}
//		}
//		return maxCorrelation.id;
	}
	public static Statistics calculateStatistic(ArrayList<Frame> frames){

	  float[] t1 = new float[dlugosc_okna_czasowego_n];
	
	  int id = 0;
	  for(int j=czas_poczatkowy_t; j<dlugosc_okna_czasowego_n+czas_poczatkowy_t; j++){
		 t1[id] = frames.get(j).getAvgC();
		  id++;
	  }	    	 
	  Statistics stat = new Statistics(czas_poczatkowy_t, t1);
	 
//	    	  System.out.println("c: "+ correlation[i-1].id + " " + i);
		 
	  return stat;
	}
	
	public static double calculateAutocorrelationStd(int[] correlations){
		System.out.println("Calculate autocorrelation std");
		DescriptiveStatistics stats = new DescriptiveStatistics();


		  float[] t1 = new float[dlugosc_okna_czasowego_dla_autokorelacji];
		
		  int id = 0;
		  for(int j=czas_poczatkowy_t; j<dlugosc_okna_czasowego_dla_autokorelacji+czas_poczatkowy_t; j++){
			 t1[id] = correlations[j];			  		 
			  id++;
		  }	    	
		  for(float f : t1){
				stats.addValue(f);
			}	
		  double std = stats.getStandardDeviation();
		  
		  return std;
		}
	
	
	public static void calculateForVector(){
		FileUtils fu = new FileUtils();
//		String pomiarA = fu.openFile("pomiarA");
//		String pomiarA = fu.openFile("flow01_15_5Hzpow_80rot.txt");
//		String pomiar = fu.openFile("test_input.txt");
		
		String pomiar = fu.openFile("new_test2_15_5_100Hz.csv");
//		String pomiar = fu.openFile("new_test2_15_5_80Hz.csv");
//		String pomiar = fu.openFile("new_test2_17_80Hz.csv");
		ArrayList<Frame> frames = fu.getFrames(pomiar, 28);
		
		
		ArrayList<Frame> framesA = new ArrayList<Frame>();
		ArrayList<Frame> framesB = new ArrayList<Frame>();
		int i = 0, idA =0, idB=0;
		for(Frame f : frames){
			if(i%2 == 0){
				f.setTime(idA);
				framesA.add(f);
				idA++;
			}
			else{
				f.setTime(idB);
				framesB.add(f);
				idB++;
			}
			i++;
		}
			createChartForFrames(framesA, framesB);
			createTestChart(framesA, framesB);		
		      
		      fu.saveToFile("A", framesA.toString());
		      fu.saveToFile("B", framesB.toString());
		  		      
		      CorrelationObject[] correlation = calculateCorrelation(framesA, framesB);
		      
		      createChartForCorrelation(correlation);
		      Arrays.sort(correlation);
	}
	
	public static void createChartForFrames(ArrayList<Frame> framesA, ArrayList<Frame> framesB){
		ChartUtils chart = new ChartUtils(
				   gui.chartDataPanel,
			      "Measurement" ,
			      "Measurement",
			      "time",
			      "avg",
			      framesA,
			      framesB);
		gui.setVisible(true);
	}
	
	public static void createChartForFrames(ArrayList<Frame> frames) throws IOException{
		System.out.println("rysuje wykresik");
		ChartUtils chart = new ChartUtils(
				   gui.chartDataPanel,
			      "Przebieg" ,
			      "Przebieg",
			      "czas[ramka]",
			      "wartoœæ",
			      frames);
		gui.setVisible(true);
		
	}
	
	public static void createStatisticsChart(ArrayList<Statistics> statistic_for_t) throws FileNotFoundException, IOException{
		Statistics [] statisticsArray = new Statistics[statistic_for_t.size()];
		int i = 0;
		for(Statistics s: statistic_for_t){
			statisticsArray[i] = s;
			i++;
		}
		ChartUtils chart = new ChartUtils(
				   gui.chartStatisticPanel,
			      "Dane statystyczne" ,
			      "Dane statystyczne",
			      "czas[ramka]",
			      "wartoœæ",
			      statisticsArray);
		gui.setVisible(true);
	}
	
	public static void createAllChart(ArrayList<Statistics> statistic_for_t, int[] correlation, ArrayList<Frame> frames, ArrayList<Double> stdForAtuocorrelation) throws FileNotFoundException, IOException{
		System.out.println("CreateAllChart");
		Statistics [] statisticsArray = new Statistics[statistic_for_t.size()];
		int i = 0;
		for(Statistics s: statistic_for_t){
			statisticsArray[i] = s;
			i++;
		}
		
		chart.createChartForAll(
				   gui.chartAllPanel,
			      "Dane statystyczne" ,
			      "Dane statystyczne",
			      "czas[ramka]",
			      "wartoœæ",
			      statisticsArray,
			      correlation,
			      frames,
			      stdForAtuocorrelation);
		  gui.setVisible(true);
		  GUI.allPanel.validate();
		  GUI.allPanel.repaint();
		  GUI.tabbedPanel.setSelectedComponent(GUI.allPanel);
	}
	
	public static void refreshAllChart() {
		
		try {
			chart.refreshChartForAll(
					   gui.chartAllPanel,
				      "Dane statystyczne" ,
				      "Dane statystyczne",
				      "czas[ramka]",
				      "wartoœæ");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  gui.setVisible(true);
		  GUI.allPanel.validate();
		  GUI.allPanel.repaint();
		  GUI.tabbedPanel.setSelectedComponent(GUI.allPanel);

	}
	
	public static void createChartForTest(ArrayList<Frame> framesA, ArrayList<Frame> framesB){
		ChartUtils chart = new ChartUtils(
				   gui.chartTestPanel,
			      "Measurement" ,
			      "Measurement",
			      "time",
			      "avg",
			      framesA,
			      framesB);
		gui.setVisible(true);
	}
	public static void createChartForPixel(float[] framesA, float[] framesB) throws IOException{
		ChartUtils chart = new ChartUtils(
				 gui.chartDataPanel,
			      "Measurement" ,
			      "Measurement",
			      "time",
			      "C",
			      framesA,
			      framesB);
		gui.setVisible(true);

	}
	public static void createChartForPixel(float[] framesA) throws IOException{
		ChartUtils chart = new ChartUtils(
				 gui.chartDataPanel,
			      "Measurement" ,
			      "Measurement",
			      "time",
			      "C",
			      framesA);
		gui.setVisible(true);

	}
	
	public static void createTestChartForPixel(float[] framesA, float[] framesB) throws IOException{
		ChartUtils chart = new ChartUtils(
				 gui.chartTestPanel,
			      "Measurement" ,
			      "Measurement",
			      "time",
			      "C",
			      framesA,
			      framesB);
		gui.setVisible(true);


	}
	
	
	public static void createChartForCorrelation(CorrelationObject[] correlation){
		ChartUtils chart = new ChartUtils(
				  gui.chartCorrelationPanel,
			      "Korelacja" ,
			      "Korelacja",
			      "czas[ramka]",
			      "wartoœæ",
			      correlation);
		
		gui.setVisible(true);
	}
	
	public static void createCorrelationInTimeChart(int[] correlation) throws IOException{
		ChartUtils chart = new ChartUtils(
				  gui.chartCorrelationInTimePanel,
			      "Autokorelacja" ,
			      "Autokorelacja",
			      "czas[ramka]",
			      "przesuniecie dla maksymalnej korelacji[ramka]",
			      correlation);
		
		gui.setVisible(true);
	}
	
	
	public static CorrelationObject[] calculateAutocorrelation(ArrayList<Frame> frames){
//		CorrelationObject[] correlation = new CorrelationObject[frames.size()-dlugosc_okna_czasowego_n*2-czas_poczatkowy_t-1];
//		 for(int i=1; i<frames.size()-dlugosc_okna_czasowego_n*2-czas_poczatkowy_t; i++){
		CorrelationObject[] correlation;
//		System.out.println(czas_poczatkowy_t);
		if(dlugosc_przesuniecia_k + dlugosc_okna_czasowego_n> frames.size()-czas_poczatkowy_t)
		{
		//	System.out.println("if");
//			 correlation = new CorrelationObject[frames.size()-dlugosc_okna_czasowego_n*2-czas_poczatkowy_t];
				correlation = new CorrelationObject[1];
				correlation[0] = new CorrelationObject(0, 0);
				return correlation;
		}
		else{
			correlation = new CorrelationObject[dlugosc_przesuniecia_k];
		

		
		 for(int i=0; i<dlugosc_przesuniecia_k; i++){
//			 if(i> frames.size()-dlugosc_okna_czasowego_n*2-czas_poczatkowy_t){
//				// System.out.println("Break");
//				 break;
//			 }
		//	  System.out.println(correlation.length);
	    	  float[] t1 = new float[dlugosc_okna_czasowego_n];
	    	  float[] t2 = new float[dlugosc_okna_czasowego_n];
	    	  int id = 0;
	    	  for(int j=czas_poczatkowy_t; j<dlugosc_okna_czasowego_n+czas_poczatkowy_t; j++){
	    		 t1[id] = frames.get(j).getAvgC();
	    		  id++;
	    	  }
	    	  id = 0;
	    	  for(int j=i+czas_poczatkowy_t; j<dlugosc_okna_czasowego_n+i+czas_poczatkowy_t; j++){
	    		  t2[id] = frames.get(j).getAvgC();
	    		  id++;
	    	  }
//	    	  System.out.println(i);
	    	  PearsonCorrelation pc = new PearsonCorrelation(t1, t2);
	    	  correlation[i] = new CorrelationObject(pc.correlation, i);
//	    	  System.out.println("c: "+ correlation[i-1].id + " " + i);
		 }
	    	  return correlation;
		}
	}
//		CorrelationObject[] correlation = new CorrelationObject[dlugosc_przesuniecia_k];
//		correlation[0] = new CorrelationObject(0, 0);
//		for(int l=1; l<dlugosc_przesuniecia_k; l++){
//			float[] t1 = new float[dlugosc_okna_czasowego_n+1];
//	  	  	float[] t2 = new float[dlugosc_okna_czasowego_n+1];
//	  	  	int id = 0;
//	  	  	while(czas_poczatkowy_t < frames.size() - dlugosc_okna_czasowego_n){
//	  	  		CorrelationObject[] correlation_for_t = calculateCorrelation(frames, frames);
//	  	  		correlation_for_t
//	  	  	}
////			for(int i=t; i<t+dlugosc_okna_czasowego_n; i++){
//				t1[id] = frames.get(i).getAvgC();
//				id++;
//			}
//			id = 0;
//			//System.out.println("j: ");
//			for(int j=t+l; j<t+l+dlugosc_okna_czasowego_n; j++){
//				//System.out.println(j);
//				t2[id] = frames.get(j).getAvgC();
//				id++;
//			}
//			System.out.println("t + l + k");
//			System.out.println(t+" " + l+ " "+dlugosc_okna_czasowego_n);
//			System.out.println("autocorrelation: t1:");
//			for(float tt:t1){
////				System.out.println(tt);
//			}
//			System.out.println("autocorrelation: t2:");
//			for(float tt:t2){
////				System.out.println(tt);
//			}
//	    	  PearsonCorrelation pc = new PearsonCorrelation(t1, t2);
////	    	  System.out.println("corr: "+pc.correlation);
//	    	  correlation[l] = new CorrelationObject(pc.correlation, l);	
////	    	  System.out.println(correlation[l].value);
//		}
//		System.out.println("wyniki: ");
//		for(CorrelationObject co : correlation){
//			System.out.println(co.value);
//		}
//	    return correlation;
//	}
	public static CorrelationObject[] calculateCorrelation(ArrayList<Frame> framesA, ArrayList<Frame> framesB){
		CorrelationObject[] correlation = new CorrelationObject[dlugosc_okna_czasowego_n];
		 for(int i=dlugosc_przesuniecia_k; i<dlugosc_okna_czasowego_n+dlugosc_przesuniecia_k; i++){
	    	  float[] t1 = new float[dlugosc_okna_czasowego_n];
	    	  float[] t2 = new float[dlugosc_okna_czasowego_n];
	    	  int id = 0;
	    	  for(int j=czas_poczatkowy_t; j<dlugosc_okna_czasowego_n+czas_poczatkowy_t; j++){
	    		  t1[id] = framesA.get(j).getAvgC();
	    		  id++;
	    	  }
	    	  id = 0;
	    	  for(int j=i+czas_poczatkowy_t; j<dlugosc_okna_czasowego_n+i+czas_poczatkowy_t; j++){
	    		  t2[id] = framesB.get(j).getAvgC();
	    		  id++;
	    	  }
	    	  PearsonCorrelation pc = new PearsonCorrelation(t1, t2);
	    	  correlation[i-dlugosc_przesuniecia_k] = new CorrelationObject(pc.correlation, i);	
//	    	  System.out.println("t1");
//	    	  for(float f: t1){
//	    		  System.out.println(f);
//	    	  }
//	    	  System.out.println("t2");
//	    	  for(float f : t2){
//	    		  System.out.println(f);
//	    	  }
		 }
	    	  return correlation;
	}
	
	public static CorrelationObject[] calculateCorrelationForPixel(float[] framesA, float[] framesB){
		temp++;
		CorrelationObject[] correlation = new CorrelationObject[dlugosc_okna_czasowego_n];
		 for(int i=dlugosc_przesuniecia_k; i<dlugosc_okna_czasowego_n+dlugosc_przesuniecia_k; i++){

	    	  float[] t1 = new float[dlugosc_okna_czasowego_n];
	    	  float[] t2 = new float[dlugosc_okna_czasowego_n];
	    	  int id = 0;
	    	  
	    	  for(int j=czas_poczatkowy_t; j<dlugosc_okna_czasowego_n+czas_poczatkowy_t; j++){
	    		  t1[id] = framesA[j];
	    		  id++;
	    	  }
	    	  id = 0;
	    	  for(int j=i+czas_poczatkowy_t; j<dlugosc_okna_czasowego_n+i+czas_poczatkowy_t; j++){
	    		  t2[id] = framesB[j];
	    		  id++;
	    	  }
	    	  PearsonCorrelation pc = new PearsonCorrelation(t1, t2);
	    	  correlation[i-dlugosc_przesuniecia_k] = new CorrelationObject(pc.correlation, i);
		 }
	    	  return correlation;
	}
	public static <T> boolean contains(final T[] array, final T v) {
	    for (final T e : array)
	        if (e == v || v != null && v.equals(e))
	            return true;

	    return false;
	}
	
	public static void createPixelMapForAllTimeButton(){
		System.out.println("createPixelMapForAllTimeButton");
		GUI.tabbedPanel.setSelectedComponent(GUI.pixelPanel);

//		pixelsToAutocorrelation.clear();
//		FileUtils fu = new FileUtils();
//		String pomiarA = fu.openFile("plik_15_5_100Hz_planeA_OK.txt");
//		ArrayList<Frame> framesA = fu.getFramesForPixel(pomiarA, 1024);
//	
//		float[][] pixelsA = new float[framesA.get(0).getC().length][framesA.size()];
//		
//		int id = 0;
//		for(int i=0; i<framesA.get(0).getC().length; i++){
//			for(int j=0; j<framesA.size(); j++){	
//				pixelsA[id][j] = framesA.get(j).getC()[i];
//			}
//			id++;
//			}
//		
//		Dimension d = new Dimension(100,100);
//		GUI1.pixelPanel.removeAll();
//		GUI1.squarePanel.removeAll();
//        Insets insetsZero = new Insets(0, 0, 0, 0);
//        GridBagLayout gridbagForPixelMap = new GridBagLayout();
//        GUI1.squarePanel.setLayout(gridbagForPixelMap);
//        for(int t=0; t<pixelsA[0].length; t++){
//            for(int i=0; i<32; i++){
//            	for(int j=0; j<32; j++){
//            		final Pixel button = new Pixel();
//            		button.setId(i*32+j);
//            		button.setValue(pixelsA[button.getId()][t]);
////            		System.out.println(pixelsA[i*32+j][t]);
//            		int rgbColor = (int) (100000*pixelsA[i*32+j][t]);
//            		final Color c = new Color(rgbColor);
//            		button.setBackground(c);
//            		button.addActionListener(new ActionListener() {
//    					
//    					public void actionPerformed(ActionEvent arg0) {
//    						if(button.getBackground() == Color.GRAY){
//    							button.setBackground(c);
//    							pixelsToAutocorrelation.remove((Integer)button.getId());
//    						}
//    						else{
//    							button.setBackground(Color.GRAY);
//    							pixelsToAutocorrelation.add(button.getId());
//    						}
//    							
//    						
//    					}
//    				});
//            		GUI1.squarePanel.add(button);
//            		gridbagForPixelMap.setConstraints(button, new GridBagConstraints(i, j, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insetsZero, 0, 0));
//            	}
//            }
//            GUI1.pixelPanel.add(GUI1.squarePanel);
//            GUI1.pixelPanel.updateUI();
//        }

	}

	public static void createPixelMap(){
//		GUI1.pixelPanel.add(loadingPanel());
		pixelsToAutocorrelation.clear();
		framesA = loadFile();
//		FileUtils fu = new FileUtils();
//		String pomiarA = fu.openFile("plik_15_5_100Hz_planeA_OK.txt");
//		ArrayList<Frame> framesA = fu.getFramesForPixel(pomiarA, 1024);
	
		float[][] pixelsA = new float[framesA.get(0).getC().length][framesA.size()];
		
		int id = 0;
		for(int i=0; i<framesA.get(0).getC().length; i++){
			for(int j=0; j<framesA.size(); j++){	
				pixelsA[id][j] = framesA.get(j).getC()[i];
			}
			id++;
			}
		
		Dimension d = new Dimension(100,100);
		GUI.pixelPanel.removeAll();
		GUI.squarePanel.removeAll();
        Insets insetsZero = new Insets(0, 0, 0, 0);
        GridBagLayout gridbagForPixelMap = new GridBagLayout();
        GUI.squarePanel.setLayout(gridbagForPixelMap);
        for(int i=0; i<32; i++){
        	for(int j=0; j<32; j++){
        		final Pixel button = new Pixel();
        		button.setId(i*32+j);
        		button.setValue(pixelsA[button.getId()][timeForPixelMap]);
//        		System.out.println(pixelsA[i*32+j][timeForPixelMap]);
//        		int rgbColor = (int) (16777215*pixelsA[i*32+j][timeForPixelMap]);
//        		final Color c = new Color(rgbColor);
        		final Color c = Color.getHSBColor((float) (1 - pixelsA[i*32+j][timeForPixelMap]) * 0.6f, 1.0f, 1.0f);
//        		final Color c = Color.getHSBColor((float) pixelsA[i*32+j][timeForPixelMap]*100 / (float) 150, 0.85f, 1.0f);
        		button.setBackground(c);
        		button.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						if(button.getBackground() == Color.GRAY){
							button.setBackground(c);
							pixelsToAutocorrelation.remove((Integer)button.getId());
						}
						else{
							button.setBackground(Color.GRAY);
							pixelsToAutocorrelation.add(button.getId());
						}													
					}
				});
        		GUI.squarePanel.add(button);
        		gridbagForPixelMap.setConstraints(button, new GridBagConstraints(i+1, j, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insetsZero, 0, 0));
        	}
        }
        
        GridBagLayout gridbagForLegend = new GridBagLayout();
        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(gridbagForLegend);
        for(int i = 0; i<=100; i++){
        	JLabel label = new JLabel();
        	label.setText(" ");
        	label.setOpaque(true);
        	             

        	Color c = Color.getHSBColor((float) (1- (float)i/ 100) * 0.6f, 1.0f, 1.0f);
        
        	label.setBackground(c);
        	label.setForeground(c);
        	label.setMinimumSize(new Dimension(1,10));
        	label.setMaximumSize(new Dimension(1, 10));
        	label.setPreferredSize(new Dimension(1, 10));
        	label.setBounds(0, 0, 0, 0);
        	JLabel label2 = new JLabel();
        	if(i%20 == 0){
        		label2.setText(""+i/100.0);
        	}
        	else{
        		label2.setText("");
        	}
        	gridbagForLegend.setConstraints(label, new GridBagConstraints(i, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insetsZero, 0, 0));
        	gridbagForLegend.setConstraints(label2, new GridBagConstraints(i, 1, 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, insetsZero, 0, 0));
        	legendPanel.add(label);
        	legendPanel.add(label2);
//        	gridbagForPixelMap.setConstraints(label, new GridBagConstraints(32, i, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insetsZero, 0, 0));
        	
        }
//        gridbagForPixelMap.setConstraints(legendPanel, new GridBagConstraints(0, 0, 32, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insetsZero, 0, 0));
        
        GUI.tabbedPanel.setSelectedComponent(GUI.pixelPanel);
        GUI.pixelPanel.add(GUI.squarePanel);
        GUI.pixelPanel.add(legendPanel);
        GUI.pixelPanel.validate();
        GUI.pixelPanel.repaint();
     
        
	}
	
	private static JPanel loadingPanel() {
	    JPanel panel = new JPanel();
	    BoxLayout layoutMgr = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
	    panel.setLayout(layoutMgr);
//
//	    ClassLoader cldr = this.getClass().getClassLoader();
//	    java.net.URL imageURL   = cldr.getResource("ajax-loader.gif");
	    ImageIcon imageIcon = new ImageIcon("ajax-loader.gif");
	    JLabel iconLabel = new JLabel();
	    iconLabel.setIcon(imageIcon);
	    imageIcon.setImageObserver(iconLabel);

	    JLabel label = new JLabel("Loading...");
	    panel.add(iconLabel);
	    panel.add(label);
	    return panel;
	}
	
	public static void loadingAnimation(){
		 JFrame frame = new JFrame("Loading...");

		 ImageIcon loading = new ImageIcon("ajax-loader.gif");
		    frame.add(new JLabel("loading... ", loading, JLabel.CENTER));

		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setSize(400, 300);
		    frame.setVisible(true);
	}
	
	
	public static ArrayList<Frame> loadFile(){
		if(isPixel){
			System.out.println("Load file for pixel");
			correlationForTest = new ArrayList<CorrelationObject[]>();
			System.out.println("Pixel + autokorlecja");
			FileUtils fu = new FileUtils();
			String pomiarA = fu.loadFile(file);
			ArrayList<Frame> framesA = fu.getFramesForPixel(pomiarA, 1024);
			
			return framesA;
		}
		return null;
	}
	
	public static void calculateAvgMeasurementForPixel(){
		ArrayList<Frame> framesA = loadFile();
		
		try {
			createChartForFrames(framesA);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static JFrame f;
	public static void createLoadingFrame(){
		 f = new JFrame("Proszê czekaæ...");
		    f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		    Container content = f.getContentPane();
		    JProgressBar progressBar = new JProgressBar();
		    progressBar.setIndeterminate(true);
		    Border border = BorderFactory.createTitledBorder("£adowanie...");
		    progressBar.setBorder(border);
		    content.add(progressBar, BorderLayout.NORTH);
		    f.setAlwaysOnTop(true);
		    f.setLocationRelativeTo(null);
		    f.setSize(300, 100);
		    f.setVisible(true);
	}
	
	public static void closeLoadingFrame(){
		gui.setEnabled(true);
		f.setVisible(false);
	}
	
	public static void calculateAutocorrelationForPixel(){		
		createLoadingFrame();

		framesA = loadFile();
	
		float[][] pixelsA = new float[framesA.get(0).getC().length][framesA.size()];
		
		int id = 0;
		for(int i=0; i<framesA.get(0).getC().length; i++){
			for(int j=0; j<framesA.size(); j++){	
				pixelsA[id][j] = framesA.get(j).getC()[i];
			}
			id++;
			}

		ArrayList<Frame> framesToAutocorrelation = new ArrayList<Frame>();
		
		for(Frame f: framesA){
			Frame frame = new Frame();
			frame.setTime(f.getTime());
			frame.setAvgC(0);
			for(int i : pixelsToAutocorrelation){		
				frame.setAvgC(frame.getAvgC()+f.getC()[i]);
			}
			frame.setAvgC(frame.getAvgC()/pixelsToAutocorrelation.size());
//			System.out.println(frame.getAvgC());
			framesToAutocorrelation.add(frame);
		}
		
		try {
			createChartForFrames(framesToAutocorrelation);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		correlations = new int[framesToAutocorrelation.size()-dlugosc_okna_czasowego_n];
		CorrelationObject[] correlations_for_test_chart = new CorrelationObject[framesToAutocorrelation.size()-dlugosc_okna_czasowego_n];
		statistic_for_t = new ArrayList<Statistics>();
		stdForAtuocorrelation = new ArrayList<Double>();

  	  	id = 0;
  	  	int temp_czas_poczatkowy = czas_poczatkowy_t;
  	  	while(czas_poczatkowy_t < framesToAutocorrelation.size() - dlugosc_okna_czasowego_n*2-1){
  	  		CorrelationObject[] correlation_for_t = calculateAutocorrelation(framesToAutocorrelation);

	  	  	if(czas_poczatkowy_t == przesuniecie_do_testow){
//	  	  		System.out.println("Wykres korelacji do testów...");
	  	  		correlations_for_test_chart = correlation_for_t;
	  	  		createChartForCorrelation(correlations_for_test_chart);
	  	  		}
	  	  correlationForTest.add(correlation_for_t);
	  	  if(k_poczatkowe<correlation_for_t.length){
	  		CorrelationObject[] correlation_for_sort = Arrays.copyOfRange(correlation_for_t, k_poczatkowe, correlation_for_t.length);

//	  		System.out.println("id: "+czas_poczatkowy_t);
	  		
  	  	correlations[czas_poczatkowy_t] = getMaxAutocorrelation(correlation_for_sort);
	  	  }

	  	  
//  	  		System.out.println(correlation_for_t[0].id);
  	  		czas_poczatkowy_t++;
  	  		statistic_for_t.add(calculateStatistic(framesToAutocorrelation));
  	  	}
  	  	czas_poczatkowy_t = temp_czas_poczatkowy;
  	  	id = 0;
  	  	while(czas_poczatkowy_t < correlations.length - dlugosc_okna_czasowego_dla_autokorelacji*2-1){
  	  		czas_poczatkowy_t++;
  	  		stdForAtuocorrelation.add(calculateAutocorrelationStd(correlations));
  		
  	  	}
  	  	

//	  	  System.out.println("correlation:");
//	  	  int i=0;
//	  	  for(int c : correlations){
//	  		  System.out.println(i+" "+c);
//	  		  i++;
//	  	  }
	  	  	

		try {
			createCorrelationInTimeChart(correlations);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			createStatisticsChart(statistic_for_t);
			createAllChart(statistic_for_t, correlations, framesToAutocorrelation, stdForAtuocorrelation);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			try {
				createChartForPixel(pixelsA[pixel]);
			} catch (IOException e) {}
		GUI1.tabbedPanel.setSelectedComponent(GUI1.allPanel);
		closeLoadingFrame();
	}
	public static void calculateForPixel(){
		
		FileUtils fu = new FileUtils();
//		String pomiarA = fu.openFile("f40_v800_hor.aim");
		String pomiarA = fu.openFile("plik_15_5_100Hz_planeA_OK.txt");
		ArrayList<Frame> framesA = fu.getFramesForPixel(pomiarA, 1024);
			      
//	    String pomiarB = fu.openFile("f40_v800_par.aim");
		String pomiarB = fu.openFile("plik_15_5_100Hz_planeB_OK.txt");
		ArrayList<Frame> framesB = fu.getFramesForPixel(pomiarB, 1024);
	
		float[][] pixelsA = new float[framesA.get(0).getC().length][framesA.size()];
		float[][] pixelsB = new float[framesA.get(0).getC().length][framesA.size()];

		int id = 0;
		for(int i=0; i<framesA.get(0).getC().length; i++){
			for(int j=0; j<framesA.size(); j++){	
				pixelsA[id][j] = framesA.get(j).getC()[i];
				pixelsB[id][j] = framesB.get(j).getC()[i];
			}
			id++;
			}

			double[][] correlationTable = new double [32][32];
			int a = 0, b=0;
//			System.out.println("ilosc pikseli: "+pixelsA.length);
			for(int i=0; i<pixelsA.length; i++){
				CorrelationObject[] correlation = calculateCorrelationForPixel(pixelsA[i], pixelsB[i]);    		     
			      Arrays.sort(correlation);
			      correlationTable[a][b] = correlation[correlation.length-1].id;
			      if(b<31){
			    	  b++;
//			    	  System.out.print(" "+correlation[correlation.length-1].id+" ");
			      }else{
//			    	  System.out.print("\n");
			    	  b=0;
			    	  a++;
			      }
			}
			
			String correlationTableForChart = "";
			for(int i = 0; i<correlationTable.length; i++)
			{
				for(int j=0; j<correlationTable[0].length; j++){
					correlationTableForChart += " "+ correlationTable[i][j];
				}
				correlationTableForChart += "\n";
			}
			HeatChart map = new HeatChart(correlationTable);

			map.setTitle("Correlation pixel map");

			try {
				map.saveToFile(new File("correlation_pixel_map.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				createChartForPixel(pixelsA[pixel], pixelsB[pixel]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			createChartForCorrelation(calculateCorrelationForPixel(pixelsA[pixel], pixelsB[pixel]));
			
			

			float[] t1 = new float[dlugosc_okna_czasowego_n];
			float[] t2 = new float[dlugosc_okna_czasowego_n];
	    	int id1 = 0;
	    	  
	    	for(int j=czas_poczatkowy_t; j<dlugosc_okna_czasowego_n+czas_poczatkowy_t; j++){
	    		  t1[id1] = pixelsA[pixel][j];
	    		  id1++;
	    	}
	    	id1 = 0;
	    	for(int j=dlugosc_przesuniecia_k+czas_poczatkowy_t; j<dlugosc_okna_czasowego_n+dlugosc_przesuniecia_k+czas_poczatkowy_t; j++){
	    		  t2[id1] = pixelsB[pixel][j];
	    		  id1++;
	    	}
	    	try {
				createTestChartForPixel(t1, t2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	  
		    fu.saveToFile("A", framesA.toString());
		    fu.saveToFile("B", framesB.toString()); 
		    fu.saveToFile("correlation_map.txt", correlationTableForChart);
		      
	}
	
	
	public static void createTestChart(ArrayList<Frame> framesA, ArrayList<Frame> framesB){
		ArrayList<Frame> t1 = new ArrayList<Frame>();
		ArrayList<Frame> t2 = new ArrayList<Frame>();

		for(int j=czas_poczatkowy_t; j<dlugosc_okna_czasowego_n+czas_poczatkowy_t; j++){
		  t1.add(framesA.get(j));
//		  System.out.println(j);
		  }

//    	  System.out.println("Create t2: ");
//    	  System.out.println("difference: "+dlugosc_przesuniecia_k);
    	  for(int j=dlugosc_przesuniecia_k+czas_poczatkowy_t; j<dlugosc_okna_czasowego_n+dlugosc_przesuniecia_k+czas_poczatkowy_t; j++){
    		  Frame f = framesB.get(j);
    		  f.setTime(j-dlugosc_przesuniecia_k);
    		  t2.add(f);
//    		  System.out.println(j);
    	  }
	    	  
	    createChartForTest(t1, t2);
	    	  
	}
	
	public static void createTestChart(ArrayList<Frame> frames){
		ArrayList<Frame> t1 = new ArrayList<Frame>();
		ArrayList<Frame> t2 = new ArrayList<Frame>();

//		System.out.println("create t1:");
		for(int j=czas_poczatkowy_t; j<dlugosc_okna_czasowego_n+czas_poczatkowy_t; j++){
		  t1.add(frames.get(j));
//		  System.out.println(j);
		  }

//    	  System.out.println("Create t2: ");
//    	  System.out.println("difference: "+dlugosc_przesuniecia_k);
    	  for(int j=dlugosc_przesuniecia_k+czas_poczatkowy_t; j<dlugosc_okna_czasowego_n+dlugosc_przesuniecia_k+czas_poczatkowy_t; j++){
    		  Frame f = frames.get(j);
    		  f.setTime(j-dlugosc_przesuniecia_k);
    		  t2.add(f);
//    		  System.out.println(j);
    	  }   
//    	  System.out.println("Calosc t1");
//    	  for(Frame f : t1){
//    		  System.out.println(f.getAvgC());
//    	  }
//    	  System.out.println("calosc t2");
//    	  for(Frame f: t2){
//    		  System.out.println(f.getAvgC());
//    	  }
	    createChartForTest(t1, t2);	  
	}
	

	public static void main(String[] args) {
		
	    gui = new GUI();
	    gui.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    gui.setVisible(true);
	    
	}


}
