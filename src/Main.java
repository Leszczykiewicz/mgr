import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.tc33.jheatchart.HeatChart;


public class Main {
	
	public static int k = 100; //300
	public static int t = 0;
	public static int d = 0;
	public static int difference = 20;  //20 / 30
	public static int startPoint = 3000; //2000
	public static GUI1 gui;
	public static int temp = 0;
	public static int pixel = 0;
	public static boolean isVector = true;
	
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
		System.out.println("A: " + framesA.size());
		System.out.println("B: " + framesB.size());
			      
//	      String pomiarB = fu.openFile("pomiarB");
//			ArrayList<Frame> framesB = fu.getFrames(pomiarB, 28);
			createChartForFrames(framesA, framesB);
			createTestChart(framesA, framesB);		
		      
		      fu.saveToFile("A", framesA.toString());
		      fu.saveToFile("B", framesB.toString());
		  		      
		      CorrelationObject[] correlation = calculateCorrelation(framesA, framesB);
		      
		      createChartForCorrelation(correlation);
		      Arrays.sort(correlation);
		      System.out.println(correlation[correlation.length-1].id);
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
	public static void createChartForPixel(float[] framesA, float[] framesB){
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
	
	public static void createTestChartForPixel(float[] framesA, float[] framesB){
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
			      "Correlation" ,
			      "Correlation",
			      "time",
			      "value",
			      correlation);
		
		gui.setVisible(true);
	}
	
	
	public static CorrelationObject[] calculateCorrelation(ArrayList<Frame> framesA, ArrayList<Frame> framesB){
		CorrelationObject[] correlation = new CorrelationObject[k];
		 for(int i=difference; i<k+difference; i++){
	    	  float[] t1 = new float[k];
	    	  float[] t2 = new float[k];
	    	  int id = 0;
	    	  for(int j=startPoint; j<k+startPoint; j++){
	    		  t1[id] = framesA.get(j).getAvgC();
	    		  id++;
	    	  }
	    	  id = 0;
	    	  for(int j=i+startPoint; j<k+i+startPoint; j++){
	    		  t2[id] = framesB.get(j).getAvgC();
	    		  id++;
	    	  }
	    	  PearsonCorrelation pc = new PearsonCorrelation(t1, t2);
	    	  correlation[i-difference] = new CorrelationObject(pc.correlation, i);	    	  	    	  
		 }
	    	  return correlation;
	}
	
	public static CorrelationObject[] calculateCorrelationForPixel(float[] framesA, float[] framesB){
		temp++;
		CorrelationObject[] correlation = new CorrelationObject[k];
		 for(int i=difference; i<k+difference; i++){

	    	  float[] t1 = new float[k];
	    	  float[] t2 = new float[k];
	    	  int id = 0;
	    	  
	    	  for(int j=startPoint; j<k+startPoint; j++){
	    		  t1[id] = framesA[j];
	    		  id++;
	    	  }
	    	  id = 0;
	    	  for(int j=i+startPoint; j<k+i+startPoint; j++){
	    		  t2[id] = framesB[j];
	    		  id++;
	    	  }
	    	  PearsonCorrelation pc = new PearsonCorrelation(t1, t2);
	    	  correlation[i-difference] = new CorrelationObject(pc.correlation, i);
		 }
	    	  return correlation;
	}
	public static <T> boolean contains(final T[] array, final T v) {
	    for (final T e : array)
	        if (e == v || v != null && v.equals(e))
	            return true;

	    return false;
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
			System.out.println("ilosc pikseli: "+pixelsA.length);
			for(int i=0; i<pixelsA.length; i++){
				CorrelationObject[] correlation = calculateCorrelationForPixel(pixelsA[i], pixelsB[i]);    		     
			      Arrays.sort(correlation);
			      correlationTable[a][b] = correlation[correlation.length-1].id;
			      if(b<31){
			    	  b++;
			    	  System.out.print(" "+correlation[correlation.length-1].id+" ");
			      }else{
			    	  System.out.print("\n");
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
			createChartForPixel(pixelsA[pixel], pixelsB[pixel]);
			
			createChartForCorrelation(calculateCorrelationForPixel(pixelsA[pixel], pixelsB[pixel]));
			
			

			float[] t1 = new float[k];
			float[] t2 = new float[k];
	    	int id1 = 0;
	    	  
	    	for(int j=startPoint; j<k+startPoint; j++){
	    		  t1[id1] = pixelsA[pixel][j];
	    		  id1++;
	    	}
	    	id1 = 0;
	    	for(int j=difference+startPoint; j<k+difference+startPoint; j++){
	    		  t2[id1] = pixelsB[pixel][j];
	    		  id1++;
	    	}
	    	createTestChartForPixel(t1, t2);
    	  
		    fu.saveToFile("A", framesA.toString());
		    fu.saveToFile("B", framesB.toString()); 
		    fu.saveToFile("correlation_map.txt", correlationTableForChart);
		      
	}
	
	public static void createTestChart(ArrayList<Frame> framesA, ArrayList<Frame> framesB){
		ArrayList<Frame> t1 = new ArrayList<Frame>();
		ArrayList<Frame> t2 = new ArrayList<Frame>();

	    	  for(int j=startPoint; j<k+startPoint; j++){
	    		  t1.add(framesA.get(j));
	    		  System.out.println(j);

	    	  }

	    	  System.out.println("Create t2: ");
	    	  System.out.println("difference: "+difference);
	    	  for(int j=difference+startPoint; j<k+difference+startPoint; j++){
	    		  Frame f = framesB.get(j);
	    		  f.setTime(j-difference);
	    		  t2.add(f);
	    		  System.out.println(j);
	    	  }
	    	  
	    	  createChartForTest(t1, t2);
	    	  
	}
	

	public static void main(String[] args) {
		
	    gui = new GUI1();
        gui.setVisible(true);
	}


}
