import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileUtils {
		
	public ArrayList<Frame> getFramesForPixel(String file, int size){
//		System.out.println("get frames for pixel");
		ArrayList<Frame> result = new ArrayList<Frame>();
		Frame frame = null;
		float[] C = new float[size];
		int i = 0;
		int lineCounter = 0;
		int frameID = 0;
		frame = new Frame();
		frame.setTime(frameID);
		for(String line : file.split("\n")){
			lineCounter++;
				if(!line.trim().matches("##EOF")){
					
					if(line.trim().matches("##.*")){
//						System.out.println("do pominiêcia");
					}
					else{
	
			        	String[] n = line.split(" ");
			        	for(String k:n){
			        		if(k.trim().length()>0){
			        			float tmp =  Float.parseFloat(k);
			        			if(tmp>1){
			        				tmp = 1;
			        			}
			        			else if(tmp < -1){
			        				tmp = -1;
			        			}
			        			C[i] = tmp;
			        			i++;
			        		}		        			
			        	}
			        if(i == size){
			        	for(int k=0; k<C.length; k++){
			        	}
			        	frame.setC(C);
			        	result.add(frame);
			        	i = 0;
			        	C = new float[size];
			        	frameID++;
			        	frame = new Frame();
			        	frame.setTime(frameID);
			        	lineCounter = 0;
			        }				
				}
				}
		}
		return result;

		
	}
	
	public ArrayList<Frame> getFrames(String file, int size){
		ArrayList<Frame> result = new ArrayList<Frame>();
		Frame frame = null;
		float[] C = new float[size];
		int i = 0;
		int lineCounter = 0;
		for(String line : file.split("\n")){
			lineCounter++;
				if(!line.trim().matches("##EOF")){
						
						if(line.trim().matches("##.*")){
//							System.out.println("do pominiêcia");
						}
						else{
		        	
			        	frame = new Frame();
			        	frame.setTime(lineCounter);

			        	String[] n = line.split(",");
			        	for(String k:n){
			        		if(k.trim().length()>0){
			        			float tmp =  Float.parseFloat(k);
//			        			System.out.println(tmp);
			        			if(tmp>1){
			        				tmp = 1;
			        			}
			        			else if(tmp < -1){
			        				tmp = -1;
			        			}		        				
			        			C[i] = tmp;
			        			i++;
			        		}		        			
			        	}
			        }
			        if(i == size){
			        	frame.setC(C);
			        	result.add(frame);
			        	i = 0;
			        	C = new float[size];
			        }
				}
		}
//		}
		return result;
	}
	public ArrayList<Frame> getFramesForGenerate(String file){
		ArrayList<Frame> result = new ArrayList<Frame>();
		Frame frame = null;
		int lineCounter = 0;
		for(String line : file.split("\n")){
			frame = new Frame();
			frame.setAvgC(Float.parseFloat(line));
			frame.setTime(lineCounter);
			result.add(frame);
			lineCounter++;
		}
		return result;

	}
	public ArrayList<Frame> getFramesForManchester(String file, int size){
		ArrayList<Frame> result = new ArrayList<Frame>();
		Frame frame = null;
		float[] C = new float[size];
		int i = 0;
		int lineCounter = 0;
		for(String line : file.split("\n")){
//			System.out.println("linia pierwsza: " + line);
			
				if(!line.trim().matches("##EOF")){
		        	
						if(line.trim().matches("## frame \\d+ .+")){
//							System.out.println("nowa ramka");
							lineCounter++;
							frame = new Frame();
							frame.setTime(lineCounter);
						}
						else if(line.trim().matches("## .*")){
							//do nothing ;P
						}
						else{


			        	String[] n = line.split(" ");
			        	for(String k:n){
			        		if(k.trim().length()>0){
			        			float tmp =  Float.parseFloat(k);
//			        			System.out.println(tmp);
			        			if(tmp>1){
			        				tmp = 1;
			        			}
			        			else if(tmp < -1){
			        				tmp = -1;
			        			}		        				
			        			C[i] = tmp;
			        			i++;
			        		}		        			
			        	}
			        }
			        if(i == size){
			        	frame.setC(C);
			        	result.add(frame);
			        	i = 0;
			        	C = new float[size];
			        }}
				
		}
		return result;
	}
	
	public String loadFile(File file){
		System.out.println("plik:" + file);
		FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            String str = new String(data, "UTF-8");
            return str;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
	}
	
	public String openFile(String path) {
		File file = new File(path);
		return loadFile(file);
    }
	
	 public boolean saveToFile(String path, String content){
	        try {
	            PrintWriter out = new PrintWriter(path);
	            out.print(content);
	            out.close();
	            return true;
	        } catch (FileNotFoundException ex) {
	            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
	        }

	        return false;
	    }

}
